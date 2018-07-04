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
 * @version 2011-6-28 ����04:38:32
 * @author wuxla
 */

public class ChangeVOAdjust5XTo21 extends AbstractOrderChangeVOAdjust {
  @Override
  protected void fillInformation(BillHelper<OrderVO> helper, int[] rows) {
    // ������֯��ص���Ϣ
    new OrganizationDefaultValue(helper).setDefaultOrganizationValue(rows);
    // ���䱾λ�Һͻ��ʵ������Ϣ
    new CurrencyAndExchangerate(helper).setCurrencyAndExchangeRate(rows);
    // ��������
    new NumValueSetter(helper).setNastnum(rows);

    // ���䶩������
    // �����������ţ��򿪶������棬���ô���
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
