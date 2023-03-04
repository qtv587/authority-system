package com.manong.config.swagger;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Author: XiaoDu
 * @CreateTime: 2023/3/3 20:59
 * @Description:
 */
//@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    // 请求路径排除
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor()
//                .addPathPatterns("/**")
//                .excludePathPatterns("/swagger-resources/**")
//                .excludePathPatterns("/webjars/**")
//                .excludePathPatterns("/v2/**")
//                .excludePathPatterns("/swagger-ui.html/**");
        super.addInterceptors(registry);
    }

    // 资源映射增加
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }
}