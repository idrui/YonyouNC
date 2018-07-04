package nc.bs.pu.m20.maintain.rule.pub;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.vo.pu.m20.entity.PraybillVO;

/**
 * 资产工单并发控制,没有真正回写
 * 
 * @since 6.0
 * @version 2011-11-8 下午01:56:28
 * @author wangljc
 */
public class Rewite4B36 implements IRewite, Serializable {

  private static final long serialVersionUID = 4922153786769945174L;

  // @Override
  // public void addSRCClazz(BillRewriter tool, String billtype) {
  // tool.addSRCHeadClazz(billtype, WorkOrderHeadVO.class);
  // tool.addSRCItemClazz(billtype, WOPlanInVVO.class);
  // }

  @Override
  public void writeback(List<RewritePara> rwParas,
      Map<String, PraybillVO> bidVOMap) {
    return;
  }

}
