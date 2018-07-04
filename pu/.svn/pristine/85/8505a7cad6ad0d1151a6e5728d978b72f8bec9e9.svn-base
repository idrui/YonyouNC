package nc.vo.pu.m21.vochange;

import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.rule.RelationCalculate;
import nc.vo.pu.m21.rule.RowNoRule;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.scmpub.res.billtype.ECBillType;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * 补货申请推式保存采购订单VO交换后期处理类。
 * 
 * @since 6.0
 * @version 2012-11-12 下午06:26:40
 * @author lixyp
 */
public class ChangeVOAdjustEC38To21 extends AbstractOrderChangeVOAdjust {

  private static final String EC38TYPEID = "1001ZF10000000008G0U";

  /**
   * 由于EC单据类型的ID和CODE不一致，在这里转换一下。
   * 
   * @param vos 订单vo数组
   */
  private void setSourceBilltype(OrderVO[] vos) {
    for (OrderVO vo : vos) {
      for (OrderItemVO itemVo : vo.getBVO()) {
        if (ECBillType.EC38.getCode().equals(itemVo.getCsourcetypecode())) {
          itemVo.setCsourcetypecode(ChangeVOAdjustEC38To21.EC38TYPEID);
        }
        if (itemVo.getCfirsttypecode() != null
            && ECBillType.EC38.getCode().equals(itemVo.getCfirsttypecode())) {
          itemVo.setCfirsttypecode(ChangeVOAdjustEC38To21.EC38TYPEID);
        }
      }
    }
  }

  @Override
  protected String[] getNumStrs() {
    return new String[]{"main_amount", "main_amount", "main_amount"};
  }

  @Override
  protected OrderVO[] fillInformation(OrderVO[] vos,
      AggregatedValueObject[] srcVOs) {
    // 设置行号
    new RowNoRule().process(vos);
    // // 设置交易类型 TODO 平台开发接口之后去掉。
    // new TrantypeValueRule(vos).setTrantypeValue();
    // 补充流程
    PfServiceScmUtil.setBusiType(vos, POBillType.Order.getCode());

    // 父类同名方法中包含从主数量发起的联动计算，根据tft的建议，在这之后再根据含税单价发起联动计算。
    OrderVO[] result = super.fillInformation(vos, srcVOs);
    // 根据含税单价联动计算。
    RelationCalculate cal = new RelationCalculate();
    for (OrderVO vo : result) {
      cal.calculate(vo, OrderItemVO.NQTORIGTAXPRICE);
    }
    // 转换来源单据类型。
    this.setSourceBilltype(vos);
    return result;
  }
}
