package nc.ui.pu.report.pub.validator;

import java.util.List;

import nc.ui.querytemplate.ICriteriaEditor;
import nc.ui.querytemplate.ICriteriaEditorValidator;
import nc.ui.querytemplate.SystemConstantFieldValueElement;
import nc.ui.querytemplate.filter.IFilter;
import nc.ui.querytemplate.filtereditor.IFilterEditor;
import nc.ui.querytemplate.simpleeditor.SimpleEditor;
import nc.ui.querytemplate.value.DefaultFieldValueElement;
import nc.ui.querytemplate.value.IFieldValue;
import nc.ui.querytemplate.value.IFieldValueElement;
import nc.vo.pu.pub.util.ListUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.StringUtils;

/**
 * 日期校验，开始结束日期必输等
 *
 * @since 6.0
 * @version 2011-7-27 下午02:46:01
 * @author 田锋涛
 */

public class PuReportDateValidator implements ICriteriaEditorValidator {

  private String dateField;

  public PuReportDateValidator(String dateField) {
    this.dateField = dateField;
  }

  @Override
  public void validate(ICriteriaEditor editor) throws Exception {
    if (editor instanceof SimpleEditor) {
      this.checkEmpty((SimpleEditor) editor);
    }
  }

  protected void checkEmpty(SimpleEditor simpleEditor) {
    List<IFilterEditor> filterEditors =
        simpleEditor.getFilterEditorsByCode(this.dateField);

    boolean emptyFlag = false;
    String dateName = null;
    for (IFilterEditor filterEditor : filterEditors) {
      IFilter filter = filterEditor.getFilter();
      IFieldValue fieldValue = filter.getFieldValue();
      List<IFieldValueElement> fieldValueElementList =
          fieldValue.getFieldValues();
      dateName = filter.getFilterMeta().getFieldName();
      if (ListUtil.isEmpty(fieldValueElementList)) {
        emptyFlag = true;
        break;
      }
      for (IFieldValueElement fieldValueElement : fieldValueElementList) {
        if (fieldValueElement == null) {
          emptyFlag = true;
          break;
        }
        if (fieldValueElement instanceof SystemConstantFieldValueElement) {
          SystemConstantFieldValueElement dfValueEelement =
              (SystemConstantFieldValueElement) fieldValueElement;
          if (StringUtils.isBlank(dfValueEelement.getSqlString())) {
            emptyFlag = true;
            break;
          }
        }
        if (fieldValueElement instanceof DefaultFieldValueElement) {
          DefaultFieldValueElement dfValueEelement =
              (DefaultFieldValueElement) fieldValueElement;
          if (StringUtils.isBlank(dfValueEelement.getSqlString())) {
            emptyFlag = true;
            break;
          }
        }
      }
    }
    if (emptyFlag) {
      ExceptionUtils.wrappBusinessException(dateName + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004090_0","04004090-0000")/*@res ": 开始或结束日期必输，请检查！"*/);
    }
  }

}