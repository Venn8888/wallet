package com.venn.user.service;

import com.venn.domain.dto.UserInfoDTO;

/**
 * @author 杜文锋
 * @version 1.0.0
 * @date 2019/10/30 14:55
 */
public interface UserInfoService {

    UserInfoDTO getUserInfoByUserId(Long userId);
}
