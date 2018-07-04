package nc.ui.pu.m23.editor.card.beforeedit.body;

import nc.ui.pu.m23.refmodel.MaterialRefModel;
import nc.ui.pu.m23.utils.ArriveClientUtil;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.pu.m23.entity.ArriveItemVO;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>�������ϵĲ��շ�Χ��������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���ù���
 * <li>�������Դ�����в�����Ʒ && ������������Ʒ�������ϲ��� = �������ϣ����������ϣ��滻����
 * <li>�������ϲ��� = �������ϣ��滻��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-22 ����01:25:25
 */
public class Material implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent e) {
    BillCardPanel card = e.getBillCardPanel();
    // ��Ӧ�ɹ�������������������������
    String pk_order_b =
        ArriveClientUtil.getStringCellValue(card, e.getRow(),
            ArriveItemVO.PK_ORDER_B);
    if (StringUtils.isEmpty(pk_order_b)) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }

    UIRefPane materialRefPane =
        (UIRefPane) card.getBodyItem(ArriveItemVO.PK_MATERIAL).getComponent();
    MaterialRefModel materialRefModel =
        (MaterialRefModel) materialRefPane.getRefModel();
    materialRefModel.initialize(card, e.getRow());

    e.setReturnValue(Boolean.TRUE);
  }
}
