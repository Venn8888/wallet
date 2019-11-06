package com.venn.strategy;

import com.venn.domain.dto.ExpectedEntity;
import com.venn.domain.dto.RealDataDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author dwf
 * @date 2019/9/27 22:48
 */

public abstract class AbstractFilterStrategy<R> implements IFilterStrategy<RealDataDTO, ExpectedEntity> {

  protected Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public boolean filter(RealDataDTO real, ExpectedEntity expected) {
    if (expected == null){
      return false;
    }
    if (ifFilterNeed(expected)){
      return doFilter(extractRealData(real),expected);
    }
    return true;
  }

  /**
   * 做实际的过滤
   * @param extractRealData 实际入参
   * @param expected 期待的参数
   * @return 返回true表示数据符合要求
   */
  protected abstract boolean doFilter(R extractRealData, ExpectedEntity expected);

  /**
   * 获取各个策略自己关心的数据
   * @param real 真实数据集合
   * @return 返回实际关心的数据
   */
  protected abstract R extractRealData(RealDataDTO real);

  /**
   * 是否需要做过滤
   * @param expected 期待数据
   * @return true表示需要过滤，false表示不需要过滤
   */
  protected abstract boolean ifFilterNeed(ExpectedEntity expected);

}
