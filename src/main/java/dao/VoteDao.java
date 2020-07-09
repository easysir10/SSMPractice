package dao;

import info.VoteInfo;
import info.VoteItemInfo;

import java.util.List;

/**
 * 投票信息数据库dao层
 *
 * @author Hu.Wang 2020/07/07 13:53
 */

public interface VoteDao {

    /**
     *
     * @author Hu.Wang 2020-07-07 13:56
     * @return List<VoteInfo> 投票信息列表
     */
    List<VoteInfo> queryAllVote();

    /**
     *
     * @author Hu.Wang 2020-07-07 13:56
     * @return VoteInfo 投票信息
     */
    VoteInfo queryVoteById(String voteId);

    /**
     *
     * @author Hu.Wang 2020-07-07 13:56
     * @return List<VoteInfo> 投票信息列表
     */
    List<VoteItemInfo> queryAllVoteItem(String VoteId);

    /**
     *
     * @param id 投票id
     * @author Hu.Wang 2020-07-07 13:58
     * @return int 影响行数，成功为1，失败为0
     */
    int deleteVoteById(String id);

    /**
     *
     * @param voteInfo 要添加的投票信息实体
     * @author Hu.Wang 2020-07-07 13:59
     * @return int 影响行数，成功为1，失败为0
     */
    int addVote(VoteInfo voteInfo);

    /**
     *
     * @param voteItemInfo 要添加的投票项实体
     * @author Hu.Wang 2020-07-07 13:59
     * @return int 影响行数，成功为1，失败为0
     */
    int addVoteItem(VoteItemInfo voteItemInfo);

    /**
     *
     *
     * @param voteInfo 要修改的投票信息实体
     * @author Hu.Wang 2020-07-07 14:00
     * @return int 影响行数，成功为1，失败为0
     */
    int updateVote(VoteInfo voteInfo);

    /**
     *
     *
     * @param voteItemInfo 要修改的投票项信息实体
     * @author Hu.Wang 2020-07-07 14:00
     * @return int 影响行数，成功为1，失败为0
     */
    int updateVoteItem(VoteItemInfo voteItemInfo);

    /**
     *
     * @param userId    用户id
     * @param voteId    投票id
     * @param results   投票解惑
     * @author Hu.Wang 2020-07-07 15:15
     * @return int 影响行数，成功为1，失败为0
     */
    int submitVote(String userId,String voteId,String results);


    /**
     *
     * @author Hu.Wang 2020-07-07 15:15
     * @return int 影响行数，成功为1，失败为0
     */
    int insertUser(String userId,String nickname);
}
