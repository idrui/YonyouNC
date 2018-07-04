package nc.ui.pu.m4t.editor.body.before;

import java.util.HashMap;
import java.util.Map;

import nc.ui.ic.batchcode.ref.BatchRefPane;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pu.pub.util.VBatchCodeUtil;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.ic.onhand.entity.OnhandDimVO;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.m4t.entity.InitialEstItemVO;

/**
 * @since 6.0
 * @version 2011-6-19 上午09:38:56
 * @author wuxla
 */

public class VBatchCode implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    BillCardPanel panel = event.getBillCardPanel();
    int row = event.getRow();
    String pk_material =
        (String) panel.getBodyValueAt(row, InitialEstItemVO.PK_MATERIAL);
    String pk_stockorg =
        (String) panel.getHeadItem(InitialEstHeaderVO.PK_STOCKORG)
            .getValueObject();
    if (!VBatchCodeUtil.canEdit(pk_material, pk_stockorg)) {
      event.setReturnValue(Boolean.FALSE);
      return;
    }
    // 设置批次参照所需要的参数
    nc.ui.pub.bill.BillItem billItem = panel.getBodyItem(event.getKey());
    if (!(billItem.getComponent() instanceof BatchRefPane)) {
      return;
    }
    // 设置批次参照所需要的参数
    BatchRefPane pane = (BatchRefPane) billItem.getComponent();
    pane.setParam(VBatchCodeUtil.getBatchDlgParam(event, event.getRow(),
        this.getHeadDims(), this.getBodyDims()));
    event.setReturnValue(Boolean.TRUE);
  }

  private Map<String, String> getBodyDims() {
    Map<String, String> bodyDims = new HashMap<String, String>();
    bodyDims.put(OnhandDimVO.PK_ORG, InitialEstItemVO.PK_ORG);
    return bodyDims;
  }

  private Map<String, String> getHeadDims() {
    Map<String, String> headDims = new HashMap<String, String>();
    headDims.put(OnhandDimVO.CWAREHOUSEID, InitialEstHeaderVO.PK_STORDOC);
    headDims.put(OnhandDimVO.CWAREHOUSEID, InitialEstHeaderVO.PK_SUPPLIER);
    return headDims;
  }
}
