/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-18 上午10:26:58
 */
package nc.ui.pu.est.model;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.uif2.editor.BillListView.VOBillListPanelValueSetter;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.CircularlyAccessibleValueObject;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>将暂估VO设置到列表模板处理器
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-18 上午10:26:58
 */
public class EstVOBillListPanelValueSetter extends VOBillListPanelValueSetter {
  private BillCardPanel billcardpanel;

  /**
   * @return billcardpanel
   */
  public BillCardPanel getBillcardpanel() {
    return this.billcardpanel;
  }

  /**
   * @param billcardpanel 要设置的 billcardpanel
   */
  public void setBillcardpanel(BillCardPanel billcardpanel) {
    this.billcardpanel = billcardpanel;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.editor.BillListView.VOBillListPanelValueSetter#setBodyData(nc.ui.pub.bill.BillListPanel,
   *      java.lang.Object)
   */
  @Override
  public void setBodyData(BillListPanel listPanel, Object selectedData) {
    if (null == this.billcardpanel) {
      super.setBodyData(listPanel, selectedData);
    }
    else {
      if (selectedData instanceof AggregatedValueObject) {
        BillModel bm = this.billcardpanel.getBillModel();
        CircularlyAccessibleValueObject[] items =
            ((AggregatedValueObject) selectedData).getChildrenVO();
        bm.setBodyDataVO(items);
        bm.loadLoadRelationItemValue();
        bm.execLoadFormula();
        bm.updateValue();
        this.setEstFeeEditable();
      }
      else {
        this.billcardpanel.getBillData().setBodyValueVO(null);
      }
    }
  }

  private String getPkName() {
    BillModel bm = this.getBillcardpanel().getBillModel();
    return bm.getTabvo().getBillMetaDataBusinessEntity().getKeyAttribute()
        .getName();
  }

  /**
   * 方法功能描述：设置已经暂估过的费用行可编辑性。
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author zhyhang
   * @time 2010-6-14 下午04:45:28
   */
  private void setEstFeeEditable() {
    BillModel bm = this.getBillcardpanel().getBillModel();
    String pkname = this.getPkName();
    for (int i = 0; i < bm.getRowCount(); ++i) {
      String pk = (String) bm.getValueAt(i, pkname);
      if (!StringUtil.isEmptyWithTrim(pk)) {
        for (BillItem bi : bm.getBodyItems()) {
          bm.setCellEditable(i, bi.getKey(), false);
        }
      }
    }
  }

}
