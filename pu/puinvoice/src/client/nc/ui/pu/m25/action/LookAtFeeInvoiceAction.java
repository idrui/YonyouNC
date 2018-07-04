/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-14 上午11:02:57
 */
package nc.ui.pu.m25.action;

import java.awt.event.ActionEvent;

import nc.ui.uif2.NCAction;

import com.mxgraph.view.mxGraph;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>联查费用发票动作
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-4-14 上午11:02:57
 */
public class LookAtFeeInvoiceAction extends NCAction {

  /**
   * 
   */
  private static final long serialVersionUID = 223657034447840432L;

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    mxGraph graph = new mxGraph();
    graph.setCellsEditable(false);
  }

}
