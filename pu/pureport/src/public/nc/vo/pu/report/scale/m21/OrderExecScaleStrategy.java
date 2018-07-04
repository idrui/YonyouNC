package nc.vo.pu.report.scale.m21;

import java.util.ArrayList;
import java.util.List;

import nc.adapter.ic.icreport.base.ICRptBaseScalePrcStrategy;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.report.scale.PUPubMetaNameConst;
import nc.vo.pu.report.view.order.OrderExecDetailViewMeta;

/**
 * @since 6.0
 * @version 2011-9-7 下午01:35:05
 * @author 田锋涛
 */

public class OrderExecScaleStrategy extends ICRptBaseScalePrcStrategy {

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
    List<String> arrayListStr = new ArrayList<String>();
    // 本币无税金额
    arrayListStr.add(OrderItemVO.NMNY);
    // 本币价税合计
    arrayListStr.add(OrderItemVO.NTAXMNY);
    // 本币税额
    arrayListStr.add(OrderItemVO.NTAX);
    arrayListStr.add(OrderExecDetailViewMeta.INVOICE_NTAXMNY);
    arrayListStr.add(OrderExecDetailViewMeta.NACCCANCELINVMNY);
    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  // 金额字段
  @Override
  protected String[] getMnyFields() {
    List<String> arrayListStr = new ArrayList<String>();
    // norigmny 无税金额
    arrayListStr.add(OrderItemVO.NORIGMNY);
    // norigtaxmny 价税合计
    arrayListStr.add(OrderItemVO.NORIGTAXMNY);
    // // norigtax 税额
    // arrayListStr.add(OrderItemVO.NORIGTAX);
    // 入库金额
    arrayListStr.add(OrderExecDetailViewMeta.IC_NORIGMNY);
    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  // 主数量字段
  @Override
  protected String[] getNumFields() {
    List<String> arrayListStr = new ArrayList<String>();
    // 主数量
    arrayListStr.add(OrderItemVO.NNUM);
    arrayListStr.add(OrderExecDetailViewMeta.CONFIRM_NNUM);
    arrayListStr.add(OrderExecDetailViewMeta.OUTPUT_NNUM);
    // 到货主数量
    arrayListStr.add(OrderExecDetailViewMeta.ARRIVE_NNUM);
    // 退货主数量
    arrayListStr.add(OrderExecDetailViewMeta.NBACKARRVNUM);
    arrayListStr.add(OrderExecDetailViewMeta.QC_NNUM);
    arrayListStr.add(OrderExecDetailViewMeta.ARRIVE_BLARGESSNUM);
    arrayListStr.add(OrderExecDetailViewMeta.NELIGNUM);
    // 发票主数量
    arrayListStr.add(OrderExecDetailViewMeta.INVOICE_NNUM);

    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  // 价格字段
  @Override
  protected String[] getPriceFields() {
    List<String> arrayListStr = new ArrayList<String>();
    // 采购订单执行查询
    // 无税单价
    arrayListStr.add(OrderItemVO.NQTORIGPRICE);
    // 含税单价
    arrayListStr.add(OrderItemVO.NQTORIGTAXPRICE);
    // 无税净价
    arrayListStr.add(OrderItemVO.NQTORIGNETPRICE);
    // 含税净价
    arrayListStr.add(OrderItemVO.NQTORIGTAXNETPRC);
    // 主无税净价
    arrayListStr.add(OrderItemVO.NORIGNETPRICE);
    // 主含税净价
    arrayListStr.add(OrderItemVO.NORIGTAXNETPRICE);
    // 主本币无税净价
    arrayListStr.add(OrderItemVO.NNETPRICE);
    // 主本币含税净价
    arrayListStr.add(OrderItemVO.NTAXNETPRICE);
    // 入库单价
    arrayListStr.add(OrderExecDetailViewMeta.IC_NORIGPRICE);
    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  protected String[] getStockNumFields() {
    List<String> arrayListStr = new ArrayList<String>();
    // 入库主数量
    arrayListStr.add(OrderExecDetailViewMeta.IC_BLARGESSNUM);
    arrayListStr.add(OrderExecDetailViewMeta.IC_NNUM);
    arrayListStr.add(OrderExecDetailViewMeta.NBACKSTORENUM);
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
    // 入库相关数量
    this.setNumDigits(OrderExecDetailViewMeta.IC_CUNITID,
        this.getStockNumFields());
    // 对本币金额等的处理
    this.setMnyDigits(OrderItemVO.CCURRENCYID, this.getCurrencyMnyFields());

    this.getReportScaleProcess().setConstantDigits(new String[] {
      OrderItemVO.NITEMDISCOUNTRATE
    }, 2);
  }
}
