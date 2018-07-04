package nc.itf.pu.m20;

import java.util.List;
import java.util.Map;

import nc.vo.pu.m20.entity.SupplierQuotaPara;
import nc.vo.pub.BusinessException;

/**
 * ������
 * 
 * @since 6.0
 * @version 2011-4-24 ����04:29:26
 * @author wuxla
 */

public interface ISupplierQuotas {
  /**
   * ������
   * a) ���ݹ������ɹ�
   * b) ���ݲ����������ɹ�
   * c) �������������ɹ�
   * d) ����Ӧ�̲ɹ�
   * 
   * @param quotaPara ���������
   * @return ��Ҫ������ı�������
   * @throws BusinessException
   */
  Map<String, SupplierQuotaPara> getSupplierQuotas(
      List<SupplierQuotaPara> quotaPara) throws BusinessException;
}
