/**
 * 
 */
package nc.ui.pu.m25.action;

import java.awt.event.ActionEvent;

import nc.ui.pubapp.uif2app.actions.pflow.UNApproveScriptAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.UIState;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.pub.util.ApproveFlowUtil;

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
 * @time 2010-1-25 上午09:55:28
 */
public class InvoiceUnApproveAction extends UNApproveScriptAction {

  /**
   * 
   */
  private static final long serialVersionUID = -2662213321607955397L;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    this.isUnapprove();
    super.doAction(e);
  }

  private boolean isUnapprove() {
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
          .getNCLangRes().getStrByID("4004050_0", "04004050-0137")/*
                                                                   * 以下发票来源为运费发票，
                                                                   * 无法取消审批！
                                                                   */+ str,
          this.getModel().getContext());
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
    // // 发票可取消审批状态检查
    // new UnApproveStatusChkRule().process(vos);
  }

  @Override
  protected boolean isActionEnable() {
    boolean isEnable = true;
    isEnable &=
        UIState.NOT_EDIT == this.getModel().getUiState()
            && null != this.getModel().getSelectedData();
    if (isEnable) {
      InvoiceVO vo = (InvoiceVO) this.getModel().getSelectedData();
      Object[] vos = this.getModel().getSelectedOperaDatas();

      // 状态审批
      isEnable &=
          null != vos && 1 < vos.length || ApproveFlowUtil.isCanUnApprove(vo);
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
