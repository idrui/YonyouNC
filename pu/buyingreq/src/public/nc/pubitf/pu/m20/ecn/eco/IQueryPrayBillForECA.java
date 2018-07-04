package nc.pubitf.pu.m20.ecn.eco;

import nc.pubitf.mmf.busi.eco.IBill4ECAResult;
import nc.pubitf.mmf.busi.eco.IECAItemPara;
import nc.vo.pub.BusinessException;

/**
 * 离散制造工程变更分析时，查询请购单信息接口
 * 
 * @since 6.3.1
 * @version 2013-8-13 下午03:41:50
 * @author fanly3
 */
public interface IQueryPrayBillForECA {
  /**
   * 根据指定库存组织和物料辅助属性组合查询请购单
   * 
   * @param paras 参数列表
   * @param temptable 查询结果临时表信息
   * @throws BusinessException 业务异常
   */
  void qryECAResult(IECAItemPara[] paras, IBill4ECAResult temptable)
      throws BusinessException;
}
