package nc.vo.pu.report.scale.m25;

import java.util.ArrayList;

import nc.adapter.ic.icreport.base.ICRptBaseScalePrcStrategy;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pu.report.scale.PUPubMetaNameConst;

/**
 * 采购入库待开票数量
 * 
 * @since 6.0
 * @version 2011-9-7 下午01:39:41
 * @author 田锋涛
 */

public class M45NoInvoiceScaleStrategy extends ICRptBaseScalePrcStrategy {

  @Override
  protected String getCurrencyKey() {
    return PurchaseinFIItemVO.CORIGCURRENCYID;
  }

  @Override
  protected String[] getMnyFields() {
    ArrayList<String> arrayListStr = new ArrayList<String>();
    arrayListStr.add("toinvoicetaxmny"); // 待开票价税合计
    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  // 主数量字段
  @Override
  protected String[] getNumFields() {
    ArrayList<String> arrayListStr = new ArrayList<String>();
    arrayListStr.add(PUPubMetaNameConst.NINNUM);// 入库主数量
    arrayListStr.add(PUPubMetaNameConst.NOINVOICENUM);// noinvoicenum 未开票数量
    arrayListStr.add("naccumsettlenum");// 累计结算数量
    arrayListStr.add("nosettlenum");// 未结算数量
    arrayListStr.add("naccuminvoicenum");// 累计开票主数量
    arrayListStr.add("toinvoicenum");// 待开票数量

    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  // 价格字段
  @Override
  protected String[] getPriceFields() {
    ArrayList<String> arrayListStr = new ArrayList<String>();
    arrayListStr.add(PurchaseinFIItemVO.NESTOPRICE); // 主无税单价
    arrayListStr.add(PurchaseinFIItemVO.NESTOTAXPRICE); // 主含税单价
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
