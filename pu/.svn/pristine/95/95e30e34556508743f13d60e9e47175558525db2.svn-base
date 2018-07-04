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
 * 离散生产订单回写实现类
 * 
 * @since 6.3
 * @version 2012-10-31 下午01:18:27
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

    // 构造回写离散生产订单的回写参数数组
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
      // 是否委外
      para.setBsc(bidVOMap.get(rwParas.get(i).getCbill_bid()).getHVO()
          .getBsctype());
      // 离散生产订单主键
      para.setPk_dmo(rwParas.get(i).getCsrcid());
      // 转采购主数量
      para.setChangedNum(rwParas.get(i).getNnum());
      // 时间戳
      para.setBillTs(rwParas.get(i).getSrcTS());
      // 状态
      para.setBillStatus(rwParas.get(i).getStatus());

      paraArray[i] = para;

    }
    return paraArray;
  }
}
