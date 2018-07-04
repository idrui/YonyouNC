/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-19 ����03:28:01
 */
package nc.ui.pu.est.view;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.ui.pu.est.model.EstUIContext;
import nc.ui.pu.est.util.EstScaleUtil;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillListPanel;
import nc.vo.pu.est.entity.m45.PurchaseInEstHeaderVO;
import nc.vo.pu.est.enumeration.QueryEstType;
import nc.vo.pu.est.util.EstVOUtil.ScaleKeyInfo;
import nc.vo.pu.est.util.EstVOUtil.StockScaleKeyInfo;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��������һЩ��ʼ������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-19 ����03:28:01
 */
public class EstEditorInitializer {
  private EstEditor editor;

  /**
   * EstEditorInitiaizer �Ĺ�����
   * 
   * @param editor
   */
  public EstEditorInitializer(EstEditor editor) {
    super();
    this.editor = editor;
  }

  public void setEditable() {
    EstUIContext context = this.getContext();
    QueryEstType esttype = context.getEsttype();
    if (QueryEstType.GOODS_EST == esttype) {
      this.getBillListPanel().setEnabled(true);
      this.getBillCardPanel().setEnabled(true);
    }
    else if (QueryEstType.FEE_EST == esttype) {
      this.setFeeEstListEdit();
      this.getBillCardPanel().setEnabled(true);
    }
    else {
      this.setFeeEstListEdit();
      this.getBillCardPanel().setEnabled(false);
    }
  }

  /** ��ʼ�������ݹ�(����)���� **/
  public void setFeeScale(ScaleKeyInfo keyInfo) {
    EstScaleUtil util =
        new EstScaleUtil(this.getBillCardPanel(), keyInfo, WorkbenchEnvironment
            .getInstance().getGroupVO().getPk_group());
    util.initFeeScale();
  }

  /** ��ʼ�������ݹ����б��ͷ������ **/
  public void setGoodsScale(ScaleKeyInfo keyInfo) {
    EstScaleUtil util =
        new EstScaleUtil(this.getBillListPanel(), keyInfo, WorkbenchEnvironment
            .getInstance().getGroupVO().getPk_group());
    util.initGoodsScale();
  }

  /** ��ʼ������ݹ����б��ͷ������ */
  public void setStockScale(ScaleKeyInfo keyInfo,
      StockScaleKeyInfo stockScaleKeyInfo) {
    EstScaleUtil util1 =
        new EstScaleUtil(this.getBillListPanel(), keyInfo, WorkbenchEnvironment
            .getInstance().getGroupVO().getPk_group());
    EstScaleUtil util2 =
        new EstScaleUtil(this.getBillListPanel(), stockScaleKeyInfo,
            WorkbenchEnvironment.getInstance().getGroupVO().getPk_group());
    util1.initGoodsScale();
    util2.initStockScale();
  }

  private BillCardPanel getBillCardPanel() {
    return this.editor.getBillCardPanel();
  }

  private BillListPanel getBillListPanel() {
    return this.editor.getBillListPanel();
  }

  private EstUIContext getContext() {
    return (EstUIContext) this.editor.getModel().getContext();
  }

  private void setFeeEstListEdit() {
    this.getBillListPanel().setEnabled(true);
    for (BillItem bi : this.getBillListPanel().getHeadBillModel()
        .getBodyItems()) {
      if (!PurchaseInEstHeaderVO.ONEBILLSELECT.equals(bi.getKey())) {
        bi.setEnabled(false);
      }
    }
  }

}
