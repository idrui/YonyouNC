/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-16 ����03:58:42
 */
package nc.bs.pu.est.rule.billfactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.vo.pu.est.entity.FeeEstVO;
import nc.vo.pu.est.util.EstTOIAUtil;
import nc.vo.pu.m4201.entity.PurchaseinFIHeaderVO;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pu.m4201.entity.PurchaseinFIVO;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.scmpub.res.billtype.POBillType;

public class PurchsInFeeEstGen extends AbstractEstBillGenerator<PurchaseinFIVO> {
  private FeeEstVO[] fees;

  public PurchsInFeeEstGen(String destBilltype, PurchaseinFIVO[] srcVos,
      FeeEstVO[] fees) {
    super(POBillType.PurchaseInFinance.getCode(), destBilltype, srcVos);
    this.fees = fees;
  }

  private void copyFiItem(PurchaseinFIItemVO fiitem, List<FeeEstVO> feeitems,
      List<PurchaseinFIItemVO> voItemlist) {
    for (int i = 0; i < feeitems.size(); ++i) {
      // ����һ��Ҫ��¡����Ҫʹ��ԭ����item�������ƻ�����
      PurchaseinFIItemVO copyfiitem = (PurchaseinFIItemVO) fiitem.clone();
      voItemlist.add(copyfiitem);
      this.fillSrcItemInfo(copyfiitem, feeitems.get(i));
    }
  }

  /**
   * ���ݷ���������ͷ��Ϣ
   * 
   * @param header
   * @param fee
   */
  protected void fillSrcHeaderInfo(PurchaseinFIHeaderVO header, FeeEstVO fee) {
    header.setCsendcountryid(fee.getCsendcountryid());// ���͹�
    header.setCtaxcountryid(fee.getCtaxcountryid());// ��˰��
    header.setCrececountryid(fee.getCrececountryid());// �ջ���
    header.setFbuysellflag(fee.getFbuysellflag());// ��������
    header.setBtriatradeflag(fee.getBtriatradeflag());// ����ó��
  }

  protected void fillSrcItemInfo(PurchaseinFIItemVO fiitem, FeeEstVO fee) {
    fiitem.setDtocostapdate(fee.getDestdate());// �ݹ�����
//    fiitem.setPk_supplier(fee.getPk_supplier());// ��Ӧ��
    fiitem.setPk_costappsn(fee.getPk_estpsn());// �ݹ���
    // wuxla V61
    fiitem.setNestmny(fee.getNestmny());// �ݹ����
    // fiitem.setNestmny(fee.getNcalcostmny());// �ݹ����
    fiitem.setNestcalcostmny(fee.getNcalcostmny());
    fiitem.setNestnum(UFDouble.ZERO_DBL);// �ݹ�����Ϊ0
  }

  protected FeeEstVO[] getFees() {
    return this.fees;
  }

  @Override
  protected void processSrcVO() {
    super.processSrcVO();
    // �Է��ռ�������ݽ��д���
    EstTOIAUtil.clearCenterSettleInfo(this.getSrcVos());
    PurchaseinFIVO[] fivos = this.getSrcVos();
    Map<String, ArrayList<FeeEstVO>> feeMap =
        CirVOUtil.getFieldVOList(this.fees, FeeEstVO.PK_STOCKPS_B);
    for (PurchaseinFIVO fivo : fivos) {
      FeeEstVO fee = null;
      List<PurchaseinFIItemVO> voItemlist = new ArrayList<PurchaseinFIItemVO>();
      for (PurchaseinFIItemVO fiitem : fivo.getChildrenVO()) {
        String pk_stockps_b = fiitem.getPk_stockps_b();
        List<FeeEstVO> feeitems = feeMap.get(pk_stockps_b);
        this.copyFiItem(fiitem, feeitems, voItemlist);
        if (fee == null) {
          fee = feeitems.get(0);
        }
      }
      // 2012-05-09 tianft��wuxla��zhaoyha��
      // ��������wangyfȷ�ϣ��ݹ�Ӧ�����Ĺ����ڱ�ͷ�������ݹ�Ӧ��ʱ���������ȡ���õ�ĳ�С�
      this.fillSrcHeaderInfo(fivo.getParentVO(), fee);
      fivo.setChildrenVO(voItemlist.toArray(new PurchaseinFIItemVO[voItemlist
          .size()]));
    }
  }

  protected void setFees(FeeEstVO[] fees) {
    this.fees = fees;
  }

}
