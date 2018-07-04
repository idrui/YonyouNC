/**
 * $文件说明$
 * 
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-13 下午01:15:25
 */
package nc.ui.pu.m21.action.status.confirm;

import java.awt.event.ActionEvent;
import java.util.List;

import nc.bs.uif2.validation.IValidationService;
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
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.scmpub.res.SCMActionCode;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>对方确认
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-7-13 下午01:15:25
 */
public class ConfirmAction extends NCAction {

  private static final long serialVersionUID = -5228259836113497433L;

  private ModelDataManager dataManager = null;

  private IEditor editor;

  private BillManageModel model;

  private OrderConfirmService service;

  // private ConfirmQueryActionMy queryAction = null;

  private IValidationService validateService = null;

  public ConfirmAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_CONFIRM);
    // this.setBtnName("确认");
    // this.setCode("confirmAction");
    // this.putValue(Action.SHORT_DESCRIPTION, this.getBtnName() + "(Ctrl+W)");
    // this.putValue(Action.ACCELERATOR_KEY,
    // KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK));
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    OrderVO vo = (OrderVO) ((BillForm) this.editor).getValue();
    List<ISuperVO> rows =
        ((SelectBillManageModel) this.model).getSelectedBodyRows();
    OrderItemVO[] voitems = rows.toArray(new OrderItemVO[0]);

    // 跟界面数据一致
    AggVOUtil.updateItemVOs(vo, voitems);
    this.validateService.validate(voitems);
    if (ArrayUtils.isEmpty(voitems)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0362")/*
                                                                   * @res
                                                                   * "请勾选记录进行操作"
                                                                   */);
    }

    // 获取当前业务日期
    UFDate dconfirmdate = AppContext.getInstance().getBusiDate();
    // 确认
    for (int i = 0; i < voitems.length; i++) {
      // 如果没有设置确认日期,则取当前业务日期
      if (voitems[i].getDconfirmdate() == null) {
        voitems[i].setDconfirmdate(dconfirmdate);
      }
      voitems[i].setStatus(VOStatus.UPDATED);

    }

    vo.setBVO(voitems);
    vo.getHVO().setStatus(VOStatus.UPDATED);

    this.service.update(new OrderVO[] {
      vo
    });

    // 刷新界面
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

  public IValidationService getValidateService() {
    return this.validateService;
  }

  public void setDataManager(ModelDataManager dataManager) {
    this.dataManager = dataManager;
  }

  public void setEditor(IEditor editor) {
    this.editor = editor;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  /**
   * @param queryAction
   *          要设置的 queryAction
   */
  // public void setQueryAction(ConfirmQueryActionMy queryAction) {
  // this.queryAction = queryAction;
  // }

  public void setService(OrderConfirmService service) {
    this.service = service;
  }

  public void setValidateService(IValidationService validateService) {
    this.validateService = validateService;
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

    if (this.isconfirm()) {
      return false;
    }

    return true;
  }

}
