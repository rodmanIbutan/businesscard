package com.wuzhou.businesscard.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 静态资源配置类
 */
@ComponentScan
@Configuration
public class WebConfig implements WebMvcConfigurer {
    static final String LOCAL_PATH="file:/D:/project/ideaproject/businesscard/static/img/";
    static final String SERVER_PATH="file:/businesscard/static/img/";


    public void addResourceHandlers(org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**").addResourceLocations(SERVER_PATH);
    }

}
