package nc.pubift.pu.m25.ic.eurrpt;

import nc.vo.ic.icreport.param.mncommon.MNReportQueryParam;
import nc.vo.ic.icreport.param.mncommon.MNRptTempTableWrapperParam;

/**
 * Ϊ��������ṩ�ɹ���Ʊ�Ĳ�ѯ�ӿ�
 * 
 * @since 6.1
 * @version 2012-2-13 ����01:14:07
 * @author lixyp
 */
public interface IPUForICEURRpt {

  /**
   * Ϊ��������ṩ�ɹ���Ʊ�Ĳ�ѯ�ӿ�
   */
  public MNRptTempTableWrapperParam queryArriveAndInvoice(
      MNReportQueryParam mnReportQueryParam);
}
