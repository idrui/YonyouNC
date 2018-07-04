/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-9 ����12:51:24
 */
package nc.ui.pu.m422x.rule;

import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-8-9 ����12:51:24
 */
public class ReqDateSetter {
  private IKeyValue keyValue;

  public ReqDateSetter(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  public void setReqDate(int[] rows) {
    UFDate defaultReqDate = AppContext.getInstance().getBusiDate().asLocalEnd();
    UFDate appDate =
        (UFDate) this.keyValue.getHeadValue(StoreReqAppHeaderVO.DBILLDATE);
    if (appDate != null && appDate.compareTo(defaultReqDate) > 0) {
      defaultReqDate = appDate.asLocalEnd();
    }

    for (int row : rows) {
      this.keyValue.setBodyValue(row, StoreReqAppItemVO.DREQDATE,
          defaultReqDate);
    }
  }
}
