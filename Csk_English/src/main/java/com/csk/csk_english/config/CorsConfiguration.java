package com.csk.csk_english.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //映射所有传入的HTTP请求
        registry.addMapping("/**")
                //允许所有域名的访问
                .allowedOrigins("*")
                //允许所有HTTP方法
                .allowedMethods("*")
                //允许所有请求头
                .allowedHeaders("*")
                //允许跨域访问的最大时间(单位：秒）
                .maxAge(1800);
    }
}

