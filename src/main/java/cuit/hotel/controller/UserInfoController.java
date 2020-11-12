package cuit.hotel.controller;

import cn.hutool.json.JSONUtil;
import com.github.pagehelper.PageInfo;
import cuit.hotel.common.PagePojo;
import cuit.hotel.common.StatusEnum;
import cuit.hotel.entity.UserInfo;
import cuit.hotel.service.UserInfoService;
import cuit.hotel.common.Result;
import cuit.hotel.common.ResultUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class UserInfoController {

    @Resource
    private UserInfoService userDetailsServiceImpl;

    @Resource
    private PasswordEncoder passwordEncoder;

    @PutMapping("/users")
    public Result changeUserInfo(UserInfo userInfo,
                                 @RequestParam(required = false) String newPassword) {
        UserInfo user = userDetailsServiceImpl.getByUserID(userInfo.getUserID());
        if (user == null)
            return ResultUtils.failure();
        userInfo.setRoleID(1);
        // 修改username
        if (userInfo.getUsername()!=null && !userInfo.getUsername().equals("")
                && !userInfo.getUsername().equals(user.getUsername())){
            UserInfo userTemp = userDetailsServiceImpl.getByUsernameAndRoleID(userInfo.getUsername(), 1);
            if (userTemp != null)
                return ResultUtils.failure("该用户名已被注册");
            user.setUsername(userInfo.getUsername());
        }
        // 修改password
        if (userInfo.getPassword()!= null && newPassword!=null){
            if (!passwordEncoder.matches(userInfo.getPassword(), user.getPassword()))
                return ResultUtils.failure("密码错误");
            user.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
        }
        // 修改其他信息
        if (userInfo.getMemberLevel()!=null && !userInfo.getMemberLevel().equals(""))
            user.setMemberLevel(userInfo.getMemberLevel());
        if (userInfo.getFullName()!=null && !userInfo.getFullName().equals(""))
            user.setFullName(userInfo.getFullName());
        if (userInfo.getPhone()!=null && !userInfo.getPhone().equals(""))
            user.setPhone(userInfo.getPhone());
        if (userInfo.getGender()!=null && !userInfo.getGender().equals(""))
            user.setGender(userInfo.getGender());
        if (userInfo.getMail()!=null && !userInfo.getMail().equals(""))
            user.setMail(userInfo.getMail());
        System.out.println(JSONUtil.parse(userInfo));
        Integer row =userDetailsServiceImpl.updUserInfo(user);
        if (row == 0)
            return ResultUtils.failure("信息输入出错");
        return ResultUtils.success(StatusEnum.CREATED, user);
    }

    @PreAuthorize("hasAuthority('0')")
    @GetMapping("/users")
    public Result getUserInfoPage(PagePojo pagePojo) {
        PageInfo<UserInfo> userInfoPage = userDetailsServiceImpl.getUserInfoPage(pagePojo);
        if (userInfoPage == null)
            return ResultUtils.failure("获取分页用户信息失败");
        return ResultUtils.success(JSONUtil.parse(userInfoPage));
    }
}
