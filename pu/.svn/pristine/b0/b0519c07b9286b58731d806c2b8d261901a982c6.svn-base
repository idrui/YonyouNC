package nc.pubitf.pu.m21.ecn.eco;

import nc.pubitf.mmf.busi.eco.IBill4ECAResult;
import nc.pubitf.mmf.busi.eco.IECAItemPara;
import nc.vo.pub.BusinessException;

/**
 * 离散制造工程变更分析时，查询采购订单信息接口
 * 
 * @since 6.3.1
 * @version 2013-8-15 下午01:49:27
 * @author fanly3
 */
public interface IQueryOrderBillForECA {
  /**
   * 根据指定收货库存组织和物料辅助属性组合查询采购订单
   * 
   * @param paras 参数列表
   * @param temptable 查询结果临时表信息
   * @throws BusinessException 业务异常
   */
  void qryECAResult(IECAItemPara[] paras, IBill4ECAResult temptable)
      throws BusinessException;
}
