/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-18 ����10:26:58
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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���ݹ�VO���õ��б�ģ�崦����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-18 ����10:26:58
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
   * @param billcardpanel Ҫ���õ� billcardpanel
   */
  public void setBillcardpanel(BillCardPanel billcardpanel) {
    this.billcardpanel = billcardpanel;
  }

  /**
   * ���෽����д
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
   * �������������������Ѿ��ݹ����ķ����пɱ༭�ԡ�
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @since 6.0
   * @author zhyhang
   * @time 2010-6-14 ����04:45:28
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
