package nc.ui.pu.m21.editor.card.beforeedit.body;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.ui.ic.batchcode.ref.BatchRefPane;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.ic.batchcode.BatchDlgParam;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;

/**
 * @since 6.0
 * @version 2011-4-23 上午08:51:36
 * @author wuxla
 */

public class RPBatchCode implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    if (!this.canEdit(event)) {
      event.setReturnValue(Boolean.FALSE);
      return;
    }
    // 设置批次参照所需要的参数
    this.setBatchRefPara(event);
    event.setReturnValue(Boolean.TRUE);
  }

  private boolean canEdit(CardBodyBeforeEditEvent event) {
    BillCardPanel panel = event.getBillCardPanel();
    int row = event.getRow();
    String pk_material =
        (String) panel.getBodyValueAt(row, OrderReceivePlanVO.PK_MATERIAL);
    String pk_arrvstoorg =
        (String) panel.getBodyValueAt(row, OrderReceivePlanVO.PK_ARRVSTOORG);
    if (nc.vo.jcom.lang.StringUtil.isEmptyWithTrim(pk_material)
        || nc.vo.jcom.lang.StringUtil.isEmptyWithTrim(pk_arrvstoorg)) {
      return false;
    }

    MaterialStockVO[] mrlStockVOArray = null;
    // 查询物料是否批次管理
    mrlStockVOArray =
        MaterialPubService.queryMaterialStockInfoByPks(new String[] {
          pk_material
        }, pk_arrvstoorg, new String[] {
          MaterialStockVO.WHOLEMANAFLAG
        });
    if (null == mrlStockVOArray) {
      return false;
    }
    return mrlStockVOArray[0].getWholemanaflag().booleanValue();
  }

  private BatchDlgParam getBatchDlgParam(CardBodyBeforeEditEvent event) {
    BillCardPanel panel = event.getBillCardPanel();
    int row = event.getRow();
    BatchDlgParam para = new BatchDlgParam();
    para.setCmaterialoid((String) panel.getBodyValueAt(row,
        OrderReceivePlanVO.PK_SRCMATERIAL));
    para.setCmaterialvid((String) panel.getBodyValueAt(row,
        OrderReceivePlanVO.PK_MATERIAL));
    para.setLoginContext(event.getContext());
    para.getOnhandDim().setCwarehouseid(
        (String) panel.getBodyValueAt(row, OrderReceivePlanVO.PK_RECVSTORDOC));
    para.getOnhandDim().setPk_org(
        (String) panel.getBodyValueAt(row, OrderReceivePlanVO.PK_ARRVSTOORG));
    para.getOnhandDim().setCastunitid(
        (String) panel.getBodyValueAt(row, OrderReceivePlanVO.CASTUNITID));
    para.getOnhandDim().setPk_group(
        (String) panel.getBodyValueAt(row, OrderReceivePlanVO.PK_GROUP));
    para.getOnhandDim().setCvendorid(null);
    para.getOnhandDim().setCprojectid(null);
    para.getOnhandDim().setCproductorid(
        (String) panel.getBodyValueAt(row, OrderReceivePlanVO.CVENDORID));
    para.getOnhandDim().setVchangerate(
        (String) panel.getBodyValueAt(row, OrderReceivePlanVO.VCHANGERATE));
    // 自由辅助属性
    for (int i = 1; i <= 10; i++) {
      para.getOnhandDim().setAttributeValue("vfree" + i,
          panel.getBodyValueAt(row, "vfree" + i));
    }
    return para;
  }

  private void setBatchRefPara(CardBodyBeforeEditEvent e) {
    BillCardPanel card = e.getBillCardPanel();

    nc.ui.pub.bill.BillItem billItem = card.getBodyItem(e.getKey());
    if (!(billItem.getComponent() instanceof BatchRefPane)) {
      return;
    }
    // 设置批次参照所需要的参数
    BatchRefPane pane = (BatchRefPane) billItem.getComponent();
    pane.setParam(this.getBatchDlgParam(e));
  }
}
