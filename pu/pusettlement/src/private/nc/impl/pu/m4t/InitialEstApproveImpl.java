/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-26 上午10:47:47
 */
package nc.impl.pu.m4t;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.impl.pu.m4t.action.InitialEstApproveAction;
import nc.impl.pu.m4t.action.InitialEstUnApproveAction;
import nc.itf.pu.m4t.IInitialEstApprove;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>审批操作实现组件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-3-26 上午10:47:47
 */
public class InitialEstApproveImpl implements IInitialEstApprove {

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.m4t.IInitialEstApprove#approve(nc.vo.pu.m4t.entity.InitialEstVO[],
   *      nc.bs.pub.compiler.AbstractCompiler2)
   */
  @Override
  public InitialEstVO[] approve(InitialEstVO[] vos, AbstractCompiler2 script)
      throws BusinessException {
    try {
      return new InitialEstApproveAction().approve(vos, script);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.m4t.IInitialEstApprove#unapprove(nc.vo.pu.m4t.entity.InitialEstVO[],
   *      nc.bs.pub.compiler.AbstractCompiler2)
   */
  @Override
  public InitialEstVO[] unapprove(InitialEstVO[] vos, AbstractCompiler2 script)
      throws BusinessException {
    try {
      return new InitialEstUnApproveAction().unapprove(vos, script);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

}
