package cuit.hotel.config;

import cuit.hotel.component.LoginFailureHandler;
import cuit.hotel.component.LoginSuccessHandler;
import cuit.hotel.component.MyAccessDeniedHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.annotation.Resource;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	//登录成功handler
	@Resource
	private LoginSuccessHandler loginSuccessHandler;

	//登录失败handler
	@Resource
	private LoginFailureHandler loginFailureHandler;

	// 无权限时处理类
	@Bean
	public AccessDeniedHandler getAccessDeniedHandler() {
		return new MyAccessDeniedHandler();
	}
	// 配置密码编码器
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// 配置安全拦截机制
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//关闭crsf
		http.csrf().disable()
				.authorizeRequests()
				.antMatchers("/druid/**").hasAuthority("0")
				.antMatchers("/swagger-ui.html").hasAuthority("0")
				.antMatchers("/doc.html").hasAuthority("0")
				.antMatchers("/manage/**").hasAnyAuthority("0", "2", "3")
				.anyRequest().permitAll() // 其他请求可直接访问
			.and()
				.formLogin()
				.successHandler(loginSuccessHandler)
				.failureHandler(loginFailureHandler)
			.and()
				.exceptionHandling()
				.accessDeniedHandler(getAccessDeniedHandler());
	}
}
