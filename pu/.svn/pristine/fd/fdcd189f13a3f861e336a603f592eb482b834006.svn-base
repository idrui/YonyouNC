package nc.vo.pu.report.scale.m23;

import java.util.ArrayList;

import nc.adapter.ic.icreport.base.ICRptBaseScalePrcStrategy;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.report.scale.PUPubMetaNameConst;

/**
 * 采购订单退货明细
 * 
 * @since 6.0
 * @version 2011-9-7 下午01:49:24
 * @author 田锋涛
 */

public class ReturnDetailScaleStrategy extends ICRptBaseScalePrcStrategy {
  // 辅数量字段
  @Override
  protected String[] getAssistnumFields() {
    ArrayList<String> arrayListStr = new ArrayList<String>();
    // 采购订单退货明细查询
    arrayListStr.add(PUPubMetaNameConst.NASTNUM);// NASTNUM 退货数量
    arrayListStr.add(PUPubMetaNameConst.RETURNENUM);// RETURNENUM 退回数量
    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  // 辅计量单位
  @Override
  protected String getAstunitKey() {
    return PUPubMetaNameConst.CASTUNITID;
  }

  // 币种
  @Override
  protected String getCurrencyKey() {
    return PUPubMetaNameConst.CCURRENCYID;
  }

  // 金额字段
  @Override
  protected String[] getMnyFields() {
    ArrayList<String> arrayListStr = new ArrayList<String>();
    arrayListStr.add(OrderItemVO.NTAXMNY);// 退货金额
    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  // 主数量字段
  @Override
  protected String[] getNumFields() {
    ArrayList<String> arrayListStr = new ArrayList<String>();
    arrayListStr.add(PUPubMetaNameConst.NACCUMREPLNUM);// NACCUMREPLNUM 补货主数量
    arrayListStr.add(OrderItemVO.NNUM);// 退货主数量
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
