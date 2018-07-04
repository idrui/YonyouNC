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

  /** ���÷�̯VO **/
  private FeeEstDistVO[] fdVos;

  public PurchsIn4639Gen(PurchaseinFIVO[] srcVos, PurchaseinFIFeeVO[] fees,
      FeeEstDistVO[] fdVos) {
    super(PCIABillType.RKTZ.getCode(), srcVos, fees);
    this.fdVos = fdVos;
    // ����̯��ϸ��ַ���VO
    FeeEstVO[] splitFees = FeeEstI9GenUtil.splitFDVOToFeeVO(this.fdVos, fees);
    this.setFees(splitFees);
  }

  @Override
  public P4639BillVO[] genBill() {
    P4639BillVO[] p4639s = (P4639BillVO[]) super.genBill();
    // ��I9�ı����а���̯���ݣ�����Դ���ϲ��ɱ�Ҫ�ء�����
    EstTOPCIAUtil.combineAdjustBillRow(p4639s);
    return p4639s;
  }

  @Override
  protected void fillItemInfo(CircularlyAccessibleValueObject[] items) {
    super.fillItemInfo(items);
    // ���óɱ�Ҫ��(�ָ���ԴIDǰ��)
    FeeEst4639GenUtil.setCostFactor((P4639ItemVO[]) items, this.getFees(),
        this.fdVos);
    // ���õ�������
    FeeEst4639GenUtil.setAdjustInfo((P4639ItemVO[]) items, this.fdVos);
    // �ָ���Դ��ID
    FeeEst4639GenUtil.restoreP4639SrcBID((P4639ItemVO[]) items, this.fdVos);

  }

  @Override
  protected void fillSrcItemInfo(PurchaseinFIItemVO fiitem, FeeEstVO fee) {
    super.fillSrcItemInfo(fiitem, fee);
    // ��ʱʹ������ϸ��ID������I9����Դ��ID�ϣ�����VO��������I9����ʱ����ȡ�����ݹ���ϸ
    // ʹ����ɺ�I9��Դ��������Ϊ��Ӧ��ֵ
    fiitem.setPk_stockps_b(fee.getPk_stockps_fd());
  }

  @Override
  protected void processSrcVO() {
    super.processSrcVO();
    PurchaseinFIVO[] fivos = this.getSrcVos();
    // ����Ӧ�̷ֵ�
    SplitBill<PurchaseinFIVO> split = new SplitBill<PurchaseinFIVO>();
    split.appendKey(PurchaseinFIItemVO.PK_SUPPLIER);
    fivos = split.split(fivos);
    this.setSrcVos(fivos);
  }

}
