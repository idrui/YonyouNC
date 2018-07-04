package nc.vo.pu.report.scale.m25;

import java.util.ArrayList;

import nc.adapter.ic.icreport.base.ICRptBaseScalePrcStrategy;
import nc.itf.scmpub.reference.arap.ArapServicesForPUUtil;
import nc.ui.pub.bill.IBillItem;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.report.scale.PUPubMetaNameConst;
import nc.vo.pubapp.scale.FieldInfo;

/**
 * 发票明细查询
 * 
 * @since 6.0
 * @version 2011-9-7 下午01:39:41
 * @author 田锋涛
 */

public class InvoiceDetailScaleStrategy extends ICRptBaseScalePrcStrategy {

  private void setOrgExchange() {
    FieldInfo rate =
        new FieldInfo(InvoiceHeaderVO.NEXCHANGERATE, IBillItem.HEAD, null);
    FieldInfo srcCurr =
        new FieldInfo(InvoiceHeaderVO.CORIGCURRENCYID, IBillItem.HEAD, null);
    FieldInfo destCurr =
        new FieldInfo(InvoiceHeaderVO.CCURRENCYID, IBillItem.HEAD, null);
    FieldInfo org =
        new FieldInfo(PUEntity.M25_H_TAB + "." + InvoiceHeaderVO.PK_ORG,
            IBillItem.HEAD, null);
    this.getReportScaleProcess().setOrgExchangeCtlInfo(rate, srcCurr, destCurr,
        org);
  }

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

    arrayListStr.add(InvoiceItemVO.NMNY);// 本币无税金额
    arrayListStr.add(InvoiceItemVO.NTAXMNY);// 本币税额
    arrayListStr.add(InvoiceItemVO.NTAX);// 本币税额
    arrayListStr.add("inbandnesttotalmny");// 入库金额
    arrayListStr.add(ArapServicesForPUUtil.NVERIFYMNY);// 本币付款金额
    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  // 原币金额字段
  @Override
  protected String[] getMnyFields() {
    ArrayList<String> arrayListStr = new ArrayList<String>();

    arrayListStr.add(InvoiceItemVO.NORIGMNY);// 无税金额
    arrayListStr.add(InvoiceItemVO.NORIGTAXMNY);// 本币税额
    arrayListStr.add(ArapServicesForPUUtil.NORIGVERIFYMNY);// 累计付款金额
    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  // 主数量字段
  @Override
  protected String[] getNumFields() {
    ArrayList<String> arrayListStr = new ArrayList<String>();
    arrayListStr.add(InvoiceItemVO.NNUM);
    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  // 价格字段
  @Override
  protected String[] getPriceFields() {
    ArrayList<String> arrayListStr = new ArrayList<String>();
    // 采购发票明细查询
    arrayListStr.add(InvoiceItemVO.NORIGPRICE);// 主无税单价
    arrayListStr.add(InvoiceItemVO.NORIGTAXPRICE);// 主含税单价
    arrayListStr.add(InvoiceItemVO.NPRICE);// 主本币无税单价
    arrayListStr.add(InvoiceItemVO.NTAXPRICE);// 主本币含税单价
    arrayListStr.add(PUPubMetaNameConst.INBANDNTAXPRICE);// 入库单价

    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  // 税率字段
  @Override
  protected String[] getTaxRateStrFields() {
    return new String[] {
      PUPubMetaNameConst.NTAXRATE
    };
  }

  // 主单位
  @Override
  protected String getUnitKey() {
    return PUPubMetaNameConst.CUNITID;
  }

  @Override
  protected void init() {
    // 对本币金额等的处理
    this.setMnyDigits(PUPubMetaNameConst.CCURRENCYID,
        this.getCurrencyMnyFields());
    // 设置折本汇率精度
    this.setOrgExchange();
  }
}
