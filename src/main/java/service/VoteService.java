package service;

import info.UserVoteInfo;
import info.VoteInfo;

import java.util.List;

/**
 * 投票信息服务层
 *
 * @author Hu.Wang 2020/07/07 14:01
 */
public interface VoteService {

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
     *
     * @param voteInfo 要修改的投票信息实体
     * @author Hu.Wang 2020-07-07 14:00
     * @return int 影响行数，成功为1，失败为0
     */
    int updateVote(VoteInfo voteInfo);

    /**
     *
     * @param userId    用户id
     * @param voteId    投票id
     * @param itemIds   投票项
     * @author Hu.Wang 2020-07-07 15:15
     * @return int 影响行数，成功为1，失败为0
     */
    int submitVote(String userId,String voteId,String itemIds);

    /**
     *
     * @author Hu.Wang 2020-07-07 15:15
     * @return int 影响行数，成功为1，失败为0
     */
    int insertUser(String userId,String nickname);

    /**
     *
     * @author Hu.Wang 2020-07-07 15:15
     * @return List 用户投票结果
     */
    List<UserVoteInfo> queryUserVote(String voteId);

    /**
     *
     * @author Hu.Wang 2020-07-07 15:15
     * @return List 统计结果
     */
    List queryResults(String voteId);
}
