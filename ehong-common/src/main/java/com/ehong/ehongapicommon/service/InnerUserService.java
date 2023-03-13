package com.ehong.ehongapicommon.service;

import com.ehong.ehongapicommon.model.entity.User;


/**
 * 用户服务
 *
 * @author ehong
 */
public interface InnerUserService {

    /**
     * 数据库中查是否已分配给用户秘钥（accessKey）
     * @param accessKey
     * @return
     */
    User getInvokeUser(String accessKey);

    /**
     * 新增用户信息缓存
     * @param accessKey
     * @param user
     * @return
     */
    public User putInvokerCache(String accessKey, User user);

    /**
     * 根据用户id获取用户信息缓存
     * @param accessKey
     * @return
     */
    public User getInvokerCache(String accessKey);

    /**
     * 根据用户id删除用户信息缓存
     * @param accessKey
     */
    public void removeInvokerCache(String accessKey);

}
