package com.timezero.seckill.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * JWT 配置
 *
 * @author TimeZero
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {
    /**
     * 密钥
     */
    private String secret;
    /**
     * token 过期时间
     */
    private long expiration;
    /**
     * 请求头
     */
    private String header;
}
