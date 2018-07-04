package nc.vo.pu.m21.vochange;

import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.pub.OrderVOUtil;
import nc.vo.pu.m21.rule.AutoSwitchPUAssistUnitRule;
import nc.vo.pu.m21.rule.DefaultTaxRateSetter;
import nc.vo.pu.m21.rule.RelationCalculate;
import nc.vo.pu.m21.rule.margin.OrderMarginRule;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.scmpub.res.billtype.ICBillType;

/**
 * @since 6.0
 * @version 2011-6-22 下午12:11:54
 * @author wuxla
 */

public class ChangeVOAdjust49To21 extends AbstractOrderChangeVOAdjust {
  @Override
  protected void fillInformation(BillHelper<OrderVO> helper, int[] rows) {
    super.fillInformation(helper, rows);
    // 借入单到采购是到界面，可以不用处理
    // new TrantypeValue(helper).setTrantypeValue();
    // 补充默认值税率
    new DefaultTaxRateSetter(helper).setDefaultTaxtRate(rows);
  }

  @Override
  protected void fillOtherInfo(OrderVO[] vos, AggregatedValueObject[] srcVOs) {
    AutoSwitchPUAssistUnitRule unit = new AutoSwitchPUAssistUnitRule();
    unit.process(vos);
  }

  @Override
  protected void processMargin(OrderVO[] vos, AggregatedValueObject[] srcVos) {
    new OrderMarginRule(ICBillType.BorrowIn.getCode(), srcVos).process(vos);
  }

  @Override
  protected String[] getNumStrs() {
    return new String[]{"nnum", "nassistnum", "nassistnum"};
  }

  @Override
  protected void relationCalculate(RelationCalculate cal, OrderVO vo) {
    cal.calculate(vo, OrderItemVO.NNUM);
    if (OrderVOUtil.isTaxPrior(vo.getHVO().getPk_org())) {
      cal.calculate(vo, OrderItemVO.NORIGTAXPRICE);
    }
    else {
      cal.calculate(vo, OrderItemVO.NORIGPRICE);
    }
  }

  @Override
  protected void setOrderPrice(OrderVO[] vos) {
    this.setPriceByParaPO16(vos);
  }

}
