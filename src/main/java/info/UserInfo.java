package info;

/**
 * 用户实体类
 *
 * @author Hu.Wang 2020/07/07 13:33
 */
public class UserInfo {

    private String userId;          // 用户id
    private String userNickName;    // 用户昵称

    public UserInfo(String userId, String userNickName) {
        this.userId = userId;
        this.userNickName = userNickName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId='" + userId + '\'' +
                ", userNickName='" + userNickName + '\'' +
                '}';
    }
}
