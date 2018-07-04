/**
 * $�ļ�˵��$
 *
 * @author zhaoyha
 * @version
 * @see
 * @since
 * @time 2009-7-10 ����11:04:56
 */
package nc.ui.pu.m25.action;

import java.awt.event.ActionEvent;

import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.ui.pubapp.pub.common.context.PFlowContext;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.pflow.ScriptPFlowAction;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.enumeration.InvoiceBillAction;
import nc.vo.pu.m25.env.InvoiceUIToBSEnv;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.scmpub.res.SCMActionCode;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������Ŀ1
 * <li>������Ŀ2
 * <li>...
 * </ul>
 * <p>
 * <b>�����ʷ����ѡ����</b>
 * <p>
 * XXX�汾����XXX��֧�֡�
 * <p>
 * <p>
 * 
 * @version ���汾��
 * @since ��һ�汾��
 * @author zhaoyha
 * @time 2009-7-10 ����11:04:56
 */
public class InvoiceSendApAction extends ScriptPFlowAction {

  /**
   *
   */
  private static final long serialVersionUID = 6514211178808426296L;

  /**
   * InvoiceSendApAction �Ĺ�����
   */
  public InvoiceSendApAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.PU_SENDTOAP);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    if (!SysInitGroupQuery.isAPEnabled()) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004050_0", "04004050-0007")/*
                                                                   * @res
                                                                   * "Ӧ��ģ��δ���ã��޷���Ӧ����"
                                                                   */);
    }
    this.isSendAp();
    super.doAction(e);
  }

  private boolean isSendAp() {
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
          .getNCLangRes().getStrByID("4004050_0", "04004050-0135")/*
                                                                   * ���·�Ʊ��ԴΪ�˷ѷ�Ʊ��
                                                                   * �޷���Ӧ����
                                                                   */+ str,
          this.getModel().getContext());
      return false;
    }
    return true;
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.pubapp.uif2app.actions.pflow.AbstractScriptExcAction#beforeCheck(java.lang.Object)
   */
  @Override
  protected void beforeCheck(Object vo) {
    super.beforeCheck(vo);
    // InvoiceVO[] vos = new InvoiceVO[] {
    // (InvoiceVO) vo
    // };
    // �ɴ�Ӧ���ķ�Ʊ״̬(��־)���(ǰ��̨���)
    // 2011-09-13 tianft ��Ϊkongxd�Ľű�ǰ̨��Ϊ�����������У�鲻����ǰ̨У���ˣ������ȫ����ͨ��
    // new SendAPStateFilterRule(true).process(vos);

  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.pubapp.uif2app.actions.pflow.ScriptPFlowAction#fillUpContext(nc.ui.pubapp.pub.common.context.PFlowContext)
   */
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
    env.setBManual(UFBoolean.TRUE);
    env.setTrigger(InvoiceBillAction.SENDAP);
    usrObj.setUserObject(env);
    for (int i = 0; i < usrObjs.length; i++) {
      usrObjs[i] = usrObj;
    }
    context.setUserObjs(usrObjs);
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.uif2.actions.SaveAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    boolean isEnable = true;
    isEnable &=
        AppUiState.NOT_EDIT == this.getModel().getAppUiState()
            && null != this.getModel().getSelectedData();
    if (isEnable) {
      InvoiceVO vo = (InvoiceVO) this.getModel().getSelectedData();
      Object[] vos = this.getModel().getSelectedOperaDatas();
      // ����˲���û�д�Ӧ��ʱ����Ӧ��
      isEnable &=
          POEnumBillStatus.APPROVE.toInt() == vo.getParentVO().getFbillstatus()
              .intValue()
              && !vo.getParentVO().getBapflag().booleanValue()
              || null != vos
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
