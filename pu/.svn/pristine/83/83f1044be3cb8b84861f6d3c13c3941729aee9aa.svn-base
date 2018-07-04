package nc.bs.pu.m21.pf.function;

import nc.bs.ml.NCLangResOnserver;
import nc.itf.scmpub.reference.uap.bd.accesor.SupplierAccessor;
import nc.itf.scmpub.reference.uap.bd.supplier.SupplierPubService;
import nc.vo.bd.accessor.IBDData;
import nc.vo.bd.supplier.SupplierVO;
import nc.vo.pu.m21.entity.OrderVO;
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
      OrderVO vo = (OrderVO) aggVO;
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
        IBDData supplier = SupplierAccessor.getDocByPk(pk_supplier);
        String supplierName = supplier.getName().toString();
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance().getStrByID("4004030_0", "04004030-0247", null, new String[]{supplierName})/*供应商{0}未核准*/);
      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
      return UFBoolean.FALSE;
    }

    return UFBoolean.TRUE;
  }
}
