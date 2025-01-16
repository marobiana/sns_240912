package com.sns.config;

import com.sns.common.FileManagerService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    // 예언된 이미지 path와 서버(컴퓨터)에 실제 업로드 된 파일을 매핑
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/images/**") // path http://localhost:8080/images/aaaa_1737013427267/declan-sun-wQsf3k2d_hA-unsplash.jpg
                .addResourceLocations("file:///" + FileManagerService.FILE_UPLOAD_PATH); // 실제 파일 위치
    }
}
