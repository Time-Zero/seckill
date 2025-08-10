package com.timezero.seckill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.timezero.seckill.entity.UserEntity;
import com.timezero.seckill.responsity.UserRepository;
import com.timezero.seckill.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 用户服务实现类
 *
 * @author TimeZero
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserRepository, UserEntity> implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserEntity authenticate(String username, String password) {
        LambdaQueryWrapper<UserEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserEntity::getName, username)
                .eq(UserEntity::getPassword, password);
        return userRepository.selectOne(queryWrapper);
    }
}
