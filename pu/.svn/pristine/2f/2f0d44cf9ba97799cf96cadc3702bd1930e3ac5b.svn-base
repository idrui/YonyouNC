package nc.vo.pu.report.scale.m21;

import nc.adapter.ic.icreport.base.ICRptBaseScalePrcStrategy;
import nc.vo.pu.m21.entity.OrderItemVO;

/**
 * 采购订单在途查询精度处理类。
 * 
 * @since 6.0
 * @version 2012-9-18 上午10:46:37
 * @author lixyp
 */
public class OrderOnwayScaleStrategy extends ICRptBaseScalePrcStrategy {

  // 币种
  @Override
  protected String getCurrencyKey() {
    return OrderItemVO.CCURRENCYID;
  }

  // 金额
  @Override
  protected String[] getMnyFields() {
    return new String[] {
      OrderItemVO.NTAXMNY
    };
  }

  // 主数量
  @Override
  protected String[] getNumFields() {
    return new String[] {
      OrderItemVO.NNUM
    };
  }

  // 单价
  @Override
  protected String[] getPriceFields() {
    return new String[] {
      OrderItemVO.NQTTAXNETPRICE
    };
  }

  // 主单位
  @Override
  protected String getUnitKey() {
    return OrderItemVO.CUNITID;
  }
}
