package nc.bs.pu.it;

import nc.bs.pu.m27.match.rule.AutoMatchFeeDistributeRule;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.StockSettleVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>自动结算一次分摊规则（后台使用for进出口）
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.31
 * @since 6.31
 * @author liangchen1
 * @time 2013-11-11 上午08:44:10
 */

public class AutoMatchFeeDistributeRuleForIT extends AutoMatchFeeDistributeRule {

  public AutoMatchFeeDistributeRuleForIT(StockSettleVO[] ssVos) {
    super(ssVos);
  }

  @Override
  protected void distFeeDiscount(SettleBillVO vo) {
    FirstDistributeUtilForIT util =
        new FirstDistributeUtilForIT(vo, this.ssVos);
    // 进行分摊,结果会同时记录到StockSettleVO上
    util.distribute();
  }

}
