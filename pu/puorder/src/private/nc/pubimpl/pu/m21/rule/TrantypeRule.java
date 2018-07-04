/**
 * $文件说明$
 * 
 * @author lixyp
 * @version 6.1
 * @see
 * @since 6.1
 * @time 2011-12-22 下午03:37:26
 */
package nc.pubimpl.pu.m21.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>根据物料对应订单类型设置－〉上下游接口关系定义设置来匹配对应的订单类型作为默认值
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.1
 * @since 6.1
 * @author lixyp
 * @time 2011-12-22 下午03:37:26
 */
public class TrantypeRule implements IRule<OrderVO> {

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(OrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    // this.setTrantype(vos);
    // 补充流程就可以了
    PfServiceScmUtil.setBusiType(vos, POBillType.Order.getCode());
  }

  /**
   * 方法功能描述：根据物料对应订单类型设置－〉上下游接口关系定义设置来匹配对应的订单类型作为默认值（包括直运销售采购）
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderVOs
   *          <p>
   * @since 6.1
   * @author lixyp
   * @time 2011-12-22 下午04:37:18
   */
  // private void setTrantype(OrderVO[] orderVOs) {
  // TrantypeValueRule trantypeValueRule = new TrantypeValueRule(orderVOs);
  // trantypeValueRule.setTrantypeValue();
  // }
}
