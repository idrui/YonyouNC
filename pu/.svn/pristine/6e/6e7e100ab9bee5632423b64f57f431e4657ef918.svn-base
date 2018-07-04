/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-3 上午10:58:08
 */
package nc.ui.pu.m25.service;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.m25.pf.IInvoiceQueryForPf;
import nc.ui.uif2.model.IPFAppModelService;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>为流程平台提供的查询服务
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-2-3 上午10:58:08
 */
public class InvoicePFModelService implements IPFAppModelService {

  /**
   * 父类方法重写
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
