package nc.ui.pu.m27.match.util;

import java.util.List;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.querytemplate.QueryConditionDLG;
import nc.ui.querytemplate.filtereditor.FilterEditorWrapper;
import nc.ui.querytemplate.filtereditor.IFilterEditor;
import nc.ui.querytemplate.valueeditor.RefElementEditor;

/**
 * @since 6.0
 * @version 2011-7-1 下午04:47:29
 * @author 田锋涛
 */

public class QueryDlgValueSetter {

  private QueryConditionDLGDelegator qryDLGDelegator;

  public QueryDlgValueSetter(QueryConditionDLGDelegator qryDLGDelegator) {
    this.qryDLGDelegator = qryDLGDelegator;
  }

  public IFilterEditor findFilterEidtorByKey(String key, QueryConditionDLG dlg) {
    List<IFilterEditor> filterEditors = dlg.getSimpleEditorFilterEditors();
    for (IFilterEditor editor : filterEditors) {
      if (key.equals(editor.getFilter().getFilterMeta().getFieldCode())) {
        return editor;
      }
    }
    return null;
  }

  /**
   * 财务组织重新设置
   */
  public void resetFiOrg(String orgField, String fiOrg) {

    IFilterEditor filteEditor =
        this.findFilterEidtorByKey(orgField,
            this.qryDLGDelegator.getQueryConditionDLG());
    if (filteEditor != null) {
      FilterEditorWrapper wrapper = new FilterEditorWrapper(filteEditor);
      UIRefPane refPane =
          (UIRefPane) wrapper.getFieldValueElemEditorComponent();

      if (refPane.getRefModel() != null) {
        String pk = refPane.getRefModel().getPkValue();
        if (!fiOrg.equals(pk)) {
          refPane.setPK(fiOrg);
          RefElementEditor edtior =
              (RefElementEditor) wrapper.getFieldValueElementEditor();
          // 触发事件，此行代码不可少
          edtior.fireActionPerformed(null);
        }
      }
    }
  }
}
