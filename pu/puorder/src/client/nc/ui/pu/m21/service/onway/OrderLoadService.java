/**
 * $�ļ�˵��$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-10 ����09:08:11
 */
package nc.ui.pu.m21.service.onway;

import nc.vo.pu.m21transtype.enumeration.OnwayStatus;
import nc.vo.pu.pub.enumeration.OnwayStatusQryEnum;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ�����״̬ά��װ�˷�����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-8-10 ����09:08:11
 */
public class OrderLoadService extends AbstractOrderOnwayService {

  /**
   * ���෽����д
   * 
   * @see nc.ui.pu.m21.service.onway.AbstractOrderOnwayService#getIsDone()
   */
  // @Override
  // public boolean getIsDone(String sqlWhere) {
  // boolean isDone = false;
  // if (sqlWhere.indexOf("bisload = 'N'") > 0) {
  // isDone = false;
  // }
  // else if (sqlWhere.indexOf("bisload = 'Y'") > 0) {
  // isDone = true;
  // }
  // return isDone;
  // }

  @Override
  public String getOnwayStatusStr() {
    return OnwayStatusQryEnum.bisload.code();
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.pu.m21.service.onway.AbstractOrderOnwayService#getStatus()
   */
  @Override
  public int getStatus() {
    return OnwayStatus.STATUS_SHIP.toInt();
  }
}
