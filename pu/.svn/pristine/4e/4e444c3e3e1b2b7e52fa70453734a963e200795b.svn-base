/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-7 下午04:14:54
 */
package nc.ui.pu.est.action.m50;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.est.m50.IVMIEstMaintain;
import nc.ui.pu.est.action.CancelEstAction;
import nc.vo.pu.est.entity.EstVO;
import nc.vo.pu.est.entity.m50.VmiEstVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>消耗汇总取消暂估前台动作
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-7-7 下午04:14:54
 */
public class VMICancelEstAction extends CancelEstAction {

  /**
   * 
   */
  private static final long serialVersionUID = -5303356237220584141L;

  @Override
  protected void cancelEst(EstVO[] vos, UFBoolean onlyCancelFee)
      throws BusinessException {
    IVMIEstMaintain srv = NCLocator.getInstance().lookup(IVMIEstMaintain.class);
    srv.cancelEst((VmiEstVO[]) vos, onlyCancelFee);
  }

}
