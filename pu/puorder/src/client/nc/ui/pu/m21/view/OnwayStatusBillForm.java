/**
 * $文件说明$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-6 上午09:21:07
 */
package nc.ui.pu.m21.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.ui.pu.m21.action.direction.OrderFirstLineAction;
import nc.ui.pu.m21.action.direction.OrderLastLineAction;
import nc.ui.pu.m21.action.direction.OrderNextLineAction;
import nc.ui.pu.m21.action.direction.OrderPreLineAction;
import nc.ui.pu.m21.rule.OnwayScaleSetter;
import nc.ui.pub.bill.BillModel;
import nc.ui.pub.bill.BillScrollPane;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.model.BillManageModel;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.tool.BillIndex;
import nc.vo.pubapp.pattern.pub.Constructor;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单维护状态卡片视图
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-8-6 上午09:21:07
 */
public class OnwayStatusBillForm extends ShowUpableBillForm {

  /**
   * 
   */
  private static final long serialVersionUID = 5717296578627596037L;

  private List<Action> pageActions = null;

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pubapp.uif2app.view.ShowUpableBillForm#getActions()
   */
  @Override
  public List<Action> getActions() {
    if (null == this.pageActions && this.getModel() instanceof BillManageModel) {
      this.pageActions = this.getEditorActions();
    }
    return this.pageActions;
  }

