/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-27 ����01:58:26
 */
package nc.pubitf.pu.m21.so.m30;

import java.util.Map;

import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>����Эͬ���۶�����д�ɹ�����
 * <li>���ñ�ͷ��Эͬ�������۶���
 * <li>���ñ�ͷ�Է�������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-27 ����01:58:26
 */
public interface IOrderUpdateCoopFor30 {

  /**
   * ������������������Эͬ���۶�����д�ɹ����������ñ�ͷ��Эͬ�������۶����ͶԷ�������
   * <p>
   * <b>����˵��</b>
   * 
   * @param flag
   *          ��ɾ��־��trueΪ���ӣ�falseΪɾ��
   * @param wbMap
   *          ��д���� key:�ɹ�������ͷ���� value:���۶�����
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-27 ����02:06:31
   */
  void updateCoopFlag(boolean flag, Map<String, String> wbMap)
      throws BusinessException;
}
