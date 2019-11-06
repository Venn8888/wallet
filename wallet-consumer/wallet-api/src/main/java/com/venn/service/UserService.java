package com.venn.service;

import com.venn.domain.IResponse;
import com.venn.domain.dto.MobileMessageDTO;
import com.venn.domain.dto.UserInfoDTO;
import com.venn.domain.enums.ErrorCodeMsgEnum;
import com.venn.domain.vo.req.UserInfoReqVO;
import com.venn.domain.vo.rsp.UserInfoRspVO;
import com.venn.feign.facade.MessageRpcFacade;
import com.venn.feign.facade.UserRpcFacade;
import com.venn.exception.WalletException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 杜文锋
 * @version 1.0.0
 * @date 2019/11/4 16:41
 */
@Component
public class UserService {

    private static final Logger log = LogManager.getLogger(UserService.class);

    private final UserRpcFacade userRpcFacade;

    private final MessageRpcFacade messageRpcFacade;

    @Autowired
    public UserService(UserRpcFacade userRpcFacade, MessageRpcFacade messageRpcFacade) {
        this.userRpcFacade = userRpcFacade;
        this.messageRpcFacade = messageRpcFacade;
    }

    /**
     * <p>
     *
     * @return : com.venn.domain.vo.rsp.UserInfoRspVO
     * @Param() : [userInfoReqVO]
     * @author : 杜文锋
     * @date : 2019/11/5
     **/
    public UserInfoRspVO getUserInfo(@RequestBody @Validated UserInfoReqVO userInfoReqVO) {
        log.info("req:{}", userInfoReqVO);
        IResponse<UserInfoDTO> iResponse = userRpcFacade.getUserInfoByUserId(userInfoReqVO.getUserId());
        IResponse<MobileMessageDTO> response = messageRpcFacade.getMobileMessageDTO(123L);
        log.info("message rsp:{}", response);
        if (!iResponse.isSuccess()) {
            throw new WalletException(ErrorCodeMsgEnum.FAIL);
        }
        UserInfoRspVO rspVO = new UserInfoRspVO();
        UserInfoDTO dto = iResponse.getData();
        BeanUtils.copyProperties(dto, rspVO);
        log.info("user rsp:{}", rspVO);
        return rspVO;
    }
}
