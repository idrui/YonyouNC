package nc.bs.pu.m25.writeback.source;

import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.sc.m61.pu.ISCOrderFor25Ref;
import nc.vo.pu.m25.env.InvoiceUIToBSEnv;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.sc.m61.entity.wrtbck.SCOrdWrtbckVOForRef;

/**
 * 采购发票回写委外订单
 * 
 * @since 6.0
 * @version 2010-11-11 下午08:04:44
 * @author tianft
 */
public class WriteBack61 implements IInvoiceWriteBackSource {

  // 委外订单的回写服务
  private ISCOrderFor25Ref writeBackSrv = null;

  @Override
  public void writeback(List<RewritePara> rwParas) {
    return;
  }

  @Override
  public void writeback(List<RewritePara> rwParas, InvoiceUIToBSEnv env) {
    try {
      if (!SysInitGroupQuery.isSCEnabled()) {
        return;
      }
      this.getWriteBackService().writeback(this.createWriteBackVO(rwParas));
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
   * @return
   */
  private SCOrdWrtbckVOForRef[] createWriteBackVO(List<RewritePara> rwParas) {
    SCOrdWrtbckVOForRef[] wbVos = new SCOrdWrtbckVOForRef[rwParas.size()];
    for (int i = 0; i < rwParas.size(); ++i) {
      wbVos[i] =
          new SCOrdWrtbckVOForRef(rwParas.get(i).getCsrcid(), rwParas.get(i)
              .getCsrcbid(), rwParas.get(i).getNnum(), rwParas.get(i)
              .getNnum2());
    }
    return wbVos;
  }

  /**
   * 委外订单的回写服务
   * 
   * @return
   */
  private ISCOrderFor25Ref getWriteBackService() {

    if (this.writeBackSrv == null) {
      this.writeBackSrv =
          NCLocator.getInstance().lookup(ISCOrderFor25Ref.class);
    }
    return this.writeBackSrv;
  }

}
