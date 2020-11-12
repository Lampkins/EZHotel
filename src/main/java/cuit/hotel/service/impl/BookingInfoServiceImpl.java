package cuit.hotel.service.impl;

import cuit.hotel.entity.BookingInfo;
import cuit.hotel.mapper.BookingInfoMapper;
import cuit.hotel.service.BookingInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BookingInfoServiceImpl implements BookingInfoService {

    @Resource
    private BookingInfoMapper bookingInfoMapper;

    @Override
    public Integer saveBookingInfo(BookingInfo bookingInfo) {
        return bookingInfoMapper.insertSelective(bookingInfo);
    }

    @Override
    public Integer updBybookingID(BookingInfo bookingInfo) {
        return bookingInfoMapper.updateByPrimaryKeySelective(bookingInfo);
    }

    @Override
    public BookingInfo getByBookingID(Integer bookingID) {
        return bookingInfoMapper.selectByPrimaryKey(bookingID);
    }

    @Override
    public List<BookingInfo> getByUserIDAndStatus(Integer userID, Byte bookingStatus) {
        return bookingInfoMapper.selectByUserIDAndBookingStatus(userID, bookingStatus);
    }
}
