/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-17 上午10:12:51
 */
package nc.vo.pu.m21.pub;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.AggItemReceivePlanVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.constant.PUParaValue;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.IVOMeta;
import nc.vo.pubapp.pattern.model.tool.BillIndex;
import nc.vo.pubapp.pattern.pub.MapList;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货计划工具类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-17 上午10:12:51
 */
public class OrderReceivePlanUtils {

  // 到货计划号的分隔线"-"
  private static final String JOIN_SYMBOL_VPLANCODE = "-";

  /**
   * 方法功能描述：得到更新和新增的数组
   * <p>
   * <b>参数说明</b>
   * 
   * @param batchVO
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-17 下午02:47:57
   */
  public static OrderReceivePlanVO[] getAddAndUpVOs(BatchOperateVO batchVO) {
    if (null == batchVO) {
      return null;
    }

    Object[] checkVOs = null;
    // 新增的VO
    Object[] addVOs = batchVO.getAddObjs();
    // 更新后的VO
    Object[] upVOs = batchVO.getUpdObjs();

    if (ArrayUtils.isEmpty(addVOs) && ArrayUtils.isEmpty(upVOs)) {
      return null;
    }

    if (ArrayUtils.isEmpty(addVOs)) {
      return ArrayUtil.convertArrayType(upVOs);
    }
    else if (ArrayUtils.isEmpty(upVOs)) {
      return ArrayUtil.convertArrayType(addVOs);
    }
    else {
      int addLength = addVOs.length;
      int upLength = upVOs.length;
      checkVOs = new Object[addLength + upLength];
      System.arraycopy(addVOs, 0, checkVOs, 0, addLength);
      System.arraycopy(upVOs, 0, checkVOs, addLength, upLength);
      OrderReceivePlanVO[] rpVOs = ArrayUtil.convertArrayType(checkVOs);
      return rpVOs;
    }
  }

  public static AggItemReceivePlanVO[] getAggItemRPVO(
      OrderReceivePlanVO[] rpVOs, OrderVO vo) {
    MapList<String, OrderReceivePlanVO> mapList =
        new MapList<String, OrderReceivePlanVO>();
    for (OrderReceivePlanVO rpVO : rpVOs) {
      mapList.put(rpVO.getPk_order_b(), rpVO);
    }

    List<AggItemReceivePlanVO> list = new ArrayList<AggItemReceivePlanVO>();
    BillIndex index = new BillIndex(new OrderVO[] {
      vo
    });
    IVOMeta meta = vo.getMetaData().getVOMeta(OrderItemVO.class);
    for (Entry<String, List<OrderReceivePlanVO>> entry : mapList.entrySet()) {
      String key = entry.getKey();
      OrderItemVO itemVO = (OrderItemVO) index.get(meta, key);
      List<OrderReceivePlanVO> value = entry.getValue();
      OrderReceivePlanVO[] rps =
          value.toArray(new OrderReceivePlanVO[value.size()]);
      AggItemReceivePlanVO aggVO = new AggItemReceivePlanVO();
      aggVO.setParent(itemVO);
      aggVO.setChildren(OrderReceivePlanVO.class, rps);
      list.add(aggVO);
    }

    return list.toArray(new AggItemReceivePlanVO[list.size()]);
  }

  public static OrderReceivePlanVO[] getAllRPVOs(BatchOperateVO batchVO) {
    if (null == batchVO) {
      return null;
    }

    // 新增的VO
    Object[] addVOs = batchVO.getAddObjs();
    // 更新后的VO
    Object[] upVOs = batchVO.getUpdObjs();
    // 删除后的VO
    Object[] delVOs = batchVO.getDelObjs();

    Object[] ret = ArrayUtil.combinArrays(addVOs, upVOs, delVOs);
    if (ArrayUtils.isEmpty(ret)) {
      return null;
    }

    return ArrayUtil.convertArrayType(ret);
  }

  /**
   * 批次管理的到货计划
   * 
   * @param rpVOs
   * @return
   */
  public static OrderReceivePlanVO[] getBatchCodeRPVOs(
      OrderReceivePlanVO[] rpVOs) {
    MapList<String, OrderReceivePlanVO> mapList =
        new MapList<String, OrderReceivePlanVO>();
    for (OrderReceivePlanVO rpVO : rpVOs) {
      if (StringUtil.isEmptyWithTrim(rpVO.getVbatchcode())) {
        mapList.put(rpVO.getPk_arrvstoorg(), rpVO);
      }
    }

    List<OrderReceivePlanVO> rpList = new ArrayList<OrderReceivePlanVO>();
    for (Entry<String, List<OrderReceivePlanVO>> entry : mapList.entrySet()) {
      String key = entry.getKey();
      if (PUParaValue.po44.arrived_plan.equals(PUSysParamUtil.getPO44(key))) {
        rpList.addAll(entry.getValue());
      }
    }
    if (rpList.size() > 0) {
      return rpList.toArray(new OrderReceivePlanVO[rpList.size()]);
    }
    return null;
  }

