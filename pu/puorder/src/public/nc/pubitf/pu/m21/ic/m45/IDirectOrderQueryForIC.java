/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-11 ����09:32:00
 */
package nc.pubitf.pu.m21.ic.m45;

import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.pub.MapList;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>Ϊ����ṩֱ�˽ӿڣ���ѯֱ�˶�����Դ���۶���ͷ����id
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-10-11 ����09:32:00
 */
public interface IDirectOrderQueryForIC {

  /**
   * ��������������Ϊ����ṩֱ�˽ӿڣ����ݶ�����id��ѯֱ�˶�����Դ���۶���ͷ����id
   * <p>
   * <b>����˵��</b>
   * 
   * @param bids
   *          �ɹ���������id
   * @return
   *         MapList-key���ɹ���������id��value��ArrayList��0-���۶���ͷid��1-���۶�����id��2-��Դ�������ͣ�3
   *         -
   *         ��Դ�������ͣ�
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-10-11 ����09:41:34
   */
  public MapList<String, String> directQueryForIC(String[] bids)
      throws BusinessException;
}
