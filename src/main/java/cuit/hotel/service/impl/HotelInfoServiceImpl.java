package cuit.hotel.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cuit.hotel.common.PagePojo;
import cuit.hotel.entity.HotelInfo;
import cuit.hotel.entity.HotelInfoExample;
import cuit.hotel.mapper.HotelInfoMapper;
import cuit.hotel.service.HotelInfoService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HotelInfoServiceImpl implements HotelInfoService {
    @Resource
    private HotelInfoMapper hotelInfoMapper;

    @Override
    public HotelInfo getByHotelID(Integer hotelID) {
        return hotelInfoMapper.selectByPrimaryKey(hotelID);
    }

//    @Override
//    public PageInfo<HotelInfo> getHotelInfoPage(PagePojo pagePojo) {
//        if ((pagePojo.getOrder()==null || pagePojo.getOrder().equals(""))
//                && (pagePojo.getSortName()==null || pagePojo.getSortName().equals(""))){
//            PageHelper.startPage(pagePojo.getPageNum(), pagePojo.getPageSize());
//        }else{
//            // 处理中文乱码
//            String orderBy = "CONVERT("+pagePojo.getSortName()+" USING gbk)"+pagePojo.getOrder();
//            if (pagePojo.getOrder().equals("RAND()"))
//                orderBy = pagePojo.getOrder();
//            PageHelper.startPage(pagePojo.getPageNum(), pagePojo.getPageSize(),orderBy);
//        }
//        HotelInfoExample hotelInfoExample = new HotelInfoExample();
//        List<HotelInfo> list = hotelInfoMapper.selectByExample(hotelInfoExample);
//        return new PageInfo<>(list);
//    }

    @Override
    public PageInfo<HotelInfo> getHotelInfoPage(HotelInfo hotelInfo, PagePojo pagePojo, Double hightestPrice) {
        // 开始分页 (有无排序信息)
//        if ((pagePojo.getOrder()==null || pagePojo.getOrder().equals(""))
//                && (pagePojo.getSortName()==null || pagePojo.getSortName().equals(""))){
            PageHelper.startPage(pagePojo.getPageNum(), pagePojo.getPageSize());
//        }else{
//            // 处理中文乱码
//            String orderBy = "CONVERT("+pagePojo.getSortName()+" USING gbk)"+pagePojo.getOrder();
//            if (pagePojo.getOrder().equals("RAND()"))
//                orderBy = pagePojo.getOrder();
//            PageHelper.startPage(pagePojo.getPageNum(), pagePojo.getPageSize(),orderBy);
//        }
        HotelInfoExample hotelInfoExample = new HotelInfoExample();
        if (pagePojo.getOrder()!=null && pagePojo.getSortName()!=null) {
            hotelInfoExample.setOrderByClause(pagePojo.getSortName()+" "+pagePojo.getOrder());
        }
        if (pagePojo.getOrder()!=null && pagePojo.getOrder().equals("RAND()"))
            hotelInfoExample.setOrderByClause(pagePojo.getOrder());
        HotelInfoExample.Criteria criteria = hotelInfoExample.createCriteria();
        if (hotelInfo.getHotelName() != null)
            criteria.andHotelNameLike("%" + hotelInfo.getHotelName() + "%");
        if (hotelInfo.getCity() != null)
            criteria.andCityEqualTo(hotelInfo.getCity());
        if (hotelInfo.getStartingPrice()!=null && hightestPrice!=null)
            criteria.andStartingPriceBetween(hotelInfo.getStartingPrice(), hightestPrice);

        return new PageInfo<>(hotelInfoMapper.selectByExampleWithBLOBs(hotelInfoExample));
    }
}
