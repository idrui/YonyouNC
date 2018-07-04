/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-19 下午09:13:57
 */
package nc.impl.pu.m422x;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.impl.pu.m422x.action.StoreReqAppApproveAction;
import nc.impl.pu.m422x.action.StoreReqAppSendApproveAction;
import nc.impl.pu.m422x.action.StoreReqAppUnApproveAction;
import nc.impl.pu.m422x.action.StoreReqAppUnSendApproveAction;
import nc.itf.pu.m422x.IStoreReqAppApprove;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
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
 * @author wuxla
 * @time 2010-7-19 下午09:13:57
 */
public class StoreReqAppApproveImpl implements IStoreReqAppApprove {

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.m422x.IStoreReqAppApprove#approve(nc.vo.pu.m422x.entity.StoreReqAppVO[],
   *      nc.bs.pub.compiler.AbstractCompiler2)
   */
  @Override
  public StoreReqAppVO[] approve(StoreReqAppVO[] vos, AbstractCompiler2 script)
      throws BusinessException {
    try {
      return new StoreReqAppApproveAction().approve(vos, script);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.m422x.IStoreReqAppApprove#sendapprove(nc.vo.pu.m422x.entity.StoreReqAppVO[],
   *      nc.bs.pub.compiler.AbstractCompiler2)
   */
  @Override
  public StoreReqAppVO[] sendapprove(StoreReqAppVO[] vos,
      AbstractCompiler2 script) throws BusinessException {
    try {
      return new StoreReqAppSendApproveAction().sendapprove(vos, script);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.m422x.IStoreReqAppApprove#unapprove(nc.vo.pu.m422x.entity.StoreReqAppVO[],
   *      nc.bs.pub.compiler.AbstractCompiler2)
   */
  @Override
  public StoreReqAppVO[] unapprove(StoreReqAppVO[] vos, AbstractCompiler2 script)
      throws BusinessException {
    try {
      return new StoreReqAppUnApproveAction().unapprove(vos, script);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.m422x.IStoreReqAppApprove#unSendapprove(nc.vo.pu.m422x.entity.StoreReqAppVO[],
   *      nc.bs.pub.compiler.AbstractCompiler2)
   */
  @Override
  public StoreReqAppVO[] unSendapprove(StoreReqAppVO[] vos,
      AbstractCompiler2 script) throws BusinessException {
    try {
      return new StoreReqAppUnSendApproveAction().unSendapprove(vos, script);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

}
