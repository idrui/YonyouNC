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
    // ע��Զ�̵���
    this.registerRomoteCall(event);
    int row = event.getRow();
    CardEditorHelper card = new CardEditorHelper(event.getBillCardPanel());
    // ���䱾λ�Һͻ��ʵ������Ϣ�������ÿɱ༭��
    new CurrencyRelated(event.getBillCardPanel())
        .setCurrencyAndExchangeRate(new int[] {
          row
        });

    // ͬ��Ӧ����֯
    this.asyncApfinanceorg(card, row);
    // ����������
    this.processCountryCall(event);
    // mengjian ���ݲ���PO16�Զ�ѯ�������Զ�ѯ��
    this.setDefaultPrice(event);
  }

  /**
   * ��������������ͬ��Ӧ����֯
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-7-30 ����08:43:12
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
    // �ջ���
    OrderFinanceOrgCountrySetter reSetter =
        new OrderFinanceOrgCountrySetter(CountryType.receiveCountry, card, rows);
    reSetter.setFromSourcebill(false);
    countryList.add(reSetter);
    // ��˰��
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
   * ע��Զ�̵���
   * 
   * @param event
   */
  private void registerRomoteCall(CardBodyAfterEditEvent event) {
    // ������֯�����ջ���,��˰��
    this.registerCountryCall(event);
  }

  /**
   * ���ݲ���PO16�Զ�ѯ�������Զ�ѯ��
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
