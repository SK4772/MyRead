package com.csk.csk_english.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author yang
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //此处路径和上面的图片上传位置保持一致
       registry.addResourceHandler("/essay/**").addResourceLocations("file:D:/Code/idea_pro/Csk_English/src/main/resources/static/essay/");
       // registry.addResourceHandler("/essay").addResourceLocations("file:"+"/uploadedImg/");
    }
}
