/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-25 下午03:55:07
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
 * <li>货物暂估动作
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-25 下午03:55:07
 */
public class PurchsInGoodsEstAction {
  public void estimate(PurchaseInEstVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    EstVOTransferTool<PurchaseInEstVO> tool =
        new EstVOTransferTool<PurchaseInEstVO>(QueryEstType.GOODS_EST, vos,
            PurchaseinFIFeeVO.class);
    PurchaseInEstVO[] origVos = tool.getOrigVos();
    CompareAroundProcesser<PurchaseInEstVO> prcr =
        new CompareAroundProcesser<PurchaseInEstVO>(
            PurchsInEstPluginPoint.GOODS_EST_ACTION);
    this.addRule(prcr);
    prcr.before(vos, origVos);
    new PurchsInEstBP().estimate(vos);
    prcr.after(vos, origVos);
  }

  /**
   * @param prcr
   */
  private void addRule(CompareAroundProcesser<PurchaseInEstVO> prcr) {
    //
  }
}
