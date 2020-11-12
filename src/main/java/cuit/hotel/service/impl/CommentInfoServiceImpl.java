package cuit.hotel.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cuit.hotel.common.PagePojo;
import cuit.hotel.entity.CommentInfo;
import cuit.hotel.mapper.CommentInfoMapper;
import cuit.hotel.service.CommentInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentInfoServiceImpl implements CommentInfoService {

    @Resource
    private CommentInfoMapper commentInfoMapper;

    @Override
    public CommentInfo getByBookingID(Integer bookingID) {
        return commentInfoMapper.selectByPrimaryKey(bookingID);
    }

    @Override
    public PageInfo<CommentInfo> getPageByHotelID(Integer hotelID, PagePojo pagePojo) {
        if (pagePojo.getSortName()==null || pagePojo.getSortName().equals("")){
            PageHelper.startPage(pagePojo.getPageNum(), pagePojo.getPageSize());
        }else{
            // 处理中文乱码
            String orderBy = "CONVERT("+pagePojo.getSortName()+" USING gbk)"+pagePojo.getOrder();
            PageHelper.startPage(pagePojo.getPageNum(), pagePojo.getPageSize(),orderBy);
        }
        List<CommentInfo> list = commentInfoMapper.selectByHotelID(hotelID);
        return new PageInfo<>(list);
    }

    @Override
    public Integer saveCommentInfo(CommentInfo commentInfo) {
        return commentInfoMapper.insertSelective(commentInfo);
    }

    @Override
    public Integer updByBookingID(CommentInfo commentInfo) {
        return commentInfoMapper.updateByPrimaryKeySelective(commentInfo);
    }
}
