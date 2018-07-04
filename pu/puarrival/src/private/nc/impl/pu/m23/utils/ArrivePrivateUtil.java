package nc.impl.pu.m23.utils;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.pubitf.scmbd.vrm.vm.IVendorMaterialQuery;
import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmbd.vrm.vm.entity.VendorMaterBVO;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货单后台工具类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-18 下午02:51:11
 */
public class ArrivePrivateUtil {

  /**
   * 方法功能描述：查询生产档案 /供应商存货关系,判断存货是否免检
   * <p>
   * <b>参数说明</b>
   * 
   * @param supplier
   *          PK供应商
   * @param stockOrg
   *          PK库存组织
   * @param mrlPKs
   *          物料Pk数组
   * @return Map<物料主键，是否免检>
   * @throws Exception
   *           <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-18 下午02:54:46
   */
  public static Map<String, UFBoolean> isMaterialFreeChk(String[] mrlPKs,
      String supplierPK, String stock) {
    // <物料主键，是否免检>
    Map<String, UFBoolean> mrlToIsFreeChkMap = new HashMap<String, UFBoolean>();

    if (!StringUtils.isEmpty(supplierPK)) {
      VendorMaterBVO[] vendors =
          ArrivePrivateUtil.getVendorInfo(mrlPKs, supplierPK, stock);
      if (ArrayUtils.isEmpty(vendors)) {
        return null;
      }
      ArrivePrivateUtil.fillMrlToIsFreeChkMap(vendors, mrlToIsFreeChkMap);
    }

    // 根据 物料+库存组织 查询存货管理档案生产制造页的免检属性
    MaterialStockVO[] mrlvos = null;
    String[] fields = new String[] {
      MaterialStockVO.CHKFREEFLAG
    };
    mrlvos =
        MaterialPubService.queryMaterialStockInfoByPks(mrlPKs, stock, fields);
    if (mrlvos == null) {
      return null;
    }
    for (int i = 0, len = mrlPKs.length; i < len; i++) {
      if (mrlvos[i].getChkfreeflag() == null) {
        mrlToIsFreeChkMap.put(mrlPKs[i], UFBoolean.FALSE);
      }
      else {
        mrlToIsFreeChkMap.put(mrlPKs[i], mrlvos[i].getChkfreeflag());
      }
    }

    return mrlToIsFreeChkMap;
  }

  private static void fillMrlToIsFreeChkMap(VendorMaterBVO[] vendors,
      Map<String, UFBoolean> mrlToIsFreeChkMap) {
    for (VendorMaterBVO vo : vendors) {
      mrlToIsFreeChkMap.put(vo.getPk_material(), vo.getBnocheck());
    }
  }

  private static VendorMaterBVO[] getVendorInfo(String[] mrts,
      String pk_supplier, String pk_org) {
    IVendorMaterialQuery service =
        NCLocator.getInstance().lookup(IVendorMaterialQuery.class);
    VendorMaterBVO[] vendorMatVOs = null;
    try {
      vendorMatVOs = service.queryByVendorIds(new String[] {
        pk_supplier
      }, mrts, new String[] {
        pk_org
      });
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return vendorMatVOs;
  }
}
