/**
 * $�ļ�˵��$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-29 ����05:40:13
 */
package nc.itf.pu.m21;

import java.util.List;

import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;

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
 * @author wanghuid
 * @time 2010-10-29 ����05:40:13
 */
public interface IOrderEditRecordQuery {

  /**
   * ���������������ɹ������޶���¼��ѯ
   * <p>
   * <b>����˵��</b>
   * 
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-10-29 ����05:40:58
   */
  public OrderVO[] queryOrderPrice(List<String> bvoPkList)
      throws BusinessException;
}
