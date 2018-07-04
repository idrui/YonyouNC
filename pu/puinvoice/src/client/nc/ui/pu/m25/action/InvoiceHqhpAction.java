/**
 * $�ļ�˵��$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-11 ����02:29:00
 */
package nc.ui.pu.m25.action;

import java.awt.event.ActionEvent;

import nc.ui.pu.m25.rule.InvoiceQueryPriceHandler;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.vo.scmpub.res.SCMActionCode;

/**
 * <p>
 * �ɹ���Ʊ�����ż۰�ť���ܣ�����������
 * <ul>
 * <li>ԭ�ұ��ֵ��ڱ�λ�ң�
 * <li>������ⵥ���ɵģ�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-4-11 ����02:29:00
 */
public class InvoiceHqhpAction extends InvoiceNormalAction {

  private static final long serialVersionUID = -4075835748710745407L;

  public InvoiceHqhpAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.PU_HQHP);
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    // ע�ᣭѯ��Զ�̵��úϲ�
    InvoiceQueryPriceHandler priceHandler =
        new InvoiceQueryPriceHandler(this.getEditor().getBillCardPanel());
    priceHandler.prepareHqHpPrice();
    // �����ż۴���
    priceHandler.handleHqHpPrice();

  }

  @Override
  protected boolean isActionEnable() {
    // ֻ�б༭̬�ſ���
    return this.getModel().getAppUiState() == AppUiState.EDIT
        || this.getModel().getAppUiState() == AppUiState.ADD
        || AppUiState.TRANSFERBILL_ADD == this.getModel().getAppUiState();
  }

}
