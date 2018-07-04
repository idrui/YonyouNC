package nc.ui.pu.m21.editor.card.beforeedit.header;

import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.supplier.SupplierPubService;
import nc.ui.bd.ref.model.FreeCustRefModel;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pub.lang.UFBoolean;

/**
 * @since 6.0
 * @version 2011-6-1 上午11:45:40
 * @author wuxla
 */

public class Freecust implements ICardHeadTailBeforeEditEventListener {

  @SuppressWarnings("restriction")
  @Override
  public void beforeEdit(CardHeadTailBeforeEditEvent e) {
    BillCardPanel panel = e.getBillCardPanel();
    String pk_supplier =
        (String) panel.getHeadItem(OrderHeaderVO.PK_SUPPLIER).getValueObject();
    if (null == pk_supplier) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }
    // 单据供应商不为空时判断散户，若不为散户，不可编辑（返回false）；若为散户，可编辑（不处理）。
    Map<String, UFBoolean> freeCustInfo =
        SupplierPubService.getFreeCust(new String[] {
          pk_supplier
        });
    if (freeCustInfo == null || freeCustInfo.size() == 0) {
      return;
    }

    UFBoolean isFreeCust = freeCustInfo.get(pk_supplier);
    if (UFBoolean.FALSE.equals(isFreeCust)) {
      e.setReturnValue(Boolean.FALSE);
      return;
    }

    UIRefPane refPanel =
        (UIRefPane) panel.getHeadItem(OrderHeaderVO.PK_FREECUST).getComponent();
    ((FreeCustRefModel) refPanel.getRefModel()).setCustomSupplier(pk_supplier);
  }

}
