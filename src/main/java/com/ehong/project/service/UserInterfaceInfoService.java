package com.ehong.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ehong.ehongapicommon.model.entity.UserInterfaceInfo;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

/**
 *
 */
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {
    void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add);

    /**
     * 调用接口统计
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    boolean invokeCount(long interfaceInfoId, long userId);

    /**
     * 获取
     * @param interfaceInfoId
     * @param userId
     * @return
     * */
    public UserInterfaceInfo getUserInterfaceInfo(long interfaceInfoId, long userId);

}
