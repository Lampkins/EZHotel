package cuit.hotel.service;

import cuit.hotel.entity.HotelCollection;

import java.util.List;

public interface HotelCollectionService {

    /**
     * 根据用户ID获取收藏列表
     * @param userID 用户ID
     * @return 酒店收藏集合
     */
    List<HotelCollection> getByUserID(Integer userID);

    /**
     * 添加收藏
     * @param hotelCollection 酒店收藏基本信息
     * @return 影响行数
     */
    Integer saveHotelCollection(HotelCollection hotelCollection);

    /**
     * 删除一个收藏
     * @param userID 用户ID
     * @param hotelID 酒店ID
     * @return 影响行数
     */
    Integer delHotelCollection(Integer userID, Integer hotelID);
}
