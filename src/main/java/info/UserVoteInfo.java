package info;

/**
 * 用户投票实体类
 *
 * @author Hu.Wang 2020/07/07 13:33
 */
public class UserVoteInfo {

    private String userId;          // 用户id
    private String voteId;          // 投票id
    private String userVoteResults; // 投票结果（多项用逗号隔开）

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVoteId() {
        return voteId;
    }

    public void setVoteId(String voteId) {
        this.voteId = voteId;
    }

    public String getUserVoteResults() {
        return userVoteResults;
    }

    public void setUserVoteResults(String userVoteResults) {
        this.userVoteResults = userVoteResults;
    }

    @Override
    public String toString() {
        return "UserVoteInfo{" +
                "userId='" + userId + '\'' +
                ", voteId='" + voteId + '\'' +
                ", userVoteResults='" + userVoteResults + '\'' +
                '}';
    }
}
