package stu.lcr.eztravel.entity;

import java.io.Serializable;

public class UserInfo implements Serializable {
    // 用户编号
    private Integer userID;

    // 用户账号名
    private String username;

    // 会员等级
    private String memberLevel;

    // 真实姓名
    private String fullName;

    // 手机号
    private String phone;

    // 性别
    private String gender;

    // 邮箱
    private String mail;

    private static final long serialVersionUID = 1L;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(String memberLevel) {
        this.memberLevel = memberLevel == null ? null : memberLevel.trim();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName == null ? null : fullName.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", memberLevel='" + memberLevel + '\'' +
                ", fullName='" + fullName + '\'' +
                ", phone='" + phone + '\'' +
                ", gender='" + gender + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}