  /**
   * 方法功能描述：根据一组到货计划号得到下一个到货计划号
   * <p>
   * <b>参数说明</b>
   * 
   * @param allPlanCode到货计划号
   * @param orderCode订单号
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-17 上午10:14:05
   */
  public static String getNextPlanCode(String[] allPlanCode, String orderCode) {
    if (StringUtil.isEmptyWithTrim(orderCode)) {
      return null;
    }

    // 最大的到货计划号
    int iMaxSequenceNo = 0;
    if (allPlanCode != null) {
      int iCurNo = 0;
      for (int i = 0; i < allPlanCode.length; i++) {
        if (allPlanCode[i] == null) {
          continue;
        }

        // 到货计划号
        iCurNo =
            OrderReceivePlanUtils.getSequenceNoByPlanCode(allPlanCode[i],
                orderCode);
        if (iCurNo > iMaxSequenceNo) {
          iMaxSequenceNo = iCurNo;
        }
      }
    }

    iMaxSequenceNo++;

    return OrderReceivePlanUtils.getVPlanCode(orderCode, iMaxSequenceNo);
  }

  /**
   * 方法功能描述：得到到货计划VO对应的所有表体
   * <p>
   * <b>参数说明</b>
   * 
   * @param rpVOs
   * @param orderVO
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-17 下午03:41:26
   */
  public static OrderItemVO[] getOrderItemVOs(OrderReceivePlanVO[] rpVOs,
      OrderVO orderVO) {
    if (ArrayUtils.isEmpty(rpVOs) || null == orderVO) {
      return null;
    }

    // 得到到货计划VO对应的所有表体主键
    Set<String> pkOrderBs = new HashSet<String>();
    for (OrderReceivePlanVO rpVO : rpVOs) {
      if (null == rpVO) {
        continue;
      }
      pkOrderBs.add(rpVO.getPk_order_b());
    }

    // 得到对应的表体
    int size = pkOrderBs.size();
    if (0 == size) {
      return null;
    }
    // Map<String, OrderItemVO> map = AggVOUtil.createItemMap(new OrderVO[] {
    // orderVO
    // });

    BillIndex index = new BillIndex(new OrderVO[] {
      orderVO
    });
    IVOMeta meta = orderVO.getMetaData().getVOMeta(OrderItemVO.class);
    OrderItemVO[] itemVOs = new OrderItemVO[size];
    Iterator<String> iter = pkOrderBs.iterator();
    int i = 0;
    while (iter.hasNext()) {
      String pkOrderB = iter.next();
      // OrderItemVO itemVO = map.get(pkOrderB);
      OrderItemVO itemVO = (OrderItemVO) index.get(meta, pkOrderB);
      itemVOs[i++] = itemVO;
    }

    return itemVOs;
  }

  /**
   * 方法功能描述：按到货计划号得到顺序号
   * <p>
   * <b>参数说明</b>
   * 
   * @param planCode到货计划号
   * @param orderCode订单号
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-17 上午10:16:21
   */
  private static int getSequenceNoByPlanCode(String planCode, String orderCode) {
    int iNo = 0;
    if (StringUtil.isEmptyWithTrim(orderCode)
        || StringUtil.isEmptyWithTrim(planCode)) {
      return iNo;
    }

    // 顺序号
    String sNo =
        planCode.substring(orderCode.length()
            + OrderReceivePlanUtils.JOIN_SYMBOL_VPLANCODE.length(),
            planCode.length());
    iNo = new Integer(sNo).intValue();
    return iNo;
  }

  /**
   * 方法功能描述：按订单号及顺序号得到到货计划号
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderCode订单号
   * @param orderNo顺序号
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-17 上午10:18:04
   */
  private static String getVPlanCode(String orderCode, int orderNo) {
    if (StringUtil.isEmptyWithTrim(orderCode) || orderNo <= 0) {
      return null;
    }

    // 数字按四位前面补0，适用于绝大多数情况。超出四位则按实际位数
    int iMaxLen = 4;
    String sNo = "" + orderNo;
    StringBuffer sPrefix = new StringBuffer();
    if (sNo.length() < iMaxLen) {
      int iPrefixLen = iMaxLen - sNo.length();
      for (int i = 0; i < iPrefixLen; i++) {
        sPrefix.append("0");
      }
      sNo = sPrefix.toString() + sNo;
    }

    // 设置
    return orderCode + OrderReceivePlanUtils.JOIN_SYMBOL_VPLANCODE + sNo;
  }

}
