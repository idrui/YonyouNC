/**
 * $�ļ�˵��$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-27 ����08:50:57
 */
package nc.itf.pu.m20;

import java.util.Set;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�빺����ɾ�Ĳ�
 * <li>�빺�������򿪡������ر�
 * <li>�빺���д򿪡��йر�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-1-27 ����08:50:57
 */
public interface IPraybillMaintain {

  /**
   * �ر��빺����
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   *          ��Ҫ�رյ��빺��
   * @return �رպ���빺��
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-15 ����10:17:07
   */
  PraybillVO[] closeBill(PraybillVO[] vos) throws BusinessException;

  /**
   * Ϊ�˼�¼��־��ȡȫvo�żӵķ���
   * 
   * @param Vos ����ȫvo
   * @return ����Ҳ��ȫvo
   * @throws BusinessException
   */
  PraybillVO[] closeBillByFullVO(PraybillVO[] Vos) throws BusinessException;

  /**
   * �ر��빺���С�
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   *          ��Ҫ�رյ��빺����
   * @return �йرպ���빺��
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-15 ����10:17:07
   */
  PraybillVO[] closeBillRow(PraybillVO[] vos) throws BusinessException;

  /**
   * Ϊ�˼�¼��־��ȡȫvo�żӵķ���
   * 
   * @param Vos ����ȫvo
   * @return ����Ҳ��ȫvo
   * @throws BusinessException
   */
  PraybillVO[] closeBillRowByFullVO(PraybillVO[] Vos, Set<String> closePks)
      throws BusinessException;

  /**
   * ����������������Ҫ�����������Ĺ��ܡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-1-27 ����08:02:05
   */
  void delete(PraybillVO[] vos) throws BusinessException;

  /**
   * �����������������������빺����
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @return �������빺��
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-1-27 ����08:51:38
   */
  PraybillVO[] insert(PraybillVO[] vos) throws BusinessException;

  /**
   * ���빺����
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   *          ��Ҫ�򿪵��빺��
   * @return �򿪺���빺��
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-15 ����10:17:07
   */
  PraybillVO[] openBill(PraybillVO[] vos) throws BusinessException;

  /**
   * Ϊ�˼�¼��־��ȡȫvo�żӵķ���
   * 
   * @param Vos ����ȫvo
   * @return ����Ҳ��ȫvo
   * @throws BusinessException
   */

  PraybillVO[] openBillByFullVO(PraybillVO[] Vos) throws BusinessException;

  /**
   * ���빺���С�
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   *          ��Ҫ�򿪵��빺����
   * @return �д򿪺���빺��
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-15 ����10:17:07
   */
  PraybillVO[] openBillRow(PraybillVO[] vos) throws BusinessException;

  /**
   * Ϊ�˼�¼��־��ȡȫvo�żӵķ���
   * 
   * @param Vos ����ȫvo
   * @return ����Ҳ��ȫvo
   * @throws BusinessException
   */
  PraybillVO[] openBillRowByFullVO(PraybillVO[] Vos, Set<String> openPks)
      throws BusinessException;

  /**
   * ����������������ѯ�빺����
   * <p>
   * <b>����˵��</b>
   * 
   * @param queryScheme
   * @return �빺��
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-1-28 ����04:25:07
   */
  PraybillVO[] queryPrayBills(IQueryScheme queryScheme)
      throws BusinessException;

  /**
   * ������������������ͳһ��ڡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @return �������빺��
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-1-28 ����08:45:33
   */
  PraybillVO[] saveBase(PraybillVO[] vos) throws BusinessException;

  /**
   * ǰ̨��ʽ�����빺������Ĭ��ֵ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   *          ��Ҫ��ʽ���ɵ��빺��
   * @return ����Ĭ��ֵ����빺��
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-6-07 ����10:17:07
   */
  PraybillVO[] setDefaultValue(PraybillVO[] vos) throws BusinessException;

  /**
   * ���������������빺�����¡�
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @return ���º���빺��
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-1-27 ����08:02:14
   */
  PraybillVO[] update(PraybillVO[] vos) throws BusinessException;

  /**
   * �ʲ�ά�޼ƻ���ʽ�����빺��ʱ�����빺������Ĭ��ֵ
   * 
   * @param vos ��Ҫ��ʽ���ɵ��빺��
   * @return ���º���빺��
   * @throws BusinessException
   */
  PraybillVO[] setDefaultValueForM4B32(PraybillVO[] vos)
      throws BusinessException;
}
