package nc.bs.pu.m20.maintain.rule.pub;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.itf.scmpub.reference.mmpac.PublicMoService4PU;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.mmpac.dmo.param.DmoRewriteParaForTurn;
import nc.vo.pu.m20.entity.PraybillVO;

/**
 * ��ɢ����������дʵ����
 * 
 * @since 6.3
 * @version 2012-10-31 ����01:18:27
 * @author fanly3
 */
public class RewiteM55C2 implements IRewite, Serializable {
  private static final long serialVersionUID = -305491237070306221L;

  @Override
  public void writeback(List<RewritePara> rwParas,
      Map<String, PraybillVO> bidVOMap) {
    if (!SysInitGroupQuery.isMMEnabled()) {
      return;
    }

    if (rwParas == null || rwParas.isEmpty()) {
      return;
    }

    // �����д��ɢ���������Ļ�д��������
    DmoRewriteParaForTurn[] paras = this.buildParaArray(rwParas, bidVOMap);
    PublicMoService4PU.rewriteDmo4PrayOrder(paras);
  }

  private DmoRewriteParaForTurn[] buildParaArray(List<RewritePara> rwParas,
      Map<String, PraybillVO> bidVOMap) {
    if (rwParas == null || rwParas.isEmpty()) {
      return null;
    }

    DmoRewriteParaForTurn[] paraArray =
        new DmoRewriteParaForTurn[rwParas.size()];
    for (int i = 0, len = rwParas.size(); i < len; i++) {
      DmoRewriteParaForTurn para = new DmoRewriteParaForTurn();
      // �Ƿ�ί��
      para.setBsc(bidVOMap.get(rwParas.get(i).getCbill_bid()).getHVO()
          .getBsctype());
      // ��ɢ������������
      para.setPk_dmo(rwParas.get(i).getCsrcid());
      // ת�ɹ�������
      para.setChangedNum(rwParas.get(i).getNnum());
      // ʱ���
      para.setBillTs(rwParas.get(i).getSrcTS());
      // ״̬
      para.setBillStatus(rwParas.get(i).getStatus());

      paraArray[i] = para;

    }
    return paraArray;
  }
}
