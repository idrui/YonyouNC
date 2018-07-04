/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-24 上午10:33:17
 */
package nc.bs.pu.m21.writeback.source;

import java.util.List;

import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.itf.scmpub.reference.mmpac.PublicMoService4PU;
import nc.vo.mmpac.pmo.parameter.PMORewriteParaVO;
import nc.vo.pu.pub.writeback.IWriteBackSource;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>回写生产制造-生产订单
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-6-24 上午10:33:17
 */
public class WriteBack55A2 implements IWriteBackSource {

  public PMORewriteParaVO[] getWritebackPara(List<RewritePara> rwParas) {
    PMORewriteParaVO[] paras = new PMORewriteParaVO[rwParas.size()];

    for (int i = 0; i < paras.length; ++i) {
      RewritePara para = rwParas.get(i);
      paras[i] = new PMORewriteParaVO();
      paras[i].setCpmohid(para.getCsrcid());
      paras[i].setCpmobid(para.getCsrcbid());
      paras[i].setDelNum(para.getNnum());
      paras[i].setBillTs(para.getSrcTS());
      // 2011-12-16 tianft 生产制造讨论参数中的vo状态、时间戳没用
      // paras[i].setVostatus(para.getStatus());
      paras[i].setBsc(UFBoolean.FALSE);
    }

    return paras;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pu.pub.writeback.IWriteBackSource#writeback(java.util.List)
   */
  @Override
  public void writeback(List<RewritePara> rwParas) {
    PublicMoService4PU.rewrite4PuOrder(this.getWritebackPara(rwParas));
  }

}
