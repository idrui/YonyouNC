/**
 * $文件说明$
 *
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-9 下午04:07:02
 */
package nc.ui.pu.m21.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import nc.ui.pu.m21.rule.OrderScaleSetter;
import nc.ui.pub.beans.table.ColumnGroup;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.IBillModelDecimalListener;
import nc.ui.pubapp.ClientContext;
import nc.ui.pubapp.scale.BizDecimalListener;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.pubapp.uif2app.view.util.BillPanelUtils;
import nc.vo.pu.m21.entity.OrderItemVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-4-9 下午04:07:02
 */
public class OrderCloseListView extends ShowUpableBillListView {

  /**
   *
   */
  private static final long serialVersionUID = -4021084463850596934L;

  private IBillListPanelValueSetter billListPanelValueSetter;

  private Hashtable<String, TableColumn> hShowCol = null; // 显示列

  @Override
  public IBillListPanelValueSetter getBillListPanelValueSetter() {
    return this.billListPanelValueSetter;
  }

  public TableColumn getShowCol(String strKey) {
    if (this.hShowCol == null) {
      return null;
    }
    if (this.hShowCol.containsKey(strKey)) {
      return this.hShowCol.get(strKey);
    }
    return null;
  }

  public void initTable() {
    BillItem[] items = null;
    if (this.getBillListPanel() != null
        && this.getBillListPanel().getHeadBillModel() != null) {
      items = this.getBillListPanel().getHeadBillModel().getBodyItems();
    }
    if (items == null) {
      return;
    }

    TableColumnModel cm =
        this.getBillListPanel().getHeadTable().getColumnModel();
    this.hShowCol = new Hashtable<String, TableColumn>();

    BillItem item = null;
    TableColumn tclCol = null;

    Map<String, BillItem> map = new HashMap<String, BillItem>();
    for (int i = 0; i < items.length; i++) {
      String key = items[i].getName();
      if (null == map.get(key)) {
        map.put(key, items[i]);
      }
    }

    // 按表结构来设置hShowCol
    for (int i = 0; i < cm.getColumnCount(); i++) {
      tclCol = cm.getColumn(i);
      if (null != map.get(tclCol.getHeaderValue())) {
        item = map.get(tclCol.getHeaderValue());
        if (OrderItemVO.NCANARRIVENUM.equals(item.getKey())
            || OrderItemVO.NCANINNUM.equals(item.getKey())
            || OrderItemVO.NCANINVOICENUM.equals(item.getKey())) {
          if (null == this.hShowCol.get(OrderItemVO.NCANARRIVENUM)) {
            this.hShowCol.put(OrderItemVO.NCANARRIVENUM, tclCol);
          }
          else if (null == this.hShowCol.get(OrderItemVO.NCANINNUM)) {
            this.hShowCol.put(OrderItemVO.NCANINNUM, tclCol);
          }
          else {
            this.hShowCol.put(OrderItemVO.NCANINVOICENUM, tclCol);
          }
        }
        else {
          this.hShowCol.put(item.getKey(), tclCol);
        }
      }
    }
  }

  @Override
  public void initUI() {
    super.initUI();

    this.initTable();

    this.initMultiHeadItems();
    // 初始化精度处理
    this.initScale();

  }

  @Override
  public void setBillListPanelValueSetter(
      IBillListPanelValueSetter billListPanelValueSetter) {
    super.setBillListPanelValueSetter(billListPanelValueSetter);
    this.billListPanelValueSetter = billListPanelValueSetter;
  }

  /**
   * 去掉ScmBizDecimalListener监听
   */
  private void clearItemDecialListener() {
    BillItem[] items =
        this.getBillListPanel().getHeadBillModel().getBodyItems();
    for (int i = 0; i < items.length; i++) {
      IBillModelDecimalListener listener = items[i].getDecimalListener();
      if (listener instanceof BizDecimalListener) {
        items[i].removeDecimalListener();
      }
    }
  }

  private void initMultiHeadItems() {

    Map<String, List<String>> map = new HashMap<String, List<String>>();

    // 到货状态
    List<String> arriveList = new ArrayList<String>();
    arriveList.add("naccumarrvnum");
    arriveList.add(OrderItemVO.NCANARRIVENUM);
    arriveList.add("barriveclose");
    map.put(
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
            "04004030-0094")/* @res "到货状态" */, arriveList);

    // 入库状态
    List<String> inList = new ArrayList<String>();
    inList.add("naccumstorenum");
    inList.add(OrderItemVO.NCANINNUM);
    inList.add("bstockclose");
    map.put(
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
            "04004030-0095")/* @res "入库状态" */, inList);

    // 开票状态
    List<String> invoiceList = new ArrayList<String>();
    invoiceList.add("naccuminvoicenum");
    invoiceList.add(OrderItemVO.NCANINVOICENUM);
    invoiceList.add("binvoiceclose");
    map.put(
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
            "04004030-0096")/* @res "开票状态" */, invoiceList);

    // 付款状态
    List<String> payList = new ArrayList<String>();
    payList.add(OrderItemVO.NACCCANCELINVMNY);
    payList.add(OrderItemVO.NNOPAYORGMNY);
    payList.add("bpayclose");
    map.put(
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
            "04004030-0097")/* @res "付款状态" */, payList);

    List<ColumnGroup> cgList = new ArrayList<ColumnGroup>();

    ColumnGroup cg = null;
    TableColumn tableColumn = null;

    for (Map.Entry<String, List<String>> entry : map.entrySet()) {
      cg = new ColumnGroup(entry.getKey());
      for (String itemkey : entry.getValue()) {
        tableColumn = this.hShowCol.get(itemkey);
        cg.add(tableColumn);
      }
      cgList.add(cg);
    }

    BillPanelUtils.createMultiHeadTable(this.getBillListPanel().getHeadTable(),
        cgList);

  }

  /**
   * 处理精度
   * 
   * @param pkOrg
   */
  private void initScale() {
    // 订单关闭自己的列表精度处理器
    new OrderScaleSetter(ClientContext.getInstance().getPk_group())
        .setSingleTableScale(this.getBillListPanel());
  }
}
