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
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.rule.vat.OrderVatValueFillRule;
import nc.vo.pu.m21.rule.vat.OrderVatValueFillRule.ICountrySetter;
import nc.vo.pu.m21.rule.vat.setter.country.AbstractOrderCountrySetter.CountryType;
import nc.vo.pu.m21.rule.vat.setter.country.OrderReceiveAddressCountrySetter;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>收货地址编辑后处理类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-3-22 下午02:02:42
 */
public class ReceiveAddress implements ICardBodyAfterEditEventListener {
  private Map<String, IPURemoteCallCombinator> remoteCaller =
      new HashMap<String, IPURemoteCallCombinator>();

  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    int row = event.getRow();
    CardEditorHelper card = new CardEditorHelper(event.getBillCardPanel());

    String receiveAddress =
        (String) card.getBodyValue(row, OrderItemVO.PK_RECEIVEADDRESS);

    if (StringUtil.isEmptyWithTrim(receiveAddress)) { // 表体地址清空，则表体相关地区、地点也清空
      // 收货地点
      card.setBodyValue(row, OrderItemVO.CDEVADDRID, null);
      // 收货地区
      card.setBodyValue(row, OrderItemVO.CDEVAREAID, null);
      return;
    }
    this.registerRomoteCall(event);
    // // 直运，设置发货地址国家
    // if (UFBoolean.TRUE.equals(card.getHeadValue(OrderHeaderVO.BDIRECT))) {
    this.processCountryRomoteCall(event);
    // }
  }

  /**
   * 处理收货地址的国家
   * 
   * @param event
   */
  private void processCountryRomoteCall(CardBodyAfterEditEvent event) {
    OrderVatValueFillRule vatrule =
        (OrderVatValueFillRule) this.remoteCaller
            .get(OrderReceiveAddressCountrySetter.class.getName());
    vatrule.process();
    OrderCalculatorUtils.calculate(event.getBillCardPanel(),
        vatrule.getValueChangeObject());
  }

  private void registerCountryRomoteCall(CardBodyAfterEditEvent event) {
    CardEditorHelper card = new CardEditorHelper(event.getBillCardPanel());
    List<ICountrySetter> countryList = new ArrayList<ICountrySetter>();
    int[] rows = new int[] {
      event.getRow()
    };
    countryList.add(new OrderReceiveAddressCountrySetter(
        CountryType.receiveCountry, card, rows));
    countryList.add(new OrderReceiveAddressCountrySetter(
        CountryType.destCountry, card, rows));
    OrderVatValueFillRule vatrule =
        new OrderVatValueFillRule(card, rows, countryList);
    vatrule.prepare();
    this.remoteCaller.put(OrderReceiveAddressCountrySetter.class.getName(),
        vatrule);
  }

  /**
   * 注册远程调用
   * 
   * @param event
   */
  private void registerRomoteCall(CardBodyAfterEditEvent event) {
    this.registerCountryRomoteCall(event);
  }
}
