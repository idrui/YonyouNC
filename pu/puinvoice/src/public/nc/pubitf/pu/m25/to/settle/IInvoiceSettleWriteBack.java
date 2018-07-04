package nc.pubitf.pu.m25.to.settle;

import nc.vo.pu.m25.entity.InvoiceWriteBackVO;
import nc.vo.pub.BusinessException;

/**
 * 采购发票为内部交易费用结算提供的回写服务
 * 
 * @since 6.0
 * @version 2010-12-27 上午09:50:46
 * @author duy
 */
public interface IInvoiceSettleWriteBack {

  /**
   * 费用发票的回写
   * 
   * @param wbVos 回写参数
   * @throws BusinessException
   */
  public void writeBackForToSettle(InvoiceWriteBackVO[] wbVos)
      throws BusinessException;
}
