package com.venn.service;

import com.venn.domain.dto.UserInfoDTO;
import com.venn.domain.vo.req.UserInfoReqVO;
import com.venn.domain.vo.rsp.UserInfoRspVO;
import com.venn.user.service.UserInfoService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @author venn
 * @version 1.0.0
 * @date 2019/11/4 16:41
 */
@Component
public class UserService {

    private static final Logger log = LogManager.getLogger(UserService.class);

    @Reference
    private UserInfoService userInfoService;
    /**
     * <p>
     *
     * @return : com.venn.domain.vo.rsp.UserInfoRspVO
     * @Param() : [userInfoReqVO]
     * @author : venn
     * @date : 2019/11/5
     **/
    public UserInfoRspVO getUserInfo(UserInfoReqVO userInfoReqVO) {
        log.info("req:{}", userInfoReqVO);
        UserInfoDTO infoByUserId = userInfoService.getUserInfoByUserId(Long.valueOf(userInfoReqVO.getUserId()));
        UserInfoRspVO userInfoRspVO = new UserInfoRspVO();
        BeanUtils.copyProperties(infoByUserId,userInfoRspVO);
        return userInfoRspVO;
    }
}
