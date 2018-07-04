/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-24 下午04:27:52
 */
package nc.bs.pu.est.m50;

import nc.bs.pu.est.m45.rule.settle.VMISettledFeeChkRule;
import nc.bs.pu.est.m50.rule.VMIEstimatedFeeChkRule;
import nc.bs.pu.est.m50.rule.VMIFeeEstInfoFillRule;
import nc.bs.pu.est.m50.rule.VMIFeeEstTOAPRule;
import nc.bs.pu.est.m50.rule.VMIFeeEstTOIARule;
import nc.bs.pu.est.m50.rule.VMIGoodsEstNecssFillRule;
import nc.bs.pu.est.m50.rule.VMIGoodsEstTOAPRule;
import nc.bs.pu.est.m50.rule.VMIGoodsEstTOIARule;
import nc.bs.pu.est.plugin.VMIEstPluginPoint;
import nc.bs.pu.est.rule.EstCalCostMnyPriceRule;
import nc.bs.pu.est.rule.FeeEstSettleTOIAChkRule;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.data.vo.VOInsert;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pu.est.entity.m50.VmiEstHeaderVO;
import nc.vo.pu.est.entity.m50.VmiEstVO;
import nc.vo.pu.est.rule.m50.VMIFeeEstNotNullChkRule;
import nc.vo.pu.est.rule.m50.VMIGoodsEstNotNullChkRule;
import nc.vo.pu.est.util.EstVOUtil;
import nc.vo.pu.m4202.entity.VmiFIFeeVO;
import nc.vo.pu.m4202.entity.VmiFIHeaderVO;
import nc.vo.pu.pub.util.AggVOUtil;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>消耗汇总暂估BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-24 下午04:27:52
 */
public class VMIEstBP {

  /** 入库单手工暂估 **/
  public void estimate(VmiEstVO[] vos) {
    AroundProcesser<VmiEstVO> prcr =
        new AroundProcesser<VmiEstVO>(VMIEstPluginPoint.EST_BP);
    this.addRule(prcr);
    prcr.before(vos);
    ViewUpdate<VmiEstHeaderVO> vusrv = new ViewUpdate<VmiEstHeaderVO>();
    vusrv.update(this.getHeaders(vos), VmiFIHeaderVO.class,
        EstVOUtil.getGoodsEstBUpdate());
    VmiFIFeeVO[] items = this.getItems(vos);
    if (!ArrayUtils.isEmpty(items)) {
      VOInsert<VmiFIFeeVO> voinsert = new VOInsert<VmiFIFeeVO>();
      voinsert.insert(items);
    }
    prcr.after(vos);
  }

  /** 费用暂估 **/
  public void feeEstimate(VmiEstVO[] vos, VmiEstVO[] origVos) {
    CompareAroundProcesser<VmiEstVO> prcr =
        new CompareAroundProcesser<VmiEstVO>(VMIEstPluginPoint.FEE_EST_BP);
    this.addFeeEstRule(prcr);
    prcr.before(vos, origVos);
    VmiFIFeeVO[] items = this.getItems(vos);
    VOInsert<VmiFIFeeVO> voinsert = new VOInsert<VmiFIFeeVO>();
    voinsert.insert(items);
    prcr.after(vos, origVos);
  }

  private void addFeeEstRule(CompareAroundProcesser<VmiEstVO> prcr) {
    prcr.addBeforeFinalRule(new VMIFeeEstNotNullChkRule());// 对暂估费用的非空检查
    prcr.addBeforeFinalRule(new VMIEstimatedFeeChkRule());// 检查是否已经有费用项进行过暂估
    prcr.addBeforeFinalRule(new VMISettledFeeChkRule());// 检查是否已经有费用项进行过结算
    prcr.addBeforeFinalRule(new VMIFeeEstInfoFillRule());// 费用暂估必须信息设置
    prcr.addBeforeFinalRule(new FeeEstSettleTOIAChkRule<VmiEstVO>());// 检查货物结算是否已经传成本
    prcr.addAfterFinalRule(new VMIFeeEstTOIARule());// 费用暂估成本传IA
    prcr.addAfterFinalRule(new VMIFeeEstTOAPRule());// 费用暂估应付传AP
  }

  private void addRule(AroundProcesser<VmiEstVO> prcr) {
    prcr.addBeforeFinalRule(new VMIGoodsEstNotNullChkRule());// 对暂估货物的非空项检查
    prcr.addBeforeFinalRule(new VMIFeeEstNotNullChkRule());// 对暂估费用的非空检查
    prcr.addBeforeFinalRule(new VMISettledFeeChkRule());// 检查是否已经有费用项进行过结算
    prcr.addBeforeFinalRule(new VMIGoodsEstNecssFillRule());// 暂估前必须信息填充
    prcr.addBeforeFinalRule(new VMIFeeEstInfoFillRule());// 费用暂估必须信息设置

    // wuxla v61 记成本单价设置
    prcr.addBeforeFinalRule(new EstCalCostMnyPriceRule<VmiEstVO>());

    prcr.addBeforeFinalRule(new VMIGoodsEstTOIARule());// 货物暂估成本传IA
    prcr.addBeforeFinalRule(new VMIGoodsEstTOAPRule());// 暂估应付传AP
    prcr.addAfterFinalRule(new VMIFeeEstTOIARule());// 费用暂估成本传IA
    prcr.addAfterFinalRule(new VMIFeeEstTOAPRule());// 费用暂估应付传AP

  }

  /** 得到暂估VO表头数组，即要更新的采购入视图 **/
  private VmiEstHeaderVO[] getHeaders(VmiEstVO[] vos) {
    VmiEstHeaderVO[] heads = new VmiEstHeaderVO[vos.length];
    for (int i = 0; i < heads.length; i++) {
      heads[i] = vos[i].getParentVO();
    }
    return heads;
  }

  private VmiFIFeeVO[] getItems(VmiEstVO[] vos) {
    VmiFIFeeVO[] items = AggVOUtil.getCombinItemVOs(vos);
    return items;
  }

}
