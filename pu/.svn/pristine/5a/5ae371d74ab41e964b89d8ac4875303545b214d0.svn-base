package nc.vo.pu.m21.vochange;

import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.m21transtype.IPoTransTypeQuery;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.rule.AutoSwitchPUAssistUnitRule;
import nc.vo.pu.m21.rule.CurrencyAndExchangerate;
import nc.vo.pu.m21.rule.NumValueSetter;
import nc.vo.pu.m21.rule.OrganizationDefaultValue;
import nc.vo.pu.m21.rule.RelationCalculate;
import nc.vo.pu.m21.rule.margin.OrderMarginRule;
import nc.vo.pu.m21.rule.vat.OrderVatInfoSrcFillRule;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.ETBillType;

/**
 * 与出口合同的vo交换前后期处理类
 * 
 * @since 6.31
 * @version 2013-08-07 下午03:56:36
 * @author zhangyhh
 */
public class ChangeVOAdjustET5720To21 extends AbstractOrderChangeVOAdjust {

  private void clearNotDirectTrantype(BillHelper<OrderVO> helper) {
    String vtrantypecode =
        (String) helper.getHeadValue(OrderHeaderVO.VTRANTYPECODE);
    if (vtrantypecode == null) {
      return;
    }

    IPoTransTypeQuery service =
        NCLocator.getInstance().lookup(IPoTransTypeQuery.class);
    Map<String, PoTransTypeVO> potrantypeMap = null;
    try {
      potrantypeMap =
          service.queryAttrByTypes(new String[] {
            vtrantypecode
          }, new String[] {
            PoTransTypeVO.VTRANTYPECODE, PoTransTypeVO.CTRANTYPEID,
            PoTransTypeVO.BDIRECT
          });
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    if (potrantypeMap == null) {
      return;
    }
    PoTransTypeVO potrantyepvo = potrantypeMap.get(vtrantypecode);
    if (potrantyepvo != null
        && UFBoolean.TRUE.equals(potrantyepvo.getBdirect())) {
      return;
    }
    helper.setHeadValue(OrderHeaderVO.CTRANTYPEID, null);
    helper.setHeadValue(OrderHeaderVO.VTRANTYPECODE, null);
  }

  @Override
  protected String[] getNumStrs() {
    return new String[]{"nnum", "nastnum", "nqtunitnum"};
  }
  
  @Override
  protected void fillInformation(BillHelper<OrderVO> helper, int[] rows) {
    boolean isdirect =
        UFBoolean.TRUE.equals((UFBoolean) helper
            .getHeadValue(OrderHeaderVO.BDIRECT));

    // 补充组织相关的信息,上游过来财务组织、应付组织、库存组织等对过来。这里需要处理的是利润中心的重新查找。
    OrganizationDefaultValue orgValue = new OrganizationDefaultValue(helper);
    if(isdirect)
    	orgValue.setClear(false);
    orgValue.setDefaultOrganizationValue(rows);
    // 补充本位币和汇率的相关信息
    new CurrencyAndExchangerate(helper).setCurrencyAndExchangeRate(rows);
    // 设置数量
    new NumValueSetter(helper).setNastnum(rows);

    if (isdirect) {
      // 补充订单类型
      // new TrantypeValue(helper).setDirectTrantypeValue();
      this.clearNotDirectTrantype(helper);
      // 为直运补充直运仓库
      // this.fillStordocForDirect(helper);
    }
  }

  @Override
  protected void fillOtherInfo(OrderVO[] vos, AggregatedValueObject[] srcVOs) {
    // new DirectOrderClearRule().process(vos);
    new AutoSwitchPUAssistUnitRule().process(vos);
  }

  @Override
  protected void fillVatInfo(OrderVO[] vos) {
    OrderVatInfoSrcFillRule vatRule = new OrderVatInfoSrcFillRule();
    vatRule.setResetVat(true);
    vatRule.process(vos);
  }

  @Override
  protected void relationCalculate(RelationCalculate cal, OrderVO vo) {
    return;
  }

  @Override
  protected void fillOrgSuppValue(BillHelper<OrderVO> helper, int[] rows) {
    return;
  }

  @Override
  protected void processMargin(OrderVO[] vos, AggregatedValueObject[] srcVos) {
    new OrderMarginRule(ETBillType.CONTRACT.getCode(), srcVos).process(vos);
  }
}
