package nc.vo.pu.m21.rule;

import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.util.IKeyValue;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>表头项目同步到表体
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-4-22 上午10:39:56
 */
public class ProjectSetter {
  private IKeyValue keyValue;

  public ProjectSetter(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * 方法功能描述：用表头的项目同步表体的项目
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author duy
   * @time 2010-4-22 上午10:41:31
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
