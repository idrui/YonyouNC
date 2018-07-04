package nc.vo.pu.m21.rule;

import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.util.IKeyValue;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ѱ�ͷ�Ŀ�˰���ͬ�������еı�����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-1-15 ����12:17:48
 */
public class TaxTypeSetter {
  private IKeyValue keyValue;

  public TaxTypeSetter(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * ���������������ñ�ͷ�Ŀ�˰�����±�������п�˰���
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @since 6.0
   * @author duy
   * @time 2010-3-20 ����10:12:10
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
