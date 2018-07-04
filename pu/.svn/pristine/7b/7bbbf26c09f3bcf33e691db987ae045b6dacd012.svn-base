package nc.vo.pu.m21.vochange;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.m21transtype.IPoTransTypeQuery;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.pub.DirectUtil;
import nc.vo.pu.m21.rule.AutoSwitchPUAssistUnitRule;
import nc.vo.pu.m21.rule.CurrencyAndExchangerate;
import nc.vo.pu.m21.rule.DefaultTaxRateSetter;
import nc.vo.pu.m21.rule.DirectOrderClearRule;
import nc.vo.pu.m21.rule.NumValueSetter;
import nc.vo.pu.m21.rule.OrganizationDefaultValue;
import nc.vo.pu.m21.rule.RelationCalculate;
import nc.vo.pu.m21.rule.margin.OrderMarginRule;
import nc.vo.pu.m21.rule.vat.OrderVatInfoSrcFillRule;
import nc.vo.pu.m21.rule.vat.OrderVatValueFillRule.ICountrySetter;
import nc.vo.pu.m21.rule.vat.setter.country.OrderSendCountrySetter;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.SOBillType;

/**
 * @since 6.0
 * @version 2011-6-22 上午11:40:20
 * @author wuxla
 */

public class ChangeVOAdjust30To21 extends AbstractOrderChangeVOAdjust {

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
  protected void fillInformation(BillHelper<OrderVO> helper, int[] rows) {
    boolean isdirect = DirectUtil.isDirect(helper, 0);

    // 补充组织相关的信息,上游过来财务组织、应付组织、库存组织等对过来。这里需要处理的是利润中心的重新查找。
    OrganizationDefaultValue orgValue = new OrganizationDefaultValue(helper);
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
      this.fillStordocForDirect(helper);
    }
    // else {
    // // 补货和拉单都是界面，不用处理了。
    // new TrantypeValue(helper).setTrantypeValue();
    // }
    // 补充默认值税率
    new DefaultTaxRateSetter(helper).setDefaultTaxtRate(rows);
  }

  @Override
  protected void fillOtherInfo(OrderVO[] vos, AggregatedValueObject[] srcVOs) {
    new DirectOrderClearRule().process(vos);
    new AutoSwitchPUAssistUnitRule().process(vos);
  }
  
  @Override
  protected String[] getNumStrs(){
    return new String[]{"nnum", "nastnum", "nqtunitnum"};
  }

  @Override
  protected void fillVatInfo(OrderVO[] vos) {
    BillHelper<OrderVO>[] bills = BillHelper.declareArray(vos);
    List<ICountrySetter> countrySetter = new ArrayList<ICountrySetter>();
    // 收货国家和报税国家由销售订单对照过来，发货国家需要重新设置
    countrySetter.add(new OrderSendCountrySetter(bills));// 发货国
    OrderVatInfoSrcFillRule vatRule = new OrderVatInfoSrcFillRule();
    vatRule.setCountrySetterList(countrySetter);
    vatRule.setResetVat(true);
    vatRule.process(vos);
  }

  @Override
  protected void processMargin(OrderVO[] vos, AggregatedValueObject[] srcVos) {
    new OrderMarginRule(SOBillType.Order.getCode(), srcVos).process(vos);
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
