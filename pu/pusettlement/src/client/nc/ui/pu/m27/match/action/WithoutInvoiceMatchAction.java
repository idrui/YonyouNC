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
 * <li>����"�޷�Ʊ����"��ť
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-1-6 ����02:05:38
 */
public class WithoutInvoiceMatchAction extends MatchDefaultAction {

  private static final long serialVersionUID = -5667491871111125098L;

  public WithoutInvoiceMatchAction() {
    super();
    SCMActionInitializer.initializeAction(this,
        SCMActionCode.PU_WITHOUTINVOICEMATCH);
    // this.setBtnName("�޷�Ʊ����");
    // this.setCode("btnWithoutInvoiceMatch");
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
        .setSettleType(EnumSettleType.WITHOUT_INVOICE);

    // ��ʼ����������VO��Ϣ
    this.getModel().initMatchVos();
  }

}
