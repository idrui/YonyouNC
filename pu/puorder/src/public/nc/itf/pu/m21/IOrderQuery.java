/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version
 * @see
 * @since
 * @time 2009-12-28 ����04:18:44
 */
package nc.itf.pu.m21;

import java.util.Map;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m21.entity.OrderCloseVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������ѯ����
 * <li>ά����ѯ
 * <li>...
 * </ul>
 * <p>
 * <b>�����ʷ����ѡ����</b>
 * <p>
 * XXX�汾����XXX��֧�֡�
 * <p>
 * <p>
 * 
 * @version ���汾��
 * @since ��һ�汾��
 * @author zhaoyha
 * @time 2009-12-28 ����04:18:44
 */
public interface IOrderQuery {

  /**
   * ��������������Ϊ�ɹ������ر��ṩ��ѯ������
   * <p>
   * <b>����˵��</b>
   * 
   * @param cond
   * @return
   * @throws BusinessException <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-1-11 ����11:06:31
   */
  OrderCloseVO[] closeQuery(IQueryScheme queryScheme) throws BusinessException;

  /**
   * ��������������ά�������Ĳ�ѯ��
   * 
   * @param queryScheme
   * @return
   * @throws BusinessException
   */
  OrderVO[] maintainQuery(IQueryScheme queryScheme) throws BusinessException;

  /**
   * �����������������ݱ���id�����ѯ������ͼVO
   * <p>
   * <b>����˵��</b>
   * 
   * @param ids ������������
   * @return key:�������� value:��ͼVO
   * @throws BusinessException <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-29 ����04:12:10
   */
  Map<String, OrderViewVO> queryOrderViewsByIds(String[] ids)
      throws BusinessException;

  /**
   * ������������������һ���ͷID��ѯ����
   * <p>
   * <b>����˵��</b>
   * 
   * @param ids
   * @return
   * @throws BusinessException <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-3 ����04:57:40
   */
  OrderVO[] queryOrderVOsByIds(String[] ids, UFBoolean isLazy)
      throws BusinessException;

  /**
   * ���������������ɹ������޶���ѯ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param cond �ɲ�ѯģ��ƴ��������
   * @param isLazy �Ƿ�������
   * @return
   * @throws BusinessException <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-3-16 ����10:45:04
   */
  OrderVO[] reviseQuery(IQueryScheme queryScheme) throws BusinessException;
}
