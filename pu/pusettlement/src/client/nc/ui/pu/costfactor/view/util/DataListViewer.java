/**
 * $�ļ�˵��$
 * 
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-23 ����04:26:59
 */
package nc.ui.pu.costfactor.view.util;

import javax.swing.JComponent;
import javax.swing.ListSelectionModel;

import nc.ui.pub.beans.UIList;
import nc.ui.trade.component.IListDataViewer;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-6-23 ����04:26:59
 */
public class DataListViewer extends UIList implements IListDataViewer {

  /**
   * 
   */
  private static final long serialVersionUID = 5729754286705370649L;

  public DataListViewer() {
    this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.trade.component.IListDataViewer#getViewComponent()
   */
  @Override
  public JComponent getViewComponent() {
    return this;
  }

}
