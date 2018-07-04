/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-26 ����07:50:10
 */
package nc.vo.pu.m422x.rule;

import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.pub.util.IKeyValue;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-26 ����07:50:10
 */
public class ProjectSetter {
  private IKeyValue keyValue;

  public ProjectSetter(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * ��������������
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-7-26 ����08:10:39
   */
  public void setBodyProject(int[] rows) {
    Object obj = this.keyValue.getHeadValue(StoreReqAppHeaderVO.PK_PROJECT);
    if (obj != null) {
      for (int row : rows) {
        this.keyValue.setBodyValue(row, StoreReqAppItemVO.CPROJECTID, obj);
      }
    }
  }
}
