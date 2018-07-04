package nc.pubitf.pu.m422x.ewm.m4b36;

import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pub.BusinessException;
/**
 * 物资需求申请单为资产维修工单提供的查询服务
 * @author zhangshqb
 *
 */
public interface IQuery422xFor4b36 {
  /**
   * 方法功能描述：根据资产维修工单表头ID查询下游物资需求申请单。
   * <p>
   * <b>参数说明</b>
   * 
   * @param billids
   *          资产维修工单表头id数组
   * @return 物资需求申请单
   * @throws BusinessException
   *           <p>
   */
  StoreReqAppVO[] queryBillBySource(String[] billids) throws BusinessException;

  /**
   * 方法功能描述：根据资产维修工单表体ID数组查询下游物资需求申请单。
   * <p>
   * <b>参数说明</b>
   * 
   * @param itemids
   *          资产维修工单表体id数组
   * @return 物资需求申请单
   * @throws BusinessException
   */
  StoreReqAppVO[] queryBillBySourceBID(String[] itemids) throws BusinessException;

}
