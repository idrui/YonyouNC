/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version
 * @see
 * @since
 * @time 2009-5-12 下午02:58:44
 */
package nc.ui.pu.costfactor.view;

import javax.swing.JSplitPane;

import nc.itf.org.IOrgConst;
import nc.itf.scmpub.reference.uap.org.FinanceOrgPubService;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.itf.scmpub.reference.uap.setting.defaultdata.DefaultDataSettingAccessor;
import nc.ui.pu.costfactor.editor.list.afteredit.rule.CalFactorOrder;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.view.BillListEditor;
import nc.ui.uif2.UIState;
import nc.vo.bd.ref.IFilterStrategy;
import nc.vo.pu.costfactor.entity.CostfactorHeaderVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 成本要素定义列表视图
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>功能条目1
 * <li>功能条目2
 * <li>...
 * </ul>
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version v60
 * @since v60
 * @author zhaoyha
 * @time 2009-5-12 下午02:58:44
 */
public class CostFactorListEditor extends BillListEditor {

  /**
     * 
     */
  private static final long serialVersionUID = 6037585702496065881L;

  /**
   * 重写行改变事件. 当在编辑和增加状态时选择行改变时，不进行处理
   */
  @Override
  public void bodyRowChange(BillEditEvent e) {

    if (this.getModel().getUiState() == UIState.ADD
        || this.getModel().getUiState() == UIState.EDIT) {
      return;
    }
    super.bodyRowChange(e);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.editor.BillListView#initUI()
   */
  @Override
  public void initUI() {
    super.initUI();
    // 左右形式(垂直分割)
    /*this.getBillListPanel().setOrientation(JSplitPane.HORIZONTAL_SPLIT);
    this.getBillListPanel().getUISplitPane().setDividerLocation(0.5);*/
    this.initRefPanel();

  }

  @Override
  public void onAdd() {
    super.onAdd();
    try {
      String pk_group = this.getModel().getContext().getPk_group();
      // 取个性化中心的组织，如果是财务组织，增行时候作为默认值
      String defaultOrg = DefaultDataSettingAccessor.getDefaultOrgUnit();
      int curRow = this.getBillListPanel().getHeadBillModel().getRowCount() - 1;
      if (StringUtils.isNotEmpty(defaultOrg)) {
        // 判断是否财务组织
        if (OrgUnitPubService.isTypeOf(defaultOrg, IOrgConst.FINANCEORGTYPE)) {
          // 组织不可见
          if (!this.isPermissionOrg(defaultOrg)
              || !FinanceOrgPubService.isEnable(defaultOrg).booleanValue()) {
            return;
          }
          this.getBillListPanel().getHeadBillModel()
              .setValueAt(defaultOrg, curRow, CostfactorHeaderVO.PK_ORG);
          this.getBillListPanel().getHeadBillModel()
              .loadEditRelationItemValue(curRow, CostfactorHeaderVO.PK_ORG);
          new CalFactorOrder().setOrder(defaultOrg, curRow,
              this.getBillListPanel());// 设置成本要素序号
        }
      }
      this.getBillListPanel().getHeadBillModel()
          .setValueAt(pk_group, curRow, CostfactorHeaderVO.PK_GROUP);
      this.getBillListPanel().getHeadBillModel().loadLoadRelationItemValue();
      // 只允许编辑当前新增行
      this.setHeadEditRow(curRow);
    }
    catch (Exception e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 方法功能描述：updateAction执行时调用。
   * <p>
   * <b>examples:</b>
   * <p>
   * 使用示例
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @author zhaoyha
   * @time 2009-5-18 下午03:45:50
   */
  @Override
  public void onEdit() {
    super.onEdit();

    int[] selectedRows =
        this.getBillListPanel().getHeadTable().getSelectedRows();
    if (selectedRows != null && selectedRows.length == 1) {
      this.setHeadEditRow(selectedRows[0]);
    }
    for (int i = 0; i < this.getBillListPanel().getHeadBillModel()
        .getRowCount(); i++) {
      this.getBillListPanel().getHeadBillModel()
          .setCellEditable(i, CostfactorHeaderVO.PK_ORG, false);
    }
  }

  @Override
  public void onSave() throws Exception {
    Object value = this.getValue();
    if (value == null) {
      return;
    }

    if (this.getModel().getUiState() == UIState.ADD) {
      this.getModel().add(value);
    }
    if (this.getModel().getUiState() == UIState.EDIT) {
      this.getModel().update(value);
    }
    this.getModel().setUiState(UIState.NOT_EDIT);
    this.getBillListPanel().setEnabled(false);
  }

  /**
   * 设置当前选择行为可编辑。 表头：当前行，可编辑的字段可以编辑；其余行，均不能编辑
   * 
   * @param row 当前选择行
   */
  public void setHeadEditRow(int row) {
    BillModel bm_head = this.getBillListPanel().getHeadBillModel();
    BillItem[] bi_head = bm_head.getBodyItems();
    for (int i = 0, len = bi_head.length; i < len; i++) {
      if (bi_head[i].isShow()) {
        for (int j = 0, len2 = bm_head.getRowCount(); j < len2; j++) {
          bm_head.setCellEditable(j, bi_head[i].getKey(), false);
        }
      }
    }

    if (row > -1 && row < bm_head.getRowCount()) {
      for (int i = 0, len = bi_head.length; i < len; i++) {
        if (bi_head[i].isShow() && bi_head[i].isEdit()) {
          bm_head.setCellEditable(row, bi_head[i].getKey(), true);
        }
      }
    }
  }

  /**
   * 参照初始化
   */
  private void initRefPanel() {
    UIRefPane panel =
        (UIRefPane) this.getBillListPanel().getHeadBillModel()
            .getItemByKey(CostfactorHeaderVO.PK_ORG).getComponent();
    // 财务组织过滤
    panel.getRefModel().setFilterPks(
        this.getModel().getContext().getFuncInfo().getFuncPermissionPkorgs(),
        IFilterStrategy.INSECTION);
  }

  /**
   * 财务组织是否可见
   * 
   * @param pk_org
   * @return
   */
  private boolean isPermissionOrg(String pk_org) {
    String[] orgs =
        this.getModel().getContext().getFuncInfo().getFuncPermissionPkorgs();
    if (ArrayUtils.isEmpty(orgs)) {
      return false;
    }
    for (String org : orgs) {
      if (org.equals(pk_org)) {
        return true;
      }
    }
    return false;
  }
}
