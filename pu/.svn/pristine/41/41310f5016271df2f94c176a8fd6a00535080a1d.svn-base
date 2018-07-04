package nc.vo.pu.report.scale.m21;

import java.util.ArrayList;

import nc.adapter.ic.icreport.base.ICRptBaseScalePrcStrategy;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.report.scale.PUPubMetaNameConst;

/**
 * 采购订单明细查询
 * 
 * @since 6.0
 * @version 2011-9-7 下午01:35:05
 * @author 田锋涛
 */

public class OrderDetailsScaleStrategy extends ICRptBaseScalePrcStrategy {
  // 辅数量字段
  @Override
  protected String[] getAssistnumFields() {
    ArrayList<String> arrayListStr = new ArrayList<String>();
    // 采购订单明细查询
    arrayListStr.add(OrderItemVO.NASTNUM);// nastnum 数量
    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  // 辅计量单位
  @Override
  protected String getAstunitKey() {
    return PUPubMetaNameConst.CASTUNITID;
  }

  // 原币币种
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
    arrayListStr.add(OrderItemVO.NMNY);// 本币无税金额
    arrayListStr.add(OrderItemVO.NTAXMNY);// 本币价税合计
    arrayListStr.add(OrderItemVO.NTAX);// 本币税额
    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  // 金额字段
  @Override
  protected String[] getMnyFields() {
    ArrayList<String> arrayListStr = new ArrayList<String>();
    // 采购订单明细查询
    arrayListStr.add(OrderItemVO.NORIGMNY);// norigmny 无税金额
    arrayListStr.add(OrderItemVO.NORIGTAXMNY);// norigtaxmny 价税合计
    // arrayListStr.add(OrderItemVO.NORIGTAX);// norigtax 税额

    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  // 主数量字段
  @Override
  protected String[] getNumFields() {
    ArrayList<String> arrayListStr = new ArrayList<String>();
    arrayListStr.add(OrderItemVO.NNUM);
    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  // 价格字段
  @Override
  protected String[] getPriceFields() {
    ArrayList<String> arrayListStr = new ArrayList<String>();
    // 采购订单执行查询
    arrayListStr.add(OrderItemVO.NQTORIGPRICE);// 无税单价
    arrayListStr.add(OrderItemVO.NQTORIGTAXPRICE);// 含税单价
    arrayListStr.add(OrderItemVO.NQTORIGNETPRICE);// 无税净价
    arrayListStr.add(OrderItemVO.NQTORIGTAXNETPRC);// 含税净价
    arrayListStr.add(OrderItemVO.NORIGNETPRICE);// 主无税净价
    arrayListStr.add(OrderItemVO.NORIGTAXNETPRICE);// 主含税净价
    arrayListStr.add(OrderItemVO.NNETPRICE);// 主本币无税净价
    arrayListStr.add(OrderItemVO.NTAXNETPRICE);// 主本币含税净价
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
    this.setMnyDigits(OrderItemVO.CCURRENCYID, this.getCurrencyMnyFields());

  }
}
