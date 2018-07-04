package nc.bs.pu.m23.maintain.rule.insert;

import java.util.List;
import java.util.Map;

import nc.bs.pu.m23.writeback.source.AbstractWrite61;
import nc.impl.pubapp.bill.rewrite.BillRewriter;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.env.ArrivalUIToBSEnv;
import nc.vo.pu.m23.utils.ArrivePublicUtil;
import nc.vo.scmpub.res.billtype.SCBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 修改保存会用到此规则，本类主要完成以下功能：
 * 回写委外订单的累计到货数量、途耗数量
 * @scene
 * 到货单修改保存
 * @param
 * env
 *
 * @since 6.3
 * @version 2010-1-14 下午03:42:46
 * @author hanbin
 */

public class Write61WhenInsertRule extends AbstractWrite61 implements
    IRule<ArriveVO> {
  public Write61WhenInsertRule(ArrivalUIToBSEnv env) {
    super(env);
  }

  @Override
  public void process(ArriveVO[] vos) {

    if (!SysInitGroupQuery.isSCEnabled()) {
      return;
    }

    if (ArrayUtils.isEmpty(vos) || !ArrivePublicUtil.isArriveFromSC(vos)) {
      return;
    }

    BillRewriter tool = this.getBillRewriter(this.getItemKeyMapping());
    Map<String, List<RewritePara>> rwParaMap = tool.splitForInsert(vos);
    List<RewritePara> paras = rwParaMap.get(SCBillType.Order.getCode());

    // 回写累计到货数量、回写累计途耗数量
    this.writeback(paras, vos);
  }

}
