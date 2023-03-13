package com.ehong.project.service.impl.inner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ehong.ehongapicommon.model.entity.User;
import com.ehong.ehongapicommon.service.InnerUserService;
import com.ehong.project.common.ErrorCode;
import com.ehong.project.exception.BusinessException;
import com.ehong.project.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@DubboService
@Slf4j
public class InnerUserServiceImpl implements InnerUserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisTemplate<String, User> redisTemplate;

    @Override
    public User getInvokeUser(String accessKey) {
        if (StringUtils.isAnyBlank(accessKey)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("accessKey", accessKey);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public User putInvokerCache(String accessKey, User user) {

        ValueOperations<String, User> valueOperations = redisTemplate.opsForValue();
        // 写缓存
        try {
            valueOperations.set(accessKey, user, 30 * 1000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            log.error("redis set key error", e);
        }
        return user;
    }

    @Override
    public User getInvokerCache(String accessKey) {
        User user = null;
        try {
            user = redisTemplate.opsForValue().get(accessKey);
        } catch (Exception e) {
            log.error("redis get key error", e);
        }
        return user;
    }

    @Override
    public void removeInvokerCache(String accessKey) {
        try {
            redisTemplate.delete(accessKey);
        } catch (Exception e) {
            log.error("redis delete key error", e);
        }

    }
}
