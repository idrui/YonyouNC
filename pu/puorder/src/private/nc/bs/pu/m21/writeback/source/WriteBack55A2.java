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
import nc.vo.mmpac.pmo.parameter.PMORewriteParaVO;
import nc.vo.pu.pub.writeback.IWriteBackSource;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��д��������-��������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-6-24 ����10:33:17
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
    PublicMoService4PU.rewrite4PuOrder(this.getWritebackPara(rwParas));
  }

}
