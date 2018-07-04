package nc.bs.pu.m21.maintain.rule.save;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.util.PUBillCodeUtils;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ObjectUtils;

/**
 * @description
 *              采购订单单据号唯一检查
 * @scene
 *        采购订单保存修改
 * @param 无
 * @since 6.3
 * @version 2014-10-22 下午2:56:30
 * @author luojw
 */

public class BillCodeUniqueRule implements ICompareRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos, OrderVO[] originVOs) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    // 新增时检查
    if (ArrayUtils.isEmpty(originVOs)) {
      this.uniqueCheck(vos);
    }
    else {
      // 修改检查
      // 只有前后单据号不一致时才检查
      List<OrderVO> list = new ArrayList<OrderVO>();
      for (int i = 0; i < vos.length; ++i) {
        if (!ObjectUtils.equals(vos[i].getHVO().getVbillcode(), originVOs[i]
            .getHVO().getVbillcode())) {
          list.add(vos[i]);
        }
      }

      if (list.size() > 0) {
        this.uniqueCheck(list.toArray(new OrderVO[list.size()]));
      }
    }
  }

  /**
   * 单据号检查
   * 
   * @param orderVOs
   */
  private void uniqueCheck(OrderVO[] orderVOs) {
    PUBillCodeUtils.getOrderCode().checkUnique(orderVOs);
  }

}
