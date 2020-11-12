package cuit.hotel.mapper;

import cuit.hotel.entity.BookingInfo;

import java.util.List;

public interface BookingInfoMapper {
    List<BookingInfo> selectByUserIDAndBookingStatus(Integer userID, Byte bookingStatus);
    /**
     *  根据主键删除数据库的记录,booking_info
     *
     * @param bookingID
     */
    int deleteByPrimaryKey(Integer bookingID);

    /**
     *  新写入数据库记录,booking_info
     *
     * @param record
     */
    int insert(BookingInfo record);

    /**
     *  动态字段,写入数据库记录,booking_info
     *
     * @param record
     */
    int insertSelective(BookingInfo record);

    /**
     *  根据指定主键获取一条数据库记录,booking_info
     *
     * @param bookingID
     */
    BookingInfo selectByPrimaryKey(Integer bookingID);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,booking_info
     *
     * @param record
     */
    int updateByPrimaryKeySelective(BookingInfo record);

    /**
     *  根据主键来更新符合条件的数据库记录,booking_info
     *
     * @param record
     */
    int updateByPrimaryKey(BookingInfo record);
}