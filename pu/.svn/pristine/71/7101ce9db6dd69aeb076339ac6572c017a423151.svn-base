package nc.ui.pu.m21.editor.card.afteredit.body;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.ui.pu.m21.rule.CurrencyRelated;
import nc.ui.pu.m21.util.OrderCalculatorUtils;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.rule.FlowStockOrgValue;
import nc.vo.pu.m21.rule.OrganizationDefaultValue;
import nc.vo.pu.m21.rule.WarehouseDefaultValue;
import nc.vo.pu.m21.rule.vat.OrderVatValueFillRule;
import nc.vo.pu.m21.rule.vat.OrderVatValueFillRule.ICountrySetter;
import nc.vo.pu.m21.rule.vat.setter.country.AbstractOrderCountrySetter.CountryType;
import nc.vo.pu.m21.rule.vat.setter.country.OrderFinanceOrgCountrySetter;
import nc.vo.pu.m21.rule.vat.setter.country.OrderReceiveStoreOrgCountrySetter;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>收货库存组织编辑后处理类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-3-11 下午05:22:00
 */
public class ArriveStoreOrg implements ICardBodyAfterEditEventListener {
  private Map<String, IPURemoteCallCombinator> remoteCaller =
      new HashMap<String, IPURemoteCallCombinator>();

  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    int row = event.getRow();
    CardEditorHelper card = new CardEditorHelper(event.getBillCardPanel());

    // 清空表体的收货仓库的值
    this.clearReceiveWarehouse(card, row);

    if (card.getBodyValue(row, OrderItemVO.PK_ARRVSTOORG) == null) {
      return;
    }

    // 根据组织业务委托关系找结算财务组织、应付财务组织、利润中心等
    this.defaultOrganization(card, row);

    // 注册远程调用
    this.registerRomoteCall(event);

    // 补充本位币和汇率的相关信息，并设置可编辑性
    new CurrencyRelated(event.getBillCardPanel())
        .setCurrencyAndExchangeRate(new int[] {
          row
        });
    // 处理收货库存组织国家
    this.processCountryCall(event);

  }

  private void clearReceiveWarehouse(CardEditorHelper card, int row) {
    card.setBodyValue(row, OrderItemVO.PK_RECVSTORDOC, null);
    WarehouseDefaultValue warehouse = new WarehouseDefaultValue(card);
    warehouse.setWarehouseDefaultValue(row);
  }

  private void defaultOrganization(CardEditorHelper card, int row) {
    // 组织业务委托关系找组织
    OrganizationDefaultValue util = new OrganizationDefaultValue(card);
    int[] rows = new int[] {
      row
    };
    util.setDefaultOrganizationValueByArrOrg(rows);

    new FlowStockOrgValue(card).setFlowStockOrg(rows);
  }

  private void processCountryCall(CardBodyAfterEditEvent event) {
    OrderVatValueFillRule vatrule =
        (OrderVatValueFillRule) this.remoteCaller
            .get(OrderReceiveStoreOrgCountrySetter.class.getName());
    vatrule.process();
    OrderCalculatorUtils.calculate(event.getBillCardPanel(),
        vatrule.getValueChangeObject());
  }

  /**
   * 收货地址国家
   * 
   * @param event
   */
  private void registerCountryCall(CardBodyAfterEditEvent event) {
    CardEditorHelper card = new CardEditorHelper(event.getBillCardPanel());
    List<ICountrySetter> countryList = new ArrayList<ICountrySetter>();
    int[] rows = new int[] {
      event.getRow()
    };
    // 收货国
    OrderReceiveStoreOrgCountrySetter reSetter =
        new OrderReceiveStoreOrgCountrySetter(CountryType.receiveCountry, card,
            rows);
    reSetter.setFromSourcebill(false);
    countryList.add(reSetter);
    // 报税国
    OrderReceiveStoreOrgCountrySetter taxSetter =
        new OrderReceiveStoreOrgCountrySetter(CountryType.taxCountry, card,
            rows);

    taxSetter.setFromSourcebill(false);
    countryList.add(taxSetter);
    // 按财务组织找一次国家（跨公司时）
    OrderFinanceOrgCountrySetter fiTaxcountrySetter =
        new OrderFinanceOrgCountrySetter(CountryType.taxCountry, card, rows);
    fiTaxcountrySetter.setFromSourcebill(false);
    countryList.add(fiTaxcountrySetter);
    OrderVatValueFillRule vatrule =
        new OrderVatValueFillRule(card, rows, countryList);
    vatrule.prepare();
    this.remoteCaller.put(OrderReceiveStoreOrgCountrySetter.class.getName(),
        vatrule);

  }

  /**
   * 注册远程调用
   * 
   * @param event
   */
  private void registerRomoteCall(CardBodyAfterEditEvent event) {
    // 收货地址国家
    this.registerCountryCall(event);
  }

}
