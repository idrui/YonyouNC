/**
 * $文件说明$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-7 下午03:55:18
 */
package nc.impl.pu.m21.onway.bp;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.data.vo.tool.VOConcurrentTool;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.vo.pu.m21.entity.OrderOnwayHeaderVO;
import nc.vo.pu.m21.entity.OrderOnwayVO;
import nc.vo.pu.m21.entity.StatusOnWayItemVO;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>在途状态维护数据库交互类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-9-7 下午03:55:18
 */
public class OnwayDAO {

  /**
   * 方法功能描述：锁表，通过查询锁有问题
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_order
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-10-8 下午04:58:41
   */
  public void clockByPk(String pk_order) {
    String sql =
        "select distinct(pk_order_bb) from po_order_bb where pk_order = '"
            + pk_order + "' and dr = 0";
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql);
    String[] statusIdKeys = rowset.toOneDimensionStringArray();

    // 锁表
    new VOConcurrentTool().lock(StatusOnWayItemVO.class, statusIdKeys);

  }

  /**
   * 方法功能描述：查询累计数量，返回以pk_order_b为key的累计数量Map
   * <p>
   * <b>参数说明</b>
   * 
   * @param keys
   * @param status
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-29 上午09:37:13
   */
  public Map<String, UFDouble> getAccumNum(String keys[], Integer status) {

    Map<String, UFDouble> numMap = new HashMap<String, UFDouble>();

    if (ArrayUtils.isEmpty(keys)) {
      return numMap;
    }

    SqlBuilder sqlsb = new SqlBuilder();
    sqlsb
        .append("select pk_order_b, sum(nonwaynum) from po_order_bb where dr = 0 and ");
    sqlsb.append(StatusOnWayItemVO.FONWAYSTATUS, status);
    sqlsb.append(" and ");
    sqlsb.append(StatusOnWayItemVO.ISOPERATED, UFBoolean.TRUE);
    sqlsb.append(" and ");
    // 临时表
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_38.name());
    sqlsb.append(builder.buildSQL(StatusOnWayItemVO.PK_ORDER_B, keys));
    sqlsb.append(" group by ");
    sqlsb.append(StatusOnWayItemVO.PK_ORDER_B);

    DataAccessUtils utils = new DataAccessUtils();

    IRowSet rowset = utils.query(sqlsb.toString());

    String[][] numbers = rowset.toTwoDimensionStringArray();

    int firstDimLen = numbers.length;

    for (int firstDim = 0; firstDim < firstDimLen; firstDim++) {
      numMap.put(numbers[firstDim][0], new UFDouble(numbers[firstDim][1]));
    }

    return numMap;
  }

  /**
   * 方法功能描述：查询入库或到货数量
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @param comStatus
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-9 上午09:01:58
   */
  // public Map<String, UFDouble> getAfterOnwayNum(StatusOnWayItemVO[] vos,
  // OnwayStatus comStatus) {
  //
  // Set<String> bodyKeySet = new HashSet<String>();
  // for (StatusOnWayItemVO vo : vos) {
  // bodyKeySet.add(vo.getPk_order_b());
  // }
  // String[] bodyKeys = bodyKeySet.toArray(new String[0]);
  //
  // // if (iCompStatus > (Integer) OnwayStatus.STATUS_GETOUT.value()) {
  // StringBuffer sbufSQL3 = new StringBuffer("");
  // sbufSQL3.append(" select pk_order_b,");
  // // if (comStatus == ((Integer)
  // // OnwayStatus.STATUS_ARRIVE.value()).intValue()) {
  // if (comStatus == OnwayStatus.STATUS_ARRIVE) {
  // sbufSQL3.append(" sum(naccumarrvnum)");
  // }
  // else {
  // sbufSQL3.append(" sum(naccumstorenum)");
  //
  // }
  // sbufSQL3.append(" from po_order_b");
  // sbufSQL3.append(" where ");
  // int iLen = bodyKeys.length;
  // for (int i = 0; i < iLen; i++) {
  // sbufSQL3.append(" pk_order_b='");
  // sbufSQL3.append(bodyKeys[i]);
  // sbufSQL3.append("'");
  // if (i < iLen - 1) {
  // sbufSQL3.append(" or ");
  // }
  // }
  // sbufSQL3.append(" group by pk_order_b");
  // // }
  //
  // // 查数量 到货（或入库）数量
  // DataAccessUtils utils = new DataAccessUtils();
  // IRowSet rs = utils.query(sbufSQL3.toString());
  // // 比较状态数量：到货（或入库）累计数量
  // Map<String, UFDouble> hashBodyAfterOnWayNum =
  // new HashMap<String, UFDouble>();
  //
  // while (rs.next()) {
  // // KEY:corder_bid VALUE:到货（或入库）数量
  // hashBodyAfterOnWayNum.put(rs.getString(0), rs.getUFDouble(1));
  // }
  //
  // return hashBodyAfterOnWayNum;
  // }

  /**
   * 方法功能描述：取得确认数量，如果没有确认数量则去审批数量为确认数量
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-10-9 上午10:37:13
   */
  // public Map<String, UFDouble> getConfirmNum(String[] pk_order_bs) {
  // SqlBuilder sqlbd = new SqlBuilder();
  // sqlbd.append("select pk_order_b, min(nonwaynum) from po_order_bb where ");
  // sqlbd.append(StatusOnWayItemVO.PK_ORDER_B, pk_order_bs);
  // sqlbd.append(" and ");
  //
  // Integer[] onwayStatus =
  // new Integer[] {
  // (Integer) OnwayStatus.STATUS_AUDIT.value(),
  // (Integer) OnwayStatus.STATUS_CONFIRM.value()
  // };
  //
  // sqlbd.append(StatusOnWayItemVO.FONWAYSTATUS, new int[] {
  // 1, 2
  // });
  // // String sql =
  // //
  // "select pk_order_b, min(nonwaynum) from po_order_bb where pk_order_b in";
  // }

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
  public Map<String, StatusOnWayItemVO> getNextStatusOnwayVO(String[] pks,
      String isoper, Integer status) {

    SqlBuilder sqlbd = new SqlBuilder();
    sqlbd.append(" and ");
    sqlbd.append(StatusOnWayItemVO.FONWAYSTATUS, status);
    sqlbd.append(" and ");
    sqlbd.append(StatusOnWayItemVO.ISOPERATED, isoper);
    sqlbd.append(" and ");
    // 临时表
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_39.name());
    sqlbd.append(builder.buildSQL(StatusOnWayItemVO.PK_ORDER_B, pks));

    VOQuery<StatusOnWayItemVO> voqurey =
        new VOQuery<StatusOnWayItemVO>(StatusOnWayItemVO.class);

    StatusOnWayItemVO[] onwayVOs = voqurey.query(sqlbd.toString(), null);

    return OnwayBpTools.groupKeyMap(onwayVOs, StatusOnWayItemVO.PK_ORDER_B);
  }

  // public Map<String, UFDouble[]> getStatusNum(StatusOnWayItemVO[] vos,
  // int status, int comStatus) {
  //
  // if (ArrayUtils.isEmpty(vos)) {
  // return null;
  // }
  //
  // Set<String> bodyKeySet = new HashSet<String>();
  // for (StatusOnWayItemVO vo : vos) {
  // bodyKeySet.add(vo.getPk_order_b());
  // }
  // String[] bodyKeys = bodyKeySet.toArray(new String[0]);
  //
  // // 查询本状态及比较状态累计数量的语句
  // StringBuilder sbufSQL2 = new StringBuilder("");
  // sbufSQL2.append(" select pk_order_b,");
  // sbufSQL2.append(" case when fonwaystatus = " + status
  // + " then sum(nonwaynum) end d1,");
  // sbufSQL2.append(" case when fonwaystatus = " + comStatus
  // + " then sum(nonwaynum) end d2");
  // sbufSQL2.append(" from po_order_bb");
  // sbufSQL2.append(" where pk_order = '");
  // sbufSQL2.append(vos[0].getPk_order());
  // sbufSQL2.append("' and dr = 0 ");
  // sbufSQL2.append(" and fonwaystatus in (");
  // sbufSQL2.append(status);
  // sbufSQL2.append(",");
  // sbufSQL2.append(comStatus);
  // sbufSQL2.append(") and (");
  // int iLen = bodyKeys.length;
  // for (int i = 0; i < iLen; i++) {
  // sbufSQL2.append(" po_order_bb.pk_order_b = '");
  // sbufSQL2.append(bodyKeys[i]);
  // sbufSQL2.append("'");
  // if (i < iLen - 1) {
  // sbufSQL2.append(" or ");
  // }
  // }
  // sbufSQL2.append(")");
  // sbufSQL2.append(" group by pk_order_b,fonwaystatus");
  //
  // // 累计数量:本状态及比较状态 KEY:corder_bid VALUE:[本状态累计数量，比较状态累计数量]
  // Map<String, UFDouble[]> hashBodyAccuNum = new HashMap<String,
  // UFDouble[]>();
  //
  // DataAccessUtils utils = new DataAccessUtils();
  // IRowSet rs = utils.query(sbufSQL2.toString());
  // while (rs.next()) {
  // // KEY:corder_bid VALUE:[本状态累计数量，比较状态累计数量]
  // String sBKey = rs.getString(0);
  // if (hashBodyAccuNum.containsKey(sBKey)) {
  // UFDouble[] dTemp = hashBodyAccuNum.get(sBKey);
  // dTemp[0] = MathTool.add(dTemp[0], rs.getUFDouble(1));
  // dTemp[1] = MathTool.add(dTemp[1], rs.getUFDouble(2));
  // // hashBodyAccuNum.put(sBKey, dTemp);
  // }
  // else {
  // hashBodyAccuNum.put(sBKey, new UFDouble[] {
  // rs.getUFDouble(1), rs.getUFDouble(2)
  // });
  // }
  // }
  //
  // return hashBodyAccuNum;
  // }

  /**
   * 方法功能描述：在途状态操作时更新表头自定义项
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_order
   * @param onwayVO
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-29 下午02:44:18
   */
  public void updateVhdef(String pk_order, OrderOnwayVO onwayVO, int status) {

    String sql =
        "select distinct(pk_order_bb) from po_order_bb where pk_order = '"
            + pk_order + "' and dr = 0 and fonwaystatus = " + status;
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sql);
    String[] statusIdKeys = rowset.toOneDimensionStringArray();
    VOConcurrentTool tool = new VOConcurrentTool();
    // 锁表
    tool.lock(StatusOnWayItemVO.class, statusIdKeys);

    // 取得需要更形的子子表pk
    VOQuery<StatusOnWayItemVO> query =
        new VOQuery<StatusOnWayItemVO>(StatusOnWayItemVO.class);

    if (!ArrayUtils.isEmpty(statusIdKeys)) {
      StatusOnWayItemVO[] statusvos = query.query(statusIdKeys);

      for (StatusOnWayItemVO statusvo : statusvos) {
        this.headVOMapping(statusvo, onwayVO.getHVO());
      }

      String[] keys = new String[40];
      for (int i = 0; i < 40; i++) {
        String hdef = "vhdef" + String.valueOf(i + 1);
        keys[i] = hdef;
      }

      VOUpdate<StatusOnWayItemVO> update = new VOUpdate<StatusOnWayItemVO>();
      StatusOnWayItemVO[] newStatusvos = update.update(statusvos, keys);

      tool.checkTS(newStatusvos, statusvos);
    }
  }

  /**
   * 方法功能描述：由于将页面表头的自定义项信息也存于子子表中，因此此种映射
   * <p>
   * <b>参数说明</b>
   * 
   * @param onwayItemVO
   *          采购订单子子表-在途状态VO
   * @param viewHVO
   *          用于页面显示的表头VO
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-8-20 下午02:02:05
   */
  private void headVOMapping(StatusOnWayItemVO onwayItemVO,
      OrderOnwayHeaderVO viewHVO) {
    for (int i = 0; i < 40; i++) {
      String vhdef = "vhdef" + String.valueOf(i + 1);
      onwayItemVO.setAttributeValue(vhdef, viewHVO.getAttributeValue(vhdef));
    }
  }
}
