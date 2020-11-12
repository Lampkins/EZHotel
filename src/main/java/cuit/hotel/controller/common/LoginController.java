package cuit.hotel.controller.common;

import cuit.hotel.common.Result;
import cuit.hotel.common.StatusEnum;
import cuit.hotel.entity.UserInfo;
import cuit.hotel.common.ResultUtils;
import cuit.hotel.service.impl.UserDetailsServiceImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class LoginController {

    @Resource
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Resource
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signIn")
    public Result userSignIn(UserInfo userInfo) {
        UserInfo user = userDetailsServiceImpl.getByUsernameAndRoleID(userInfo.getUsername(), 1);
        if (user == null)
            return ResultUtils.failure("用户不存在");
        else if (!passwordEncoder.matches(userInfo.getPassword(), user.getPassword()))
            return ResultUtils.failure("密码错误");
        return ResultUtils.success(user);
    }

    @PostMapping("/register")
    public Result register(UserInfo userInfo) {
        if (userInfo.getUsername()==null || userInfo.getUsername().equals(""))
            return ResultUtils.failure("注册失败");
        userInfo.setRoleID(1);
        userInfo.setMemberLevel("普通会员");
        if (userDetailsServiceImpl.getByUsernameAndRoleID(userInfo.getUsername(), 1) != null) {
            return ResultUtils.failure("该用户名已被使用");
        }
        Integer row = userDetailsServiceImpl.saveUserInfo(userInfo);
        if (row == 0)
            return ResultUtils.failure("注册失败");
        return ResultUtils.success(StatusEnum.CREATED);
    }

//    @ApiOperation(value = "管理员登录验证", notes = "管理员登录：/login")
//    @PostMapping("/admin/checkLogin")
//    public Result adminLogin(UserInfo userInfo) {
//        System.out.println("测试测试");
////        UserInfo user = userDetailsServiceImpl.getByUsernameAndRoleID(userInfo.getUsername(), null);
//        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(userInfo.getUsername());
//        if (userDetails == null)
//            return ResultUtils.failure("用户不存在");
//        else if (!passwordEncoder.matches(userInfo.getPassword(), userDetails.getPassword())) {
//            return ResultUtils.failure("密码错误");
//        }
//        return ResultUtils.success("登录成功");
//    }
}
