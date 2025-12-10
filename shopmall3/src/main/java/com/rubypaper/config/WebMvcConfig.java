package com.rubypaper.config;

import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // 프로젝트 루트 기준 경로를 절대경로로 변환
        String fullPath = Paths.get(uploadPath)
                .toAbsolutePath()
                .normalize()
                .toUri()
                .toString();

        System.out.println("이미지 매핑 경로 → " + fullPath);

        registry.addResourceHandler("/images/**")
                // 업로드한 이미지들
                .addResourceLocations(fullPath)
                // 기존 static 이미지도 유지
                .addResourceLocations("classpath:/static/images/");
    }
}
