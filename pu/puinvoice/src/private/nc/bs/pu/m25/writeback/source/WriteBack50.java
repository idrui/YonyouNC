/**
 * $文件说明$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-2 上午09:12:11
 */
package nc.bs.pu.m25.writeback.source;

import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.pubitf.ic.m50.m25.IParameter50For25;
import nc.pubitf.ic.m50.m25.IRewrite50For25;
import nc.vo.pu.m25.env.InvoiceUIToBSEnv;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购发票回写消耗汇总
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-8-2 上午09:12:11
 */
public class WriteBack50 implements IInvoiceWriteBackSource {

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pu.pub.writeback.IWriteBackSource#writeback(java.util.List)
   */
  @Override
  public void writeback(List<RewritePara> rwParas) {
    return;

  }

  /**
   * 父类方法重写
   * 
   * @see nc.bs.pu.m25.writeback.source.IInvoiceWriteBackSource#writeback(java.util.List,
   *      nc.vo.pu.m25.env.InvoiceUIToBSEnv)
   */
  @Override
  public void writeback(List<RewritePara> rwParas, InvoiceUIToBSEnv env) {
    try {
      this.getWriteBackService().rewrite50InvoiceNumFor25(
          this.createWriteBackVO(rwParas));
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }

  }

  private IParameter50For25[] createWriteBackVO(List<RewritePara> rwParas) {
    IParameter50For25[] wbVos = new IParameter50For25[rwParas.size()];
    for (int i = 0; i < rwParas.size(); ++i) {
      final RewritePara para = rwParas.get(i);
      wbVos[i] = new IParameter50For25() {
        /**
         * 消耗汇总单表头ID
         * 
         * @return
         */
        @Override
        public String getCvmihid() {
          return para.getCsrcid();
        }

        /**
         * 采购发票表体的开票主数量
         * 
         * @return
         */
        @Override
        public UFDouble getNinvoicenum() {
          return para.getNnum();
        }
      };
    }
    return wbVos;
  }

  private IRewrite50For25 getWriteBackService() {
    return NCLocator.getInstance().lookup(IRewrite50For25.class);
  }

}
