package nc.ui.pu.m27.settlebill.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m27.ISettleBillMaintain;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.ui.pu.pub.action.MultiBillAction;
import nc.ui.pubapp.pub.task.ISingleBillService;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.SCMActionCode;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>传存货按钮事件
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-6-3 下午07:17:03
 */
public class SettleBillToIAAction extends MultiBillAction {

  private static final long serialVersionUID = 6083572906555428091L;

  public SettleBillToIAAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.PU_SENDTOIA);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    if (!SysInitGroupQuery.isIAEnabled()) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0041")/*@res "传存核算模块未启用，无法传存货！"*/);
    }
    super.doAction(e);
  }

  @Override
  protected ISingleBillService<Object> getSingleBillService() {
    ISingleBillService<Object> service = new ISingleBillService<Object>() {
      @Override
      public SettleBillVO operateBill(Object bill) throws Exception {
        // 执行远程调用，传存货
        ISettleBillMaintain manageService =
            NCLocator.getInstance().lookup(ISettleBillMaintain.class);
        SettleBillVO[] returnVos = manageService.toIA(new SettleBillVO[] {
          (SettleBillVO) bill
        });
        return returnVos != null ? returnVos[0] : (SettleBillVO) bill;
      }
    };
    return service;
  }

  @Override
  protected String getSuccessInfo() {
    return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0042")/*@res "传存货成功。"*/;
  }

  @Override
  protected String getTitle() {
    return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0043")/*@res "传存货"*/;
  }

  @Override
  protected boolean isActionEnable() {
    Object[] objs = this.getModel().getSelectedOperaDatas();
    if (objs != null && objs.length > 1) {
      return true;
    }

    SettleBillVO vo = (SettleBillVO) this.getModel().getSelectedData();
    return vo != null && UFBoolean.FALSE.equals(vo.getParentVO().getBtoia());
  }
}