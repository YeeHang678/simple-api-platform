package com.ehong.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ehong.ehongapicommon.model.entity.InterfaceInfo;
import com.ehong.ehongapicommon.model.entity.UserInterfaceInfo;

/**
 *
 */
public interface InterfaceInfoService extends IService<InterfaceInfo> {

    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);

}
