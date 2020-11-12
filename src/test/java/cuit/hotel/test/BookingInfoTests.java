package cuit.hotel.test;

import cn.hutool.core.date.DateUtil;
import cuit.hotel.HotelApplication;
import cuit.hotel.entity.BookingInfo;
import cuit.hotel.service.BookingInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest(classes= HotelApplication.class)
public class BookingInfoTests {
    @Resource
    BookingInfoService bookingInfoServiceImpl;
//    @Test
//    public void testAddBookingInfo() {
//        BookingInfo bookingInfo1 = new BookingInfo();
//        bookingInfo1.setUserID(3);
//        bookingInfo1.setHotelID(101859);
//        bookingInfo1.setRoomTypeID(2);
//        bookingInfo1.setFullName("张三");
//        bookingInfo1.setPhone("12345678912");
//        bookingInfo1.setCheckIn(DateUtil.parse("2020/06/09"));
//        bookingInfo1.setCheckOut(DateUtil.parse("2020/06/11"));
//        bookingInfo1.setBookingStatus((byte)2);
//        Integer i = bookingInfoServiceImpl.saveBookingInfo(bookingInfo1);
//        System.out.println("添加订单："+i);
//    }
    @Test
    public void testGet(){
        BookingInfo bookingInfo = bookingInfoServiceImpl.getByBookingID(18);
        System.out.println(bookingInfo.getCheckIn());
        System.out.println(bookingInfo.getCreateTime());
    }
}
