package nc.ui.pu.m21.action;

import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.ClientContext;
import nc.ui.pubapp.uif2app.actions.BodyAddLineAction;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderPaymentVO;
import nc.vo.pu.m21.rule.LineDefaultValue;
import nc.vo.pu.m21.rule.vat.setter.country.AbstractOrderCountrySetter.CountryType;
import nc.vo.pu.m21.rule.vat.setter.country.OrderSendAddressCountrySetter;
import nc.vo.pu.pub.context.IContext;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�������а�ť�¼�������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-2-2 ����07:44:48
 */
public class AddLineAction extends BodyAddLineAction {
  private static final long serialVersionUID = 1623829184658049315L;

  @Override
  public void doAction() {
    // ����Э��ҳǩ�е��������а�ť������������а�ť
    if (super.getCardPanel().getBodyPanel().getTableCode()
        .equals(OrderPaymentVO.TABSNAME)) {
      return;
    }
    super.doAction();
  }

  private void setSendCountry(CardEditorHelper helper, int row) {
    // ���ݷ�����ַ���÷�������
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
    // ���ñ���Ĭ��ֵ
    new LineDefaultValue(helper, (IContext) this.getModel().getContext())
        .setDefaultValue(index);
    // ���ݷ�����ַ���÷�������
    this.setSendCountry(helper, index);
    
    // ����Context�еı�־���ж��Ƿ���Ҫ���ع������Ҫ�Ƕ�ѡ���ϵ�ʱ���ǲ���Ҫ���ع�����ġ�
    ClientContext clientContext = (ClientContext) this.getModel().getContext();
    if (clientContext.isNeedLoadRelationItem()) {
      this.getCardPanel().getBillModel().loadLoadRelationItemValue(new int[] {
        index
      });
    }
  }
}
