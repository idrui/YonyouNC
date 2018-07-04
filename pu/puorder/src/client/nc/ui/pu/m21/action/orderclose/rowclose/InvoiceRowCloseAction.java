/**
 * $�ļ�˵��$
 *
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-9 ����08:33:45
 */
package nc.ui.pu.m21.action.orderclose.rowclose;

import java.awt.event.ActionEvent;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m21.IOrderClose;
import nc.ui.pu.m21.action.orderclose.AfterOrderCloseAction;
import nc.ui.pu.m21.service.OrderCloseManageModel;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.vo.pu.m21.entity.OrderCloseVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.enumeration.EnumCloseFlag;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillToServer;
import nc.vo.scmpub.res.SCMActionCode;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��Ʊ�йر�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-4-9 ����08:33:45
 */
public class InvoiceRowCloseAction extends AfterOrderCloseAction {

  /**
   *
   */
  private static final long serialVersionUID = -5514317664850860911L;

  private OrderCloseManageModel model = null;

  public InvoiceRowCloseAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_INVOICECLOSE);
    // this.setBtnName("��Ʊ�ر�");
    // this.setCode("invoiceRowClose");

  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    this.doBeforeAction();

    OrderVO[] vos = OrderCloseVO.getOrderVO(this.model.getSelectedCloseVO());

    ClientBillToServer<OrderVO> tool = new ClientBillToServer<OrderVO>();
    OrderVO[] lightVOs = tool.construct(vos, vos);

    // ִ��Զ�̵���,�����йر�
    IOrderClose orderCloseService =
        NCLocator.getInstance().lookup(IOrderClose.class);
    OrderVO[] returnVos =
        orderCloseService.close(lightVOs,
            ((Integer) EnumCloseFlag.INVOICE_CLOSE.value()).intValue(), false);

    new ClientBillCombinServer<OrderVO>().combine(vos, returnVos);

    // ��Ʊ�رտ��ܵ��������ر�,��������������
    Map<Integer, OrderCloseVO> mapAllClose = this.model.getAllCloseRow();

    this.model
        .directlyUpdate(mapAllClose.values().toArray(new OrderCloseVO[0]));

  }

  public OrderCloseManageModel getModel() {
    return this.model;
  }

  public void setModel(OrderCloseManageModel model) {
    this.model = model;
    this.model.addAppEventListener(this);
  }

  private void doBeforeAction() {
    if (ArrayUtils.isEmpty(this.model.getSelectedOperaDatas())) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0013")/*
                                                                   * @res
                                                                   * "����ѡ��Ʊ�ر���"
                                                                   */);
    }
  }

  @Override
  protected boolean isActionEnable() {

    // �Ƿ���ѡ�е���
    OrderCloseVO[] orderVOs = this.model.getSelectedCloseVO();
    if (ArrayUtils.isEmpty(orderVOs)) {
      return false;
    }

    for (OrderCloseVO vo : orderVOs) {
      if (!vo.getBinvoiceclose().booleanValue()) {
        return true;
      }
    }
    return false;
  }

}
