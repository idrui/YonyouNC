package nc.itf.pu.reference.ic;

import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.ic.m4d.pu.IMaterialOutForPU;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 材料出库4D提供给采购的服务适配
 * 
 * @since 6.0
 * @version 2011-5-8 上午09:09:00
 * @author wuxla
 */

public class M4DPUServices {

  /**
   * 取得供应商材料领用量
   * 
   * @param paramMap paramMap Key:物料oid，value:供应商数组
   * @return Key:物料oid|供应商pk，value:领用量(UFDouble)
   */
  public static Map<String, UFDouble> getVendorQuotas(
      Map<String, String[]> paramMap) {
    IMaterialOutForPU service =
        NCLocator.getInstance().lookup(IMaterialOutForPU.class);
    try {
      return service.getVendorQuotas(paramMap);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }
}
