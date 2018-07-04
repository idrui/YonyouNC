package nc.ui.pu.m21.editor.card.afteredit.body;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.ui.pu.m21.editor.card.afteredit.PriceQuoterUtil;
import nc.ui.pu.m21.rule.CurrencyRelated;
import nc.ui.pu.m21.util.OrderCalculatorUtils;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.rule.vat.OrderVatValueFillRule;
import nc.vo.pu.m21.rule.vat.OrderVatValueFillRule.ICountrySetter;
import nc.vo.pu.m21.rule.vat.setter.country.AbstractOrderCountrySetter.CountryType;
import nc.vo.pu.m21.rule.vat.setter.country.OrderFinanceOrgCountrySetter;
import nc.vo.pu.pub.enumeration.PriceParam;

public class SettleFinanceOrg implements ICardBodyAfterEditEventListener {
  private Map<String, IPURemoteCallCombinator> remoteCaller =
      new HashMap<String, IPURemoteCallCombinator>();

  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    // 注册远程调用
    this.registerRomoteCall(event);
    int row = event.getRow();
    CardEditorHelper card = new CardEditorHelper(event.getBillCardPanel());
    // 补充本位币和汇率的相关信息，并设置可编辑性
    new CurrencyRelated(event.getBillCardPanel())
        .setCurrencyAndExchangeRate(new int[] {
          row
        });

    // 同步应付组织
    this.asyncApfinanceorg(card, row);
    // 处理国家相关
    this.processCountryCall(event);
    // mengjian 根据参数PO16自动询价条件自动询价
    this.setDefaultPrice(event);
  }

  /**
   * 方法功能描述：同步应付组织
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-7-30 上午08:43:12
   */
  private void asyncApfinanceorg(CardEditorHelper card, int row) {
    String psfinanceorg =
        (String) card.getBodyValue(row, OrderItemVO.PK_PSFINANCEORG);
    String psfinanceorg_v =
        (String) card.getBodyValue(row, OrderItemVO.PK_PSFINANCEORG_V);
    card.setBodyValue(row, OrderItemVO.PK_APFINANCEORG, psfinanceorg);
    card.setBodyValue(row, OrderItemVO.PK_APFINANCEORG_V, psfinanceorg_v);
  }

  private void processCountryCall(CardBodyAfterEditEvent event) {
    OrderVatValueFillRule vatrule =
        (OrderVatValueFillRule) this.remoteCaller
            .get(OrderFinanceOrgCountrySetter.class.getName());
    vatrule.process();
    OrderCalculatorUtils.calculate(event.getBillCardPanel(),
        vatrule.getValueChangeObject());
  }

  private void registerCountryCall(CardBodyAfterEditEvent event) {
    CardEditorHelper card = new CardEditorHelper(event.getBillCardPanel());
    List<ICountrySetter> countryList = new ArrayList<ICountrySetter>();
    int[] rows = new int[] {
      event.getRow()
    };
    // 收货国
    OrderFinanceOrgCountrySetter reSetter =
        new OrderFinanceOrgCountrySetter(CountryType.receiveCountry, card, rows);
    reSetter.setFromSourcebill(false);
    countryList.add(reSetter);
    // 报税国
    OrderFinanceOrgCountrySetter taxSetter =
        new OrderFinanceOrgCountrySetter(CountryType.taxCountry, card, rows);
    taxSetter.setFromSourcebill(false);
    countryList.add(taxSetter);
    OrderVatValueFillRule vatrule =
        new OrderVatValueFillRule(card, rows, countryList);
    vatrule.prepare();
    this.remoteCaller
        .put(OrderFinanceOrgCountrySetter.class.getName(), vatrule);
  }

  /**
   * 注册远程调用
   * 
   * @param event
   */
  private void registerRomoteCall(CardBodyAfterEditEvent event) {
    // 财务组织设置收货国,报税国
    this.registerCountryCall(event);
  }

  /**
   * 根据参数PO16自动询价条件自动询价
   * mengjian
   * 
   * @param event
   */
  @SuppressWarnings("restriction")
  private void setDefaultPrice(CardBodyAfterEditEvent event) {
    PriceQuoterUtil priceQuoterUtil = new PriceQuoterUtil();
    priceQuoterUtil.setDefaultPrice(event.getBillCardPanel(),
        PriceParam.Psfinanceorg, new int[] {
          event.getRow()
        });
  }

}
