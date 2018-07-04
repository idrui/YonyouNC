package nc.pubitf.pu.m23.pu.m21;

import java.util.Map;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货单为采购订单提供可补货的退货单的查询接口类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-8 下午04:24:59
 */
public interface IQuery23For21 {

  /**
   * 方法功能描述：为采购订单提供可补货的退货单的查询方法
   * <p>
   * <b>参数说明</b>
   * 
   * @param sql
   *          查询语句
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-8 下午04:26:04
   */
  ArriveVO[] queryBackArrive(IQueryScheme queryScheme) throws BusinessException;

  /**
   * 判断订单是否有到货
   * 
   * @param bids 订单表体
   * @return 如果没有返回null或者false
   * @throws BusinessException
   */
  Map<String, UFBoolean> queryHaveArrive(String[] bids)
      throws BusinessException;

  /**
   * 判断订单到货计划是否有到货
   * 
   * @param bbids
   * @return
   * @throws BusinessException
   */
  Map<String, UFBoolean> queryHaveArriveByBBid(String[] bbids)
      throws BusinessException;
}
