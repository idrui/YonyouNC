package nc.itf.pu.pfxx;

import nc.vo.pub.AggregatedValueObject;

/**
 * 外部交换的统一接口，负责新增和更新
 * 
 * @since 6.0
 * @version 2011-4-26 上午09:51:07
 * @author 田锋涛
 */

public interface IPuPfxxSave {
  /**
   * 新增操作
   * 
   * @param vo
   * @return
   */
  AggregatedValueObject insert(AggregatedValueObject vo);

  /**
   * 更新操作
   * 
   * @param vo
   * @return
   */
  AggregatedValueObject update(AggregatedValueObject vo);
}
