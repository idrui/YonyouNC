package nc.pubitf.pu.m20.am.m4b36;

import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>请购单为资产维修工单提供的查询服务
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-1-26 下午07:22:29
 */
public interface IQuery20For4b36 {
  /**
   * 方法功能描述：根据资产维修工单表头ID查询下游请购单。
   * <p>
   * <b>参数说明</b>
   * 
   * @param billids
   *          资产维修工单表头id数组
   * @return 请购单
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-5-21 下午03:54:18
   */
  PraybillVO[] queryBillBySource(String[] billids) throws BusinessException;

  /**
   * 方法功能描述：根据资产维修工单表体ID数组查询下游请购单。
   * <p>
   * <b>参数说明</b>
   * 
   * @param itemids
   *          资产维修工单表体id数组
   * @return 请购单
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-5-21 下午03:54:18
   */
  PraybillVO[] queryBillBySourceBID(String[] itemids) throws BusinessException;

}
