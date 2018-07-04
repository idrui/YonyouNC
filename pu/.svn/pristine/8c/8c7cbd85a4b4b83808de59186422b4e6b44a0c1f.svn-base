/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-19 下午05:13:59
 */
package nc.pubimpl.pu.m4t.pu.m25;

import nc.bs.pu.m4t.writeback.pu.InitialEstInvoicePullWBBP;
import nc.pubitf.pu.m4t.pu.m25.IInitialEstInvoiceWriteBack;
import nc.pubitf.pu.m4t.pu.m25.IInvoicePullWBPara;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>期初暂估单生成采购发票的回写服务实现类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-19 下午05:13:59
 */
public class InitialEstInvoiceWriteBackImpl implements
    IInitialEstInvoiceWriteBack {

  /**
   * 父类方法重写
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
