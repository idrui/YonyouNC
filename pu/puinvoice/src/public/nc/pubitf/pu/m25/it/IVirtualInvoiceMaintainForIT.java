package nc.pubitf.pu.m25.it;

import nc.vo.ic.m45.entity.PurchaseInVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pub.BusinessException;

public interface IVirtualInvoiceMaintainForIT {

  /**
   * �����������������ݴ���Ľ�����ⵥ��Ϣ�������ⷢƱ�������޷�Ʊ���㡣
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos Ҫ�������ⷢƱ�Ĳɹ���ⵥVO����
   * @return ���ɵ����ⷢƱ
   * @throws BusinessException <p>
   * @since 6.31
   * @author mengjian
   * @time 2013-11-06 ����10:46:59
   */
  InvoiceVO[] genByPurchsIn4IT(PurchaseInVO[] vos) throws BusinessException;

}
