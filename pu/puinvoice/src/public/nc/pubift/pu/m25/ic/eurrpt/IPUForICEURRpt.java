package nc.pubift.pu.m25.ic.eurrpt;

import nc.vo.ic.icreport.param.mncommon.MNReportQueryParam;
import nc.vo.ic.icreport.param.mncommon.MNRptTempTableWrapperParam;

/**
 * 为跨国报表提供采购发票的查询接口
 * 
 * @since 6.1
 * @version 2012-2-13 下午01:14:07
 * @author lixyp
 */
public interface IPUForICEURRpt {

  /**
   * 为跨国报表提供采购发票的查询接口
   */
  public MNRptTempTableWrapperParam queryArriveAndInvoice(
      MNReportQueryParam mnReportQueryParam);
}
