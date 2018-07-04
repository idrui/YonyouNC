package nc.pubift.pu.m25.ia.costcal;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * �ɹ���ƱΪ�������ɱ������ṩ�Ĳ�ѯ
 * 
 * @since 6.0
 * @version 2012-10-31 ����03:25:47
 * @author wuxla
 */
public interface IInvoiceQueryForIACostCal {
  /**
   * ���ݲ�����֯������OID�����ڲ�ѯ��Ʊ��߼�
   * �������ɱ������㷨�д���ȡ�ɹ���Ʊ��߼۵��㷨
   * 
   * @param pk_org ������֯
   * @param pk_srcmaterials ����OID����
   * @param beginDate ��ʼ���ڣ�����
   * @param endDate �������ڣ�����
   * @return key:����oid��value�������������˰����(��Ϊ��Ʊû���ۿۣ�Ҳ������������˰����)�����û�з��ؿյ�map
   * @throws BusinessException
   */
  Map<String, UFDouble> queryMaxPrice(String pk_org, String[] pk_srcmaterials,
      UFDate beginDate, UFDate endDate) throws BusinessException;
}
