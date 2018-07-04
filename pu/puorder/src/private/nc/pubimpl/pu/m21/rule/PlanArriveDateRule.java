/**
 * $文件说明$
 * 
 * @author lixyp
 * @version 6.1
 * @see
 * @since 6.1
 * @time 2011-12-22 下午02:49:28
 */
package nc.pubimpl.pu.m21.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.rule.PlanArriveDate;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pu.pub.util.IKeyValue;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>设置计划到货日期为截止型
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.1
 * @since 6.1
 * @author lixyp
 * @time 2011-12-22 下午02:49:28
 */
public class PlanArriveDateRule implements IRule<OrderVO> {

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

    this.setPlanArriveDate(vos);
  }

  /**
   * 方法功能描述：设置计划到货日期
   * 根据物料计划页签上的采购提前期设置计划到货日期
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderVOs
   *          <p>
   * @since 6.1
   * @author lixyp
   * @time 2011-12-22 下午03:10:16
   */
  private void setPlanArriveDate(OrderVO[] orderVOs) {
    for (OrderVO orderVO : orderVOs) {
      if (null == orderVO) {
        continue;
      }
      IKeyValue keyValue = new BillHelper<OrderVO>(orderVO);
      PlanArriveDate planArriveDate = new PlanArriveDate(keyValue);
      planArriveDate.setPlanArriveDate(0, keyValue.getItemCount() - 1);
    }
  }
}
