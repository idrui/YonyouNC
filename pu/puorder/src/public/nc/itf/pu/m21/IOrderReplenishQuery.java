/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-2 ����08:30:20
 */
package nc.itf.pu.m21;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������ѯ
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-6-2 ����08:30:20
 */
public interface IOrderReplenishQuery {

  /**
   * ��������������������ѯ
   * <p>
   * <b>����˵��</b>
   * 
   * @param cond
   * @param isLazy
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-2 ����10:55:58
   */
  OrderVO[] replenishQuery(IQueryScheme queryScheme) throws BusinessException;
}
