/**
 * $文件说明$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-21 下午02:40:08
 */
package nc.impl.pu.m21.onway.bp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.data.vo.VOInsert;
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
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>在途状态输出
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-9-21 下午02:40:08
 */
public class OutputUpdateBp {

  // 未操作
  private static final String notOperated = "N";

  public void updateOutput(OrderVO[] orderVOs) {

    List<StatusOnWayItemVO> insList = new ArrayList<StatusOnWayItemVO>();
    List<StatusOnWayItemVO> updList = new ArrayList<StatusOnWayItemVO>();

    // 更新下一状态的数据.
    // 将传入的各张单据按交易类型分组
    Map<String, List<OrderVO>> orderMaplist =
        OnwayBpTools.groupByTrantype(orderVOs);

    // 相同交易类型的单据,取得所有的子表id
    Set<Map.Entry<String, List<OrderVO>>> mapEntry = orderMaplist.entrySet();

    // 针对不同的交易类型,下状态可能不样,因此分开处理
    for (Map.Entry<String, List<OrderVO>> entry : mapEntry) {

      String tranType = entry.getKey();
      List<OrderVO> orderList = entry.getValue();
      // 取得当前状态的VO
      Map<String, OrderItemVO> bodyPkMap = OnwayBpTools.getBodyPk(orderList);

      String[] bodyPkArray =
          bodyPkMap.keySet().toArray(new String[bodyPkMap.keySet().size()]);

      Map<String, StatusOnWayItemVO> currentStatusVOMap =
          this.getExistVO(bodyPkArray, OnwayStatus.STATUS_OUTPUT);
      // 查询出当前状态为空,说明出现并发,抛错
      if (currentStatusVOMap.size() == 0) {
        String tip = null;
        String message =
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("pubapp_0",
                "0pubapp-0177")/* @res "出现并发，请重新查询" */;
        ExceptionUtils.wrappBusinessException(message, tip);
      }

      Map<String, PoTransTypeVO> tranTypeMap =
          OnwayBpTools.getTransTypeVOs(orderVOs, OrderHeaderVO.VTRANTYPECODE);

      // 插入下一状态
      this.insertNextStatus(bodyPkMap, tranTypeMap, currentStatusVOMap,
          insList, tranType, OnwayStatus.STATUS_OUTPUT);

      // 更新自身状态的数据
      this.updateSelfStatus(currentStatusVOMap, updList);
    }

    // 批量插入
    VOInsert<StatusOnWayItemVO> insertTool = new VOInsert<StatusOnWayItemVO>();
    insertTool.insert(insList.toArray(new StatusOnWayItemVO[insList.size()]));

    // 批量更新
    VOUpdate<StatusOnWayItemVO> updateTool = new VOUpdate<StatusOnWayItemVO>();
    updateTool.update(updList.toArray(new StatusOnWayItemVO[updList.size()]),
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
   * 方法功能描述：生成需要插入的VO
   * <p>
   * <b>参数说明</b>
   * 
   * @param nextStatusVOMap
   * @param pkMap
   * @param nextStatus
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-25 上午11:48:32
   */
  private StatusOnWayItemVO[] creatInsertDataForOutput(
      Map<String, StatusOnWayItemVO> nextStatusVOMap,
      Map<String, OrderItemVO> pkMap, Integer nextStatus) {

    Set<Map.Entry<String, StatusOnWayItemVO>> nextStatusVO =
        nextStatusVOMap.entrySet();

    List<StatusOnWayItemVO> statusVOList = new ArrayList<StatusOnWayItemVO>();

    for (Map.Entry<String, StatusOnWayItemVO> nextStatusEntry : nextStatusVO) {
      String pk_order_b = nextStatusEntry.getKey();
      StatusOnWayItemVO statusVO = nextStatusEntry.getValue();
      StatusOnWayItemVO insertVO = (StatusOnWayItemVO) statusVO.clone();
      // 在途数量即为主数量
      insertVO.setNonwaynum(pkMap.get(pk_order_b).getNnum());
      // 在途状态为下一状态
      insertVO.setFonwaystatus(nextStatus);
      // 重新分配PK
      // this.resetPk(insertVO);
      insertVO.setPk_order_bb(null);

      statusVOList.add(insertVO);
    }

    return statusVOList.toArray(new StatusOnWayItemVO[statusVOList.size()]);
  }

  /**
   * 方法功能描述：由于每个操作状态的可操作数据只能存在一条，因此按照子表pk也可以将数据分开。
   * <p>
   * <b>参数说明</b>
   * 
   * @param onwayStatusVOs
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-25 上午10:31:51
   */
  private Map<String, StatusOnWayItemVO> formatReturnData(
      StatusOnWayItemVO[] onwayStatusVOs) {

    Map<String, StatusOnWayItemVO> onwayItemVO =
        new HashMap<String, StatusOnWayItemVO>();
    for (StatusOnWayItemVO onwayStatusVO : onwayStatusVOs) {
      onwayItemVO.put(onwayStatusVO.getPk_order_b(), onwayStatusVO);
    }

    return onwayItemVO;
  }

  /**
   * 方法功能描述：取得已经存在的VO
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderList
   * @param nextStatus
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-21 下午04:40:48
   */
  private Map<String, StatusOnWayItemVO> getExistVO(String[] pks,
      OnwayStatus nextStatus) {

    SqlBuilder sqlBd = new SqlBuilder();
    sqlBd.append(" and ");
    sqlBd.append(StatusOnWayItemVO.FONWAYSTATUS, nextStatus.toInt());
    sqlBd.append("and ");
    // 临时表
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_42.name());
    sqlBd.append(builder.buildSQL(StatusOnWayItemVO.PK_ORDER_B, pks));
    sqlBd.append("and ");
    sqlBd.append(StatusOnWayItemVO.ISOPERATED, OutputUpdateBp.notOperated);
    VOQuery<StatusOnWayItemVO> voquery =
        new VOQuery<StatusOnWayItemVO>(StatusOnWayItemVO.class);
    StatusOnWayItemVO[] onwayStatusVOs = voquery.query(sqlBd.toString(), null);

    // 用子表pk将数据分开
    return this.formatReturnData(onwayStatusVOs);
  }

  /**
   * 方法功能描述：插入下一状态的数据
   * <p>
   * <b>参数说明</b>
   * 
   * @param pkMap
   * @param tranTypeMap
   * @param currentStatusVOMap
   * @param insList
   * @param tranType
   * @param status
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-25 下午04:33:15
   */
  private void insertNextStatus(Map<String, OrderItemVO> pkMap,
      Map<String, PoTransTypeVO> tranTypeMap,
      Map<String, StatusOnWayItemVO> currentStatusVOMap,
      List<StatusOnWayItemVO> insList, String tranType, OnwayStatus status) {

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

    // 如果下一状态不存在，不插入数据
    if (nextStatus == null) {
      return;
    }

    // 生成要插入的数据
    StatusOnWayItemVO[] insertVO =
        this.creatInsertDataForOutput(currentStatusVOMap, pkMap, nextStatus);

    insList.addAll(Arrays.asList(insertVO));
  }

  /**
   * 方法功能描述：重新分配pk
   * <p>
   * <b>参数说明</b>
   * 
   * @param bvo
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-3 下午03:32:17
   */
  // private void resetPk(StatusOnWayItemVO bvo) {
  // DBTool dao = new DBTool();
  // String[] ids = dao.getOIDs(1);
  // String pk_order_bb = ids[0];
  // bvo.setPk_order_bb(pk_order_bb);
  // bvo.setPrimaryKey(pk_order_bb);
  // }

  /**
   * 方法功能描述：更新自身状态
   * <p>
   * <b>参数说明</b>
   * 
   * @param currentStatusVO
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-25 上午11:47:48
   */
  private void updateSelfStatus(Map<String, StatusOnWayItemVO> currentStatusVO,
      List<StatusOnWayItemVO> updList) {

    Collection<StatusOnWayItemVO> statusVOs = currentStatusVO.values();

    List<StatusOnWayItemVO> statusList = new ArrayList<StatusOnWayItemVO>();

    for (StatusOnWayItemVO statusVO : statusVOs) {
      StatusOnWayItemVO updateData = (StatusOnWayItemVO) statusVO.clone();
      updateData.setIsoperated(UFBoolean.TRUE);
      statusList.add(updateData);
    }

    updList.addAll(statusList);
  }
}
