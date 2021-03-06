package controller;

import info.UserInfo;
import info.VoteInfo;
import info.VoteItemModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.VoteService;
import utils.UserUtils;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author Hu.Wang 2020/07/07 14:04
 */
@Controller
@RequestMapping("/vote")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @RequestMapping(value = "/allVote")
    public String allVote(Model model, HttpSession session) {

        List<VoteInfo> list = voteService.queryAllVote();
        // 返回查询数据
        model.addAttribute("votes", list);
        // 生成随机用户
        UserInfo userInfo = UserUtils.getRandomUser();
        // 将用数据插入数据库
        voteService.insertUser(userInfo.getUserId(), userInfo.getUserNickName());

        // 保存一个标志到session，标识用户是否已经投票
        Map<String,Integer> map = new HashMap<>();
        for (VoteInfo voteInfo: list){
            map.put(voteInfo.getVoteId(),0);
        }
        session.setAttribute("flag",map);

        // 将用户数据存入session
        session.setAttribute("userId", userInfo.getUserId());
        session.setAttribute("nickname", userInfo.getUserNickName());
        model.addAttribute("message", session.getAttribute("message"));
        session.removeAttribute("message");
        return "vote_info";
    }

    @RequestMapping(value = "/allVote1")
    public String allVote1(Model model, HttpSession session) {
        // 返回查询数据
        model.addAttribute("votes", voteService.queryAllVote());
        model.addAttribute("message", session.getAttribute("message"));
        session.removeAttribute("message");
        return "vote_info";
    }

    @RequestMapping(value = "/deleteVote", method = RequestMethod.POST)
    @ResponseBody
    public void deleteVote(String voteId, HttpSession session) {

        // 如果id为空，则重定向到投票信息页面
        if (voteId == null) {
            System.out.println("id为空！");
            session.setAttribute("message", "删除失败！");
        }
        System.out.println(voteId);
        int result = voteService.deleteVoteById(voteId);
        if (result == 0) {// 影响行数为0，删除失败
            System.out.println("影响行数为0！");
        } else {
            session.setAttribute("message", "删除成功！");
        }
    }

    @RequestMapping(value = "/updateVote", method = RequestMethod.POST)
    public String updateVote(Model model, VoteInfo voteInfo, VoteItemModel voteItems) {

        // 如果voteInfo或voteItems为空，则重定向到投票信息页面
        if (voteInfo == null || voteItems.getVoteItems() == null) {
            model.addAttribute("votes", voteService.queryAllVote());
            System.out.println("数据为空！");
            model.addAttribute("message", "修改失败！");
            return "vote_info";
        }

        // 设置投票的投票项
        voteInfo.setItems(voteItems.getVoteItems());

        int result = voteService.updateVote(voteInfo);
        if (result == 0) {// 影响行数为0，更新失败
            System.out.println("影响行数为0！");
            model.addAttribute("message", "修改成功！");
        } else {// 影响行数不为0，更新成功
            model.addAttribute("message", "修改成功！");
        }
        model.addAttribute("votes", voteService.queryAllVote());
        return "vote_info";
    }

    @RequestMapping(value = "/addVote", method = RequestMethod.POST)
    public String addVote(VoteInfo voteInfo, VoteItemModel voteItems,HttpSession session) {

        // 如果voteInfo或voteItems为空，则重定向到投票信息页面
        if (voteInfo == null || voteItems.getVoteItems() == null) {
            session.setAttribute("votes", voteService.queryAllVote());
            System.out.println("数据为空！");
            session.setAttribute("message", "新建失败！");
            return "vote_info";
        }
        // 设置投票的投票项
        voteInfo.setItems(voteItems.getVoteItems());

        int result = voteService.addVote(voteInfo);
        if (result == 0) {// 影响行数为0，新建失败
            System.out.println("影响行数为0！");
            session.setAttribute("message", "新建失败！");
        } else {// 影响行数不为0，新建成功
            session.setAttribute("message", "新建成功！");
        }
        return "redirect:/vote/allVote1";
    }

    @RequestMapping(value = "/voting", method = RequestMethod.GET)
    public String voting(Model model, String voteId) {
        model.addAttribute("vote", voteService.queryVoteById(voteId));
        return "vote_submit";
    }

    @RequestMapping(value = "/submitVote", method = RequestMethod.POST)
    public String submitVote(Model model, String voteId, String itemId, HttpSession session) {

        // 如果voteInfo或itemId为空，则重定向到投票信息页面
        if (voteId == null || itemId == null) {
            model.addAttribute("votes", voteService.queryAllVote());
            System.out.println("数据为空！");
            model.addAttribute("message", "投票失败！");
            return "vote_info";
        }

        // 提交投票信息
        int result = voteService.submitVote((String) session.getAttribute("userId"), voteId, itemId);
        if (result == 0) {
            System.out.println("影响行数为0！");
            model.addAttribute("message", "投票失败！");
        } else {// 影响行数不为0，新建成功
            Map<String,Integer> map = (Map<String, Integer>) session.getAttribute("flag");
            map.put(voteId,1);
            session.setAttribute("flag",map);
            model.addAttribute("message", "投票成功！");
        }
        model.addAttribute("votes", voteService.queryAllVote());
        return "vote_info";
    }

    @RequestMapping("/voteResults")
    public String voteResults(Model model,String voteId){
        // 如果voteInfo为空，则重定向到投票信息页面
        if (voteId == null) {
            model.addAttribute("votes", voteService.queryAllVote());
            System.out.println("数据为空！");
            model.addAttribute("message", "访问失败！");
            return "vote_info";
        }
        model.addAttribute("userVote",voteService.queryUserVote(voteId));
        model.addAttribute("items",voteService.queryResults(voteId).get(0));
        model.addAttribute("count",voteService.queryResults(voteId).get(1));
        return "vote_result";
    }
}
