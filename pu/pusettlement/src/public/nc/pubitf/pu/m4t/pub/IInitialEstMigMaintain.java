package nc.pubitf.pu.m4t.pub;

import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pub.BusinessException;

/**
 * �ڳ��ݹ���Ǩ�����ݱ���
 * <p>
 * ʹ�ó�����
 * <ul>
 * <li>V57��V61����Ǩ�ƿ��itf����VO����ı���
 * <li>Ǩ���п�����Ҫ��������
 * <li>���VOѭ���������쳣��VO������־��Ϣ���Ա�֤����Ǩ�Ƶ������ԣ������쳣�ж�
 * <li>Ŀǰ��Ҫ��������
 * </ul>
 * 
 * @since 6.0
 * @version 2012-8-9 ����06:15:42
 * @author liuyand
 */
public interface IInitialEstMigMaintain {

  /**
   * �ڳ��ݹ���Ǩ�����ݱ���(����)
   * <p>
   * ʹ�ó�����
   * <ul>
   * <li>V57��V61����Ǩ�ƿ��itf����VO����ı���
   * <li>Ǩ���п�����Ҫ��������
   * <li>���VOѭ���������쳣��VO������־��Ϣ���Ա�֤����Ǩ�Ƶ������ԣ������쳣�ж�
   * </ul>
   * 
   * @param bills Ҫ������ڳ��ݹ���VO������ΪȫVO��Ŀǰ��һ��������
   * @return ��־��Ϣ
   * @throws BusinessException
   */
  String[] migSave(InitialEstVO[] bills) throws BusinessException;

}
