package org.kamjeon.pcforge.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	System.out.println("WebConfig 적용 완료!");
        // /upload/** 경로로 접근하면 실제 /home/ubuntu/upload/ 경로에 있는 파일을 제공
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:/home/ubuntu/upload/");  // 업로드된 파일 경로 설정
    }
}
