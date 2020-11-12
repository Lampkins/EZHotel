package cuit.hotel.mapper;

import cuit.hotel.entity.RoomType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoomTypeMapper {
    /**
     *  根据主键删除数据库的记录,room_type
     *
     * @param hotelID
     * @param roomTypeID
     */
    int deleteByPrimaryKey(@Param("hotelID") Integer hotelID, @Param("roomTypeID") Integer roomTypeID);

    /**
     *  新写入数据库记录,room_type
     *
     * @param record
     */
    int insert(RoomType record);

    /**
     *  动态字段,写入数据库记录,room_type
     *
     * @param record
     */
    int insertSelective(RoomType record);

    /**
     *  根据指定主键获取一条数据库记录,room_type
     *
     * @param hotelID
     * @param roomTypeID
     */
    List<RoomType> selectByPrimaryKey(@Param("hotelID") Integer hotelID, @Param("roomTypeID") Integer roomTypeID);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,room_type
     *
     * @param record
     */
    int updateByPrimaryKeySelective(RoomType record);

    /**
     *  根据主键来更新符合条件的数据库记录,room_type
     *
     * @param record
     */
    int updateByPrimaryKey(RoomType record);
}