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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>ȡ���������ť�¼�
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-6-3 ����07:18:16
 */
public class SettleBillCancelIAAction extends MultiBillAction {
  private static final long serialVersionUID = 2571910273611370450L;

  public SettleBillCancelIAAction() {
    super();
    SCMActionInitializer
        .initializeAction(this, SCMActionCode.PU_CANCELSENDTOIA);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    if (!SysInitGroupQuery.isIAEnabled()) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0038")/*@res "�������ģ��δ���ã��޷�ȡ���������"*/);
    }
    super.doAction(e);
  }

  @Override
  protected ISingleBillService<Object> getSingleBillService() {
    ISingleBillService<Object> service = new ISingleBillService<Object>() {
      @Override
      public SettleBillVO operateBill(Object bill) throws Exception {
        // ִ��Զ�̵��ã�ȡ�������
        ISettleBillMaintain manageService =
            NCLocator.getInstance().lookup(ISettleBillMaintain.class);
        SettleBillVO[] returnVos = manageService.cancelToIA(new SettleBillVO[] {
          (SettleBillVO) bill
        });
        return returnVos != null ? returnVos[0] : (SettleBillVO) bill;
      }
    };
    return service;
  }

  @Override
  protected String getSuccessInfo() {
    return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0039")/*@res "ȡ��������ɹ���"*/;
  }

  @Override
  protected String getTitle() {
    return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0040")/*@res "ȡ�������"*/;
  }

  @Override
  protected boolean isActionEnable() {
    Object[] objs = this.getModel().getSelectedOperaDatas();
    if (objs != null && objs.length > 1) {
      return true;
    }

    SettleBillVO vo = (SettleBillVO) this.getModel().getSelectedData();
    return vo != null && UFBoolean.TRUE.equals(vo.getParentVO().getBtoia());
  }
}