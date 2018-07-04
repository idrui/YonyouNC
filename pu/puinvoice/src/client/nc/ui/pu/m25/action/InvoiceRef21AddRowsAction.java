/**
 * $�ļ�˵��$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-5 ����06:49:29
 */
package nc.ui.pu.m25.action;

import java.awt.event.ActionEvent;

import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��������-�ɹ�����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-4-5 ����06:49:29
 */
public class InvoiceRef21AddRowsAction extends InvoiceRef21AddAction {

  private static final long serialVersionUID = 5942667618060274034L;

  /**
   * ���෽����д
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    this.doRefAddRowAction();
  }

  @Override
  protected String getSrcBillTypeCode() {
    // �������е����ε�������
    return POBillType.Order.getCode();
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.pu.m25.action.InvoiceRef21AddAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    return true;
  }
}
