package nc.vo.pu.report.scale.m422x;

import java.util.ArrayList;

import nc.adapter.ic.icreport.base.ICRptBaseScalePrcStrategy;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.report.scale.PUPubMetaNameConst;

/**
 * 物资需求申请执行情况查询
 * 
 * @since 6.32
 * @version 2014-5-25 下午06:35:05
 * @author zhangyhh
 */

public class StoreReqDetailScaleStrategy extends ICRptBaseScalePrcStrategy {
  // 辅数量字段
  @Override
  protected String[] getAssistnumFields() {
    ArrayList<String> arrayListStr = new ArrayList<String>();

    arrayListStr.add(StoreReqAppItemVO.NASTNUM);// nastnum 数量
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
    return PUPubMetaNameConst.CCURRENCYID;
  }

  /**
   * 本币金额相关字段
   * 
   * @return
   */
  protected String[] getCurrencyMnyFields() {
    ArrayList<String> arrayListStr = new ArrayList<String>();
    arrayListStr.add(StoreReqAppItemVO.NTAXMNY);// 本币价税合计
    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  // 主数量字段
  @Override
  protected String[] getNumFields() {
    ArrayList<String> arrayListStr = new ArrayList<String>();
    arrayListStr.add(StoreReqAppItemVO.NNUM);
    arrayListStr.add(StoreReqAppItemVO.NACCCUSTORNUM);
    arrayListStr.add(StoreReqAppItemVO.NACCUMBUYREQNUM);
    arrayListStr.add(StoreReqAppItemVO.NACCUOUTNUM);
    arrayListStr.add(StoreReqAppItemVO.NACCUOUTREQNUM);
    arrayListStr.add(StoreReqAppItemVO.NNETNUM);

    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  // 价格字段
  @Override
  protected String[] getPriceFields() {
    ArrayList<String> arrayListStr = new ArrayList<String>();
    arrayListStr.add(StoreReqAppItemVO.NTAXPRICE);// 主本币含税单价
    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  // 主单位
  @Override
  protected String getUnitKey() {
    return PUPubMetaNameConst.CUNITID;
  }

  @Override
  protected void init() {
    // 对本币金额等的处理
    this.setMnyDigits(StoreReqAppItemVO.CCURRENCYID,
        this.getCurrencyMnyFields());
  }
}
