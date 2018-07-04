package nc.itf.pu.m24;

import nc.bs.pub.compiler.AbstractCompiler2;
import nc.vo.pu.m24.entity.PricestlVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>价格结算单审批操作
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-7-30 上午11:47:25
 */
public interface IPricestlApprove {

  /**
   * 方法功能描述：价格结算单审批。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @param script
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-7-30 上午11:47:58
   */
  PricestlVO[] approve(PricestlVO[] vos, AbstractCompiler2 script)
      throws BusinessException;

  /**
   * 方法功能描述：价格结算单送审。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @param script
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-7-30 上午11:49:12
   */
  PricestlVO[] sendapprove(PricestlVO[] vos, AbstractCompiler2 script)
      throws BusinessException;

  /**
   * 方法功能描述：价格结算单弃审。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @param script
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-7-30 上午11:48:58
   */
  PricestlVO[] unApprove(PricestlVO[] vos, AbstractCompiler2 script)
      throws BusinessException;

  /**
   * 方法功能描述：价格结算单收回。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @param script
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-7-30 上午11:49:12
   */
  PricestlVO[] unSendapprove(PricestlVO[] vos, AbstractCompiler2 script)
      throws BusinessException;

}
