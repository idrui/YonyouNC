/**
 * $�ļ�˵��$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-26 ����08:10:51
 */
package nc.pubitf.pu.m20.invp.m4f;

import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;

/**
 * ��ʽ����-���ƻ��ƻ�����
 * 
 * @since 6.0
 * @version 2011-12-12 ����05:06:59
 * @author �����
 */
public interface IPushSave20For4F {

  /**
   * ���ƻ��ƻ�������ʽ�����빺����
   * 
   * @param praybills
   * @throws BusinessException
   */
  void pushSaveBills(PraybillVO[] praybills) throws BusinessException;

}
