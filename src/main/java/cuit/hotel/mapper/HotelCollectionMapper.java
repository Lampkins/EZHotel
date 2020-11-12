package cuit.hotel.mapper;

import cuit.hotel.entity.HotelCollection;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HotelCollectionMapper {
    /**
     *  根据主键删除数据库的记录,hotel_collection
     *
     * @param userID
     * @param hotelID
     */
    int deleteByPrimaryKey(@Param("userID") Integer userID, @Param("hotelID") Integer hotelID);

    /**
     *  新写入数据库记录,hotel_collection
     *
     * @param record
     */
    int insert(HotelCollection record);

    /**
     *  动态字段,写入数据库记录,hotel_collection
     *
     * @param record
     */
    int insertSelective(HotelCollection record);

    /**
     *  根据指定主键获取一条数据库记录
     *
     * @param userID
     * @param hotelID
     */
    List<HotelCollection> selectByKey(@Param("userID") Integer userID, @Param("hotelID") Integer hotelID);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,hotel_collection
     *
     * @param record
     */
    int updateByPrimaryKeySelective(HotelCollection record);

    /**
     *  根据主键来更新符合条件的数据库记录,hotel_collection
     *
     * @param record
     */
    int updateByPrimaryKey(HotelCollection record);
}