package nc.bs.pu.m23.maintain.rule.update;

import java.util.List;
import java.util.Map;

import nc.bs.pu.m23.writeback.source.AbstractWrite21;
import nc.impl.pubapp.bill.rewrite.BillRewriter;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.env.ArrivalUIToBSEnv;
import nc.vo.pu.m23.utils.ArrivePublicUtil;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 回写采购订单的累计到货数量、途耗数量
 * @scene
 * 到货单修改保存
 * @param
 * env
 *
 * @since 6.3
 * @version 2010-1-14 下午03:42:46
 * @author hanbin
 */

public class Write21WhenUpdateRule extends AbstractWrite21 implements
    ICompareRule<ArriveVO> {

  /**
   * @param env
   */
  public Write21WhenUpdateRule(ArrivalUIToBSEnv env) {
    super(env);
  }

  @Override
  public void process(ArriveVO[] vos, ArriveVO[] srcvos) {
    if (ArrayUtils.isEmpty(vos) || ArrivePublicUtil.isArriveFromSC(vos)) {
      return;
    }
    BillRewriter tool = this.getBillRewriter(this.getItemKeyMapping());
    Map<String, List<RewritePara>> rwParaMap = tool.splitForUpdate(vos, srcvos);
    List<RewritePara> paras = rwParaMap.get(POBillType.Order.getCode());

    // 回写累计到货数量、回写累计途耗数量
    this.writeback(paras, vos);
  }

}
