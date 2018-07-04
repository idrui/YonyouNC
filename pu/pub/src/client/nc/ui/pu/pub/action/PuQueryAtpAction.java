package nc.ui.pu.pub.action;

import java.awt.event.ActionEvent;

import nc.ui.pu.pub.atp.ATPForOneMaterialMulOrgUI;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.editor.BillListView;
import nc.vo.pu.atp.ATPParamVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.SCMActionCode;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ� ������ѯAction����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-9-27 ����04:06:01
 */
public abstract class PuQueryAtpAction extends NCAction {

  private static final long serialVersionUID = -5771455814680977918L;

  /** ��Ƭ��ͼ **/
  private ShowUpableBillForm billForm;

  /** �б���� **/
  private BillListView list;

  private BillManageModel model;

  /** ������� **/
  private ATPForOneMaterialMulOrgUI onAtppane;

  /** Ĭ�Ϲ����� **/
  public PuQueryAtpAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_ONHANDQUERY);
    // this.putValue(Action.ACCELERATOR_KEY,
    // KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.ALT_MASK));
    // this.putValue(INCAction.CODE, "������ѯ");
    // this.setBtnName("������ѯ");
    // this.putValue(Action.SHORT_DESCRIPTION, "������ѯ");
  }

  /**
   * ������ѯ
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    ATPParamVO[] params = this.getParamVO();

    if (params == null || params.length == 0) {
      ShowStatusBarMsgUtil.showStatusBarMsg(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004000_0", "04004000-0030")/*
                                                                   * @res
                                                                   * "�����֯�����ϡ��ƻ�������Ϣ������,���ܲ�ѯ������"
                                                                   */, this
          .getAppModel().getContext());
      return;
    }

    this.getOnAtppane().initData(params);

    this.getOnAtppane().showModal();

    ShowStatusBarMsgUtil.showStatusBarMsg(
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0",
            "04004000-0031")/* @res "������ѯ���" */, this.getAppModel()
            .getContext());
  }

  /**
   * ���������������õ�model
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-6 ����04:16:31
   */
  public BillManageModel getAppModel() {
    return this.model;
  }

  /** ȡ�ÿ�Ƭ��ͼ **/
  public ShowUpableBillForm getBillForm() {
    return this.billForm;
  }

  /** ȡ�ô������ **/
  public ATPForOneMaterialMulOrgUI getOnAtppane() {
    if (null == this.onAtppane) {
      this.onAtppane =
          new ATPForOneMaterialMulOrgUI(this.model.getContext().getEntranceUI());
    }
    return this.onAtppane;
  }

  /**
   * ����������������Ƭ��ȡ�ò�������Ҫȡ��������Ϣ�ͼƻ�������Ϣ
   * <p>
   * <b>����˵��</b>
   * 
   * @param panel
   *          ��Ƭ
   * @param selectRow
   *          ����ѡ����
   * @return <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-9-28 ����04:13:01
   */
  public abstract ATPParamVO[] getParamByCardRow(BillCardPanel panel,
      int[] selectRows);

  /**
   * ���������������б���ȡ�ò�������Ҫȡ��������Ϣ�ͼƻ�������Ϣ
   * <p>
   * <b>����˵��</b>
   * 
   * @param panel
   *          �б�
   * @param selectRow
   *          ����ѡ����
   * @return <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-9-28 ����04:13:06
   */
  public abstract ATPParamVO[] getParamByListRow(BillListPanel panel,
      int[] selectRows);

  /**
   * ��������������ȡ�ò�ѯVO
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-9-27 ����04:10:24
   */
  public ATPParamVO[] getParamVO() {
    // ��Ƭ����
    if (this.billForm.isComponentVisible()) {
      return this.getParamByCard();
    }

    // �б����
    return this.getParamByList();

  }

  /** ���ÿ�Ƭ��ͼ **/
  public void setBillForm(ShowUpableBillForm billForm) {
    this.billForm = billForm;
  }

  /**
   * @param list
   *          Ҫ���õ� list
   */
  public void setList(BillListView list) {
    this.list = list;
  }

  /**
   * @param model
   *          Ҫ���õ� model
   */
  public void setModel(BillManageModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  private boolean checkParam(ATPParamVO param) {
    if (null == param) {
      return false;
    }

    if (param.getPk_material() == null || param.getEnd_date() == null
        || param.getPk_stockorgs() == null
        || param.getPk_stockorgs()[0] == null) {
      return false;
    }

    return true;
  }

  private ATPParamVO[] getParamByCard() {
    BillCardPanel panel = this.billForm.getBillCardPanel();
    int[] selectRows = panel.getBillTable().getSelectedRows();
    if (selectRows == null || selectRows.length == 0) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004000_0", "04004000-0029")/*
                                                                   * @res
                                                                   * "����ѡ����"
                                                                   */);
    }
    return this.getParamByCardRow(panel, selectRows);
  }

  private ATPParamVO[] getParamByList() {
    BillListPanel panel = this.list.getBillListPanel();
    int[] selectRows = panel.getBodyTable().getSelectedRows();
    if (selectRows == null || selectRows.length == 0) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004000_0", "04004000-0029")/*
                                                                   * @res
                                                                   * "����ѡ����"
                                                                   */);
    }
    return this.getParamByListRow(panel, selectRows);
  }

}
