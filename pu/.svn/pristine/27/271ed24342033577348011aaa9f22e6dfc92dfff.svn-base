package nc.vo.pu.m21.rule.vat;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.rule.RelationCalculate;
import nc.vo.pu.m21.rule.vat.OrderVatValueFillRule.IAreaSetter;
import nc.vo.pu.m21.rule.vat.OrderVatValueFillRule.ICountrySetter;
import nc.vo.pu.m21.rule.vat.OrderVatValueFillRule.OrderValueChangeObject;
import nc.vo.pu.m21.rule.vat.setter.area.OrderDestAreaSetter;
import nc.vo.pu.m21.rule.vat.setter.area.OrderOrigAreaSetter;
import nc.vo.pu.m21.rule.vat.setter.country.AbstractOrderCountrySetter.CountryType;
import nc.vo.pu.m21.rule.vat.setter.country.OrderReceiveAddressCountrySetter;
import nc.vo.pu.m21.rule.vat.setter.country.OrderReceiveCountrySetter;
import nc.vo.pu.m21.rule.vat.setter.country.OrderSendAddressCountrySetter;
import nc.vo.pu.m21.rule.vat.setter.country.OrderSendCountrySetter;
import nc.vo.pu.m21.rule.vat.setter.country.OrderTaxCountrySetter;
import nc.vo.pu.pub.util.BillHelper;

import org.apache.commons.lang.ArrayUtils;

/**
 * 订单vat信息补充规则，此规则是一个完美的规则，即按单据扫描，按需求规则依次补全vat信息。
 * 
 * @since 6.0
 * @version 2012-2-29 下午04:27:25
 * @author tianft
 */
public class OrderVatInfoSrcFillRule implements IRule<OrderVO> {

  /**
   * 注册收货国、发货国、报税国
   * 销售直运推采购订单时：
   * 收货国家和报税国家由销售订单对照过来，发货国家需要重新设置
   */
  private List<ICountrySetter> countrySetterList = null;

  /**
   * 是否需要联动计算
   */
  private boolean needCalculate = true;

  /**
   * 是否强制设值vat
   */
  private boolean resetVat = false;

  public OrderVatInfoSrcFillRule() {
    //
  }

  /**
   * @param needCal 是否需要联动计算
   */
  public OrderVatInfoSrcFillRule(boolean needCal) {
    this.needCalculate = needCal;
  }

  public boolean isNeedCalculate() {
    return this.needCalculate;
  }

  public boolean isResetVat() {
    return this.resetVat;
  }

  @Override
  public void process(OrderVO[] vos) {
    BillHelper<OrderVO>[] bills = BillHelper.declareArray(vos);
    // 国家设置器
    List<ICountrySetter> countrySetter = new ArrayList<ICountrySetter>();
    if (this.countrySetterList == null) {
      countrySetter.add(new OrderReceiveCountrySetter(bills));// 收货国
      countrySetter.add(new OrderSendCountrySetter(bills));// 发货国
      countrySetter.add(new OrderTaxCountrySetter(bills));// 报税国
    }
    else {
      countrySetter.addAll(this.countrySetterList);
    }
    countrySetter.add(new OrderSendAddressCountrySetter(
        CountryType.origCountry, bills));// 原产国
    countrySetter.add(new OrderReceiveAddressCountrySetter(
        CountryType.destCountry, bills));// 目的国
    // 地区设置器
    List<IAreaSetter> areaSetter = new ArrayList<IAreaSetter>();
    areaSetter.add(new OrderDestAreaSetter(bills));
    areaSetter.add(new OrderOrigAreaSetter(bills));
    OrderVatValueFillRule vatRule =
        new OrderVatValueFillRule(bills, countrySetter);
    vatRule.setAreaSetterList(areaSetter);
    vatRule.setFromSrcBill(!this.resetVat);
    vatRule.prepare();
    vatRule.process();
    if (!this.isNeedCalculate()) {
      return;
    }
    OrderValueChangeObject[] valueChangeObject = vatRule.getValueChangeObject();
    if (!ArrayUtils.isEmpty(valueChangeObject)) {
      RelationCalculate calculate = new RelationCalculate();
      for (OrderValueChangeObject ovco : valueChangeObject) {
        OrderVO calVo = new OrderVO();
        for (int i = 0; i < bills.length; i++) {
          OrderItemVO item = vos[ovco.getBillIndex()].getBVO()[ovco.getRow()];
          calVo.setHVO(vos[ovco.getBillIndex()].getHVO());
          calVo.setBVO(new OrderItemVO[] {
            item
          });
          calculate.calculate(calVo, ovco.getChangeKey());
        }

      }
    }

  }

  /**
   * 注册收货国、发货国、报税国
   * 销售直运推采购订单时：
   * 收货国家和报税国家由销售订单对照过来，发货国家需要重新设置
   */
  public void setCountrySetterList(List<ICountrySetter> countrySetterList) {
    this.countrySetterList = countrySetterList;
  }

  public void setNeedCalculate(boolean needCalculate) {
    this.needCalculate = needCalculate;
  }

  public void setResetVat(boolean resetVat) {
    this.resetVat = resetVat;
  }

}
