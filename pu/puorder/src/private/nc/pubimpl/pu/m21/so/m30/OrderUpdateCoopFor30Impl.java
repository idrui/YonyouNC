/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-27 ����02:16:10
 */
package nc.pubimpl.pu.m21.so.m30;

import java.util.Map;

import nc.bs.pu.m21.writeback.so.OrderUpdateCoopFor30BP;
import nc.pubitf.pu.m21.so.m30.IOrderUpdateCoopFor30;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>����Эͬ���۶�����д�ɹ�����ʵ����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-27 ����02:16:10
 */
public class OrderUpdateCoopFor30Impl implements IOrderUpdateCoopFor30 {

  /**
   * ���෽����д
   * 
   * @see nc.pubitf.pu.m21.so.m30.IOrderUpdateCoopFor30#updateCoopFlag(boolean,
   *      java.util.Map)
   */
  @Override
  public void updateCoopFlag(boolean flag, Map<String, String> wbMap)
      throws BusinessException {
    try {
      new OrderUpdateCoopFor30BP().updateCoopFlag(flag, wbMap);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);

    }
  }

}
