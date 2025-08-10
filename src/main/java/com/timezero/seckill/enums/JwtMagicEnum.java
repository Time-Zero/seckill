package com.timezero.seckill.enums;

import lombok.Getter;

/**
 * Jwt常用常量枚举
 *
 * @author TimeZero
 */
@Getter
public enum JwtMagicEnum {
    JWT_AUTH_HEADER_START("Bearer "),
    JWT_AUTH_ERROR("Unauthorized");

    private final String value;

    JwtMagicEnum(String value) {
        this.value = value;
    }
}
