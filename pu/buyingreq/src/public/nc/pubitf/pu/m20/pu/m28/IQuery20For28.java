/**
 * $�ļ�˵��$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-26 ����07:26:38
 */
package nc.pubitf.pu.m20.pu.m28;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m20.entity.PraybillVO;
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
 * @author linsf
 * @time 2010-1-26 ����07:26:38
 */
public interface IQuery20For28 {

  /**
   * ���������������빺��Ϊ�۸��������ṩ��ת����ѯ����
   * <p>
   * <b>����˵��</b>
   * 
   * @param sql
   * @return ת�����빺��
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author linsf
   * @time 2010-1-26 ����07:28:55
   */
  PraybillVO[] queryPrayBills(IQueryScheme scheme) throws BusinessException;

}
