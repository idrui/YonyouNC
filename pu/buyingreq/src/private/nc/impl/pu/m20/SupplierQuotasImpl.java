package nc.impl.pu.m20;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.pu.m20.quotas.SupplierQuotasBP;
import nc.itf.pu.m20.ISupplierQuotas;
import nc.vo.pu.m20.entity.SupplierQuotaPara;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.collections.CollectionUtils;

/**
 * 配额分配实现类
 * 
 * @since 6.0
 * @version 2011-4-24 下午04:33:53
 * @author wuxla
 */

public class SupplierQuotasImpl implements ISupplierQuotas {

  @Override
  public Map<String, SupplierQuotaPara> getSupplierQuotas(
      List<SupplierQuotaPara> quotaPara) throws BusinessException {
    try {
      new SupplierQuotasBP().getVendorQuotas(quotaPara);
      Map<String, SupplierQuotaPara> map =
          new HashMap<String, SupplierQuotaPara>();
      for (SupplierQuotaPara para : quotaPara) {
        if (!CollectionUtils.isEmpty(para.getSupplierList())) {
          map.put(para.getPk_praybill() + para.getPk_praybill_b(), para);
        }
      }
      return map;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

}
