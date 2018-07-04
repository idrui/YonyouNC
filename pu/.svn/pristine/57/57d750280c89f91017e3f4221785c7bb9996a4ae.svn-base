package nc.bs.pu.m20.quotas;

import java.util.List;
import java.util.Map;

import nc.vo.bd.material.pu.MaterialPuVO;
import nc.vo.pu.m20.entity.SupplierQuotaPara;
import nc.vo.scmbd.vrm.vm.entity.VendorMaterBVO;


/**
 * 配额分配接口
 * 
 * @since 6.0
 * @version 2011-4-24 下午04:37:48
 * @author wuxla
 */

public interface IQuotaStrategy {
  /**
   * 配额分配
   */
  void quota();

  /**
   * 物料
   * 
   * @param puvoMap
   */
  void setMaterialPuVO(Map<String, MaterialPuVO> puvoMap);

  /**
   * 配额分配参数
   * 
   * @param paraList
   */
  void setQuotaPara(List<SupplierQuotaPara> paraList);

  /**
   * 供应商物料管理
   * 
   * @param vendorMaterVOs
   */
  void setVendorMaterBVO(VendorMaterBVO[] vendorMaterVOs);
}
