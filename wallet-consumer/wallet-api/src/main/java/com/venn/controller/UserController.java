package com.venn.controller;

import com.venn.domain.IResponse;
import com.venn.domain.dto.UserInfoDTO;
import com.venn.domain.vo.req.UserInfoReqVO;
import com.venn.user.service.UserInfoService;
import com.venn.utils.IResponseUtil;
import com.venn.utils.IdWorkerConfiguration;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author venn
 * @version 1.0.0
 * @date 2019/11/4 16:29
 */
@RestController
//@RefreshScope
//@EnableDiscoveryClient
public class UserController {

    @Reference
    UserInfoService userInfoService;

    @Autowired
    private IdWorkerConfiguration idWorkerConfiguration;

    @PostMapping("get")
    public IResponse getUserInfo(@RequestParam @Validated UserInfoReqVO userInfoReqVO) {
        long nextId = idWorkerConfiguration.idWorker().nextId();
        System.out.println("------------>" + nextId);
        UserInfoDTO userInfoByUserId = userInfoService.getUserInfoByUserId(Long.valueOf(userInfoReqVO.getUserId()));
        return IResponseUtil.success(userInfoByUserId);
    }
}
