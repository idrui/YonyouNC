package nc.vo.pu.pub.rule;

import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pu.pub.util.IKeyValue.RowStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>单据表头整单数量和价税合计的计算类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-3-26 上午09:46:26
 */
public class NumAndOrigmnySum {
  private String blargessField;

  private String headerMnyField = "ntotalorigmny";

  private String headerNumField = "ntotalastnum";

  private String itemMnyField = "norigtaxmny";

  private String itemNumField = "nastnum";

  private IKeyValue keyValue;

  public NumAndOrigmnySum(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * 方法功能描述：是否排除某行
   * <p>
   * <b>参数说明</b>
   * 
   * @param row
   *          行
   * @return 如果排除掉，则返回true，不排除，返回false
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-26 上午10:51:40
   */
  public boolean excludeRow(int row) {
    if (row >= 0) {
      return false;
    }
    return true;
  }

  /**
   * 方法功能描述：设置赠品字段名
   * <p>
   * <b>参数说明</b>
   * 
   * @param blargessField
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-26 上午10:41:11
   */
  public void setBlargessField(String blargessField) {
    this.blargessField = blargessField;
  }

  /**
   * 方法功能描述：设置表头价税合计字段名
   * <p>
   * <b>参数说明</b>
   * 
   * @param headerMnyField
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-26 上午10:40:21
   */
  public void setHeaderMnyField(String headerMnyField) {
    this.headerMnyField = headerMnyField;
  }

  /**
   * 方法功能描述：设置表头总数量字段名
   * <p>
   * <b>参数说明</b>
   * 
   * @param headerNumField
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-26 上午10:39:48
   */
  public void setHeaderNumField(String headerNumField) {
    this.headerNumField = headerNumField;
  }

  /**
   * 方法功能描述：设置表体价税合计字段名
   * <p>
   * <b>参数说明</b>
   * 
   * @param itemMnyField
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-26 上午10:40:54
   */
  public void setItemMnyField(String itemMnyField) {
    this.itemMnyField = itemMnyField;
  }

  /**
   * 方法功能描述：设置表体总数量字段名
   * <p>
   * <b>参数说明</b>
   * 
   * @param itemNumField
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-26 上午10:40:35
   */
  public void setItemNumField(String itemNumField) {
    this.itemNumField = itemNumField;
  }

  /**
   * 方法功能描述：计算整单数量和价税合计
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author duy
   * @time 2010-3-26 上午09:47:56
   */
  public void sum() {
    int rows = this.keyValue.getItemCount();
    UFDouble nnum = UFDouble.ZERO_DBL;
    UFDouble nmny = UFDouble.ZERO_DBL;
    for (int i = 0; i < rows; i++) {
      // 删除行不计算
      if (this.keyValue.getRowStatus(i) == RowStatus.DELETED) {
        continue;
      }
      // 赠品行计算总数量
      nnum =
          MathTool.add(nnum,
              (UFDouble) this.keyValue.getBodyValue(i, this.itemNumField));
      // 赠品行不计算总金额
      if (this.blargessField != null) {
        Object l = this.keyValue.getBodyValue(i, this.blargessField);
        if (UFBoolean.TRUE.equals(l)) {
          continue;
        }
      }
      // 其他排除的行不计算
      if (this.excludeRow(i)) {
        continue;
      }

      nmny =
          MathTool.add(nmny,
              (UFDouble) this.keyValue.getBodyValue(i, this.itemMnyField));
    }

    this.keyValue.setHeadValue(this.headerNumField, nnum);
    this.keyValue.setHeadValue(this.headerMnyField, nmny);
  }
}
