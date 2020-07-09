package info;

import java.util.List;

/**
 * description
 *
 * @author Hu.Wang 2020/07/08 13:45
 */
public class VoteItemModel {

    private List<VoteItemInfo> voteItems;

    public VoteItemModel() {
    }

    public VoteItemModel(List<VoteItemInfo> voteItems) {
        this.voteItems = voteItems;
    }

    public List<VoteItemInfo> getVoteItems() {
        return voteItems;
    }

    public void setVoteItems(List<VoteItemInfo> voteItems) {
        this.voteItems = voteItems;
    }
}