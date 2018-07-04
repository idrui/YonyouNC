/**
 * $文件说明$
 * 
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-13 下午12:09:05
 */
package nc.impl.pu.m21;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.pu.m21.query.OrderQueryBP;
import nc.impl.pu.m21.onway.bp.OnwayBpTools;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.pu.m21.IConfirmQuery;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.StatusOnWayItemVO;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pu.m21transtype.enumeration.OnwayStatus;
import nc.vo.pu.pub.enumeration.OnwayStatusQryEnum;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapSet;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单对方确认查询
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-7-13 下午12:09:05
 */
public class ConfirmQueryImpl implements IConfirmQuery {

  @Override
  public OrderVO[] confirmQuery(IQueryScheme queryScheme)
      throws BusinessException {
    try {
      DataAccessUtils utils = new DataAccessUtils();
      String sql = this.createSql(queryScheme);
      IRowSet rowset = utils.query(sql);

      // 主子表pk
      String[][] pks = rowset.toTwoDimensionStringArray();
      if (null == pks || pks.length == 0) {
        return null;
      }

      // 锁表,子子表主键
      String[] subPks = OnwayBpTools.getSubBodyPks(pks);

      // 主表pk
      String[] pk_orders = OnwayBpTools.getPkorder(pks);

      // 检索
      OrderVO[] orderVOs = new OrderQueryBP().query(pk_orders, UFBoolean.FALSE);

      Map<String, StatusOnWayItemVO> subOnwayVOs = this.getOnwayVO(subPks);

      // 将pk按照主表主键和子表主键分组,不考虑子子表
      Map<String, Set<String>> pkMap = this.groupPks(pks);

      // 用scheme
      boolean isConfirmed = this.isConfirm(queryScheme);
      // 过滤子表vo
      this.filtrateItemVO(orderVOs, pkMap, subOnwayVOs, isConfirmed);

      return orderVOs;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.m21.IConfirmQuery#confirmQuery(java.lang.String)
   */
  @Override
  public OrderVO[] confirmQuery(String sqlWhere) throws BusinessException {

    if (sqlWhere == null) {
      return new OrderVO[0];
    }

    boolean isConfirmed = false;

    try {

      if (sqlWhere.indexOf(StatusOnWayItemVO.ISOPERATED + " = 'N'") < 0) {
        isConfirmed = true;
      }

      DataAccessUtils utils = new DataAccessUtils();

      // String whereSql =
      // sqlWhere.replaceAll(OnwayStatusQryEnum.confirm.name() + " = 'N'",
      // "1 = 1");
      // whereSql =
      // whereSql.replaceAll(OnwayStatusQryEnum.confirm.name() + " = 'Y'",
      // "1 = 1");

      IRowSet rowset = utils.query(sqlWhere);

      // 主子表pk
      String[][] pks = rowset.toTwoDimensionStringArray();

      // 锁表,子子表主键
      String[] subPks = OnwayBpTools.getSubBodyPks(pks);

      // 主表pk
      String[] pk_orders = OnwayBpTools.getPkorder(pks);

      // 检索
      OrderVO[] orderVOs = new OrderQueryBP().query(pk_orders, UFBoolean.FALSE);

      Map<String, StatusOnWayItemVO> subOnwayVOs = this.getOnwayVO(subPks);

      // 将pk按照主表主键和子表主键分组,不考虑子子表
      Map<String, Set<String>> pkMap = this.groupPks(pks);

      // 过滤子表vo
      this.filtrateItemVO(orderVOs, pkMap, subOnwayVOs, isConfirmed);

      return orderVOs;

    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return null;

  }

  private String createSql(IQueryScheme queryScheme) {
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
        (Integer) OnwayStatus.STATUS_CONFIRM.value());
    sql.append(" and " + mainTableAlias + "." + OrderHeaderVO.BISLATEST,
        UFBoolean.TRUE);
    sql.append(" and " + itemTableAlias + "." + OrderItemVO.FISACTIVE,
        (Integer) EnumActive.ACTIVE.value());

    QueryCondition outputCond =
        qrySchemeProcessor.getQueryCondition(OnwayStatusQryEnum.confirm.code());
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
   * 方法功能描述：填充子子表信息
   * <p>
   * <b>参数说明</b>
   * 
   * @param subOnwayVOs
   * @param isoperated
   * @param bvo
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-26 下午07:05:43
   */
  private void fillSubInfo(Map<String, StatusOnWayItemVO> subOnwayVOs,
      boolean isoperated, OrderItemVO bvo) {

    // 填充子子表信息
    if (!isoperated) {
      this.fillSubInfoForNotConfirm(bvo);
    }
    else {
      this.fillSubInfoForConfirm(bvo, subOnwayVOs);
    }

  }

  /**
   * 方法功能描述：已确认状态设置子子表信息
   * <p>
   * <b>参数说明</b>
   * 
   * @param bvo
   * @param subOnwayVOs
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-26 下午06:58:13
   */
  private void fillSubInfoForConfirm(OrderItemVO bvo,
      Map<String, StatusOnWayItemVO> subOnwayVOs) {

    String pk_order_b = bvo.getPk_order_b();
    StatusOnWayItemVO onwayVO = subOnwayVOs.get(pk_order_b);

    // 对方订单号
    bvo.setVvendorordercode(onwayVO.getVbillcode());

    // 对方订单行号
    bvo.setVvendororderrow(onwayVO.getVvendororderrow());

    // 确认主数量
    UFDouble onwayNum = onwayVO.getNonwaynum();
    bvo.setNconfirmnum(onwayNum);

    // 确认金额
    UFDouble orignetPrice = bvo.getNorignetprice();
    UFDouble confirmMny = onwayNum.multiply(orignetPrice);
    bvo.setNconfirmmny(confirmMny);

    // 确认日期
    if (onwayVO.getDbilldate() != null) {
      bvo.setDconfirmdate(onwayVO.getDbilldate());
    }
    else {
      bvo.setDconfirmdate(AppContext.getInstance().getBusiDate());
    }

    // 计划到货日期（取子子表的计划到货日期）
    bvo.setDplanarrvdate(onwayVO.getDplanarrvdate());

  }

  /**
   * 方法功能描述：未确认状态设置默认值
   * <p>
   * <b>参数说明</b>
   * 
   * @param bvo
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-26 下午06:57:36
   */
  private void fillSubInfoForNotConfirm(OrderItemVO bvo) {

    // 确认数量
    UFDouble nnum = bvo.getNnum();
    bvo.setNconfirmnum(nnum);

    // 确认金额
    UFDouble orignetPrice = bvo.getNorignetprice();
    UFDouble confirmMny = nnum.multiply(orignetPrice);
    bvo.setNconfirmmny(confirmMny);

    // 确认日期
    // bvo.setDconfirmdate(dconfirmdate);
  }

  /**
   * 方法功能描述：过滤子表VO
   * <p>
   * <b>参数说明</b>
   * 
   * @param orderVOs
   *          要过滤的VO
   * @param pkMap
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-26 下午02:03:01
   */
  private void filtrateItemVO(OrderVO[] orderVOs,
      Map<String, Set<String>> pkMap,
      Map<String, StatusOnWayItemVO> subOnwayVOs, boolean isoperated) {

    List<OrderItemVO> bodyVOs = new ArrayList<OrderItemVO>();

    for (OrderVO orderVO : orderVOs) {
      String pk_order = orderVO.getHVO().getPk_order();
      OrderItemVO[] bvos = orderVO.getBVO();
      Set<String> bodyPkList = pkMap.get(pk_order);

      for (OrderItemVO bvo : bvos) {
        String pk_order_b = bvo.getPk_order_b();
        if (bodyPkList.contains(pk_order_b)) {

          this.fillSubInfo(subOnwayVOs, isoperated, bvo);

          bodyVOs.add(bvo);
        }
      }

      orderVO.setBVO(bodyVOs.toArray(new OrderItemVO[bodyVOs.size()]));

      bodyVOs.clear();
    }
  }

  /**
   * 方法功能描述：取得子子表VO
   * <p>
   * <b>参数说明</b>
   * 
   * @param pks
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-26 下午04:06:18
   */
  private Map<String, StatusOnWayItemVO> getOnwayVO(String[] pks) {

    if (ArrayUtils.isEmpty(pks)) {
      return new HashMap<String, StatusOnWayItemVO>();
    }

    // 取得子子表信息
    VOQuery<StatusOnWayItemVO> voquery =
        new VOQuery<StatusOnWayItemVO>(StatusOnWayItemVO.class);

    StatusOnWayItemVO[] onwayVOs = voquery.query(pks);

    Map<String, StatusOnWayItemVO> returnMap =
        new HashMap<String, StatusOnWayItemVO>();

    // 由于确认状态，子子表中每个子表数据只能对应一条子子表数据，因此质检按照子表pk区分即可。
    for (StatusOnWayItemVO onwayVO : onwayVOs) {
      returnMap.put(onwayVO.getPk_order_b(), onwayVO);
    }

    return returnMap;
  }

  /**
   * 方法功能描述：将pk按照主表主键和子表主键分组,不考虑子子表
   * <p>
   * <b>参数说明</b>
   * 
   * @param pks
   * @return <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-9-26 下午02:01:25
   */
  private Map<String, Set<String>> groupPks(String[][] pks) {

    if (pks == null || pks.length == 0) {
      return new HashMap<String, Set<String>>();
    }

    MapSet<String, String> pkMapSet = new MapSet<String, String>();

    int firstDimLen = pks.length;

    for (int firstDim = 0; firstDim < firstDimLen; firstDim++) {
      pkMapSet.put(pks[firstDim][0], pks[firstDim][1]);
    }

    return pkMapSet.toMap();
  }

  private boolean isConfirm(IQueryScheme queryScheme) {
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    QueryCondition confirmCond =
        qrySchemeProcessor.getQueryCondition(OnwayStatusQryEnum.confirm.code());
    if (null == confirmCond) {
      return false;
    }
    Object[] values = confirmCond.getValues();
    UFBoolean confirm = UFBoolean.valueOf((String) values[0]);
    return confirm.booleanValue();
  }
}
