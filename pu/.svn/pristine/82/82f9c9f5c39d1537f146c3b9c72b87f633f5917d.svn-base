package nc.vo.pu.report.scale.estdiffer;

import java.util.ArrayList;

import nc.adapter.ic.icreport.base.ICRptBaseScalePrcStrategy;
import nc.vo.pu.report.scale.PUPubMetaNameConst;

/**
 * @since 6.1
 * @version 2012-8-17 下午05:03:44
 * @author tianft
 */
public class EstDifferScaleStrategy extends ICRptBaseScalePrcStrategy {

  // 币种
  @Override
  protected String getCurrencyKey() {
    return PUPubMetaNameConst.CCURRENCYID;
  }

  /**
   * 本币金额相关字段
   * 
   * @return
   */
  @Override
  protected String[] getMnyFields() {
    ArrayList<String> arrayListStr = new ArrayList<String>();
    // 暂估月统计
    arrayListStr.add(PUPubMetaNameConst.CURRUNESTMNY);// CURRUNESTMNY 本月未冲销暂估金额
    arrayListStr.add(PUPubMetaNameConst.PRENCLASHESTMONEY);// PRENCLASHESTMONEY
                                                           // 本月前冲销暂估金额
    arrayListStr.add(PUPubMetaNameConst.PREUNESTMNY);// PREUNESTMNY 本月前未冲销暂估金额
    arrayListStr.add(PUPubMetaNameConst.PRENGOODSMONEY);// PRENGOODSMONEY
                                                        // 本月前结算金额
    arrayListStr.add(PUPubMetaNameConst.CURRNGOODSMONEY);// CURRNGOODSMONEY
                                                         // 本月结算金额
    arrayListStr.add(PUPubMetaNameConst.CURRNCLASHESTMONEY);// CURRNCLASHESTMONEY
                                                            // 本月冲销暂估金额
    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  // 主数量字段
  @Override
  protected String[] getNumFields() {
    ArrayList<String> arrayListStr = new ArrayList<String>();
    // 暂估差异表
    arrayListStr.add(PUPubMetaNameConst.CURRNSETTLENUM);// CURRNSETTLENUM
    // 本月结算数量
    arrayListStr.add(PUPubMetaNameConst.CURRUNESTNUM);// CURRUNESTNUM
    // 本月未冲销暂估数量
    arrayListStr.add(PUPubMetaNameConst.PREUNESTNUM);// PREUNESTNUM
    // 本月前未冲销暂估数量
    arrayListStr.add(PUPubMetaNameConst.PRENSETTLENUM);// PRENSETTLENUM
    // 本月前结算数量

    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  @Override
  protected String[] getTaxRateStrFields() {
    // 两个差异率字段，借助税率字段做精度。
    return new String[] {
      "prendifferrate", "currndifferrate"
    };
  }

  // 主单位
  @Override
  protected String getUnitKey() {
    return PUPubMetaNameConst.CUNITID;
  }

  @Override
  protected void init() {

    // 合计不区分大小写后此代码去掉
    String[] totalFields = new String[] {
      "CURRNSETTLENUM",// CURRNSETTLENUM 本月结算数量
      "PRENSETTLENUM",// PRENSETTLENUM 本月前结算数量
      "PRENCLASHESTMONEY",// PRENCLASHESTMONEY 本月前冲销暂估金额
      "PRENGOODSMONEY",// PRENGOODSMONEY 本月前结算金额
      "CURRNGOODSMONEY",// CURRNGOODSMONEY 本月结算金额
      "CURRNCLASHESTMONEY"// CURRNCLASHESTMONEY 本月冲销暂估金额
    };
    this.getReportScaleProcess().setTotalFields(totalFields);
  }
}
