package cuit.hotel.controller;

import cn.hutool.json.JSONUtil;
import cuit.hotel.common.Result;
import cuit.hotel.common.ResultUtils;
import cuit.hotel.entity.RoomType;
import cuit.hotel.service.RoomTypeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class RoomTypeController {

    @Resource
    private RoomTypeService roomTypeServiceImpl;

    @GetMapping("/roomTypes/{hotelID}/{roomTypeID}")
    public Result getRoomType(@PathVariable Integer hotelID, @PathVariable Integer roomTypeID) {
        List<RoomType> roomTypeList = roomTypeServiceImpl.getRoomTypeList(hotelID, roomTypeID);
        if (roomTypeList.size() == 0)
            return ResultUtils.failure("获取酒店房型信息失败");
        return ResultUtils.success(JSONUtil.parse(roomTypeList.get(0)));
    }

    @GetMapping("/roomTypes")
    public Result getRoomTypeList(@RequestParam Integer hotelID) {
        List<RoomType> roomTypeList = roomTypeServiceImpl.getRoomTypeList(hotelID, null);
        if (roomTypeList.size() == 0)
            return ResultUtils.failure("获取酒店房型信息失败");
        return ResultUtils.success(JSONUtil.parseArray(roomTypeList));
    }

}
