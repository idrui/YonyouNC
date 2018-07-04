package nc.pubitf.pu.m21.pub;

import java.util.List;

import nc.vo.pub.AggregatedValueObject;

/**
 * �ֵ�����ʵ�ֽӿ�
 * 
 * @since 6.0
 * @version 2011-7-12 ����09:35:42
 * @author liuchx
 */
public interface ISplitForOrder {

  /**
   * ���ձ��ֵַ�
   * �����ε����ֶΣ�ѰԴȷ��
   * �����ڣ����۶��������������
   * �����ڱ��壬�����֯ �ڱ���
   * 
   * @param vo: �ۺ�vo
   * @param keys: [0]:���ϣ�[1]:�����֯
   * @return
   */
  List<String> splitBCcurrencyid(AggregatedValueObject vo, String[] keys);

  /**
   * ���չ�Ӧ�̷ֵ�
   * �����ε����ֶΣ�ѰԴȷ��
   * �����ڱ��壬�����֯ �ڱ���
   * �����ڣ��������������۶���
   * 
   * @param vo: �ۺ�vo
   * @param keys: [0]:���ϣ�[1]:�����֯
   * @return
   */
  List<String> splitBSupplier(AggregatedValueObject vo, String[] keys);

  /**
   * ���պ�ͬ�ŷֵ�
   * 
   * @param keys ��������id����
   * @return
   */
  List<String> splitByCtCode(String[] bids);

  /**
   * ���ձ��ֵַ�
   * �����ε����ֶΣ�ѰԴȷ��
   * �����ڱ��壬�����֯ �ڱ�ͷ
   * �����ڣ������������ƻ�����
   * 
   * @param vo: �ۺ�vo
   * @param keys: [0]:���ϣ�[1]:�����֯
   * @return
   */
  List<String> splitHCcurrencyid(AggregatedValueObject vo, String[] keys);

  /**
   * ���չ�Ӧ�̷ֵ�
   * �����ε����ֶΣ�ѰԴȷ��
   * �����ڱ��壬�����֯ �ڱ�ͷ
   * �����ڣ������������ƻ�����
   * 
   * @param vo: �ۺ�vo
   * @param keys: [0]:���ϣ�[1]:�����֯
   * @return
   */
  List<String> splitHSupplier(AggregatedValueObject vo, String[] keys);
  
  /**
   * @description ���ս��㷽ʽ�ֵ�
   * @param bids
   * @return
   */

  List<String> splitByStype(String[] bids);
}
