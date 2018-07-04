/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-24 ����10:33:17
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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��д��������-��ɢ��������
 * </ul>
 * <p>
 * <p>
 * 
 * @since 6.0
 * @version 2012-11-5 ����02:56:36
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
      // 2011-12-16 tianft �����������۲����е�vo״̬��ʱ���û��
      // paras[i].setVostatus(para.getStatus());
      paras[i].setBsc(UFBoolean.FALSE);
    }

    return paras;
  }

  /**
   * ���෽����д
   * 
   * @see nc.vo.pu.pub.writeback.IWriteBackSource#writeback(java.util.List)
   */
  @Override
  public void writeback(List<RewritePara> rwParas) {
    PublicMoService4PU.rewriteDmo4PuOrder(this.getWritebackPara(rwParas));
  }

}
