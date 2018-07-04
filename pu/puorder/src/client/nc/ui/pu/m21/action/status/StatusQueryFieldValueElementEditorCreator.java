/**
 * $文件说明$
 * 
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-29 上午09:23:16
 */
package nc.ui.pu.m21.action.status;

import javax.swing.JComboBox;

import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.ui.pubapp.uif2app.query2.refedit.IFieldValueElementEditorCreator;
import nc.ui.querytemplate.meta.FilterMeta;
import nc.ui.querytemplate.valueeditor.DefaultFieldValueElementEditor;
import nc.ui.querytemplate.valueeditor.IFieldValueElementEditor;
import nc.vo.pub.lang.UFBoolean;

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
 * @author chenlla
 * @time 2010-6-29 上午09:23:16
 */
public class StatusQueryFieldValueElementEditorCreator implements
    IFieldValueElementEditorCreator {

  @Override
  public IFieldValueElementEditor createFVEditor(FilterMeta meta) {
    JComboBox<DefaultConstEnum> combo = new JComboBox<>();
    combo.addItem(new DefaultConstEnum(UFBoolean.FALSE,
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
            "04004030-0207")/*
                             * @res
                             * "否"
                             */));
    combo.addItem(new DefaultConstEnum(UFBoolean.TRUE,
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
            "04004030-0206")/*
                             * @res
                             * "是"
                             */));
    return new DefaultFieldValueElementEditor(combo);
  }

}
