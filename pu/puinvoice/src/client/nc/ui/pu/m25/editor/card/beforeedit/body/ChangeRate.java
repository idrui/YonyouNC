/**
 * $�ļ�˵��$
 * 
 * @author tianft
 * @version
 * @see
 * @since
 * @time 2010-3-25 ����04:39:16
 */
package nc.ui.pu.m25.editor.card.beforeedit.body;

import nc.ui.pu.m25.editor.utils.UnitAndChangeRateUtil;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m25.entity.InvoiceItemVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���廻���ʱ༭ǰ����
 * </ul>
 * <p>
 * <b>�����ʷ����ѡ����</b>
 * <p>
 * XXX�汾����XXX��֧�֡�
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-3-25 ����04:39:16
 */
public class ChangeRate implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    CardEditorHelper helper = new CardEditorHelper(event.getBillCardPanel());
    // ��λ
    String astUnit =
        helper.getBodyStringValue(event.getRow(), InvoiceItemVO.CASTUNITID);
    // ����λ
    String unit =
        helper.getBodyStringValue(event.getRow(), InvoiceItemVO.CUNITID);
    // �����λ�����ڻ�������λ�뵥λ��ͬ,������༭
    if (StringUtil.isEmptyWithTrim(astUnit) || StringUtil.isEmptyWithTrim(unit)
        || astUnit.equals(unit)) {
      event.setReturnValue(Boolean.FALSE);
      return;
    }
    String pk_material =
        helper.getBodyStringValue(event.getRow(), InvoiceItemVO.PK_MATERIAL);
    // �̶������ʲ�����༭
    boolean fixedRate =
        UnitAndChangeRateUtil.isFixedChangeRate(pk_material, astUnit);
    event.setReturnValue(Boolean.valueOf(!fixedRate));
  }

}
