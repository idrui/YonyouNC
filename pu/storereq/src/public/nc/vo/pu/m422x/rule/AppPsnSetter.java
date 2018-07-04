/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-26 下午08:19:42
 */
package nc.vo.pu.m422x.rule;

import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.pub.util.IKeyValue;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-26 下午08:19:42
 */
public class AppPsnSetter {
  private IKeyValue keyValue;

  public AppPsnSetter(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  public void setBodyAppPsn(int[] rows) {
    Object obj = this.keyValue.getHeadValue(StoreReqAppHeaderVO.PK_APPPSNH);
    if (obj != null) {
      for (int row : rows) {
        this.keyValue.setBodyValue(row, StoreReqAppItemVO.PK_APPPSN, obj);
      }
    }
  }
}
