/**
 * $�ļ�˵��$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-1 ����09:43:53
 */
package nc.ui.pu.m21.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m21.IOrderClose;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.UIState;
import nc.ui.uif2.editor.BillListView;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pu.m21.enumeration.EnumCloseFlag;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.IVOMeta;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.tool.BillIndex;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillToServer;
import nc.vo.scmpub.res.SCMActionCode;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�д�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-1 ����09:43:53
 */
public class RowOpenAction extends NCAction {

  private static final long serialVersionUID = 8482334456022240082L;

  private BillForm billForm;

  private BillListView list;

  private int[] selectIndex = null;


  public RowOpenAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_LINEOPEN);
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    this.getSelectIndex();

    this.doBeforeAction();

    OrderVO vo = this.getSelectedVO();

    if (null == vo) {
      return;
    }

    OrderVO oldVO = this.getOldVO();

    // ����VO����
    ClientBillToServer<OrderVO> tool = new ClientBillToServer<OrderVO>();
    OrderVO[] lightVOs = tool.construct(new OrderVO[] {
      vo
    }, new OrderVO[] {
      vo
    });

    // ִ��Զ�̵��ã������д�
    IOrderClose orderCloseService =
        NCLocator.getInstance().lookup(IOrderClose.class);
    OrderVO[] returnVos =
        orderCloseService.open(lightVOs,
            ((Integer) EnumCloseFlag.ROW_CLOSE.value()).intValue(), false);

    new ClientBillCombinServer<OrderVO>().combine(new OrderVO[] {
      oldVO
    }, returnVos);


    ShowStatusBarMsgUtil.showStatusBarMsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
			.getStrByID("4004000_0", "04004000-0162", null,
					new String[] {this.selectIndex.length + ""}), /*
					   											   * @res
					   											   * "�д򿪲����ɹ������򿪣����У�"
					   											   */
             this.billForm.getModel().getContext());
    
    this.getAppModel().directlyUpdate(oldVO);
  }

  /**
   * @param billForm
   *          Ҫ���õ� billForm
   */
  public void setBillForm(BillForm billForm) {
    this.billForm = billForm;
    billForm.getModel().addAppEventListener(this);
  }

  /**
   * @param list
   *          Ҫ���õ� list
   */
  public void setList(BillListView list) {
    this.list = list;
  }

  /**
   * ���������������ж���û��ѡ����
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-10 ����10:31:29
   */
  private void doBeforeAction() {
    if (ArrayUtils.isEmpty(this.selectIndex)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0065")/*
                                                                   * @res
                                                                   * "����ѡ����"
                                                                   */);
    }
  }

  /**
   * ���������������õ�model
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-6 ����04:16:31
   */
  private AbstractAppModel getAppModel() {
    return this.billForm.getModel();
  }

  /**
   * ���������������õ�model�еľ�VO
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-6 ����04:28:59
   */
  private OrderVO getOldVO() {
    OrderVO vo = (OrderVO) this.getAppModel().getSelectedData();
    return vo;
  }

  /**
   * ������������������ǿ�Ƭ���棬�ӿ�Ƭ��ȡ��������
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-4 ����12:24:04
   */
  private OrderVO getOpenVOFromBillForm() {
    if (ArrayUtils.isEmpty(this.selectIndex)) {
      return null;
    }

    // ��þ�VO��Ȼ�󽫱�����Ϊѡ���VO���õ�ѡ������VO
    OrderVO oldVO = this.getOldVO();
    if (null == oldVO) {
      return null;
    }
    OrderVO vo = (OrderVO) oldVO.clone();
    // Map<String, OrderItemVO> itemMap = AggVOUtil.createItemMap(new OrderVO[]
    // {
    // vo
    // });
    BillIndex index = new BillIndex(new OrderVO[] {
      vo
    });
    IVOMeta meta = vo.getMetaData().getVOMeta(OrderItemVO.class);
    OrderItemVO[] itemVOs = new OrderItemVO[this.selectIndex.length];
    for (int i = 0; i < this.selectIndex.length; ++i) {
      String pk_order_b =
          (String) this.billForm.getBillCardPanel().getBillModel()
              .getValueAt(this.selectIndex[i], OrderItemVO.PK_ORDER_B);
      // OrderItemVO itemVO = itemMap.get(pk_order_b);
      OrderItemVO itemVO = (OrderItemVO) index.get(meta, pk_order_b);
      if (null == itemVO) {
        return null;
      }
      itemVOs[i] = itemVO;
    }
    vo.setBVO(itemVOs);

    return vo;

  }

  /**
   * ��������������������б���棬���б��ȡ��������
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-4 ����03:42:55
   */
  private OrderVO getOpenVOFromListView() {
    if (ArrayUtils.isEmpty(this.selectIndex)) {
      return null;
    }

    // ��þ�VO��Ȼ�󽫱�����Ϊѡ���VO���õ�ѡ�����ݵ�VO
    OrderVO oldVO = this.getOldVO();
    if (null == oldVO) {
      return null;
    }
    OrderVO vo = (OrderVO) oldVO.clone();
    // Map<String, OrderItemVO> itemMap = AggVOUtil.createItemMap(new OrderVO[]
    // {
    // vo
    // });
    BillIndex index = new BillIndex(new OrderVO[] {
      vo
    });
    IVOMeta meta = vo.getMetaData().getVOMeta(OrderItemVO.class);
    OrderItemVO[] itemVOs = new OrderItemVO[this.selectIndex.length];
    for (int i = 0; i < this.selectIndex.length; ++i) {
      String pk_order_b =
          (String) this.list.getBillListPanel().getBodyBillModel()
              .getValueAt(this.selectIndex[i], OrderItemVO.PK_ORDER_B);
      // OrderItemVO itemVO = itemMap.get(pk_order_b);
      OrderItemVO itemVO = (OrderItemVO) index.get(meta, pk_order_b);
      if (null == itemVO) {
        return null;
      }
      itemVOs[i] = itemVO;
    }
    vo.setBVO(itemVOs);

    return vo;
  }

  /**
   * ���������������õ�Panel
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-10 ����10:21:05
   */
  private UIPanel getPanel() {
    UIPanel panel = null;

    // ��Ƭ����
    if (((ShowUpableBillForm) this.billForm).isComponentVisible()) {
      panel = this.billForm.getBillCardPanel();
    }
    else {// �б����
      panel = this.list.getBillListPanel();
    }
    return panel;
  }

  /**
   * ���������������õ�ѡ�����ݵ�VO
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-4 ����01:49:33
   */
  private OrderVO getSelectedVO() {
    OrderVO vo = null;

    // ��Ƭ����
    if (((ShowUpableBillForm) this.billForm).isComponentVisible()) {
      vo = this.getOpenVOFromBillForm();
    }
    else {// �б����
      vo = this.getOpenVOFromListView();
    }

    return vo;
  }

  /**
   * ���������������õ�ѡ�����
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-4-10 ����10:23:23
   */
  private void getSelectIndex() {
    // ��Ƭ����
    if (((ShowUpableBillForm) this.billForm).isComponentVisible()) {
      BillCardPanel panel = this.billForm.getBillCardPanel();
      this.selectIndex = panel.getBodyPanel().getTable().getSelectedRows();
    }
    else {// �б����
      BillListPanel panel = this.list.getBillListPanel();
      this.selectIndex = panel.getBodyTable().getSelectedRows();
    }
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.uif2.NCAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    if (UIState.ADD == this.billForm.getModel().getUiState()
        || UIState.EDIT == this.billForm.getModel().getUiState()) {
      return false;
    }
    this.getSelectIndex();

    if (ArrayUtils.isEmpty(this.selectIndex)) {
      return false;
    }

    OrderVO vo = this.getSelectedVO();

    if (null == vo) {
      return false;
    }
    if (POEnumBillStatus.APPROVE.toInt() != vo.getHVO().getForderstatus()
        .intValue()) {
      return false;
    }
    // ���ѡ�������һ��Ϊ����״̬���򷵻�false���������
    for (OrderItemVO itemVO : vo.getBVO()) {
      if (itemVO != null
          && itemVO.getFisactive().equals(EnumActive.ACTIVE.value())) {
        return false;
      }
    }

    return true;
  }

}
