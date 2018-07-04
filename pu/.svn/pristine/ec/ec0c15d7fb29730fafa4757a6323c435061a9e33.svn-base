/**
 * $文件说明$
 *
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-3 下午02:11:11
 */
package nc.ui.pu.est.view;

import nc.ui.pub.beans.UIComboBox;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.ui.pubapp.uif2app.query2.refedit.IFieldValueElementEditorCreator;
import nc.ui.querytemplate.meta.FilterMeta;
import nc.ui.querytemplate.valueeditor.DefaultFieldValueElementEditor;
import nc.ui.querytemplate.valueeditor.IFieldValueElementEditor;
import nc.vo.pu.est.enumeration.QueryEstType;
import nc.vo.pu.est.enumeration.QueryNonMetaFieldCode;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>暂估查询模板中条件编辑器
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-6-3 下午02:11:11
 */
public class EstQueryComboEditorCreator implements
    IFieldValueElementEditorCreator {

  /**
   * 父类方法重写
   *
   * @see nc.ui.scmpub.query.refedit.IFieldValueElementEditorCreator#createFVEditor(nc.ui.querytemplate.meta.FilterMeta)
   */
  @Override
  public IFieldValueElementEditor createFVEditor(FilterMeta meta) {
    String code = meta.getFieldCode();
    IFieldValueElementEditor editor = null;
    if (QueryNonMetaFieldCode.bestfee.name().equals(code)) {
      editor = this.createBestfeeEditor();
    }
    else if (QueryNonMetaFieldCode.festtype.name().equals(code)) {
      editor = this.createFesttypeEditor();
    }
    return editor;
  }

  private IFieldValueElementEditor createBestfeeEditor() {
    UIComboBox combo = new UIComboBox();
    combo.addItem(new DefaultConstEnum(UFBoolean.TRUE,nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0013")/*@res "是"*/));
    combo.addItem(new DefaultConstEnum(UFBoolean.FALSE, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0014")/*@res "否"*/));
    return new DefaultFieldValueElementEditor(combo);
  }

  private IFieldValueElementEditor createFesttypeEditor() {
    UIComboBox combo = new UIComboBox();
    combo.addItem(new DefaultConstEnum(QueryEstType.GOODS_EST.name(),
        QueryEstType.GOODS_EST.dispName()));
    combo.addItem(new DefaultConstEnum(QueryEstType.FEE_EST.name(),
        QueryEstType.FEE_EST.dispName()));
    return new DefaultFieldValueElementEditor(combo);
  }

}