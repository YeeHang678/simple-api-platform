package com.ehong.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ehong.project.annotation.AuthCheck;
import com.ehong.project.common.BaseResponse;
import com.ehong.project.common.ErrorCode;
import com.ehong.project.common.ResultUtils;
import com.ehong.project.exception.BusinessException;
import com.ehong.project.mapper.UserInterfaceInfoMapper;
import com.ehong.project.model.vo.InterfaceInfoVO;
import com.ehong.project.service.InterfaceInfoService;
import com.ehong.ehongapicommon.model.entity.InterfaceInfo;
import com.ehong.ehongapicommon.model.entity.UserInterfaceInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 分析控制器
 */
@RestController
@RequestMapping("/analysis")
@Slf4j
public class AnalysisController {

    @Resource
    private UserInterfaceInfoMapper userInterfaceInfoMapper;

    @Resource
    private InterfaceInfoService interfaceInfoService;

    @Resource
    private RedisTemplate<String, List<InterfaceInfoVO>> redisTemplate;

    @GetMapping("/top/interface/invoke")
    @AuthCheck(mustRole = "admin")
    public BaseResponse<List<InterfaceInfoVO>> listTopInvokeInterfaceInfo() {
        String topInvokeInterfaceKey = "topInvokeInterface";

        List<InterfaceInfoVO> interfaceInfoVOList = redisTemplate.opsForValue().get(topInvokeInterfaceKey);
        //如果为空则写缓存（热点数据时效性不高）
        if (interfaceInfoVOList.isEmpty()) {
            List<UserInterfaceInfo> userInterfaceInfoList = userInterfaceInfoMapper.listTopInvokeInterfaceInfo(3);
            Map<Long, List<UserInterfaceInfo>> interfaceInfoIdObjMap = userInterfaceInfoList.stream()
                    .collect(Collectors.groupingBy(UserInterfaceInfo::getInterfaceInfoId));
            QueryWrapper<InterfaceInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.in("id", interfaceInfoIdObjMap.keySet());
            List<InterfaceInfo> list = interfaceInfoService.list(queryWrapper);
            if (CollectionUtils.isEmpty(list)) {
                throw new BusinessException(ErrorCode.SYSTEM_ERROR);
            }
            interfaceInfoVOList = list.stream().map(interfaceInfo -> {
                InterfaceInfoVO interfaceInfoVO = new InterfaceInfoVO();
                BeanUtils.copyProperties(interfaceInfo, interfaceInfoVO);
                int totalNum = interfaceInfoIdObjMap.get(interfaceInfo.getId()).get(0).getTotalNum();
                interfaceInfoVO.setTotalNum(totalNum);
                return interfaceInfoVO;
            }).collect(Collectors.toList());
            ValueOperations<String, List<InterfaceInfoVO>> valueOperations = redisTemplate.opsForValue();
            // 写缓存
            try {
                valueOperations.set(topInvokeInterfaceKey, interfaceInfoVOList, 30 * 1000, TimeUnit.MILLISECONDS);
            } catch (Exception e) {
                log.error("redis set key error", e);
            }

        }
        return ResultUtils.success(interfaceInfoVOList);
    }
}
