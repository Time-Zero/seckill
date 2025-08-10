package com.timezero.seckill.controller.user;

import com.timezero.seckill.annotations.NoAuth;
import com.timezero.seckill.entity.UserEntity;
import com.timezero.seckill.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制层
 *
 * @author TimeZero
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @NoAuth
    @RequestMapping("/get")
    public UserEntity getUserInfo(Integer id) {
        return userService.getById(id);
    }
}
