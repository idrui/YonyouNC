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

import java.util.List;
import java.util.Map;

import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.itf.scmpub.reference.mmpps.PubPoRewriteService;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.mmpps.plo.entity.PloRewriteParamVO;
import nc.vo.pu.m20.entity.PraybillVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>生产制造-计划订单回写实现
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-6-10 下午08:46:47
 */
public class RewiteM55B4 implements IRewite {

  // @Override
  // public void addSRCClazz(BillRewriter tool, String billtype) {
  // tool.addSRCHeadClazz(billtype, PoVO.class);
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

    // 构造回写计划订单的主数量
    PloRewriteParamVO[] paraArray = this.buildParaArray(rwParas, bidVOMap);
    PubPoRewriteService.reWriteNaccpraynumFor20(paraArray);

  }

  private PloRewriteParamVO[] buildParaArray(List<RewritePara> rwParas,
      Map<String, PraybillVO> bidVOMap) {
    if (rwParas == null || rwParas.isEmpty()) {
      return null;
    }

    PloRewriteParamVO[] paraArray = new PloRewriteParamVO[rwParas.size()];
    for (int i = 0, len = rwParas.size(); i < len; i++) {
      PloRewriteParamVO para = new PloRewriteParamVO();
      para.setCpoid(rwParas.get(i).getCsrcid());
      para.setNum(rwParas.get(i).getNnum());
      para.setBsc(bidVOMap.get(rwParas.get(i).getCbill_bid()).getHVO()
          .getBsctype());
      paraArray[i] = para;
    }
    return paraArray;
  }
}
