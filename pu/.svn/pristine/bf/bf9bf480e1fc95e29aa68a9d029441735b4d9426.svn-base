/**
 * $文件说明$
 * 
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-20 下午04:31:13
 */
package nc.ui.pu.m21.action;

import java.awt.event.ActionEvent;

import nc.bs.uif2.validation.IValidationService;
import nc.ui.pu.m21.service.onway.AbstractOrderOnwayService;
import nc.ui.pu.m21.view.OnwayStatusBillForm;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.query2.model.ModelDataManager;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.editor.IEditor;
import nc.vo.pu.m21.entity.OrderOnwayItemVO;
import nc.vo.pu.m21.entity.OrderOnwayVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>在途状态Action
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-8-17 下午04:31:13
 */
public abstract class AbstractOnwayStatusAction extends NCAction {

  private static final long serialVersionUID = -6745259290311034753L;

  protected ModelDataManager dataManager;

  protected IEditor editor;

  protected BillManageModel model;

  protected AbstractOrderOnwayService service = null;

  protected IValidationService validateService = null;

  public AbstractOnwayStatusAction() {
    super();
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    OrderOnwayVO vo = (OrderOnwayVO) ((BillForm) this.editor).getValue();
    OrderOnwayItemVO[] voitems =
        (OrderOnwayItemVO[]) ((OnwayStatusBillForm) this.editor)
            .getBodySelectedVOs();
    // 检测非空、确认数量的有效性
    this.validateService.validate(null);

    // 设置在途状态
    for (int i = 0; i < voitems.length; i++) {
      voitems[i].setFonwaystatus(ValueUtils.getInteger(this.getStatus()));
      voitems[i].setStatus(VOStatus.UPDATED);
    }
    vo.setBVO(voitems);
    this.service.update(new OrderOnwayVO[] {
      vo
    });

    // 刷新界面
    int nRowNum = this.getModel().getSelectedRow();
    if (this.dataManager != null) {
      this.dataManager.refresh();
    }
    int num = this.getModel().getRowCount();
    if (nRowNum >= num) {
      nRowNum--;
    }
    this.getModel().setSelectedRow(nRowNum);
  }

  public ModelDataManager getDataManager() {
    return this.dataManager;
  }

  public IEditor getEditor() {
    return this.editor;
  }

  // public abstract boolean getIsDone();

  public BillManageModel getModel() {
    return this.model;
  }

  public AbstractOrderOnwayService getService() {
    return this.service;
  }

  /**
   * 方法功能描述：取得状态
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-8-17 下午04:25:11
   */
  public abstract Integer getStatus();

  public abstract String getStatusStr();

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

  public void setService(AbstractOrderOnwayService service) {
    this.service = service;
  }

  public void setValidateService(IValidationService validateService) {
    this.validateService = validateService;
  }

  private boolean getIsDone() {
    IQueryScheme queryScheme = this.getDataManager().getQueryScheme();
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    QueryCondition outputCond =
        qrySchemeProcessor.getLogicalCondition(this.getStatusStr());
    Object[] values = outputCond.getValues();
    UFBoolean output = UFBoolean.valueOf((String) values[0]);
    return output.booleanValue();
  }

  @Override
  protected boolean isActionEnable() {
    OrderOnwayItemVO[] vos =
        (OrderOnwayItemVO[]) ((OnwayStatusBillForm) this.editor)
            .getBodySelectedVOs();
    if (ArrayUtils.isEmpty(vos)) {
      return false;
    }

    if (this.getIsDone()) {
      return false;
    }
    return true;
  }
}
