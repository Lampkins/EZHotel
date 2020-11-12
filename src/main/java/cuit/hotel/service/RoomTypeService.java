package cuit.hotel.service;

import cuit.hotel.entity.RoomType;

import java.util.List;

public interface RoomTypeService {

    List<RoomType> getRoomTypeList(Integer hotelID, Integer roomTypeID);
}
