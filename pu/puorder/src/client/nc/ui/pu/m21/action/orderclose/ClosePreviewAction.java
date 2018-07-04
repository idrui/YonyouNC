/**
 * $文件说明$
 *
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-12 下午02:27:17
 */
package nc.ui.pu.m21.action.orderclose;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import nc.funcnode.ui.action.INCAction;
import nc.ui.uif2.NCAction;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单预览
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-4-12 下午02:27:17
 */
public class ClosePreviewAction extends NCAction {

  /**
   *
   */
  private static final long serialVersionUID = -2263889216674435468L;

  public ClosePreviewAction() {

    String name = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0","04004030-0025")/*@res "打印预览"*/;
    this.setBtnName(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common","UC001-0000112")/*@res "预览"*/);
    this.putValue(INCAction.CODE, name);
    this.putValue(Action.SHORT_DESCRIPTION, name);
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

  @Override
  protected boolean isActionEnable() {
    return true;
  }

}