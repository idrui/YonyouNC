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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��������֯�ı༭�¼�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-1-29 ����04:08:08
 */
public class RequestStoreOrg implements ICardBodyAfterEditEventListener {

  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    int row = event.getRow();
    CardEditorHelper card = new CardEditorHelper(event.getBillCardPanel());
    if (card.getBodyValue(row, OrderItemVO.PK_REQSTOORG) == null) {
      return;
    }

    // ͬ���ջ������֯
    this.asyncArriveStoreOrganization(card, row);
    // ������֯ҵ��ί�й�ϵ�ҽ��������֯��Ӧ��������֯���������ĵ�
    this.defaultOrganization(card, row);

    // ���䱾λ�Һͻ��ʵ������Ϣ�������ÿɱ༭��
    new CurrencyRelated(event.getBillCardPanel())
        .setCurrencyAndExchangeRate(new int[] {
          row
        });

    // ��������˾
    new ReqCorpDefaultValue(card).setDefaultValue(new int[] {
      row
    });
  }

  private void asyncArriveStoreOrganization(CardEditorHelper card, int row) {
    // ��������֯ͬ���ջ������֯
    String req = (String) card.getBodyValue(row, OrderItemVO.PK_REQSTOORG);
    String req_v = (String) card.getBodyValue(row, OrderItemVO.PK_REQSTOORG_V);
    if (req != null) {
      card.setBodyValue(row, OrderItemVO.PK_ARRVSTOORG, req);
      card.setBodyValue(row, OrderItemVO.PK_ARRVSTOORG_V, req_v);

      // ��ձ�����ջ��ֿ��ֵ
      this.clearReceiveWarehouse(card, row);
      // ���������ջ���������
      // �����ջ���������Ĭ��ֵ
      new PCCostregionSetter(card).setArrviCentersByOrg(new int[] {
        row
      });
      // CardEditorHelper editor = new CardEditorHelper(card);
      List<ICountrySetter> countryList = new ArrayList<ICountrySetter>();
      int[] rows = new int[] {
        row
      };
      // �ջ���
      OrderReceiveStoreOrgCountrySetter revCountry =
          new OrderReceiveStoreOrgCountrySetter(CountryType.receiveCountry,
              card, rows);
      revCountry.setFromSourcebill(false);
      countryList.add(revCountry);
      // ��˰��
      OrderReceiveStoreOrgCountrySetter taxCountry =
          new OrderReceiveStoreOrgCountrySetter(CountryType.taxCountry, card,
              rows);
      taxCountry.setFromSourcebill(false);
      countryList.add(taxCountry);

      // ��������֯��һ�ι��ң��繫˾ʱ��
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
    // ��֯ҵ��ί�й�ϵ����֯
    OrganizationDefaultValue util = new OrganizationDefaultValue(card);
    util.setDefaultOrganizationValue(new int[] {
      row
    });
  }

}
