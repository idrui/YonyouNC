/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-24 下午03:22:03
 */
package nc.bs.pu.est.m45.rule;

import java.util.ArrayList;
import java.util.List;

import nc.bs.pu.est.rule.billfactory.EstFIBillFactory;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.arap.ArapServicesForPUUtil;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.arap.estipayable.AggEstiPayableBillVO;
import nc.vo.pu.est.entity.m45.PurchaseInEstHeaderVO;
import nc.vo.pu.est.entity.m45.PurchaseInEstVO;
import nc.vo.pu.est.util.EstVOUtil;
import nc.vo.pu.m4201.entity.PurchaseinFIFeeVO;
import nc.vo.pu.m4201.entity.PurchaseinFIHeaderVO;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pu.m4201.entity.PurchaseinFIVO;
import nc.vo.pu.pub.util.ListUtil;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.scmpub.util.CombineViewToAggUtil;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 费用单独暂估应付规则，必须作为后规则，要使用费用暂估ID
 * @scene
 * 暂估的BP操作
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-23 上午9:45:10
 * @author zhangshqb
 */
public class PurchsInFeeEstTOAPRule implements IRule<PurchaseInEstVO> {

  @Override
  public void process(PurchaseInEstVO[] vos) {
    boolean isIAEnabled = SysInitGroupQuery.isAPEnabled();
    if (!isIAEnabled) {
      return;
    }
    PurchaseinFIVO[] fivos = this.getFeeEstFiVO(vos);
    if (ArrayUtils.isEmpty(fivos)) {
      return;
    }
    PurchaseinFIFeeVO[] fees =
        (PurchaseinFIFeeVO[]) EstVOUtil.getFeeEstVOs(vos);
    AggEstiPayableBillVO[] payVos =
        (AggEstiPayableBillVO[]) EstFIBillFactory.getPurchsInFeeEstPayGen(
            fivos, fees).genBill();
    this.estToAP(payVos);
  }

  private void estToAP(AggEstiPayableBillVO[] payVos) {
    ArapServicesForPUUtil.estPayable(payVos);
  }

  /**
   * 得到暂估费用时的财务聚合VO，用于数据交换
   */
  private PurchaseinFIVO[] getFeeEstFiVO(PurchaseInEstVO[] estVos) {
    List<PurchaseInEstHeaderVO> hasFeeHeads =
        new ArrayList<PurchaseInEstHeaderVO>();
    for (PurchaseInEstVO estVo : estVos) {
      if (ArrayUtils.isEmpty(estVo.getChildrenVO())) {
        continue;
      }
      PurchaseInEstHeaderVO head = estVo.getParentVO();
      String pk_fiorg = head.getPk_financeorg();
      // 赵玉行 如果有效率问题，这里也可以从第1个表体（费用）VO中取得标志，因为前规则中已经设置好
      UFBoolean estAP = PUSysParamUtil.getPO52(pk_fiorg);
      if (!estAP.booleanValue()) {
        continue;
      }
      hasFeeHeads.add(head);
    }
    if (ListUtil.isEmpty(hasFeeHeads)) {
      return null;
    }
    CombineViewToAggUtil<PurchaseinFIVO> util =
        new CombineViewToAggUtil<PurchaseinFIVO>(PurchaseinFIVO.class,
            PurchaseinFIHeaderVO.class, PurchaseinFIItemVO.class);
    PurchaseinFIVO[] fiVos =
        util.combineViewToAgg(
            hasFeeHeads.toArray(new PurchaseInEstHeaderVO[hasFeeHeads.size()]),
            PurchaseinFIHeaderVO.PK_STOCKPS);
    return fiVos;
  }
}
