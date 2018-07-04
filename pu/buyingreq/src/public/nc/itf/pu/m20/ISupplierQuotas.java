package nc.itf.pu.m20;

import java.util.List;
import java.util.Map;

import nc.vo.pu.m20.entity.SupplierQuotaPara;
import nc.vo.pub.BusinessException;

/**
 * 配额分配
 * 
 * @since 6.0
 * @version 2011-4-24 下午04:29:26
 * @author wuxla
 */

public interface ISupplierQuotas {
  /**
   * 配额分配
   * a) 根据供货配额采购
   * b) 根据材料领用量采购
   * c) 根据批量能力采购
   * d) 单供应商采购
   * 
   * @param quotaPara 配额分配参数
   * @return 需要配额分配的表体主键
   * @throws BusinessException
   */
  Map<String, SupplierQuotaPara> getSupplierQuotas(
      List<SupplierQuotaPara> quotaPara) throws BusinessException;
}
