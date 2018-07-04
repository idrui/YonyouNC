package nc.ui.pu.m21.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.ui.pu.m21.rule.ReferenceFilterByOrg;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.util.BusitypeSetter;
import nc.ui.pubapp.uif2app.actions.AbstractReferenceAction;
import nc.ui.uif2.UIState;
import nc.ui.uif2.editor.IBillCardPanelEditor;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.rule.HeadDefaultValue;
import nc.vo.pu.m21.rule.LineDefaultValue;
import nc.vo.pu.pub.context.IContext;
import nc.vo.pu.pub.rule.SetPeptRule;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���Ƶ���
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-1-27 ����03:39:36
 */
public class AddManualAction extends AbstractReferenceAction {
  private static final long serialVersionUID = -3060427151080092840L;

  private IBillCardPanelEditor editor;

  private AbstractAppModel model = null;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    this.model.setUiState(UIState.ADD);
    this.editor.getBillCardPanel().getBodyTabbedPane().setSelectedIndex(0);
    CardEditorHelper helper =
        new CardEditorHelper(this.getEditor().getBillCardPanel());
    // ���ñ�ͷĬ��ֵ
    new HeadDefaultValue(helper, (IContext) this.getModel().getContext())
        .setDefaultValue();
    
    new SetPeptRule(helper,   this.getModel().getContext(),
            OrderHeaderVO.CEMPLOYEEID, OrderHeaderVO.PK_DEPT,
            OrderHeaderVO.PK_DEPT_V).setPsnAndDept();
    // ���ñ���Ĭ��ֵ
    new LineDefaultValue(helper, (IContext) this.getModel().getContext())
        .setDefaultValue(0);
    // ���˲���
    new ReferenceFilterByOrg(this.getEditor()).filter();
    List<IPURemoteCallCombinator> rccRuleLst =
        new ArrayList<IPURemoteCallCombinator>();

    // ע��ɺϲ���Զ�̵���
    this.registerRccRule(rccRuleLst);
    // ִ��Զ�̵��ù���
    this.doRccRule(rccRuleLst);

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
    new BusitypeSetter(POBillType.Order, billhelper, this.model.getContext())
        .manualAddSet(rccRuleLst);
  }

  private void registerRccRule(List<IPURemoteCallCombinator> rccRuleLst) {
    // ֧������Զ�̵��ã�ȷ��ҵ�����̣�ע��
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
