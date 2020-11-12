package cuit.hotel.controller;

import cn.hutool.json.JSONUtil;
import cuit.hotel.common.Result;
import cuit.hotel.common.StatusEnum;
import cuit.hotel.entity.BookingInfo;
import cuit.hotel.service.BookingInfoService;
import cuit.hotel.common.ResultUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class BookingInfoController {

    @Resource
    private BookingInfoService bookingInfoServiceImpl;

    @GetMapping("/bookingInfos/{bookingID}")
    public Result getByBookingID(@PathVariable Integer bookingID) {
        BookingInfo bookingInfo = bookingInfoServiceImpl.getByBookingID(bookingID);
        if (bookingInfo == null)
            return ResultUtils.failure("订单号输入错误");

        return ResultUtils.success(JSONUtil.parse(bookingInfo));
    }

    @GetMapping("/bookingInfos")
    public Result getByUserID(@RequestParam Integer userID,@RequestParam(required = false, defaultValue = "4") Integer bookingStatus) {
        if (bookingStatus > 4)
            bookingStatus = 4;
        List<BookingInfo> bookingInfoList = bookingInfoServiceImpl.getByUserIDAndStatus(userID, bookingStatus.byteValue());
        if (bookingInfoList.size() == 0)
            return ResultUtils.success("订单为空", null);
        return ResultUtils.success(JSONUtil.parseArray(bookingInfoList));
    }

    @PostMapping("/bookingInfos")
    public Result saveBookingInfo(@RequestBody BookingInfo bookingInfo) {
        bookingInfo.setBookingStatus((byte)2);
        bookingInfo.setCreateTime(null);
        Integer row = bookingInfoServiceImpl.saveBookingInfo(bookingInfo);
        if (row == 0)
            return ResultUtils.failure("订单信息输入错误");
        return ResultUtils.success();
    }

    @PutMapping("/bookingInfos")
    public Result updBookingInfo(Integer bookingID, Byte bookingStatus){
        BookingInfo bookingInfo = new BookingInfo();
        bookingInfo.setBookingID(bookingID);
        bookingInfo.setBookingStatus(bookingStatus);
        Integer row = bookingInfoServiceImpl.updBybookingID(bookingInfo);
        if (row == 0)
            return ResultUtils.failure("信息输入有误");
        return ResultUtils.success(StatusEnum.CREATED);
    }
}
