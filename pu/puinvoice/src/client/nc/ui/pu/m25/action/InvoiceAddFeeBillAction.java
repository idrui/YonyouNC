/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-11 下午04:36:46
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
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>维护费用发票动作
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-3-11 下午04:36:46
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
    // this.setBtnName("费用发票");
    // this.setCode("费用发票");
    // this.putValue(Action.SHORT_DESCRIPTION, "维护费用发票");
  }

  /**
   * 父类方法重写
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

    // 设置部门以及vid
    new SetPeptRule(helper, this.model.getContext(), InvoiceHeaderVO.PK_BIZPSN,
        InvoiceHeaderVO.PK_DEPT, InvoiceHeaderVO.PK_DEPT_V).setPsnAndDept();

    List<IPURemoteCallCombinator> rccRuleLst =
        new ArrayList<IPURemoteCallCombinator>();

    // 注册可合并的远程调用
    this.registerRccRule(rccRuleLst, helper);
    // 执行远程调用规则
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
    // 支持批量远程调用－确定业务流程－注册
    this.regiest_bizRule(rccRuleLst);
    this.register_VatDefaultValueRule(rccRuleLst, helper);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    InvoiceVO vo = (InvoiceVO) this.getModel().getSelectedData();
    // 界面中有货物物发票时，且货物发票保存后，未审批前可用
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
