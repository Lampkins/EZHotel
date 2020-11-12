package cuit.hotel.entity;

import java.io.Serializable;

public class RoomType implements Serializable {
    /**
     * 酒店id
     * 表字段 : room_type.hotelID
     */
    private Integer hotelID;

    /**
     * 房型编号
     * 表字段 : room_type.roomTypeID
     */
    private Integer roomTypeID;

    /**
     * 房型名
     * 表字段 : room_type.roomTypeName
     */
    private String roomTypeName;

    /**
     * 预订价格
     * 表字段 : room_type.price
     */
    private Double price;

    /**
     * 预订折扣
     * 表字段 : room_type.discount
     */
    private Double discount;

    /**
     * 房间大小(单位：m2)
     * 表字段 : room_type.area
     */
    private String area;

    /**
     * 床型，如:大床、单人床、双床
     * 表字段 : room_type.bedType
     */
    private String bedType;

    /**
     * 最大入住人数
     * 表字段 : room_type.maxPerson
     */
    private Byte maxPerson;

    /**
     * 楼层
     * 表字段 : room_type.floor
     */
    private String floor;

    /**
     * 房型图片，（"img1.png,img2.png"）
     * 表字段 : room_type.imgs
     */
    private String imgs;

    /**
     * 其他信息，json格式
     * 表字段 : room_type.other
     */
    private String other;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table room_type
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;

    /**
     * 获取 酒店id 字段:room_type.hotelID
     *
     * @return room_type.hotelID, 酒店id
     */
    public Integer getHotelID() {
        return hotelID;
    }

    /**
     * 设置 酒店id 字段:room_type.hotelID
     *
     * @param hotelID the value for room_type.hotelID, 酒店id
     */
    public void setHotelID(Integer hotelID) {
        this.hotelID = hotelID;
    }

    /**
     * 获取 房型编号 字段:room_type.roomTypeID
     *
     * @return room_type.roomTypeID, 房型编号
     */
    public Integer getRoomTypeID() {
        return roomTypeID;
    }

    /**
     * 设置 房型编号 字段:room_type.roomTypeID
     *
     * @param roomTypeID the value for room_type.roomTypeID, 房型编号
     */
    public void setRoomTypeID(Integer roomTypeID) {
        this.roomTypeID = roomTypeID;
    }

    /**
     * 获取 房型名 字段:room_type.roomTypeName
     *
     * @return room_type.roomTypeName, 房型名
     */
    public String getRoomTypeName() {
        return roomTypeName;
    }

    /**
     * 设置 房型名 字段:room_type.roomTypeName
     *
     * @param roomTypeName the value for room_type.roomTypeName, 房型名
     */
    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName == null ? null : roomTypeName.trim();
    }

    /**
     * 获取 预订价格 字段:room_type.price
     *
     * @return room_type.price, 预订价格
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 设置 预订价格 字段:room_type.price
     *
     * @param price the value for room_type.price, 预订价格
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 获取 预订折扣 字段:room_type.discount
     *
     * @return room_type.discount, 预订折扣
     */
    public Double getDiscount() {
        return discount;
    }

    /**
     * 设置 预订折扣 字段:room_type.discount
     *
     * @param discount the value for room_type.discount, 预订折扣
     */
    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    /**
     * 获取 房间大小(单位：m2) 字段:room_type.area
     *
     * @return room_type.area, 房间大小(单位：m2)
     */
    public String getArea() {
        return area;
    }

    /**
     * 设置 房间大小(单位：m2) 字段:room_type.area
     *
     * @param area the value for room_type.area, 房间大小(单位：m2)
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * 获取 床型，如:大床、单人床、双床 字段:room_type.bedType
     *
     * @return room_type.bedType, 床型，如:大床、单人床、双床
     */
    public String getBedType() {
        return bedType;
    }

    /**
     * 设置 床型，如:大床、单人床、双床 字段:room_type.bedType
     *
     * @param bedType the value for room_type.bedType, 床型，如:大床、单人床、双床
     */
    public void setBedType(String bedType) {
        this.bedType = bedType == null ? null : bedType.trim();
    }

    /**
     * 获取 最大入住人数 字段:room_type.maxPerson
     *
     * @return room_type.maxPerson, 最大入住人数
     */
    public Byte getMaxPerson() {
        return maxPerson;
    }

    /**
     * 设置 最大入住人数 字段:room_type.maxPerson
     *
     * @param maxPerson the value for room_type.maxPerson, 最大入住人数
     */
    public void setMaxPerson(Byte maxPerson) {
        this.maxPerson = maxPerson;
    }

    /**
     * 获取 楼层 字段:room_type.floor
     *
     * @return room_type.floor, 楼层
     */
    public String getFloor() {
        return floor;
    }

    /**
     * 设置 楼层 字段:room_type.floor
     *
     * @param floor the value for room_type.floor, 楼层
     */
    public void setFloor(String floor) {
        this.floor = floor == null ? null : floor.trim();
    }

    /**
     * 获取 房型图片，（"img1.png,img2.png"） 字段:room_type.imgs
     *
     * @return room_type.imgs, 房型图片，（"img1.png,img2.png"）
     */
    public String getImgs() {
        return imgs;
    }

    /**
     * 设置 房型图片，（"img1.png,img2.png"） 字段:room_type.imgs
     *
     * @param imgs the value for room_type.imgs, 房型图片，（"img1.png,img2.png"）
     */
    public void setImgs(String imgs) {
        this.imgs = imgs == null ? null : imgs.trim();
    }

    /**
     * 获取 其他信息，json格式 字段:room_type.other
     *
     * @return room_type.other, 其他信息，json格式
     */
    public String getOther() {
        return other;
    }

    /**
     * 设置 其他信息，json格式 字段:room_type.other
     *
     * @param other the value for room_type.other, 其他信息，json格式
     */
    public void setOther(String other) {
        this.other = other == null ? null : other.trim();
    }
}