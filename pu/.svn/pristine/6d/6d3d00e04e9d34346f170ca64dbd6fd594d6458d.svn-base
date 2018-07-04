/**
 * $文件说明$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-27 下午04:05:45
 */
package nc.impl.pu.m21.onway.bp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.StatusOnWayItemVO;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pu.m21transtype.enumeration.OnwayStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>对方确认状态反确认BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-9-27 下午04:05:45
 */
public class ConfirmDeleteBp {

  /**
   * 方法功能描述：对方确认状态反操作
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderVOs
   * @param status
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-28 上午09:53:56
   */
  public void deleteConfirm(OrderVO[] orderVOs) {

    Map<String, PoTransTypeVO> tranTypeMap =
        OnwayBpTools.getTransTypeVOs(orderVOs, OrderHeaderVO.VTRANTYPECODE);

    Integer nextStatus =
        OnwayBpTools.getNextStatus(orderVOs[0],
            OnwayStatus.STATUS_CONFIRM.toInt(), tranTypeMap,
            OrderHeaderVO.VTRANTYPECODE);

    OrderItemVO[] bvo = orderVOs[0].getBVO();

    Map<String, OrderItemVO> bvoMap =
        OnwayBpTools.groupKeyMap(bvo, OrderItemVO.PK_ORDER_B);

    // 更新下一状态数量，如果更新后下一状态数量小于0，抛异常
    if (nextStatus != null) {
      this.updateNextStatus(bvoMap, nextStatus);
    }

    // 更新当前状态剩余数量（数量更新为主数量）
    this.updateCurrentStatusOnwayVO(bvoMap, OnwayStatus.STATUS_CONFIRM);

    // 更新自定义项
    BillTransferTool<OrderVO> transferTool =
        new BillTransferTool<OrderVO>(orderVOs);
    BillUpdate<OrderVO> billUpdate = new BillUpdate<OrderVO>();
    OrderVO[] oldVOs = transferTool.getOriginBills();
    billUpdate.update(orderVOs, oldVOs);
  }

  /**
   * 方法功能描述：生成需要更新的VO
   * <p>
   * <b>参数说明</b>
   * 
   * @param bvoMap
   * @param nextOnwayVOMap
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-28 上午08:49:06
   */
  private StatusOnWayItemVO[] creatUpdateNextStatusVO(
      Map<String, OrderItemVO> bvoMap,
      Map<String, StatusOnWayItemVO> nextOnwayVOMap) {

    Set<Map.Entry<String, StatusOnWayItemVO>> nextOnwaySet =
        nextOnwayVOMap.entrySet();

    Set<StatusOnWayItemVO> updateOnwaySet = new HashSet<StatusOnWayItemVO>();

    for (Map.Entry<String, StatusOnWayItemVO> nextOnwayMapEntry : nextOnwaySet) {
      StatusOnWayItemVO updateOnwayVO =
          this.getNextRemainNum(nextOnwayMapEntry, bvoMap);
      updateOnwaySet.add(updateOnwayVO);
    }

    return updateOnwaySet.toArray(new StatusOnWayItemVO[updateOnwaySet.size()]);
  }

  /**
   * 方法功能描述：生成需要更新的当前状态VO并更新，将数量更新回主数量
   * <p>
   * <b>参数说明</b>
   * 
   * @param bvoMap
   * @param currOperatedVOMap
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-28 上午09:40:43
   */
  private void doUpdateCurrVOs(Map<String, OrderItemVO> bvoMap,
      Map<String, StatusOnWayItemVO> currOperatedVOMap) {

    Set<Map.Entry<String, StatusOnWayItemVO>> currUpdateOnwaySet =
        currOperatedVOMap.entrySet();

    List<StatusOnWayItemVO> currUpdateVOList =
        new ArrayList<StatusOnWayItemVO>();
    List<StatusOnWayItemVO> origUpdateVOList =
        new ArrayList<StatusOnWayItemVO>();

    for (Map.Entry<String, StatusOnWayItemVO> currOperVOEntry : currUpdateOnwaySet) {
      String pk_order_b = currOperVOEntry.getKey();
      StatusOnWayItemVO currOperVO = currOperVOEntry.getValue();

      StatusOnWayItemVO currUpdateVO =
          this.fillCurrUpdateInfo(bvoMap, pk_order_b, currOperVO);

      currUpdateVOList.add(currUpdateVO);
      origUpdateVOList.add(currOperVO);
    }

    StatusOnWayItemVO[] updateArray =
        currUpdateVOList
            .toArray(new StatusOnWayItemVO[currUpdateVOList.size()]);
    StatusOnWayItemVO[] origArray =
        origUpdateVOList
            .toArray(new StatusOnWayItemVO[origUpdateVOList.size()]);

    VOUpdate<StatusOnWayItemVO> voupdate = new VOUpdate<StatusOnWayItemVO>();
    voupdate.update(updateArray, origArray);
  }

