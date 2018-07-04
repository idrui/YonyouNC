/**
 * $�ļ�˵��$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-19 ����02:36:21
 */
package nc.ui.pu.m20.editor.card.beforeedit.body;

import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.pub.enumeration.PuRefBillTypeIdEnum;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�����༭ǰ�¼�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-4-19 ����02:36:21
 */
public class Nastnum implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    String pk_material =
        (String) event.getBillCardPanel().getBodyValueAt(event.getRow(),
            PraybillItemVO.PK_MATERIAL);
    String csourcetypecode =
        (String) event.getBillCardPanel().getBodyValueAt(event.getRow(),
            PraybillItemVO.CSOURCETYPECODE);
    // �빺������Դ���ʲ��������룬�������ñ༭
    if (StringUtil.isEmptyWithTrim(pk_material)
        || PuRefBillTypeIdEnum.M4A08.getBillTypeId().equals(csourcetypecode)) {
      // ����δ���ã����ܱ༭
      event.setReturnValue(Boolean.FALSE);
    }
  }

}
