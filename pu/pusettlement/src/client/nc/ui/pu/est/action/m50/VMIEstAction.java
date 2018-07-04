/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-16 下午02:46:57
 */
package nc.ui.pu.est.action.m50;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.est.m50.IVMIEstMaintain;
import nc.ui.pu.est.action.AbstractEstAction;
import nc.vo.pu.est.entity.EstVO;
import nc.vo.pu.est.entity.m50.VmiEstVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>消耗汇总前台暂估动作
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-8-16 下午02:46:57
 */
public class VMIEstAction extends AbstractEstAction {

  private static final long serialVersionUID = -2913484043534025233L;

  private IVMIEstMaintain getService() {
    return NCLocator.getInstance().lookup(IVMIEstMaintain.class);
  }

  @Override
  protected void feeEstInvokeBS(EstVO[] vos) throws Exception {
    this.getService().feeEst((VmiEstVO[]) vos);
  }

  @Override
  protected void goodsEstInvokeBS(EstVO[] vos) throws Exception {
    this.getService().goodsEst((VmiEstVO[]) vos);
  }

}
