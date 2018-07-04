/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-17 下午01:42:31
 */
package nc.itf.pu.est.m50;

import nc.vo.pu.est.entity.m50.VmiEstVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>消耗汇总暂估维护操作
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-8-17 下午01:42:31
 */
public interface IVMIEstMaintain {
  /**
   * 方法功能描述：取消暂估。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   *          要取消的暂估VO数组
   * @param onlyCancelFee
   *          true - 只取消费用暂估 false - 货物和费用同时取消
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-8-17 下午01:44:58
   */
  public void cancelEst(VmiEstVO[] vos, UFBoolean onlyCancelFee)
      throws BusinessException;

  /**
   * 方法功能描述：费用暂估。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   *          要暂估VO数组
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-8-17 下午01:45:48
   */
  public void feeEst(VmiEstVO[] vos) throws BusinessException;

  /**
   * 方法功能描述：货物暂估(可同时费用暂估)。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   *          要暂估VO数组
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-8-17 下午01:46:10
   */
  public void goodsEst(VmiEstVO[] vos) throws BusinessException;
}
