package cuit.hotel.mapper;

import cuit.hotel.entity.HotelInfo;
import cuit.hotel.entity.HotelInfoExample;

import java.util.List;

public interface HotelInfoMapper {
    /**
     *  根据主键删除数据库的记录,hotel_info
     *
     * @param hotelID
     */
    int deleteByPrimaryKey(Integer hotelID);

    /**
     *  新写入数据库记录,hotel_info
     *
     * @param record
     */
    int insert(HotelInfo record);

    /**
     *  动态字段,写入数据库记录,hotel_info
     *
     * @param record
     */
    int insertSelective(HotelInfo record);

    /**
     * ,hotel_info
     *
     * @param example
     */
    List<HotelInfo> selectByExampleWithBLOBs(HotelInfoExample example);

    /**
     *  根据指定的条件查询符合条件的数据库记录,hotel_info
     *
     * @param example
     */
    List<HotelInfo> selectByExample(HotelInfoExample example);

    /**
     *  根据指定主键获取一条数据库记录,hotel_info
     *
     * @param hotelID
     */
    HotelInfo selectByPrimaryKey(Integer hotelID);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,hotel_info
     *
     * @param record
     */
    int updateByPrimaryKeySelective(HotelInfo record);

    /**
     * ,hotel_info
     *
     * @param record
     */
    int updateByPrimaryKeyWithBLOBs(HotelInfo record);

    /**
     *  根据主键来更新符合条件的数据库记录,hotel_info
     *
     * @param record
     */
    int updateByPrimaryKey(HotelInfo record);
}