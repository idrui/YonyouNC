/**
 * $�ļ�˵��$
 * 
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-13 ����01:16:09
 */
package nc.ui.pu.m21.action.status.confirm;

import java.awt.event.ActionEvent;
import java.util.List;

import nc.ui.pu.m21.editor.list.SelectBillManageModel;
import nc.ui.pu.m21.service.onway.OrderConfirmService;
import nc.ui.pu.m21.view.StatusBillFormEditor;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.query2.model.ModelDataManager;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.editor.IEditor;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.enumeration.OnwayStatusQryEnum;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.scmpub.res.SCMActionCode;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��ȷ�ϲ���
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-7-13 ����01:16:09
 */
public class UnConfirmAction extends NCAction {

  private static final long serialVersionUID = 5847969397011748619L;

  private ModelDataManager dataManager;

  private IEditor editor;

  private BillManageModel model;

  // private ConfirmQueryActionMy queryAction;

  private OrderConfirmService service;

  public UnConfirmAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_UNCONFIRM);
    // this.setBtnName("��ȷ��");
    // this.setCode("unConfirmAction");
    // this.putValue(Action.SHORT_DESCRIPTION, this.getBtnName() + "(Shift+X)");
    // this.putValue(Action.ACCELERATOR_KEY,
    // KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.SHIFT_MASK));
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    OrderVO vo = (OrderVO) ((BillForm) this.editor).getValue();

    // ��ȡ����ѡ�������
    List<ISuperVO> rows =
        ((SelectBillManageModel) this.model).getSelectedBodyRows();
    OrderItemVO[] voitems = rows.toArray(new OrderItemVO[0]);

    // ����������һ��
    AggVOUtil.updateItemVOs(vo, voitems);
    if (ArrayUtils.isEmpty(voitems)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0362")/*
                                                                   * @res
                                                                   * "�빴ѡ��¼���в���"
                                                                   */);
    }
    // for (int i = 0; i < voitems.length; i++) {
    // voitems[i].setForderrowstatus(ValueUtils.getInteger(
    // EnumRowStatus.FREE.value()));
    // this.clearUnConfirmItems(voitems[i]);
    // voitems[i].setStatus(VOStatus.UPDATED);
    // }
    vo.setBVO(voitems);
    vo.getHVO().setStatus(VOStatus.UPDATED);

    this.service.delete(new OrderVO[] {
      vo
    });
    // ˢ�½���
    // int nRowNum = this.getModel().getSelectedRow();
    if (this.dataManager != null) {
      this.dataManager.refresh();
    }
  }

  public ModelDataManager getDataManager() {
    return this.dataManager;
  }

  public IEditor getEditor() {
    return this.editor;
  }

  public BillManageModel getModel() {
    return this.model;
  }

  /**
   * @return queryAction
   */
  // public ConfirmQueryActionMy getQueryAction() {
  // return this.queryAction;
  // }

  public OrderConfirmService getService() {
    return this.service;
  }

  public void setDataManager(ModelDataManager dataManager) {
    this.dataManager = dataManager;
  }

  public void setEditor(IEditor editor) {
    this.editor = editor;
  }

  // ��ձ��������
  // private void clearUnConfirmItems(OrderItemVO item) {
  // // �Է�������
  // item.setVvendorordercode(null);
  // // �Է������к�
  // item.setVvendororderrow(null);
  // // ȷ������
  // item.setDconfirmdate(null);
  // // ȷ������
  // item.setNconfirmnum(null);
  // }

  public void setModel(BillManageModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  /**
   * @param queryAction
   *          Ҫ���õ� queryAction
   */
  // public void setQueryAction(ConfirmQueryActionMy queryAction) {
  // this.queryAction = queryAction;
  // }

  public void setService(OrderConfirmService service) {
    this.service = service;
  }

  private boolean isconfirm() {
    IQueryScheme queryScheme = this.getDataManager().getQueryScheme();
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    QueryCondition outputCond =
        qrySchemeProcessor.getLogicalCondition(OnwayStatusQryEnum.confirm
            .code());
    Object[] values = outputCond.getValues();
    UFBoolean output = UFBoolean.valueOf((String) values[0]);
    return output.booleanValue();
  }

  @Override
  protected boolean isActionEnable() {
    OrderItemVO[] vos =
        (OrderItemVO[]) ((StatusBillFormEditor) this.editor)
            .getBodySelectedVOs();
    if (ArrayUtils.isEmpty(vos)) {
      return false;
    }
    if (!this.isconfirm()) {
      return false;
    }
    return true;
  }
}
