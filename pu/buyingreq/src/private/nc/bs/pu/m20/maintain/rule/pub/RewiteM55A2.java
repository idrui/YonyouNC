/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-10 下午08:46:47
 */
package nc.bs.pu.m20.maintain.rule.pub;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.itf.scmpub.reference.mmpac.PublicMoService4PU;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.mmpac.pmo.parameter.PMORewriteParaVO;
import nc.vo.pu.m20.entity.PraybillVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>生产制造-生产订单回写实现
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-6-10 下午08:46:47
 */
public class RewiteM55A2 implements IRewite, Serializable {

  private static final long serialVersionUID = 4922153786769945174L;

  // @Override
  // public void addSRCClazz(BillRewriter tool, String billtype) {
  // tool.addSRCHeadClazz(billtype, MOHeadVO.class);
  // }

  @Override
  public void writeback(List<RewritePara> rwParas,
      Map<String, PraybillVO> bidVOMap) {
    if (!SysInitGroupQuery.isMMEnabled()) {
      return;
    }

    if (rwParas == null || rwParas.isEmpty()) {
      return;
    }

    // 构造回写生产订单的主数量
    PMORewriteParaVO[] paras = this.buildParaArray(rwParas, bidVOMap);
    PublicMoService4PU.rewrite4PrayBill(paras);
  }

  // public void writeback(List<RewritePara> rwParas, String billtype,
  // UFBoolean bsc) {
  //
  // if (!SysInitGroupQuery.isMMEnabled()) {
  // return;
  // }
  //
  // if (rwParas == null || rwParas.isEmpty()) {
  // return;
  // }
  //
  // // 构造回写生产订单的主数量
  // MoRewriteParaVO[] paras = this.buildParaArray(rwParas, bsc);
  // PublicMoService4PU.rewrite4PrayBill(paras);
  // }

  private PMORewriteParaVO[] buildParaArray(List<RewritePara> rwParas,
      Map<String, PraybillVO> bidVOMap) {
    if (rwParas == null || rwParas.isEmpty()) {
      return null;
    }

    PMORewriteParaVO[] paraArray = new PMORewriteParaVO[rwParas.size()];
    for (int i = 0, len = rwParas.size(); i < len; i++) {
      PMORewriteParaVO para = new PMORewriteParaVO();
      para.setBsc(bidVOMap.get(rwParas.get(i).getCbill_bid()).getHVO()
          .getBsctype());
      para.setCpmohid(rwParas.get(i).getCsrcid());
      para.setCpmobid(rwParas.get(i).getCsrcbid());
      para.setBillTs(rwParas.get(i).getSrcTS());
      para.setDelNum(rwParas.get(i).getNnum());
      // 2011-12-16 tianft 生产制造讨论参数中的vo状态、时间戳没用
      // para.setVostatus(rwParas.get(i).getStatus());
      paraArray[i] = para;
      // new NumRewritePara(rwParas.get(i).getNnum(), rwParas.get(i)
      // .getCsrcid());
    }
    return paraArray;
  }

}
