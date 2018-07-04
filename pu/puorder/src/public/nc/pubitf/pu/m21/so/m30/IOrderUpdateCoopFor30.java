/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-27 下午01:58:26
 */
package nc.pubitf.pu.m21.so.m30;

import java.util.Map;

import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>生成协同销售订单回写采购订单
 * <li>设置表头已协同生成销售订单
 * <li>设置表头对方订单号
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-27 下午01:58:26
 */
public interface IOrderUpdateCoopFor30 {

  /**
   * 方法功能描述：生成协同销售订单回写采购订单，设置表头已协同生成销售订单和对方订单号
   * <p>
   * <b>参数说明</b>
   * 
   * @param flag
   *          增删标志：true为增加，false为删除
   * @param wbMap
   *          回写参数 key:采购订单表头主键 value:销售订单号
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-27 下午02:06:31
   */
  void updateCoopFlag(boolean flag, Map<String, String> wbMap)
      throws BusinessException;
}
