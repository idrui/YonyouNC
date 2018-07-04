/**
 * $文件说明$
 * 
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-14 下午04:35:31
 */
package nc.ui.pu.m21.action.orderclose;

import java.awt.event.ActionEvent;

import nc.ui.pu.m21.view.OrderCloseListView;
import nc.ui.uif2.NCAction;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>填充未完量:
 * <li>到货未完量=订单数量-已到货数量
 * <li>入库未完量=订单数量-已入库数量
 * <li>开票未完量=订单数量-已开票数量
 * <li>未完金额=价税合计-已付款金额
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-4-14 下午04:35:31
 */
public class AfterOrderCloseAction extends NCAction {

  private static final long serialVersionUID = 1789118313838267624L;

  protected OrderCloseListView list = null;

  public Integer[] convertOtoInt(Object[] obj) {

    Integer ints[] = new Integer[obj.length];

    for (int num = 0; num < obj.length; num++) {
      ints[num] = Integer.getInteger(obj[num].toString());
    }

    return ints;

  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    return;
  }

  public OrderCloseListView getList() {
    return this.list;
  }

  public void setList(OrderCloseListView list) {
    this.list = list;
  }

  // 设置采购订单关闭中未完量的值
  public void setUnfinishedValues(Integer[] indexs) {
    if (ArrayUtils.isEmpty(indexs)) {
      return;
    }
    return;

    // BillListPanel cardPanel = list.getBillListPanel();
    //
    // for (int row = 0; row < indexs.length; row++) {
    //
    // // --------------------- 到货未完量
    //
    // // 获取单据数量
    // UFDouble nnum = (UFDouble) cardPanel.getHeadBillModel().getValueAt(
    // indexs[row], OrderItemVO.NNUM);
    //
    // // 获取已到货数量
    // UFDouble naccumarrvnum = (UFDouble) cardPanel.getHeadBillModel()
    // .getValueAt(indexs[row], OrderItemVO.NACCUMARRVNUM);
    //
    // // 设定未到货数量
    // if (!StringUtil.isEmptyWithTrim(nnum)
    // && !StringUtil.isEmptyWithTrim(naccumarrvnum)) {
    // UFDouble naccumnoarrvnum = nnum.sub(naccumarrvnum);
    // cardPanel.getHeadBillModel().setValueAt(naccumnoarrvnum, indexs[row],
    // "naccumnoarrvnum");
    // }
    //
    // // --------------------- 入库未完量
    //
    // // 获取已入库数量
    // UFDouble naccumstorenum = (UFDouble) cardPanel.getHeadBillModel()
    // .getValueAt(indexs[row], OrderItemVO.NACCUMSTORENUM);
    //
    // // 设定未入库数量
    // if (!StringUtil.isEmptyWithTrim(nnum)
    // && !StringUtil.isEmptyWithTrim(naccumstorenum)) {
    // UFDouble nnocaninnum = nnum.sub(naccumstorenum);
    // cardPanel.getHeadBillModel().setValueAt(nnocaninnum, indexs[row],
    // "nnocaninnum");
    // }
    //
    // // --------------------- 开票未完量
    //
    // // 获取已入库数量
    // UFDouble naccuminvoicenum = (UFDouble) cardPanel.getHeadBillModel()
    // .getValueAt(indexs[row], OrderItemVO.NACCUMINVOICENUM);
    //
    // // 设定未开票数量
    // if (!StringUtil.isEmptyWithTrim(nnum)
    // && !StringUtil.isEmptyWithTrim(naccuminvoicenum)) {
    // UFDouble nnocaninvoicenum = nnum.sub(naccuminvoicenum);
    // cardPanel.getHeadBillModel().setValueAt(nnocaninvoicenum, indexs[row],
    // "nnocaninvoicenum");
    // }
    //
    // // --------------------- 未完金额
    //
    // // 获取价税合计
    // UFDouble norigtaxmny = (UFDouble) cardPanel.getHeadBillModel()
    // .getValueAt(indexs[row], OrderItemVO.NORIGTAXMNY);
    //
    // // 获取已付款金额
    // UFDouble naccumpayorgmny = (UFDouble) cardPanel.getHeadBillModel()
    // .getValueAt(indexs[row], OrderItemVO.NACCUMPAYORGMNY);
    //
    // // 设定未完成金额
    // if (!StringUtil.isEmptyWithTrim(norigtaxmny)
    // && !StringUtil.isEmptyWithTrim(naccumpayorgmny)) {
    // UFDouble nnoaccumpayorgmny = norigtaxmny.sub(naccumpayorgmny);
    // cardPanel.getHeadBillModel().setValueAt(nnoaccumpayorgmny, indexs[row],
    // "nnoaccumpayorgmny");
    // }
    //
    // }

  }

}
