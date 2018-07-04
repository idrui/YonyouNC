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
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>供应商发货地址编辑后处理类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-3-22 上午11:30:29
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

    if (StringUtils.isBlank(venddevaddr)) {// 表体地址清空，则表体相关地区、地点也清空
      // 供应商发货地点
      card.setBodyValue(row, OrderItemVO.CVENDDEVADDRID, null);
      // 供应商发货地区
      card.setBodyValue(row, OrderItemVO.CVENDDEVAREAID, null);
      return;
    }
    // 注册远程调用
    this.registerRomoteCall(event);
    this.processSendAddressRomoteCall(event);

  }

  /**
   * 处理发货地址的国家
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
   * 注册远程调用
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
    // 发货国
    OrderSendAddressCountrySetter sendCountrySetter =
        new OrderSendAddressCountrySetter(CountryType.sendCountry, card, rows);
    sendCountrySetter.setFromSourcebill(false);
    countryList.add(sendCountrySetter);
    // 原产国
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
