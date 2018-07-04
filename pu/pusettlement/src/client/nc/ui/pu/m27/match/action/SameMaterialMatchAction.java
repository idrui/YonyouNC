package nc.ui.pu.m27.match.action;

import java.awt.event.ActionEvent;

import nc.ui.scmpub.action.SCMActionInitializer;
import nc.vo.pu.m27.enumeration.EnumSettleType;
import nc.vo.scmpub.res.SCMActionCode;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>ͬ���Ͻ���İ�ť�¼�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-9-30 ����02:07:28
 */
public class SameMaterialMatchAction extends MatchDefaultAction {
  private static final long serialVersionUID = -1081048528627323723L;

  public SameMaterialMatchAction() {
    super();
    SCMActionInitializer.initializeAction(this,
        SCMActionCode.PU_SAMEMATERIELMATCH);
    // this.setBtnName("ͬ���Ͻ���");
    // this.setCode("btnSameMaterielMatch");
    // this.putValue(Action.SHORT_DESCRIPTION, this.getBtnName());
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    // ��������
    this.getModel().getSettleEnvironment()
        .setSettleType(EnumSettleType.SAME_MATERIAL);

    // ��ʼ����������VO��Ϣ
    this.getModel().initMatchVos();

  }

}
