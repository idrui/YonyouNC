/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-3 下午05:38:54
 */
package nc.bs.pu.est.rule.billfactory;

import nc.vo.pu.est.entity.FeeEstDistVO;
import nc.vo.pu.est.entity.m50.VmiFIFDVO;
import nc.vo.pu.m4201.entity.PurchaseinFIFeeVO;
import nc.vo.pu.m4201.entity.PurchaseinFIVO;
import nc.vo.pu.m4202.entity.VmiFIVO;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>暂估时生成财务单据(IA，AP)的工厂
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-6-3 下午05:38:54
 */
public class EstFIBillFactory {

  /** 入库单费用暂估-I9单据生成器 **/
  public static AbstractEstBillGenerator<PurchaseinFIVO> getPurchsInFeeEstI9Gen(
      PurchaseinFIVO[] vos, PurchaseinFIFeeVO[] fees, FeeEstDistVO[] fdVos) {
    return new PurchsInI9Gen(vos, fees, fdVos);
  }

  /** 入库单费用暂估-4639单据生成器 **/
  public static AbstractEstBillGenerator<PurchaseinFIVO> getPurchsInFeeEst4639Gen(
      PurchaseinFIVO[] vos, PurchaseinFIFeeVO[] fees, FeeEstDistVO[] fdVos) {
    return new PurchsIn4639Gen(vos, fees, fdVos);
  }

  /** 入库单费用暂估-应付单据生成器 **/
  public static AbstractEstBillGenerator<PurchaseinFIVO> getPurchsInFeeEstPayGen(
      PurchaseinFIVO[] vos, PurchaseinFIFeeVO[] fees) {
    return new PurchsInFeeEstPayGen(vos, fees);
  }

  /** 入库单货物暂估-I2单据生成器 **/
  public static PurchsInEstI2BillGen getPurchsInGoodsEstI2Gen(
      PurchaseinFIVO[] vos) {
    return new PurchsInEstI2BillGen(POBillType.PurchaseInFinance.getCode(), vos);
  }

  /** 入库单货物暂估-应付单据生成器 **/
  public static AbstractEstBillGenerator<PurchaseinFIVO> getPurchsInGoodsEstPayGen(
      PurchaseinFIVO[] vos) {
    return new PurchsInGoodsEstPayGen(vos);
  }

  /** 消耗汇总费用暂估-I9单据生成器 **/
  public static AbstractEstBillGenerator<VmiFIVO> getVMIFeeEstI9Gen(
      VmiFIVO[] vos, VmiFIFDVO[] fdVos) {
    return new VMIFeeEstI9Gen(vos, fdVos);
  }

  /** 消耗汇总费用暂估-应付单据生成器 **/
  public static AbstractEstBillGenerator<VmiFIVO> getVMIFeeEstPayGen(
      VmiFIVO[] vos) {
    return new VMIFeeEstPayGen(vos);
  }

  /** 消耗汇总货物暂估-I2单据生成器 **/
  public static VMIEstI2BillGen getVMIGoodsEstI2Gen(VmiFIVO[] vos) {
    return new VMIEstI2BillGen(vos);
  }

  /** 消耗汇总货物暂估-应付单据生成器 **/
  public static AbstractEstBillGenerator<VmiFIVO> getVMIGoodsEstPayGen(
      VmiFIVO[] vos) {
    return new VMIGoodsEstPayGen(vos);
  }

}
