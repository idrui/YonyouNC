package nc.bs.pu.m20.quotas;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.vo.bd.material.IMaterialEnumConst;
import nc.vo.bd.material.pu.MaterialPuVO;
import nc.vo.pu.m20.entity.SupplierQuotaPara;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.scmbd.vrm.vm.entity.VendorMaterBVO;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPuPubService;

import nc.pubitf.scmbd.vrm.vm.IVendorMaterialQuery;

import nc.bs.framework.common.NCLocator;

/**
 * 配额分配
 * 
 * @since 6.0
 * @version 2011-4-24 下午04:36:11
 * @author wuxla
 */

public class SupplierQuotasBP {

  /**
   * 配额分配
   * 分两步：1.数据准备;2.根据采购配额策略进行配额分配.
   * 
   * @param quotaPara
   * @return
   */
  public List<SupplierQuotaPara> getVendorQuotas(
      List<SupplierQuotaPara> quotaPara) {
    Map<String, MaterialPuVO> puvoMap = this.getPuvoMap(quotaPara);
    MapList<Integer, SupplierQuotaPara> strategyMap =
        this.getStrategyMap(quotaPara, puvoMap);
    VendorMaterBVO[] vendorMaterVOs = this.getVendorMaterVOs(quotaPara);
    for (Entry<Integer, List<SupplierQuotaPara>> entry : strategyMap.entrySet()) {
      Integer key = entry.getKey();
      IQuotaStrategy strategy = this.getQuotaStrategy(key);
      strategy.setMaterialPuVO(puvoMap);
      strategy.setVendorMaterBVO(vendorMaterVOs);
      List<SupplierQuotaPara> paraList = entry.getValue();
      strategy.setQuotaPara(paraList);
      strategy.quota();
    }
    return quotaPara;
  }

  private Map<String, MaterialPuVO> getPuvoMap(List<SupplierQuotaPara> quotaPara) {
    List<String> matList = new ArrayList<String>();
    List<String> purOrgList = new ArrayList<String>();
    for (SupplierQuotaPara para : quotaPara) {
      matList.add(para.getPk_material());
      purOrgList.add(para.getPk_purchaseorg());
    }

    String[] mats = matList.toArray(new String[matList.size()]);
    String[] purOrgs = purOrgList.toArray(new String[purOrgList.size()]);

    Map<String, MaterialPuVO> puvoMap =
        MaterialPuPubService.queryMaterialPurchaseInfoOrgIDsAndVIDs(mats,
            purOrgs, new String[] {
              MaterialPuVO.PURCHASESTGE, MaterialPuVO.PEBEGIN,
              MaterialPuVO.PEEND
            });
    return puvoMap;
  }

  private IQuotaStrategy getQuotaStrategy(Integer key) {
    int para = key.intValue();
    if (IMaterialEnumConst.PUSTRATEGY_QUO == para) {
      return new VendorMatQuoStrategy();
    }
    else if (IMaterialEnumConst.PUSTRATEGY_MAR == para) {
      return new MatDrawQuotaStrategy();
    }
    else if (IMaterialEnumConst.PUSTRATEGY_BAT == para) {
      return new BatchQuotaStrategy();
    }
    else if (IMaterialEnumConst.PUSTRATEGY_SUP == para) {
      return new SingleSupplierQuotaStrategy();
    }
    return null;
  }

  private MapList<Integer, SupplierQuotaPara> getStrategyMap(
      List<SupplierQuotaPara> quotaPara, Map<String, MaterialPuVO> puvoMap) {
    MapList<Integer, SupplierQuotaPara> strategyMap =
        new MapList<Integer, SupplierQuotaPara>();
    for (SupplierQuotaPara para : quotaPara) {
      String key = para.getPk_material() + "-" + para.getPk_purchaseorg();
      MaterialPuVO matpuvo = puvoMap.get(key);
      if (null == matpuvo) {
        continue;
      }
      Integer purStrategy = matpuvo.getPurchasestge();
      if (null == purStrategy) {
        purStrategy = Integer.valueOf(IMaterialEnumConst.PUSTRATEGY_QUO);
      }
      strategyMap.put(purStrategy, para);
    }

    return strategyMap;
  }

  private VendorMaterBVO[] getVendorMaterVOs(List<SupplierQuotaPara> quotaPara) {
    List<String> matList = new ArrayList<String>();
    Set<String> orgSet = new HashSet<String>();
    for (SupplierQuotaPara para : quotaPara) {
      matList.add(para.getPk_srcmaterial());
      orgSet.add(para.getPk_purchaseorg());
    }

    String[] mats = matList.toArray(new String[matList.size()]);
    String[] orgs = orgSet.toArray(new String[orgSet.size()]);
    // 过滤冻结供应商
    IVendorMaterialQuery service =
        NCLocator.getInstance().lookup(IVendorMaterialQuery.class);
    try {
      return service.queryByMaterialIds(mats, orgs);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }
}
