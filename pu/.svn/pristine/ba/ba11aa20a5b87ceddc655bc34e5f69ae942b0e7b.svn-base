package nc.pubitf.pu.m24;

import nc.vo.pu.m24.entity.PricParaVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.pub.MapSet;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>价格结算单提供的查询服务
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-8-13 下午01:45:11
 */
public interface IQueryPricestl {

  /**
   * 查询订单行是否已经被价格结算单引用
   * 调用时机：采购订单修订
   * 处理方式：使用订单id关联结算单表体行，然后反向查询入库单中记录的订单行id
   * 一个订单行可能会多次到货，对应进行多次入库，所以会对应多个价格结算单表体，需要去掉重复值
   * 
   * @param corderids 订单表头id
   * @return
   * @throws BusinessException
   */
  MapSet<String, String> queryOrderUsedByHid(String[] corderids)
      throws BusinessException;

  /**
   * 方法功能描述：取价格结算单上的结算价格。
   * <p>
   * <b>参数说明</b>
   * 
   * @param cgeneralbids
   *          入库单行ID
   * @return 价格结算单无税及含税价格后的VO数组
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-8-13 下午01:46:28
   */
  PricParaVO[] queryPricStlPrices(String[] cgeneralbids)
      throws BusinessException;
}
