/**
 * $�ļ�˵��$
 * 
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-13 ����12:07:21
 */
package nc.itf.pu.m21;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ������Է�ȷ�ϲ�ѯ
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-7-13 ����12:07:21
 */
public interface IConfirmQuery {

  OrderVO[] confirmQuery(IQueryScheme queryScheme) throws BusinessException;

  OrderVO[] confirmQuery(String sqlWhere) throws BusinessException;

}
