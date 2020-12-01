package cdu.pjm.model;

public class User {
    Long userID;
    String userName="";
    String userPwd="";
    String userGender="";
    String userAge= "";
    String userLike="";
    String userPicUrl="";

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", userGender='" + userGender + '\'' +
                ", userAge='" + userAge + '\'' +
                ", userLike='" + userLike + '\'' +
                ", userPicUrl='" + userPicUrl + '\'' +
                '}';
    }

    public User() {
    }
    public User(Long userID,String userName,String userPwd,String userGender,String userAge,String userLike,String userPicUrl) {
        this.userID=userID;
        this.userName=userName;
        this.userPwd=userPwd;
        this.userGender=userGender;
        this.userAge=userAge;
        this.userLike=userLike;
        this.userPicUrl=userPicUrl;
    }

    public String getUserPicUrl() {
        return userPicUrl;
    }

    public void setUserPicUrl(String userPicUrl) {
        this.userPicUrl = userPicUrl;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getUserLike() {
        return userLike;
    }

    public void setUserLike(String userLike) {
        this.userLike = userLike;
    }
}
