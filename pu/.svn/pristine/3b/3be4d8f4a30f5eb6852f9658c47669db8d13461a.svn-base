/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-19 下午06:11:35
 */
package nc.ui.pu.m21.action;

import nc.ui.pubapp.uif2app.actions.batch.BatchEditAction;
import nc.ui.uif2.UIState;

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
 * @time 2010-4-19 下午06:11:35
 */
public class RPBatchEditAction extends BatchEditAction {

  private static final long serialVersionUID = 6597593305515473331L;

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.actions.batch.BatchEditAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    return this.getModel().getUiState() == UIState.NOT_EDIT;
  }
}
