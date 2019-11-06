package com.venn.user.service;

import com.venn.domain.dto.UserInfoDTO;
import com.venn.user.entity.UserInfoEntity;
import com.venn.user.manager.UserInfoManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author 杜文锋
 * @version 1.0.0
 * @date 2019/10/30 14:55
 */
@Component
@RefreshScope
public class UserInfoService {

    private static final Logger LOGGER = LogManager.getLogger(UserInfoService.class);

    private final UserInfoManager userInfoManager;

    public UserInfoService(UserInfoManager userInfoManager) {
        this.userInfoManager = userInfoManager;
    }

    @Value("${abc}")
    private String refreshDataInRealTime;

    public UserInfoDTO getUserInfoByUserId(String userId) {

        LOGGER.info("service abc:{}", refreshDataInRealTime);

        UserInfoEntity entity = userInfoManager.getUserInfoByUserId(userId);

        if (entity != null) {
            UserInfoDTO dto = new UserInfoDTO();
            BeanUtils.copyProperties(entity, dto);
            return dto;
        }
        return null;
    }
}
