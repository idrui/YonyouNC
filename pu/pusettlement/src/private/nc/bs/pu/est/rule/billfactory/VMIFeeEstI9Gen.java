/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-19 ����04:19:58
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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���Ļ����ݹ����ɵ�I9
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-8-19 ����04:19:58
 */
public class VMIFeeEstI9Gen extends AbstractEstBillGenerator<VmiFIVO> {
  /** ���÷�̯VO **/
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
    // ��I9�ı����а���̯���ݣ�����Դ���ϲ��ɱ�Ҫ�ء�����
    EstTOIAUtil.combineAdjustBillRow(i9s);
    // ����PO54�����������
    EstTOIAUtil.setStockTranTypeForVMI(i9s, this.getSrcVos());
    return i9s;
  }

  /**
   * ��ʱʹ������ϸ(�ݹ����÷�̯��ϸ)��ID������I9����Դ��ID�ϣ�����VO��������I9����ʱ����ȡ�����ݹ���ϸ ʹ����ɺ�I9��Դ���ٻָ�Ϊ��Ӧ��ֵ
   **/
  private void adjFeeStockBID(VmiFIFeeVO[] newFees) {
    for (VmiFIFeeVO vmiFIFeeVO : newFees) {
      vmiFIFeeVO.setPk_stockps(vmiFIFeeVO.getPk_stockps_fd());
    }
  }

  @Override
  protected void fillItemInfo(CircularlyAccessibleValueObject[] items) {
    super.fillItemInfo(items);
    // ���óɱ�Ҫ��(�ָ���ԴIDǰ��)
    FeeEstI9GenUtil.setCostFactor((I9ItemVO[]) items, this.fees, this.fdVos);
    // ���õ�������
    FeeEstI9GenUtil.setAdjustInfo((I9ItemVO[]) items, this.fdVos);
    // �ָ���Դ��IDΪ��Ӧֵ
    FeeEstI9GenUtil.restoreI9SrcBID((I9ItemVO[]) items, this.fdVos);
  }

  @Override
  protected void processSrcVO() {
    super.processSrcVO();
    // �Է��ռ�������ݽ��д���
    EstTOIAUtil.clearCenterSettleInfo(this.getSrcVos());
    VmiFIVO[] fivos = this.getSrcVos();
    // �����÷�̯��ϸ�������
    for (VmiFIVO fivo : fivos) {
      VmiFIFeeVO[] oldFees = fivo.getChildrenVO();
      VmiFIFeeVO[] newFees =
          (VmiFIFeeVO[]) FeeEstI9GenUtil.splitFDVOToFeeVO(this.fdVos, oldFees);
      this.adjFeeStockBID(newFees);
      fivo.setChildrenVO(newFees);
    }
    // ����Ӧ�̷ֵ�
    SplitBill<VmiFIVO> split = new SplitBill<VmiFIVO>();
    split.appendKey(FeeEstVO.PK_SUPPLIER);
    fivos = split.split(fivos);
    this.setSrcVos(fivos);
  }

}
