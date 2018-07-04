package nc.ui.pu.m23.editor.card.beforeedit.body;

import java.util.HashMap;
import java.util.Map;

import nc.ui.ic.batchcode.ref.BatchRefPane;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pu.pub.util.VBatchCodeUtil;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.ic.onhand.entity.OnhandDimVO;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;

/**
 * <p>
 * <b>“批次号”字段的编辑事件处理类，本类主要完成以下功能：</b>
 * <ul>
 * <li>根据当前物料是否批次号管理，设置“批次号”字段是否可以编辑、设置批次参照所需要的参数
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-22 下午01:25:25
 */
public class BatchCode implements ICardBodyBeforeEditEventListener {
  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    BillCardPanel panel = event.getBillCardPanel();
    int row = event.getRow();
    String pk_material =
        (String) panel.getBodyValueAt(row, ArriveItemVO.PK_MATERIAL);
    String pk_org =
        (String) panel.getHeadItem(ArriveHeaderVO.PK_ORG).getValueObject();
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
    bodyDims.put(OnhandDimVO.PK_ORG, ArriveItemVO.PK_ORG);
    bodyDims.put(OnhandDimVO.CWAREHOUSEID, ArriveItemVO.PK_RECEIVESTORE);
    return bodyDims;
  }

  private Map<String, String> getHeadDims() {
    Map<String, String> headDims = new HashMap<String, String>();
    headDims.put(OnhandDimVO.CVENDORID, ArriveHeaderVO.PK_SUPPLIER);
    return headDims;
  }
}
