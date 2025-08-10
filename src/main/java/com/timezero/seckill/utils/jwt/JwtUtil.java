package com.timezero.seckill.utils.jwt;

import com.timezero.seckill.config.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

/**
 * jwt工具类
 *
 * @author TimeZero
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil {
    public final JwtConfig jwtConfig;

    /**
     * 创建token
     *
     * @param subject
     * @return
     */
    public String createToken(String subject) {
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + jwtConfig.getExpiration() * 1000);

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret())
                .compact();
    }

    /**
     * 验证token
     *
     * @param token
     * @return
     */
    public Claims getTokenClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(jwtConfig.getSecret().getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * 判断token是否过期
     *
     * @param expirationTime
     * @return
     */
    public boolean isTokenExpired(Date expirationTime) {
        return expirationTime.before(new Date());
    }

    /**
     * 获取token的过期时间
     *
     * @param token
     * @return
     */
    public Date getExpirationDateFromToken(String token) {
        Claims claims = getTokenClaims(token);
        return Optional.ofNullable(claims).map(Claims::getExpiration).orElse(null);
    }

    /**
     * 获取token中的用户名
     *
     * @param token
     * @return
     */
    public String getUserNameFromToken(String token) {
        Claims claims = getTokenClaims(token);
        return Optional.ofNullable(claims).map(Claims::getSubject).orElse(null);
    }

    /**
     * 获取token创建时间
     *
     * @param token
     * @return
     */
    public Date getIssuedAtDateFromToken(String token) {
        Claims claims = getTokenClaims(token);
        return Optional.ofNullable(claims).map(Claims::getIssuedAt).orElse(null);
    }

    /**
     * 验证token
     *
     * @param token
     * @return
     */
    public boolean validateToken(String token) {
        Claims claims = getTokenClaims(token);
        return claims != null && !isTokenExpired(claims.getExpiration());
    }
}
