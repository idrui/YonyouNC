package nc.vo.pu.m21.rule;

import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.util.IKeyValue;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>把表头的扣税类别同步到所有的表体行
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-1-15 下午12:17:48
 */
public class TaxTypeSetter {
  private IKeyValue keyValue;

  public TaxTypeSetter(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * 方法功能描述：用表头的扣税类别更新表体的所有扣税类别。
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author duy
   * @time 2010-3-20 下午10:12:10
   */
  public void setBodyTaxType() {
    Object obj = this.keyValue.getHeadValue(OrderHeaderVO.FHTAXTYPEFLAG);
    if (obj != null) {
      int rows = this.keyValue.getItemCount();
      for (int row = 0; row < rows; row++) {
        this.keyValue.setBodyValue(row, OrderItemVO.FTAXTYPEFLAG, obj);
      }
    }
  }

  public void setBodyTaxType(int[] rows) {
    Object obj = this.keyValue.getHeadValue(OrderHeaderVO.FHTAXTYPEFLAG);
    if (obj != null) {
      for (int row : rows) {
        this.keyValue.setBodyValue(row, OrderItemVO.FTAXTYPEFLAG, obj);
      }
    }
  }
}
