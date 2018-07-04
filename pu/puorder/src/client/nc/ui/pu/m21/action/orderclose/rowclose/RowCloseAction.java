/**
 * $�ļ�˵��$
 *
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-8 ����06:17:53
 */
package nc.ui.pu.m21.action.orderclose.rowclose;

import java.awt.event.ActionEvent;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m21.IOrderClose;
import nc.ui.pu.m21.action.orderclose.AfterOrderCloseAction;
import nc.ui.pu.m21.service.OrderCloseManageModel;
import nc.ui.pubapp.pub.power.PowerCheckUtils;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.vo.pu.m21.entity.OrderCloseVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.enumeration.EnumCloseFlag;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillToServer;
import nc.vo.pubapp.pub.power.PowerActionEnum;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�йر�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-4-8 ����06:17:53
 */
public class RowCloseAction extends AfterOrderCloseAction {

  /**
   *
   */
  private static final long serialVersionUID = -5214849177013546292L;

  private OrderCloseManageModel model = null;

  public RowCloseAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_LINECLOSE);
    // this.setBtnName("�йر�");
    // this.setCode("rowClose");
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

    PowerCheckUtils.checkHasPermission(vos, POBillType.Order.getCode(),
        PowerActionEnum.CLOSE.getActioncode(), OrderHeaderVO.VBILLCODE);

    ClientBillToServer<OrderVO> tool = new ClientBillToServer<OrderVO>();
    OrderVO[] lightVOs = tool.construct(vos, vos);

    // ִ��Զ�̵���,�����йر�
    IOrderClose orderCloseService =
        NCLocator.getInstance().lookup(IOrderClose.class);
    OrderVO[] returnVos =
        orderCloseService.close(lightVOs,
            ((Integer) EnumCloseFlag.ROW_CLOSE.value()).intValue(), false);

    new ClientBillCombinServer<OrderVO>().combine(vos, returnVos);

    // �йرտ��ܵ��������ر�,��������������
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
          .getNCLangRes().getStrByID("4004030_0", "04004030-0015")/*
                                                                   * @res
                                                                   * "����ѡ��Ҫ�йر���"
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
      if (!(vo.getBarriveclose().booleanValue()
          && vo.getBinvoiceclose().booleanValue()
          && vo.getBpayclose().booleanValue() && vo.getBstockclose()
          .booleanValue())) {
        return true;
      }
    }
    return false;
  }

}
