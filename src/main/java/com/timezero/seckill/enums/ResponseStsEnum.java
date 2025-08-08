package com.timezero.seckill.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 通用枚举
 *
 * @author TimeZero
 */
@Getter
@AllArgsConstructor
public enum ResponseStsEnum {
    REQUEST_SUCCESS("SUC0000", "请求成功"),
    BUSINESS_BUSY("ERO0001", "业务繁忙"),
    USER_NOT_LOGIN("ERO0002", "用户未登录");

    /**
     * 状态码
     */
    private final String stsCode;
    /**
     * 状态信息
     */
    private final String stsMsg;
}
