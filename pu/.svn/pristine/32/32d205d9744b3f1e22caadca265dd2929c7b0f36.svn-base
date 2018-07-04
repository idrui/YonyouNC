package nc.vo.pu.report.scale.m21;

import java.util.ArrayList;
import java.util.List;

import nc.adapter.ic.icreport.base.ICRptBaseScalePrcStrategy;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.report.view.order.ReceivePlanRptViewMeta;

/**
 * 到货计划查询
 * 
 * @since 6.0
 * @version 2011-9-7 下午01:35:05
 * @author 田锋涛
 */

public class ReceivePlanScaleStrategy extends ICRptBaseScalePrcStrategy {

  // 辅数量字段
  @Override
  protected String[] getAssistnumFields() {
    // 数量
    List<String> arrayListStr = new ArrayList<String>();
    arrayListStr.add(OrderReceivePlanVO.NASTNUM);
    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  // 辅计量单位
  @Override
  protected String getAstunitKey() {
    return OrderReceivePlanVO.CASTUNITID;
  }

  // 主数量字段
  @Override
  protected String[] getNumFields() {
    List<String> arrayListStr = new ArrayList<String>();
    // 主数量
    arrayListStr.add(OrderReceivePlanVO.NNUM);
    // 到货主数量
    arrayListStr.add(ReceivePlanRptViewMeta.IC_NNUM);
    // 入库主数量
    arrayListStr.add(ReceivePlanRptViewMeta.ARR_NNUM);

    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  @Override
  protected String getUnitKey() {
    return OrderReceivePlanVO.CUNITID;
  }

  @Override
  protected void init() {
    //
  }
}
