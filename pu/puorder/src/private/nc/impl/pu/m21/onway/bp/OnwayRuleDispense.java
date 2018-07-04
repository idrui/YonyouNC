/**
 * $文件说明$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-19 上午10:39:24
 */
package nc.impl.pu.m21.onway.bp;

import nc.impl.pu.m21.onway.rule.OnwayChkEmpty;
import nc.impl.pu.m21.onway.rule.SendOutChkBusi;
import nc.impl.pu.m21.onway.rule.SendOutChkEmpty;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m21.entity.OrderOnwayVO;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pu.m21transtype.enumeration.OnwayStatus;
import nc.vo.pu.pub.enumeration.OnwayStatusQryEnum;
import nc.vo.pubapp.pattern.data.ValueUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>分发在途状态规则类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-8-19 上午10:39:24
 */
public class OnwayRuleDispense {

  private int getInt(Object obj) {
    return ValueUtils.getInt(obj);
  }

  /**
   * 方法功能描述：装运规则
   * <p>
   * <b>参数说明</b>
   * 
   * @param processer
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-8-19 上午10:55:33
   */
  private void getLoadRule(AroundProcesser<OrderOnwayVO> processer) {
    // 校验非空项
    processer.addBeforeRule(new OnwayChkEmpty(PoTransTypeVO.BLOADCODE,
        PoTransTypeVO.BLOADDATE, OnwayStatusQryEnum.bisload.getName()));
  }

  /**
   * 方法功能描述：发货规则
   * <p>
   * <b>参数说明</b>
   * 
   * @param processer
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-8-19 上午10:55:24
   */
  private void getSendOutRule(AroundProcesser<OrderOnwayVO> processer) {
    // 校验非空项
    processer.addBeforeRule(new SendOutChkEmpty());

    // 校验业务合法性
    processer.addBeforeRule(new SendOutChkBusi());
  }

  /**
   * 方法功能描述：分发在途状态规则
   * <p>
   * <b>参数说明</b>
   * 
   * @param status
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-9 上午11:03:08
   */
  protected AroundProcesser<OrderOnwayVO> getOnwayRule(int status) {

    AroundProcesser<OrderOnwayVO> processer =
        new AroundProcesser<OrderOnwayVO>(null);

    // 发货
    if (status == this.getInt(OnwayStatus.STATUS_SENDOUT.value())) {
      this.getSendOutRule(processer);
    }
    // 装运
    else if (status == this.getInt(OnwayStatus.STATUS_SHIP.value())) {
      this.getLoadRule(processer);
    }

    return processer;
  }
}
