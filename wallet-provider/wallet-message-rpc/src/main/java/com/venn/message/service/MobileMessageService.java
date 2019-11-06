package com.venn.message.service;

import com.venn.domain.dto.MobileMessageDTO;
import com.venn.message.entity.MobileMessageEntity;
import com.venn.message.manager.MobileMessageManager;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @author 杜文锋
 * @version 1.0.0
 * @date 2019/10/31 14:18
 */
@Component
public class MobileMessageService {

    private final MobileMessageManager mobileMessageManager;

    public MobileMessageService(MobileMessageManager mobileMessageManager) {
        this.mobileMessageManager = mobileMessageManager;
    }

    public MobileMessageDTO getMobileMessageDTO(Long id) {
        MobileMessageEntity entity = mobileMessageManager.getMobileMessageById(id);
        if (entity != null) {
            MobileMessageDTO dto = new MobileMessageDTO();
            BeanUtils.copyProperties(entity, dto);
            return dto;
        }
        return null;
    }
}
