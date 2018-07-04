/**
 *
 */
package nc.ui.pu.m25.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.itf.uap.pf.busiflow.PfButtonClickContext;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.util.BusiDateSetter;
import nc.ui.pub.pf.PfUtilClient;
import nc.ui.pubapp.uif2app.actions.AbstractReferenceAction;
import nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeFuncUtils;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.uif2.UIState;
import nc.ui.uif2.editor.IBillCardPanelEditor;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pu.pub.util.ListUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.Log;
import nc.vo.pubapp.util.VORowNoUtils;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.res.billtype.SCBillType;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * �ɹ���Ʊ���հ�ť
 * 
 * @since 6.0
 * @version 2010-11-11 ����10:38:42
 * @author tianft
 */
public abstract class InvoiceRefAddAction extends AbstractReferenceAction {

  private static final long serialVersionUID = -7167526730230052116L;

  // ��Ƭ���
  private IBillCardPanelEditor editor;

  // model
  private BillManageModel model;

  public InvoiceRefAddAction() {
    super();
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    PfUtilClient.childButtonClickedNew(this.createPfButtonClickContext());
    if (PfUtilClient.isCloseOK()) {
      InvoiceVO[] retvos = (InvoiceVO[]) PfUtilClient.getRetVos();
      if (retvos == null || retvos.length <= 0) {
        return;
      }

      BusiDateSetter busidateSetter = new BusiDateSetter();
      // ��������
      busidateSetter.setHeaderBusiDate(retvos, InvoiceHeaderVO.DBILLDATE);
      // Ʊ������
      busidateSetter.setHeaderBusiDate(retvos, InvoiceHeaderVO.DARRIVEDATE);
      this.getTransferViewProcessor().processBillTransfer(retvos);
    }
  }

  public IBillCardPanelEditor getEditor() {
    return this.editor;
  }

  /**
   * @return model
   */
  public BillManageModel getModel() {
    return this.model;
  }

  public void setEditor(IBillCardPanelEditor editor) {
    this.editor = editor;
  }

  /**
   * @param model
   *          Ҫ���õ� model
   */
  public void setModel(BillManageModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  /**
   * �޸Ķ�ģ��ǿ��ֶ������Ե�У��Ϊ����OID���������ڵ����汾��֮����������°汾��һ������
   * Ŀǰ������ֶΣ�������֯
   * 
   * @param notNullKey �ǿ��ֶ������б�
   */
  private void changeNotNullKey2OID(String[] notNullKey) {
    if (ArrayUtils.isEmpty(notNullKey)) {
      return;
    }
    for (int i = 0; i < notNullKey.length; i++) {
      if (notNullKey[i] != null && InvoiceItemVO.PK_ORG_V.equals(notNullKey[i])) {
        notNullKey[i] = InvoiceItemVO.PK_ORG;
        break;
      }
    }
  }

  /**
   * ��ҵ�����̵ļ��,�Ƿ���ֱ����Դ
   * 
   * @param pk_busitype
   * @return
   */
  private boolean checkBusiType(String pk_busitype, String srcBilltype) {
    if (StringUtils.isEmpty(pk_busitype)) {
      return false;
    }
    String msg = "";
    if (POBillType.Order.getCode().equals(srcBilltype)) {
      msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0118")/*
                               * @res
                               * "��Ʊ������Դ�ڲɹ����������ܲ��ղɹ��������У�"
                               */;
    }
    else if (ICBillType.PurchaseIn.getCode().equals(srcBilltype)) {
      msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0119")/*
                               * @res
                               * "��Ʊ������Դ�ڲɹ���ⵥ�����ܲ��ղɹ���ⵥ���У�";
                               */;
    }
    else if (ICBillType.SubContinIn.getCode().equals(srcBilltype)) {
      msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0131")/*
                               * @res
                               * "��Ʊ������Դ��ί�мӹ���ⵥ�����ܲ���ί�мӹ���ⵥ���У�";
                               */;
    }
    else if (POBillType.InitEstimate.getCode().equals(srcBilltype)) {
      msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0132")/*
                               * @res
                               * "��Ʊ������Դ���ڳ��ݹ��������ܲ����ڳ��ݹ������У�";
                               */;
    }
    else if (ICBillType.VmiSum.getCode().equals(srcBilltype)) {
      msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0133")/*
                               * @res
                               * "��Ʊ������Դ�����Ļ��ܣ����ܲ������Ļ������У�";
                               */;
    }
    else if (SCBillType.Order.getCode().equals(srcBilltype)) {
      msg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004050_0",
              "04004050-0130")/*
                               * @res
                               * "��Ʊ������Դ��ί�ⶩ�������ܲ���ί�ⶩ�����У�";
                               */;
    }
    int rowCount = this.getEditor().getBillCardPanel().getRowCount();
    String sourcetypecode = null;
    for (int i = 0; i < rowCount; i++) {
      Object bodyValueAt =
          this.getEditor().getBillCardPanel()
              .getBodyValueAt(i, InvoiceItemVO.CSOURCETYPECODE);
      sourcetypecode = bodyValueAt == null ? null : (String) bodyValueAt;
      if (null != sourcetypecode) {
        break;
      }
    }
    if (null == sourcetypecode || sourcetypecode.equals(srcBilltype)) {
      return true;
    }
    ExceptionUtils.wrappBusinessException(msg);
    return false;
  }

