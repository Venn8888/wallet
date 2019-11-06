package com.venn.feign.facade;

import com.venn.domain.IResponse;
import com.venn.domain.dto.UserInfoDTO;
import com.venn.feign.hystrix.UserRpcHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 杜文锋
 * @version 1.0.0
 * @date 2019/11/4 15:59
 */
@FeignClient(name = "user-rpc", fallback = UserRpcHystrix.class)
public interface UserRpcFacade {

    @GetMapping(value = "/user/get")
    IResponse<UserInfoDTO> getUserInfoByUserId(@RequestParam String userId);
}
