package com.venn.controller;

import com.venn.domain.IResponse;
import com.venn.domain.vo.req.UserInfoReqVO;
import com.venn.domain.vo.rsp.UserInfoRspVO;
import com.venn.service.UserService;
import com.venn.utils.IResponseUtil;
import com.venn.utils.IdWorkerConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private IdWorkerConfiguration idWorkerConfiguration;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("get")
    public IResponse getUserInfo(@RequestBody @Validated UserInfoReqVO userInfoReqVO) {
        long nextId = idWorkerConfiguration.idWorker().nextId();
        System.out.println("------------>" + nextId);
        UserInfoRspVO userInfo = userService.getUserInfo(userInfoReqVO);
        return IResponseUtil.success(userInfo);
    }
}
