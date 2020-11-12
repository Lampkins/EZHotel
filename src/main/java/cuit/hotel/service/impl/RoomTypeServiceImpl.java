package cuit.hotel.service.impl;

import cuit.hotel.entity.RoomType;
import cuit.hotel.mapper.RoomTypeMapper;
import cuit.hotel.service.RoomTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoomTypeServiceImpl implements RoomTypeService {

    @Resource
    private RoomTypeMapper roomTypeMapper;

    @Override
    public List<RoomType> getRoomTypeList(Integer hotelID, Integer roomTypeID) {
        return roomTypeMapper.selectByPrimaryKey(hotelID, roomTypeID);
    }
}
