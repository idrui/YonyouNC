/**
 * $文件说明$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-27 上午10:05:13
 */
package nc.impl.pu.m21.onway.bp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.data.vo.VOInsert;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.StatusOnWayItemVO;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pu.m21transtype.enumeration.OnwayStatus;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.collections.MapUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>订单状态对方确认更新
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-9-27 上午10:05:13
 */
public class ConfirmUpdateBp {

  /**
   * 方法功能描述：更新订单状态对方确认
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderVOs
   * @param status
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-27 上午10:06:11
   */
  public void updateConfirm(OrderVO[] orderVOs) {

    // 更新下一状态的操作状态为0的数据，如果没有，插入一条
    Map<String, PoTransTypeVO> tranTypeMap =
        OnwayBpTools.getTransTypeVOs(orderVOs, OrderHeaderVO.VTRANTYPECODE);

    Integer nextStatus =
        OnwayBpTools.getNextStatus(orderVOs[0],
            OnwayStatus.STATUS_CONFIRM.toInt(), tranTypeMap,
            OrderHeaderVO.VTRANTYPECODE);

    OrderItemVO[] bvos = orderVOs[0].getBVO();

    // 按子表主键分组
    Map<String, OrderItemVO> bvoMap =
        OnwayBpTools.groupKeyMap(bvos, OrderItemVO.PK_ORDER_B);

    // 取得子子表中下状态为操作的数据
    // 取得当前状态数据
    Map<String, StatusOnWayItemVO> currOnwayVOMap =
        this.getOnwayVOs(bvoMap, OnwayStatus.STATUS_CONFIRM.toInteger());

    // 查询出当前状态为空,说明出现并发,抛错
    if (currOnwayVOMap.size() == 0) {
      String tip = null;
      String message =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("pubapp_0",
              "0pubapp-0177")/* @res "出现并发，请重新查询" */;
      ExceptionUtils.wrappBusinessException(message, tip);
    }

    // 没有下状态时不对下状态操作
    if (nextStatus != null) {
      Map<String, StatusOnWayItemVO> nextOnwayVOMap =
          this.getOnwayVOs(bvoMap, nextStatus);

      Map<String, OrderItemVO> notExistBvoMap =
          new HashMap<String, OrderItemVO>();

      // 更新已存在的数据
      if (MapUtils.isNotEmpty(nextOnwayVOMap)) {
        notExistBvoMap = this.updateExistNextStatus(bvoMap, nextOnwayVOMap);
      }
      else {
        notExistBvoMap.putAll(bvoMap);
      }

      // 对于不存在下状态的数据，插入
      if (MapUtils.isNotEmpty(notExistBvoMap)) {
        this.insertNextStatus(currOnwayVOMap, notExistBvoMap, nextStatus);
      }
    }

    // 更新自身数据，将数量更新为主数量。
    this.updateCurrentStatus(currOnwayVOMap, bvoMap);

    // 更新自定义项
    BillTransferTool<OrderVO> transferTool =
        new BillTransferTool<OrderVO>(orderVOs);
    BillUpdate<OrderVO> billUpdate = new BillUpdate<OrderVO>();
    OrderVO[] oldVOs = transferTool.getOriginBills();
    billUpdate.update(orderVOs, oldVOs);

  }

