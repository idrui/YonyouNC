/**
 * $文件说明$
 * 
 * @author zhyhang
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-24 下午08:32:39
 */
package nc.bs.pu.m21.writeback.source;

import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.pubitf.pu.m20.pu.m21.IReWrite20For21;
import nc.vo.pu.m20.entity.writeback.M21WriteBackVO;
import nc.vo.pu.m21.context.OrderContext;
import nc.vo.pu.pub.writeback.IWriteBackSource;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>回写请购单
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhyhang
 * @time 2010-1-24 下午08:32:39
 */
public class WriteBack20 implements IWriteBackSource {

  private OrderContext ctx;

  public WriteBack20(OrderContext ctx) {
    this.ctx = ctx;
  }

  @Override
  public void writeback(List<RewritePara> rwParas) {
    try {
      M21WriteBackVO[] backVOs = this.getWritebackVO(rwParas);
      this.getWriteBackService().backWriteNum(backVOs);
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
  }

  private IReWrite20For21 getWriteBackService() {
    return NCLocator.getInstance().lookup(IReWrite20For21.class);
  }

  private M21WriteBackVO[] getWritebackVO(List<RewritePara> rwParas) {
    M21WriteBackVO[] vos = new M21WriteBackVO[rwParas.size()];
    for (int i = 0; i < vos.length; i++) {
      RewritePara para = rwParas.get(i);
      vos[i] = new M21WriteBackVO();
      vos[i].setUserConfirm(this.ctx.getPrayNumToleranceConfirm()
          .booleanValue());
      vos[i].setDiffNum(para.getNnum());
      vos[i].setPk_downbill_b(para.getCbill_bid());
      vos[i].setPk_praybill(para.getCsrcid());
      vos[i].setPk_praybill_b(para.getCsrcbid());
      if (para.getSrcTS() != null) {
        vos[i].setStrHTS(para.getSrcTS().toString());
      }
      if (para.getSrcbTS() != null) {
        vos[i].setStrBTS(para.getSrcbTS().toString());
      }
    }
    return vos;
  }

}
