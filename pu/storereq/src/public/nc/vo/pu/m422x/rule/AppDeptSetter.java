/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-26 ����08:13:11
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
 * @time 2010-7-26 ����08:13:11
 */
public class AppDeptSetter {
  private IKeyValue keyValue;

  public AppDeptSetter(IKeyValue keyValue) {
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
   * @time 2010-7-26 ����08:24:34
   */
  public void setBodyAppDept(int[] rows) {
    Object objv = this.keyValue.getHeadValue(StoreReqAppHeaderVO.PK_APPDEPTH_V);
    if (objv != null) {
      for (int row : rows) {
        this.keyValue.setBodyValue(row, StoreReqAppItemVO.PK_APPDEPT_V, objv);
      }
    }

    Object obj = this.keyValue.getHeadValue(StoreReqAppHeaderVO.PK_APPDEPTH);
    if (obj != null) {
      for (int row : rows) {
        this.keyValue.setBodyValue(row, StoreReqAppItemVO.PK_APPDEPT, obj);
      }
    }
  }
}
