/**
 * $�ļ�˵��$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-26 ����07:44:54
 */
package nc.pubitf.pu.m20.pu.m28;

import nc.vo.pu.m20.entity.writeback.M28WriteBackVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author linsf
 * @time 2010-1-26 ����07:44:54
 */
public interface IReWrite20For28 {
  /**
   * ���������������۸���������д�빺����
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-8-18 ����04:17:31
   */
  void backWriteGenNum(M28WriteBackVO[] vos) throws BusinessException;
}
