/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-19 ����05:13:59
 */
package nc.pubimpl.pu.m4t.pu.m25;

import nc.bs.pu.m4t.writeback.pu.InitialEstInvoicePullWBBP;
import nc.pubitf.pu.m4t.pu.m25.IInitialEstInvoiceWriteBack;
import nc.pubitf.pu.m4t.pu.m25.IInvoicePullWBPara;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ڳ��ݹ������ɲɹ���Ʊ�Ļ�д����ʵ����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-19 ����05:13:59
 */
public class InitialEstInvoiceWriteBackImpl implements
    IInitialEstInvoiceWriteBack {

  /**
   * ���෽����д
   * 
   * @see nc.pubitf.pu.m4t.pu.m25.IInitialEstInvoiceWriteBack#writeback(nc.pubitf.pu.m4t.pu.m25.IInvoicePullWBPara[])
   */
  @Override
  public void writeback(IInvoicePullWBPara[] vos) throws BusinessException {
    try {
      new InitialEstInvoicePullWBBP().writeBack(vos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }
}
