package cuit.hotel.controller;

import cuit.hotel.common.Result;
import cuit.hotel.common.ResultUtils;
import cuit.hotel.common.StatusEnum;
import cuit.hotel.entity.HotelCollection;
import cuit.hotel.service.HotelCollectionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class HotelCollectionController {

    @Resource
    private HotelCollectionService hotelCollectionServiceImpl;

    @GetMapping("/hotelCollections")
    public Result getByUserID(@RequestParam Integer userID) {
        List<HotelCollection> hotelCollectionList = hotelCollectionServiceImpl.getByUserID(userID);
        if (hotelCollectionList.size()==0)
            return ResultUtils.success("收藏为空", null);
        return ResultUtils.success(hotelCollectionList);
    }

    @PostMapping("/hotelCollections")
    public Result saveHotelCollection(@RequestBody HotelCollection hotelCollection) {
        Integer row = hotelCollectionServiceImpl.saveHotelCollection(hotelCollection);
        if (row == 0)
            return ResultUtils.failure("收藏失败");
        return ResultUtils.success(StatusEnum.CREATED);
    }

    @DeleteMapping("/hotelCollections/{userID}/{hotelID}")
    public Result delHotelCollection(@PathVariable Integer userID,@PathVariable Integer hotelID) {
        Integer row = hotelCollectionServiceImpl.delHotelCollection(userID, hotelID);
        if (row == 0)
            return ResultUtils.failure("删除收藏失败");
        return ResultUtils.success(StatusEnum.NO_CONTENT);
    }
}
