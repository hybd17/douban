package com.experimental.douban.config;


import com.experimental.douban.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

//过滤器
@Configuration
public class LoginInterceptorConfiguration implements WebMvcConfigurer {
    HandlerInterceptor interceptor = new LoginInterceptor();
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> pattern = new ArrayList<>();
        pattern.add("/css/**");
        pattern.add("/js/**");
        pattern.add("/images/**");
        //放行一些web页面
        pattern.add("/user/**");
        pattern.add("/movie/**");
        //拦截器注册
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(pattern);
    }
}
