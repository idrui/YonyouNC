/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-6 下午04:33:10
 */
package nc.impl.pu.est.m50.action;

import nc.bs.pu.est.EstVOTransferTool;
import nc.bs.pu.est.m50.VMICancelEstBP;
import nc.bs.pu.est.plugin.VMIEstPluginPoint;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.vo.pu.est.entity.m50.VmiEstVO;
import nc.vo.pu.est.enumeration.QueryEstType;
import nc.vo.pu.m4202.entity.VmiFIFeeVO;

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
public class VMICancelEstAction {
  /** 取消货物和费用暂估 **/
  public void cancelEstimate(VmiEstVO[] vos) {
    EstVOTransferTool<VmiEstVO> tool =
        new EstVOTransferTool<VmiEstVO>(QueryEstType.UN_EST, vos,
            VmiFIFeeVO.class);
    VmiEstVO[] origVos = tool.getOrigVos();
    CompareAroundProcesser<VmiEstVO> prcr =
        new CompareAroundProcesser<VmiEstVO>(VMIEstPluginPoint.UNEST_ACTION);
    this.addRule(prcr);
    prcr.before(vos, origVos);
    new VMICancelEstBP().cancelEst(vos);
    prcr.after(vos, origVos);
  }

  /** 只取消费用暂估 **/
  public void cancelFeeEstimate(VmiEstVO[] vos) {
    EstVOTransferTool<VmiEstVO> tool =
        new EstVOTransferTool<VmiEstVO>(QueryEstType.UN_EST, vos,
            VmiFIFeeVO.class);
    VmiEstVO[] origVos = tool.getOrigVos();
    CompareAroundProcesser<VmiEstVO> prcr =
        new CompareAroundProcesser<VmiEstVO>(
            VMIEstPluginPoint.UN_FEE_EST_ACTION);
    this.addRule(prcr);
    prcr.before(vos, origVos);
    new VMICancelEstBP().cancelFeeEst(vos);
    prcr.after(vos, origVos);
  }

  /**
   * @param prcr
   */
  private void addRule(CompareAroundProcesser<VmiEstVO> prcr) {
    //
  }

}
