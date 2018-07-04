package nc.pubift.pu.m25.ia;

import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pub.BusinessException;
import nc.vo.scmpub.parameter.ia.QueryParaVO;

/**
 * @ClassName:IInvoiceQueryForIAClosingCheck
 * @Description:采购发票的为存货核算提供关账检查接口
 * @author liyjp
 * @date 2014-11-17 下午4:06:12
 */
public interface IInvoiceQueryForIAClosingCheck {

  /**
   * @Title:queryUnapprovedInvoice
   * @Description:存货核算未审批采购发票查询接口方法
   *                                表头开票日期在当前会计期间，财务组织=当前业务单元，自由态的采购发票
   * @param @param queryParaVO 查询参数VO
   *        包含pk_financeorg财务组织、startData会计期间开始日期、endpData会计期间结束日期
   * @param @throws BusinessException 业务异常
   * @return InvoiceHeaderVO[] 采购发票主表（表头）VO
   * @throws
   */
  InvoiceHeaderVO[] queryUnapprovedInvoice(QueryParaVO queryParaVO)
      throws BusinessException;

  /**
   * @Title:queryUnsettledInvoice
   * @Description:存货核算未结算采购发票查询接口方法
   *                                表头开票日期在当前会计期间，财务组织=当前业务单元，已审批未结算的采购发票
   * @param @param queryParaVO 查询参数VO
   *        包含pk_financeorg财务组织、startData会计期间开始日期、endpData会计期间结束日期
   * @param @throws BusinessException 业务异常
   * @return InvoiceHeaderVO[] 采购发票主表（表头）VO
   * @throws
   */
  InvoiceHeaderVO[] queryUnsettledInvoice(QueryParaVO queryParaVO)
      throws BusinessException;
}
