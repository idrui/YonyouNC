/**
 * $�ļ�˵��$
 *
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-9 ����08:43:55
 */
package nc.ui.pu.m21.action.orderclose.allopen;

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
 * <li>������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-4-9 ����08:43:55
 */
public class FinalAllOpenAction extends AfterOrderCloseAction {

  /**
   *
   */
  private static final long serialVersionUID = 1919641734440842888L;

  private OrderCloseManageModel model = null;

  public FinalAllOpenAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_BILLOPEN);
    // this.setBtnName("������");
    // this.setCode("finalAllOpen");

  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    // �����û��ѡ��������
    this.doBeforeAction();

    OrderVO[] vos =
        OrderCloseVO.getOrderVO(this.model.getAllCloseRow().values()
            .toArray(new OrderCloseVO[0]));

    ClientBillToServer<OrderVO> tool = new ClientBillToServer<OrderVO>();
    OrderVO[] lightVOs = tool.construct(vos, vos);

    // ִ��Զ�̵��ã�����������
    IOrderClose orderCloseService =
        NCLocator.getInstance().lookup(IOrderClose.class);
    OrderVO[] returnVos =
        orderCloseService.open(lightVOs,
            ((Integer) EnumCloseFlag.FINAL_CLOSE.value()).intValue(), true);

    new ClientBillCombinServer<OrderVO>().combine(vos, returnVos);

    // ���´���
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
          .getNCLangRes().getStrByID("4004030_0", "04004030-0008")/*
                                                                   * @res
                                                                   * "����ѡ��Ҫ��������"
                                                                   */);
    }
  }

  @Override
  // ��ť�û�
  protected boolean isActionEnable() {

    // �Ƿ���ѡ�е���
    Map<Integer, OrderCloseVO> orderCloseMap = this.model.getAllCloseRow();

    OrderCloseVO[] orderVOs = null;

    if (orderCloseMap != null) {
      orderVOs = orderCloseMap.values().toArray(new OrderCloseVO[0]);
    }

    if (null == orderVOs || 0 == orderVOs.length) {
      return false;
    }

    for (OrderCloseVO vo : orderVOs) {
      if (vo.getBarriveclose().booleanValue()
          || vo.getBpayclose().booleanValue()
          || vo.getBinvoiceclose().booleanValue()
          || vo.getBstockclose().booleanValue()) {
        return true;
      }
    }
    return false;

  }

}