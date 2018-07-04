/**
 * $文件说明$
 *
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-25 下午02:20:07
 */
package nc.impl.pu.m21.onway.bp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.data.vo.VODelete;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.StatusOnWayItemVO;
import nc.vo.pu.m21.pub.TrantypeUtil;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pu.m21transtype.enumeration.OnwayStatus;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>反输出Bp
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-9-25 下午02:20:07
 */
public class OutputDeleteBp {

  /**
   * 方法功能描述：反输出
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderVOs
   * @param status
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-25 下午04:11:57
   */
  public void deletOutput(OrderVO[] orderVOs) {

    List<StatusOnWayItemVO> delList = new ArrayList<StatusOnWayItemVO>();
    List<StatusOnWayItemVO> updList = new ArrayList<StatusOnWayItemVO>();

    // 批量取得交易类型Map
    Map<String, PoTransTypeVO> tranTypeMap =
        OnwayBpTools.getTransTypeVOs(orderVOs, OrderHeaderVO.VTRANTYPECODE);

    // 将传入的各张单据按交易类型分组
    Map<String, List<OrderVO>> orderMaplist = this.groupByTrantype(orderVOs);
    Set<Map.Entry<String, List<OrderVO>>> mapEntry = orderMaplist.entrySet();

    // 针对不同的交易类型,下状态可能不样,因此分开处理
    for (Map.Entry<String, List<OrderVO>> statusEntry : mapEntry) {

      // 相同交易类型的单据,取得所有的子表id
      List<OrderVO> orderVOList = statusEntry.getValue();
      Map<String, OrderItemVO> bodyPkMap = OnwayBpTools.getBodyPk(orderVOList);
      String tranType = statusEntry.getKey();

      Map<String, String> billPkCodeMap = this.getBillPkCodeMap(orderVOList);

      // 删除下一状态数据
      this.deleteNextStatus(tranType, bodyPkMap, tranTypeMap, delList,
          OnwayStatus.STATUS_OUTPUT, billPkCodeMap);

      // 更新当前数据
      this.updateCurrentStatus(bodyPkMap, updList, OnwayStatus.STATUS_OUTPUT);
    }

    // 批量删除
    VODelete<StatusOnWayItemVO> vodel = new VODelete<StatusOnWayItemVO>();
    vodel.delete(delList.toArray(new StatusOnWayItemVO[delList.size()]));

    // 批量更新
    VOUpdate<StatusOnWayItemVO> voupd = new VOUpdate<StatusOnWayItemVO>();
    voupd.update(updList.toArray(new StatusOnWayItemVO[delList.size()]),
        new String[] {
          StatusOnWayItemVO.ISOPERATED
        });

    // 更新自定义项
    // BillTransferTool<OrderVO> transferTool =
    // new BillTransferTool<OrderVO>(orderVOs);
    // BillUpdate<OrderVO> billUpdate = new BillUpdate<OrderVO>();
    // OrderVO[] oldVOs = transferTool.getOriginBills();
    // billUpdate.update(orderVOs, oldVOs);
  }

