package cuit.hotel.component;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cuit.hotel.common.ResultUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        JSONObject json = new JSONObject();
        List<String> roles = new ArrayList<>();
        for (GrantedAuthority authority: authentication.getAuthorities()){
            roles.add(authority.getAuthority());
        }
        json.putOnce("roleID", roles);
        if (roles.get(0).equals("0")){
            json.putOnce("url", "http://localhost:8080/doc.html,http://localhost:8080/druid/index.html");
            // 跳转到输入的url
            super.onAuthenticationSuccess(request, response, authentication);
        }else {
            json.putOnce("url", "待开发");
            writer.write(JSONUtil.toJsonStr(ResultUtils.success("登录成功", json)));
            writer.flush();
            writer.close();
        }
    }
}
