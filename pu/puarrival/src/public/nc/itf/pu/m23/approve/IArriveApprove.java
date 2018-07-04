package nc.itf.pu.m23.approve;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>到货单的审批操作接口定义，本类主要完成以下功能：</b>
 * <ul>
 * <li>到货单的审核
 * <li>到货单的弃审
 * <li>到货单的送审
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-11 下午01:38:53
 */
public interface IArriveApprove {

  /**
   * 方法功能描述：到货单的审核
   * <p>
   * <b>参数说明</b>
   * 
   * @param voArray
   * @param script
   * @return 全VO数组
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-19 下午01:30:09
   */
  public ArriveVO[] approveArrive(ArriveVO[] voArray, AbstractCompiler2 script)
      throws BusinessException;

  /**
   * 方法功能描述：到货单的送审
   * <p>
   * <b>参数说明</b>
   * 
   * @param voArray
   * @param script
   * @return 轻量级VO数组
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-19 下午01:31:03
   */
  public ArriveVO[] sendArrive(ArriveVO[] voArray, AbstractCompiler2 script)
      throws BusinessException;

  /**
   * 方法功能描述：到货单的弃审
   * <p>
   * <b>参数说明</b>
   * 
   * @param voArray
   * @param script
   * @return 全VO数组
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-19 下午01:30:47
   */
  public ArriveVO[] unApproveArrive(ArriveVO[] voArray, AbstractCompiler2 script)
      throws BusinessException;

  /**
   * 方法功能描述：到货单的收回
   * <p>
   * <b>参数说明</b>
   * 
   * @param voArray
   * @param script
   * @return 轻量级VO数组
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-19 下午01:31:03
   */
  public ArriveVO[] unSendArrive(ArriveVO[] voArray, AbstractCompiler2 script)
      throws BusinessException;
}
