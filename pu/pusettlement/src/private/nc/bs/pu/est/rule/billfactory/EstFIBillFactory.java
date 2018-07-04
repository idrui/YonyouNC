/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-3 ����05:38:54
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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ݹ�ʱ���ɲ��񵥾�(IA��AP)�Ĺ���
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-6-3 ����05:38:54
 */
public class EstFIBillFactory {

  /** ��ⵥ�����ݹ�-I9���������� **/
  public static AbstractEstBillGenerator<PurchaseinFIVO> getPurchsInFeeEstI9Gen(
      PurchaseinFIVO[] vos, PurchaseinFIFeeVO[] fees, FeeEstDistVO[] fdVos) {
    return new PurchsInI9Gen(vos, fees, fdVos);
  }

  /** ��ⵥ�����ݹ�-4639���������� **/
  public static AbstractEstBillGenerator<PurchaseinFIVO> getPurchsInFeeEst4639Gen(
      PurchaseinFIVO[] vos, PurchaseinFIFeeVO[] fees, FeeEstDistVO[] fdVos) {
    return new PurchsIn4639Gen(vos, fees, fdVos);
  }

  /** ��ⵥ�����ݹ�-Ӧ������������ **/
  public static AbstractEstBillGenerator<PurchaseinFIVO> getPurchsInFeeEstPayGen(
      PurchaseinFIVO[] vos, PurchaseinFIFeeVO[] fees) {
    return new PurchsInFeeEstPayGen(vos, fees);
  }

  /** ��ⵥ�����ݹ�-I2���������� **/
  public static PurchsInEstI2BillGen getPurchsInGoodsEstI2Gen(
      PurchaseinFIVO[] vos) {
    return new PurchsInEstI2BillGen(POBillType.PurchaseInFinance.getCode(), vos);
  }

  /** ��ⵥ�����ݹ�-Ӧ������������ **/
  public static AbstractEstBillGenerator<PurchaseinFIVO> getPurchsInGoodsEstPayGen(
      PurchaseinFIVO[] vos) {
    return new PurchsInGoodsEstPayGen(vos);
  }

  /** ���Ļ��ܷ����ݹ�-I9���������� **/
  public static AbstractEstBillGenerator<VmiFIVO> getVMIFeeEstI9Gen(
      VmiFIVO[] vos, VmiFIFDVO[] fdVos) {
    return new VMIFeeEstI9Gen(vos, fdVos);
  }

  /** ���Ļ��ܷ����ݹ�-Ӧ������������ **/
  public static AbstractEstBillGenerator<VmiFIVO> getVMIFeeEstPayGen(
      VmiFIVO[] vos) {
    return new VMIFeeEstPayGen(vos);
  }

  /** ���Ļ��ܻ����ݹ�-I2���������� **/
  public static VMIEstI2BillGen getVMIGoodsEstI2Gen(VmiFIVO[] vos) {
    return new VMIEstI2BillGen(vos);
  }

  /** ���Ļ��ܻ����ݹ�-Ӧ������������ **/
  public static AbstractEstBillGenerator<VmiFIVO> getVMIGoodsEstPayGen(
      VmiFIVO[] vos) {
    return new VMIGoodsEstPayGen(vos);
  }

}
