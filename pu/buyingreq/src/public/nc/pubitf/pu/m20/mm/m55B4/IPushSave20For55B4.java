/**
 * $�ļ�˵��$
 * 
 * @author lixyp
 * @version 6.1
 * @see
 * @since 6.1
 * @time 2011-12-26 ����04:00:37
 */
package nc.pubitf.pu.m20.mm.m55B4;

import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��ʽ����-�ƻ�����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.1
 * @since 6.1
 * @author lixyp
 * @time 2011-12-26 ����04:00:37
 */
public interface IPushSave20For55B4 {

  /**
   * ���������������ƻ�������ʽ�����빺����
   * <p>
   * <b>����˵��</b>
   * 
   * @param praybills
   *          �빺������VO
   * @throws BusinessException
   *           <p>
   * @since 6.1
   * @author lixyp
   * @time 2011-12-26 ����04:03:01
   */
  void pushSaveBills(PraybillVO[] praybills) throws BusinessException;

}
