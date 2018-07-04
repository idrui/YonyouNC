package nc.itf.pu.upgrade;

import nc.vo.pub.BusinessException;

/**
 * �ɹ�������v65�ӿ�
 * 
 * @since 6.5
 * @version 2014-1-23 ����02:59:48
 * @author fanly3
 */
public interface IPUUpgradeToV636 {

  /**
   * ��nc63����nc65ʱ,���������������
   * 
   * @throws BusinessException
   */
  void doAfterUpdateDataFrom63To636() throws BusinessException;

  /**
   * ��nc63����nc65ʱ��������������֮ǰ����
   * 
   * @throws BusinessException
   */
  void doBeforeUpdateDataFrom63To636() throws BusinessException;

  /**
   * ��nc63����nc65ʱ,�������ݿ�ṹ����֮ǰ����
   * 
   * @throws BusinessException
   */
  void doBeforeUpdateDBFrom63To636() throws BusinessException;
}
