package nc.bs.pu.m21.writeback.et;

import nc.bs.pu.m21.plugin.OrderPluginPoint;
import nc.bs.pu.m21.writeback.et.rule.AccPickupNumCalcRule;
import nc.bs.pu.m21.writeback.et.rule.StoreCloseChkRule;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.pubitf.pu.m21.et.IOrderWriteBackParaForET;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pu.m21.pub.OrderVOUtil;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>为出口合同提供的回写服务
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.31
 * @since 6.0
 * @author zhangyhh
 * @time 2013-8-1 下午03:48:50
 */
public class OrderWriteBackForETBP {

  public void writeBackForET(IOrderWriteBackParaForET[] Vos) {
    if (ArrayUtils.isEmpty(Vos)) {
      return;
    }
    String[] bids = OrderVOUtil.getInsance().getBIDs(Vos);
    OrderViewVO[] views =
        new ViewQuery<OrderViewVO>(OrderViewVO.class).query(bids);

    if (ArrayUtils.isEmpty(views)) {
      return;
    }

    AroundProcesser<OrderViewVO> processer =
        new AroundProcesser<OrderViewVO>(OrderPluginPoint.WRITEBACK_ET);
    this.addRule(processer, Vos);

    processer.before(views);

    String[] wbNames = new String[] {
      OrderItemVO.NACCUMPICKUPNUM
    };
    ViewUpdate<OrderViewVO> bo = new ViewUpdate<OrderViewVO>();
    views = bo.update(views, OrderItemVO.class, wbNames);

    processer.after(views);
  }

  private void addRule(AroundProcesser<OrderViewVO> processer,
      IOrderWriteBackParaForET[] vos) {
    processer.addBeforeRule(new AccPickupNumCalcRule(vos));// 累计拣货数量计算
    processer.addBeforeRule(new StoreCloseChkRule());// 入库关闭检查
  }
}
