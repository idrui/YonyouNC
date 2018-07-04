package nc.vo.pu.m21.rule;

import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pubapp.scale.VOScaleProcessor;

/**
 * 到货计划精度
 * 
 * @since 6.0
 * @version 2011-4-15 下午06:20:10
 * @author wuxla
 */

public class ReceivePlanScaleRule {
  /**
   * @param scale
   */
  public void setScale(VOScaleProcessor scale) {
    // 换算率
    // String[] changeRates = new String[] {
    // OrderReceivePlanVO.VCHANGERATE, OrderReceivePlanVO.VQTUNITRATE
    // };

    // 报价单位数量
    String[] quoteNumkeys = new String[] {
      OrderReceivePlanVO.NQTUNITNUM
    };
    // 业务单位数量
    String[] assistNumkeys = new String[] {
      OrderReceivePlanVO.NASTNUM
    };
    // 主数量
    String[] numkeys =
        new String[] {
          OrderReceivePlanVO.NACCUMARRVNUM, OrderReceivePlanVO.NACCUMDEVNUM,
          OrderReceivePlanVO.NACCUMSTORENUM, OrderReceivePlanVO.NACCUMWASTNUM,
          OrderReceivePlanVO.NBACKARRVNUM, OrderReceivePlanVO.NBACKSTORENUM,
          OrderReceivePlanVO.NNUM, OrderReceivePlanVO.NACCUMRECEIVENUM
        };

    // 换算率精度
    // scale.setHslCtlInfo(changeRates);
    // 报价单位数量精度
    scale.setNumCtlInfo(quoteNumkeys, OrderReceivePlanVO.CQTUNITID);
    // 业务单位数量精度
    scale.setNumCtlInfo(assistNumkeys, OrderItemVO.CASTUNITID);
    // 主单位数量精度
    scale.setNumCtlInfo(numkeys, OrderItemVO.CUNITID);

    // 进行计算
    scale.process();
  }
}
