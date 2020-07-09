package service.impl;

import dao.VoteDao;
import info.VoteInfo;
import info.VoteItemInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.VoteService;
import utils.UUIDUtils;

import java.util.List;

/**
 * description
 *
 * @author Hu.Wang 2020/07/07 14:02
 */
@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    VoteDao voteDao;

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
            voteInfo.setItems(voteDao.queryAllVoteItem(voteInfo.getVoteId()));
        }

        return voteDao.queryAllVote();
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
     * @param items 投票项
     * @return int 影响行数，成功为1，失败为0
     * @author Hu.Wang 2020-07-07 15:15
     */
    @Override
    public int submitVote(String userId, String voteId, List<VoteItemInfo> items) {

        // 获取投票结果项id
        StringBuilder results = new StringBuilder();
        for (VoteItemInfo itemInfo : items) {
            results.append(itemInfo.getItemId());
        }

        return voteDao.submitVote(userId,voteId,results.toString());
    }

    /**
     * @return int 影响行数，成功为1，失败为0
     * @author Hu.Wang 2020-07-07 15:15
     */
    @Override
    public int insertUser(String userId, String nickname) {
        return voteDao.insertUser(userId,nickname);
    }


}
