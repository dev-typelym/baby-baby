package com.app.babybaby.config;

import com.app.babybaby.interceptor.AlarmInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class WebMvcConfig implements WebMvcConfigurer {

    //    제작한 Interceptor를 주입
    private final AlarmInterceptor alarmInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(alarmInterceptor)
                .addPathPatterns("/main/**") // 작성한 경로에 Interceptor 동작
                .addPathPatterns("/mypage/**") // 작성한 경로에 Interceptor 동작
                .addPathPatterns("/alert/**") // 작성한 경로에 Interceptor 동작
                .excludePathPatterns("/admin/**");

    }
}
