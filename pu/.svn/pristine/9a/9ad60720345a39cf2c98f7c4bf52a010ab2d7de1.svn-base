/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-1 上午11:08:58
 */
package nc.impl.pu.est.m45.action;

import nc.bs.pu.est.EstVOTransferTool;
import nc.bs.pu.est.m45.PurchsInEstBP;
import nc.bs.pu.est.plugin.PurchsInEstPluginPoint;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pu.est.entity.m45.PurchaseInEstVO;
import nc.vo.pu.est.enumeration.QueryEstType;
import nc.vo.pu.m4201.entity.PurchaseinFIFeeVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>费用暂估的动作
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-7-1 上午11:08:58
 */
public class PurchsInFeeEstAction {
  public void estimate(PurchaseInEstVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    EstVOTransferTool<PurchaseInEstVO> tool =
        new EstVOTransferTool<PurchaseInEstVO>(QueryEstType.FEE_EST, vos,
            PurchaseinFIFeeVO.class);
    PurchaseInEstVO[] origVos = tool.getOrigVos();
    CompareAroundProcesser<PurchaseInEstVO> prcr =
        new CompareAroundProcesser<PurchaseInEstVO>(
            PurchsInEstPluginPoint.FEE_EST_ACTION);
    this.addRule(prcr);
    prcr.before(vos, origVos);
    new PurchsInEstBP().feeEstimate(vos, origVos);
    prcr.after(vos, origVos);
  }

  /**
   * @param prcr
   */
  private void addRule(CompareAroundProcesser<PurchaseInEstVO> prcr) {
    //
  }
}
