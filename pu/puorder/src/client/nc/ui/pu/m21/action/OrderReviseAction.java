/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-11-1 上午09:54:30
 */
package nc.ui.pu.m21.action;

import java.awt.event.ActionEvent;

import nc.ui.pu.pub.action.PUReviseAction;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.BillManageModel;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pubapp.AppContext;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>修订按钮
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-11-1 上午09:54:30
 */
public class OrderReviseAction extends PUReviseAction {

  private static final long serialVersionUID = 2397544874618256275L;

  private BillForm editor;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    super.doAction(e);
    this.setDefaultReviseItems();
  }

  /**
   * @return editor
   */
  public BillForm getEditor() {
    return this.editor;
  }

  /**
   * @param editor
   *          要设置的 editor
   */
  public void setEditor(BillForm editor) {
    this.editor = editor;
  }

  /**
   * 方法功能描述：设置修订订单的默认值。
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-3-21 上午09:47:09
   */
  private void setDefaultReviseItems() {
    BillCardPanel bcp = this.getEditor().getBillCardPanel();
    // 修订人
    bcp.setTailItem(OrderHeaderVO.CREVISEPSN, AppContext.getInstance()
        .getPkUser());
    // 修订日期
    bcp.setTailItem(OrderHeaderVO.TREVISIONTIME, AppContext.getInstance()
        .getBusiDate());
    // 版本+1
    bcp.setHeadItem(OrderHeaderVO.NVERSION, Integer.valueOf(((Integer) bcp
        .getHeadItem(OrderHeaderVO.NVERSION).getValueObject()).intValue() + 1));
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.actions.EditAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    if (this.getModel().getUiState() != UIState.NOT_EDIT) {
      return false;
    }

    Object[] objs = ((BillManageModel) this.getModel()).getSelectedOperaDatas();
    if (objs != null && objs.length > 1) {
      return false;
    }

    OrderVO order = (OrderVO) this.getModel().getSelectedData();
    return order != null;
  }
}
