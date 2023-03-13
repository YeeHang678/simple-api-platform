package com.ehong.ehongapicommon.service;

import com.ehong.ehongapicommon.model.entity.InterfaceInfo;

/**
 *
 */
public interface InnerInterfaceInfoService {

    /**
     * 从数据库中查询模拟接口是否存在（请求路径、请求方法、请求参数）
     */
    InterfaceInfo getInterfaceInfo(String path, String method);

    /**
     * 新增接口信息缓存
     * @param methodKey
     * @param interfaceInfo
     * @return
     */
    public InterfaceInfo putInterfaceInfoCache(String methodKey, InterfaceInfo interfaceInfo);

    /**
     * 根据用户id获取接口信息缓存
     * @param methodKey
     * @return
     */
    public InterfaceInfo getInterfaceInfoCache(String methodKey);

    /**
     * 根据用户id删除接口信息缓存
     * @param methodKey
     */
    public void removeInterfaceInfoCache(String methodKey) ;
}
