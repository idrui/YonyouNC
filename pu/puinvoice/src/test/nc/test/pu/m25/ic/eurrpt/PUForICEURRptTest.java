package nc.test.pu.m25.ic.eurrpt;

import nc.bs.framework.common.NCLocator;
import nc.bs.framework.test.AbstractTestCase;
import nc.pubift.pu.m25.ic.eurrpt.IPUForICEURRpt;
import nc.vo.ic.icreport.param.mncommon.MNReportQueryParam;
import nc.vo.ic.icreport.param.mncommon.MNRptTempTableWrapperParam;
import nc.vo.pub.lang.UFDateTime;

public class PUForICEURRptTest extends AbstractTestCase {

  public void test() {
    MNReportQueryParam mnReportQueryParam = new MNReportQueryParam();
    mnReportQueryParam.setDstartdate(new UFDateTime("2000-02-01 23:01:43"));
    mnReportQueryParam.setDenddate(new UFDateTime("2100-02-01 15:41:12"));
    mnReportQueryParam.setCorpoid("sdfsdfsdfdsfsdfsd");
    mnReportQueryParam.setCreportcountryid("reportcountry");

    IPUForICEURRpt puForICEURRpt =
        NCLocator.getInstance().lookup(IPUForICEURRpt.class);
    MNRptTempTableWrapperParam param =
        puForICEURRpt.queryArriveAndInvoice(mnReportQueryParam);
    System.out.println(param.getSqlString());
  }
}
