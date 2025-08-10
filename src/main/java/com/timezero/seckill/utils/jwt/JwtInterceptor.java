package com.timezero.seckill.utils.jwt;

import cn.hutool.core.util.ObjectUtil;
import com.timezero.seckill.annotations.NoAuth;
import com.timezero.seckill.config.JwtConfig;
import com.timezero.seckill.enums.JwtMagicEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * jwt拦截器
 *
 * @author TimeZero
 */
@Component
@RequiredArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {
    /**
     * jwt工具类
     */
    private final JwtUtil jwtUtil;
    /**
     * jwt配置类
     */
    private final JwtConfig jwtConfig;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String authHeader = request.getHeader(jwtConfig.getHeader());

        // 只处理控制器方法
        if (handler instanceof HandlerMethod) {
            // 获取方法注解，并且校验是不是无需认证
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            if (handlerMethod.hasMethodAnnotation(NoAuth.class)) {
                return true;
            }

            // 认证校验流程
            if (ObjectUtil.isNotNull(authHeader) && authHeader.startsWith(JwtMagicEnum.JWT_AUTH_HEADER_START.getValue())) {
                final String authToken = authHeader.substring(7);
                if (jwtUtil.validateToken(authToken)) {
                    return true;
                }
            }
        }

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, JwtMagicEnum.JWT_AUTH_ERROR.getValue());
        return false;
    }
}
