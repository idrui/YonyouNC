package nc.pubitf.pu.m21.ps.m36d1;

import nc.vo.pub.BusinessException;

/**
 * 资金查询接口
 * 
 * @since 6.0
 * @version 2011-3-22 下午05:54:53
 * @author wuxla
 */

public interface IBillIdQueryFor36D1 {

  /**
   * 合同、订单、应付生成付款申请时需要检查其他单据是否已经生成付款申请，如果已经生成付款申请则不许重复申请；
   * 查询时根据单据类型查询其他单据主键数组。
   * 
   * @param ids 单据表头主键数组：付款申请来源单据主键数组
   * @param billtype 单据类型：付款申请来源单据类型
   * @return 其他单据主键数组
   * @throws BusinessException
   */
  String[] queryBillIdsFor36D1(String[] ids, String billtype)
      throws BusinessException;
}
