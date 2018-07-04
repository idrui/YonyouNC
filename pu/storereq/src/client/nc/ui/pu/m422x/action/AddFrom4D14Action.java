package nc.ui.pu.m422x.action;

import java.awt.event.ActionEvent;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.uap.pf.busiflow.PfButtonClickContext;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.pf.PfUtilClient;
import nc.ui.pubapp.uif2app.actions.AbstractReferenceAction;
import nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeFuncUtils;
import nc.ui.uif2.UIState;
import nc.ui.uif2.editor.IBillCardPanelEditor;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * ����Ŀ�����ʼ�������������
 * 
 * @since 6.3.1
 * @version 2013-8-8 ����01:32:26
 * @author fanly3
 */
public class AddFrom4D14Action extends AbstractReferenceAction {

  private static final long serialVersionUID = -8193872247728573192L;

  private IBillCardPanelEditor editor;

  private AbstractAppModel model;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    if (!SysInitGroupQuery.isPIMEnabled()) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004010_0", "04004010-0029")/*
                                                                   * @res
                                                                   * "δ��װ��Ŀ����ģ��ù��ܲ����ã�"
                                                                   */);
    }
    PfUtilClient.childButtonClickedNew(this.createPfButtonClickContext());
    if (PfUtilClient.isCloseOK()) {
      StoreReqAppVO[] vos = (StoreReqAppVO[]) PfUtilClient.getRetVos();
      // ��ʾ��ת��������
      this.getTransferViewProcessor().processBillTransfer(vos);
      // �ɱ༭��
      this.setEditable();
    }
  }

  public IBillCardPanelEditor getEditor() {
    return this.editor;
  }

  public AbstractAppModel getModel() {
    return this.model;
  }

  public void setEditor(IBillCardPanelEditor editor) {
    this.editor = editor;
  }

  public void setModel(AbstractAppModel model) {
    this.model = model;
  }

  private PfButtonClickContext createPfButtonClickContext() {
    PfButtonClickContext context = new PfButtonClickContext();
    context.setParent(this.getModel().getContext().getEntranceUI());
    context.setSrcBillType(this.getSourceBillType());
    context.setPk_group(this.getModel().getContext().getPk_group());
    context.setUserId(this.getModel().getContext().getPk_loginUser());
    // ����ýڵ����ɽ������ͷ����ģ���ô�������Ӧ�ô��������ͣ����򴫵�������
    // ����ýڵ����ɽ������ͷ����ģ���ô�������Ӧ�ô��������ͣ����򴫵�������
    String vtrantype =
        TrantypeFuncUtils.getTrantype(this.getModel().getContext());
    if (StringUtil.isEmptyWithTrim(vtrantype)) {
      context.setCurrBilltype(POBillType.MRBill.getCode());
    }
    else {
      context.setCurrBilltype(vtrantype);
    }
    context.setUserObj(null);
    context.setSrcBillId(null);
    context.setBusiTypes(this.getBusitypes());
    // ����Ĳ�����ԭ�����õķ����ж����漰��ֻ���������һ�����ṹ�����������������¼ӵĲ���
    // ���εĽ������ͼ���
    context.setTransTypes(this.getTranstypes());
    // ��־�ڽ�������Ŀ�Ľ������ͷ���ʱ������Ŀ�Ľ������͵����ݣ�������������ֵ��1�����ݽӿڶ��壩��
    // 2�������������ã���-1�������ݽ������ͷ��飩
    context.setClassifyMode(PfButtonClickContext.ClassifyByItfdef);
    return context;
  }

  /**
   * ���õ����ֶεı༭��
   */
  private void setEditable() {
    // �������ʼ���������,���Ϻ���Ŀ�������޸�
    BillCardPanel panel = this.editor.getBillCardPanel();
    // ��ͷ��Ŀ�������޸�
    panel.getHeadItem(StoreReqAppHeaderVO.PK_PROJECT).setEdit(false);
    // �������ϣ���Ŀ�������޸�
    for (int i = 0; i < panel.getRowCount(); ++i) {
      panel.setCellEditable(i, StoreReqAppItemVO.PK_MATERIAL, false);
      panel.setCellEditable(i, StoreReqAppItemVO.PK_SRCMATERIAL, false);
      panel.setCellEditable(i, StoreReqAppItemVO.CPROJECTID, false);
      panel.setCellEditable(i, StoreReqAppItemVO.CBS, false);
    }
  }

  @Override
  protected boolean isActionEnable() {
    return this.model.getUiState() == UIState.NOT_EDIT;
  }
}
