/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-25 下午03:55:07
 */
package nc.impl.pu.est.m50.action;

import nc.bs.pu.est.EstVOTransferTool;
import nc.bs.pu.est.m50.VMIEstBP;
import nc.bs.pu.est.plugin.VMIEstPluginPoint;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pu.est.entity.m50.VmiEstVO;
import nc.vo.pu.est.enumeration.QueryEstType;
import nc.vo.pu.m4202.entity.VmiFIFeeVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>消耗汇总货物暂估后台动作
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-25 下午03:55:07
 */
public class VMIGoodsEstAction {
  public void estimate(VmiEstVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    EstVOTransferTool<VmiEstVO> tool =
        new EstVOTransferTool<VmiEstVO>(QueryEstType.GOODS_EST, vos,
            VmiFIFeeVO.class);
    VmiEstVO[] origVos = tool.getOrigVos();
    CompareAroundProcesser<VmiEstVO> prcr =
        new CompareAroundProcesser<VmiEstVO>(VMIEstPluginPoint.GOODS_EST_ACTION);
    this.addRule(prcr);
    prcr.before(vos, origVos);
    new VMIEstBP().estimate(vos);
    prcr.after(vos, origVos);
  }

  /**
   * @param prcr
   */
  private void addRule(CompareAroundProcesser<VmiEstVO> prcr) {
    //
  }
}
