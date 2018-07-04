/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-24 下午04:27:52
 */
package nc.bs.pu.est.m45;

import nc.bs.pu.est.m45.rule.PurchsInFeeEstTOAPRule;
import nc.bs.pu.est.m45.rule.PurchsInFeeEstTOIARule;
import nc.bs.pu.est.m45.rule.PurchsInGoodsEstNecssFillRule;
import nc.bs.pu.est.m45.rule.PurchsInGoodsEstTOAPRule;
import nc.bs.pu.est.m45.rule.PurchsInGoodsEstTOIARule;
import nc.bs.pu.est.m45.rule.PurchsInGoodsEstTOPCIARule;
import nc.bs.pu.est.m45.rule.PurchsInSettledFeeChkRule;
import nc.bs.pu.est.m45.rule.fee.PurchsInEstimatedFeeChkRule;
import nc.bs.pu.est.m45.rule.fee.PurchsInFeeEstInfoFillRule;
import nc.bs.pu.est.plugin.PurchsInEstPluginPoint;
import nc.bs.pu.est.rule.EstCalCostMnyPriceRule;
import nc.bs.pu.est.rule.FeeEstSettleTOIAChkRule;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.data.vo.VOInsert;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pu.est.entity.m45.PurchaseInEstHeaderVO;
import nc.vo.pu.est.entity.m45.PurchaseInEstVO;
import nc.vo.pu.est.rule.m45.PurchsInEstNotNullChkRule;
import nc.vo.pu.est.rule.m45.PurchsInFeeEstNotNullChkRule;
import nc.vo.pu.est.util.EstVOUtil;
import nc.vo.pu.m4201.entity.PurchaseinFIFeeVO;
import nc.vo.pu.m4201.entity.PurchaseinFIHeaderVO;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pu.pub.util.AggVOUtil;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>暂估BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-24 下午04:27:52
 */
public class PurchsInEstBP {

  /** 入库单签字自动暂估 **/
  public void autoEstimate(PurchaseInEstVO[] vos) {
    AroundProcesser<PurchaseInEstVO> prcr =
        new AroundProcesser<PurchaseInEstVO>(PurchsInEstPluginPoint.AUTP_EST_BP);
    this.addRule(prcr);
    prcr.before(vos);
    ViewUpdate<PurchaseInEstHeaderVO> vusrv =
        new ViewUpdate<PurchaseInEstHeaderVO>();
    vusrv.update(this.getHeaders(vos), PurchaseinFIItemVO.class,
        EstVOUtil.getGoodsEstBUpdate());
    vusrv.update(this.getHeaders(vos), PurchaseinFIHeaderVO.class,
        EstVOUtil.getPurchsInGoodsHUpdate());
    prcr.after(vos);
  }

  /** 入库单手工暂估 **/
  public void estimate(PurchaseInEstVO[] vos) {
    AroundProcesser<PurchaseInEstVO> prcr =
        new AroundProcesser<PurchaseInEstVO>(PurchsInEstPluginPoint.EST_BP);
    this.addRule(prcr);
    prcr.before(vos);
    ViewUpdate<PurchaseInEstHeaderVO> vusrv =
        new ViewUpdate<PurchaseInEstHeaderVO>();
    vusrv.update(this.getHeaders(vos), PurchaseinFIItemVO.class,
        EstVOUtil.getGoodsEstBUpdate());
    PurchaseinFIFeeVO[] items = this.getItems(vos);
    if (!ArrayUtils.isEmpty(items)) {
      VOInsert<PurchaseinFIFeeVO> voinsert = new VOInsert<PurchaseinFIFeeVO>();
      voinsert.insert(items);
    }
    prcr.after(vos);
  }

  /** 费用暂估 **/
  public void feeEstimate(PurchaseInEstVO[] vos, PurchaseInEstVO[] origVos) {
    CompareAroundProcesser<PurchaseInEstVO> prcr =
        new CompareAroundProcesser<PurchaseInEstVO>(
            PurchsInEstPluginPoint.FEE_EST_BP);
    this.addFeeEstRule(prcr);
    prcr.before(vos, origVos);
    PurchaseinFIFeeVO[] items = this.getItems(vos);
    VOInsert<PurchaseinFIFeeVO> voinsert = new VOInsert<PurchaseinFIFeeVO>();
    voinsert.insert(items);
    prcr.after(vos, origVos);
  }

  private void addFeeEstRule(CompareAroundProcesser<PurchaseInEstVO> prcr) {
    prcr.addBeforeFinalRule(new PurchsInFeeEstNotNullChkRule());// 对暂估费用的非空检查
    prcr.addBeforeFinalRule(new PurchsInEstimatedFeeChkRule());// 检查是否已经有费用项进行过暂估
    prcr.addBeforeFinalRule(new PurchsInSettledFeeChkRule());// 检查是否已经有费用项进行过结算
    prcr.addBeforeFinalRule(new PurchsInFeeEstInfoFillRule());// 费用暂估必须信息设置
    prcr.addBeforeFinalRule(new FeeEstSettleTOIAChkRule<PurchaseInEstVO>());// 检查货物结算是否已经传成本
    prcr.addAfterFinalRule(new PurchsInFeeEstTOIARule());// 费用暂估成本传IA
    prcr.addAfterFinalRule(new PurchsInFeeEstTOAPRule());// 费用暂估应付传AP
  }

  private void addRule(AroundProcesser<PurchaseInEstVO> prcr) {
    prcr.addBeforeFinalRule(new PurchsInEstNotNullChkRule());// 对暂估货物的非空项检查
    prcr.addBeforeFinalRule(new PurchsInFeeEstNotNullChkRule());// 对暂估费用的非空检查
    prcr.addBeforeFinalRule(new PurchsInSettledFeeChkRule());// 检查是否已经有费用项进行过结算
    prcr.addBeforeFinalRule(new PurchsInGoodsEstNecssFillRule());// 暂估前必须信息填充
    prcr.addBeforeFinalRule(new PurchsInFeeEstInfoFillRule());// 费用暂估必须信息设置

    // wuxla v61 设置记成本单价
    prcr.addBeforeFinalRule(new EstCalCostMnyPriceRule<PurchaseInEstVO>());

    prcr.addBeforeFinalRule(new PurchsInGoodsEstTOIARule());// 货物暂估成本传IA
    prcr.addBeforeFinalRule(new PurchsInGoodsEstTOPCIARule());// 货物暂估成本传PCIA
    prcr.addBeforeFinalRule(new PurchsInGoodsEstTOAPRule());// 暂估应付传AP
    prcr.addAfterFinalRule(new PurchsInFeeEstTOIARule());// 费用暂估成本传IA
    prcr.addAfterFinalRule(new PurchsInFeeEstTOAPRule());// 费用暂估应付传AP

  }

  /** 得到暂估VO表头数组，即要更新的采购入视图 **/
  private PurchaseInEstHeaderVO[] getHeaders(PurchaseInEstVO[] vos) {
    PurchaseInEstHeaderVO[] heads = new PurchaseInEstHeaderVO[vos.length];
    for (int i = 0; i < heads.length; i++) {
      heads[i] = vos[i].getParentVO();
    }
    return heads;
  }

  private PurchaseinFIFeeVO[] getItems(PurchaseInEstVO[] vos) {
    PurchaseinFIFeeVO[] items = AggVOUtil.getCombinItemVOs(vos);
    return items;
  }

}
