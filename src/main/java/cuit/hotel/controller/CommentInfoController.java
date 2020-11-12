package cuit.hotel.controller;

import cn.hutool.json.JSONUtil;
import com.github.pagehelper.PageInfo;
import cuit.hotel.common.PagePojo;
import cuit.hotel.common.Result;
import cuit.hotel.common.ResultUtils;
import cuit.hotel.common.StatusEnum;
import cuit.hotel.entity.BookingInfo;
import cuit.hotel.entity.CommentInfo;
import cuit.hotel.service.BookingInfoService;
import cuit.hotel.service.CommentInfoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class CommentInfoController {

    @Resource
    private CommentInfoService commentInfoServiceImpl;
    @Resource
    private BookingInfoService bookingInfoServiceImpl;

    @GetMapping("/comments/{bookingID}")
    public Result getByBookingID(@PathVariable Integer bookingID) {
        CommentInfo commentInfo = commentInfoServiceImpl.getByBookingID(bookingID);
        if (commentInfo == null)
            return ResultUtils.failure();
        return ResultUtils.success(JSONUtil.parse(commentInfo));
    }

    @GetMapping("/comments")
    public Result getPageByHotelID(Integer hotelID, PagePojo pagePojo) {
        PageInfo<CommentInfo> commentInfoPage = commentInfoServiceImpl.getPageByHotelID(hotelID,pagePojo);
        if (commentInfoPage == null)
            return ResultUtils.failure();
        return ResultUtils.success(JSONUtil.parse(commentInfoPage));
    }

    @PostMapping("/comments")
    public Result saveCommentInfo(@RequestBody CommentInfo commentInfo) {
        commentInfo.setReply(null);
        commentInfo.setCommentStatus((byte)1);
        commentInfo.setCreateTime(null);
        Integer row = commentInfoServiceImpl.saveCommentInfo(commentInfo);
        if (row == 0)
            return ResultUtils.failure();
        BookingInfo bookingInfo = new BookingInfo();
        bookingInfo.setBookingID(commentInfo.getBookingID());
        bookingInfo.setBookingStatus((byte)4);
        bookingInfoServiceImpl.updBybookingID(bookingInfo);
        return ResultUtils.success(StatusEnum.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('0','2')")
    @PutMapping("/comments")
    public Result updByBookingID(Integer bookingID,
                                 @RequestParam(required = false, defaultValue = "null") String reply) {
        CommentInfo commentInfo = new CommentInfo();
        commentInfo.setBookingID(bookingID);
        if (reply != null && !reply.equals(""))
            commentInfo.setReply(reply);
        commentInfo.setCommentStatus((byte)1);
        Integer row = commentInfoServiceImpl.updByBookingID(commentInfo);
        if (row == 0)
            return ResultUtils.failure();
        return ResultUtils.success(StatusEnum.CREATED);
    }

}