  /**
   * 方法功能描述：生成插入下状态用的VO
   * <p>
   * <b>参数说明</b>
   * 
   * @param currOnwayVOMap
   * @param bvoMapEntry
   * @param nextStatus
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-27 下午03:35:57
   */
  private StatusOnWayItemVO creatInsertStatusVO(
      Map<String, StatusOnWayItemVO> currOnwayVOMap,
      Map.Entry<String, OrderItemVO> bvoMapEntry, Integer nextStatus) {

    String pk_order_b = bvoMapEntry.getKey();
    OrderItemVO bvo = bvoMapEntry.getValue();

    StatusOnWayItemVO currOnwayVO = currOnwayVOMap.get(pk_order_b);
    StatusOnWayItemVO insertOnwayVO = (StatusOnWayItemVO) currOnwayVO.clone();

    // 在途状态
    insertOnwayVO.setFonwaystatus(nextStatus);
    // 在途数量为确认数量
    insertOnwayVO.setNonwaynum(bvo.getNconfirmnum());
    // 最大可操作数量
    insertOnwayVO.setNmaxhandlenum(bvo.getNconfirmnum());
    // 是否已操作为否
    insertOnwayVO.setIsoperated(UFBoolean.FALSE);
    // 清空主键
    insertOnwayVO.setPk_order_bb(null);

    return insertOnwayVO;
    // // 单据日期记为确认日期
    // insertOnwayVO.setDbilldate(bvo.getDconfirmdate());
    // // 计划到货日期
    // insertOnwayVO.setDplanarrvdate(bvo.getDplanarrvdate());
    // // 在途状态
    // insertOnwayVO.setFonwaystatus(Integer.valueOf(nextStatus));
    // // 是否已操作为否
    // insertOnwayVO.setIsoperated(UFBoolean.FALSE);
    // // 在途数量为确认数量
    // insertOnwayVO.setNonwaynum(bvo.getNconfirmnum());
    // // 单据号为对方订单号
    // insertOnwayVO.setVbillcode(bvo.getVvendorordercode());
    // // 对方订单行号
    // insertOnwayVO.setVvendororderrow(bvo.getVvendororderrow());

    // StatusOnWayItemVO statusOnWayItemVO = new StatusOnWayItemVO();
    // // 单据日期记为确认日期
    // statusOnWayItemVO.setDbilldate(bvo.getDconfirmdate());
    // // 计划到货日期
    // statusOnWayItemVO.setDplanarrvdate(bvo.getDplanarrvdate());
    // // 在途状态
    // statusOnWayItemVO.setFonwaystatus(Integer.valueOf(nextStatus));
    // // 是否已操作为否
    // statusOnWayItemVO.setIsoperated(UFBoolean.FALSE);
    // // 在途数量为确认数量
    // statusOnWayItemVO.setNonwaynum(bvo.getNconfirmnum());
    // statusOnWayItemVO.setPk_group(bvo.getPk_group());
    // statusOnWayItemVO.setPk_order(bvo.getPk_order());
    // statusOnWayItemVO.setPk_order_b(bvo.getPk_order_b());
    // statusOnWayItemVO.setPk_org(bvo.getPk_org());
    // statusOnWayItemVO.setPk_org_v(bvo.getPk_org_v());
    // // 单据号为对方订单号
    // statusOnWayItemVO.setVbillcode(bvo.getVvendorordercode());
    // // 对方订单行号
    // statusOnWayItemVO.setVvendororderrow(bvo.getVvendororderrow());
  }

  /**
   * 方法功能描述：生成更新当前状态VO
   * <p>
   * <b>参数说明</b>
   * 
   * @param bvoMap
   * @param updateCurrVO
   * @param updateOrigVO
   * @param onwayMapEntry
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-27 下午03:59:52
   */
  private void creatUpdateCurrOnwayVO(Map<String, OrderItemVO> bvoMap,
      List<StatusOnWayItemVO> updateCurrVO,
      List<StatusOnWayItemVO> updateOrigVO,
      Map.Entry<String, StatusOnWayItemVO> onwayMapEntry) {

    String pk_order_b = onwayMapEntry.getKey();
    StatusOnWayItemVO onwayVO = onwayMapEntry.getValue();

    OrderItemVO bvo = bvoMap.get(pk_order_b);

    StatusOnWayItemVO updateOnwayVO = (StatusOnWayItemVO) onwayVO.clone();

    // 单据日期记为确认日期
    updateOnwayVO.setDbilldate(bvo.getDconfirmdate());
    // 计划到货日期
    updateOnwayVO.setDplanarrvdate(bvo.getDplanarrvdate());
    // 在途状态
    // updateOnwayVO.setFonwaystatus(Integer.valueOf(nextStatus));
    // 是否已操作为否
    updateOnwayVO.setIsoperated(UFBoolean.TRUE);
    // 在途数量为确认数量
    updateOnwayVO.setNonwaynum(bvo.getNconfirmnum());
    // 最大可操作数量
    updateOnwayVO.setNmaxhandlenum(bvo.getNconfirmnum());
    // 单据号为对方订单号
    updateOnwayVO.setVbillcode(bvo.getVvendorordercode());
    // 对方订单行号
    updateOnwayVO.setVvendororderrow(bvo.getVvendororderrow());

    updateCurrVO.add(updateOnwayVO);
    updateOrigVO.add(onwayVO);
  }

