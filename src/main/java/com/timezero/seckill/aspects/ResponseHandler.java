package com.timezero.seckill.aspects;

import com.alibaba.fastjson2.JSON;
import com.timezero.seckill.annotations.IgnoreHttpResult;
import com.timezero.seckill.dto.HttpResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.annotation.Annotation;

/**
 * 统一返回结果处理
 *
 * @author TimeZero
 */
@RestControllerAdvice(basePackages = "com.timezero.seckill.controller")
public class ResponseHandler implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        for (Annotation annotation : returnType.getMethodAnnotations()) {
            if (annotation.annotationType().equals(IgnoreHttpResult.class)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof HttpResult) {
            return body;
        } else if (body instanceof String) {
            return JSON.toJSONString(HttpResult.success(body));
        } else {
            return body;
        }
    }
}
