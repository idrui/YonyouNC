package nc.pubitf.pu.m23.ic.m45;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�������ṩ�����Ĳɹ���ⵥ�Ĳ�ѯ����ӿ���
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-8 ����03:57:12
 */
public interface IQuery23For45 {

  /**
   * ���������������������ṩ�����Ĳɹ���ⵥ�Ĳ�ѯ���񷽷�
   * <p>
   * <b>����˵��</b>
   * 
   * @param queryScheme
   *          ��ѯscheme
   * @return <p>
   *         �����ĵ�����
   * @since 6.0
   * @author hanbin
   * @time 2010-1-8 ����04:00:53
   */
  public ArriveVO[] queryArrive(IQueryScheme queryScheme)
      throws BusinessException;

  /**
   * ���������������������ṩ�����Ĳɹ���ⵥ�Ĳ�ѯ���񷽷�
   * <p>
   * <b>����˵��</b>
   * 
   * @param sql
   *          ��ѯ���
   * @return <p>
   *         �����ĵ�����
   * @since 6.0
   * @author hanbin
   * @time 2010-1-8 ����04:00:53
   */
  // public ArriveVO[] queryArrive(String sql) throws BusinessException;
}
