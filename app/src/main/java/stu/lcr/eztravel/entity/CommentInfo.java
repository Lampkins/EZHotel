package stu.lcr.eztravel.entity;

import java.io.Serializable;

public class CommentInfo implements Serializable {
    // 订单号
    private Integer bookingID;

    // 用户id
    private Integer userID;

    // 酒店ID
    private Integer hotelID;

    // 评分（1-5）
    private Double score;

    // 评价内容
    private String content;

    // 回复内容，默认无
    private String reply;

    // 评价状态，0:待审核，1:已审核
    private Byte commentStatus;

    // 评价创建时间
    private String createTime;

    // 用户名
    private String userName;

    // 房型名
    private String roomTypeName;

    private static final long serialVersionUID = 1L;

    public Integer getBookingID() {
        return bookingID;
    }

    public void setBookingID(Integer bookingID) {
        this.bookingID = bookingID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getHotelID() {
        return hotelID;
    }

    public void setHotelID(Integer hotelID) {
        this.hotelID = hotelID;
    }

    /**
     * 获取 评分（1-5） 字段:comment_info.score
     *
     * @return comment_info.score, 评分（1-5）
     */
    public Double getScore() {
        return score;
    }

    /**
     * 设置 评分（1-5） 字段:comment_info.score
     *
     * @param score the value for comment_info.score, 评分（1-5）
     */
    public void setScore(Double score) {
        this.score = score;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply == null ? null : reply.trim();
    }

    public Byte getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(Byte commentStatus) {
        this.commentStatus = commentStatus;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName == null ? null : roomTypeName.trim();
    }
}