package nc.pubitf.pu.m27;

import java.util.Map;

import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>为存货核算提供的查询服务
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-9-8 上午10:47:00
 */
public interface ISettleBillQueryForIA {
  /**
   * 方法功能描述：查询最新结算价
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_costregion
   *          成本域
   * @param pk_materials
   *          物料的OID数组
   * @return 最新结算价的MAP<物料OID，最新结算价>
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author duy
   * @time 2010-9-8 上午10:48:48
   */
  public Map<String, UFDouble> queryLatestSettlePrice(String pk_costregion,
      String[] pk_materials) throws BusinessException;
}
