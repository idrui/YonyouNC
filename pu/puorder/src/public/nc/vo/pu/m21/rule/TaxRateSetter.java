package nc.vo.pu.m21.rule;

import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.util.IKeyValue;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>把表头税率同步到表体的每一行
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-1-15 下午12:18:54
 */
public class TaxRateSetter {
  private IKeyValue keyValue;

  public TaxRateSetter(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * 方法功能描述：用表头的税率更新表体的所有税率。
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author duy
   * @time 2010-3-28 下午02:55:01
   */
  public void setBodyTaxRate() {
    Object obj = this.keyValue.getHeadValue(OrderHeaderVO.NHTAXRATE);
    if (obj != null) {
      int rows = this.keyValue.getItemCount();
      for (int row = 0; row < rows; row++) {
        this.keyValue.setBodyValue(row, OrderItemVO.NTAXRATE, obj);
      }
    }
  }

  public void setBodyTaxRate(int[] rows) {
    Object obj = this.keyValue.getHeadValue(OrderHeaderVO.NHTAXRATE);
    if (obj != null) {
      for (int row : rows) {
        this.keyValue.setBodyValue(row, OrderItemVO.NTAXRATE, obj);
      }
    }
  }

}
