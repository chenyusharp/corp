package com.xzy.sso.server.service.controler.interceptor;

import javax.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Date: 2023/7/31
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {


    @Resource
    private PermissionInterceptor permissionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(permissionInterceptor).addPathPatterns("/**");
    }
}