package cuit.hotel.mapper;

import cuit.hotel.entity.CommentInfo;

import java.util.List;

public interface CommentInfoMapper {

    /**
     * 根据酒店ID查询评论信息
     * @return
     */
    List<CommentInfo> selectByHotelID(Integer hotelID);
    /**
     *  根据主键删除数据库的记录,comment_info
     *
     * @param bookingID
     */
    int deleteByPrimaryKey(Integer bookingID);

    /**
     *  新写入数据库记录,comment_info
     *
     * @param record
     */
    int insert(CommentInfo record);

    /**
     *  动态字段,写入数据库记录,comment_info
     *
     * @param record
     */
    int insertSelective(CommentInfo record);

    /**
     *  根据指定主键获取一条数据库记录,comment_info
     *
     * @param bookingID
     */
    CommentInfo selectByPrimaryKey(Integer bookingID);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,comment_info
     *
     * @param record
     */
    int updateByPrimaryKeySelective(CommentInfo record);

    /**
     *  根据主键来更新符合条件的数据库记录,comment_info
     *
     * @param record
     */
    int updateByPrimaryKey(CommentInfo record);
}