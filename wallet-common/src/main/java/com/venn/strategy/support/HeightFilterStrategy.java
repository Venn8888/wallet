package com.venn.strategy.support;

import com.venn.domain.dto.ExpectedEntity;
import com.venn.domain.dto.RealDataDTO;
import com.venn.strategy.AbstractFilterStrategy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author dwf
 * @date 2019/9/27 23:30
 */
@Component
@Order(2)
public class HeightFilterStrategy extends AbstractFilterStrategy<Double> {

    @Override
    protected boolean doFilter(Double realHeight, ExpectedEntity expected) {
        if (realHeight == null) {
            return false;
        }
        boolean b = realHeight > expected.getHeight();
        logger.info("是否符合身高：{}", b);
        return b;
    }

    @Override
    protected Double extractRealData(RealDataDTO real) {
        return real.getHeight();
    }

    @Override
    protected boolean ifFilterNeed(ExpectedEntity expected) {
        if (expected.getHeight() != null) {
            return true;
        }
        return false;
    }
}
