package nc.ui.pu.m21.rule;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.scmpub.ref.FilterMeasdocRefUtils;
import nc.vo.pu.m21.entity.OrderItemVO;

/**
 * <p>
 * <b>本类主要完成以下功能：过滤跟物料相关的参照</b>
 * <ul>
 * <li>1、过滤计量档案参照
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-3-5 上午09:46:00
 */
public class ReferenceFilterByMaterial {
  private BillCardPanel panel;

  public ReferenceFilterByMaterial(BillCardPanel panel) {
    this.panel = panel;
  }

  /**
   * 方法功能描述：过滤参照。
   * <p>
   * <b>参数说明</b>
   * 
   * @since 6.0
   * @author duy
   * @time 2010-1-27 上午10:13:15
   */
  public void filter(int row, String unitItemKey) {
    // 过滤计量单位档案参照
    this.filterMeasdoc(row, unitItemKey);
  }

  private void filterMeasdoc(int row, String unitItemKey) {
    FilterMeasdocRefUtils filter =
        new FilterMeasdocRefUtils(this.panel.getBodyItem(unitItemKey));
    filter.setMaterialPk((String) this.panel.getBodyValueAt(row,
        OrderItemVO.PK_MATERIAL));
  }

}
