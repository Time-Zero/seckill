package com.timezero.seckill.controller.auth;

import cn.hutool.core.util.ObjectUtil;
import com.timezero.seckill.annotations.NoAuth;
import com.timezero.seckill.entity.UserEntity;
import com.timezero.seckill.service.UserService;
import com.timezero.seckill.utils.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 认证控制
 *
 * @author TimeZero
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    private final JwtUtil jwtUtil;

    @NoAuth
    @PostMapping("/login")
    public ResponseEntity<?> login(String username, String password) {
        UserEntity user = userService.authenticate(username, password);
        if (ObjectUtil.isNotNull(user)) {
            String token = jwtUtil.createToken(username);
            return ResponseEntity.ok().header("Authorization", "Bearer " + token).body("Login successful");
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }
}
