/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-28 ����06:34:35
 */
package nc.pubitf.pu.m21.so.m30;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���۶�������Эͬ�ɹ�������ѯ
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-28 ����06:34:35
 */
public interface ICoopOrderQueryFor30 {

  /**
   * �����������������۶�������Эͬ�ɹ�������ѯ
   * <p>
   * <b>����˵��</b>
   * 
   * @param condition
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-28 ����06:37:07
   */
  OrderVO[] coopOrderQueryFor30(IQueryScheme queryScheme)
      throws BusinessException;
}
