package nc.bs.pu.m23.pf.function;

import nc.itf.scmpub.reference.uap.bd.supplier.SupplierPubService;
import nc.vo.bd.supplier.SupplierVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 核准供应商检查
 * 
 * @since 6.0
 * @version 2011-4-13 上午08:52:39
 * @author wuxla
 */

public class SupplierStateChk {

  public UFBoolean checkSupplierState(AggregatedValueObject aggVO)
      throws BusinessException {
    if (null == aggVO) {
      return UFBoolean.TRUE;
    }

    try {
      ArriveVO vo = (ArriveVO) aggVO;
      String pk_supplier = vo.getHVO().getPk_supplier();
      SupplierVO[] supplierVOs = SupplierPubService.getSupplierVO(new String[] {
        pk_supplier
      }, new String[] {
        SupplierVO.PK_SUPPLIER, SupplierVO.SUPSTATE
      });
      if (ArrayUtils.isEmpty(supplierVOs)) {
        return UFBoolean.TRUE;
      }

      if (supplierVOs[0].getSupstate().intValue() != 1) {
        return UFBoolean.FALSE;
      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return UFBoolean.TRUE;
  }
}
