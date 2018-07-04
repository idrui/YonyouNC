/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-28 下午04:49:14
 */
package nc.pubitf.pu.m21.ct.mz2;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.MapList;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购合同查询接口
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-5-28 下午04:49:14
 */
public interface IOrderQueryForZ2 {

  /**
   * 方法功能描述：是否存在关联某一合同的订单，存在返回true，否则返回false
   * <p>
   * <b>参数说明</b>
   * 
   * @param pkCt
   * @return
   * @throws BusinessException <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-28 下午04:52:52
   */
  public boolean isExistOrderFromCt(String pkCt) throws BusinessException;

  /**
   * 根据合同主表PK，查询关联它的订单表体行PK
   * 
   * @param pkCts
   * @return MapList{合同主表PK，订单表体行PK列表}
   * @throws BusinessException
   */
  MapList<String, String> getRelationOrderItem(String[] pkCts)
      throws BusinessException;

  /**
   * 是否存在关联某一合同的订单
   * 
   * @param pkCt 合同主键
   * @param pk_org 采购组织
   * @return key:采购组织，value如果没有UFBoolean.FALSE，如果有UFBoolean.TRUE
   * @throws BusinessException
   */
  Map<String, UFBoolean> isExistOrderFromCt(String pkCt, String[] pk_org)
      throws BusinessException;
}
