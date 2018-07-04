/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-17 下午01:47:19
 */
package nc.impl.pu.est.m50;

import nc.impl.pu.est.m50.action.VMICancelEstAction;
import nc.impl.pu.est.m50.action.VMIFeeEstAction;
import nc.impl.pu.est.m50.action.VMIGoodsEstAction;
import nc.itf.pu.est.m50.IVMIEstMaintain;
import nc.vo.pu.est.entity.m50.VmiEstVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>消耗汇总暂估的实现
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-8-17 下午01:47:19
 */
public class VMIEstMaintainImpl implements IVMIEstMaintain {

  @Override
  public void cancelEst(VmiEstVO[] vos, UFBoolean onlyCancelFee)
      throws BusinessException {
    try {
      VMICancelEstAction action = new VMICancelEstAction();
      if (!onlyCancelFee.booleanValue()) {
        action.cancelEstimate(vos);
      }
      else {
        action.cancelFeeEstimate(vos);
      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  @Override
  public void feeEst(VmiEstVO[] vos) throws BusinessException {
    try {
      new VMIFeeEstAction().estimate(vos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  @Override
  public void goodsEst(VmiEstVO[] vos) throws BusinessException {
    try {
      new VMIGoodsEstAction().estimate(vos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

}
