package cuit.hotel.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cuit.hotel.common.PagePojo;
import cuit.hotel.entity.UserInfo;
import cuit.hotel.entity.UserInfoExample;
import cuit.hotel.mapper.UserInfoMapper;
import cuit.hotel.service.UserInfoService;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService, UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public PageInfo<UserInfo> getUserInfoPage(PagePojo pagePojo) {
        if (pagePojo.getSortName()==null || pagePojo.getSortName().equals("")){
            PageHelper.startPage(pagePojo.getPageNum(), pagePojo.getPageSize());
        }else{
            // 处理中文乱码
            String orderBy = "CONVERT("+pagePojo.getSortName()+" USING gbk)"+pagePojo.getOrder();
            PageHelper.startPage(pagePojo.getPageNum(), pagePojo.getPageSize(),orderBy);
        }
        UserInfoExample userInfoExample = new UserInfoExample();
        List<UserInfo> list = userInfoMapper.selectByExample(userInfoExample);
        return new PageInfo<>(list);
    }

    @Override
    public UserInfo getByUserID(Integer userID) {
        return userInfoMapper.selectByPrimaryKey(userID);
    }

    @Override
    public UserInfo getByUsernameAndRoleID(String username, Integer roleID) {
        UserInfoExample userInfoExample = new UserInfoExample();
        UserInfoExample.Criteria criteria = userInfoExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        if (roleID == null)
            criteria.andRoleIDNotEqualTo(1);
        else
            criteria.andRoleIDEqualTo(roleID);
        List<UserInfo> userInfoList = userInfoMapper.selectByExample(userInfoExample);
        if (userInfoList==null || userInfoList.size() == 0)
            return null;
        return userInfoList.get(0);
    }

    @Override
    public Integer saveUserInfo(UserInfo userInfo) {
        // 对密码进行加密
        userInfo.setPassword(BCrypt.hashpw(userInfo.getPassword(), BCrypt.gensalt()));
        userInfoMapper.insertSelective(userInfo);
        return userInfo.getUserID();
    }

    @Override
    public Integer updUserInfo(UserInfo userInfo) {
        return userInfoMapper.updateByPrimaryKeySelective(userInfo);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = this.getByUsernameAndRoleID(username, null);
        if (userInfo == null) {
            throw new UsernameNotFoundException("");
        }
        return User.withUsername(userInfo.getUsername())
                .password(userInfo.getPassword()).authorities(String.valueOf(userInfo.getRoleID())).build();
    }
}
