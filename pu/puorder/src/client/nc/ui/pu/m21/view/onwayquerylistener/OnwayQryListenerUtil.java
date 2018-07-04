/**
 * $文件说明$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-1 上午10:36:36
 */
package nc.ui.pu.m21.view.onwayquerylistener;

import nc.ui.bd.ref.AbstractRefModel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.ui.querytemplate.filter.IFilter;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>在途状态查询对话框事件监听共通类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-9-1 上午10:36:36
 */
public class OnwayQryListenerUtil {

  /**
   * 方法功能描述：取得要控制的参照框的字段
   * <p>
   * <b>参数说明</b>
   * 
   * @param fieldKey
   * @param dlg
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-1 上午10:43:34
   */
  // public static UIRefPane getListenField(String fieldKey,
  // SCMQueryConditionDlg dlg) {
  //
  // // 取得要控制的元素
  // IFilterEditor supplierEditor =
  // SCMQueryDlgUtil.findFilterEidtorByKey(fieldKey, dlg);
  // if (supplierEditor == null) {
  // return null;
  // }
  // FilterEditorWrapper wrapper = new FilterEditorWrapper(supplierEditor);
  // UIRefPane supplierPane =
  // (UIRefPane) wrapper.getFieldValueElemEditorComponent();
  //
  // return supplierPane;
  //
  // }

  /**
   * 方法功能描述：设置参照对话框封存数据是否显示，并将参照框的封存显示checkbox隐藏
   * <p>
   * <b>参数说明</b>
   * 
   * @param filter
   * @param refPane
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-1 上午10:50:27
   */
  public static void setSealShow(IFilter filter, UIRefPane refPane) {

    if (filter == null || refPane == null) {
      return;
    }
    DefaultConstEnum value =
        (DefaultConstEnum) filter.getFieldValue().getFieldValues().get(0)
            .getValueObject();
    refPane.setDisabledDataButtonShow(false);
    AbstractRefModel refModel = refPane.getRefModel();
    refModel.setDisabledDataShow(((UFBoolean) value.getValue()).booleanValue());

  }
}
