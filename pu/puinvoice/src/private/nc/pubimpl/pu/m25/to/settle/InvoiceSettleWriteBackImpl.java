package nc.pubimpl.pu.m25.to.settle;

import nc.bs.pu.m25.writeback.pu.InvoiceSettleWriteBackBP;
import nc.pubitf.pu.m25.to.settle.IInvoiceSettleWriteBack;
import nc.vo.pu.m25.entity.InvoiceWriteBackVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 采购发票为内部交易费用结算提供的回写服务的实现类
 * 
 * @since 6.0
 * @version 2010-12-27 上午09:55:13
 * @author duy
 */
public class InvoiceSettleWriteBackImpl implements IInvoiceSettleWriteBack {

  @Override
  public void writeBackForToSettle(InvoiceWriteBackVO[] wbVos) throws BusinessException {
    try {
      new InvoiceSettleWriteBackBP().writeback(wbVos);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
  }

}
