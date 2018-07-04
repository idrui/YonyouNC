/**
 * $文件说明$
 * 
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-28 下午02:24:35
 */
package nc.ui.pu.m21.action.status.output;

import java.awt.event.ActionEvent;

import nc.ui.pu.m21.editor.list.SelectBillManageModel;
import nc.ui.pu.m21.service.onway.OrderOutputService;
import nc.ui.pubapp.uif2app.query2.model.ModelDataManager;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.model.BillManageModel;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.scmpub.res.SCMActionCode;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>反输出
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-6-28 下午02:24:35
 */
public class UnOutputAction extends NCAction {

  private static final long serialVersionUID = -2108370765523940742L;

  private ModelDataManager dataManager;

  private BillManageModel model;

  // private OutputQueryActionMy queryAction ;

  private OrderOutputService service;

  public UnOutputAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.PU_CANCELOUTPUT);
    // this.setBtnName("反输出");
    // this.setCode("unOutputAction");
    // this.putValue(Action.SHORT_DESCRIPTION, this.getBtnName() + "(Shift+X)");
    // this.putValue(Action.ACCELERATOR_KEY,
    // KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.SHIFT_MASK));
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    // 采购订单反输出
    OrderVO[] objs =
        ((SelectBillManageModel) this.getModel()).getSelectedBills().toArray(
            new OrderVO[0]);

    if (ArrayUtils.isEmpty(objs)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0362")/*
                                                                   * @res
                                                                   * "请勾选记录进行操作"
                                                                   */);
    }
    for (OrderVO vo : objs) {
      vo.getHVO().setForderstatus((Integer) POEnumBillStatus.APPROVE.value());
      vo.getHVO().setStatus(VOStatus.UPDATED);
    }
    this.service.delete(objs);

    if (this.dataManager != null) {
      this.dataManager.refresh();
    }
  }

  public ModelDataManager getDataManager() {
    return this.dataManager;
  }

  public BillManageModel getModel() {
    return this.model;
  }

  /**
   * @return queryAction
   */
  // public OutputQueryActionMy getQueryAction() {
  // return this.queryAction;
  // }

  public OrderOutputService getService() {
    return this.service;
  }

  public void setDataManager(ModelDataManager dataManager) {
    this.dataManager = dataManager;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  /**
   * @param queryAction
   *          要设置的 queryAction
   */
  // public void setQueryAction(OutputQueryActionMy queryAction) {
  // this.queryAction = queryAction;
  // }

  public void setService(OrderOutputService service) {
    this.service = service;
  }

  private boolean isoutput() {
    IQueryScheme queryScheme = this.getDataManager().getQueryScheme();
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    QueryCondition outputCond = qrySchemeProcessor.getQueryCondition("output");
    Object[] values = outputCond.getValues();
    UFBoolean output = UFBoolean.valueOf((String) values[0]);
    return output.booleanValue();
  }

  @Override
  protected boolean isActionEnable() {

    Object[] obj = this.model.getSelectedOperaDatas();

    if (obj == null) {
      return false;
    }

    // OrderVO[] objs = new OrderVO[] {
    // (OrderVO) obj
    // };
    // if (objs.length == 0) {
    // return false;
    // }
    // for (OrderVO vo : objs) {
    // if (!vo.getHVO().getForderstatus().equals(EnumBillStatus.EXPORT.value()))
    // {
    // return false;
    // }
    // }

    if (!this.isoutput()) {
      return false;
    }
    return true;
  }
}
