/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-2 上午10:04:30
 */
package nc.pubimpl.pu.m25.pu.settle;

import nc.bs.pu.m25.writeback.pu.InvoiceSettleWriteBackBP;
import nc.pubitf.pu.m25.pu.settle.IInvoiceSettleWriteBack;
import nc.vo.pu.m25.entity.InvoiceWriteBackVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购发票为结算提供回写服务
 * </ul>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-2-2 上午10:04:30
 */
public class InvoiceSettleWriteBackImpl implements IInvoiceSettleWriteBack {

  @Override
  public void writeBack(InvoiceWriteBackVO[] wbVos) throws BusinessException {
    try {
      new InvoiceSettleWriteBackBP().writeback(wbVos);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
  }

}