  /**
   * 方法功能描述：填入页面信息
   * <p>
   * <b>参数说明</b>
   * 
   * @param onwayVO
   * @param bvo
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-27 下午02:15:40
   */
  private void fillInputInfo(StatusOnWayItemVO onwayVO, OrderItemVO bvo) {
    // 对方订单号
    // onwayVO.setVbillcode(bvo.getVvendorordercode());

    // 对方订单行号
    // onwayVO.setVvendororderrow(bvo.getVvendororderrow());

    // 在途数量记为确认主数量
    onwayVO.setNonwaynum(bvo.getNconfirmnum());

    // 最大可操作数量
    onwayVO.setNmaxhandlenum(bvo.getNconfirmnum());

    // 确认日期
    // onwayVO.setDbilldate(bvo.getDconfirmdate());

    // 计划到货日期
    // onwayVO.setDplanarrvdate(bvo.getDplanarrvdate());
  }

  // /**
  // * 方法功能描述：取得下一状态
  // * <p>
  // * <b>参数说明</b>
  // *
  // * @param orderVOs
  // * @param status
  // * @param tranTypeMap
  // * @return <p>
  // * @since 6.0
  // * @author wanghuid
  // * @time 2010-9-27 上午11:56:07
  // */
  // private int getNextStatus(OrderVO orderVO, int status,
  // Map<String, PoTransTypeVO> tranTypeMap) {
  //
  // PoTransTypeVO tranTypeVO =
  // tranTypeMap.get(orderVO.getHVO().getVtrantypecode());
  //
  // String tranTypeStr = OnwayBpTools.getNextStatusStr(status);
  //
  // int nextStatus =
  // ((Integer) tranTypeVO.getAttributeValue(tranTypeStr)).intValue();
  // return nextStatus;
  // }

  /**
   * 方法功能描述：取得在途状态子子表中的数据（针对于每个订单表体行，子子表中操作状态为N的数据只有一条，因此返回值按照子表pk分类）
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderVO
   * @param status
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-27 上午11:52:04
   */
  private Map<String, StatusOnWayItemVO> getOnwayVOs(
      Map<String, OrderItemVO> bvoMap, Integer status) {

    String[] bvoPkArray = bvoMap.keySet().toArray(new String[bvoMap.size()]);

    SqlBuilder sqlbd = new SqlBuilder();

    sqlbd.append(" and ");
    sqlbd.append(StatusOnWayItemVO.FONWAYSTATUS, status);
    sqlbd.append(" and ");
    sqlbd.append(StatusOnWayItemVO.ISOPERATED, "N");
    sqlbd.append(" and ");
    // 临时表
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_37.name());
    sqlbd.append(builder.buildSQL(StatusOnWayItemVO.PK_ORDER_B, bvoPkArray));

    VOQuery<StatusOnWayItemVO> voquery =
        new VOQuery<StatusOnWayItemVO>(StatusOnWayItemVO.class);
    StatusOnWayItemVO[] onwayVOs = voquery.query(sqlbd.toString(), null);

