/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-26 上午09:26:58
 */
package nc.itf.pu.m4t;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>期初暂估审批操作组件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-3-26 上午09:26:58
 */
public interface IInitialEstApprove {

  /**
   * 方法功能描述：审批。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @param context
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-3-26 上午09:47:34
   */
  public InitialEstVO[] approve(InitialEstVO[] vos, AbstractCompiler2 script)
      throws BusinessException;

  /**
   * 弃审 方法功能描述：简要描述本方法的功能。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-3-26 上午09:48:13
   */
  public InitialEstVO[] unapprove(InitialEstVO[] vos, AbstractCompiler2 script)
      throws BusinessException;
}
