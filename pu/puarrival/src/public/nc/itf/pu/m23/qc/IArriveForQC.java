package nc.itf.pu.m23.qc;

import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>�������������������Ľӿڶ���,������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���������ʼ�
 * <li>�������ķ���
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-11 ����01:40:39
 */
public interface IArriveForQC {

  /**
   * ��������������>�������ķ���
   * <p>
   * <b>����˵��</b>
   * 
   * @param aggVO
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-13 ����09:43:38
   */
  public ArriveItemVO[] antiQualityCheck(ArriveItemVO[] bills) throws BusinessException;

  /**
   * �����������������������ʼ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param aggVO
   * @param isCheck ������Ƿ񱨼�
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-13 ����09:43:38
   */
  public Object[] qualityCheck(ArriveVO[] bills, boolean isCheck)
      throws BusinessException;
}
