package com.venn.message.service.impl;
import com.venn.domain.dto.MobileMessageDTO;
import com.venn.message.service.MobileMessageService;
import org.apache.dubbo.config.annotation.Service;

import java.util.Date;

/**
 * @author venn
 * @version 1.0.0
 * @date 2020/6/5
 */
@Service
public class MobileMessageServiceImpl implements MobileMessageService {


    @Override
    public MobileMessageDTO getMobileMessageDTO(Long id) {
        MobileMessageDTO mobileMessageDTO = new MobileMessageDTO();
        mobileMessageDTO.setId(0L);
        mobileMessageDTO.setEventName("12");
        mobileMessageDTO.setMessageText("12");
        mobileMessageDTO.setRecipient("21");
        mobileMessageDTO.setCountRecipient(0L);
        mobileMessageDTO.setFailRecipient("1");
        mobileMessageDTO.setCountFail(0L);
        mobileMessageDTO.setStoreCode("12");
        mobileMessageDTO.setStatus((byte)0);
        mobileMessageDTO.setAuthor("1");
        mobileMessageDTO.setReviewer("12");
        mobileMessageDTO.setReasonRejection("21");
        mobileMessageDTO.setPushMessageTime(new Date());
        mobileMessageDTO.setReviewTime(new Date());

        return mobileMessageDTO;
    }
}
