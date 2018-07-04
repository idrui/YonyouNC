/**
 * $文件说明$
 *
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-10 下午01:50:23
 */
package nc.ui.pu.costfactor.action;

import java.awt.event.ActionEvent;

import nc.ui.pubapp.uif2app.actions.DeleteAction;
import nc.ui.pubapp.uif2app.view.BillListEditor;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>删除成本要素
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-8-10 下午01:50:23
 */
public class CostFactorDeleteAction extends DeleteAction {

  private static final long serialVersionUID = 6099744751949986262L;

  private BillListEditor editor;

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pubapp.uif2app.actions.DeleteAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    int[] delRows =
        this.getEditor().getBillListPanel().getHeadTable().getSelectedRows();
    if (ArrayUtils.isEmpty(delRows)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004060_0", "04004060-0000")/*
                                                                   * @res
                                                                   * "没有选中成本要素行！"
                                                                   */);
    }
    super.doAction(e);
  }

  /**
   * @return listEditor
   */
  public BillListEditor getEditor() {
    return this.editor;
  }

  /**
   * @param listEditor
   *          要设置的 listEditor
   */
  public void setEditor(BillListEditor editor) {
    this.editor = editor;
  }

}
