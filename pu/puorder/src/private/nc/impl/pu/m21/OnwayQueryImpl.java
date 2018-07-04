/**
 * $文件说明$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-10 上午10:04:55
 */
package nc.impl.pu.m21;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.pu.m21.query.OrderQueryBP;
import nc.impl.pu.m21.onway.bp.OnwayBpTools;
import nc.impl.pu.m21.onway.bp.OnwayDAO;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.pu.m21.IOnwayQuery;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderOnwayHeaderVO;
import nc.vo.pu.m21.entity.OrderOnwayItemVO;
import nc.vo.pu.m21.entity.OrderOnwayVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.StatusOnWayItemVO;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>在途查询服务实现类
 * 注：为了应对添加40个表头自定义项，且自定义项在状态间不传递，因此不能使用订单表头自定义项，只能将在途状态的表头自定义项存于子子表
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-8-10 上午10:04:55
 */
public class OnwayQueryImpl implements IOnwayQuery {
  @Override
  public OrderOnwayVO[] onwayQuery(IQueryScheme queryScheme,
      String onwayStatusStr, int onwayStatus, boolean isDone)
      throws BusinessException {
    try {
      String sql = this.createSql(queryScheme, onwayStatusStr, onwayStatus);
      DataAccessUtils utils = new DataAccessUtils();

      IRowSet rowset = utils.query(sql);

      // 主子表pk
      String[][] pks = rowset.toTwoDimensionStringArray();
      if (null == pks || pks.length == 0) {
        return new OrderOnwayVO[0];
      }

      // pks = 空
      // 锁表,子子表主键
      String[] subPks = OnwayBpTools.getSubBodyPks(pks);

      // 主表pk
      String[] pk_orders = OnwayBpTools.getPkorder(pks);

      // 检索订单VO
      OrderVO[] orderVOs = new OrderQueryBP().query(pk_orders, UFBoolean.FALSE);

      // 生成页面显示用的表头VO的Map
      Map<String, OrderOnwayHeaderVO> onwayHVOMap =
          OnwayBpTools.creatOnwayHVOMap(orderVOs);

      // 生成订单表体的Map
      Map<String, OrderItemVO> orderBVOMap =
          OnwayBpTools.creatOrderBVOMap(orderVOs);

      // 取子子表信息
      VOQuery<StatusOnWayItemVO> voquery =
          new VOQuery<StatusOnWayItemVO>(StatusOnWayItemVO.class);

      StatusOnWayItemVO[] subVOs = new StatusOnWayItemVO[0];

      if (!ArrayUtils.isEmpty(subPks)) {
        subVOs = voquery.query(subPks);
      }

      // 取得累计操作数量
      // 子表pk
      String[] pk_order_bs = OnwayBpTools.getBodyPksByTwoDimStr(pks);
      OnwayDAO dao = new OnwayDAO();
      Map<String, UFDouble> accumNumMap =
          dao.getAccumNum(pk_order_bs, Integer.valueOf(onwayStatus));

      // 取得下状态操作总数量
      Map<String, UFDouble> nextStatusAccNumMap =
          this.getNextStatusAccNum(orderVOs, onwayStatus);

      OrderOnwayVO[] onwayVOs =
          this.creatOnwayAggVO(subVOs, onwayHVOMap, orderBVOMap, accumNumMap,
              nextStatusAccNumMap);

      return onwayVOs;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return new OrderOnwayVO[0];
  }

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.m21.IOnwayQuery#onwayQuery(java.lang.String)
   */
  @Override
  public OrderOnwayVO[] onwayQuery(String sqlWhere, int onwayStatus,
      boolean isDone) throws BusinessException {

    if (sqlWhere == null) {
      return new OrderOnwayVO[0];
    }

    // String sql = OnwayBpTools.replaceStatus(sqlWhere, onwayStatus);

    try {
      DataAccessUtils utils = new DataAccessUtils();

      IRowSet rowset = utils.query(sqlWhere);

      // 主子表pk
      String[][] pks = rowset.toTwoDimensionStringArray();

      if (null == pks || 0 == pks.length) {
        return new OrderOnwayVO[0];
      }
      // 锁表,子子表主键
      String[] subPks = OnwayBpTools.getSubBodyPks(pks);

      // 主表pk
      String[] pk_orders = OnwayBpTools.getPkorder(pks);

      // 检索订单VO
      OrderVO[] orderVOs = new OrderQueryBP().query(pk_orders, UFBoolean.FALSE);

      // 生成页面显示用的表头VO的Map
      Map<String, OrderOnwayHeaderVO> onwayHVOMap =
          OnwayBpTools.creatOnwayHVOMap(orderVOs);

      // 生成订单表体的Map
      Map<String, OrderItemVO> orderBVOMap =
          OnwayBpTools.creatOrderBVOMap(orderVOs);

      // 取子子表信息
      VOQuery<StatusOnWayItemVO> voquery =
          new VOQuery<StatusOnWayItemVO>(StatusOnWayItemVO.class);

      StatusOnWayItemVO[] subVOs = new StatusOnWayItemVO[0];

      if (!ArrayUtils.isEmpty(subPks)) {
        subVOs = voquery.query(subPks);
      }

      // 取得累计操作数量
      // 子表pk
      String[] pk_order_bs = OnwayBpTools.getBodyPksByTwoDimStr(pks);
      OnwayDAO dao = new OnwayDAO();
      Map<String, UFDouble> accumNumMap =
          dao.getAccumNum(pk_order_bs, Integer.valueOf(onwayStatus));

      // 取得下状态操作总数量
      Map<String, UFDouble> nextStatusAccNumMap =
          this.getNextStatusAccNum(orderVOs, onwayStatus);

      OrderOnwayVO[] onwayVOs =
          this.creatOnwayAggVO(subVOs, onwayHVOMap, orderBVOMap, accumNumMap,
              nextStatusAccNumMap);

      return onwayVOs;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return new OrderOnwayVO[0];
  }

  private String createSql(IQueryScheme queryScheme, String onwayStatusStr,
      int onwayStatus) {
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    String mainTableAlias = qrySchemeProcessor.getMainTableAlias();
    String itemTableAlias =
        qrySchemeProcessor.getTableAliasOfAttribute("pk_order_b.pk_org");
    String bbTableAlias =
        qrySchemeProcessor
            .getTableAliasOfAttribute("pk_order_b.pk_order_bb.pk_org");
    String where = queryScheme.getTableJoinFromWhereSQL().getWhere();
    if (StringUtils.isNotBlank(where)) {
      qrySchemeProcessor.appendWhere(" and ");
    }

    SqlBuilder sql = new SqlBuilder();
    sql.append(bbTableAlias + ".dr", 0);
    sql.append(" and " + bbTableAlias + "." + StatusOnWayItemVO.FONWAYSTATUS,
        onwayStatus);
    sql.append(" and " + mainTableAlias + "." + OrderHeaderVO.BISLATEST,
        UFBoolean.TRUE);
    sql.append(" and " + itemTableAlias + "." + OrderItemVO.FISACTIVE,
        (Integer) EnumActive.ACTIVE.value());

    QueryCondition outputCond =
        qrySchemeProcessor.getQueryCondition(onwayStatusStr);
    Object[] values = outputCond.getValues();
    UFBoolean output = UFBoolean.valueOf((String) values[0]);
    sql.append(" and ");
    if (output.booleanValue()) {
      sql.append(bbTableAlias + "." + StatusOnWayItemVO.ISOPERATED,
          UFBoolean.TRUE);
    }
    else {
      sql.append(bbTableAlias + "." + StatusOnWayItemVO.ISOPERATED,
          UFBoolean.FALSE);
    }

    qrySchemeProcessor.appendWhere(sql.toString());
    qrySchemeProcessor.appendCurrentGroup();

    sql = new SqlBuilder();
    sql.append("select distinct " + mainTableAlias + ".pk_order, ");
    sql.append(itemTableAlias + ".pk_order_b, " + bbTableAlias + ".pk_order_bb");

    sql.append(qrySchemeProcessor.getFinalFromWhere());
    return sql.toString();
  }

  /**
   * 方法功能描述：生成页面显示用聚合VO
   * <p>
   * <b>参数说明</b>
   * 
   * @param subVOs
   * @param onwayHVOMap
   * @param orderBVOMap
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-28 下午04:50:54
   */
  private OrderOnwayVO[] creatOnwayAggVO(StatusOnWayItemVO[] subVOs,
      Map<String, OrderOnwayHeaderVO> onwayHVOMap,
      Map<String, OrderItemVO> orderBVOMap, Map<String, UFDouble> accumNumMap,
      Map<String, UFDouble> nextStatusAccNumMap) {

    MapList<String, OrderOnwayItemVO> onwayBVOMapList =
        new MapList<String, OrderOnwayItemVO>();

    Map<String, StatusOnWayItemVO> statusItemVOMap =
        new HashMap<String, StatusOnWayItemVO>();

    for (StatusOnWayItemVO statusOnwayVO : subVOs) {

      // String pk_order_b = onwayVO.getPk_order_b();
      String pk_order = statusOnwayVO.getPk_order();

      OrderOnwayItemVO onwayBVO =
          OnwayBpTools.creatOnwayBVO(statusOnwayVO, orderBVOMap, accumNumMap,
              nextStatusAccNumMap);

      if (onwayBVO != null) {
        onwayBVOMapList.put(pk_order, onwayBVO);
        statusItemVOMap.put(pk_order, statusOnwayVO);
      }
    }

    OrderOnwayVO[] orderOnwayVOArray =
        OnwayBpTools.doCreatOnwayAggVO(onwayHVOMap, onwayBVOMapList,
            statusItemVOMap);

    return orderOnwayVOArray;
  }

  private Map<String, UFDouble> getAccNumMap(String[] pk_order_bs,
      Integer status) {
    OnwayDAO dao = new OnwayDAO();
    return dao.getAccumNum(pk_order_bs, status);
  }

  /**
   * 方法功能描述：计算下一状态操作总数量
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderVOs
   * @param onwayStatus
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-10-11 上午10:54:01
   */
  private Map<String, UFDouble> getNextStatusAccNum(OrderVO[] orderVOs,
      int onwayStatus) {

    Map<String, PoTransTypeVO> tranTypeMap =
        OnwayBpTools.getTransTypeVOs(orderVOs, OrderHeaderVO.VTRANTYPECODE);

    // 按照下一状态将vo分组
    Map<Integer, List<OrderVO>> orderMap =
        this.groupVOByNextStatus(orderVOs, tranTypeMap, onwayStatus);

    // 取得下状态操作总数量
    Map<String, UFDouble> nextStatusAccNumMap =
        this.getNextStatusAccNumMap(orderMap);

    return nextStatusAccNumMap;
  }

  /**
   * 方法功能描述：取得下状态操作总数量
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderMap
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-10-11 上午10:50:44
   */
  private Map<String, UFDouble> getNextStatusAccNumMap(
      Map<Integer, List<OrderVO>> orderMap) {

    Set<Map.Entry<Integer, List<OrderVO>>> orderSet = orderMap.entrySet();
    Set<String> pkSet = new HashSet<String>();

    Map<String, UFDouble> returnMap = new HashMap<String, UFDouble>();

    for (Map.Entry<Integer, List<OrderVO>> orderMapEntry : orderSet) {
      List<OrderVO> orderList = orderMapEntry.getValue();
      for (OrderVO orderVO : orderList) {
        OrderItemVO[] bvos = orderVO.getBVO();
        for (OrderItemVO bvo : bvos) {
          pkSet.add(bvo.getPk_order_b());
        }
      }

      String[] pk_order_bs = pkSet.toArray(new String[pkSet.size()]);
      Map<String, UFDouble> nextStatusAccNumMap =
          this.getAccNumMap(pk_order_bs, orderMapEntry.getKey());

      returnMap.putAll(nextStatusAccNumMap);

      pkSet.clear();
    }

    return returnMap;
  }

  /**
   * 方法功能描述：按照下状态将ordervo分组
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderVOs
   * @param tranTypeMap
   * @param onwayStatus
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-10-11 上午10:26:19
   */
  private Map<Integer, List<OrderVO>> groupVOByNextStatus(OrderVO[] orderVOs,
      Map<String, PoTransTypeVO> tranTypeMap, int onwayStatus) {

    MapList<Integer, OrderVO> orderMapList = new MapList<Integer, OrderVO>();

    for (OrderVO orderVO : orderVOs) {
      Integer nextStatus =
          OnwayBpTools.getNextStatus(orderVO, onwayStatus, tranTypeMap,
              OrderHeaderVO.VTRANTYPECODE);
      orderMapList.put(nextStatus, orderVO);
    }

    return orderMapList.toMap();
  }

}
