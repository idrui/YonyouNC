/**
 * $�ļ�˵��$
 *
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-17 ����04:36:50
 */
package nc.ui.pu.m21.action;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import nc.ui.pubapp.uif2app.actions.RefreshAction;
import nc.ui.uif2.model.BillManageModel;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��;״̬ˢ��
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-8-17 ����04:36:50
 */
public class OnwayRefreshAction extends RefreshAction {

  private static final long serialVersionUID = 8930578340762362255L;

  public OnwayRefreshAction() {
    super();
    this.putValue(Action.SHORT_DESCRIPTION, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0","04004030-0026")/*@res "ˢ��(F5)"*/);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    int nRowNum = ((BillManageModel) this.getModel()).getSelectedRow();
    super.doAction(e);
    int num = ((BillManageModel) this.getModel()).getRowCount();
    if (nRowNum >= num) {
      nRowNum--;
    }
    ((BillManageModel) this.getModel()).setSelectedRow(nRowNum);
  }
}