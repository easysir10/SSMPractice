package service.impl;

import dao.VoteDao;
import info.UserVoteInfo;
import info.VoteInfo;
import info.VoteItemInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.VoteService;
import utils.UUIDUtils;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author Hu.Wang 2020/07/07 14:02
 */
@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    VoteDao voteDao;
    @Autowired
    HttpSession session;

    /**
     * @return List<VoteInfo> 投票信息列表
     * @author Hu.Wang 2020-07-07 13:56
     */
    @Override
    public List<VoteInfo> queryAllVote() {
        // 查询所有的投票信息
        List<VoteInfo> voteInfos = voteDao.queryAllVote();

        // 查询所有的投票项信息
        for (VoteInfo voteInfo : voteInfos) {
            System.out.println(voteDao.queryAllVoteItem(voteInfo.getVoteId()).get(0).toString());
            voteInfo.setItems(voteDao.queryAllVoteItem(voteInfo.getVoteId()));
        }

        return voteInfos;
    }

    /**
     * @param voteId 投票信息id
     * @return VoteInfo 投票信息
     * @author Hu.Wang 2020-07-07 13:56
     */
    @Override
    public VoteInfo queryVoteById(String voteId) {
        // 根据投票信息id查询投票信息
        VoteInfo voteInfo = voteDao.queryVoteById(voteId);
        // 根据投票信息id查询投票项信息
        voteInfo.setItems(voteDao.queryAllVoteItem(voteId));
        return voteInfo;
    }

    /**
     * @param id 投票id
     * @return int 影响行数，成功为1，失败为0
     * @author Hu.Wang 2020-07-07 13:58
     */
    @Override
    public int deleteVoteById(String id) {
        Map<String,Integer> map = (Map<String, Integer>) session.getAttribute("flag");
        map.remove(id);
        session.setAttribute("flag",map);
        return voteDao.deleteVoteById(id);
    }

    /**
     * @param voteInfo 要添加的投票信息实体
     * @return int 影响行数，失败为0, 成功非0
     * @author Hu.Wang 2020-07-07 13:59
     */
    @Override
    public int addVote(VoteInfo voteInfo) {

        // 生成投票信息id
        voteInfo.setVoteId(UUIDUtils.getUUID());
        // 增加投票信息数据
        int result = voteDao.addVote(voteInfo);

        // 增加投票项信息数据
        for (VoteItemInfo itemInfo : voteInfo.getItems()) {
            // 生成投票项id
            itemInfo.setItemId(UUIDUtils.getUUID());
            // 设置投票项所属投票id
            itemInfo.setVoteId(voteInfo.getVoteId());
            result += voteDao.addVoteItem(itemInfo);
        }
        Map<String,Integer> map = (Map<String, Integer>) session.getAttribute("flag");
        map.put(voteInfo.getVoteId(),0);
        session.setAttribute("flag",map);
        return result;
    }

    /**
     * @param voteInfo 要修改的投票信息实体
     * @return int 影响行数，失败为0, 成功非0
     * @author Hu.Wang 2020-07-07 14:00
     */
    @Override
    public int updateVote(VoteInfo voteInfo) {

        // 更新投票信息
        int result = voteDao.updateVote(voteInfo);

        // 更新投票项信息
        for (VoteItemInfo itemInfo : voteInfo.getItems()) {
            result += voteDao.updateVoteItem(itemInfo);
        }
        return result;
    }

    /**
     * @param userId 用户id
     * @param voteId 投票id
     * @param itemIds 投票项id
     * @return int 影响行数，成功为1，失败为0
     * @author Hu.Wang 2020-07-07 15:15
     */
    @Override
    public int submitVote(String userId, String voteId, String itemIds) {

        int result = 0;
        String[] itemIdList = itemIds.split(",");
        // 插入用户投票数据
        for (String itemId : itemIdList) {
            System.out.println("id:"+itemId);
            result += voteDao.submitVote(userId,voteId,itemId);
        }
        return result;
    }

    /**
     * @return int 影响行数，成功为1，失败为0
     * @author Hu.Wang 2020-07-07 15:15
     */
    @Override
    public int insertUser(String userId, String nickname) {
        return voteDao.insertUser(userId, nickname);
    }

    /**
     * @param voteId 投票id
     * @return List 用户投票结果
     * @author Hu.Wang 2020-07-07 15:15
     */
    @Override
    public List<UserVoteInfo> queryUserVote(String voteId) {
        return voteDao.queryUserVote(voteId);
    }

    /**
     * @param voteId 投票id
     * @return Map 统计结果
     * @author Hu.Wang 2020-07-07 15:15
     */
    @Override
    public List queryResults(String voteId) {

        // 获取统计结果
        List<Map> listMap = voteDao.queryResults(voteId);

        List<Object> list = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        // 将数据格式化（为前端配置数据源）
        for (Map map : listMap){
            list1.add((String) map.get("item"));
            list2.add(Integer.parseInt(map.get("count").toString()));
        }
        list.add(list1.toArray());
        list.add(list2.toArray());
        return list;
    }


}
