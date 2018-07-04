/**
 * $文件说明$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-30 上午09:42:16
 */
package nc.impl.pu.m21.onway.bp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.data.vo.VODelete;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderOnwayItemVO;
import nc.vo.pu.m21.entity.OrderOnwayVO;
import nc.vo.pu.m21.entity.StatusOnWayItemVO;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-9-30 上午09:42:16
 */
public class OnwayDeleteBpMy {

  // 当前状态
  private static final String ISCURR = "Y";

  // 下一状态
  private static final String NEXTCURR = "N";

  /**
   * 方法功能描述：在途状态反操作
   * <p>
   * <b>参数说明</b>
   * 
   * @param viewOnwayVOs
   * @param status
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-30 下午01:15:16
   */
  public void deleteOnway(OrderOnwayVO[] viewOnwayVOs, int status) {

    // 取得采购订单主表的pk
    OnwayDAO dao = new OnwayDAO();
    String pk_order = viewOnwayVOs[0].getHVO().getPk_order();
    dao.clockByPk(pk_order);

    Map<String, PoTransTypeVO> tranTypeMap =
        OnwayBpTools.getTransTypeVOs(viewOnwayVOs, OrderHeaderVO.VTRANTYPECODE);

    Integer nextStatus =
        OnwayBpTools.getNextStatus(viewOnwayVOs[0], status, tranTypeMap,
            OrderHeaderVO.VTRANTYPECODE);

    // 按pk_order_b分组求出反操作的总数量
    Map<String, String[]> operaAccumNumMap =
        this.getOperateAccumNum(viewOnwayVOs[0]);

    // 更新下一状态可操作的数量，如果操作后数量小于0， 抛异常
    if (nextStatus != null) {
      this.updateStatusNum(operaAccumNumMap, nextStatus,
          OnwayDeleteBpMy.NEXTCURR);
    }

    // 更新本状态剩余数量
    this.updateStatusNum(operaAccumNumMap, Integer.valueOf(status),
        OnwayDeleteBpMy.ISCURR);

    // 删除选择的已操作数据
    this.deleteSelectOnwayVO(viewOnwayVOs[0]);

    // 除了插入发货的数据之外，还要更新相同单据的表头自定义项信息。
    // 取得采购订单主表的pk
    dao.updateVhdef(pk_order, viewOnwayVOs[0], status);
  }

  /**
   * 方法功能描述：生成需要更新的VO数组
   * <p>
   * <b>参数说明</b>
   * 
   * @param operaAccumNumMap
   * @param nextOnwayVOMap
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-30 上午11:17:31
   */
  private StatusOnWayItemVO[] createStatusNumUpdateVO(
      Map<String, String[]> operaAccumNumMap,
      Map<String, StatusOnWayItemVO> onwayVOMap, String isCurr) {

    Set<StatusOnWayItemVO> updateOnwaySet = new HashSet<StatusOnWayItemVO>();

    Set<Map.Entry<String, StatusOnWayItemVO>> onwaySet = onwayVOMap.entrySet();

    if (OnwayDeleteBpMy.NEXTCURR.equals(isCurr)) {

      for (Map.Entry<String, StatusOnWayItemVO> onwayMapEntry : onwaySet) {
        // 计算下一状态更新后数量
        StatusOnWayItemVO updateOnwayVO =
            this.getNextStatusNum(onwayMapEntry, operaAccumNumMap);

        updateOnwaySet.add(updateOnwayVO);
      }
    }
    else {

      for (Map.Entry<String, StatusOnWayItemVO> onwayMapEntry : onwaySet) {
        // 计算当前状态剩余数量
        StatusOnWayItemVO updateOnwayVO =
            this.getCurrRemainNum(onwayMapEntry, operaAccumNumMap);

        updateOnwaySet.add(updateOnwayVO);
      }
    }

    return updateOnwaySet.toArray(new StatusOnWayItemVO[updateOnwaySet.size()]);
  }

