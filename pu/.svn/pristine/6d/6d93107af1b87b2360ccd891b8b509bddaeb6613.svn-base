package nc.bs.pu.m21.writeback.source;

import java.util.List;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.itf.pu.reference.et.M5720PUServices;
import nc.pubitf.et.m5720arrange.Rewrite5720ArrangePara;
import nc.vo.pu.pub.writeback.IWriteBackSource;
import nc.vo.pub.lang.UFDouble;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>回写出口合同
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.31
 * @since 6.31
 * @author zhangyhh
 * @time 2013-08-09 下午09:56:42
 */
public class WriteBackET implements IWriteBackSource {

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pu.pub.writeback.IWriteBackSource#writeback(java.util.List)
   */
  @Override
  public void writeback(List<RewritePara> rwParas) {
    M5720PUServices.writeback5720For21(this.getWriteBackPara(rwParas));
  }

  private Rewrite5720ArrangePara[] getWriteBackPara(List<RewritePara> rwParas) {
    String userid = InvocationInfoProxy.getInstance().getUserId();
    Rewrite5720ArrangePara[] paras = new Rewrite5720ArrangePara[rwParas.size()];
    for (int i = 0; i < paras.length; ++i) {
      RewritePara para = rwParas.get(i);
      String bid = para.getCsrcbid();
      UFDouble narrangednum = para.getNnum();
      paras[i] =
          new Rewrite5720ArrangePara(bid, narrangednum, userid,
              POBillType.Order.getCode());
    }
    return paras;
  }

}
