/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-29 上午09:30:59
 */
package nc.bs.pu.m21.maintain.rule.save;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.util.PUBillCodeUtils;
import nc.vo.pub.VOStatus;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              采购订单单据号(订单号)补充规则
 * @scene
 *        采购订单保存修改
 * @param 无
 * @since 6.3
 * @version 2014-10-22 下午2:43:29
 * @author luojw
 */
public class OrderCodeProceRule implements ICompareRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos, OrderVO[] orgVos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    // 新增保存
    if (VOStatus.NEW == vos[0].getHVO().getStatus()
        || null == vos[0].getHVO().getPk_order()) {
      this.handleInsertVO(vos);
    }
    else {
      // 修改保存
      this.handleUpdateVO(vos, orgVos);
    }

  }

  private void handleInsertVO(OrderVO[] vos) {
    PUBillCodeUtils.getOrderCode().createBillCode(vos);
  }

  private void handleUpdateVO(OrderVO[] vos, OrderVO[] orgVos) {
    PUBillCodeUtils.getOrderCode().upadteBillCode(vos, orgVos);
  }

}
