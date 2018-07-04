/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-13 下午02:37:23
 */
package nc.bs.pu.m21.state.rule;

import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              采购订单最终关闭时记录最终关闭日期，整单打开时清空最终关闭日期
 * @scene
 *        采购订单最终打开、关闭
 * @param UFBoolean value 如果为true，则记录关闭日期，否则记录打开日期
 * @since 6.3
 * @version 2014-10-21 上午9:04:37
 * @author luojw
 */
public class UpdateCloseDateRule implements IRule<OrderVO> {

  private UFBoolean value;

  public UpdateCloseDateRule(UFBoolean value) {
    this.value = value;
  }

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

    OrderHeaderVO[] headerVOs = new OrderHeaderVO[vos.length];
    // 最终关闭时记录最终关闭日期
    if (this.value.booleanValue()) {
      UFDate closedate = AppContext.getInstance().getBusiDate();
      for (int i = 0; i < vos.length; ++i) {
        OrderHeaderVO headerVO = vos[i].getHVO();
        headerVO.setDclosedate(closedate);
        headerVOs[i] = headerVO;
      }
    }
    else {// 整单打开时清空最终关闭日期
      for (int i = 0; i < vos.length; ++i) {
        OrderHeaderVO headerVO = vos[i].getHVO();
        headerVO.setDclosedate(null);
        headerVOs[i] = headerVO;
      }
    }

    VOUpdate<OrderHeaderVO> update = new VOUpdate<OrderHeaderVO>();
    update.update(headerVOs, new String[] {
      OrderHeaderVO.DCLOSEDATE
    });
  }

}
