package com.venn.message.manager;

import com.venn.message.entity.MobileMessageEntity;
import com.venn.message.mapper.MobileMessageEntityMapper;
import org.springframework.stereotype.Component;

/**
 * @author 杜文锋
 * @version 1.0.0
 * @date 2019/10/31 14:09
 */
@Component
public class MobileMessageManager {

    private final MobileMessageEntityMapper mobileMessageEntityMapper;

    public MobileMessageManager(MobileMessageEntityMapper mobileMessageEntityMapper) {
        this.mobileMessageEntityMapper = mobileMessageEntityMapper;
    }

    public MobileMessageEntity getMobileMessageById(Long id) {
        return mobileMessageEntityMapper.selectByPrimaryKey(id);
    }

    public boolean saveMobileMessage(MobileMessageEntity entity) {
        return 1 == mobileMessageEntityMapper.insertSelective(entity);
    }

    public boolean deleteMobileMessageById(Long id) {
        return 1 == mobileMessageEntityMapper.deleteByPrimaryKey(id);
    }

    public boolean updateMobileMessageById(MobileMessageEntity entity) {
        return 1 == mobileMessageEntityMapper.updateByPrimaryKey(entity);
    }
}
