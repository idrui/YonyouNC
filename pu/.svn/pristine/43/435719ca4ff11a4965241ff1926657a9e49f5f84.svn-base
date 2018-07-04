package nc.ui.pu.report.pub.query.refregion;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.ui.bd.ref.model.AccPeriodDefaultRefModel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.refregion.CommonTwoLayerListener;
import nc.ui.querytemplate.QueryConditionDLG;
import nc.ui.querytemplate.filtereditor.FilterEditorWrapper;
import nc.ui.querytemplate.filtereditor.IFilterEditor;
import nc.ui.querytemplate.value.IFieldValueElement;
import nc.ui.querytemplate.valueeditor.ref.CompositeRefPanel;

/**
 * 取业务单元对应的会计期间方案
 * 
 * @since 6.0
 * @version 2011-10-13 下午03:51:52
 * @author 田锋涛
 */

public class QOrgAccountPeriod extends CommonTwoLayerListener {

  // 会计期间
  private String accountKey;

  private String orgKey;

  public QOrgAccountPeriod(QueryConditionDLG dlg, String orgKey,
      String accountKey) {
    this(new QueryConditionDLGDelegator(dlg), orgKey, accountKey);
  }

  public QOrgAccountPeriod(QueryConditionDLGDelegator qryCondDLGDelegator,
      String orgKey, String accountKey) {
    super(qryCondDLGDelegator);
    this.orgKey = orgKey;
    this.accountKey = accountKey;
  }

  public void addEditorListener() {
    this.setFatherPath(this.orgKey);
    this.setChildPath(this.accountKey);
    // 注册编辑事件
    this.qryCondDLGDelegator.registerCriteriaEditorListener(this);
  }

  // @Override
  // public boolean isClearChildWhenFatherChanged() {
  // return false;
  // }

  @Override
  public void setChildRefRegion(List<IFieldValueElement> fatherValues,
      IFilterEditor editor) {
    List<String> diffValues = new ArrayList<String>();
    for (IFieldValueElement fve : fatherValues) {
      if (diffValues.contains(fve.getSqlString())) {
        continue;
      }
      diffValues.add(fve.getSqlString());
    }

    FilterEditorWrapper wrapper = new FilterEditorWrapper(editor);
    // UIRefPane refPane = (UIRefPane)
    // wrapper.getFieldValueElemEditorComponent();
    JComponent component = wrapper.getFieldValueElemEditorComponent();
    UIRefPane refPane = null;
    if (component instanceof UIRefPane) {
      refPane = (UIRefPane) component;
    }
    else if (component instanceof CompositeRefPanel) {
      refPane = ((CompositeRefPanel) component).getStdRefPane();
    }
    if (refPane == null) {
      return;
    }
    AccPeriodDefaultRefModel model =
        (AccPeriodDefaultRefModel) refPane.getRefModel();
    if (refPane.getRefModel() == null) {
      return;
    }
    if (diffValues.size() == 0) {
      wrapper.setRefPK(null);
      model.setDefaultpk_accperiodscheme("noValue");
      return;
    }
    String pk_fiorg = diffValues.get(0);
    String accperiodScheme = OrgUnitPubService.getOrgAccperiodscheme(pk_fiorg);
    // model.reset();
    editor.clearData();
    // wrapper.getFieldValueEditor().
    model.setDefaultpk_accperiodscheme(accperiodScheme);
  }
}
