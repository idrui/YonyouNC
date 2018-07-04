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
import nc.itf.pu.reference.to.M5XServices;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.to.m5x.to.m5x.RewriteArrangedNumPara;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>调拨订单回写实现
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-6-10 下午08:46:47
 */
public class RewiteM5x implements IRewite {

  // @Override
  // public void addSRCClazz(BillRewriter tool, String billtype) {
  // tool.addSRCHeadClazz(billtype, BillHeaderVO.class);
  // tool.addSRCItemClazz(billtype, BillItemVO.class);
  // }

  @Override
  public void writeback(List<RewritePara> rwParas,
      Map<String, PraybillVO> bidVOMap) {
    if (!SysInitGroupQuery.isTOEnabled()) {
      return;
    }

    if (rwParas == null || rwParas.isEmpty()) {
      return;
    }

    // 构造回写调拨订单的参数
    RewriteArrangedNumPara[] paraArray = this.buildParaArray(rwParas);

    try {
      M5XServices.writeback5XFor20(paraArray);
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }

  }

  private RewriteArrangedNumPara[] buildParaArray(List<RewritePara> rwParas) {
    if (rwParas == null || rwParas.isEmpty()) {
      return null;
    }

    RewriteArrangedNumPara[] paras = new RewriteArrangedNumPara[rwParas.size()];
    for (int i = 0, len = rwParas.size(); i < len; i++) {
      RewritePara para = rwParas.get(i);
      String bid = para.getCsrcbid();
      UFDouble num = para.getNnum();
      paras[i] = new RewriteArrangedNumPara(bid, num);
    }
    return paras;
  }
}
