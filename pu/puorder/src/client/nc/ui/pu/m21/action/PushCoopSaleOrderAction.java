/**
 * $�ļ�˵��$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-26 ����11:30:00
 */
package nc.ui.pu.m21.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m21.IOrderRelateFuncQuery;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.BillManageModel;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillToServer;
import nc.vo.scmpub.res.SCMActionCode;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>����Эͬ���۶���
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-26 ����11:30:00
 */
public class PushCoopSaleOrderAction extends NCAction {

  private static final long serialVersionUID = -3771521609942451520L;

  private BillManageModel model;

  public PushCoopSaleOrderAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.PU_COOPTO30);
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    if (!SysInitGroupQuery.isSOEnabled()) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0054")/*
                                                                   * @res
                                                                   * "����δ����"
                                                                   */);
    }
    Object[] objs = this.model.getSelectedOperaDatas();
    if (ArrayUtils.isEmpty(objs)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0036")/*
                                                                   * @res
                                                                   * "��ѡ������"
                                                                   */);
    }

    OrderVO[] vos = ArrayUtil.convertArrayType(objs);
    ClientBillToServer<OrderVO> tool = new ClientBillToServer<OrderVO>();
    OrderVO[] lightVOs = tool.construct(vos, vos);
    try {
      IOrderRelateFuncQuery query =
          NCLocator.getInstance().lookup(IOrderRelateFuncQuery.class);
      OrderVO[] returnVos = query.pushCoop30(lightVOs);
      new ClientBillCombinServer<OrderVO>().combine(vos, returnVos);
      this.getModel().directlyUpdate(vos);
    }
    catch (Exception ex) {
      ExceptionUtils.wrappException(ex);
    }

    ShowStatusBarMsgUtil.showStatusBarMsg(
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
            "04004030-0366")/* "��������Эͬ�����ɹ�" */, this.getModel().getContext());
  }

  public BillManageModel getModel() {
    return this.model;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.uif2.NCAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    if (UIState.ADD == this.model.getUiState()
        || UIState.EDIT == this.model.getUiState()) {
      return false;
    }
    Object[] tempData = this.model.getSelectedOperaDatas();
    if (ArrayUtils.isEmpty(tempData) || tempData.length > 1) {
      return false;
    }

    OrderVO orderVO = (OrderVO) tempData[0];
    return orderVO != null
        && orderVO.getHVO() != null
        && UFBoolean.FALSE.equals(orderVO.getHVO().getBcooptoso())
        && POEnumBillStatus.APPROVE.value().equals(
            orderVO.getHVO().getForderstatus());
  }
}
