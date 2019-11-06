package com.venn.strategy;

/**
 * 过滤策略
 *
 * @author dwf
 * @date 2019/9/27 22:00
 */

public interface IFilterStrategy<R, E> {

  /**
   * 数据过滤
   *
   * @param real 实际请求的数据
   * @param expected 期待的数据
   * @return true表示数据符合要求，可以通过
   */
  boolean filter(R real, E expected);
}
