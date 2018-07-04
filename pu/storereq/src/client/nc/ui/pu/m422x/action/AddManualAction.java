/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-19 上午10:00:39
 */
package nc.ui.pu.m422x.action;

import java.awt.event.ActionEvent;

import nc.ui.pu.m422x.rule.ReferenceFilterByOrg;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pubapp.uif2app.actions.AddAction;
import nc.ui.uif2.UIState;
import nc.ui.uif2.editor.IBillCardPanelEditor;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.rule.HeadDefaultValue;
import nc.vo.pu.m422x.rule.ItemDefaultValue;
import nc.vo.pu.pub.context.IContext;
import nc.vo.pu.pub.rule.SetPeptRule;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>新增
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-19 上午10:00:39
 */
public class AddManualAction extends AddAction {

  private static final long serialVersionUID = -1741536805331625373L;

  private IBillCardPanelEditor editor;

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    this.model.setUiState(UIState.ADD);

    CardEditorHelper helper =
        new CardEditorHelper(this.getEditor().getBillCardPanel());

    new HeadDefaultValue(helper, (IContext) this.getModel().getContext())
        .setDefaultValue();

    new ItemDefaultValue(helper, (IContext) this.getModel().getContext())
        .setDefaultValue(new int[] {
          0
        });
    // 设置部门人员
    new SetPeptRule(helper, this.getModel().getContext(),
        StoreReqAppHeaderVO.PK_APPPSNH, StoreReqAppHeaderVO.PK_APPDEPTH,
        StoreReqAppHeaderVO.PK_APPDEPTH_V).setPsnAndDept();

    new ReferenceFilterByOrg(this.getEditor()).filter();
  }

  public IBillCardPanelEditor getEditor() {
    return this.editor;
  }

  public void setEditor(IBillCardPanelEditor editor) {
    this.editor = editor;
  }

}
