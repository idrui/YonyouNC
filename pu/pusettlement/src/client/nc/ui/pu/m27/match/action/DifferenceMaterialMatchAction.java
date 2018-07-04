/**
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-6 ����02:05:38
 */
package nc.ui.pu.m27.match.action;

import java.awt.event.ActionEvent;

import nc.ui.scmpub.action.SCMActionInitializer;
import nc.vo.pu.m27.enumeration.EnumSettleType;
import nc.vo.scmpub.res.SCMActionCode;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>����"�����Ͻ���"��ť
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-1-6 ����02:05:38
 */
public class DifferenceMaterialMatchAction extends MatchDefaultAction {

  private static final long serialVersionUID = -5667491871111125098L;

  /**
   * InvoiceAction �Ĺ�����
   */
  public DifferenceMaterialMatchAction() {
    super();
    SCMActionInitializer.initializeAction(this,
        SCMActionCode.PU_DIFFERENCEMATERIALMATCH);
    // this.setBtnName("�����Ͻ���");
    // this.setCode("btnDifferenceMaterialMatch");
    // this.putValue(Action.SHORT_DESCRIPTION, this.getBtnName());
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    // ��������
    this.getModel().getSettleEnvironment()
        .setSettleType(EnumSettleType.DIFFERENT_MATERIAL);

    // ��ʼ����������VO��Ϣ
    this.getModel().initMatchVos();
  }

}
