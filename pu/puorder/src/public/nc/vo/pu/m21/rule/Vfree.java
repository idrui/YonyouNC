/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-1 ����08:48:00
 */
package nc.vo.pu.m21.rule;

import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.util.IKeyValue;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�������ɸ�������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-8-1 ����08:48:00
 */
public class Vfree {
  private IKeyValue keyValue;

  public Vfree(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * �������������������ɸ��������ÿ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param rows
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-8-1 ����08:52:12
   */
  public void setVfreeNull(int[] rows) {
    for (int row : rows) {
      this.keyValue.setBodyValue(row, OrderItemVO.VFREE1, null);
      this.keyValue.setBodyValue(row, OrderItemVO.VFREE2, null);
      this.keyValue.setBodyValue(row, OrderItemVO.VFREE3, null);
      this.keyValue.setBodyValue(row, OrderItemVO.VFREE4, null);
      this.keyValue.setBodyValue(row, OrderItemVO.VFREE5, null);
      this.keyValue.setBodyValue(row, OrderItemVO.VFREE6, null);
      this.keyValue.setBodyValue(row, OrderItemVO.VFREE7, null);
      this.keyValue.setBodyValue(row, OrderItemVO.VFREE8, null);
      this.keyValue.setBodyValue(row, OrderItemVO.VFREE9, null);
      this.keyValue.setBodyValue(row, OrderItemVO.VFREE10, null);
    }
  }
}
