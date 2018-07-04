/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-9 下午01:21:32
 */
package nc.pubitf.pu.m4203;

import nc.vo.ic.m47.entity.SubcontInVO;
import nc.vo.pub.BusinessException;

/**
 * 委托加工入库单财务（采购结算）副本维护服务。<br>
 * 采购结算提供给委托加工入库单的服务，在签字和取消签字时调用。
 * 
 * @since 6.0
 * @version 2011-1-20 上午10:42:24
 * @author zhaoyha
 */
public interface ISubcontinFIMaintain {

  /**
   * 委托加工入库单取消签字时删除财务（采购结算）副本。
   * 
   * @param hids 委托加工入库单表头ID数组
   * @throws BusinessException
   */
  public void cancelDuplicate(String[] hids) throws BusinessException;

  /**
   * 委托加工入库单签字时写财务（采购结算）副本。
   * 
   * @param vos 委托加工入库单聚合VO
   * @throws BusinessException
   */
  public void duplicate(SubcontInVO[] vos) throws BusinessException;
}
