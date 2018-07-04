/**
 * $文件说明$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-2 下午02:11:25
 */
package nc.itf.pu.m20;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;

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
 * @time 2010-2-2 下午02:11:25
 */
public interface IPraybillApprove {

  /**
   * 方法功能描述：请购单审批。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @param script
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author linsf
   * @return 审批后的请购单
   * @time 2010-2-2 下午02:11:57
   */
  PraybillVO[] approve(PraybillVO[] vos, AbstractCompiler2 script)
      throws BusinessException;

  /**
   * 方法功能描述：请购单送审。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @param script
   * @return 送审的请购单
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-26 上午11:23:51
   */
  PraybillVO[] sendapprove(PraybillVO[] vos, AbstractCompiler2 script)
      throws BusinessException;

  /**
   * 方法功能描述：请购单弃审。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @param script
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author linsf
   * @return 弃审后的请购单
   * @time 2010-2-2 下午02:13:31
   */
  PraybillVO[] unApprove(PraybillVO[] vos, AbstractCompiler2 script)
      throws BusinessException;

  /**
   * 方法功能描述：请购单收回。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @param script
   * @return 收回的请购单
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-26 上午11:23:51
   */
  PraybillVO[] unSendapprove(PraybillVO[] vos, AbstractCompiler2 script)
      throws BusinessException;

}