    // 按照子表pk生成map
    return OnwayBpTools.groupKeyMap(onwayVOs, StatusOnWayItemVO.PK_ORDER_B);
  }

  /**
   * 方法功能描述：插入下状态数据
   * <p>
   * <b>参数说明</b>
   * 
   * @param currOnwayVOMap
   * @param bvoMap
   * @param nextStatus
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-27 下午03:33:40
   */
  private void insertNextStatus(Map<String, StatusOnWayItemVO> currOnwayVOMap,
      Map<String, OrderItemVO> bvoMap, Integer nextStatus) {

    Set<StatusOnWayItemVO> insertOnwaySet = new HashSet<StatusOnWayItemVO>();

    Set<Map.Entry<String, OrderItemVO>> bvoSet = bvoMap.entrySet();
    for (Map.Entry<String, OrderItemVO> bvoMapEntry : bvoSet) {

      StatusOnWayItemVO insertOnwayVO =
          this.creatInsertStatusVO(currOnwayVOMap, bvoMapEntry, nextStatus);

      insertOnwaySet.add(insertOnwayVO);

    }

    StatusOnWayItemVO[] insertOnwayVOArray =
        insertOnwaySet.toArray(new StatusOnWayItemVO[insertOnwaySet.size()]);

    VOInsert<StatusOnWayItemVO> voinsert = new VOInsert<StatusOnWayItemVO>();
    voinsert.insert(insertOnwayVOArray);
  }

  /**
   * 方法功能描述：更新当前状态的数据
   * <p>
   * <b>参数说明</b>
   * 
   * @param currOnwayVOMap
   * @param bvoMap
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-28 上午08:57:12
   */
  private void updateCurrentStatus(
      Map<String, StatusOnWayItemVO> currOnwayVOMap,
      Map<String, OrderItemVO> bvoMap) {

    List<StatusOnWayItemVO> updateCurrList = new ArrayList<StatusOnWayItemVO>();
    List<StatusOnWayItemVO> updateOrigList = new ArrayList<StatusOnWayItemVO>();

    Set<Map.Entry<String, StatusOnWayItemVO>> onwaySet =
        currOnwayVOMap.entrySet();
    for (Map.Entry<String, StatusOnWayItemVO> onwayMapEntry : onwaySet) {

      this.creatUpdateCurrOnwayVO(bvoMap, updateCurrList, updateOrigList,
          onwayMapEntry);
    }

    StatusOnWayItemVO[] updateCurrArray =
        updateCurrList.toArray(new StatusOnWayItemVO[updateCurrList.size()]);
    StatusOnWayItemVO[] updateOrigArray =
        updateOrigList.toArray(new StatusOnWayItemVO[updateOrigList.size()]);

    VOUpdate<StatusOnWayItemVO> voupdate = new VOUpdate<StatusOnWayItemVO>();
    voupdate.update(updateCurrArray, updateOrigArray);
  }

  /**
   * 方法功能描述：更新已经存在的下状态数据
   * <p>
   * <b>参数说明</b>
   * 
   * @param bvoMap
   * @param onwayVOMap
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-27 下午02:35:38
   */
  private Map<String, OrderItemVO> updateExistNextStatus(
      Map<String, OrderItemVO> bvoMap, Map<String, StatusOnWayItemVO> onwayVOMap) {

    Set<Map.Entry<String, StatusOnWayItemVO>> onwayVOEntrySet =
        onwayVOMap.entrySet();

    List<StatusOnWayItemVO> updateList = new ArrayList<StatusOnWayItemVO>();
    List<StatusOnWayItemVO> originList = new ArrayList<StatusOnWayItemVO>();

    Map<String, OrderItemVO> notExistBvoMap =
        new HashMap<String, OrderItemVO>();
    notExistBvoMap.putAll(bvoMap);

    for (Map.Entry<String, StatusOnWayItemVO> onwayVOMapEntry : onwayVOEntrySet) {
      String pk_order_b = onwayVOMapEntry.getKey();
      StatusOnWayItemVO onwayVO = onwayVOMapEntry.getValue();

      StatusOnWayItemVO updateVO = (StatusOnWayItemVO) onwayVO.clone();

      OrderItemVO bvo = bvoMap.get(pk_order_b);

      // 将页面信息填入
      this.fillInputInfo(updateVO, bvo);

      // 生成更新用VO
      updateList.add(updateVO);
      originList.add(onwayVO);

      // 将已经存在的VO去除
      notExistBvoMap.remove(pk_order_b);
    }

    VOUpdate<StatusOnWayItemVO> voupdate = new VOUpdate<StatusOnWayItemVO>();

    StatusOnWayItemVO[] updateVOs =
        updateList.toArray(new StatusOnWayItemVO[updateList.size()]);
    StatusOnWayItemVO[] originVOs =
        originList.toArray(new StatusOnWayItemVO[originList.size()]);

    voupdate.update(updateVOs, originVOs);

    return notExistBvoMap;
  }
}
