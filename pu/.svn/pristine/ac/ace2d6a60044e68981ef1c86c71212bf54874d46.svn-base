/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-19 下午04:19:58
 */
package nc.bs.pu.est.rule.billfactory;

import nc.vo.ia.mi9.entity.I9BillVO;
import nc.vo.ia.mi9.entity.I9ItemVO;
import nc.vo.pu.est.entity.FeeEstDistVO;
import nc.vo.pu.est.entity.FeeEstVO;
import nc.vo.pu.est.util.EstTOIAUtil;
import nc.vo.pu.est.util.FeeEstI9GenUtil;
import nc.vo.pu.m4202.entity.VmiFIFeeVO;
import nc.vo.pu.m4202.entity.VmiFIVO;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pubapp.bill.SplitBill;
import nc.vo.scmpub.res.billtype.IABillType;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>消耗汇总暂估生成的I9
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-8-19 下午04:19:58
 */
public class VMIFeeEstI9Gen extends AbstractEstBillGenerator<VmiFIVO> {
  /** 费用分摊VO **/
  private FeeEstDistVO[] fdVos;

  private VmiFIFeeVO[] fees;

  public VMIFeeEstI9Gen(VmiFIVO[] srcVos, FeeEstDistVO[] fdVos) {
    super(POBillType.VoiConsumeFinance.getCode(), IABillType.RKTZ.getCode(),
        srcVos);
    this.fdVos = fdVos;
    this.fees = AggVOUtil.getCombinItemVOs(srcVos);
  }

  @Override
  public I9BillVO[] genBill() {
    I9BillVO[] i9s = (I9BillVO[]) super.genBill();
    // 将I9的表体行按分摊依据（或来源）合并成本要素、金额等
    EstTOIAUtil.combineAdjustBillRow(i9s);
    // 根据PO54补充入库类型
    EstTOIAUtil.setStockTranTypeForVMI(i9s, this.getSrcVos());
    return i9s;
  }

  /**
   * 临时使用最明细(暂估费用分摊明细)的ID，传到I9的来源行ID上，用于VO交换后处理I9数据时，获取费用暂估明细 使用完成后I9来源行再恢复为相应的值
   **/
  private void adjFeeStockBID(VmiFIFeeVO[] newFees) {
    for (VmiFIFeeVO vmiFIFeeVO : newFees) {
      vmiFIFeeVO.setPk_stockps(vmiFIFeeVO.getPk_stockps_fd());
    }
  }

  @Override
  protected void fillItemInfo(CircularlyAccessibleValueObject[] items) {
    super.fillItemInfo(items);
    // 设置成本要素(恢复来源ID前面)
    FeeEstI9GenUtil.setCostFactor((I9ItemVO[]) items, this.fees, this.fdVos);
    // 设置调整对象
    FeeEstI9GenUtil.setAdjustInfo((I9ItemVO[]) items, this.fdVos);
    // 恢复来源行ID为相应值
    FeeEstI9GenUtil.restoreI9SrcBID((I9ItemVO[]) items, this.fdVos);
  }

  @Override
  protected void processSrcVO() {
    super.processSrcVO();
    // 对分收集结的数据进行处理
    EstTOIAUtil.clearCenterSettleInfo(this.getSrcVos());
    VmiFIVO[] fivos = this.getSrcVos();
    // 按费用分摊明细拆表体行
    for (VmiFIVO fivo : fivos) {
      VmiFIFeeVO[] oldFees = fivo.getChildrenVO();
      VmiFIFeeVO[] newFees =
          (VmiFIFeeVO[]) FeeEstI9GenUtil.splitFDVOToFeeVO(this.fdVos, oldFees);
      this.adjFeeStockBID(newFees);
      fivo.setChildrenVO(newFees);
    }
    // 按供应商分单
    SplitBill<VmiFIVO> split = new SplitBill<VmiFIVO>();
    split.appendKey(FeeEstVO.PK_SUPPLIER);
    fivos = split.split(fivos);
    this.setSrcVos(fivos);
  }

}
