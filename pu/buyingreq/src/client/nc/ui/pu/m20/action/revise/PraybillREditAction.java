package nc.ui.pu.m20.action.revise;

import java.awt.event.ActionEvent;

import nc.ui.pu.pub.action.PUReviseAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.editor.BillListView;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�빺���ı༭��ť������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-3-5 ����10:45:07
 */
public class PraybillREditAction extends PUReviseAction {
  private static final long serialVersionUID = -116097492156325868L;

  private BillListView list;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    super.doAction(e);
    ShowStatusBarMsgUtil.showStatusBarMsg(
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004020_0",
            "04004020-0001")/* @res "�����޶�" */, this.getModel().getContext());
  }

  /**
   * @param list
   *          Ҫ���õ� list
   */
  public void setList(BillListView list) {
    this.list = list;
  }

  @Override
  protected boolean isActionEnable() {

    int selectRowCount =
        this.list.getBillListPanel().getHeadTable().getSelectedRowCount();
    // �޶�
    if (selectRowCount == 1) {
      return true;
    }
    return false;

  }
}
