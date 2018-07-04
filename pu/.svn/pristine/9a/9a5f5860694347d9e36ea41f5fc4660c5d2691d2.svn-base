/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-25 上午10:52:54
 */
package nc.pubitf.pu.est.m50;

import nc.vo.pu.est.entity.m50.VmiEstVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>查询消耗汇总暂估信息公共服务
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-8-25 上午10:52:54
 */
public interface IVMIEstPubQuery {
  /**
   * 方法功能描述：根据消耗汇总BID(与HID相同)查询暂估信息
   * <p>
   * <b>参数说明</b>
   * 
   * @param bids
   *          消耗汇总BID(或HID)数组
   * @return VmiEstVO[] 指定ID的暂估VO，包括暂估和未暂估的数据
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-8-25 上午10:57:48
   */
  public VmiEstVO[] query(String[] bids) throws BusinessException;
}
