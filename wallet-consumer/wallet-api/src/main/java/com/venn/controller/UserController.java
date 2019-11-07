package com.venn.controller;

import com.venn.domain.IResponse;
import com.venn.domain.vo.req.UserInfoReqVO;
import com.venn.domain.vo.rsp.UserInfoRspVO;
import com.venn.service.UserService;
import com.venn.utils.IResponseUtil;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author venn
 * @version 1.0.0
 * @date 2019/11/4 16:29
 */
@RestController
@RefreshScope
@EnableDiscoveryClient
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("get")
    public IResponse getUserInfo(@RequestBody @Validated UserInfoReqVO userInfoReqVO) {
        UserInfoRspVO userInfo = userService.getUserInfo(userInfoReqVO);
        return  IResponseUtil.success(userInfo);
    }
}
