/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-9 ����01:21:32
 */
package nc.pubitf.pu.m4203;

import nc.vo.ic.m47.entity.SubcontInVO;
import nc.vo.pub.BusinessException;

/**
 * ί�мӹ���ⵥ���񣨲ɹ����㣩����ά������<br>
 * �ɹ������ṩ��ί�мӹ���ⵥ�ķ�����ǩ�ֺ�ȡ��ǩ��ʱ���á�
 * 
 * @since 6.0
 * @version 2011-1-20 ����10:42:24
 * @author zhaoyha
 */
public interface ISubcontinFIMaintain {

  /**
   * ί�мӹ���ⵥȡ��ǩ��ʱɾ�����񣨲ɹ����㣩������
   * 
   * @param hids ί�мӹ���ⵥ��ͷID����
   * @throws BusinessException
   */
  public void cancelDuplicate(String[] hids) throws BusinessException;

  /**
   * ί�мӹ���ⵥǩ��ʱд���񣨲ɹ����㣩������
   * 
   * @param vos ί�мӹ���ⵥ�ۺ�VO
   * @throws BusinessException
   */
  public void duplicate(SubcontInVO[] vos) throws BusinessException;
}
