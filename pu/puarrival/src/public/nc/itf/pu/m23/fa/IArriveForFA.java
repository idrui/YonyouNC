package nc.itf.pu.m23.fa;

import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������������ʲ��Ľӿڶ��壬������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�����������ʲ���Ƭ
 * <li>������ɾ���ʲ���Ƭ
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-11 ����01:39:45
 */
public interface IArriveForFA {

  /**
   * �������������������������ʲ���Ƭ
   * <p>
   * <b>����˵��</b>
   * 
   * @param aggVO
   * @param bid_snVOArray_map
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-5-10 ����09:06:00
   */
  ArriveVO[] createFACard(ArriveVO[] aggVO) throws BusinessException;

  /**
   * ��������������������ɾ���ʲ���Ƭ
   * <p>
   * <b>����˵��</b>
   * 
   * @param aggVO
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-13 ����09:38:43
   */
  ArriveVO[] deleteFACard(ArriveVO[] aggVO) throws BusinessException;

  /**
   * ɾ�����������ɵ�ת�̵�
   * 
   * @param vos ɾ��ת�̵��ĵ�����
   * @return ������
   * @throws BusinessException
   */
  ArriveVO[] deleteTransAsset(ArriveVO[] vos) throws BusinessException;

  /**
   * ��ת��ֱ���ѯ����
   * 
   * @param vos ������VO��ֻ����������ts
   * @return
   * @throws BusinessException
   */
  ArriveVO[] queryArriveFor4A60(ArriveVO[] vos) throws BusinessException;

  /**
   * ����������ת�̵�
   * 
   * @param vos ����ת�̵��ĵ�����
   * @return ������
   * @throws BusinessException
   */
  ArriveVO[] transAsset(ArriveVO[] vos) throws BusinessException;
}
