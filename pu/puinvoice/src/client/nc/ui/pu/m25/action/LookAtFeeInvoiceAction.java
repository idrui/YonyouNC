/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-14 ����11:02:57
 */
package nc.ui.pu.m25.action;

import java.awt.event.ActionEvent;

import nc.ui.uif2.NCAction;

import com.mxgraph.view.mxGraph;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������÷�Ʊ����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-4-14 ����11:02:57
 */
public class LookAtFeeInvoiceAction extends NCAction {

  /**
   * 
   */
  private static final long serialVersionUID = 223657034447840432L;

  /**
   * ���෽����д
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    mxGraph graph = new mxGraph();
    graph.setCellsEditable(false);
  }

}
