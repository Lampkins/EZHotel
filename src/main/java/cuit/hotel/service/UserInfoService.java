package cuit.hotel.service;

import com.github.pagehelper.PageInfo;
import cuit.hotel.common.PagePojo;
import cuit.hotel.entity.UserInfo;

public interface UserInfoService {

    PageInfo<UserInfo> getUserInfoPage(PagePojo pagePojo);

    UserInfo getByUserID(Integer userID);

    UserInfo getByUsernameAndRoleID(String username, Integer roleID);

    Integer saveUserInfo(UserInfo userInfo);

    Integer updUserInfo(UserInfo userInfo);
}
