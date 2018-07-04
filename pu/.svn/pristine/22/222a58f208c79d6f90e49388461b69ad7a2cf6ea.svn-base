package nc.ui.pu.m422x.editor.card.beforeedit.body;

import java.util.HashMap;
import java.util.Map;

import nc.ui.ic.batchcode.ref.BatchRefPane;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pu.pub.util.VBatchCodeUtil;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.ic.onhand.entity.OnhandDimVO;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;

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
        (String) panel.getBodyValueAt(row, StoreReqAppItemVO.PK_MATERIAL);
    String pk_org =
        (String) panel.getHeadItem(StoreReqAppHeaderVO.PK_ORG).getValueObject();
    if (!VBatchCodeUtil.canEdit(pk_material, pk_org)) {
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
    bodyDims.put(OnhandDimVO.PK_ORG, StoreReqAppItemVO.PK_ORG);
    bodyDims.put(OnhandDimVO.CVENDORID, StoreReqAppItemVO.CVENDORID);
    bodyDims.put(OnhandDimVO.CWAREHOUSEID, StoreReqAppItemVO.PK_REQSTORDOC);
    return bodyDims;
  }

  private Map<String, String> getHeadDims() {
    Map<String, String> headDims = new HashMap<String, String>();
    return headDims;
  }

}
