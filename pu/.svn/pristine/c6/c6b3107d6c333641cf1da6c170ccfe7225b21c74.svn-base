package nc.bs.pu.m21.writeback.ic;

import nc.bs.pu.m21.writeback.ic.rule.OrderViewLockRule;
import nc.bs.pu.m21.writeback.ic.rule.SupplyReserveNumCalRule;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.pubitf.pu.m21.ic.reserve.IOrderWriteBackPara;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pu.m21.pub.OrderVOUtil;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>库存预留回写采购订单累计预留数量的BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-6-17 下午07:45:43
 */
public class OrderWriteBackForReserveBP {

  public void writeback(IOrderWriteBackPara[] paras) {
    if ((paras == null) || (paras.length == 0)) {
      return;
    }
    String[] bids = OrderVOUtil.getInsance().getBIDs(paras);
    // 查询订单
    OrderViewVO[] views =
        new ViewQuery<OrderViewVO>(OrderViewVO.class).query(bids);
    if (ArrayUtils.isEmpty(views)) {
      return;
    }

    AroundProcesser<OrderViewVO> processer =
        new AroundProcesser<OrderViewVO>(null);
    // 增加保存前规则：加锁
    processer.addBeforeFinalRule(new OrderViewLockRule());
    // 增加保存前规则：计算累计预留数量
    processer.addBeforeRule(new SupplyReserveNumCalRule(paras));

    processer.before(views);
    ViewUpdate<OrderViewVO> update = new ViewUpdate<OrderViewVO>();
    String[] fields = new String[] {
      OrderItemVO.NSUPRSNUM
    };
    update.update(views, OrderItemVO.class, fields);
    processer.after(views);
  }
}
