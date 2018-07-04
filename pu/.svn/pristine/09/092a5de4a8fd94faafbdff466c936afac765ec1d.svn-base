/**
 * $文件说明$
 * 
 * @author zhyhang
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-31 下午12:24:01
 */
package nc.ui.pu.pub.editor.card.handler;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>表头整单合计处理器
 * <li><b>注意,设计时已经考虑效率问题,但代码中还未实现.</b>
 * <li><i> 可能的效率点在多行批改时</i>
 * <li><i> 这时可以在这里设置一个标志由用户决定是否计算合计, 可参考#BillModel.isNeedCalculate()</i>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhyhang
 * @time 2010-1-31 下午12:24:01
 */
public class TotalValueHanlder implements TableModelListener {

  private BillCardPanel billCardPanel;

  private Map<String, String> bodyHeadMap;

  /** UI工厂2的model **/
  private BillManageModel model;

  public TotalValueHanlder() {
    // 缺省构造方法

  }

  /**
   * @return billCardPanel
   */
  public BillCardPanel getBillCardPanel() {
    return this.billCardPanel;
  }

  /**
   * @return bodyHeadMap
   */
  public Map<String, String> getBodyHeadMap() {
    return this.bodyHeadMap;
  }

  /**
   * @return model
   */
  public BillManageModel getModel() {
    return this.model;
  }

  /**
   * @param billCardPanel
   *          要设置的 billCardPanel
   */
  public void setBillCardPanel(BillCardPanel billCardPanel) {
    this.billCardPanel = billCardPanel;
  }

  /**
   * @param bodyHeadMap
   *          要设置的 bodyHeadMap
   */
  public void setBodyHeadMap(Map<String, String> bodyHeadMap) {
    this.bodyHeadMap = bodyHeadMap;
  }

  /**
   * @param model
   *          要设置的 model
   */
  public void setModel(BillManageModel model) {
    this.model = model;
  }

  @Override
  public void tableChanged(TableModelEvent e) {
    if ((null == this.getBodyHeadMap()) || (0 == this.getBodyHeadMap().size())) {
      return;
    }

    if ((AppUiState.EDIT != this.getModel().getAppUiState())
        && (AppUiState.ADD != this.getModel().getAppUiState())) {
      return;
    }

    // 这里如果出现效率问题,可以不用每次都全部重算一次
    // 只要将删除的行和新增的行加到合计或从合计减掉即可
    if (TableModelEvent.INSERT == e.getType()) {
      this.reCalAllInsert(e);
      return;
    }
    if (TableModelEvent.DELETE == e.getType()) {
      this.reCalAll();
      return;
    }
    if ((TableModelEvent.UPDATE == e.getType())
        && (TableModelEvent.ALL_COLUMNS == e.getColumn())) {
      return;// 整行都更新先不处理
    }

    BillModel model1 = this.getBillModel();
    BillItem item = model1.getBodyItems()[e.getColumn()];
    String changeName = item.getKey();
    Set<String> bodyNames = this.getBodyHeadMap().keySet();
    if (!bodyNames.contains(changeName)) {
      return;
    }
    this.calTotalValue(changeName, this.getBodyHeadMap().get(changeName));
  }

  protected void calTotalValue(String bodyName, String headName) {
    if (null == this.getBillCardPanel().getHeadItem(headName)) {
      return;
    }
    int rowCnt = this.getBillCardPanel().getRowCount();
    UFDouble total = UFDouble.ZERO_DBL;
    for (int i = 0; i < rowCnt; i++) {
      if (!this.isCalInclude(i)) {
        continue;
      }
      UFDouble bodyValue =
          (UFDouble) this.getBillCardPanel().getBodyValueAt(i, bodyName);
      total = MathTool.add(total, bodyValue);
    }
    this.getBillCardPanel().getHeadItem(headName).setValue(total);
  }

  protected BillModel getBillModel() {
    return this.getBillCardPanel().getBillModel();
  }

  /**
   * 方法功能描述：是否将这行包含在合计之中,赠品行可能需要。
   * <p>
   * <b>参数说明</b>
   * 
   * @param row
   * @return <p>
   * @since 6.0
   * @author zhyhang
   * @time 2010-1-31 下午10:03:21
   */
  protected boolean isCalInclude(int row) {
    if (row >= 0) {
      return true;
    }
    return false;
  }

  protected void reCalAll() {
    int rowCnt = this.getBillModel().getRowCount();
    Map<String, UFDouble> allTotal = new HashMap<String, UFDouble>();
    for (String bodyName : this.getBodyHeadMap().keySet()) {
      allTotal.put(this.getBodyHeadMap().get(bodyName), UFDouble.ZERO_DBL);
    }
    for (int i = 0; i < rowCnt; ++i) {
      if (!this.isCalInclude(i)) {
        continue;
      }
      for (String bodyName : this.getBodyHeadMap().keySet()) {
        String headName = this.getBodyHeadMap().get(bodyName);
        UFDouble total = allTotal.get(headName);
        UFDouble newValue =
            (UFDouble) this.getBillModel().getValueAt(i, bodyName);
        total = MathTool.add(total, newValue);
        allTotal.put(headName, total);
      }
    }
    Set<Entry<String, UFDouble>> headNames = allTotal.entrySet();
    for (Entry<String, UFDouble> headName : headNames) {
      UFDouble total = headName.getValue();
      this.getBillCardPanel().getHeadItem(headName.getKey()).setValue(total);
    }
  }

  protected void reCalAllInsert(TableModelEvent e) {
    boolean recalc = false;
    for (int row = e.getFirstRow(); (row <= e.getLastRow()) && !recalc; ++row) {
      for (String bodyName : this.getBodyHeadMap().keySet()) {
        UFDouble newValue =
            (UFDouble) this.getBillModel().getValueAt(row, bodyName);
        if ((null != newValue) && UFDouble.ZERO_DBL.equals(newValue)) {
          recalc = true;
          break;
        }
      }
    }
    if (!recalc) {
      return;
    }

    this.reCalAll();

  }

}
