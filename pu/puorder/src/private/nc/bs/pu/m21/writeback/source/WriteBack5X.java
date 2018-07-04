/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-10 上午10:59:26
 */
package nc.bs.pu.m21.writeback.source;

import java.util.List;

import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.itf.pu.reference.to.M5XServices;
import nc.pubitf.to.m5x.to.m5x.RewriteArrangedNumPara;
import nc.vo.pu.pub.writeback.IWriteBackSource;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>回写调拨订单
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-6-10 上午10:59:26
 */
public class WriteBack5X implements IWriteBackSource {

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pu.pub.writeback.IWriteBackSource#writeback(java.util.List)
   */
  @Override
  public void writeback(List<RewritePara> rwParas) {
    try {
      M5XServices.writeback5XFor21(this.getWritebackPara(rwParas));
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private RewriteArrangedNumPara[] getWritebackPara(List<RewritePara> rwParas) {
    RewriteArrangedNumPara[] paras = new RewriteArrangedNumPara[rwParas.size()];
    for (int i = 0; i < paras.length; ++i) {
      RewritePara para = rwParas.get(i);
      String bid = para.getCsrcbid();
      UFDouble narrangednum = para.getNnum();
      paras[i] = new RewriteArrangedNumPara(bid, narrangednum);
    }

    return paras;
  }

}
