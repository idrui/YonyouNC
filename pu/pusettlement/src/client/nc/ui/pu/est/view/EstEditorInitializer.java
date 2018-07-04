/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-19 下午03:28:01
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
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>负责界面的一些初始化工作
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-19 下午03:28:01
 */
public class EstEditorInitializer {
  private EstEditor editor;

  /**
   * EstEditorInitiaizer 的构造子
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

  /** 初始化费用暂估(表体)精度 **/
  public void setFeeScale(ScaleKeyInfo keyInfo) {
    EstScaleUtil util =
        new EstScaleUtil(this.getBillCardPanel(), keyInfo, WorkbenchEnvironment
            .getInstance().getGroupVO().getPk_group());
    util.initFeeScale();
  }

  /** 初始化货物暂估（列表表头）精度 **/
  public void setGoodsScale(ScaleKeyInfo keyInfo) {
    EstScaleUtil util =
        new EstScaleUtil(this.getBillListPanel(), keyInfo, WorkbenchEnvironment
            .getInstance().getGroupVO().getPk_group());
    util.initGoodsScale();
  }

  /** 初始化入库暂估（列表表头）精度 */
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