  private PfButtonClickContext createAddRowPfButtonClickContext(
      List<String> busiType) {
    PfButtonClickContext context = new PfButtonClickContext();
    context.setParent(this.getModel().getContext().getEntranceUI());
    context.setSrcBillType(this.getSrcBillTypeCode());
    context.setPk_group(this.getModel().getContext().getPk_group());
    context.setUserId(this.getModel().getContext().getPk_loginUser());
    // ����ýڵ����ɽ������ͷ����ģ���ô�������Ӧ�ô��������ͣ����򴫵�������
    String vtrantype =
        TrantypeFuncUtils.getTrantype(this.getModel().getContext());
    if (StringUtil.isEmptyWithTrim(vtrantype)) {
      context.setCurrBilltype(POBillType.Invoice.getCode());
    }
    else {
      context.setCurrBilltype(vtrantype);
    }
    context.setUserObj(null);
    context.setSrcBillId(null);
    context.setBusiTypes(busiType);
    // ����Ĳ�����ԭ�����õķ����ж����漰��ֻ���������һ�����ṹ�����������������¼ӵĲ���
    // ���εĽ������ͼ���,��������ֻҪ�пյ�����Ͳ���set
    if (!ListUtil.isEmpty(this.getTranstypes())
        && !this.getTranstypes().contains("")) {
      context.setTransTypes(this.getTranstypes());
    }
    // ��־�ڽ�������Ŀ�Ľ������ͷ���ʱ������Ŀ�Ľ������͵����ݣ�������������ֵ��1�����ݽӿڶ��壩��
    // 2�������������ã���-1�������ݽ������ͷ��飩
    context.setClassifyMode(PfButtonClickContext.ClassifyByBusiflow);
    return context;
  }

  private PfButtonClickContext createPfButtonClickContext() {
    PfButtonClickContext context = new PfButtonClickContext();
    context.setParent(this.getModel().getContext().getEntranceUI());
    context.setSrcBillType(this.getSourceBillType());
    context.setPk_group(this.getModel().getContext().getPk_group());
    context.setUserId(this.getModel().getContext().getPk_loginUser());
    // ����ýڵ����ɽ������ͷ����ģ���ô�������Ӧ�ô��������ͣ����򴫵�������
    String vtrantype =
        TrantypeFuncUtils.getTrantype(this.getModel().getContext());
    if (StringUtil.isEmptyWithTrim(vtrantype)) {
      context.setCurrBilltype(POBillType.Invoice.getCode());
    }
    else {
      context.setCurrBilltype(vtrantype);
    }
    context.setUserObj(null);
    context.setSrcBillId(null);
    context.setBusiTypes(this.getBusitypes());
    // ����Ĳ�����ԭ�����õķ����ж����漰��ֻ���������һ�����ṹ�����������������¼ӵĲ���
    // ���εĽ������ͼ���,��������ֻҪ�пյ�����Ͳ���set
    if (!ListUtil.isEmpty(this.getTranstypes())
        && !this.getTranstypes().contains("")) {
      context.setTransTypes(this.getTranstypes());
    }
    // ��־�ڽ�������Ŀ�Ľ������ͷ���ʱ������Ŀ�Ľ������͵����ݣ�������������ֵ��1�����ݽӿڶ��壩��
    // 2�������������ã���-1�������ݽ������ͷ��飩
    context.setClassifyMode(PfButtonClickContext.ClassifyByBusiflow);
    return context;
  }

