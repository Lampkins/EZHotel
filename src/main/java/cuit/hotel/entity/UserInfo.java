package cuit.hotel.entity;

import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;

public class UserInfo implements Serializable {
    /**
     * 用户编号
     * 表字段 : user_info.userID
     */
    private Integer userID;

    /**
     * 角色编号
     * 表字段 : user_info.roleID
     */
    private Integer roleID;

    /**
     * 用户账号名
     * 表字段 : user_info.username
     */
    private String username;

    /**
     * 用户账号密码
     * 表字段 : user_info.password
     */
    private String password;

    /**
     * 会员等级
     * 表字段 : user_info.memberLevel
     */
    private String memberLevel;

    /**
     * 真实姓名
     * 表字段 : user_info.fullName
     */
    private String fullName;

    /**
     * 手机号
     * 表字段 : user_info.phone
     */
    private String phone;

    /**
     * 性别
     * 表字段 : user_info.gender
     */
    private String gender;

    /**
     * 邮箱
     * 表字段 : user_info.mail
     */
    private String mail;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table user_info
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * 获取 用户编号 字段:user_info.userID
     *
     * @return user_info.userID, 用户编号
     */
    public Integer getUserID() {
        return userID;
    }

    /**
     * 设置 用户编号 字段:user_info.userID
     *
     * @param userID the value for user_info.userID, 用户编号
     */
    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    /**
     * 获取 角色编号 字段:user_info.roleID
     *
     * @return user_info.roleID, 角色编号
     */
    public Integer getRoleID() {
        return roleID;
    }

    /**
     * 设置 角色编号 字段:user_info.roleID
     *
     * @param roleID the value for user_info.roleID, 角色编号
     */
    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    /**
     * 获取 用户账号名 字段:user_info.username
     *
     * @return user_info.username, 用户账号名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置 用户账号名 字段:user_info.username
     *
     * @param username the value for user_info.username, 用户账号名
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 获取 用户账号密码 字段:user_info.password
     *
     * @return user_info.password, 用户账号密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置 用户账号密码 字段:user_info.password
     *
     * @param password the value for user_info.password, 用户账号密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取 会员等级 字段:user_info.memberLevel
     *
     * @return user_info.memberLevel, 会员等级
     */
    public String getMemberLevel() {
        return memberLevel;
    }

    /**
     * 设置 会员等级 字段:user_info.memberLevel
     *
     * @param memberLevel the value for user_info.memberLevel, 会员等级
     */
    public void setMemberLevel(String memberLevel) {
        this.memberLevel = memberLevel == null ? null : memberLevel.trim();
    }

    /**
     * 获取 真实姓名 字段:user_info.fullName
     *
     * @return user_info.fullName, 真实姓名
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 设置 真实姓名 字段:user_info.fullName
     *
     * @param fullName the value for user_info.fullName, 真实姓名
     */
    public void setFullName(String fullName) {
        this.fullName = fullName == null ? null : fullName.trim();
    }

    /**
     * 获取 手机号 字段:user_info.phone
     *
     * @return user_info.phone, 手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置 手机号 字段:user_info.phone
     *
     * @param phone the value for user_info.phone, 手机号
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 获取 性别 字段:user_info.gender
     *
     * @return user_info.gender, 性别
     */
    public String getGender() {
        return gender;
    }

    /**
     * 设置 性别 字段:user_info.gender
     *
     * @param gender the value for user_info.gender, 性别
     */
    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    /**
     * 获取 邮箱 字段:user_info.mail
     *
     * @return user_info.mail, 邮箱
     */
    public String getMail() {
        return mail;
    }

    /**
     * 设置 邮箱 字段:user_info.mail
     *
     * @param mail the value for user_info.mail, 邮箱
     */
    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }
}