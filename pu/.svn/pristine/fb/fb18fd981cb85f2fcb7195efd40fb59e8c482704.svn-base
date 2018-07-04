/**
 * $文件说明$
 * 
 * @author zhyhang
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-18 下午10:35:42
 */
package nc.bs.pu.m21.query;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.scmpub.util.SCMDataAccessUtils;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.StatusOnWayItemVO;
import nc.vo.pu.m21.pub.SplitOrderVOUtil;
import nc.vo.pu.m21transtype.enumeration.OnwayStatus;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pu.pub.util.VOSortUtils;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.model.tool.BillComposite;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单后台查询工具类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhyhang
 * @time 2010-1-18 下午10:35:42
 */
public class OrderQueryUtil {

  /**
   * 方法功能描述：为采购入库单和到货单提供的公共查询。
   * <p>
   * <b>参数说明</b>
   * 
   * @param sql
   * @param isLazy
   * @return <p>
   * @since 6.0
   * @author zhyhang
   * @time 2010-1-19 下午08:35:23
   */
  public static OrderVO[] queryFor45_23(String sql, UFBoolean isLazy) {
    //DataAccessUtils utils = new DataAccessUtils();
  	/*
  	 * change by wandl
  	 * 转单界面限制查询数据量10000行，超过10000行会报错提示缩小查询范围！
  	 */
  	SCMDataAccessUtils utils = new SCMDataAccessUtils(10000);
    // 查询表头、表体、到货计划表id
    IRowSet rowset = utils.query(sql);
    Set<String> headids = new HashSet<String>();
    Set<String> itemids = new HashSet<String>();
    Set<String> bb1ids = new HashSet<String>();
    // 在途状态子子表pk
    Set<String> onwayPks = new HashSet<String>();
    while (rowset.next()) {
      if (!StringUtil.isEmptyWithTrim(rowset.getString(0))) {
        headids.add(rowset.getString(0));
      }
      if (!StringUtil.isEmptyWithTrim(rowset.getString(1))) {
        itemids.add(rowset.getString(1));
      }
      if (!StringUtil.isEmptyWithTrim(rowset.getString(2))) {
        bb1ids.add(rowset.getString(2));
      }
      if (!StringUtil.isEmptyWithTrim(rowset.getString(3))) {
        onwayPks.add(rowset.getString(3));
      }
    }
    if (0 == headids.size() || 0 == itemids.size()) {
      return null;
    }

    if (isLazy.booleanValue()) {
      //
    }
    OrderHeaderVO[] headers =
        new VOQuery<OrderHeaderVO>(OrderHeaderVO.class).query(headids
            .toArray(new String[headids.size()]));
    OrderItemVO[] items =
        new VOQuery<OrderItemVO>(OrderItemVO.class).query(itemids
            .toArray(new String[itemids.size()]));
    // 在途状态
    StatusOnWayItemVO[] onwayVOs =
        new VOQuery<StatusOnWayItemVO>(StatusOnWayItemVO.class).query(onwayPks
            .toArray(new String[onwayPks.size()]));

    Map<String, OrderItemVO> map = CirVOUtil.createKeyVOMap(items);
    for (StatusOnWayItemVO onwayVO : onwayVOs) {
      String pk_order_b = onwayVO.getPk_order_b();
      OrderItemVO itemVO = map.get(pk_order_b);
      if (null == itemVO) {
        continue;
      }
      UFDouble nonwaynum = onwayVO.getNonwaynum();
      if (OnwayStatus.STATUS_ARRIVE.toInt() == onwayVO.getFonwaystatus()
          .intValue()) {
        itemVO.setNcanarrivenum(MathTool.sub(nonwaynum,
            itemVO.getNaccumarrvnum()));
      }
      if (OnwayStatus.STATUS_STORE.toInt() == onwayVO.getFonwaystatus()
          .intValue()) {
        itemVO
            .setNcaninnum(MathTool.sub(nonwaynum, itemVO.getNaccumstorenum()));
      }
    }

    BillComposite<OrderVO> bc = new BillComposite<OrderVO>(OrderVO.class);
    OrderVO tempVO = new OrderVO();
    bc.append(tempVO.getMetaData().getParent(), headers);
    bc.append(tempVO.getMetaData().getVOMeta(OrderItemVO.class), items);
    OrderVO[] orderVOs = bc.composite();

    if (bb1ids.isEmpty()) {
      OrderQueryUtil.sort(orderVOs);
      return orderVOs;
    }

    // 存在到货计划
    OrderReceivePlanVO[] rpVOs =
        new VOQuery<OrderReceivePlanVO>(OrderReceivePlanVO.class).query(bb1ids
            .toArray(new String[bb1ids.size()]));

    // 提供在途状态的数量，需要根据在途数量与到货计划分行。
    // 拆单
    orderVOs =
        SplitOrderVOUtil.getInstance().splitOrderVOByRPVOs(orderVOs, rpVOs);
    OrderQueryUtil.sort(orderVOs);
    return orderVOs;
  }

  private static void sort(OrderVO[] orderVOs) {
    for (OrderVO orderVO : orderVOs) {
      OrderItemVO[] itemVOs = orderVO.getBVO();
      VOSortUtils.ascSort(itemVOs, OrderItemVO.CROWNO);
      orderVO.setBVO(itemVOs);
    }
  }
}
