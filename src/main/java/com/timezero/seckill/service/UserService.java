package com.timezero.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.timezero.seckill.entity.UserEntity;

/**
 * 用户服务
 *
 * @author TimeZero
 */
public interface UserService extends IService<UserEntity> {
    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    UserEntity authenticate(String username, String password);
}
