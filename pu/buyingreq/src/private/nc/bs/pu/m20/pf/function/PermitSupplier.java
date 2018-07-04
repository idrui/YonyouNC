/**
 * $文件说明$
 *
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-5 下午07:48:55
 */
package nc.bs.pu.m20.pf.function;

import java.util.HashSet;

import nc.itf.scmpub.reference.uap.bd.supplier.SupplierPubService;
import nc.vo.bd.supplier.SupplierVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.log.TimeLog;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>核准供应商检查
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-7-5 下午07:48:55
 */
public class PermitSupplier {
  /**
   * 方法功能描述：检查供应商是否核准的采购供应商<br>
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-7-5 下午06:19:35
   */
  public UFBoolean isPermitSupplier(AggregatedValueObject vo)
      throws BusinessException {
    if (vo == null || vo.getParentVO() == null || vo.getChildrenVO() == null
        || vo.getChildrenVO().length <= 0) {
      TimeLog.info(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4004020_0", "04004020-0047")/*
                                        * @res
                                        * "传入参数[nc.vo.pub.AggregatedValueObject]为空，直接返回"
                                        */);
      return UFBoolean.TRUE;
    }

    PraybillItemVO[] items = ((PraybillVO) vo).getBVO();
    HashSet<String> suppliers = new HashSet<String>();
    for (PraybillItemVO item : items) {
      // 供应商
      String supplier = item.getPk_suggestsupplier();

      if (!StringUtil.isEmptyWithTrim(supplier)) {
        suppliers.add(supplier);
      }
    }

    SupplierVO[] supplierVOs =
        SupplierPubService.getSupplierVO(
            suppliers.toArray(new String[suppliers.size()]), new String[] {
              SupplierVO.SUPSTATE
            });

    if (ArrayUtils.isEmpty(supplierVOs)) {
      return UFBoolean.TRUE;
    }

    for (SupplierVO supplier : supplierVOs) {
      // 状态不是核准
      if (!(1 == supplier.getSupstate().intValue())) {
        return UFBoolean.FALSE;
      }
    }

    return UFBoolean.TRUE;
  }
}
