package nc.vo.pu.report.scale.m27;

import java.util.ArrayList;

import nc.adapter.ic.icreport.base.ICRptBaseScalePrcStrategy;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.report.scale.PUPubMetaNameConst;

/**
 * 结算单查询
 * 
 * @since 6.0
 * @version 2011-9-7 下午01:42:21
 * @author 田锋涛
 */

public class SettleBillQueryScaleStrategy extends ICRptBaseScalePrcStrategy {

  // 币种
  @Override
  protected String getCurrencyKey() {
    return PUPubMetaNameConst.CCURRENCYID;
  }

  // 金额字段
  @Override
  protected String[] getMnyFields() {
    ArrayList<String> arrayListStr = new ArrayList<String>();
    // 采购结算单查询
    arrayListStr.add(SettleBillItemVO.NGOODSMONEY);// ngoodsmoney 货物结算金额
    arrayListStr.add(SettleBillItemVO.NREASONALWASTMNY);// 合理损耗金额
    arrayListStr.add(SettleBillItemVO.NCOSTFACTOR1);// 本币成本要素1
    arrayListStr.add(SettleBillItemVO.NCOSTFACTOR2);
    arrayListStr.add(SettleBillItemVO.NCOSTFACTOR3);
    arrayListStr.add(SettleBillItemVO.NCOSTFACTOR4);
    arrayListStr.add(SettleBillItemVO.NCOSTFACTOR5);
    arrayListStr.add(SettleBillItemVO.NCOSTFACTOR6);
    arrayListStr.add(SettleBillItemVO.NCOSTFACTOR7);
    arrayListStr.add(SettleBillItemVO.NCOSTFACTOR8);// 本币成本要素8
    arrayListStr.add(SettleBillItemVO.NDISCOUNT);
    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  // 主数量字段
  @Override
  protected String[] getNumFields() {
    ArrayList<String> arrayListStr = new ArrayList<String>();
    // 采购结算单查询
    arrayListStr.add(SettleBillItemVO.NSETTLENUM);// 结算数量
    arrayListStr.add(SettleBillItemVO.NREASONALWASTNUM);// 合理损耗数量
    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  // 价格字段
  @Override
  protected String[] getPriceFields() {
    ArrayList<String> arrayListStr = new ArrayList<String>();
    arrayListStr.add(SettleBillItemVO.NGOODSPRICE); // ngoodsprice 货物结算单价
    arrayListStr.add(SettleBillItemVO.NREASONALWASTPRICE); // nreasonalwastprice合理损耗单价
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
