/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-28 下午02:41:09
 */
package nc.pubimpl.pu.m21.action.so.m30;

import nc.bs.pu.m21.maintain.OrderSaveBP;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>销售订单生成协同采购订单动作类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-28 下午02:41:09
 */
public class CoopOrderPushSaveFor30Action {

  /**
   * 方法功能描述：销售订单生成协同采购订单
   * <p>
   * <b>参数说明</b>
   * 
   * @param srcVOs
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-28 下午02:41:27
   */
  public void coopOrderPushSaveFor30(SaleOrderVO[] srcVOs) {
    if (ArrayUtils.isEmpty(srcVOs)) {
      return;
    }

    OrderVO[] vos =
        PfServiceScmUtil.executeVOChange(SOBillType.Order.getCode(),
            PoTransTypeVO.M21_COOP, srcVOs);

    // AroundProcesser<OrderVO> processer = new AroundProcesser<OrderVO>(null);
    // this.addRule(processer, srcVOs);
    // processer.before(vos);

    OrderSaveBP bp = new OrderSaveBP(null);
    bp.save(vos, null);

    // processer.after(savedVOs);

  }

  // private void addRule(AroundProcesser<OrderVO> processer, SaleOrderVO[]
  // srcVOs) {
  // processer.addBeforeRule(new CooptosoRule());
  // processer.addBeforeRule(new CoopClearInfo());
  // processer.addBeforeRule(new CoopChangeRule(srcVOs));
  // processer.addBeforeRule(new CoopRelationCheckRule());
  // processer.addBeforeRule(new CoopOrganizationValue());
  // processer.addBeforeRule(new CoopSupplierRule());
  // processer.addBeforeRule(new HeadBreturnRule());
  // processer.addBeforeFinalRule(new CoopNumCalcRule());
  // processer.addBeforeRule(new CoopCurrencyValue());
  // processer.addBeforeRule(new AutoSwitchPUAssistUnit());
  // processer.addBeforeRule(new RowNoRule());
  // processer.addBeforeRule(new CoopDefaultValueRule());
  // }
}
