/**
 * $文件说明$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-2 下午02:25:55
 */
package nc.impl.pu.m20;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.impl.pu.m20.action.PraybillApproveAction;
import nc.impl.pu.m20.action.PraybillSendApproveAction;
import nc.impl.pu.m20.action.PraybillUnApproveAction;
import nc.impl.pu.m20.action.PraybillUnSendApproveAction;
import nc.itf.pu.m20.IPraybillApprove;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author linsf
 * @time 2010-2-2 下午02:25:55
 */
public class PraybillApproveImpl implements IPraybillApprove {

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.m20.IPraybillApprove#approve(nc.vo.pu.m20.entity.PraybillVO[],
   *      nc.bs.pub.compiler.AbstractCompiler2)
   */
  @Override
  public PraybillVO[] approve(PraybillVO[] Vos, AbstractCompiler2 script)
      throws BusinessException {
    try {
      return new PraybillApproveAction().approve(Vos, script);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }

    return null;
  }

  @Override
  public PraybillVO[] sendapprove(PraybillVO[] vos, AbstractCompiler2 script)
      throws BusinessException {
    try {
      return new PraybillSendApproveAction().sendapprove(vos, script);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.m20.IPraybillApprove#unApprove(nc.vo.pu.m20.entity.PraybillVO[],
   *      nc.bs.pub.compiler.AbstractCompiler2)
   */
  @Override
  public PraybillVO[] unApprove(PraybillVO[] Vos, AbstractCompiler2 script)
      throws BusinessException {

    try {
      return new PraybillUnApproveAction().unApprove(Vos, script);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }

    return null;
  }

  @Override
  public PraybillVO[] unSendapprove(PraybillVO[] vos, AbstractCompiler2 script)
      throws BusinessException {
    try {
      return new PraybillUnSendApproveAction().unSendapprove(vos, script);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

}
