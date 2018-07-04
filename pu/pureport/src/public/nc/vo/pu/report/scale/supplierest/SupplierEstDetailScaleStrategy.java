package nc.vo.pu.report.scale.supplierest;

import java.util.ArrayList;

import nc.adapter.ic.icreport.base.ICRptBaseScalePrcStrategy;
import nc.vo.pu.report.scale.PUPubMetaNameConst;

/**
 * 供应商暂估明细精度处理
 * 
 * @since 6.0
 * @version 2011-9-6 下午04:24:04
 * @author 田锋涛
 */

public class SupplierEstDetailScaleStrategy extends ICRptBaseScalePrcStrategy {

  // 币种
  @Override
  protected String getCurrencyKey() {
    return PUPubMetaNameConst.CORIGCURRENCYID;
  }

  /**
   * 本币金额相关字段
   * 
   * @return
   */
  protected String[] getCurrencyMnyFields() {
    ArrayList<String> arrayListStr = new ArrayList<String>();

    // 供应商暂估明细查询
    arrayListStr.add(PUPubMetaNameConst.TNESTMNY);// tnestmny 暂估金额
    arrayListStr.add(PUPubMetaNameConst.TNCLASHESTMONEY);// tnclashestmoney //
                                                         // 冲销暂估金额
    arrayListStr.add(PUPubMetaNameConst.TNGOODSMONEY);// tngoodsmoney 结算金额

    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  // 金额字段
  @Override
  protected String[] getMnyFields() {
    return null;
  }

  // 主数量字段
  @Override
  protected String[] getNumFields() {
    ArrayList<String> arrayListStr = new ArrayList<String>();
    // 供应商暂估明细查询
    arrayListStr.add(PUPubMetaNameConst.TNESTNUM);// tnestnum 暂估数量
    arrayListStr.add(PUPubMetaNameConst.TNSETTLENUM);// tnsettlenum 冲销暂估数量
    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  // 主单位
  @Override
  protected String getUnitKey() {
    return PUPubMetaNameConst.CUNITID;
  }

  @Override
  protected void init() {
    // 添加对本币金额等的处理
    this.setMnyDigits(PUPubMetaNameConst.CCURRENCYID,
        this.getCurrencyMnyFields());
    // 处理VID精度
    this.setNumDigits("this." + PUPubMetaNameConst.PK_MATERIAL + "."
        + PUPubMetaNameConst.PK_MEASDOC, this.getNumFields());
    // this.setNumDigits("this.PK_MATERIAL.pk_measdoc", this.getNumFields());

  }
}
