package nc.bs.pu.m21.pf.function;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.itf.scmpub.reference.uap.bd.accesor.MaterialAccessor;
import nc.itf.scmpub.reference.uap.bd.accesor.SupplierAccessor;
import nc.pubitf.scmbd.vrm.vm.IVendorMaterialQuery;
import nc.vo.bd.accessor.IBDData;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.scmbd.vrm.vm.entity.VendorMaterBVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * 供应商与物料检查
 * 
 * @since 6.0
 * @version 2011-4-12 下午08:45:35
 * @author wuxla
 */

public class SupplierAndMaterialChk {

  /**
   * 供应商与物料检查
   * 
   * @param vo
   * @return
   */
  public UFBoolean checkSupplierAndMaterial(AggregatedValueObject aggVO)
      throws BusinessException {
    if (null == aggVO) {
      return UFBoolean.FALSE;
    }

    try {
      OrderVO vo = (OrderVO) aggVO;
      String pk_supplier = vo.getHVO().getPk_supplier();
      String pk_org = vo.getHVO().getPk_org();
      List<String> materialList = new ArrayList<String>();
      for (OrderItemVO itemVO : vo.getBVO()) {
        materialList.add(itemVO.getPk_material());
      }
      String[] materialIds =
          materialList.toArray(new String[materialList.size()]);
      IVendorMaterialQuery service =
          NCLocator.getInstance().lookup(IVendorMaterialQuery.class);
      MapList<String,String> vrmMap= service.queryByVendorMaterialOrgIds(new String[] {
        pk_supplier
      }, materialIds, new String[] {
        pk_org
      });
      if (vrmMap==null||vrmMap.toMap().isEmpty()){
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004020_0", "04004020-0096"));
        /** @res 表体，物料与供应商不匹配！ **/
        return UFBoolean.FALSE;
      }
      List<String> matCodeList = new ArrayList<String>();
      for (OrderItemVO itemVO : vo.getBVO()) {
        String pk_material = itemVO.getPk_material();
        if (vrmMap.containsKey(pk_material+pk_org)) {
          continue;
        }
        IBDData material = MaterialAccessor.getDocByPk(pk_material);
        matCodeList.add(material.getCode());
      }
      if (0 == matCodeList.size()) {
        return UFBoolean.TRUE;
      }
      String[] matCodes = matCodeList.toArray(new String[matCodeList.size()]);
      IBDData supplier = SupplierAccessor.getDocByPk(pk_supplier);
      String supplierName = supplier.getName().toString();
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4004030_0", "04004030-0246", null, new String[] {
            matCodes[0], supplierName
          })/* 物料{0}与供应商{1}不匹配 */);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
      return UFBoolean.FALSE;
    }
    return UFBoolean.TRUE;
  }
}
