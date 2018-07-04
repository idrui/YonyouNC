/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-16 下午03:58:23
 */
package nc.bs.pu.est.rule.billfactory;

import nc.bs.arap.util.IArapBillTypeCons;
import nc.vo.pu.m4201.entity.PurchaseinFIVO;
import nc.vo.scmpub.res.billtype.POBillType;

public class PurchsInGoodsEstPayGen extends
    AbstractEstBillGenerator<PurchaseinFIVO> {

  public PurchsInGoodsEstPayGen(PurchaseinFIVO[] srcVos) {
    super(POBillType.PurchaseInFinance.getCode(), IArapBillTypeCons.C1, srcVos);
  }
}
