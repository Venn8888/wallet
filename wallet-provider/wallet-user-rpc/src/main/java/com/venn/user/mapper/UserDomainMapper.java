package com.venn.user.mapper;

import com.venn.user.entity.UserDomain;

public interface UserDomainMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserDomain record);

    int insertSelective(UserDomain record);

    UserDomain selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserDomain record);

    int updateByPrimaryKey(UserDomain record);
}