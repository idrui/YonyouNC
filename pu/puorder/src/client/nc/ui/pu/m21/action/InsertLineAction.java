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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�����еİ�ť�¼�������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-2-2 ����08:22:03
 */
public class InsertLineAction extends BodyInsertLineAction {
  private static final long serialVersionUID = -1173068897937372463L;

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
