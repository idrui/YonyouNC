package nc.itf.pu.pfxx;

import nc.vo.pub.AggregatedValueObject;

/**
 * �ⲿ������ͳһ�ӿڣ����������͸���
 * 
 * @since 6.0
 * @version 2011-4-26 ����09:51:07
 * @author �����
 */

public interface IPuPfxxSave {
  /**
   * ��������
   * 
   * @param vo
   * @return
   */
  AggregatedValueObject insert(AggregatedValueObject vo);

  /**
   * ���²���
   * 
   * @param vo
   * @return
   */
  AggregatedValueObject update(AggregatedValueObject vo);
}
