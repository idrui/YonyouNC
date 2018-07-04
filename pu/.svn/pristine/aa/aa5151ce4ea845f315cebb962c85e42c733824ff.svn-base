package nc.bs.pu.m21.writeback.dm;

import java.util.HashSet;
import java.util.Set;

import nc.bs.pu.m21.plugin.OrderPluginPoint;
import nc.bs.pu.m21.writeback.dm.rule.AccDevNumCalcRule;
import nc.bs.pu.m21.writeback.dm.rule.AccDevNumChkRule;
import nc.bs.pu.m21.writeback.dm.rule.AutoRPTransCloseRule;
import nc.bs.pu.m21.writeback.dm.rule.AutoTransCloseRule;
import nc.bs.pu.m21.writeback.dm.rule.OrderStatusChkRule;
import nc.bs.pu.m21.writeback.dm.rule.ReceivePlanDevCalRule;
import nc.bs.pu.m21.writeback.dm.rule.ReceivePlanDevChkRule;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.pu.m21.dm.m4804.IOrderWriteBackParaFor4804;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pu.m21.pub.OrderVOUtil;

import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.0
 * @version 2010-12-14 下午05:10:22
 * @author wuxla
 */

public class OrderWriteBackFor4804BP {

  public void writeBackFor4804(IOrderWriteBackParaFor4804[] wbVos) {

    if (!SysInitGroupQuery.isDMEnabled()) {
      return;
    }

    if (ArrayUtils.isEmpty(wbVos)) {
      return;
    }

    String[] bids = OrderVOUtil.getInsance().getBIDs(wbVos);
    OrderViewVO[] views =
        new ViewQuery<OrderViewVO>(OrderViewVO.class).query(bids);

    if (ArrayUtils.isEmpty(views)) {
      return;
    }

    AroundProcesser<OrderViewVO> processer =
        new AroundProcesser<OrderViewVO>(OrderPluginPoint.WRITEBACK_4804);
    this.addRule(processer, wbVos);
    processer.before(views);

    String[] wbNames = new String[] {
      OrderItemVO.NACCUMDEVNUM, OrderItemVO.BTRANSCLOSED
    };
    ViewUpdate<OrderViewVO> bo = new ViewUpdate<OrderViewVO>();
    views = bo.update(views, OrderItemVO.class, wbNames);
    processer.after(views);

    this.writeBackRPFor4804(wbVos);

  }

  private void addRPRule(AroundProcesser<OrderReceivePlanVO> processer,
      IOrderWriteBackParaFor4804[] vos) {
    processer.addBeforeRule(new ReceivePlanDevCalRule(vos));
    processer.addBeforeRule(new ReceivePlanDevChkRule());
    processer.addBeforeRule(new AutoRPTransCloseRule());
  }

  private void addRule(AroundProcesser<OrderViewVO> processer,
      IOrderWriteBackParaFor4804[] vos) {
    processer.addBeforeRule(new OrderStatusChkRule());
    // wuxla 2012-2-27 V61
    // 高扬和需求确认，不进行该检查
    // processer.addBeforeRule(new TransCloseChkRule(vos));
    processer.addBeforeRule(new AccDevNumCalcRule(vos));
    processer.addBeforeRule(new AccDevNumChkRule());
    processer.addBeforeRule(new AutoTransCloseRule());
  }

  private OrderReceivePlanVO[] getRPVOs(IOrderWriteBackParaFor4804[] wbVos) {
    if (ArrayUtils.isEmpty(wbVos)) {
      return null;
    }
    Set<String> set = new HashSet<String>();
    for (IOrderWriteBackParaFor4804 vo : wbVos) {
      if (vo != null && vo.getBBID() != null) {
        set.add(vo.getBBID());
      }
    }

    if (set.isEmpty()) {
      return null;
    }

    String[] pkOrderBBs = set.toArray(new String[0]);
    VOQuery<OrderReceivePlanVO> query =
        new VOQuery<OrderReceivePlanVO>(OrderReceivePlanVO.class);
    OrderReceivePlanVO[] rpVOs = query.query(pkOrderBBs);
    return rpVOs;
  }

  private void writeBackRPFor4804(IOrderWriteBackParaFor4804[] wbVos) {
    OrderReceivePlanVO[] rpVOs = this.getRPVOs(wbVos);
    if (!ArrayUtils.isEmpty(rpVOs)) {
      AroundProcesser<OrderReceivePlanVO> rpPrecosser =
          new AroundProcesser<OrderReceivePlanVO>(
              OrderPluginPoint.RECEIVE_PLAN_WRITEBACK_4804);
      this.addRPRule(rpPrecosser, wbVos);

      rpPrecosser.before(rpVOs);
      String[] rpNames = new String[] {
        OrderReceivePlanVO.NACCUMDEVNUM, OrderReceivePlanVO.BTRANSCLOSED
      };
      VOUpdate<OrderReceivePlanVO> rpUpdate =
          new VOUpdate<OrderReceivePlanVO>();
      rpUpdate.update(rpVOs, rpNames);
      rpPrecosser.after(rpVOs);
    }
  }
}
