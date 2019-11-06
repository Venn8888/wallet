package com.venn.feign.facade;

import com.venn.domain.IResponse;
import com.venn.domain.dto.MobileMessageDTO;
import com.venn.feign.hystrix.MessageRpcHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 杜文锋
 * @version 1.0.0
 * @date 2019/11/4 16:06
 */
@FeignClient(name = "message-rpc", fallback = MessageRpcHystrix.class)
public interface MessageRpcFacade {

    @GetMapping(value = "/mobile-msg/get")
    IResponse<MobileMessageDTO> getMobileMessageDTO(@RequestParam Long id);
}
