package nc.vo.pu.m21.vochange;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.pu.m21.reference.so.CoopVOChangeRule;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.rule.AutoSwitchPUAssistUnitRule;
import nc.vo.pu.m21.rule.CoopClearInfo;
import nc.vo.pu.m21.rule.CoopCurrencyValue;
import nc.vo.pu.m21.rule.CoopMarginRule;
import nc.vo.pu.m21.rule.CoopNumCalcRule;
import nc.vo.pu.m21.rule.CoopOrganizationValue;
import nc.vo.pu.m21.rule.CoopSupplierRule;
import nc.vo.pu.m21.rule.HeadBreturnRule;
import nc.vo.pu.m21.rule.OrganizationDefaultValue;
import nc.vo.pu.m21.rule.RowNoRule;
import nc.vo.pu.m21.rule.margin.OrderMarginRule;
import nc.vo.pu.m21.rule.vat.OrderVatInfoSrcFillRule;
import nc.vo.pu.m21.rule.vat.OrderVatValueFillRule.ICountrySetter;
import nc.vo.pu.m21.rule.vat.setter.country.OrderTaxCountrySetter;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.scmpub.res.billtype.SOBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.0
 * @version 2011-6-22 ����12:02:21
 * @author wuxla
 */

public class ChangeVOAdjust30CoopTo21 extends AbstractOrderChangeVOAdjust {

	/**
	 * ��֯���ã�����Э��������֯����󣬲�����֯�Ȳ�Ӧ�ø��ǵ������ڲ���δѰ������֯�����������ĵȡ�
	 * 
	 * @param vos
	 */
	private void setOrgValue(OrderVO[] vos) {
		for (OrderVO vo : vos) {
			OrderItemVO[] items = vo.getBVO();
			int[] rows = new int[items.length];
			for (int i = 0; i < items.length; i++) {
				rows[i] = i;
			}
			BillHelper<OrderVO> helper = new BillHelper<OrderVO>(vo);
			OrganizationDefaultValue orgValue = new OrganizationDefaultValue(helper);
			orgValue.setClear(false);
			orgValue.setDefaultOrganizationValue(rows);
		}
	}

  @Override
  protected String[] getNumStrs(){
    return new String[]{"nnum", "nastnum", "nqtunitnum"};
  }
	
	@Override
	protected OrderVO[] fillInformation(OrderVO[] destVOs,
			AggregatedValueObject[] srcVOs) {
		if (ArrayUtils.isEmpty(destVOs) || ArrayUtils.isEmpty(srcVOs)) {
			return null;
		}
    List<IRule<OrderVO>> beforeRule = new ArrayList<>();
    this.addBeforeRule(beforeRule, srcVOs);
    List<IRule<OrderVO>> afterRule = new ArrayList<>();
    this.addAfterRule(afterRule, srcVOs);
    
    for(IRule<OrderVO> rule : beforeRule){
      rule.process(destVOs);
    }
    // ���ڲ���δѰ������֯�����������ĵ�
    this.setOrgValue(destVOs);
    
    for(IRule<OrderVO> rule : afterRule){
      rule.process(destVOs);
    }

		this.checkVisible(destVOs);
		return destVOs;
	}

	private void addAfterRule(List<IRule<OrderVO>> afterRule,
	    AggregatedValueObject[] srcVOs) {
	  afterRule.add(new HeadBreturnRule());
	  afterRule.add(new RowNoRule());
	  afterRule.add(new CoopCurrencyValue());
	  afterRule.add(new CoopNumCalcRule());
	  afterRule.add(new AutoSwitchPUAssistUnitRule());
	  afterRule.add(new CoopMarginRule(srcVOs));
  }

  private void addBeforeRule(List<IRule<OrderVO>> beforeRule,
      AggregatedValueObject[] srcVOs) {
    beforeRule.add(new CoopClearInfo());
	  beforeRule.add(new CoopClearInfo());
	  beforeRule.add(new CoopVOChangeRule(srcVOs));
	  beforeRule.add(new CoopOrganizationValue());
	  beforeRule.add(new CoopSupplierRule());
  }

  @Override
	protected void fillVatInfo(OrderVO[] vos) {
		BillHelper<OrderVO>[] bills = BillHelper.declareArray(vos);
		List<ICountrySetter> countrySetter = new ArrayList<ICountrySetter>();
		// �ջ����Һͱ�˰���������۶������չ���������������Ҫ��������
		countrySetter.add(new OrderTaxCountrySetter(bills));// ��˰��
		OrderVatInfoSrcFillRule vatRule = new OrderVatInfoSrcFillRule();
		vatRule.setCountrySetterList(countrySetter);
		vatRule.setResetVat(true);
		vatRule.process(vos);
	}

	@Override
	protected void processMargin(OrderVO[] vos, AggregatedValueObject[] srcVos) {
		new OrderMarginRule(SOBillType.Order.getCode(), srcVos).process(vos);
	}
	// ��Эͬ���۶���ʱ������ѯ��
	// @Override
	// protected void setOrderPrice(OrderVO[] vos) {
	// this.setPriceByParaPO16(vos);
	// }
}
