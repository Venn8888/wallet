package com.venn.feign.hystrix;

import com.venn.domain.IResponse;
import com.venn.domain.dto.UserInfoDTO;
import com.venn.domain.enums.ErrorCodeMsgEnum;
import com.venn.feign.facade.UserRpcFacade;
import com.venn.utils.IResponseUtil;
import org.springframework.stereotype.Component;

/**
 * @author 杜文锋
 * @version 1.0.0
 * @date 2019/11/4 16:03
 */
@Component
public class UserRpcHystrix implements UserRpcFacade {

    @Override
    public IResponse<UserInfoDTO> getUserInfoByUserId(String userId) {
        return IResponseUtil.fail(ErrorCodeMsgEnum.FAIL);
    }

}
