/**
 * $文件说明$
 *
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-26 上午08:52:47
 */
package nc.ui.pu.m21.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.ui.pu.m21.rule.RelationCalculateAfterQuoter;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.UIState;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.rule.PriceQuoter;
import nc.vo.pu.pub.util.ObjectUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.SCMActionCode;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购询价action
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-10-26 上午08:52:47
 */
public class QueryDefaultPriceAction extends NCAction {

  private static final long serialVersionUID = 6597643824619581836L;

  private ShowUpableBillForm billForm;

  private ShowUpableBillForm billForm_Add;

  public QueryDefaultPriceAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.PU_QUOTEPOPRICE);
    // String str = "采购询价";
    // this.putValue(INCAction.CODE, str);
    // this.setBtnName(str);
    // this.putValue(Action.SHORT_DESCRIPTION, str);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    ShowUpableBillForm showupBillForm = null;
    if (this.billForm_Add != null && this.billForm_Add.isComponentVisible()) {
      showupBillForm = this.billForm_Add;
    }
    else if (this.billForm != null && this.billForm.isComponentVisible()) {
      showupBillForm = this.billForm;
    }
    if (null == showupBillForm) {
      return;
    }
    this.setDefaultPrice(showupBillForm);
    // cardPanel.updateValue();
  }

  /**
   * @return billForm
   */
  public ShowUpableBillForm getBillForm() {
    return this.billForm;
  }

  public ShowUpableBillForm getBillForm_Add() {
    return this.billForm_Add;
  }

  /**
   * @param billForm
   *          要设置的 billForm
   */
  public void setBillForm(ShowUpableBillForm billForm) {
    this.billForm = billForm;
    billForm.getModel().addAppEventListener(this);
  }

  public void setBillForm_Add(ShowUpableBillForm billForm_Add) {
    this.billForm_Add = billForm_Add;
  }

  /**
   * 方法功能描述：过滤掉关联到合同的项
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author wanghuid
   * @time 2010-10-26 下午01:10:58
   */
  private Integer[] notContractFilter(BillCardPanel panel) {
    int[] rows = panel.getBillTable().getSelectedRows();
    if (ArrayUtils.isEmpty(rows)) {
      return null;
    }
    List<Integer> intList = new ArrayList<Integer>();
    for (int row : rows) {
      Object pk_material = panel.getBodyValueAt(row, OrderItemVO.PK_MATERIAL);
      if (ObjectUtil.isEmptyWithTrim(pk_material)) {
        continue;
      }
      Object obj = panel.getBodyValueAt(row, OrderItemVO.CCONTRACTROWID);
      if (obj == null || StringUtils.isEmpty(obj.toString())) {
        intList.add(Integer.valueOf(row));
      }
      else {
        Object price = panel.getBodyValueAt(row, OrderItemVO.NQTORIGTAXPRICE);
        if (price == null || StringUtils.isEmpty(price.toString())) {
          intList.add(Integer.valueOf(row));
        }
      }
    }

    return intList.toArray(new Integer[intList.size()]);
  }

  /**
   * 方法功能描述：询价
   * <p>
   * <b>参数说明</b>
   * 
   * @param rows
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-10-26 上午08:58:39
   */
  private void setDefaultPrice(ShowUpableBillForm form) {
    BillCardPanel cardPanel = form.getBillCardPanel();
    CardEditorHelper editor = new CardEditorHelper(cardPanel);
    PriceQuoter quoter = new PriceQuoter(editor);
    Integer[] rows = this.notContractFilter(cardPanel);
    if (ArrayUtils.isEmpty(rows)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0056")/*
                                                                   * @res
                                                                   * "能询价的行为空"
                                                                   */);
    }
    // AssertUtils.assertValue(!ArrayUtils.isEmpty(rows), "选择的行不应该为空");

    Map<Integer, String> map = quoter.setDefaultPrice(rows);
    if (null == map || 0 == map.size()) {
      return;
    }
    RelationCalculateAfterQuoter tool =
        new RelationCalculateAfterQuoter(cardPanel);
    tool.relationCalculate(map);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    if (this.getBillForm().getModel().getUiState() != UIState.EDIT
        && this.getBillForm().getModel().getUiState() != UIState.ADD) {
      return false;
    }
    // Integer[] row = this.notContractFilter();
    // if (ArrayUtils.isEmpty(row)) {
    // return false;
    // }
    return true;
  }
}
