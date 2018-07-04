package nc.ui.pu.m4t.editor.org;

import java.util.ArrayList;
import java.util.List;

import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.ui.pu.m4t.rule.EditableByCurrency;
import nc.ui.pu.m4t.rule.InitialEstScaleSetter;
import nc.ui.pu.m4t.rule.ReferenceFilterByOrg;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.org.IOrgChangedEventListener;
import nc.ui.pu.pub.util.BusitypeSetter;
import nc.ui.pu.pub.util.PuVDefFilterUtil;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.model.IAppModelEx;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.editor.BillForm;
import nc.vo.pu.m4t.rule.HeadDefaultValue;
import nc.vo.pu.m4t.rule.LineDefaultValue;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-7 下午06:08:41
 */
public class FinanceOrganization implements IOrgChangedEventListener {

  @Override
  public void process(ShowUpableBillForm cardForm) {
    // 精度处理
    new InitialEstScaleSetter(cardForm.getModel().getContext().getPk_group())
        .setCardScale(cardForm.getBillCardPanel());
    if (((IAppModelEx) cardForm.getModel()).getAppUiState() == AppUiState.ADD) {
      CardEditorHelper helper =
          new CardEditorHelper(cardForm.getBillCardPanel());
      // 清除表单数据
      cardForm.addNew();

      // 设置表头默认值
      new HeadDefaultValue(helper, cardForm.getModel().getContext())
          .setDefaultValue();

      // 设置表体默认值
      new LineDefaultValue(helper, cardForm.getModel().getContext())
          .setDefaultValue(0);

      List<IPURemoteCallCombinator> rccRuleLst =
          new ArrayList<IPURemoteCallCombinator>();
      // 注册可合并的远程调用
      this.registerRccRule(rccRuleLst, cardForm);
      // 执行远程调用规则
      this.doRccRule(rccRuleLst);
      // 过滤参照
      new ReferenceFilterByOrg(cardForm).filter();
    }

    new EditableByCurrency(cardForm.getBillCardPanel()).setEditable();
    new PuVDefFilterUtil().setOrgForDefRef(cardForm.getBillCardPanel(),
        cardForm.getModel().getContext());
  }

  private void doRccRule(List<IPURemoteCallCombinator> rccRuleLst) {
    for (IPURemoteCallCombinator rccRule : rccRuleLst) {
      if (null != rccRule) {
        rccRule.process();
      }
    }
  }

  private void regiest_bizRule(List<IPURemoteCallCombinator> rccRuleLst,
      BillForm editor) {
    CardEditorHelper billhelper =
        new CardEditorHelper(editor.getBillCardPanel());
    new BusitypeSetter(POBillType.InitEstimate, billhelper, editor.getModel()
        .getContext()).orgChgSet(rccRuleLst);
  }

  private void registerRccRule(List<IPURemoteCallCombinator> rccRuleLst,
      BillForm editor) {
    // 支持批量远程调用－确定业务流程－注册
    this.regiest_bizRule(rccRuleLst, editor);
  }

}
