package nc.itf.pu.m21;

import nc.vo.pu.m21.query.supplier.SupplierInfo;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��Ӧ����Ϣ��ѯ
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-3-27 ����07:31:06
 */
public interface IOrderSupplierQuery {
  public SupplierInfo querySupplier(String pk_supplier, String pk_purchaseorg)
      throws BusinessException;
}
