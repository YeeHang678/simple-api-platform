package com.ehong.ehongapicommon.service;

import com.ehong.ehongapicommon.model.entity.UserInterfaceInfo;

/**
 *
 */
public interface InnerUserInterfaceInfoService {

    /**
     * 调用接口统计
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    boolean invokeCount(long interfaceInfoId, long userId);

    UserInterfaceInfo getUserInterfaceInfo(long interfaceInfoId, long userId);
}
