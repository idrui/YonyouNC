/**
 *
 */
package nc.ui.pu.m25.action;

import java.awt.event.ActionEvent;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.ui.pubapp.pub.common.context.PFlowContext;
import nc.ui.pubapp.uif2app.actions.pflow.ScriptPFlowAction;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.UIState;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.enumeration.InvoiceBillAction;
import nc.vo.pu.m25.env.InvoiceUIToBSEnv;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.scmpub.res.SCMActionCode;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>功能条目1
 * <li>功能条目2
 * <li>...
 * </ul>
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version 本版本号
 * @since 上一版本号
 * @author xiebo
 * @time 2010-1-25 下午12:26:22
 */
public class InvoiceCancelSendApAction extends ScriptPFlowAction {

  /**
   *
   */
  private static final long serialVersionUID = 4553662148501234750L;

  public InvoiceCancelSendApAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.PU_CANCELTOAP);
    // // ActionInitializer.initializeAction(this, IPuActionCode.CANCELAP);
    // this.setBtnName("取消传应付");
    // // setBtnChinaName("传应付");
    // this.setCode("取消传应付");
    // // Ctrl+2
    // this.putValue(Action.ACCELERATOR_KEY,
    // KeyStroke.getKeyStroke(KeyEvent.VK_2, Event.CTRL_MASK));
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    if (!SysInitGroupQuery.isAPEnabled()) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004050_0", "04004050-0001")/*
                                                                   * @res
                                                                   * "应付模块未启用，无法取消传应付！"
                                                                   */);
    }
    this.isCancelSendAp();
    super.doAction(e);
  }

  private boolean isCancelSendAp() {
    String str = "";
    Object[] obs = this.getModel().getSelectedOperaDatas();
    for (Object ob : obs) {
      InvoiceVO vo = (InvoiceVO) ob;
      InvoiceItemVO[] itemVOs = vo.getChildrenVO();
      if (itemVOs == null) {
        continue;
      }
      for (InvoiceItemVO itemVo : itemVOs) {
        if ("4812".equals(itemVo.getCsourcetypecode())) {
          str = str + vo.getParentVO().getVbillcode() + " ";
        }
      }
    }
    if (!str.equals("")) {
      ShowStatusBarMsgUtil.showStatusBarMsg(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004050_0", "04004050-0136")/*
                                                                   * 以下发票来源为运费发票，
                                                                   * 无法取消传应付！
                                                                   */+ str,
          this.model.getContext());
      return false;
    }
    return true;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pubapp.uif2app.actions.pflow.AbstractScriptExcAction#beforeCheck(java.lang.Object)
   */
  @Override
  protected void beforeCheck(Object vo) {
    super.beforeCheck(vo);
    // 2011-09-13 tianft 因为kongxd的脚本前台改为了批处理，这个校验不能在前台校验了，否则会全部不通过
    // InvoiceVO[] vos = new InvoiceVO[] {
    // (InvoiceVO) vo
    // };
    // // 可取消传应付的发票状态检查(前后台)
    // new CancelSendAPStateChkRule(true).process(vos);
  }

  @Override
  protected void fillUpContext(PFlowContext context) {
    super.fillUpContext(context);
    AggregatedValueObject[] vos = context.getBillVos();
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    PfUserObject[] usrObjs = new PfUserObject[vos.length];
    PfUserObject usrObj = new PfUserObject();
    InvoiceUIToBSEnv env = new InvoiceUIToBSEnv();
    env.setTrigger(InvoiceBillAction.CANCELSENDAP);
    env.setBManual(UFBoolean.TRUE);
    usrObj.setUserObject(env);
    for (int i = 0; i < usrObjs.length; i++) {
      usrObjs[i] = usrObj;
    }
    context.setUserObjs(usrObjs);

  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.actions.SaveAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    boolean isEnable = true;
    isEnable &=
        UIState.NOT_EDIT == this.getModel().getUiState()
            && null != this.getModel().getSelectedData();
    if (isEnable) {
      InvoiceVO vo = (InvoiceVO) this.getModel().getSelectedData();
      Object[] vos = this.getModel().getSelectedOperaDatas();
      // 已经传应付的情况下,允许取消传应付.
      isEnable &=
          vo.getParentVO().getBapflag().booleanValue() || null != vos
              && 1 < vos.length;
      InvoiceItemVO[] itemVOs = vo.getChildrenVO();
      if (itemVOs == null) {
        return isEnable;
      }
      for (InvoiceItemVO itemVo : itemVOs) {
        if ("4812".equals(itemVo.getCsourcetypecode())) {
          return false;
        }
      }
    }
    return isEnable;
  }
}
