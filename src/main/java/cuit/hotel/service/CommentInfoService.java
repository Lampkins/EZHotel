package cuit.hotel.service;

import com.github.pagehelper.PageInfo;
import cuit.hotel.common.PagePojo;
import cuit.hotel.entity.CommentInfo;

import java.util.List;

public interface CommentInfoService {

    /**
     * 根据订单号查询评价信息
     * @param bookingID 订单号
     * @return 评价信息
     */
    CommentInfo getByBookingID(Integer bookingID);

    /**
     * 根据酒店ID查询评论分页信息
     * @param hotelID 酒店ID
     * @return 评价信息集
     */
    PageInfo<CommentInfo> getPageByHotelID(Integer hotelID, PagePojo pagePojo);

    /**
     * 添加一条评价
     * @param commentInfo 评价相关信息
     * @return 影响行数
     */
    Integer saveCommentInfo(CommentInfo commentInfo);

    /**
     * 根据订单号更新评价信息
     * @param commentInfo 评价相关信息
     * @return 影响行数
     */
    Integer updByBookingID(CommentInfo commentInfo);
}
