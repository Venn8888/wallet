package com.venn.strategy.support;

import com.venn.domain.dto.ExpectedEntity;
import com.venn.domain.dto.RealDataDTO;
import com.venn.strategy.AbstractFilterStrategy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author dwf
 * @date 2019/9/27 23:15
 */

@Component
@Order(1)
public class AgeFilterStrategy extends AbstractFilterStrategy<Long> {

    @Override
    protected boolean doFilter(Long realAge, ExpectedEntity expected) {
        if (realAge == null) {
            return false;
        }
        boolean whetherAge = realAge > expected.getAge();
        logger.info("是否符合年龄：{}", whetherAge);
        return whetherAge;
    }

    @Override
    protected Long extractRealData(RealDataDTO real) {
        return real.getAge();
    }

    @Override
    protected boolean ifFilterNeed(ExpectedEntity expected) {
        if (null == expected.getAge()) {
            return false;
        }
        return true;
    }
}