  // 获取表体选择行VO
  // @SuppressWarnings("deprecation")
  // 元素据的字段
  public ISuperVO[] getBodySelectedVOs() {
    if (null == this.getValue()) {
      return null;
    }
    if (ArrayUtils.isEmpty(this.getSelectedRows())) {
      return null;
    }
    // Map<String, SuperVO> itemMap = AggVOUtil.createItemMap(new AbstractBill[]
    // {
    // ((AbstractBill) this.getValue())
    // });
    BillIndex index = new BillIndex(new AbstractBill[] {
      ((AbstractBill) this.getValue())
    });
    SuperVO[] vos =
        (SuperVO[]) ((AbstractBill) this.getValue()).getChildrenVO();
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }
    SuperVO[] itemVOs =
        Constructor.construct(vos[0].getClass(), this.getSelectedRows().length);
    if (ArrayUtils.isEmpty(itemVOs)) {
      return null;
    }
    IVOMeta meta = itemVOs[0].getMetaData();
    String pk_item = meta.getPrimaryAttribute().getName();
    // itemVOs[0].getPKFieldName();
    for (int i = 0; i < this.getSelectedRows().length; ++i) {
      String pk_Order_b =
          (String) this.getBillCardPanel().getBillModel()
              .getValueAt(this.getSelectedRows()[i], pk_item);
      // SuperVO itemVO = itemMap.get(pk_Order_b);
      SuperVO itemVO = (SuperVO) index.get(meta, pk_Order_b);
      itemVOs[i] = itemVO;
    }
    return itemVOs;
  }

  public int[] getSelectedRows() {
    int[] selectedRows =
        this.getBillCardPanel().getBodyPanel().getTable().getSelectedRows();
    return selectedRows;

  }

  @Override
  public void initUI() {
    super.initUI();
    this.getBillCardPanel().setBodyMultiSelect(true);

    // 清除增行、删行等默认编辑菜单
    BillScrollPane billScrollPane =
        (BillScrollPane) this.getBillCardPanel().getSelectedScrollPane(1);
    billScrollPane.clearDefalutEditAction();
  }

  /**
   * 方法功能描述：设置本次发货金额
   * <p>
   * <b>参数说明</b>
   * 
   * @param bm
   * @param i
   * @param nnum
   *          主数量
   * @param num
   *          要比较的数量
   * @param item
   *          要设置的项
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-8-19 下午04:31:42
   */
  // private void setStatusMny(BillModel bm, int i, UFDouble nnum, UFDouble num,
  // String item) {
  // UFDouble nstatusmny = UFDouble.ZERO_DBL;
  // // 如果本次发货数量=主数量
  // if (num.equals(nnum)) {
  // // 设置本次发货金额为原币无税金额
  // nstatusmny =
  // ValueUtils.getUFDouble(
  // bm.getValueAt(i, OrderOnwayItemVO.NORIGMNY));
  //
  // }
  // // 如果本次发货数量不等于主数量,本次发货金额=要比较数量*主无税净价
  // else {
  // // 取得主无税净价
  // UFDouble norignetprice =
  // ValueUtils.getUFDouble(
  // bm.getValueAt(i, OrderOnwayItemVO.NORIGNETPRICE));
  // nstatusmny = num.multiply(norignetprice);
  //
  // }
  // bm.setValueAt(nstatusmny, i, item);
  // }

  @Override
  public boolean isComponentVisible() {
    return true;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pubapp.uif2app.view.ShowUpableBillForm#setValue(java.lang.Object)
   */
  @Override
  public void setValue(Object object) {

    // 设置精度
    String pk_group = InvocationInfoProxy.getInstance().getGroupId();
    new OnwayScaleSetter(pk_group).setCardScale(this.getBillCardPanel());

    if (this.isShowOrgPanel()) {
      this.getBillOrgPanel().setPkOrg(
          this.getBillOrgPanel().getOrgGetter().getPkOrg(object));
    }

    this.getBillCardPanel().getBillData().updateValue();
    super.setValue(object);

    // OrderOnwayVO vo = null;
    // if (null != object && object.getClass().isArray()) {
    // vo = (OrderOnwayVO) new Object[] {
    // object
    // }[0];
    // }
    // else if (null != object) {
    // vo = (OrderOnwayVO) object;
    // }
    // if (null == vo) {
    // return;
    // }

    BillModel bm = this.getBillCardPanel().getBillModel();
    // // OrderOnwayItemVO[] itemVOs = vo.getBVO();
    //
    // // 设置发货日期、本次发货金额
    // for (int i = 0; i < this.getBillCardPanel().getBillModel().getRowCount();
    // i++) {
    //
    // // 确认数量
    // UFDouble confirmNum =
    // ValueUtils.getUFDouble(
    // bm.getValueAt(i, OrderOnwayItemVO.NCONFIRMNUM));
    // // 累计数量
    // UFDouble naccunum =
    // ValueUtils.getUFDouble(
    // bm.getValueAt(i, OrderOnwayItemVO.NACCUNUM));
    //
    // // 本次发货数量 = 确认数量-累计数量
    // // UFDouble nsendoutnum = confirmNum.sub(naccunum);
    // UFDouble nsendoutnum = MathTool.sub(confirmNum, naccunum);
    //
    // // 设置本次发货数量
    // bm.setValueAt(nsendoutnum, i, OrderOnwayItemVO.NSENDOUTNUM);
    //
    // // 主数量
    // UFDouble nnum =
    // ValueUtils.getUFDouble(
    // bm.getValueAt(i, OrderOnwayItemVO.NNUM));
    // // 设置本次发货金额
    // this.setStatusMny(bm, i, nnum, nsendoutnum, OrderOnwayItemVO.NSTATUSMNY);
    //
    // // 设置已发货金额
    // // 已发货数量
    // UFDouble nonwaynum =
    // ValueUtils.getUFDouble(
    // bm.getValueAt(i, OrderOnwayItemVO.NONWAYNUM));
    // // 设置本次发货金额
    // if (nonwaynum == null) {
    // nonwaynum = UFDouble.ZERO_DBL;
    // }
    // this.setStatusMny(bm, i, nnum, nonwaynum, OrderOnwayItemVO.NONWAYMNY);
    // }

    // 加载公式以及相关项
    bm.loadLoadRelationItemValue();
    bm.execLoadFormula();
  }

  /**
   * 方法功能描述：公共代码没有控制在页面变化时失去焦点，重写使其失去焦点。
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-8-6 上午09:42:58
   */
  private List<Action> getEditorActions() {
    List<Action> actions = new ArrayList<Action>();
    BillManageModel billManageModel = (BillManageModel) this.getModel();

    OrderFirstLineAction firstLineAction = new OrderFirstLineAction();
    firstLineAction.setModel(billManageModel);
    firstLineAction.setEnabled(false);
    firstLineAction.setEditor(this.getBillCardPanel());
    actions.add(firstLineAction);

    OrderPreLineAction preLineAction = new OrderPreLineAction();
    preLineAction.setModel(billManageModel);
    preLineAction.setEnabled(false);
    preLineAction.setEditor(this.getBillCardPanel());
    actions.add(preLineAction);

    OrderNextLineAction nextLineAction = new OrderNextLineAction();
    nextLineAction.setModel(billManageModel);
    nextLineAction.setEnabled(false);
    nextLineAction.setEditor(this.getBillCardPanel());
    actions.add(nextLineAction);

    OrderLastLineAction lastLineAction = new OrderLastLineAction();
    lastLineAction.setModel(billManageModel);
    lastLineAction.setEnabled(false);
    lastLineAction.setEditor(this.getBillCardPanel());
    actions.add(lastLineAction);

    return actions;
  }
}
