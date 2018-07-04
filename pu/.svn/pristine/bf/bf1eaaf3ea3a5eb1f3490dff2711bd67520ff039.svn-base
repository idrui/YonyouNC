package nc.bs.pu.m20.quotas;

import java.util.List;
import java.util.Map;

import nc.vo.bd.material.pu.MaterialPuVO;
import nc.vo.pu.m20.entity.SupplierQuotaPara;
import nc.vo.scmbd.vrm.vm.entity.VendorMaterBVO;


/**
 * 配额分配抽象类
 * 
 * @since 6.0
 * @version 2011-4-24 下午04:40:30
 * @author wuxla
 */

public abstract class AbstractQuotaStrategy implements IQuotaStrategy {
  private List<SupplierQuotaPara> paraList;

  private Map<String, MaterialPuVO> puvoMap;

  private VendorMaterBVO[] vendorMaterVOs;

  public List<SupplierQuotaPara> getParaList() {
    return this.paraList;
  }

  public Map<String, MaterialPuVO> getPuvoMap() {
    return this.puvoMap;
  }

  public VendorMaterBVO[] getVendorMaterVOs() {
    return this.vendorMaterVOs;
  }

  @Override
  public void setMaterialPuVO(Map<String, MaterialPuVO> puvoMapPara) {
    this.puvoMap = puvoMapPara;
  }

  @Override
  public void setQuotaPara(List<SupplierQuotaPara> paraListPara) {
    this.paraList = paraListPara;
  }

  @Override
  public void setVendorMaterBVO(VendorMaterBVO[] vendorMaterVOsPara) {
    this.vendorMaterVOs = vendorMaterVOsPara;
  }

}
