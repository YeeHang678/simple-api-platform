package com.ehong.project.service.impl.inner;

import com.ehong.ehongapicommon.model.entity.UserInterfaceInfo;
import com.ehong.project.service.UserInterfaceInfoService;
import com.ehong.ehongapicommon.service.InnerUserInterfaceInfoService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

@DubboService
public class InnerUserInterfaceInfoServiceImpl implements InnerUserInterfaceInfoService {

    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;

    @Override
    public boolean invokeCount(long interfaceInfoId, long userId) {
        return userInterfaceInfoService.invokeCount(interfaceInfoId, userId);
    }

    @Override
    public UserInterfaceInfo getUserInterfaceInfo(long interfaceInfoId, long userId) {
        return userInterfaceInfoService.getUserInterfaceInfo(interfaceInfoId, userId);
    }
}
