package nc.vo.pu.report.scale.m23;

import java.util.ArrayList;

import nc.adapter.ic.icreport.base.ICRptBaseScalePrcStrategy;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.report.scale.PUPubMetaNameConst;

/**
 * 到货单查询
 * 
 * @since 6.0
 * @version 2011-9-7 下午01:37:52
 * @author 田锋涛
 */

public class ArriveQueryScaleStrategy extends ICRptBaseScalePrcStrategy {

  // 辅数量字段
  @Override
  protected String[] getAssistnumFields() {
    ArrayList<String> arrayListStr = new ArrayList<String>();
    arrayListStr.add(ArriveItemVO.NWASTASTNUM);// 途耗数量
    arrayListStr.add(ArriveItemVO.NPRESENTASTNUM);// 赠品数量
    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  // 辅计量单位
  @Override
  protected String getAstunitKey() {
    return PUPubMetaNameConst.CASTUNITID;
  }

  // 主数量字段
  @Override
  protected String[] getNumFields() {
    ArrayList<String> arrayListStr = new ArrayList<String>();
    arrayListStr.add(ArriveItemVO.NNUM);
    arrayListStr.add(ArriveItemVO.NWASTNUM);// 途耗主数量
    arrayListStr.add(ArriveItemVO.NPRESENTNUM);// 赠品主数量
    arrayListStr.add(ArriveItemVO.NELIGNUM);// 合格主数量
    arrayListStr.add(ArriveItemVO.NNOTELIGNUM);// 不合格主数量
    arrayListStr.add(ArriveItemVO.NACCUMSTORENUM);// 累计入库主数量
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
