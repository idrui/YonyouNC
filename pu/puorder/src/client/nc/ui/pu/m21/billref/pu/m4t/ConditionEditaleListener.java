///**
// * $文件说明$
// * 
// * @author wuxla
// * @version 6.0
// * @see
// * @since 6.0
// * @time 2010-9-13 下午03:40:17
// */
package nc.ui.pu.m21.billref.pu.m4t;

//
//import java.util.List;
//
//import nc.ui.pubapp.uif2app.query2.refregion.AbstractCommonListener;
//import nc.ui.querytemplate.CriteriaChangedEvent;
//import nc.ui.querytemplate.filter.IFilter;
//import nc.ui.querytemplate.filtereditor.IFilterEditor;
//import nc.ui.querytemplate.value.IFieldValue;
//import nc.ui.querytemplate.value.IFieldValueElement;
//
///**
// * <p>
// * <b>本类主要完成以下功能：</b>
// * <ul>
// * <li>可编辑性监听类
// * </ul>
// * <p>
// * <p>
// * 
// * @version 6.0
// * @since 6.0
// * @author wuxla
// * @time 2010-9-13 下午03:40:17
// */
//public class ConditionEditaleListener extends AbstractCommonListener {
//
//  /**
//   * ConditionEditaleListener 的构造子
//   * 
//   * @param dlg
//   */
//  public ConditionEditaleListener(SCMQueryConditionDlg dlg) {
//    super(dlg);
//  }
//
//  @Override
//  public void criteriaChanged(CriteriaChangedEvent event) {
//    if (event.getEventtype() == CriteriaChangedEvent.FILTER_CHANGED) {
//      if (event.getFieldCode().equals("pk_order_b.pk_psfinanceorg")) {
//        IFilter filter = event.getFilter();
//        IFieldValue filterValue = filter.getFieldValue();
//        List<IFieldValueElement> fieldValueElement =
//            filterValue.getFieldValues();
//        IFilterEditor materialEditor =
//            SCMQueryDlgUtil.findFilterEidtorByKey(
//                "pk_order_b.pk_material.code", this.dlg);
//        IFilterEditor marbasclassEditor =
//            SCMQueryDlgUtil.findFilterEidtorByKey(
//                "pk_order_b.pk_material.pk_marbasclass", this.dlg);
//        IFilterEditor supplierEditor =
//            SCMQueryDlgUtil.findFilterEidtorByKey("pk_supplier", this.dlg);
//
//        if (null == fieldValueElement || 0 == fieldValueElement.size()) {
//          materialEditor.setEnable(false);
//          marbasclassEditor.setEnable(false);
//          supplierEditor.setEnable(false);
//        }
//        else {
//          materialEditor.setEnable(true);
//          marbasclassEditor.setEnable(true);
//          supplierEditor.setEnable(true);
//        }
//      }
//    }
//  }
//
// }
