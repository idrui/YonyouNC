package nc.vo.pu.m21.vochange;

import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.rule.AutoSwitchPUAssistUnitRule;
import nc.vo.pu.m21.rule.CurrencyAndExchangerate;
import nc.vo.pu.m21.rule.NumValueSetter;
import nc.vo.pu.m21.rule.OrganizationDefaultValue;
import nc.vo.pu.m21.rule.RelationCalculate;
import nc.vo.pu.m21.rule.margin.OrderMarginRule;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.scmpub.res.billtype.TOBillType;

/**
 * @since 6.0
 * @version 2011-6-28 下午04:38:32
 * @author wuxla
 */

public class ChangeVOAdjust5XTo21 extends AbstractOrderChangeVOAdjust {
  @Override
  protected void fillInformation(BillHelper<OrderVO> helper, int[] rows) {
    // 补充组织相关的信息
    new OrganizationDefaultValue(helper).setDefaultOrganizationValue(rows);
    // 补充本位币和汇率的相关信息
    new CurrencyAndExchangerate(helper).setCurrencyAndExchangeRate(rows);
    // 设置数量
    new NumValueSetter(helper).setNastnum(rows);

    // 补充订单类型
    // 调拨补货安排，打开订单界面，不用处理
    // new TrantypeValue(helper).setTrantypeValue();
  }

  @Override
  protected void fillOtherInfo(OrderVO[] vos, AggregatedValueObject[] srcVOs) {
    AutoSwitchPUAssistUnitRule unit = new AutoSwitchPUAssistUnitRule();
    unit.process(vos);
  }

  @Override
  protected void processMargin(OrderVO[] vos, AggregatedValueObject[] srcVos) {
    new OrderMarginRule(TOBillType.TransOrder.getCode(), srcVos).process(vos);
  }

  @Override
  protected void relationCalculate(RelationCalculate cal, OrderVO vo) {
    return;
  }

  @Override
  protected void setOrderPrice(OrderVO[] vos) {
    this.setPriceByParaPO16(vos);
  }
}
