package nc.ui.pu.m21.editor.card.afteredit.body;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.ui.pu.m21.util.OrderCalculatorUtils;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.rule.vat.OrderVatValueFillRule;
import nc.vo.pu.m21.rule.vat.OrderVatValueFillRule.ICountrySetter;
import nc.vo.pu.m21.rule.vat.setter.country.AbstractOrderCountrySetter.CountryType;
import nc.vo.pu.m21.rule.vat.setter.country.OrderSendAddressCountrySetter;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��Ӧ�̷�����ַ�༭������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-3-22 ����11:30:29
 */
public class SupplierAddrress implements ICardBodyAfterEditEventListener {

  private Map<String, IPURemoteCallCombinator> remoteCaller =
      new HashMap<String, IPURemoteCallCombinator>();

  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    int row = event.getRow();
    CardEditorHelper card = new CardEditorHelper(event.getBillCardPanel());

    String venddevaddr =
        (String) card.getBodyValue(row, OrderItemVO.VVENDDEVADDR);

    if (StringUtils.isBlank(venddevaddr)) {// �����ַ��գ��������ص������ص�Ҳ���
      // ��Ӧ�̷����ص�
      card.setBodyValue(row, OrderItemVO.CVENDDEVADDRID, null);
      // ��Ӧ�̷�������
      card.setBodyValue(row, OrderItemVO.CVENDDEVAREAID, null);
      return;
    }
    // ע��Զ�̵���
    this.registerRomoteCall(event);
    this.processSendAddressRomoteCall(event);

  }

  /**
   * ��������ַ�Ĺ���
   * 
   * @param event
   */
  private void processSendAddressRomoteCall(CardBodyAfterEditEvent event) {
    OrderVatValueFillRule vatrule =
        (OrderVatValueFillRule) this.remoteCaller
            .get(OrderSendAddressCountrySetter.class.getName());
    vatrule.process();
    OrderCalculatorUtils.calculate(event.getBillCardPanel(),
        vatrule.getValueChangeObject());
  }

  /**
   * ע��Զ�̵���
   * 
   * @param event
   */
  private void registerRomoteCall(CardBodyAfterEditEvent event) {
    this.registerSendAddressRomoteCall(event);
  }

  private void registerSendAddressRomoteCall(CardBodyAfterEditEvent event) {
    CardEditorHelper card = new CardEditorHelper(event.getBillCardPanel());
    List<ICountrySetter> countryList = new ArrayList<ICountrySetter>();
    int[] rows = new int[] {
      event.getRow()
    };
    // ������
    OrderSendAddressCountrySetter sendCountrySetter =
        new OrderSendAddressCountrySetter(CountryType.sendCountry, card, rows);
    sendCountrySetter.setFromSourcebill(false);
    countryList.add(sendCountrySetter);
    // ԭ����
    OrderSendAddressCountrySetter origCountrySetter =
        new OrderSendAddressCountrySetter(CountryType.origCountry, card, rows);
    origCountrySetter.setFromSourcebill(false);
    countryList.add(origCountrySetter);
    OrderVatValueFillRule vatrule =
        new OrderVatValueFillRule(card, rows, countryList);
    vatrule.prepare();
    this.remoteCaller.put(OrderSendAddressCountrySetter.class.getName(),
        vatrule);
  }

}
