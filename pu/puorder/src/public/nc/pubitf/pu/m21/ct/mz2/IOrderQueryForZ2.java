/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-28 ����04:49:14
 */
package nc.pubitf.pu.m21.ct.mz2;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.MapList;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ���ͬ��ѯ�ӿ�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-5-28 ����04:49:14
 */
public interface IOrderQueryForZ2 {

  /**
   * ���������������Ƿ���ڹ���ĳһ��ͬ�Ķ��������ڷ���true�����򷵻�false
   * <p>
   * <b>����˵��</b>
   * 
   * @param pkCt
   * @return
   * @throws BusinessException <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-28 ����04:52:52
   */
  public boolean isExistOrderFromCt(String pkCt) throws BusinessException;

  /**
   * ���ݺ�ͬ����PK����ѯ�������Ķ���������PK
   * 
   * @param pkCts
   * @return MapList{��ͬ����PK������������PK�б�}
   * @throws BusinessException
   */
  MapList<String, String> getRelationOrderItem(String[] pkCts)
      throws BusinessException;

  /**
   * �Ƿ���ڹ���ĳһ��ͬ�Ķ���
   * 
   * @param pkCt ��ͬ����
   * @param pk_org �ɹ���֯
   * @return key:�ɹ���֯��value���û��UFBoolean.FALSE�������UFBoolean.TRUE
   * @throws BusinessException
   */
  Map<String, UFBoolean> isExistOrderFromCt(String pkCt, String[] pk_org)
      throws BusinessException;
}
