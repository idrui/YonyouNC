package nc.ui.pu.m20.editor.card.beforeedit.body;

import java.util.HashMap;
import java.util.Map;

import nc.ui.ic.batchcode.ref.BatchRefPane;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pu.pub.util.VBatchCodeUtil;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.ic.onhand.entity.OnhandDimVO;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;

/**
 * 批次号 编辑前事件
 * 
 * @since 6.0
 * @version 2011-6-15 下午06:18:07
 * @author liuchx
 */
public class BatchCode implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    BillCardPanel panel = event.getBillCardPanel();
    int row = event.getRow();
    String pk_material =
        (String) panel.getBodyValueAt(row, PraybillItemVO.PK_MATERIAL);
    String pk_org =
        (String) panel.getHeadItem(PraybillHeaderVO.PK_ORG).getValueObject();
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
    bodyDims.put(OnhandDimVO.PK_ORG, PraybillItemVO.PK_ORG);
    bodyDims.put(OnhandDimVO.CVENDORID, PraybillItemVO.PK_SUGGESTSUPPLIER);
    bodyDims.put(OnhandDimVO.CWAREHOUSEID, PraybillItemVO.PK_REQSTOR);
    return bodyDims;
  }

  private Map<String, String> getHeadDims() {
    Map<String, String> headDims = new HashMap<String, String>();
    return headDims;
  }
}
