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
import nc.vo.mmpac.dmo.param.DmoRewriteParaForTurn;
import nc.vo.pu.pub.writeback.IWriteBackSource;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>回写生产制造-离散生产订单
 * </ul>
 * <p>
 * <p>
 * 
 * @since 6.0
 * @version 2012-11-5 下午02:56:36
 * @author liuyand
 */
public class WriteBack55C2 implements IWriteBackSource {

  public DmoRewriteParaForTurn[] getWritebackPara(List<RewritePara> rwParas) {
    DmoRewriteParaForTurn[] paras = new DmoRewriteParaForTurn[rwParas.size()];

    for (int i = 0; i < paras.length; ++i) {
      RewritePara para = rwParas.get(i);
      paras[i] = new DmoRewriteParaForTurn();
      paras[i].setPk_dmo(para.getCsrcid());
      paras[i].setChangedNum(para.getNnum());
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
    PublicMoService4PU.rewriteDmo4PuOrder(this.getWritebackPara(rwParas));
  }

}
