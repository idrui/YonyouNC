package nc.bs.pu.m25.writeback.source;

import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.pubitf.ic.m47.m25.IParameter47For25;
import nc.pubitf.ic.m47.m25.IRewrite47For25;
import nc.vo.pu.m25.env.InvoiceUIToBSEnv;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 采购发票回写委托加工入库单
 * 
 * @since 6.0
 * @version 2010-11-17 下午03:06:47
 * @author tianft
 */
public class WriteBack47 implements IInvoiceWriteBackSource {

  // 委托加工入库单的回写服务
  private IRewrite47For25 writeBackSrv = null;

  @Override
  public void writeback(List<RewritePara> rwParas) {
    return;
  }

  @Override
  public void writeback(List<RewritePara> rwParas, InvoiceUIToBSEnv env) {
    try {
      this.getWriteBackService().rewrite47AccInNumFor25(
          this.createWriteBackVO(rwParas));
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 构造回写参数
   * 
   * @param rwParas
   * @return IParameter47For25[]-委托加工入库单的回写服务参数
   */
  private IParameter47For25[] createWriteBackVO(List<RewritePara> rwParas) {
    IParameter47For25[] wbVos = new IParameter47For25[rwParas.size()];
    for (int i = 0; i < rwParas.size(); ++i) {
      final RewritePara para = rwParas.get(i);
      wbVos[i] = new IParameter47For25() {
        @Override
        public String getBid() {
          return para.getCsrcbid();
        }

        @Override
        public String getHid() {
          return para.getCsrcid();
        }

        @Override
        public UFDouble getNinvoicenum() {
          return para.getNnum();
        }

      };
    }
    return wbVos;
  }

  /**
   * 委托加工入库单的回写服务
   * 
   * @return 委托加工入库单的回写服务
   */
  private IRewrite47For25 getWriteBackService() {
    if (this.writeBackSrv == null) {
      this.writeBackSrv = NCLocator.getInstance().lookup(IRewrite47For25.class);
    }
    return this.writeBackSrv;
  }

}
