/**
 * $文件说明$
 * 
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-24 下午02:49:24
 */
package nc.ui.pu.m21.view;

import nc.bs.logging.Logger;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.uif2.editor.BillListView.IBillListPanelValueSetter;
import nc.vo.pub.CircularlyAccessibleValueObject;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>订单关闭赋值策略
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-6-24 下午02:49:24
 */
public class OrderCloseBillValueSetter implements IBillListPanelValueSetter {

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.editor.BillListView.IBillListPanelValueSetter#setBodyData(nc.ui.pub.bill.BillListPanel,
   *      java.lang.Object)
   */
  @Override
  // 设置表体值
  public void setBodyData(BillListPanel listPanel, Object selectedData) {
    return;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.editor.BillListView.IBillListPanelValueSetter#setHeaderDatas(nc.ui.pub.bill.BillListPanel,
   *      java.lang.Object[])
   */
  @Override
  // 设置表头值
  public void setHeaderDatas(BillListPanel listPanel, Object[] allDatas) {
    CircularlyAccessibleValueObject[] objs = this.getObjs(allDatas);
    listPanel.getHeadBillModel().setBodyDataVO(objs);
    listPanel.getHeadBillModel().loadLoadRelationItemValue();

  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.editor.BillListView.IBillListPanelValueSetter#setHeaderRowData(nc.ui.pub.bill.BillListPanel,
   *      java.lang.Object, int)
   */
  @Override
  // 设置表头特定行的值
  public void setHeaderRowData(BillListPanel listPanel, Object rowData, int row) {
    if (rowData == null) {
      Logger.debug("OrderCloseBillValueSetter.setHeaderRowData. 设置第" + row
          + "行的值为null. 忽略该操作");
    }
    else {
      BillModel headModel = listPanel.getBillListData().getHeadBillModel();
      if (headModel != null) {
        headModel
            .setBodyRowVO(((CircularlyAccessibleValueObject) rowData), row);
        // 处理参照信息
        if (listPanel.getBillListData().isMeataDataTemplate()) {
          BillItem[] items = headModel.getBodyItems();
          if ((items != null) && (items.length > 0)) {
            for (BillItem item : items) {
              headModel.loadLoadRelationItemValue(row, item.getKey());
            }
          }
        }
        // 处理装载公式
        headModel.execLoadFormulaByRow(row);
      }
    }

  }

  private CircularlyAccessibleValueObject[] getObjs(Object[] object) {
    CircularlyAccessibleValueObject[] objs =
        new CircularlyAccessibleValueObject[object.length];
    for (int i = 0; i < object.length; i++) {
      objs[i] = (CircularlyAccessibleValueObject) object[i];
    }
    return objs;
  }

}
