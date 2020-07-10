package info;

/**
 * 用户投票实体类
 *
 * @author Hu.Wang 2020/07/07 13:33
 */
public class UserVoteInfo {

    private String userId;          // 用户id
    private String userNickname;    // 用户昵称
    private String voteId;          // 投票id
    private String userVoteResults; // 投票结果
    private String itemDes;         // 投票项

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

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getItemDes() {
        return itemDes;
    }

    public void setItemDes(String itemDes) {
        this.itemDes = itemDes;
    }

    @Override
    public String toString() {
        return "UserVoteInfo{" +
                "userId='" + userId + '\'' +
                ", userNickname='" + userNickname + '\'' +
                ", voteId='" + voteId + '\'' +
                ", userVoteResults='" + userVoteResults + '\'' +
                ", itemDes='" + itemDes + '\'' +
                '}';
    }
}
