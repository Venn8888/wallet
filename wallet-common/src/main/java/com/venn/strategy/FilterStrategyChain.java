package com.venn.strategy;

import com.venn.domain.dto.ExpectedEntity;
import com.venn.domain.dto.RealDataDTO;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dwf
 * @date 2019/9/27 22:13
 */

@Component
public class FilterStrategyChain {

  private final List<IFilterStrategy<RealDataDTO, ExpectedEntity>> filterStrategyList;

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  public FilterStrategyChain(List<IFilterStrategy<RealDataDTO, ExpectedEntity>> filterStrategyList) {
    this.filterStrategyList = filterStrategyList;
  }

  public boolean filterChain(RealDataDTO real, ExpectedEntity expected) {
    boolean result = false;
    for (IFilterStrategy<RealDataDTO, ExpectedEntity> filterStrategy : filterStrategyList) {
      result = filterStrategy.filter(real, expected);
      if (!result) {
        return false;
      }
    }
    logger.info("最终的过滤结果为：{}",result);
    return true;
  }
}
