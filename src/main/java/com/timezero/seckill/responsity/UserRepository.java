package com.timezero.seckill.responsity;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.timezero.seckill.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户仓库
 *
 * @author TimeZero
 */
@Mapper
public interface UserRepository extends BaseMapper<UserEntity> {
}
