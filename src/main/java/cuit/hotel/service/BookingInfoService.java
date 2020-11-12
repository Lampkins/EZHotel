package cuit.hotel.service;

import cuit.hotel.entity.BookingInfo;

import java.util.List;

public interface BookingInfoService {

    /**
     * 添加一条订单
     * @param bookingInfo 订单信息
     * @return 订单号
     */
    Integer saveBookingInfo(BookingInfo bookingInfo);

    /**
     * 修改订单
     * @param bookingInfo 订单信息
     * @return 影响行数
     */
    Integer updBybookingID(BookingInfo bookingInfo);

    /**
     * 根据订单号获取订单信息
     * @param bookingID 订单号
     * @return 订单信息
     */
    BookingInfo getByBookingID(Integer bookingID);

    /**
     * 根据用户ID和订单状态获取所有订单
     * @param userID 用户ID
     * @return 订单集
     */
    List<BookingInfo> getByUserIDAndStatus(Integer userID, Byte bookingStatus);
}
