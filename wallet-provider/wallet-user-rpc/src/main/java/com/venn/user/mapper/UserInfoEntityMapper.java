package com.venn.user.mapper;

import com.venn.user.entity.UserInfoEntity;
import com.venn.user.entity.UserInfoEntityExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserInfoEntityMapper {
    int countByExample(UserInfoEntityExample example);

    int deleteByExample(UserInfoEntityExample example);

    int deleteByPrimaryKey(String userId);

    int insert(UserInfoEntity record);

    int insertSelective(UserInfoEntity record);

    List<UserInfoEntity> selectByExample(UserInfoEntityExample example);

    UserInfoEntity selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") UserInfoEntity record, @Param("example") UserInfoEntityExample example);

    int updateByExample(@Param("record") UserInfoEntity record, @Param("example") UserInfoEntityExample example);

    int updateByPrimaryKeySelective(UserInfoEntity record);

    int updateByPrimaryKey(UserInfoEntity record);
}