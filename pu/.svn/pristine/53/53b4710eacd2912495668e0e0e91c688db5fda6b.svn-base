package nc.impl.pu.m24;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.impl.pu.m24.action.PricestlApproveAction;
import nc.impl.pu.m24.action.PricestlSendAction;
import nc.impl.pu.m24.action.PricestlUnApproveAction;
import nc.impl.pu.m24.action.PricestlUnSendApproveAction;
import nc.itf.pu.m24.IPricestlApprove;
import nc.vo.pu.m24.entity.PricestlVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>价格结算单审批实现类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-7-30 上午11:50:00
 */
public class PricestlApproveImpl implements IPricestlApprove {

  @Override
  public PricestlVO[] approve(PricestlVO[] vos, AbstractCompiler2 script)
      throws BusinessException {
    try {
      return new PricestlApproveAction().approve(vos, script);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }

    return null;
  }

  @Override
  public PricestlVO[] sendapprove(PricestlVO[] vos, AbstractCompiler2 script)
      throws BusinessException {
    try {
      return new PricestlSendAction().sendapprove(vos, script);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public PricestlVO[] unApprove(PricestlVO[] vos, AbstractCompiler2 script)
      throws BusinessException {

    try {
      return new PricestlUnApproveAction().unapprove(vos, script);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }

    return null;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.m24.IPricestlApprove#unSendapprove(nc.vo.pu.m24.entity.PricestlVO[],
   *      nc.bs.pub.compiler.AbstractCompiler2)
   */
  @Override
  public PricestlVO[] unSendapprove(PricestlVO[] vos, AbstractCompiler2 script)
      throws BusinessException {
    try {
      return new PricestlUnSendApproveAction().unSendApprove(vos, script);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }

    return null;
  }

}
