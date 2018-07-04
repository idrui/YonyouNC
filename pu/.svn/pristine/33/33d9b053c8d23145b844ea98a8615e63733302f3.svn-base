/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-20 下午01:34:02
 */
package nc.bs.pu.est.m45.rule;

import nc.vo.ia.mi9.entity.I9BillVO;
import nc.vo.pcia.m4639.entity.P4639BillVO;
import nc.vo.pu.est.entity.EstVO;
import nc.vo.pu.est.entity.FeeEstDistVO;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.est.entity.m45.PurchaseInEstVO;
import nc.vo.pu.est.entity.m45.PurchaseinFIFDVO;
import nc.vo.pu.est.util.EstVOUtil;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m4201.entity.PurchaseinFIFeeVO;
import nc.vo.pu.m4201.entity.PurchaseinFIHeaderVO;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pu.m4201.entity.PurchaseinFIVO;
import nc.vo.scmpub.util.CombineViewToAggUtil;

import nc.itf.pu.reference.ia.IAForEstConfirmServices;
import nc.itf.pu.reference.pcia.PCIAForEstConfirmServices;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;

import nc.bs.pu.est.rule.AbstractFeeEstTOIARule;
import nc.bs.pu.est.rule.billfactory.EstFIBillFactory;
import nc.bs.pu.est.rule.fee.EstFeeBSDIVRule;

import nc.impl.pubapp.pattern.rule.IRule;

/**
 * 
 * @description
 *              采购入库单暂估到存货(IA)规则
 * @scene
 *        暂估的BP操作
 * @param 无
 * 
 * @since 6.3
 * @version 2014-10-23 上午9:44:53
 * @author zhangshqb
 */
public class PurchsInFeeEstTOIARule extends AbstractFeeEstTOIARule implements
    IRule<PurchaseInEstVO> {

  @Override
  public void process(PurchaseInEstVO[] vos) {
    super.process(vos);
  }

  @Override
  protected void estToIA(EstVO[] estVos, FeeEstDistVO[] fdVos) {
    CombineViewToAggUtil<PurchaseinFIVO> util =
        new CombineViewToAggUtil<PurchaseinFIVO>(PurchaseinFIVO.class,
            PurchaseinFIHeaderVO.class, PurchaseinFIItemVO.class);
    GoodsEstVO[] gevos = EstVOUtil.getGoodsEstVos(estVos);
    PurchaseinFIVO[] fiVos =
        util.combineViewToAgg(gevos, PurchaseinFIHeaderVO.PK_STOCKPS);
    PurchaseinFIFeeVO[] fees =
        (PurchaseinFIFeeVO[]) EstVOUtil.getFeeEstVOs(estVos);
    // clone fiVos;clone fees
    PurchaseinFIVO[] fiVos_clone = new PurchaseinFIVO[fiVos.length];
    for (int i = 0; i < fiVos.length; i++) {
      fiVos_clone[i] = (PurchaseinFIVO) fiVos[i].clone();
    }

    PurchaseinFIFeeVO[] fees_clone = new PurchaseinFIFeeVO[fees.length];
    for (int i = 0; i < fees.length; i++) {
      fees_clone[i] = (PurchaseinFIFeeVO) fees[i].clone();
    }

    I9BillVO[] i9s =
        (I9BillVO[]) EstFIBillFactory
            .getPurchsInFeeEstI9Gen(fiVos, fees, fdVos).genBill();
    IAForEstConfirmServices.feeEstimate(i9s);
    // mengjian by 20141021 启用利润中心存货核算时
    if (SysInitGroupQuery.isPCIAEnabled()) {
      P4639BillVO[] p4639bills =
          (P4639BillVO[]) EstFIBillFactory.getPurchsInFeeEst4639Gen(
              fiVos_clone, fees_clone, fdVos).genBill();
      PCIAForEstConfirmServices.feeEstimate(p4639bills);
    }
  }

  @Override
  protected EstFeeBSDIVRule<? extends FeeEstDistVO> getFeeDivProcesser() {
    return new EstFeeBSDIVRule<PurchaseinFIFDVO>(PurchaseinFIFDVO.class,
        SettleBillItemVO.PK_STOCK_B);
  }

}
