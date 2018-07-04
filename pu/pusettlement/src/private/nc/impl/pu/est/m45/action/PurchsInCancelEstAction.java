/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-6 下午04:33:10
 */
package nc.impl.pu.est.m45.action;

import nc.bs.pu.est.CancelEstBP;
import nc.bs.pu.est.EstVOTransferTool;
import nc.bs.pu.est.m45.PurchsInCancelEstBP;
import nc.bs.pu.est.plugin.PurchsInEstPluginPoint;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pu.est.entity.m45.PurchaseInEstVO;
import nc.vo.pu.est.entity.m45.PurchaseinFIFDVO;
import nc.vo.pu.est.enumeration.QueryEstType;
import nc.vo.pu.est.util.EstVOUtil;
import nc.vo.pu.m4201.entity.PurchaseinFIFeeVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>取消暂估的后台动作
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-7-6 下午04:33:10
 */
public class PurchsInCancelEstAction {
  /** 取消货物和费用暂估 **/
  public void unEstimate(PurchaseInEstVO[] vos) {
    EstVOTransferTool<PurchaseInEstVO> tool =
        new EstVOTransferTool<PurchaseInEstVO>(QueryEstType.UN_EST, vos,
            PurchaseinFIFeeVO.class);
    PurchaseInEstVO[] origVos = tool.getOrigVos();
    CompareAroundProcesser<PurchaseInEstVO> prcr =
        new CompareAroundProcesser<PurchaseInEstVO>(
            PurchsInEstPluginPoint.UN_GOODSEST_ACTION);
    this.addRule(prcr);
    prcr.before(vos, origVos);
    new PurchsInCancelEstBP(PurchsInEstPluginPoint.UN_GOODSEST_BP,
        EstVOUtil.getGoodsEstBUpdate(), PurchaseinFIFDVO.class).cancelEst(vos);
    prcr.after(vos, origVos);
  }

  /** 只取消费用暂估 **/
  public void unFeeEstimate(PurchaseInEstVO[] vos) {
    EstVOTransferTool<PurchaseInEstVO> tool =
        new EstVOTransferTool<PurchaseInEstVO>(QueryEstType.UN_EST, vos,
            PurchaseinFIFeeVO.class);
    PurchaseInEstVO[] origVos = tool.getOrigVos();
    CompareAroundProcesser<PurchaseInEstVO> prcr =
        new CompareAroundProcesser<PurchaseInEstVO>(
            PurchsInEstPluginPoint.UN_FEEEST_ACTION);
    this.addRule(prcr);
    prcr.before(vos, origVos);
    new CancelEstBP(PurchsInEstPluginPoint.UN_FEEEST_BP, null,
        PurchaseinFIFDVO.class).cancelFeeEst(vos);
    prcr.after(vos, origVos);
  }

  /**
   * @param prcr
   */
  private void addRule(CompareAroundProcesser<PurchaseInEstVO> prcr) {
    //
  }

}
