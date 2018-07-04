package nc.bs.pu.est.rule.billfactory;

import nc.vo.pcia.m4639.entity.P4639BillVO;
import nc.vo.pcia.m4639.entity.P4639ItemVO;
import nc.vo.pu.est.entity.FeeEstDistVO;
import nc.vo.pu.est.entity.FeeEstVO;
import nc.vo.pu.est.util.EstTOPCIAUtil;
import nc.vo.pu.est.util.FeeEst4639GenUtil;
import nc.vo.pu.est.util.FeeEstI9GenUtil;
import nc.vo.pu.m4201.entity.PurchaseinFIFeeVO;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pu.m4201.entity.PurchaseinFIVO;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pubapp.bill.SplitBill;
import nc.vo.scmpub.res.billtype.PCIABillType;

public class PurchsIn4639Gen extends PurchsInFeeEstGen {

  /** 费用分摊VO **/
  private FeeEstDistVO[] fdVos;

  public PurchsIn4639Gen(PurchaseinFIVO[] srcVos, PurchaseinFIFeeVO[] fees,
      FeeEstDistVO[] fdVos) {
    super(PCIABillType.RKTZ.getCode(), srcVos, fees);
    this.fdVos = fdVos;
    // 按分摊明细拆分费用VO
    FeeEstVO[] splitFees = FeeEstI9GenUtil.splitFDVOToFeeVO(this.fdVos, fees);
    this.setFees(splitFees);
  }

  @Override
  public P4639BillVO[] genBill() {
    P4639BillVO[] p4639s = (P4639BillVO[]) super.genBill();
    // 将I9的表体行按分摊依据（或来源）合并成本要素、金额等
    EstTOPCIAUtil.combineAdjustBillRow(p4639s);
    return p4639s;
  }

  @Override
  protected void fillItemInfo(CircularlyAccessibleValueObject[] items) {
    super.fillItemInfo(items);
    // 设置成本要素(恢复来源ID前面)
    FeeEst4639GenUtil.setCostFactor((P4639ItemVO[]) items, this.getFees(),
        this.fdVos);
    // 设置调整对象
    FeeEst4639GenUtil.setAdjustInfo((P4639ItemVO[]) items, this.fdVos);
    // 恢复来源行ID
    FeeEst4639GenUtil.restoreP4639SrcBID((P4639ItemVO[]) items, this.fdVos);

  }

  @Override
  protected void fillSrcItemInfo(PurchaseinFIItemVO fiitem, FeeEstVO fee) {
    super.fillSrcItemInfo(fiitem, fee);
    // 临时使用最明细的ID，传到I9的来源行ID上，用于VO交换后处理I9数据时，获取费用暂估明细
    // 使用完成后I9来源行再设置为相应的值
    fiitem.setPk_stockps_b(fee.getPk_stockps_fd());
  }

  @Override
  protected void processSrcVO() {
    super.processSrcVO();
    PurchaseinFIVO[] fivos = this.getSrcVos();
    // 按供应商分单
    SplitBill<PurchaseinFIVO> split = new SplitBill<PurchaseinFIVO>();
    split.appendKey(PurchaseinFIItemVO.PK_SUPPLIER);
    fivos = split.split(fivos);
    this.setSrcVos(fivos);
  }

}
