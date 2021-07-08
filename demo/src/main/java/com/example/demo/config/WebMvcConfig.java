package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer { //web 설정 파일

    @Value("${file.path}")
    private String uploadFolder;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);

        //이런거 할떄 이런걸 깊게 들여달 볼 필요가 없다 by 강사님
        registry
                .addResourceHandler("/upload/**") //jsp페이지에서 /upload/** 이런 주소 패턴이 나오면 발동.
                .addResourceLocations("file:///" + uploadFolder)
                .setCachePeriod(60 * 10 * 6) // 60(초)*10*6 => 한시간동안 이미지 캐싱
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }
}
