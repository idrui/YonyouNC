/**
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-6 下午02:05:38
 */
package nc.ui.pu.m27.match.action;

import java.awt.event.ActionEvent;

import nc.desktop.ui.Workbench;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.ui.pu.m27.match.view.AutoSettleRuleDlg;
import nc.ui.pub.beans.UIDialog;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.vo.pu.m27.entity.InvoiceStockOptionableVO;
import nc.vo.pu.m27.entity.RBInvoiceOptionableVO;
import nc.vo.pu.m27.entity.RBStockOptionableVO;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pub.SuperVO;
import nc.vo.scmpub.res.SCMActionCode;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>构造"自动结算规则"按钮
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-1-6 下午02:05:38
 */
public class AutoMatchRuleAction extends MatchDefaultAction {

  private static final long serialVersionUID = -5667491871111125098L;

  /**
   * InvoiceAction 的构造子
   */
  public AutoMatchRuleAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.PU_AUTOMATCHRULE);
    // this.setBtnName("自动结算规则");
    // this.setCode("btnAutoMatchRule");
    // this.putValue(Action.SHORT_DESCRIPTION, this.getBtnName());
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    Workbench wb = WorkbenchEnvironment.getInstance().getWorkbench();
    AutoSettleRuleDlg dlg =
        new AutoSettleRuleDlg(wb, this.getRuleVosFromModel());

    if (UIDialog.ID_CANCEL == dlg.showModal()) {
      return;
    }

    SuperVO[] ruleVOs = dlg.getRuleVOs();
    if (ArrayUtils.isEmpty(ruleVOs)) {
      return;
    }
    this.setRuleVosToModel(ruleVOs);
  }

  private SuperVO[] getRuleVosFromModel() {
    SettleEnvironment env = this.getModel().getSettleEnvironment();
    SuperVO[] ruleVos = new SuperVO[3];
    // 自动结算红兰入库单对冲规则
    ruleVos[0] = env.getAutoMatchRBStockOptionableVO();
    // 自动结算红兰发票对冲规则
    ruleVos[1] = env.getAutoMatchRBInvoiceOptionableVO();
    // 自动结算发票入库单规则
    ruleVos[2] = env.getAutoMatchInvoiceStockOptionableVO();
    return ruleVos;
  }

  private void setRuleVosToModel(SuperVO[] ruleVos) {
    SettleEnvironment env = this.getModel().getSettleEnvironment();
    // 自动结算红兰入库单对冲规则
    env.setAutoMatchRBStockOptionableVO((RBStockOptionableVO) ruleVos[0]);
    // 自动结算红兰发票对冲规则
    env.setAutoMatchRBInvoiceOptionableVO((RBInvoiceOptionableVO) ruleVos[1]);
    // 自动结算发票入库单规则
    env.setAutoMatchInvoiceStockOptionableVO((InvoiceStockOptionableVO) ruleVos[2]);
  }

}
