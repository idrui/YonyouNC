package nc.ui.pu.m27.match.action;

import java.awt.event.ActionEvent;

import nc.ui.scmpub.action.SCMActionInitializer;
import nc.vo.pu.m27.enumeration.EnumSettleType;
import nc.vo.scmpub.res.SCMActionCode;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>����"���ý���"��ť
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-1-6 ����02:05:38
 */
public class FeeMatchAction extends MatchDefaultAction {

  private static final long serialVersionUID = -5667491871111125098L;

  public FeeMatchAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.PU_FEEMATCH);
    // this.setBtnName("���ý���");
    // this.setCode("btnFeeMatch");
    // this.putValue(Action.SHORT_DESCRIPTION, this.getBtnName());
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {

    // �����õĻ���������������������
    this.getModel().getSettleEnvironment().setSettleType(EnumSettleType.FEE);

    // ��ʼ����������VO��Ϣ
    this.getModel().initMatchVos();

  }

}
