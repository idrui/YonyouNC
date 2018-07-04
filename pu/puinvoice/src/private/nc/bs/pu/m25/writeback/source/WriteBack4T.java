/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-5 上午09:41:39
 */
package nc.bs.pu.m25.writeback.source;

import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.pubitf.pu.m4t.pu.m25.IInitialEstInvoiceWriteBack;
import nc.pubitf.pu.m4t.pu.m25.IInvoicePullWBPara;
import nc.vo.pu.m25.env.InvoiceUIToBSEnv;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>回写期初暂估入库单
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-2-5 上午09:41:39
 */
public class WriteBack4T implements IInvoiceWriteBackSource {

  @Override
  public void writeback(List<RewritePara> rwParas) {
    return;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pu.pub.writeback.IWriteBackSource#writeback(java.util.List)
   */
  @Override
  public void writeback(List<RewritePara> rwParas, InvoiceUIToBSEnv env) {
    // 执行真正回写
    try {
      NCLocator.getInstance().lookup(IInitialEstInvoiceWriteBack.class)
          .writeback(this.createWriteBackVO(rwParas));
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private IInvoicePullWBPara[] createWriteBackVO(List<RewritePara> rwParas) {
    IInvoicePullWBPara[] wbVOs = new IInvoicePullWBPara[rwParas.size()];
    for (int i = 0; i < rwParas.size(); ++i) {
      final RewritePara rwPara = rwParas.get(i);
      wbVOs[i] = new IInvoicePullWBPara() {

        @Override
        public String getBID() {
          return rwPara.getCsrcbid();
        }

        @Override
        public UFDouble getDiffNum() {
          return rwPara.getNnum();
        }

        @Override
        public String getHID() {
          return rwPara.getCsrcid();
        }
      };
    }
    return wbVOs;
  }

}
