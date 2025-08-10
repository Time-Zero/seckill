package com.timezero.seckill.config;

import com.timezero.seckill.utils.jwt.JwtInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置
 *
 * @author TimeZero
 */
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    /**
     * 添加拦截器
     */
    private final JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(org.springframework.web.servlet.config.annotation.InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**");
    }
}