  /**
   * 方法功能描述：删除选择的已操作数据
   * <p>
   * <b>参数说明</b>
   * 
   * @param viewOnwayVO
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-10-23 下午01:51:13
   */
  private void deleteSelectOnwayVO(OrderOnwayVO viewOnwayVO) {

    String[] pks = this.getSelectOnwayPks(viewOnwayVO);

    if (!ArrayUtils.isEmpty(pks)) {
      VOQuery<StatusOnWayItemVO> voquery =
          new VOQuery<StatusOnWayItemVO>(StatusOnWayItemVO.class);
      StatusOnWayItemVO[] deleteVOs = voquery.query(pks);

      VODelete<StatusOnWayItemVO> vodelete = new VODelete<StatusOnWayItemVO>();
      vodelete.delete(deleteVOs);
    }
  }

  /**
   * 方法功能描述：计算当前状态的剩余数量
   * <p>
   * <b>参数说明</b>
   * 
   * @param currOnwayMapEntry
   * @param operaAccumNumMap
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-30 上午11:55:16
   */
  private StatusOnWayItemVO getCurrRemainNum(
      Map.Entry<String, StatusOnWayItemVO> currOnwayMapEntry,
      Map<String, String[]> operaAccumNumMap) {

    String pk_order_b = currOnwayMapEntry.getKey();
    StatusOnWayItemVO onwayVO = currOnwayMapEntry.getValue();

    // 操作总数量
    String operaAccumNumStr = operaAccumNumMap.get(pk_order_b)[1];
    UFDouble operaAccumNum = new UFDouble(operaAccumNumStr);

    StatusOnWayItemVO updateOnwayVO = (StatusOnWayItemVO) onwayVO.clone();

    // 更新前数量
    UFDouble remainNumBef = updateOnwayVO.getNonwaynum();
    // 更新后数量
    UFDouble remainNumAft = MathTool.add(remainNumBef, operaAccumNum);

    updateOnwayVO.setNonwaynum(remainNumAft);

    return updateOnwayVO;
  }

  /**
   * 方法功能描述：计算下一状态更新后数量
   * <p>
   * <b>参数说明</b>
   * 
   * @param nextOnwayMapEntry
   * @param operaAccumNumMap
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-30 上午11:14:21
   */
  private StatusOnWayItemVO getNextStatusNum(
      Map.Entry<String, StatusOnWayItemVO> nextOnwayMapEntry,
      Map<String, String[]> operaAccumNumMap) {

    String pk_order_b = nextOnwayMapEntry.getKey();
    StatusOnWayItemVO onwayVO = nextOnwayMapEntry.getValue();

    // 操作行号
    String operaRow = operaAccumNumMap.get(pk_order_b)[0];
    // 操作总数量
    String operaAccumNumStr = operaAccumNumMap.get(pk_order_b)[1];
    UFDouble operaAccumNum = new UFDouble(operaAccumNumStr);

    StatusOnWayItemVO updateOnwayVO = (StatusOnWayItemVO) onwayVO.clone();

    // 更新前数量
    UFDouble nextNumBef = updateOnwayVO.getNonwaynum();
    // 更新后数量
    UFDouble nextNumAft = MathTool.sub(nextNumBef, operaAccumNum);

    // 如果更新后数量小于0，不能反操作
    if (MathTool.compareTo(nextNumAft, UFDouble.ZERO_DBL) < 0) {

      // 可以反操作范围
      String canOperaNumMsg = "[0," + nextNumBef + ")";

      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance().getStrByID("4004030_0", "04004030-0295", null, new String[]{operaRow,canOperaNumMsg})/*第{0}行，下一环节已操作，此处可反操作的数量范围：{1}*/);
    }

    updateOnwayVO.setNonwaynum(nextNumAft);

