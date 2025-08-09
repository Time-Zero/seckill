package com.timezero.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.timezero.seckill.entity.UserEntity;
import com.timezero.seckill.responsity.UserRepository;
import com.timezero.seckill.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 用户服务实现类
 *
 * @author TimeZero
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserRepository, UserEntity> implements UserService {
}
