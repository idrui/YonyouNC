/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-27 下午02:18:30
 */
package nc.bs.pu.m21.writeback.so;

import java.util.Map;
import java.util.Set;

import nc.bs.ml.NCLangResOnserver;
import nc.bs.pu.m21.plugin.OrderPluginPoint;
import nc.bs.pu.m21.writeback.so.rule.CoopStatusCheck;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>生成协同销售订单回写采购订单BP类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-27 下午02:18:30
 */
public class OrderUpdateCoopFor30BP {

  /**
   * 方法功能描述：生成协同销售订单回写采购订单，设置表头已协同生成销售订单和对方订单号
   * <p>
   * <b>参数说明</b>
   * 
   * @param flag
   * @param wbMap
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-27 下午02:19:06
   */
  public void updateCoopFlag(boolean flag, Map<String, String> wbMap) {
    if (null == wbMap || 0 == wbMap.size()) {
      return;
    }

    OrderHeaderVO[] vos = this.getOrderHeaderVOs(wbMap);
    if (ArrayUtils.isEmpty(vos) || vos.length < wbMap.size()) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4004030_0", "04004030-0384")/* 查到的订单数量少于要回写的订单数量，请检查！ */);
    }
    AroundProcesser<OrderHeaderVO> processer =
        new AroundProcesser<OrderHeaderVO>(OrderPluginPoint.WRITEBACK_COOP30);
    this.addRule(processer, flag);
    processer.before(vos);
    if (flag) {
      this.updateCoopFlagWhenAdd(vos, wbMap);
    }
    else {
      this.updateCoopFlagWhenDel(vos);
    }
    VOUpdate<OrderHeaderVO> udpate = new VOUpdate<OrderHeaderVO>();
    udpate.update(vos, new String[] {
      OrderHeaderVO.VCOOPORDERCODE, OrderHeaderVO.BCOOPTOSO
    });

  }

  private void addRule(AroundProcesser<OrderHeaderVO> processer, boolean flag) {
    processer.addBeforeRule(new CoopStatusCheck(flag));
  }

  /**
   * 方法功能描述：检查时间戳
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderVOs
   * @param wbMap
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-27 下午02:36:23
   */
  // private void checkTs(OrderVO[] orderVOs, Map<String, Object[]> wbMap) {
  // new BillConcurrentTool().lockBill(orderVOs);
  // for (OrderVO orderVO : orderVOs) {
  // boolean flag = true;
  // String pk = orderVO.getHVO().getPk_order();
  // Object[] wbPara = wbMap.get(pk);
  // UFDateTime orderTs = orderVO.getHVO().getTs();
  // UFDateTime wbParaTs = (UFDateTime) wbPara[0];
  // if (null == wbParaTs) {
  // flag = false;
  // }
  // else if (ObjectUtils.equals(orderTs, wbParaTs)) {
  // flag = false;
  // }
  //
  // if (!flag) {
  //
  // ExceptionUtils.wrappBusinessException("出现并发，请重新查询");
  // }
  // }
  // }

  private OrderHeaderVO[] getOrderHeaderVOs(Map<String, String> wbMap) {
    Set<String> pkSet = wbMap.keySet();
    String[] pks = pkSet.toArray(new String[pkSet.size()]);
    VOQuery<OrderHeaderVO> query =
        new VOQuery<OrderHeaderVO>(OrderHeaderVO.class);
    return query.query(pks);
  }

  /**
   * 方法功能描述：查询订单
   * <p>
   * <b>参数说明</b>
   * 
   * @param wbMap
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-27 下午02:50:34
   */
  // private OrderVO[] getOrderVOs(Map<String, String> wbMap) {
  // Set<String> pkSet = wbMap.keySet();
  // String[] pks = pkSet.toArray(new String[pkSet.size()]);
  //
  // BillQuery<OrderVO> query = new BillQuery<OrderVO>(OrderVO.class);
  // return query.query(pks);
  // }

  private void updateCoopFlagWhenAdd(OrderHeaderVO[] headVOs,
      Map<String, String> wbMap) {
    for (OrderHeaderVO headVO : headVOs) {
      String vcoopordercode = wbMap.get(headVO.getPk_order());
      headVO.setVcoopordercode(vcoopordercode);
      headVO.setBcooptoso(UFBoolean.TRUE);
    }
  }

  /**
   * 方法功能描述：新增更新
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderVOs
   * @param wbMap
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-27 下午03:39:07
   */
  // private void updateCoopFlagWhenAdd(OrderVO[] orderVOs,
  // Map<String, String> wbMap) {
  // for (OrderVO orderVO : orderVOs) {
  // OrderHeaderVO headerVO = orderVO.getHVO();
  // String vcoopordercode = wbMap.get(headerVO.getPk_order());
  // headerVO.setVcoopordercode(vcoopordercode);
  // headerVO.setBcooptoso(UFBoolean.TRUE);
  // }
  // }

  private void updateCoopFlagWhenDel(OrderHeaderVO[] headerVOs) {
    for (OrderHeaderVO headerVO : headerVOs) {
      headerVO.setVcoopordercode(null);
      headerVO.setBcooptoso(UFBoolean.FALSE);
    }
  }

  /**
   * 方法功能描述：删除更新
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderVOs
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-27 下午03:39:24
   */
  // private void updateCoopFlagWhenDel(OrderVO[] orderVOs) {
  // for (OrderVO orderVO : orderVOs) {
  // OrderHeaderVO headerVO = orderVO.getHVO();
  // headerVO.setVcoopordercode(null);
  // headerVO.setBcooptoso(UFBoolean.FALSE);
  // }
  // }
}
