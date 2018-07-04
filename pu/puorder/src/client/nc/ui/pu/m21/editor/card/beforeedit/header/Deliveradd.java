package nc.ui.pu.m21.editor.card.beforeedit.header;

import nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener;
import nc.ui.pu.ref.FilterSuppAddressRefUtils;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.vo.pu.m21.entity.OrderHeaderVO;

/**
 * ��Ӧ�̷�����ַ�༭ǰ�¼������ڹ��˹�Ӧ�̷�����ַ����
 * 
 * @since 6.3
 * @version 2012-12-20 ����10:44:31
 * @author lixyp
 */
public class Deliveradd implements ICardHeadTailBeforeEditEventListener {

  @Override
  public void beforeEdit(CardHeadTailBeforeEditEvent e) {
    BillCardPanel panel = e.getBillCardPanel();
    String pk_supplier =
        (String) panel.getHeadItem(OrderHeaderVO.PK_SUPPLIER).getValueObject();
    if (pk_supplier != null) {
      String pk_org =
          (String) panel.getHeadItem(OrderHeaderVO.PK_ORG).getValueObject();
      FilterSuppAddressRefUtils utils =
          new FilterSuppAddressRefUtils((UIRefPane) panel.getHeadItem(
              OrderHeaderVO.PK_DELIVERADD).getComponent());
      utils.filteRefBySupplier(pk_supplier, pk_org);
      e.setReturnValue(Boolean.TRUE);
    }
    else {
      e.setReturnValue(Boolean.FALSE);
    }
  }

}
