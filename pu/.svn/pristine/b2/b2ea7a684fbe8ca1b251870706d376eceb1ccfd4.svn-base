/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-3 上午09:28:59
 */
package nc.vo.pu.m21.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>数据合法性校验，只用于服务器端检查，因为ScaleUtils使用的是服务器端
 * <li>校验单价，金额精度
 * <li>校验单价、数量、金额关系
 * <li>校验主数量、数量、换算率关系
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-3 上午09:28:59
 */
public class DataValidateRule implements IRule<OrderVO> {
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

    for (OrderVO vo : vos) {
      if (null == vo) {
        continue;
      }
      OrderItemVO[] itemVOs = vo.getBVO();
      if (ArrayUtils.isEmpty(itemVOs)) {
        continue;
      }
      this.checkItemValidate(itemVOs, vo);
    }
  }

  /**
   * 方法功能描述：数据合法性检查
   * <p>
   * <b>参数说明</b>
   * 
   * @param itemVO
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-3 上午10:33:43
   */
  private void checkItemValidate(OrderItemVO[] itemVOs, OrderVO vo) {
    PrecisionValidate precisionValidate = new PrecisionValidate();
    RelationValidate relationValidate = new RelationValidate(vo);
    for (OrderItemVO itemVO : itemVOs) {
      precisionValidate.precisionValidate(itemVO);
      relationValidate.relationValidate(itemVO);
    }
  }
}
