/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-11 ����04:48:50
 */
package nc.pubitf.pu.m21.pu.m4t;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>Ϊ�ڳ��ݹ����ṩ�Ĳ�ѯ
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-11 ����04:48:50
 */
public interface IOrderQueryFor4T {
  OrderVO[] queryFor4t(IQueryScheme queryScheme) throws BusinessException;

  /**
   * ���������������ڳ��ݹ������ղɹ������Ĳ�ѯ����
   * <p>
   * <b>����˵��</b>
   * 
   * @param condition
   * @param isLazy
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-13 ����11:24:22
   */
  // OrderVO[] queryFor4t(String condition, UFBoolean isLazy)
  // throws BusinessException;
}
