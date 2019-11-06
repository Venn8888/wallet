package com.venn.feign.hystrix;

import com.venn.domain.IResponse;
import com.venn.domain.dto.MobileMessageDTO;
import com.venn.domain.enums.ErrorCodeMsgEnum;
import com.venn.feign.facade.MessageRpcFacade;
import com.venn.utils.IResponseUtil;
import org.springframework.stereotype.Component;

/**
 * @author 杜文锋
 * @version 1.0.0
 * @date 2019/11/4 16:07
 */
@Component
public class MessageRpcHystrix implements MessageRpcFacade {

    @Override
    public IResponse<MobileMessageDTO> getMobileMessageDTO(Long id) {

        return IResponseUtil.fail(ErrorCodeMsgEnum.DATA_NOT_EXIST);
    }

}
