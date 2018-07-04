/**
 * $文件说明$
 * 
 * @author lixyp
 * @version 6.1
 * @see
 * @since 6.1
 * @time 2011-12-19 下午04:15:22
 */
package nc.pubimpl.pu.m21.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.rule.OrganizationDefaultValue;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pu.pub.util.IKeyValue;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>设置组织默认值：结算财务组织、应付财务组织、利润中心
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.1
 * @since 6.1
 * @author lixyp
 * @time 2011-12-19 下午04:20:25
 */
public class OrganizationValueRule implements IRule<OrderVO> {

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
    this.setOrganizationValues(vos);
  }

  /**
   * 方法功能描述：设置组织默认值
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderVOs 订单vo数组
   *          <p>
   * @since 6.1
   * @author lixyp
   * @time 2011-12-19 下午04:26:21
   */
  private void setOrganizationValues(OrderVO[] orderVOs) {
    for (OrderVO orderVO : orderVOs) {
      if (null == orderVO || null == orderVO.getHVO()) {
        continue;
      }
      IKeyValue keyValue = new BillHelper<OrderVO>(orderVO);
      int[] rows = new int[keyValue.getItemCount()];
      for (int i = 0; i < rows.length; ++i) {
        rows[i] = i;
      }
      OrganizationDefaultValue value = new OrganizationDefaultValue(keyValue);
      value.setDefaultOrganizationValue(rows);
    }
  }
}
