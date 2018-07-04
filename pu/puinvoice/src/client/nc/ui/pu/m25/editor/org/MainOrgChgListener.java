/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-1 上午11:19:36
 */
package nc.ui.pu.m25.editor.org;

import java.util.ArrayList;
import java.util.List;

import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.ui.pu.m25.editor.utils.InvoiceUIScaleProcessor;
import nc.ui.pu.m25.view.InvoiceBillForm;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.org.IOrgChangedEventListener;
import nc.ui.pu.pub.util.BusitypeSetter;
import nc.ui.pu.pub.util.PuVDefFilterUtil;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.model.IAppModelEx;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.editor.BillForm;
import nc.vo.pu.m25.rule.maintain.DefaultRececountrySetter;
import nc.vo.pu.m25.rule.maintain.DefaultSendcountrySetter;
import nc.vo.pu.m25.rule.maintain.DefaultTaxcountrySetter;
import nc.vo.pu.m25.rule.maintain.InvoiceVatDefaultValueFillRule;
import nc.vo.pu.m25.rule.maintain.InvoiceVatDefaultValueFillRule.ICountrySetter;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>主组织改变处理,此类只处理主组织改变应该处理的事情，类似于默认值设置等应该由其他处理类处理
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-3-1 上午11:19:36
 */
public class MainOrgChgListener implements IOrgChangedEventListener {

  @Override
  public void process(ShowUpableBillForm cardForm) {

    if (cardForm instanceof InvoiceBillForm) {
      InvoiceBillForm billform = (InvoiceBillForm) cardForm;
      // 初始化精度
      InvoiceUIScaleProcessor scaler = new InvoiceUIScaleProcessor();
      scaler.orgChgScale(billform);
      if (AppUiState.ADD.equals(((IAppModelEx) billform.getModel())
          .getAppUiState())) {
        billform.addNew();

        List<IPURemoteCallCombinator> rccRuleLst =
            new ArrayList<IPURemoteCallCombinator>();
        // 注册可合并的远程调用
        this.registerRccRule(rccRuleLst, cardForm);
        // 执行远程调用规则
        this.doRccRule(rccRuleLst);
      }
      new PuVDefFilterUtil().setOrgForDefRef(cardForm.getBillCardPanel(),
          cardForm.getModel().getContext());
    }
  }

  private void doRccRule(List<IPURemoteCallCombinator> rccRuleLst) {
    for (IPURemoteCallCombinator rccRule : rccRuleLst) {
      if (null != rccRule) {
        rccRule.process();
      }
    }
  }

  private void register_bizRule(List<IPURemoteCallCombinator> rccRuleLst,
      BillForm editor) {
    CardEditorHelper billhelper =
        new CardEditorHelper(editor.getBillCardPanel());
    new BusitypeSetter(POBillType.Invoice, billhelper, editor.getModel()
        .getContext()).orgChgSet(rccRuleLst);
  }

  private void register_VatDefaultValueRule(
      List<IPURemoteCallCombinator> rccRuleLst, BillForm editor) {
    IKeyValue[] billhelpers = new CardEditorHelper[] {
      new CardEditorHelper(editor.getBillCardPanel())
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
      BillForm editor) {
    // 支持批量远程调用－确定业务流程－注册
    this.register_bizRule(rccRuleLst, editor);
    // 支持批量远程调用－VAT处理－注册
    this.register_VatDefaultValueRule(rccRuleLst, editor);
  }

}
