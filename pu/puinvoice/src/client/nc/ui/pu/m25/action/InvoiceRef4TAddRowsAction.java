package nc.ui.pu.m25.action;

import java.awt.event.ActionEvent;

import nc.vo.scmpub.res.billtype.POBillType;

/**
 * ��������-�ڳ��ݹ���ⵥ
 * 
 * @since 6.3
 * @version 2014-12-9 ����3:24:02
 * @author yanxm5
 */

public class InvoiceRef4TAddRowsAction extends InvoiceRef4TAddAction {
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
    return POBillType.InitEstimate.getCode();
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.pu.m25.action.InvoiceRef4TAddAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    return true;
  }
}
