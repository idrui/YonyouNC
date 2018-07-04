/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-25 下午06:31:59
 */
package nc.bs.pu.m21.writeback.source;

import java.util.List;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.itf.pu.reference.so.M30SOServices;
import nc.pubitf.so.m30arrange.Rewrite30ArrangePara;
import nc.vo.pu.pub.writeback.IWriteBackSource;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>回写销售订单
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-5-25 下午06:31:59
 */
public class WriteBack30 implements IWriteBackSource {

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pu.pub.writeback.IWriteBackSource#writeback(java.util.List)
   */
  @Override
  public void writeback(List<RewritePara> rwParas) {
    try {
      M30SOServices.writeback30For21(this.getWritebackPara(rwParas));
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private Rewrite30ArrangePara[] getWritebackPara(List<RewritePara> rwParas) {
    Rewrite30ArrangePara[] paras = new Rewrite30ArrangePara[rwParas.size()];
    String userid = InvocationInfoProxy.getInstance().getUserId();
    for (int i = 0; i < paras.length; ++i) {
      RewritePara para = rwParas.get(i);
      String csaleorderbid = para.getCsrcbid();
      UFDouble narrangeponum = para.getNnum();
      String carrangepersonid = userid;
      paras[i] =
          new Rewrite30ArrangePara(csaleorderbid, narrangeponum,
              carrangepersonid, POBillType.Order.getCode());
    }

    return paras;
  }

}
