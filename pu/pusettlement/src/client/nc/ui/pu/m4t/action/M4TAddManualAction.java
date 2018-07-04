/**
 * 
 */
package nc.ui.pu.m4t.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.ui.pu.m4t.rule.EditableByCurrency;
import nc.ui.pu.m4t.rule.ReferenceFilterByOrg;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.util.BusitypeSetter;
import nc.ui.pubapp.uif2app.actions.AbstractReferenceAction;
import nc.ui.uif2.UIState;
import nc.ui.uif2.editor.IBillCardPanelEditor;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pu.m4t.rule.HeadDefaultValue;
import nc.vo.pu.m4t.rule.LineDefaultValue;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>期初暂估单自制按钮</li>
 * </ul>
 * <p>
 * </p>
 * 
 * @author xiebo
 * @version 6.0
 * @see
 * @since
 * @time 2010-1-28 下午02:00:31
 */
public class M4TAddManualAction extends AbstractReferenceAction {

  private static final long serialVersionUID = 7280021438919507432L;

  private IBillCardPanelEditor editor;

  private AbstractAppModel model;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    this.model.setUiState(UIState.ADD);

    CardEditorHelper helper =
        new CardEditorHelper(this.getEditor().getBillCardPanel());
    // 设置表头默认值
    new HeadDefaultValue(helper, this.getModel().getContext())
        .setDefaultValue();
    // 设置表体默认值
    new LineDefaultValue(helper, this.getModel().getContext())
        .setDefaultValue(0);
    new EditableByCurrency(this.getEditor().getBillCardPanel()).setEditable();

    List<IPURemoteCallCombinator> rccRuleLst =
        new ArrayList<IPURemoteCallCombinator>();

    // 注册可合并的远程调用
    this.registerRccRule(rccRuleLst);
    // 执行远程调用规则
    this.doRccRule(rccRuleLst);

    // 过滤参照
    new ReferenceFilterByOrg(this.getEditor()).filter();
  }

  public IBillCardPanelEditor getEditor() {
    return this.editor;
  }

  public AbstractAppModel getModel() {
    return this.model;
  }

  public void setEditor(IBillCardPanelEditor editor) {
    this.editor = editor;
  }

  public void setModel(AbstractAppModel model) {
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
    new BusitypeSetter(POBillType.InitEstimate, billhelper, this.model
        .getContext()).manualAddSet(rccRuleLst);
  }

  private void registerRccRule(List<IPURemoteCallCombinator> rccRuleLst) {
    // 支持批量远程调用－确定业务流程－注册
    this.regiest_bizRule(rccRuleLst);
  }

  @Override
  protected boolean isActionEnable() {
    return this.model.getUiState() == UIState.NOT_EDIT;
  }

  @Override
  protected boolean isManual() {
    return true;
  }

}