  /**
   * 方法功能描述：如果下一状态存在已操作的数据,不能反输出.
   * <p>
   * <b>参数说明</b>
   * 
   * @param onwayVOs
   * @param billPkCodeMap 单据主键和单据号的Map。key-单据主键，value-单据号
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-25 下午03:29:53
   */
  private void checkCanUnoutput(StatusOnWayItemVO[] onwayVOs,
      Map<String, String> billPkCodeMap) {
    // 取单据号
    Set<String> vbillcodeSet = new HashSet<String>();
    for (StatusOnWayItemVO onwayVO : onwayVOs) {
      if (onwayVO.getIsoperated().booleanValue()) {
        String vbillCode = billPkCodeMap.get(onwayVO.getPk_order());
        if (StringUtils.isNotBlank(vbillCode)) {
          vbillcodeSet.add(vbillCode);
        }
      }
    }
    if (vbillcodeSet.size() == 0) {
      return;
    }

    // 单据号排序
    List<String> vbillcodeList = new ArrayList<String>(vbillcodeSet);
    Collections.sort(vbillcodeList);
    StringBuffer buffer = new StringBuffer();
    for (String vbillcode : vbillcodeList) {
      buffer.append("[").append(vbillcode).append("]");
    }
    ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
        .getNCLangRes().getStrByID("4004030_0", "04004030-0169", null,
            new String[] {
              buffer.toString()
            })/*
               * @res
               * "选择的单据中{0}下个状态已处理,请重新选择."
               */);
  }

  /**
   * 方法功能描述:删除下一状态数据
   * <p>
   * <b>参数说明</b>
   * 
   * @param statusEntry
   * @param tranTypeMap
   * @param delList
   * @param status
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-25 下午03:59:16
   */
  private void deleteNextStatus(String tranType,
      Map<String, OrderItemVO> pkMap, Map<String, PoTransTypeVO> tranTypeMap,
      List<StatusOnWayItemVO> delList, OnwayStatus status,
      Map<String, String> billPkCodeMap) {

    // String nextStr = OnwayBpTools.getNextStatusStr(status);

    // if (StringUtils.isEmpty(nextStr)) {
    // ExceptionUtils.wrappBusinessException("当前状态不是在途订单状态之一，请检查");
    // }

    // 取得下一状态
    Integer nextStatus =
        TrantypeUtil.getInstance().getNextStatus(status.toInt(),
            tranTypeMap.get(tranType));
    // ((Integer) tranTypeMap.get(tranType).getAttributeValue(nextStr))
    // .intValue();

    // 如果下一个状态为空，没有要删除的数据
    if (nextStatus == null) {
      return;
    }

    // 取得要删除的VO
    StatusOnWayItemVO[] onwayVOs = this.getStatusOnwayVOs(pkMap, nextStatus);

    // 判断可否反输出
    this.checkCanUnoutput(onwayVOs, billPkCodeMap);

    delList.addAll(Arrays.asList(onwayVOs));

  }

  /**
   * 获取单据主键和单据号的Map。
   * 
   * @param orderVOList
   * @return Map key-单据主键，value-单据号
   */
  private Map<String, String> getBillPkCodeMap(List<OrderVO> orderVOList) {
    Map<String, String> billPkCodeMap = new HashMap<String, String>();
    for (OrderVO orderVO : orderVOList) {
      OrderHeaderVO hvo = orderVO.getHVO();
      billPkCodeMap.put(hvo.getPk_order(), hvo.getVbillcode());
    }
    return billPkCodeMap;
  }

  /**
   * 方法功能描述：取得在途状态VO
   * <p>
   * <b>参数说明</b>
   * 
   * @param pkMap
   * @param status
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-25 下午04:11:04
   */
  private StatusOnWayItemVO[] getStatusOnwayVOs(Map<String, OrderItemVO> pkMap,
      Integer status) {
    Set<String> keySet = pkMap.keySet();

    String[] keyArray = keySet.toArray(new String[keySet.size()]);

    SqlBuilder sqlBd = new SqlBuilder();
    sqlBd.append(" and ");
    sqlBd.append(StatusOnWayItemVO.FONWAYSTATUS, status);
    sqlBd.append("and ");
    // 临时表
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_41.name());
    sqlBd.append(builder.buildSQL(StatusOnWayItemVO.PK_ORDER_B, keyArray));

    VOQuery<StatusOnWayItemVO> voquery =
        new VOQuery<StatusOnWayItemVO>(StatusOnWayItemVO.class);
    StatusOnWayItemVO[] statusOnwayVOs = voquery.query(sqlBd.toString(), null);

    return statusOnwayVOs;
  }

  /**
   * 方法功能描述：按照订单类型将订单分组
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderVOs
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-21 下午03:58:38
   */
  private Map<String, List<OrderVO>> groupByTrantype(OrderVO[] orderVOs) {
    MapList<String, OrderVO> orderMaplist = new MapList<String, OrderVO>();

    for (OrderVO orderVO : orderVOs) {
      orderMaplist.put(orderVO.getHVO().getVtrantypecode(), orderVO);
    }

    return orderMaplist.toMap();
  }

  /**
   * 方法功能描述：更新当前数据
   * <p>
   * <b>参数说明</b>
   * 
   * @param pkMap
   * @param updList
   * @param status
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-25 下午04:11:32
   */
  private void updateCurrentStatus(Map<String, OrderItemVO> pkMap,
      List<StatusOnWayItemVO> updList, OnwayStatus status) {

    StatusOnWayItemVO[] currentVOs =
        this.getStatusOnwayVOs(pkMap, status.toInteger());

    for (StatusOnWayItemVO currentVO : currentVOs) {
      currentVO.setIsoperated(UFBoolean.FALSE);
    }

    updList.addAll(Arrays.asList(currentVOs));
  }
}
