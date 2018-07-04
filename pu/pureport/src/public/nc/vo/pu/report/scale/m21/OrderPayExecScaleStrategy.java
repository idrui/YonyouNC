package nc.vo.pu.report.scale.m21;

import java.util.ArrayList;

import nc.adapter.ic.icreport.base.ICRptBaseScalePrcStrategy;
import nc.vo.pu.report.scale.PUPubMetaNameConst;

/**
 * 采购订单付款执行情况
 * 
 * @since 6.0
 * @version 2011-9-7 下午01:35:05
 * @author 田锋涛
 */

public class OrderPayExecScaleStrategy extends ICRptBaseScalePrcStrategy {

  // 币种
  @Override
  protected String getCurrencyKey() {
    return PUPubMetaNameConst.CCURRENCYID;
  }

  // 金额字段
  @Override
  protected String[] getMnyFields() {
    ArrayList<String> arrayListStr = new ArrayList<String>();
    arrayListStr.add(PUPubMetaNameConst.NORDERMNY);// 订单本币金额
    arrayListStr.add(PUPubMetaNameConst.NINVOICEMNY);// 发票本币金额
    arrayListStr.add(PUPubMetaNameConst.NPAYMNY);// 订单付款金额
    arrayListStr.add(PUPubMetaNameConst.NUNVERIFYMNY);// 未核销付款金额
    arrayListStr.add(PUPubMetaNameConst.NINVOICEBALANCE);// 发票应付余额
    arrayListStr.add(PUPubMetaNameConst.NORDERBALANCE);// 订单应付余额

    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  @Override
  protected void init() {
    //
  }
}
