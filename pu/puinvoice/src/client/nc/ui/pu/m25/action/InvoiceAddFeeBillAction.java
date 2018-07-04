/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-11 ����04:36:46
 */
package nc.ui.pu.m25.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.ui.pu.m25.model.InvoiceBillManageModel;
import nc.ui.pu.m25.view.InvoiceUIState;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.util.BusitypeSetter;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.editor.IBillCardPanelEditor;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.rule.maintain.DefaultRececountrySetter;
import nc.vo.pu.m25.rule.maintain.DefaultSendcountrySetter;
import nc.vo.pu.m25.rule.maintain.DefaultTaxcountrySetter;
import nc.vo.pu.m25.rule.maintain.InvoiceVatDefaultValueFillRule;
import nc.vo.pu.m25.rule.maintain.InvoiceVatDefaultValueFillRule.ICountrySetter;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.pub.rule.SetPeptRule;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>ά�����÷�Ʊ����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-3-11 ����04:36:46
 */
public class InvoiceAddFeeBillAction extends NCAction {

  /**
   * 
   */
  private static final long serialVersionUID = -5697839450574985023L;

  private IBillCardPanelEditor editor;

  private InvoiceBillManageModel model = null;

  public InvoiceAddFeeBillAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.PU_FEEINVOICE);
    // this.setBtnName("���÷�Ʊ");
    // this.setCode("���÷�Ʊ");
    // this.putValue(Action.SHORT_DESCRIPTION, "ά�����÷�Ʊ");
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    this.getModel().backupNormContext();
    this.getModel().setAppUiState(InvoiceUIState.FEE_INVC);
    this.getModel().setAppUiState(AppUiState.ADD);

    CardEditorHelper helper =
        new CardEditorHelper(this.getEditor().getBillCardPanel());

    // ���ò����Լ�vid
    new SetPeptRule(helper, this.model.getContext(), InvoiceHeaderVO.PK_BIZPSN,
        InvoiceHeaderVO.PK_DEPT, InvoiceHeaderVO.PK_DEPT_V).setPsnAndDept();

    List<IPURemoteCallCombinator> rccRuleLst =
        new ArrayList<IPURemoteCallCombinator>();

    // ע��ɺϲ���Զ�̵���
    this.registerRccRule(rccRuleLst, helper);
    // ִ��Զ�̵��ù���
    this.doRccRule(rccRuleLst);
  }

  public IBillCardPanelEditor getEditor() {
    return this.editor;
  }

  public InvoiceBillManageModel getModel() {
    return this.model;
  }

  public void setEditor(IBillCardPanelEditor editor) {
    this.editor = editor;
  }

  public void setModel(InvoiceBillManageModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  private void doRccRule(List<IPURemoteCallCombinator> rccRuleLst) {
    for (IPURemoteCallCombinator rccRule : rccRuleLst) {
      if (null != rccRule) {
        rccRule.process();
      }
    }
  }

  private void regiest_bizRule(List<IPURemoteCallCombinator> rccRuleLst) {
    CardEditorHelper billhelper =
        new CardEditorHelper(this.editor.getBillCardPanel());
    new BusitypeSetter(POBillType.Invoice, billhelper, this.model.getContext())
        .manualAddSet(rccRuleLst);
  }

  private void register_VatDefaultValueRule(
      List<IPURemoteCallCombinator> rccRuleLst, CardEditorHelper helper) {
    IKeyValue[] billhelpers = new CardEditorHelper[] {
      helper
    };
    List<ICountrySetter> csetterLst =
        new ArrayList<InvoiceVatDefaultValueFillRule.ICountrySetter>();
    csetterLst.add(new DefaultSendcountrySetter());
    csetterLst.add(new DefaultRececountrySetter());
    csetterLst.add(new DefaultTaxcountrySetter());
    IPURemoteCallCombinator rccrule =
        new InvoiceVatDefaultValueFillRule(billhelpers, csetterLst);
    rccrule.prepare();
    rccRuleLst.add(rccrule);
  }

  private void registerRccRule(List<IPURemoteCallCombinator> rccRuleLst,
      CardEditorHelper helper) {
    // ֧������Զ�̵��ã�ȷ��ҵ�����̣�ע��
    this.regiest_bizRule(rccRuleLst);
    this.register_VatDefaultValueRule(rccRuleLst, helper);
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.uif2.NCAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    InvoiceVO vo = (InvoiceVO) this.getModel().getSelectedData();
    // �������л����﷢Ʊʱ���һ��﷢Ʊ�����δ����ǰ����
    if (null == vo) {
      return false;
    }
    if (!vo.getParentVO().getBfee().booleanValue()
        && POEnumBillStatus.APPROVE.toInt() != vo.getParentVO()
            .getFbillstatus().intValue()) {
      return true;
    }
    return false;
  }

}
