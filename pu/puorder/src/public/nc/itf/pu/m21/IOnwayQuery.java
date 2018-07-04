/**
 * $�ļ�˵��$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-10 ����09:12:48
 */
package nc.itf.pu.m21;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m21.entity.OrderOnwayVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��;״̬��ѯ����ӿ�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-8-10 ����09:12:48
 */
public interface IOnwayQuery {

  OrderOnwayVO[] onwayQuery(IQueryScheme queryScheme, String onwayStatusStr,
      int onwayStatus, boolean isDone) throws BusinessException;

  OrderOnwayVO[] onwayQuery(String sqlWhere, int onwayStatus, boolean isDone)
      throws BusinessException;
}