  /**
   * �������д���
   * 
   * @throws Exception
   */
  protected void doRefAddRowAction() throws Exception {
    // ���ݵ�ǰ�����жϿɷ����
    Object curVo = this.getEditor().getValue();
    if (curVo == null) {
      return;
    }
    InvoiceVO invoiceVO = (InvoiceVO) curVo;
    // ҵ������
    String pk_busitype = invoiceVO.getParentVO().getPk_busitype();
    if (StringUtils.isBlank(pk_busitype)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004050_0", "04004050-0005")/*
                                                                   * @res
                                                                   * "���Ʒ�Ʊ�޷��������У�"
                                                                   */);
    }
    if (!this.checkBusiType(pk_busitype, this.getSrcBillTypeCode())) {
      return;
    }
    List<String> busiType = new ArrayList<String>();
    busiType.add(pk_busitype);
    PfUtilClient.childButtonClickedNew(this
        .createAddRowPfButtonClickContext(busiType));
    if (PfUtilClient.isCloseOK()) {
      InvoiceVO[] retvos = (InvoiceVO[]) PfUtilClient.getRetVos();
      if (ArrayUtils.isEmpty(retvos)) {
        return;
      }
      // �����������½��м��㣬���ת����������ͬ
      // InvoiceVOUtil.reCalculateBasedNum(retvos);
      CardEditorHelper helper =
          new CardEditorHelper(this.getEditor().getBillCardPanel());
      // ��¡һ�ݣ������ȡ����������ݲ��ܼ�ʱˢ��
      InvoiceVO curVO = (InvoiceVO) invoiceVO.clone();
      InvoiceHeaderVO curHeaderVO = curVO.getParentVO();
      // ��ǰģ���ͷ�ǿ��ֶ�key
      String[] notNullKey = helper.getNotNullHeadItemKeys();
      // �޸Ķ�ģ��ǿ��ֶ������Ե�У��Ϊ����OID���������ڵ����汾��֮����������°汾��һ������
      this.changeNotNullKey2OID(notNullKey);
      for (String key : notNullKey) {
        Log.debug(" InvoiceRefAddAction:not null key =" + key);
        Log.debug(" \n");
      }
      // ��Դ�ı�ͷ�ǿ��ֶ�����뵱ǰ��ͷ�ǿ��ֶ�ֵ��һ�µĻ��������У��������������ֶ�
      boolean existDifferItem =
          CirVOUtil.existDifferNotNullItems(retvos, curHeaderVO, notNullKey,
              false);
      if (existDifferItem) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004050_0", "04004050-0006")/*
                                                                     * @res
                                                                     * "��Դ��ͷ�����뵱ǰ��ͷ���ݲ�һ�£��޷��������У�"
                                                                     */);
      }
      InvoiceItemVO[] combinItemVOs =
          AggVOUtil.getCombinItemVOs(retvos, invoiceVO.getChildrenVO());
      curVO.setChildrenVO(combinItemVOs);
      VORowNoUtils.setVOsRowNoByRule(new InvoiceVO[] {
        curVO
      }, InvoiceItemVO.CROWNO);
      this.getEditor().setValue(curVO);
    }
  }

  protected String getSrcBillTypeCode() {
    return null;
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.uif2.NCAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    return UIState.NOT_EDIT == this.getModel().getUiState();
  }

  /**
   * ת���Ƿ�������̹���
   * 
   * @return
   */
  protected boolean isBusiness() {
    return true;
  }

}
