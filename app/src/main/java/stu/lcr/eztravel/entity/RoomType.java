package stu.lcr.eztravel.entity;

import java.io.Serializable;

public class RoomType implements Serializable {
    // 酒店id
    private Integer hotelID;

    // 房型编号
    private Integer roomTypeID;

    // 房型名
    private String roomTypeName;

    // 预订价格
    private Double price;

    // 预订折扣
    private Double discount;

    // 房间大小(单位：m2)
    private String area;

    // 床型，如:大床、单人床、双床
    private String bedType;

    // 最大入住人数
    private Byte maxPerson;

    // 楼层
    private String floor;

    // 房型图片，（"img1.png,img2.png"）
    private String imgs;

    // 其他信息，json格式
    private String other;

    private static final long serialVersionUID = 1L;

    public Integer getHotelID() {
        return hotelID;
    }

    public void setHotelID(Integer hotelID) {
        this.hotelID = hotelID;
    }

    public Integer getRoomTypeID() {
        return roomTypeID;
    }

    public void setRoomTypeID(Integer roomTypeID) {
        this.roomTypeID = roomTypeID;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName == null ? null : roomTypeName.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType == null ? null : bedType.trim();
    }

    public Byte getMaxPerson() {
        return maxPerson;
    }

    public void setMaxPerson(Byte maxPerson) {
        this.maxPerson = maxPerson;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor == null ? null : floor.trim();
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs == null ? null : imgs.trim();
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other == null ? null : other.trim();
    }

    @Override
    public String toString() {
        return "RoomType{" +
                "hotelID=" + hotelID +
                ", roomTypeID=" + roomTypeID +
                ", roomTypeName='" + roomTypeName + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", area=" + area +
                ", bedType='" + bedType + '\'' +
                ", maxPerson=" + maxPerson +
                ", floor='" + floor + '\'' +
                ", imgs='" + imgs + '\'' +
                ", other='" + other + '\'' +
                '}';
    }
}