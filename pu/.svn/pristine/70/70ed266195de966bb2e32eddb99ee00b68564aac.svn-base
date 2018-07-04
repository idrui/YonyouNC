package nc.vo.pu.report.scale.m20;

import java.util.ArrayList;

import nc.adapter.ic.icreport.base.ICRptBaseScalePrcStrategy;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.report.scale.PUPubMetaNameConst;

/**
 * 请购单执行明细精度处理
 * 
 * @since 6.0
 * @version 2011-9-7 下午01:29:42
 * @author 田锋涛
 */

public class PrayBillDetailScaleStrategy extends ICRptBaseScalePrcStrategy {

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
  protected String[] getCurrencyMnyFields() {
    ArrayList<String> arrayListStr = new ArrayList<String>();

    // 请购单执行明细查询
    arrayListStr.add(PraybillItemVO.NTAXMNY);// ntaxmny 本币价税合计
    arrayListStr.add(PUPubMetaNameConst.ORDER_NMNY);// nmny1 本币无税金额

    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  // 金额字段
  @Override
  protected String[] getMnyFields() {
    ArrayList<String> arrayListStr = new ArrayList<String>();
    // 请购单执行明细查询
    arrayListStr.add(PraybillItemVO.NTAXMNY);// ntaxmny 本币价税合计
    arrayListStr.add(PUPubMetaNameConst.ORDER_NMNY);// nmny1 本币无税金额
    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  // 主数量字段
  @Override
  protected String[] getNumFields() {
    ArrayList<String> arrayListStr = new ArrayList<String>();
    // 请购单执行明细查询
    arrayListStr.add(PUPubMetaNameConst.ORDER_NNUM);// 订货主数量
    arrayListStr.add(PUPubMetaNameConst.PRAYBILL_NNUM);// 请购单主数量
    arrayListStr.add("arrive_nnum");// 累计退货主数量
    arrayListStr.add("arrive_backnnum");// 到货主数量
    arrayListStr.add("purin_nnum");// 累计退货主数量
    arrayListStr.add("purin_backnnum");// 到货主数量
    arrayListStr.add("close_nnum");// 未执行关闭
    arrayListStr.add("replenish_nnum");// 补货主数量
    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  // 价格字段
  @Override
  protected String[] getPriceFields() {
    ArrayList<String> arrayListStr = new ArrayList<String>();
    // 请购单执行明细查询
    arrayListStr.add(PraybillItemVO.NTAXPRICE); // NTAXPRICE 请购单主本币含税单价
    arrayListStr.add(PUPubMetaNameConst.NPRICE);// NPRICE 订单主本币无税单价
    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  // 主单位
  @Override
  protected String getUnitKey() {
    return PUPubMetaNameConst.CUNITID;
  }

  @Override
  protected void init() {
    //
  }
}
