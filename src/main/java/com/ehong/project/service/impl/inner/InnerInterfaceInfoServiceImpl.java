package com.ehong.project.service.impl.inner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ehong.ehongapicommon.model.entity.InterfaceInfo;
import com.ehong.ehongapicommon.service.InnerInterfaceInfoService;
import com.ehong.project.common.ErrorCode;
import com.ehong.project.exception.BusinessException;
import com.ehong.project.mapper.InterfaceInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@DubboService
@Slf4j
public class InnerInterfaceInfoServiceImpl implements InnerInterfaceInfoService {

    @Resource
    private InterfaceInfoMapper interfaceInfoMapper;

    @Resource
    private RedisTemplate<String, InterfaceInfo> redisTemplate;

    @Override
    public InterfaceInfo getInterfaceInfo(String url, String method) {
        if (StringUtils.isAnyBlank(url, method)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<InterfaceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("url", url);
        queryWrapper.eq("method", method);
        return interfaceInfoMapper.selectOne(queryWrapper);
    }

    @Override
    public InterfaceInfo putInterfaceInfoCache(String methodKey, InterfaceInfo interfaceInfo) {
        ValueOperations<String, InterfaceInfo> valueOperations = redisTemplate.opsForValue();
        // 写缓存
        try {
            valueOperations.set(methodKey, interfaceInfo, 30 * 1000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            log.error("redis set key error", e);
        }
        return interfaceInfo;
    }

    @Override
    public InterfaceInfo getInterfaceInfoCache(String methodKey) {
        InterfaceInfo interfaceInfo = null;
        try {
            interfaceInfo = redisTemplate.opsForValue().get(methodKey);
        } catch (Exception e) {
            log.error("redis get key error", e);
        }
        return interfaceInfo;
    }

    @Override
    public void removeInterfaceInfoCache(String methodKey) {
        try {
            redisTemplate.delete(methodKey);
        } catch (Exception e) {
            log.error("redis delete key error", e);
        }
    }
}
