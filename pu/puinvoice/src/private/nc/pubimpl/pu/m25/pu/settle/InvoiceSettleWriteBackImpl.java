/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-2 ����10:04:30
 */
package nc.pubimpl.pu.m25.pu.settle;

import nc.bs.pu.m25.writeback.pu.InvoiceSettleWriteBackBP;
import nc.pubitf.pu.m25.pu.settle.IInvoiceSettleWriteBack;
import nc.vo.pu.m25.entity.InvoiceWriteBackVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ���ƱΪ�����ṩ��д����
 * </ul>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-2-2 ����10:04:30
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
