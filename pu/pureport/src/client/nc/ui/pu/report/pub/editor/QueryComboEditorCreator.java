package nc.ui.pu.report.pub.editor;

import nc.ui.pub.beans.UIComboBox;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.ui.pubapp.uif2app.query2.refedit.IFieldValueElementEditorCreator;
import nc.ui.querytemplate.meta.FilterMeta;
import nc.ui.querytemplate.valueeditor.DefaultFieldValueElementEditor;
import nc.ui.querytemplate.valueeditor.IFieldValueElementEditor;
import nc.vo.pub.lang.UFBoolean;

/**
 * ��ѯ�������߼��Ͳ�ѯ���壬ֻ��ʾ���ǡ��͡��񡰣�����ʾ���С�
 * 
 * <pre>
 * �÷����£�
 * condDLGDelegator.setFieldValueElementEditor(null, "bincldfinish",new EstQueryComboEditorCreator());
 * 
 * <pre>
 * 
 * @since 6.0
 * @version 2011-8-23 ����03:32:01
 * @author �����
 */

public class QueryComboEditorCreator implements IFieldValueElementEditorCreator {

  @Override
  public IFieldValueElementEditor createFVEditor(FilterMeta meta) {
    UIComboBox combo = new UIComboBox();
    combo.addItem(new DefaultConstEnum(UFBoolean.TRUE,
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004090_0",
            "04004090-0004")/* @res "��" */));
    combo.addItem(new DefaultConstEnum(UFBoolean.FALSE,
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004090_0",
            "04004090-0005")/* @res "��" */));

    return new DefaultFieldValueElementEditor(combo);
  }

}