  /**
   * 方法功能描述：填充当前信息
   * <p>
   * <b>参数说明</b>
   * 
   * @param bvoMap
   * @param pk_order_b
   * @param currOperVO
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-28 上午09:50:26
   */
  private StatusOnWayItemVO fillCurrUpdateInfo(Map<String, OrderItemVO> bvoMap,
      String pk_order_b, StatusOnWayItemVO currOperVO) {
    StatusOnWayItemVO currUpdateVO = (StatusOnWayItemVO) currOperVO.clone();
    // 将在途数量更新回主数量
    OrderItemVO bvo = bvoMap.get(pk_order_b);

    UFDouble nnum = bvo.getNnum();

    currUpdateVO.setNonwaynum(nnum);
    currUpdateVO.setNmaxhandlenum(nnum);
    currUpdateVO.setIsoperated(UFBoolean.FALSE);
    currUpdateVO.setVbillcode(null);
    currUpdateVO.setVvendororderrow(null);
    currUpdateVO.setDbilldate(null);
    return currUpdateVO;
  }

  /**
   * 方法功能描述：取得下一环节的更新后数量
   * <p>
   * <b>参数说明</b>
   * 
   * @param mapEntry
   * @param bvoMap
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-28 上午08:48:38
   */
  private StatusOnWayItemVO getNextRemainNum(
      Map.Entry<String, StatusOnWayItemVO> mapEntry,
      Map<String, OrderItemVO> bvoMap) {

    String pk_order_b = mapEntry.getKey();
    StatusOnWayItemVO nextOnwayVO = mapEntry.getValue();

    StatusOnWayItemVO updateOnwayVO = (StatusOnWayItemVO) nextOnwayVO.clone();

    OrderItemVO bvo = bvoMap.get(pk_order_b);

    UFDouble confirmNum = bvo.getNconfirmnum();
    UFDouble nextNum = nextOnwayVO.getNonwaynum();
    UFDouble remainNum = MathTool.sub(nextNum, confirmNum);

    if (MathTool.compareTo(remainNum, UFDouble.ZERO_DBL) < 0) {
      String rowNun = bvo.getCrowno();

      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4004030_0", "04004030-0293", null, new String[] {
            rowNun
          })/* 第{0}行，下一环节已操作，不能反确认。 */);
    }

    updateOnwayVO.setNonwaynum(remainNum);

    return updateOnwayVO;
  }

  /**
   * 方法功能描述：取得下一状态数据
   * <p>
   * <b>参数说明</b>
   * 
   * @param pks
   * @param Status
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-27 下午04:43:19
   */
  // private Map<String, StatusOnWayItemVO> getNextStatusOnwayVO(String[] pks,
  // String isoper, int Status) {
  //
  // SqlBuilder sqlbd = new SqlBuilder();
  // sqlbd.append(" and ");
  // sqlbd.append(StatusOnWayItemVO.FONWAYSTATUS, Status);
  // sqlbd.append(" and ");
  // sqlbd.append(StatusOnWayItemVO.ISOPERATED, isoper);
  // sqlbd.append(" and ");
  // sqlbd.append(StatusOnWayItemVO.PK_ORDER_B, pks);
  //
  // VOQuery<StatusOnWayItemVO> voqurey =
  // new VOQuery<StatusOnWayItemVO>(StatusOnWayItemVO.class);
  //
  // StatusOnWayItemVO[] onwayVOs = voqurey.query(sqlbd.toString(), null);
  //
  // return OnwayBpTools.groupKeyMap(onwayVOs, StatusOnWayItemVO.PK_ORDER_B);
  // }

  /**
   * 方法功能描述：更新当前状态的数据
   * <p>
   * <b>参数说明</b>
   * 
   * @param bvoMap
   * @param status
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-28 上午09:53:27
   */
  private void updateCurrentStatusOnwayVO(Map<String, OrderItemVO> bvoMap,
      OnwayStatus status) {
    String[] bvoPkArray = bvoMap.keySet().toArray(new String[bvoMap.size()]);

    // 由于对方确认状态也只有一条数据，因此每个子表VO只对应一条数据
    OnwayDAO dao = new OnwayDAO();
    Map<String, StatusOnWayItemVO> currOperatedVOMap =
        dao.getNextStatusOnwayVO(bvoPkArray, "Y", status.toInteger());

    this.doUpdateCurrVOs(bvoMap, currOperatedVOMap);

  }

  /**
   * 方法功能描述：更新下一状态数量
   * <p>
   * <b>参数说明</b>
   * 
   * @param bvoMap
   * @param nextStatus
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-27 下午04:48:19
   */
  private void updateNextStatus(Map<String, OrderItemVO> bvoMap,
      Integer nextStatus) {

    String[] bvoPkArray = bvoMap.keySet().toArray(new String[bvoMap.size()]);

    OnwayDAO dao = new OnwayDAO();
    Map<String, StatusOnWayItemVO> nextOnwayVOMap =
        dao.getNextStatusOnwayVO(bvoPkArray, "N", nextStatus);

    StatusOnWayItemVO[] updateOnwayVOs =
        this.creatUpdateNextStatusVO(bvoMap, nextOnwayVOMap);

    VOUpdate<StatusOnWayItemVO> voupdate = new VOUpdate<StatusOnWayItemVO>();

    voupdate.update(updateOnwayVOs, new String[] {
      StatusOnWayItemVO.NONWAYNUM
    });
  }
}
