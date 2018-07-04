package nc.vo.pu.report.scale.m23;

import java.util.ArrayList;

import nc.adapter.ic.icreport.base.ICRptBaseScalePrcStrategy;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.report.scale.PUPubMetaNameConst;

/**
 * 采购到货-日到货情况查询精度处理类
 * 
 * @since 6.3
 * @version 2012-8-21 下午07:18:38
 * @author fanly3
 */
public class DayArriveScaleStrategy extends ICRptBaseScalePrcStrategy {
  @Override
  protected String[] getAssistnumFields() {
    ArrayList<String> arrayListStr = new ArrayList<String>();
    arrayListStr.add(OrderItemVO.NASTNUM);// 数量
    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  @Override
  protected String getAstunitKey() {
    return PUPubMetaNameConst.CASTUNITID;// 辅单位
  }

  @Override
  protected String getUnitKey() {
    return PUPubMetaNameConst.CUNITID;// 主单位
  }

  @Override
  protected String[] getNumFields() {
    ArrayList<String> arrayListStr = new ArrayList<String>();
    arrayListStr.add(OrderItemVO.NNUM);// 主数量
    arrayListStr.add(OrderItemVO.NACCUMARRVNUM);// 累计到货主数量
    arrayListStr.add("nnotaccumarrvnum");// 未收货主数量
    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }
}
