/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-5 ����04:38:51
 */
package nc.pubitf.pu.m21.ic.m45;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�������������˿ⵥ���˻�����ѯ�����Ҫƴ�ӵ���䡣
 * <li>�ɹ�������ͷ���ԡ��˻�/���Ƿ����ԭ���������������Ϊ�ǣ�
 * <li>����ڸòɹ��������ɵ��˻��������˿ⵥ����ʱ���ո�����д��Ӧ�������ۼƵ����������ۼ����������
 * <li>���ڲ��������ڵ���ղ�ѯ�˻������˿ⵥʱ�����˻������˿ⵥ��ѯ������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-6-5 ����04:38:51
 */
public interface IOrderQueryBrefFor45 {

  /**
   * �����������������ݶ������������ѯ�����ġ��˻�/�����ԭ��������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param ids
   * @return key:�������� value:��������
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-5 ����04:42:03
   */
  public Map<String, UFBoolean> getBrefwhenreturnMap(String[] ids)
      throws BusinessException;
}
