package com.venn.message.mapper;

import com.venn.message.entity.MobileMessageEntity;
import com.venn.message.entity.MobileMessageEntityExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MobileMessageEntityMapper {
    int countByExample(MobileMessageEntityExample example);

    int deleteByExample(MobileMessageEntityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MobileMessageEntity record);

    int insertSelective(MobileMessageEntity record);

    List<MobileMessageEntity> selectByExample(MobileMessageEntityExample example);

    MobileMessageEntity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MobileMessageEntity record, @Param("example") MobileMessageEntityExample example);

    int updateByExample(@Param("record") MobileMessageEntity record, @Param("example") MobileMessageEntityExample example);

    int updateByPrimaryKeySelective(MobileMessageEntity record);

    int updateByPrimaryKey(MobileMessageEntity record);
}