package cuit.hotel.service;

import com.github.pagehelper.PageInfo;
import cuit.hotel.common.PagePojo;
import cuit.hotel.entity.HotelInfo;

import java.util.List;

public interface HotelInfoService {

    HotelInfo getByHotelID(Integer hotelID);

//    PageInfo<HotelInfo> getHotelInfoPage(PagePojo pagePojo);

    PageInfo<HotelInfo> getHotelInfoPage(HotelInfo hotelInfo, PagePojo pagePojo, Double hightestPrice);
}
