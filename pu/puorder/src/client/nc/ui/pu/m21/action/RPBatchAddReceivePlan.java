/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-13 下午12:22:37
 */
package nc.ui.pu.m21.action;

import nc.ui.uif2.UIState;
import nc.ui.uif2.actions.batch.BatchAddLineAction;
import nc.ui.uif2.model.BatchBillTableModel;

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
 * @author wuxla
 * @time 2010-4-13 下午12:22:37
 */
public class RPBatchAddReceivePlan extends BatchAddLineAction {

  private static final long serialVersionUID = -332283478356593925L;

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.actions.batch.AbstractLineOperateAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    BatchBillTableModel model = this.getModel();
    return model.getUiState() == UIState.EDIT;
  }
}
