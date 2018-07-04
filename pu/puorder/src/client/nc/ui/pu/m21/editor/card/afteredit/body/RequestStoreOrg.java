package nc.ui.pu.m21.editor.card.afteredit.body;

import java.util.ArrayList;
import java.util.List;

import nc.ui.pu.m21.rule.CurrencyRelated;
import nc.ui.pu.m21.util.OrderCalculatorUtils;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.rule.OrganizationDefaultValue;
import nc.vo.pu.m21.rule.ReqCorpDefaultValue;
import nc.vo.pu.m21.rule.WarehouseDefaultValue;
import nc.vo.pu.m21.rule.vat.OrderVatValueFillRule;
import nc.vo.pu.m21.rule.vat.OrderVatValueFillRule.ICountrySetter;
import nc.vo.pu.m21.rule.vat.setter.country.AbstractOrderCountrySetter.CountryType;
import nc.vo.pu.m21.rule.vat.setter.country.OrderFinanceOrgCountrySetter;
import nc.vo.pu.m21.rule.vat.setter.country.OrderReceiveStoreOrgCountrySetter;
import nc.vo.pu.pub.rule.PCCostregionSetter;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>需求库存组织的编辑事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-1-29 下午04:08:08
 */
public class RequestStoreOrg implements ICardBodyAfterEditEventListener {

  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    int row = event.getRow();
    CardEditorHelper card = new CardEditorHelper(event.getBillCardPanel());
    if (card.getBodyValue(row, OrderItemVO.PK_REQSTOORG) == null) {
      return;
    }

    // 同步收货库存组织
    this.asyncArriveStoreOrganization(card, row);
    // 根据组织业务委托关系找结算财务组织、应付财务组织、利润中心等
    this.defaultOrganization(card, row);

    // 补充本位币和汇率的相关信息，并设置可编辑性
    new CurrencyRelated(event.getBillCardPanel())
        .setCurrencyAndExchangeRate(new int[] {
          row
        });

    // 设置需求公司
    new ReqCorpDefaultValue(card).setDefaultValue(new int[] {
      row
    });
  }

  private void asyncArriveStoreOrganization(CardEditorHelper card, int row) {
    // 需求库存组织同步收货库存组织
    String req = (String) card.getBodyValue(row, OrderItemVO.PK_REQSTOORG);
    String req_v = (String) card.getBodyValue(row, OrderItemVO.PK_REQSTOORG_V);
    if (req != null) {
      card.setBodyValue(row, OrderItemVO.PK_ARRVSTOORG, req);
      card.setBodyValue(row, OrderItemVO.PK_ARRVSTOORG_V, req_v);

      // 清空表体的收货仓库的值
      this.clearReceiveWarehouse(card, row);
      // 重新设置收货利润中心
      // 设置收货利润中心默认值
      new PCCostregionSetter(card).setArrviCentersByOrg(new int[] {
        row
      });
      // CardEditorHelper editor = new CardEditorHelper(card);
      List<ICountrySetter> countryList = new ArrayList<ICountrySetter>();
      int[] rows = new int[] {
        row
      };
      // 收货国
      OrderReceiveStoreOrgCountrySetter revCountry =
          new OrderReceiveStoreOrgCountrySetter(CountryType.receiveCountry,
              card, rows);
      revCountry.setFromSourcebill(false);
      countryList.add(revCountry);
      // 报税国
      OrderReceiveStoreOrgCountrySetter taxCountry =
          new OrderReceiveStoreOrgCountrySetter(CountryType.taxCountry, card,
              rows);
      taxCountry.setFromSourcebill(false);
      countryList.add(taxCountry);

      // 按财务组织找一次国家（跨公司时）
      OrderFinanceOrgCountrySetter fiTaxcountrySetter =
          new OrderFinanceOrgCountrySetter(CountryType.taxCountry, card, rows);
      fiTaxcountrySetter.setFromSourcebill(false);
      countryList.add(fiTaxcountrySetter);

      OrderVatValueFillRule vatrule =
          new OrderVatValueFillRule(card, rows, countryList);
      vatrule.prepare();
      vatrule.process();
      OrderCalculatorUtils.calculate(card.getEditor(),
          vatrule.getValueChangeObject());

    }
  }

  private void clearReceiveWarehouse(CardEditorHelper card, int row) {
    card.setBodyValue(row, OrderItemVO.PK_RECVSTORDOC, null);
    WarehouseDefaultValue warehouse = new WarehouseDefaultValue(card);
    warehouse.setWarehouseDefaultValue(row);
  }

  private void defaultOrganization(CardEditorHelper card, int row) {
    // 组织业务委托关系找组织
    OrganizationDefaultValue util = new OrganizationDefaultValue(card);
    util.setDefaultOrganizationValue(new int[] {
      row
    });
  }

}
