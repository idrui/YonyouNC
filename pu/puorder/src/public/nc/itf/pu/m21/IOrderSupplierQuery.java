package nc.itf.pu.m21;

import nc.vo.pu.m21.query.supplier.SupplierInfo;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>供应商信息查询
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-3-27 下午07:31:06
 */
public interface IOrderSupplierQuery {
  public SupplierInfo querySupplier(String pk_supplier, String pk_purchaseorg)
      throws BusinessException;
}
