/**
 * $�ļ�˵��$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-26 ����08:14:36
 */
package nc.pubitf.pu.m20.so.m30;

import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b> ��ʽ����-���۶���
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author linsf
 * @time 2010-1-26 ����08:14:36
 */
public interface IPushSave20For30 {
  /**
   * ����������������Ҫ�����������Ĺ��ܡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param praybills
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author linsf
   * @time 2010-1-26 ����08:12:04
   */
  void pushSaveBills(PraybillVO[] praybills) throws BusinessException;

}
