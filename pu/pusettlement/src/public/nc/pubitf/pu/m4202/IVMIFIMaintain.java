/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-9 下午01:21:32
 */
package nc.pubitf.pu.m4202;

import nc.vo.ic.m50.entity.VmiSumVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>消耗汇总财务(副本)维护服务
 * <li>为存库消耗汇总审批/取消审批的提供
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-8-9 下午01:21:32
 */
public interface IVMIFIMaintain {
  /**
   * 方法功能描述：消耗汇总取消审批时删除财务副本。
   * <p>
   * <b>参数说明</b>
   * 
   * @param hids 消耗汇总表头ID数组
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-8-9 下午01:24:52
   */
  public void cancelDupVMI(String[] hids) throws BusinessException;

  /**
   * 方法功能描述：消耗汇总审批时写财务副本。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos 消耗汇总VO数组
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-8-9 下午01:24:46
   */
  public void duplicateVMI(VmiSumVO[] vos) throws BusinessException;
}
