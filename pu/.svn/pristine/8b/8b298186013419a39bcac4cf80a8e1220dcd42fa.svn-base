package nc.pubitf.pu.m21.purp.discount;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

public interface IOrderQueryForDiscount {
  /**
   * 根据组织、日期查询采购订单是否引用了折扣规则档案
   * 
   * @param queryvos
   * @return key：折扣规则档案主键，value：如果引用，UFBoolean.TRUE，否则UFBoolean.FALSE
   * @throws BusinessException
   */
  Map<String, UFBoolean> queryRefDiscount(DiscountRefQueryVO[] queryvos)
      throws BusinessException;
}
