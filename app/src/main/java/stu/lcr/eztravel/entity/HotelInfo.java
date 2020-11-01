package stu.lcr.eztravel.entity;

import java.io.Serializable;

public class HotelInfo implements Serializable {
    // 表字段 : hotel_info.hotelID
    private Integer hotelID;

    // 酒店名称
    private String hotelName;

    // 酒店所在城市
    private String city;

    // 酒店详细地址
    private String address;

    // 酒店联系电话
    private String phone;

    // 酒店综合评分(0-5)，默认为5
    private Double score;

    // 起始价
    private Double startingPrice;


    // 酒店图片
    private String imgs;

    // 酒店简介
    private String introduction;

    private static final long serialVersionUID = 1L;

    public Integer getHotelID() {
        return hotelID;
    }


    public void setHotelID(Integer hotelID) {
        this.hotelID = hotelID;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName == null ? null : hotelName.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Double getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(Double startingPrice) {
        this.startingPrice = startingPrice;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs == null ? null : imgs.trim();
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    @Override
    public String toString() {
        return "HotelInfo{" +
                "hotelID=" + hotelID +
                ", hotelName='" + hotelName + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", score=" + score +
                ", startingPrice=" + startingPrice +
                ", imgs='" + imgs + '\'' +
                ", introduction='" + introduction + '\'' +
                '}';
    }
}