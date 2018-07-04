/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-16 下午03:58:42
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
      // 这里一定要克隆，不要使用原来的item，避免破坏数据
      PurchaseinFIItemVO copyfiitem = (PurchaseinFIItemVO) fiitem.clone();
      voItemlist.add(copyfiitem);
      this.fillSrcItemInfo(copyfiitem, feeitems.get(i));
    }
  }

  /**
   * 根据费用行填充表头信息
   * 
   * @param header
   * @param fee
   */
  protected void fillSrcHeaderInfo(PurchaseinFIHeaderVO header, FeeEstVO fee) {
    header.setCsendcountryid(fee.getCsendcountryid());// 发送国
    header.setCtaxcountryid(fee.getCtaxcountryid());// 报税国
    header.setCrececountryid(fee.getCrececountryid());// 收货国
    header.setFbuysellflag(fee.getFbuysellflag());// 购销类型
    header.setBtriatradeflag(fee.getBtriatradeflag());// 三角贸易
  }

  protected void fillSrcItemInfo(PurchaseinFIItemVO fiitem, FeeEstVO fee) {
    fiitem.setDtocostapdate(fee.getDestdate());// 暂估日期
//    fiitem.setPk_supplier(fee.getPk_supplier());// 供应商
    fiitem.setPk_costappsn(fee.getPk_estpsn());// 暂估人
    // wuxla V61
    fiitem.setNestmny(fee.getNestmny());// 暂估金额
    // fiitem.setNestmny(fee.getNcalcostmny());// 暂估金额
    fiitem.setNestcalcostmny(fee.getNcalcostmny());
    fiitem.setNestnum(UFDouble.ZERO_DBL);// 暂估数量为0
  }

  protected FeeEstVO[] getFees() {
    return this.fees;
  }

  @Override
  protected void processSrcVO() {
    super.processSrcVO();
    // 对分收集结的数据进行处理
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
      // 2012-05-09 tianft，wuxla，zhaoyha：
      // 根据需求wangyf确认，暂估应付单的国家在表头，费用暂估应付时，国家随便取费用的某行。
      this.fillSrcHeaderInfo(fivo.getParentVO(), fee);
      fivo.setChildrenVO(voItemlist.toArray(new PurchaseinFIItemVO[voItemlist
          .size()]));
    }
  }

  protected void setFees(FeeEstVO[] fees) {
    this.fees = fees;
  }

}
