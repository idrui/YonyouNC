/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-14 下午04:12:57
 */
package nc.bs.pu.m21.writeback.pu;

import java.util.HashSet;
import java.util.Set;

import nc.bs.pu.m21.writeback.ic.rule.AutoCloseProcRule;
import nc.bs.pu.m21.writeback.pu.rule.AccStoreNumCalcFor4TRule;
import nc.bs.pu.m21.writeback.pu.rule.AccStoreNumChkFor4TRule;
import nc.bs.pu.m21.writeback.pu.rule.RPStoreCalFor4TRule;
import nc.bs.pu.m21.writeback.pu.rule.RPStoreChkFor4TRule;
import nc.bs.pu.m21.writeback.pu.rule.StoreCloseChkRule;
import nc.bs.pu.m21.writeback.pu.rule.StoreOpenFor4TRule;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.itf.pu.reference.ic.ATPServices;
import nc.pubitf.pu.m21.ic.m45.IOrderWriteBackPara;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pu.m21.pub.OrderVOUtil;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>为期初暂估单提供的回写服务
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-14 下午04:12:57
 */
public class OrderWriteBackFor4TBP {

  /**
   * 方法功能描述：期初暂估单回写
   * <p>
   * <b>参数说明</b>
   * 
   * @param wbVos <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-14 下午04:59:42
   */
  public void writeBackFor4T(IOrderWriteBackPara[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    String[] bids = OrderVOUtil.getInsance().getBIDs(vos);
    OrderViewVO[] views =
        new ViewQuery<OrderViewVO>(OrderViewVO.class).query(bids);

    if (ArrayUtils.isEmpty(views)) {
      return;
    }

    // 拷贝
    OrderViewVO[] orgViews = new OrderViewVO[views.length];
    for (int i = 0; i < views.length; ++i) {
      orgViews[i] = (OrderViewVO) views[i].clone();
    }

    // 到货计划(包括回写和累计入库数量检查规则)
    OrderReceivePlanVO[] rpVOs = this.getRPVOs(vos);
    if (!ArrayUtils.isEmpty(rpVOs)) {
      AroundProcesser<OrderReceivePlanVO> rpPrecosser =
          new AroundProcesser<OrderReceivePlanVO>(null);
      this.addRPRule(rpPrecosser, vos);

      rpPrecosser.before(rpVOs);
      String[] rpNames = new String[] {
        OrderReceivePlanVO.NACCUMSTORENUM, OrderReceivePlanVO.NBACKSTORENUM
      };
      VOUpdate<OrderReceivePlanVO> rpUpdate =
          new VOUpdate<OrderReceivePlanVO>();
      rpUpdate.update(rpVOs, rpNames);
      rpPrecosser.after(rpVOs);
    }

    CompareAroundProcesser<OrderViewVO> processer =
        new CompareAroundProcesser<OrderViewVO>(null);
    this.addRule(processer, vos, orgViews);
    // 以后改成在4T保存时更新
    this.atpBeforeRule(orgViews);
    processer.before(views, orgViews);

    String[] wbNames = new String[] {
      OrderItemVO.NACCUMSTORENUM, OrderItemVO.NBACKSTORENUM
    };
    ViewUpdate<OrderViewVO> bo = new ViewUpdate<OrderViewVO>();
    views = bo.update(views, OrderItemVO.class, wbNames);
    processer.after(views, orgViews);

    // 以后改成在4T保存时更新
    this.atpAfterRule(views);
  }

  private void addRPRule(AroundProcesser<OrderReceivePlanVO> processer,
      IOrderWriteBackPara[] vos) {
    processer.addBeforeRule(new RPStoreCalFor4TRule(vos));
    processer.addAfterRule(new RPStoreChkFor4TRule(vos));
  }

  private void addRule(CompareAroundProcesser<OrderViewVO> processer,
      IOrderWriteBackPara[] vos, OrderViewVO[] orgViews) {
    processer.addBeforeRule(new AccStoreNumCalcFor4TRule(vos));// 累计入库数量计算
    processer.addBeforeRule(new StoreCloseChkRule(orgViews)); // 关闭检查
    processer.addAfterRule(new AccStoreNumChkFor4TRule(vos));// 累计入库数量检查
    processer.addAfterRule(new AutoCloseProcRule(vos));// 自动关闭(打开)处理
    processer.addAfterRule(new StoreOpenFor4TRule());
  }

  private void atpAfterRule(OrderViewVO[] views) {
    OrderVO[] vos = OrderViewVO.getOrderVO(views);
    ATPServices.modifyATPAfter(POBillType.Order.getCode(), vos);
  }

  private void atpBeforeRule(OrderViewVO[] orgViews) {
    OrderVO[] vos = OrderViewVO.getOrderVO(orgViews);
    ATPServices.modifyATPBefore(POBillType.Order.getCode(), vos);
  }

  private OrderReceivePlanVO[] getRPVOs(IOrderWriteBackPara[] wbVos) {
    if (ArrayUtils.isEmpty(wbVos)) {
      return null;
    }
    Set<String> set = new HashSet<String>();
    for (IOrderWriteBackPara vo : wbVos) {
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
}
