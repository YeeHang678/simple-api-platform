package com.ehong.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ehong.ehongapicommon.model.entity.UserInterfaceInfo;

import java.util.List;

/**
 * @Entity com.ehong.project.model.entity.UserInterfaceInfo
 */
public interface UserInterfaceInfoMapper extends BaseMapper<UserInterfaceInfo> {

    List<UserInterfaceInfo> listTopInvokeInterfaceInfo(int limit);
}




