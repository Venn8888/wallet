package com.venn.strategy.support;

import com.venn.domain.dto.ExpectedEntity;
import com.venn.domain.dto.RealDataDTO;
import com.venn.strategy.AbstractFilterStrategy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author dwf
 * @date 2019/9/27 23:35
 */

@Component
@Order(3)
public class NameFilterStrategy extends AbstractFilterStrategy<String> {

    @Override
    protected boolean doFilter(String realName, ExpectedEntity expected) {
        if (StringUtils.isEmpty(realName)) {
            return false;
        }
        List<String> strings = Arrays.asList(expected.getName().split(","));
        for (String string : strings) {
            if (string.equals(realName)) {
                logger.info("姓名在范围类");
                return true;
            }
        }
        return false;
    }

    @Override
    protected String extractRealData(RealDataDTO real) {
        return real.getName();
    }

    @Override
    protected boolean ifFilterNeed(ExpectedEntity expected) {
        if (!StringUtils.isEmpty(expected.getName())) {
            return true;
        }
        return false;
    }
}