    return updateOnwayVO;
  }

  /**
   * 方法功能描述：取得反操作的所有的总数量
   * <p>
   * <b>参数说明</b>
   * 
   * @param viewOnwayVO
   * @param status
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-30 上午10:33:28
   */
  private Map<String, String[]> getOperateAccumNum(OrderOnwayVO viewOnwayVO) {

    OrderOnwayItemVO[] viewBVOs = viewOnwayVO.getBVO();
    Map<String, String[]> accumNumMap = new HashMap<String, String[]>();

    Map<String, List<OrderOnwayItemVO>> viewBVOMap =
        this.groupBVOByBPK(viewBVOs);

    Set<Map.Entry<String, List<OrderOnwayItemVO>>> viewBVOSet =
        viewBVOMap.entrySet();

    // 记录行号
    StringBuilder rowsb = new StringBuilder();
    // 反操作总数量
    UFDouble operaAccumNum = new UFDouble();

    for (Map.Entry<String, List<OrderOnwayItemVO>> viewBVOMapEntry : viewBVOSet) {
      List<OrderOnwayItemVO> viewBVOList = viewBVOMapEntry.getValue();

      for (OrderOnwayItemVO viewBVO : viewBVOList) {
        rowsb.append(viewBVO.getCrowno());
        rowsb.append(",");

        // 计算反操作总数量
        operaAccumNum = MathTool.add(operaAccumNum, viewBVO.getNonwaynum());
      }

      // 数组的第一个元素记录行号，第二个元素记录操作数量
      String[] numStr = new String[2];
      numStr[0] = rowsb.substring(0, rowsb.length() - 1).toString();
      numStr[1] = operaAccumNum.toString();

      accumNumMap.put(viewBVOMapEntry.getKey(), numStr);

      rowsb.setLength(0);
      operaAccumNum = UFDouble.ZERO_DBL;
    }

    return accumNumMap;
  }

  /**
   * 方法功能描述：取得选择的所有子子表主键
   * <p>
   * <b>参数说明</b>
   * 
   * @param viewOnwayVO
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-30 下午01:10:38
   */
  private String[] getSelectOnwayPks(OrderOnwayVO viewOnwayVO) {

    OrderOnwayItemVO[] viewBVOs = viewOnwayVO.getBVO();

    Set<String> pkSet = new HashSet<String>();

    for (OrderOnwayItemVO viewBVO : viewBVOs) {
      pkSet.add(viewBVO.getPk_order_bb());
    }

    return pkSet.toArray(new String[pkSet.size()]);
  }

  /**
   * 方法功能描述：将表体VO按照pk_order_b分组
   * <p>
   * <b>参数说明</b>
   * 
   * @param viewBVOs
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-30 上午10:12:48
   */
  private Map<String, List<OrderOnwayItemVO>> groupBVOByBPK(
      OrderOnwayItemVO[] viewBVOs) {

    MapList<String, OrderOnwayItemVO> bvoMapList =
        new MapList<String, OrderOnwayItemVO>();

    for (OrderOnwayItemVO bvo : viewBVOs) {
      bvoMapList.put(bvo.getPk_order_b(), bvo);
    }

    return bvoMapList.toMap();
  }

  // private void updateCurrRemainNum(Map<String, String[]> operaAccumNumMap,
  // int status) {
  //
  // String[] bvoPkArray =
  // operaAccumNumMap.keySet().toArray(new String[operaAccumNumMap.size()]);
  //
  // // 由于对方确认状态也只有一条数据，因此每个子表VO只对应一条数据
  // OnwayDAO dao = new OnwayDAO();
  // Map<String, StatusOnWayItemVO> currOperatedVOMap =
  // dao.getNextStatusOnwayVO(bvoPkArray, "Y", status);
  //
  // }

  /**
   * 方法功能描述：更新下一状态数量
   * <p>
   * <b>参数说明</b>
   * 
   * @param operaAccumNum
   * @param nextStatus
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-30 上午11:19:56
   */
  private void updateStatusNum(Map<String, String[]> operaAccumNum,
      Integer status, String isCurr) {

    String[] bvoPkArray =
        operaAccumNum.keySet().toArray(new String[operaAccumNum.size()]);

    OnwayDAO dao = new OnwayDAO();
    Map<String, StatusOnWayItemVO> nextOnwayVOMap =
        dao.getNextStatusOnwayVO(bvoPkArray, "N", status);

    StatusOnWayItemVO[] updateOnwayVOs =
        this.createStatusNumUpdateVO(operaAccumNum, nextOnwayVOMap, isCurr);

    VOUpdate<StatusOnWayItemVO> voupdate = new VOUpdate<StatusOnWayItemVO>();

    voupdate.update(updateOnwayVOs, new String[] {
      StatusOnWayItemVO.NONWAYNUM
    });
  }
}
