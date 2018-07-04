package nc.pubitf.pu.m21.pu.m25;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;

public interface IOrderQueryFor25 {
  /**
   * 得到当前集团中非供应商寄存的交易类型的查询QS<br>
   * 采购发票结算时，不能查询出这类交易的订单
   * 
   * @param pk_group
   * @return 得到当前集团中非供应商寄存的交易类型的查询QS（select vtrantypecode from ...)
   * @throws BusinessException
   */
  IQueryScheme getNonVMITrantypeQS(String pk_group) throws BusinessException;

  OrderVO[] queryFor25(IQueryScheme queryScheme) throws BusinessException;
}
