package nc.itf.pu.m20;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m20.context.PraybillContext;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�빺���޶�����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-6-21 ����04:02:43
 */
public interface IPraybillRevise {

  /**
   * ���������������빺���޶���ʷ��ѯ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param sql
   *          ��ѯ����
   * @param isLazy
   *          �Ƿ�������
   * @return ��ʷ�빺��
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-6-28 ����04:25:07
   */
  PraybillVO[] queryHistory(String sql, UFBoolean isLazy)
      throws BusinessException;

  /**
   * ���������������빺���޶���ѯ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param sql
   *          ��ѯ����
   * @param isLazy
   *          �Ƿ�������
   * @return �빺��
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-6-28 ����04:25:07
   */
  PraybillVO[] queryPrayBills(IQueryScheme queryScheme)
      throws BusinessException;

  /**
   * ���������������빺���޶����档
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   *          �빺��
   * @return �빺��
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-6-21 ����04:02:30
   */
  PraybillVO[] reviseSave(PraybillVO[] vos, PraybillContext[] ctxs)
      throws BusinessException;

}
