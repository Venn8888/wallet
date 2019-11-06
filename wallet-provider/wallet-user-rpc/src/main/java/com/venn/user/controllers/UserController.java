package com.venn.user.controllers;

import com.venn.domain.IResponse;
import com.venn.domain.dto.UserInfoDTO;
import com.venn.user.service.UserInfoService;
import com.venn.utils.IResponseUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 杜文锋
 * @version 1.0.0
 * @date 2019/10/30 14:44
 */
@RestController
@RefreshScope
public class UserController {

    private static final Logger LOGGER = LogManager.getLogger(UserController.class);

    private final UserInfoService userInfoService;

    public UserController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @Value("${abc}")
    private String refreshDataInRealTime;

    @GetMapping(value = "/user/get")
    public IResponse<UserInfoDTO> getUserInfoByUserId(String userId) {
        LOGGER.info("controller abc:{}", refreshDataInRealTime);
        return IResponseUtil.success(userInfoService.getUserInfoByUserId(userId));
    }
}
