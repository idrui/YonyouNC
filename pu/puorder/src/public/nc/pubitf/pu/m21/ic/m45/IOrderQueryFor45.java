package nc.pubitf.pu.m21.ic.m45;

import java.util.Map;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

public interface IOrderQueryFor45 {

  /**
   * 根据订单表头ID查询可入库的订单
   * 
   * @param orderHIDs
   * @return
   * @throws BusinessException
   */
  OrderVO[] queryFor45(String[] orderHIDs) throws BusinessException;

  OrderVO[] queryFor45Pull(IQueryScheme queryScheme) throws BusinessException;

  OrderVO[] queryFor45Push(IQueryScheme queryScheme) throws BusinessException;

  OrderVO[] queryFor45Return(IQueryScheme queryScheme) throws BusinessException;

  Map<String, UFBoolean> queryReserveInfoFor45(String[] bids)
      throws BusinessException;
}
