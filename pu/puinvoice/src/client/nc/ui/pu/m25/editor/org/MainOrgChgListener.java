/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-1 ����11:19:36
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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>����֯�ı䴦��,����ֻ��������֯�ı�Ӧ�ô�������飬������Ĭ��ֵ���õ�Ӧ�������������ദ��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-3-1 ����11:19:36
 */
public class MainOrgChgListener implements IOrgChangedEventListener {

  @Override
  public void process(ShowUpableBillForm cardForm) {

    if (cardForm instanceof InvoiceBillForm) {
      InvoiceBillForm billform = (InvoiceBillForm) cardForm;
      // ��ʼ������
      InvoiceUIScaleProcessor scaler = new InvoiceUIScaleProcessor();
      scaler.orgChgScale(billform);
      if (AppUiState.ADD.equals(((IAppModelEx) billform.getModel())
          .getAppUiState())) {
        billform.addNew();

        List<IPURemoteCallCombinator> rccRuleLst =
            new ArrayList<IPURemoteCallCombinator>();
        // ע��ɺϲ���Զ�̵���
        this.registerRccRule(rccRuleLst, cardForm);
        // ִ��Զ�̵��ù���
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
    // ֧������Զ�̵��ã�ȷ��ҵ�����̣�ע��
    this.register_bizRule(rccRuleLst, editor);
    // ֧������Զ�̵��ã�VAT����ע��
    this.register_VatDefaultValueRule(rccRuleLst, editor);
  }

}
