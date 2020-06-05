package com.venn.user.service.impl;

import com.venn.domain.dto.UserInfoDTO;
import com.venn.user.entity.UserDomain;
import com.venn.user.manager.UserInfoManager;
import com.venn.user.service.UserInfoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author venn
 * @version 1.0.0
 * @date 2020/6/5
 */
@RefreshScope
@Component
//@Service
public class UserInfoServiceImpl implements UserInfoService {

    private static final Logger LOGGER = LogManager.getLogger(UserInfoService.class);

    private final UserInfoManager userInfoManager;

    public UserInfoServiceImpl(UserInfoManager userInfoManager) {
        this.userInfoManager = userInfoManager;
    }

    @Value("${abc}")
    private String refreshDataInRealTime;

    @Override
    public UserInfoDTO getUserInfoByUserId(Long userId) {

        LOGGER.info("service abc:{}", refreshDataInRealTime);

        UserDomain entity = userInfoManager.getUserInfoByUserId(userId);

        if (entity != null) {
            UserInfoDTO dto = new UserInfoDTO();
            BeanUtils.copyProperties(entity, dto);
            return dto;
        }
        return null;
    }
}