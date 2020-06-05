package com.venn.user.manager;

import com.venn.user.entity.UserDomain;
import com.venn.user.mapper.UserDomainMapper;
import org.springframework.stereotype.Component;

/**
 * @author 杜文锋
 * @version 1.0.0
 * @date 2019/10/30 14:51
 */
@Component
public class UserInfoManager {

    private final UserDomainMapper userInfoEntityMapper;

    public UserInfoManager(UserDomainMapper userInfoEntityMapper) {
        this.userInfoEntityMapper = userInfoEntityMapper;
    }

    public UserDomain getUserInfoByUserId(Long userId){
        return userInfoEntityMapper.selectByPrimaryKey(userId);
    }
}
