/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-22 下午06:25:24
 */
package nc.bs.pu.m21.writeback.source;

import java.util.List;

import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.itf.scmpub.reference.mmpps.PubPoRewriteService;
import nc.vo.mmpps.plo.entity.PloRewriteParamVO;
import nc.vo.pu.pub.writeback.IWriteBackSource;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>回写生产制造-计划订单
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-6-22 下午06:25:24
 */
public class WriteBack55B4 implements IWriteBackSource {

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pu.pub.writeback.IWriteBackSource#writeback(java.util.List)
   */
  @Override
  public void writeback(List<RewritePara> rwParas) {
    PubPoRewriteService.rewrite4PuOrder(this.getWritebackPara(rwParas));
  }

  private PloRewriteParamVO[] getWritebackPara(List<RewritePara> rwParas) {
    PloRewriteParamVO[] paras = new PloRewriteParamVO[rwParas.size()];
    for (int i = 0; i < paras.length; ++i) {
      RewritePara para = rwParas.get(i);
      paras[i] = new PloRewriteParamVO();
      paras[i].setCpoid(para.getCsrcid());
      paras[i].setNum(para.getNnum());
      // paras[i].setVostatus(para.getStatus());
      // paras[i].setBsc(UFBoolean.FALSE);
    }

    return paras;
  }
}
