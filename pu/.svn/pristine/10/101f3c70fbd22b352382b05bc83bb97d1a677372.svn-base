/**
 * $文件说明$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-6 下午03:51:05
 */
package nc.ui.pu.costfactor.action;

import java.awt.event.ActionEvent;

import nc.ui.pubapp.uif2app.actions.billlist.SaveAction;

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
 * @author tianft
 * @time 2010-8-6 下午03:51:05
 */
public class CostFactorSaveAction extends SaveAction {

  private static final long serialVersionUID = -3443860117344908946L;

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pubapp.uif2app.actions.billlist.SaveAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    Object value = this.getEditor().getValue();
    this.validate(value);
    super.doAction(e);
  }

}
