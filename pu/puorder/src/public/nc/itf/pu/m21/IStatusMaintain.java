/**
 * $文件说明$
 * 
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-1 下午03:20:15
 */
package nc.itf.pu.m21;

import nc.vo.pu.m21.entity.OrderOnwayVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.StatusOnWayItemVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>订单状态维护
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-7-1 下午03:20:15
 */
public interface IStatusMaintain {

  /**
   * 方法功能描述：对方确认反操作
   * <p>
   * <b>参数说明</b>
   * 
   * @param newVOs
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-8-19 上午11:10:49
   */
  public void deleteForConfirm(OrderVO[] newVOs) throws BusinessException;

  /**
   * 方法功能描述：输出反操作
   * <p>
   * <b>参数说明</b>
   * 
   * @param newVOs
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-8-19 上午11:10:49
   */
  public void deleteForOutput(OrderVO[] newVOs) throws BusinessException;

  /**
   * 方法功能描述：在途状态反操作时删除子子表中数据
   * <p>
   * <b>参数说明</b>
   * 
   * @param newVOs
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-8-19 上午11:10:31
   */
  public void deleteOnway(OrderOnwayVO[] newVOs, int status)
      throws BusinessException;

  /**
   * 方法功能描述：对方确认更新
   * <p>
   * <b>参数说明</b>
   * 
   * @param newVOs
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-8-19 上午11:10:49
   */
  public OrderVO[] updateForConfirm(OrderVO[] newVOs) throws BusinessException;

  /**
   * 方法功能描述：输出更新
   * <p>
   * <b>参数说明</b>
   * 
   * @param newVOs
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-8-19 上午11:10:49
   */
  public OrderVO[] updateForOutput(OrderVO[] newVOs) throws BusinessException;

  /**
   * 方法功能描述：在途状态操作时插入子子表数据
   * <p>
   * <b>参数说明</b>
   * 
   * @param newVOs
   * @param status
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-8-19 上午11:11:06
   */
  public StatusOnWayItemVO[] updateOnway(OrderOnwayVO[] newVOs, int status)
      throws BusinessException;

}
