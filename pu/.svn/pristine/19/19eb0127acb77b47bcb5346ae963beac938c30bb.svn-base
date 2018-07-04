/**
 * $文件说明$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-11-3 下午03:22:32
 */
package nc.pubitf.pu.m20.pub;

import java.util.Map;

import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.entity.PraybillViewVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>请购单查询类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-11-3 下午03:22:32
 */
public interface IQueryPrayBill {

  /**
   * 检查订单交易类型是否被请购单表体的订单类型引用
   * 
   * @param ordertransType 订单交易类型主键
   * @return
   * @throws BusinessException
   */
  String[] checkOrderTransTypeReference(String[] ordertransType)
      throws BusinessException;

  /**
   * 方法功能描述：检索交易类型是否被引用
   * <p>
   * <b>参数说明</b>
   * 
   * @param transType 请购单交易类型编码
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-11-3 下午03:23:26
   */
  String[] checkTransTypeReference(String[] transType) throws BusinessException;

  /**
   * 请购单是否直运
   * 
   * @param pk_praybills
   * @return
   * @throws BusinessException
   */
  Map<String, UFBoolean> isDirect(String[] pk_praybills)
      throws BusinessException;

  /**
   * 根据请购单表体主键查询直运来源销售订单表体
   * 
   * @param bids
   * @return
   * @throws BusinessException
   */
  Map<String, String> queryDirectSourceBidMap(String[] bids)
      throws BusinessException;

  /**
   * 根据请购单行查询对应请购行属性
   * 
   * @param pk_praybill_bs 请购单行
   * @param arrs 需要查询的属性
   * @return Map<String, PraybillItemVO> KEY:请购单行
   * @throws BusinessException
   */
  Map<String, PraybillItemVO> queryItemByPK(String[] pk_praybill_bs,
      String[] arrs) throws BusinessException;

  /**
   * 方法功能描述：根据请购单行查询对应的主本币含税单价（币种为请购单主组织所属财务组织的本位币，单位为物料的主单位）。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_praybill_bs
   *          需要查询的请购单行
   * @return Map<请购单行，主本币含税单价>
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-5-27 上午10:18:43
   */
  Map<String, UFDouble> queryPriceByItemPK(String[] pk_praybill_bs)
      throws BusinessException;

  /**
   * 根据请购单行查询对应请购单属性
   * 
   * @param pk_praybill_bs 请购单行
   * @param arrs 需要查询的属性
   * @return Map<String, PraybillViewVO> KEY:请购单行
   * @throws BusinessException
   */
  Map<String, PraybillViewVO> queryViewByItemPK(String[] pk_praybill_bs,
      String[] arrs) throws BusinessException;

  PraybillVO[] queryVOByBids(String[] bids) throws BusinessException;
}
