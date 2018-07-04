package nc.ui.pu.report.pub.editor;

import nc.ui.pub.beans.UIComboBox;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.ui.pubapp.uif2app.query2.refedit.IFieldValueElementEditorCreator;
import nc.ui.querytemplate.meta.FilterMeta;
import nc.ui.querytemplate.valueeditor.DefaultFieldValueElementEditor;
import nc.ui.querytemplate.valueeditor.IFieldValueElementEditor;
import nc.vo.pub.lang.UFBoolean;

/**
 * 查询条件里逻辑型查询定义，只显示”是“和”否“，不显示空行。
 * 
 * <pre>
 * 用法如下：
 * condDLGDelegator.setFieldValueElementEditor(null, "bincldfinish",new EstQueryComboEditorCreator());
 * 
 * <pre>
 * 
 * @since 6.0
 * @version 2011-8-23 下午03:32:01
 * @author 田锋涛
 */

public class QueryComboEditorCreator implements IFieldValueElementEditorCreator {

  @Override
  public IFieldValueElementEditor createFVEditor(FilterMeta meta) {
    UIComboBox combo = new UIComboBox();
    combo.addItem(new DefaultConstEnum(UFBoolean.TRUE,
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004090_0",
            "04004090-0004")/* @res "是" */));
    combo.addItem(new DefaultConstEnum(UFBoolean.FALSE,
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004090_0",
            "04004090-0005")/* @res "否" */));

    return new DefaultFieldValueElementEditor(combo);
  }

}
