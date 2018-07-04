package nc.pubitf.pu.m4202.ia;

import nc.vo.pub.BusinessException;
import nc.vo.scmpub.parameter.ia.QueryParaVO;

/**
 * @ClassName:IConsumeGatherQueryForIAClosingCheck
 * @Description:消耗汇总单的存货核算关账接口
 * @author liyjp
 * @date 2014-11-24 下午3:34:04
 */
public interface IConsumeGatherQueryForIAClosingCheck {
  /**
   * @Title:queryUnsettledConsumeGather
   * @Description:存货核算未结算消耗汇总单查询接口
   * @param:@param queryParaVO 查询参数VO
   *               包含pk_financeorg财务组织、startData会计期间开始日期、endpData会计期间结束日期
   * @return String[] 消耗汇总财务表头VO的主键[]（Pk_stockps_b）
   * @throws BusinessException 业务异常
   */
  String[] queryUnsettledConsumeGather(QueryParaVO queryParaVO)
      throws BusinessException;
}
