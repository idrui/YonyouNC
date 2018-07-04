/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-3 ����10:58:08
 */
package nc.ui.pu.m25.service;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.m25.pf.IInvoiceQueryForPf;
import nc.ui.uif2.model.IPFAppModelService;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>Ϊ����ƽ̨�ṩ�Ĳ�ѯ����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-2-3 ����10:58:08
 */
public class InvoicePFModelService implements IPFAppModelService {

  /**
   * ���෽����д
   * 
   * @see nc.ui.uif2.model.IPFAppModelService#queryBill(java.lang.String,
   *      java.lang.String)
   */
  @Override
  public Object queryBill(String billId, String billType) throws Exception {
    return NCLocator.getInstance().lookup(IInvoiceQueryForPf.class)
        .queryForPf(billId);
  }

}
