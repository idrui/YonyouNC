package nc.pubitf.pu.m4203.ia;

import nc.vo.pub.BusinessException;
import nc.vo.scmpub.parameter.ia.QueryParaVO;

/**
 * @ClassName:IEntrustProcessStorageQueryForIACC
 * @Description:委托加工入库单的存货核算关账接口
 * @author liyjp
 * @date 2014-11-24 上午11:20:02
 */
public interface IEntrustProcessStorageQueryForIACC {
  /**
   * @Title:queryUnsettledEPS
   * @Description:存货核算未结算委托加工入库单查询
   * @param:@param queryParaVO 查询参数VO
   *               包含pk_financeorg财务组织、startData会计期间开始日期、endpData会计期间结束日期
   * @return String[] 委托加工入库单表头VO数组（Pk_stockps）
   * @throws BusinessException 业务异常
   */
  String[] queryUnsettledEPS(QueryParaVO queryParaVO) throws BusinessException;
}
