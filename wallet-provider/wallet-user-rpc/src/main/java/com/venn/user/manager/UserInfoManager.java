package com.venn.user.manager;

import com.venn.user.entity.UserInfoEntity;
import com.venn.user.mapper.UserInfoEntityMapper;
import org.springframework.stereotype.Component;

/**
 * @author 杜文锋
 * @version 1.0.0
 * @date 2019/10/30 14:51
 */
@Component
public class UserInfoManager {

    private final UserInfoEntityMapper userInfoEntityMapper;

    public UserInfoManager(UserInfoEntityMapper userInfoEntityMapper) {
        this.userInfoEntityMapper = userInfoEntityMapper;
    }

    public UserInfoEntity getUserInfoByUserId(String userId){
        return userInfoEntityMapper.selectByPrimaryKey(userId);
    }
}
