package nc.ui.pu.m21.editor.org;

import java.util.ArrayList;
import java.util.List;

import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.ui.pu.m21.rule.OrderScaleSetter;
import nc.ui.pu.m21.rule.ReferenceFilterByOrg;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.org.IOrgChangedEventListener;
import nc.ui.pu.pub.util.BusiDateSetter;
import nc.ui.pu.pub.util.BusitypeSetter;
import nc.ui.pu.pub.util.PuVDefFilterUtil;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.model.IAppModelEx;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.editor.BillForm;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.rule.HeadDefaultValue;
import nc.vo.pu.m21.rule.LineDefaultValue;
import nc.vo.pu.pub.context.IContext;
import nc.vo.pu.pub.rule.SetPeptRule;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购组织的编辑后事件类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-1-15 上午11:32:22
 */
public class PurchaseOrganization implements IOrgChangedEventListener {
  @Override
  public void process(ShowUpableBillForm cardForm) {
    // 设置卡片精度
    String pk_group = cardForm.getModel().getContext().getPk_group();
    new OrderScaleSetter(pk_group).setCardScale(cardForm.getBillCardPanel());

    if (((IAppModelEx) cardForm.getModel()).getAppUiState() == AppUiState.ADD) {
      CardEditorHelper helper =
          new CardEditorHelper(cardForm.getBillCardPanel());
      cardForm.getBillCardPanel().getBodyTabbedPane().setSelectedIndex(0);
      // 清除表单数据
      cardForm.getBillCardPanel().getBillData().clearViewData();
      // helper.getEditor().addLine("material");
      cardForm.addNew();
      // helper.getEditor().addLine(OrderPaymentVO.TABSNAME);
      // helper.getEditor().setBodyValueAt(1, 0, OrderPaymentVO.SHOWORDER,
      // OrderPaymentVO.TABSNAME);

      // 设置表头默认值
      new HeadDefaultValue(helper, (IContext) cardForm.getModel().getContext())
          .setDefaultValue();

      // 设置部门与人员
      new SetPeptRule(helper, cardForm.getModel().getContext(),
          OrderHeaderVO.CEMPLOYEEID, OrderHeaderVO.PK_DEPT,
          OrderHeaderVO.PK_DEPT_V).setPsnAndDept();

      new BusiDateSetter().setBusiDate(helper);

      // 设置表体默认值
      new LineDefaultValue(helper, (IContext) cardForm.getModel().getContext())
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
    new BusitypeSetter(POBillType.Order, billhelper, editor.getModel()
        .getContext()).orgChgSet(rccRuleLst);
  }

  private void registerRccRule(List<IPURemoteCallCombinator> rccRuleLst,
      BillForm editor) {
    // 支持批量远程调用－确定业务流程－注册
    this.regiest_bizRule(rccRuleLst, editor);
  }
}
