package nc.ui.pu.m21.action;

import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.ClientContext;
import nc.ui.pubapp.uif2app.actions.BodyInsertLineAction;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.rule.LineDefaultValue;
import nc.vo.pu.m21.rule.vat.setter.country.AbstractOrderCountrySetter.CountryType;
import nc.vo.pu.m21.rule.vat.setter.country.OrderSendAddressCountrySetter;
import nc.vo.pu.pub.context.IContext;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>插入行的按钮事件处理类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-2-2 下午08:22:03
 */
public class InsertLineAction extends BodyInsertLineAction {
  private static final long serialVersionUID = -1173068897937372463L;

  private void setSendCountry(CardEditorHelper helper, int row) {
    // 根据发货地址设置发货国家
    OrderSendAddressCountrySetter addrSetter =
        new OrderSendAddressCountrySetter(CountryType.sendCountry, helper,
            new int[] {
              row
            });
    addrSetter.setFromSourcebill(false);
    addrSetter.setCountry();
    this.getCardPanel().getBillModel()
        .loadLoadRelationItemValue(row, OrderItemVO.CSENDCOUNTRYID);
  }

  @Override
  protected void afterLineInsert(int index) {
    super.afterLineInsert(index);
    CardEditorHelper helper = new CardEditorHelper(this.getCardPanel());
    // 设置表体默认值
    new LineDefaultValue(helper, (IContext) this.getModel().getContext())
        .setDefaultValue(index);
    // 根据发货地址设置发货国家
    this.setSendCountry(helper, index);
    
    // 根据Context中的标志来判断是否需要加载关联项，主要是多选物料的时候是不需要加载关联项的。
    ClientContext clientContext = (ClientContext) this.getModel().getContext();
    if (clientContext.isNeedLoadRelationItem()) {
      this.getCardPanel().getBillModel().loadLoadRelationItemValue(new int[] {
        index
      });
    }
  }

}
