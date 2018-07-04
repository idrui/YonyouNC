package nc.ui.pu.m21.editor.card.afteredit.header;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.itf.scmpub.reference.uap.bd.addrdoc.AddrdocPubService;
import nc.itf.scmpub.reference.uap.bd.customer.CustomerPubService;
import nc.ui.pu.m21.util.OrderCalculatorUtils;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.rule.vat.OrderVatValueFillRule;
import nc.vo.pu.m21.rule.vat.OrderVatValueFillRule.ICountrySetter;
import nc.vo.pu.m21.rule.vat.setter.country.AbstractOrderCountrySetter.CountryType;
import nc.vo.pu.m21.rule.vat.setter.country.OrderCustomerCountrySetter;
import nc.vo.pubapp.pattern.pub.PubAppTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>收货客户的编辑后事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-3-27 下午09:36:55
 */
public class ReceiveCustomer implements ICardHeadTailAfterEditEventListener {

  private Map<String, IPURemoteCallCombinator> remoteCaller =
      new HashMap<String, IPURemoteCallCombinator>();

  @Override
  public void afterEdit(CardHeadTailAfterEditEvent event) {
    // 处理收货地址相关
    this.processReceiveCustomerAddress(event);
    // 注册远程调用
    this.registerRomoteCall(event);
    // 处理收货国家，此时收货地址国家也即是收货客户的国家
    this.processCountryCall(event);
  }

  private void processCountryCall(CardHeadTailAfterEditEvent event) {
    OrderVatValueFillRule vatrule =
        (OrderVatValueFillRule) this.remoteCaller
            .get(OrderCustomerCountrySetter.class.getName());
    vatrule.process();
    OrderCalculatorUtils.calculate(event.getBillCardPanel(),
        vatrule.getValueChangeObject());
  }

  private void processReceiveCustomerAddress(CardHeadTailAfterEditEvent event) {
    CardEditorHelper helper = new CardEditorHelper(event.getBillCardPanel());
    String receiveCustomerid =
        helper.getHeadStringValue(OrderHeaderVO.PK_RECVCUSTOMER);
    if (!PubAppTool.isNull(receiveCustomerid)) {
      String pk_org = helper.getHeadStringValue(OrderHeaderVO.PK_ORG);
      // 取默认收货地址
      String defaultReceiveAddress =
          CustomerPubService.getDefaultAddress(receiveCustomerid, pk_org);
      if (!PubAppTool.isNull(defaultReceiveAddress)) {
        // 取默认收货地区
        String[] defaultReceiveAreas =
            CustomerPubService.getAreaPksByConsignAddress(new String[] {
              defaultReceiveAddress
            });
        String defaultReceiveArea = null;
        if (!ArrayUtils.isEmpty(defaultReceiveAreas)) {
          defaultReceiveArea = defaultReceiveAreas[0];
        }
        // 取默认收货地点
        Map<String, String> defaultAddressDoc =
            AddrdocPubService.getAddressDocPksByConsignAddress(new String[] {
              defaultReceiveAddress
            });

        for (int i = 0; i < helper.getItemCount(); i++) {
          if (helper.getBodyValue(i, OrderItemVO.PK_MATERIAL) != null) {
            helper.setBodyValue(i, OrderItemVO.PK_RECEIVEADDRESS,
                defaultReceiveAddress);
            helper.setBodyValue(i, OrderItemVO.CDEVAREAID, defaultReceiveArea);
            helper.setBodyValue(i, OrderItemVO.CDEVADDRID,
                defaultAddressDoc.get(defaultReceiveAddress));
          }
        }

      }

    }

  }

  /**
   * 收货地址国家
   * 
   * @param event
   */
  private void registerCountryCall(CardHeadTailAfterEditEvent event) {
    CardEditorHelper card = new CardEditorHelper(event.getBillCardPanel());
    List<ICountrySetter> countryList = new ArrayList<ICountrySetter>();
    // 收货国
    countryList.add(new OrderCustomerCountrySetter(CountryType.receiveCountry,
        card, null));
    OrderVatValueFillRule vatrule =
        new OrderVatValueFillRule(card, null, countryList);
    vatrule.prepare();
    this.remoteCaller.put(OrderCustomerCountrySetter.class.getName(), vatrule);
  }

  /**
   * 注册远程调用
   * 
   * @param event
   */
  private void registerRomoteCall(CardHeadTailAfterEditEvent event) {
    // 收货地址国家
    this.registerCountryCall(event);
  }

  // private void setBodyAddressInfo(CardEditorHelper editor,
  // CustaddressVO custaddressVO) {
  // for (int i = 0; i < editor.getEditor().getBillModel().getRowCount(); i++) {
  // if (custaddressVO == null) {
  // // 收货地址
  // editor.getEditor().getBillModel()
  // .setValueAt(null, i, OrderItemVO.PK_RECEIVEADDRESS);
  // // 收货地区
  // editor.getEditor().getBillModel()
  // .setValueAt(null, i, OrderItemVO.CDEVAREAID);
  // // 收货地点
  // editor.getEditor().getBillModel()
  // .setValueAt(null, i, OrderItemVO.CDEVADDRID);
  // }
  // else {
  // // 收货地址
  // editor
  // .getEditor()
  // .getBillModel()
  // .setValueAt(custaddressVO.getPk_address(), i,
  // OrderItemVO.PK_RECEIVEADDRESS);
  // // 收货地区
  // editor
  // .getEditor()
  // .getBillModel()
  // .setValueAt(custaddressVO.getPk_areacl(), i, OrderItemVO.CDEVAREAID);
  // // 收货地点
  // editor
  // .getEditor()
  // .getBillModel()
  // .setValueAt(custaddressVO.getPk_addressdoc(), i,
  // OrderItemVO.CDEVADDRID);
  // }
  // }
  // editor.getEditor().getBillModel().loadLoadRelationItemValue();
  // editor.getEditor().getBillModel()
  // .execLoadFormulaByKey(OrderItemVO.PK_RECEIVEADDRESS);
  // }

}
