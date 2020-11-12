package cuit.hotel.service.impl;

import cuit.hotel.entity.HotelCollection;
import cuit.hotel.mapper.HotelCollectionMapper;
import cuit.hotel.service.HotelCollectionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HotelCollectionServiceImpl implements HotelCollectionService {

    @Resource
    private HotelCollectionMapper hotelCollectionMapper;

    @Override
    public List<HotelCollection> getByUserID(Integer userID) {
        return hotelCollectionMapper.selectByKey(userID, null);
    }

    @Override
    public Integer saveHotelCollection(HotelCollection hotelCollection) {
        return hotelCollectionMapper.insert(hotelCollection);
    }

    @Override
    public Integer delHotelCollection(Integer userID, Integer hotelID) {
        return hotelCollectionMapper.deleteByPrimaryKey(userID, hotelID);
    }
}
