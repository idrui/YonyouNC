package nc.vo.pu.report.scale.supplierest;

import nc.adapter.ic.icreport.base.ICRptBaseScalePrcStrategy;

/**
 * 供应商暂估余额精度处理
 * 
 * @since 6.0
 * @version 2011-9-6 下午04:24:04
 * @author 田锋涛
 */

public class SupplierEstBalScaleStrategy extends ICRptBaseScalePrcStrategy {

  @Override
  protected void init() {
    // 暂估余额是汇总数据，不需要处理精度，但是合计需要处理一下
    String[] totalFields = new String[] {
      "TUNESTNUM", // 期初数量
      "TUNESTMNY", // 期初余额
      "TESTNUM", // 本期增加暂估数量
      "TNESTMNY", // 本期增加暂估金额
      "TNSETTLENUM", // 本期冲销暂估数量
      "TNCLASHESTMONEY", // 本期冲销暂估金额
      "TNGOODSMONEY", // 本期结算金额
      "TENDUNESTNUM", // 期末数量
      "TENDUNESTMNY"// 期末余额
    };
    this.getReportScaleProcess().setTotalFields(totalFields);

  }
}
