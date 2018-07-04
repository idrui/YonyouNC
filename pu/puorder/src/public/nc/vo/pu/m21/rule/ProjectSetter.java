package nc.vo.pu.m21.rule;

import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.util.IKeyValue;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��ͷ��Ŀͬ��������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-4-22 ����10:39:56
 */
public class ProjectSetter {
  private IKeyValue keyValue;

  public ProjectSetter(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * ���������������ñ�ͷ����Ŀͬ���������Ŀ
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @since 6.0
   * @author duy
   * @time 2010-4-22 ����10:41:31
   */
  public void setBodyProject() {
    Object obj = this.keyValue.getHeadValue(OrderHeaderVO.PK_PROJECT);
    if (obj != null) {
      int rows = this.keyValue.getItemCount();
      for (int row = 0; row < rows; row++) {
        this.keyValue.setBodyValue(row, OrderItemVO.CPROJECTID, obj);
      }
    }
  }
}
