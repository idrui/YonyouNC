/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-1 上午08:55:11
 */
package nc.vo.pu.m21.rule;

import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.util.IKeyValue;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>批次号
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-8-1 上午08:55:11
 */
public class Batchcode {
  private IKeyValue keyValue;

  public Batchcode(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * 方法功能描述：批次号置空
   * <p>
   * <b>参数说明</b>
   * 
   * @param rows
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-8-1 上午08:57:21
   */
  public void setBatchcodeNull(int[] rows) {
    for (int row : rows) {
      this.keyValue.setBodyValue(row, OrderItemVO.VBATCHCODE, null);
      this.keyValue.setBodyValue(row, OrderItemVO.PK_BATCHCODE, null);
    }
  }
}
