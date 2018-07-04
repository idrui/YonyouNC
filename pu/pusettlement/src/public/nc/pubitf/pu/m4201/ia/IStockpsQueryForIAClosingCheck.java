package nc.pubitf.pu.m4201.ia;

import nc.vo.pub.BusinessException;
import nc.vo.scmpub.parameter.ia.QueryParaVO;

/**
 * @Description:采购入库单为存货核算提供关账检查接口
 * @date 2014-11-24 上午9:02:44
 * @author liyjp
 */
public interface IStockpsQueryForIAClosingCheck {
  /**
   * @Title:queryUnsettledPFI
   * @Description:存货核算未结算采购入库单查询接口
   * @param: queryParaVO 查询参数VO
   *         包含pk_financeorg财务组织、startData会计期间开始日期、endpData会计期间结束日期
   * @return PurchaseinFIHeaderVO[] 采购入库单表头VO
   * @throws BusinessException 业务异常
   */
  String[] queryUnsettledPFI(QueryParaVO queryParaVO) throws BusinessException;

}
