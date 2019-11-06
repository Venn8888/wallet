package com.venn.message.controller;

import com.venn.domain.IResponse;
import com.venn.domain.dto.MobileMessageDTO;
import com.venn.message.service.MobileMessageService;
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
 * @date 2019/10/31 14:21
 */
@RestController
@RefreshScope
public class MobileMessageController {

    private static final Logger LOGGER = LogManager.getLogger(MobileMessageController.class);

    @Value("${abc}")
    private String refreshData;

    private final MobileMessageService mobileMessageService;

    public MobileMessageController(MobileMessageService mobileMessageService) {
        this.mobileMessageService = mobileMessageService;
    }

    @GetMapping(value = "/mobile-msg/get")
    public IResponse getMobileMessageDTO(Long id) {
        LOGGER.info("abc:{}", refreshData);
        LOGGER.info("req id:{}", id);
        MobileMessageDTO dto = mobileMessageService.getMobileMessageDTO(id);
        LOGGER.info("rsp:{}", dto);
        return IResponseUtil.success(dto);
    }
}
