package cuit.hotel.controller;

import cn.hutool.json.JSONUtil;
import com.github.pagehelper.PageInfo;
import cuit.hotel.common.PagePojo;
import cuit.hotel.common.Result;
import cuit.hotel.entity.HotelInfo;
import cuit.hotel.service.HotelInfoService;
import cuit.hotel.common.ResultUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class HotelInfoController {

    @Resource
    private HotelInfoService hotelInfoServiceImpl;

    @GetMapping("/hotels/{hotelID}")
    public Result getHotelByHotelID(@PathVariable Integer hotelID) {
        HotelInfo hotelInfo = hotelInfoServiceImpl.getByHotelID(hotelID);
        if (hotelInfo == null)
            return ResultUtils.failure("酒店ID输入错误");
        return ResultUtils.success(JSONUtil.parse(hotelInfo));
    }

    @GetMapping("/hotels")
    public Result hotels(HotelInfo hotelInfo, PagePojo pagePojo, @RequestParam(required = false) Double highestPrice){
        if (pagePojo.getOrder()!=null && pagePojo.getOrder().equals("RAND()"))
            pagePojo.setSortName(null);
        if (highestPrice == null)
            hotelInfo.setStartingPrice(null);
        PageInfo<HotelInfo> hotelInfoPage = hotelInfoServiceImpl.getHotelInfoPage(hotelInfo, pagePojo, highestPrice);
        if (hotelInfoPage.getList().size() == 0)
            return ResultUtils.failure("无符合条件的酒店信息");
        return ResultUtils.success(JSONUtil.parse(hotelInfoPage));
    }
}
