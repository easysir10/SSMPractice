package info;

/**
 * 投票项实体类
 *
 * @author Hu.Wang 2020/07/07 13:33
 */
public class VoteItemInfo {

    private String itemId;      // 投票项id
    private String itemDes;     // 投票项描述
    private String itemRemarks; // 备注
    private String voteId;      // 投票id

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemDes() {
        return itemDes;
    }

    public void setItemDes(String itemDes) {
        this.itemDes = itemDes;
    }

    public String getItemRemarks() {
        return itemRemarks;
    }

    public void setItemRemarks(String itemRemarks) {
        this.itemRemarks = itemRemarks;
    }

    public String getVoteId() {
        return voteId;
    }

    public void setVoteId(String voteId) {
        this.voteId = voteId;
    }

    @Override
    public String toString() {
        return "VoteItemInfo{" +
                "itemId='" + itemId + '\'' +
                ", itemDes='" + itemDes + '\'' +
                ", itemRemarks='" + itemRemarks + '\'' +
                ", voteId='" + voteId + '\'' +
                '}';
    }
}
