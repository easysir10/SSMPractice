package info;

import java.util.List;

/**
 * 投票实体类
 *
 * @author Hu.Wang 2020/07/07 13:33
 */
public class VoteInfo {

    private String voteId;      // 投票id
    private String voteTitle;   // 标题
    private String voteDes;     // 描述
    private String voteType;      // 类型：单项，多项
    private List<VoteItemInfo> items;         // 投票项列表

    public String getVoteId() {
        return voteId;
    }

    public void setVoteId(String voteId) {
        this.voteId = voteId;
    }

    public String getVoteTitle() {
        return voteTitle;
    }

    public void setVoteTitle(String voteTitle) {
        this.voteTitle = voteTitle;
    }

    public String getVoteDes() {
        return voteDes;
    }

    public void setVoteDes(String voteDes) {
        this.voteDes = voteDes;
    }

    public String getVoteType() {
        return voteType;
    }

    public void setVoteType(String voteType) {
        this.voteType = voteType;
    }

    public List<VoteItemInfo> getItems() {
        return items;
    }

    public void setItems(List<VoteItemInfo> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "VoteInfo{" +
                "voteId='" + voteId + '\'' +
                ", voteTitle='" + voteTitle + '\'' +
                ", voteDes='" + voteDes + '\'' +
                ", voteType=" + voteType +
                ", items=" + items +
                '}';
    }
}
