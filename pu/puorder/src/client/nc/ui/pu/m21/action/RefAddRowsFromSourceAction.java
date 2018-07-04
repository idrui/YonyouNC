/**
 * $�ļ�˵��$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-4 ����03:15:47
 */
package nc.ui.pu.m21.action;

import java.awt.event.ActionEvent;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pub.pf.PfUtilClient;
import nc.ui.pubapp.uif2app.actions.AbstractReferenceAction;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.pubapp.uif2app.view.util.BillRowNoUtils;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderPaymentVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.rule.CurrencyAndExchangerate;
import nc.vo.pu.m21.rule.OrganizationDefaultValue;
import nc.vo.pu.m21.rule.vat.OrderVatInfoSrcFillRule;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.BusinessRuntimeException;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-5-4 ����03:15:47
 */
public class RefAddRowsFromSourceAction extends AbstractReferenceAction {

  private static final long serialVersionUID = -2975120146005943998L;

  private BillForm editor;

  private AbstractAppModel model;

  public RefAddRowsFromSourceAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.PU_REFADDROWS);

  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    if (!this.isFromPray()) {
      throw new BusinessRuntimeException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0061")/*
                                                                   * @res
                                                                   * "��ǰҵ������û���빺����Դ���޷�������"
                                                                   */);
    }
    this.editor.getBillCardPanel().getBodyTabbedPane().setSelectedIndex(0);
    PfUtilClient.childButtonClicked(this.getSourceBillType(), this.getModel()
        .getContext().getPk_group(), this.getModel().getContext()
        .getPk_loginUser(), POBillType.Order.getCode(), this.getModel()
        .getContext().getEntranceUI(), null, null);
    if (PfUtilClient.isCloseOK()) {
      OrderVO[] vos = (OrderVO[]) PfUtilClient.getRetVos();
      if (ArrayUtils.isEmpty(vos)) {
        return;
      }

      this.fillInformation(vos);
    }
  }

  /**
   * @return editor
   */
  public BillForm getEditor() {
    return this.editor;
  }

  /**
   * @return model
   */
  public AbstractAppModel getModel() {
    return this.model;
  }

  /**
   * ���෽����д
   * 
   * @see javax.swing.AbstractAction#isEnabled()
   */
  @Override
  public boolean isEnabled() {
    return (this.getModel().getUiState() == UIState.ADD || this.getModel()
        .getUiState() == UIState.EDIT)
        && POBillType.PrayBill.getCode().equals(this.getSourceBillType());
  }

  /**
   * @param editor Ҫ���õ� editor
   */
  public void setEditor(BillForm editor) {
    this.editor = editor;
  }

  /**
   * @param model Ҫ���õ� model
   */
  public void setModel(AbstractAppModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.pubapp.uif2app.actions.AbstractReferenceAction#setSourceBillName(java.lang.String)
   */
  @Override
  public void setSourceBillName(String sourceBillName) {
    super.setSourceBillName(sourceBillName);
    this.setBtnName(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004030_0", "04004030-0062")/* @res "��������" */);
  }

  /**
   * ��������ʱ�����ݹ�Ӧ�̲���Ĭ����Ϣ
   * 
   * @param vo
   */
  private void fillBodyInfoBySupplier(OrderVO vo) {
    for (OrderItemVO item : vo.getBVO()) {
      // ��Ӧ�̷�����ַ
      if (!StringUtils.isEmpty(vo.getHVO().getPk_deliveradd())) {
        item.setVvenddevaddr(vo.getHVO().getPk_deliveradd());
      }
    }
  }

  private void fillInformation(OrderVO[] vos) {
    OrderVO curVO =
        (OrderVO) this.editor.getBillCardPanel().getBillValueVO(
            OrderVO.class.getName(), OrderHeaderVO.class.getName(),
            OrderItemVO.class.getName());
    // ����Э��
    OrderPaymentVO[] paymentVO =
        (OrderPaymentVO[]) this.editor.getBillCardPanel()
            .getBillModel(OrderPaymentVO.TABSNAME)
            .getBodyValueVOs(OrderPaymentVO.class.getName());
    OrderHeaderVO headerVO = curVO.getHVO();
    String[] headNotNullKeys =
        new String[] {
          OrderHeaderVO.PK_BUSITYPE, OrderHeaderVO.PK_SUPPLIER,
          OrderHeaderVO.PK_ORG
        };

    boolean existDifferItem =
        CirVOUtil
            .existDifferNotNullItems(vos, headerVO, headNotNullKeys, false);

    if (existDifferItem) {
      ShowStatusBarMsgUtil.showStatusBarMsg(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0063")/*
                                                                   * @res
                                                                   * "׷����Ϣ��ͷ�ֶ�ҵ�����ͣ���Ӧ�̣��ɹ���֯�͵�ǰ������ͷ����ͬ��ֵ������׷�ӵ���ǰ����"
                                                                   */, this
          .getModel().getContext());
      return;
    }

    OrderVO tempVO = new OrderVO();
    tempVO.setHVO(curVO.getHVO());
    OrderItemVO[] tempItemVOs = AggVOUtil.getCombinItemVOs(vos);
    tempVO.setBVO(tempItemVOs);

    BillHelper<OrderVO> helper = new BillHelper<OrderVO>(tempVO);

    int[] rows = new int[tempItemVOs.length];
    for (int i = 0; i < tempItemVOs.length; ++i) {
      rows[i] = i;
    }

    // ������֯��ص���Ϣ
    new OrganizationDefaultValue(helper).setDefaultOrganizationValue(rows);

    // ���䱾λ�Һͻ��ʵ������Ϣ
    new CurrencyAndExchangerate(helper).setCurrencyAndExchangeRate(rows);
    // ��������Ϣ
    this.fillBodyInfoBySupplier(tempVO);
    // vat ��Ϣ����
    OrderVatInfoSrcFillRule vatRule = new OrderVatInfoSrcFillRule();
    vatRule.setResetVat(true);// Ҫ�����ң�vo���չ����Ĳ�һ������ǰһ��
    vatRule.process(new OrderVO[] {
      tempVO
    });
    OrderItemVO[] itemVOs =
        ArrayUtil.combinArrays(curVO.getBVO(), tempVO.getBVO());
    curVO.setBVO(itemVOs);
    curVO.setChildren(OrderPaymentVO.class, paymentVO);

    int len = itemVOs.length;
    for (int i = 0; i < len; ++i) {
      itemVOs[i].setCrowno(null);
    }

    this.getEditor().setValue(curVO);

    this.setCrowno(len);
  }

  private boolean isFromPray() {
    BillCardPanel panel = this.getEditor().getBillCardPanel();
    // ���������Ϣҳǩ��model
    BillModel material = panel.getBillModel("material");
    int rowcount = material.getRowCount();
    if (rowcount == 0) {
      return false;
    }
    for (int i = 0; i < rowcount; ++i) {
      Object obj = material.getValueAt(i, OrderItemVO.CSOURCETYPECODE);
      if (null == obj) {
        continue;
      }

      if (POBillType.PrayBill.getCode().equals(obj)) {
        return true;
      }
    }
    return false;
  }

  private void setCrowno(int len) {
    BillRowNoUtils.addLineRowNos(this.getEditor().getBillCardPanel(),
        OrderItemVO.CROWNO, len);
  }

  /**
   * �ж����֮ǰ�Ĳ��ղ����빺�����Ͳ�����
   */
  @Override
  protected boolean isActionEnable() {
    return this.isFromPray();
  }

}
