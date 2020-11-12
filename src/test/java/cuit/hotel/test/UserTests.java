package cuit.hotel.test;

import cuit.hotel.HotelApplication;
import cuit.hotel.entity.UserInfo;
import cuit.hotel.mapper.UserInfoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.annotation.Resource;

@SpringBootTest(classes= HotelApplication.class)
public class UserTests {

    @Resource
    UserInfoMapper userInfoMapper;
    @Test
    public void testBCrypt() {
        UserInfo userInfo = new UserInfo();
        // 对密码进行加密
        userInfo.setUsername("manager");
        userInfo.setPassword(BCrypt.hashpw("1234", BCrypt.gensalt()));
        userInfo.setRoleID(2);
        // 校验密码
        userInfoMapper.insertSelective(userInfo);
    }

}
