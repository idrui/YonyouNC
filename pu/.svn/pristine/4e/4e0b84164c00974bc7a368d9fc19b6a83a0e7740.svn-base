package nc.pubimpl.pu.m4t.pu.settle;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.pubitf.pu.m4t.pu.settle.IInitialEstSettleQuery;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m27.entity.InitialEstSettleVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.entity.StockSettleVOUtil;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pu.m4t.enumeration.InitialEstStatus;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.res.Variable;
import nc.vo.scmpub.util.FromWhereParseUtil;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>功能条目1
 * <li>功能条目2
 * <li>...
 * </ul>
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version 本版本号
 * @since 上一版本号
 * @author wangyf
 * @time 2010-4-6 上午09:39:38
 */
public class InitialSettleQueryImpl implements IInitialEstSettleQuery {

  @Override
  public StockSettleVO[] queryByBID(String[] bid) throws BusinessException {
    try {
      SqlBuilder sql = this.getFromWhr();// 以无别名的方式返回期初暂估单的主子查询连接条件
      String name = PUEntity.M4T_B_TAB + "." + InitialEstItemVO.PK_INITIALEST_B;
      sql.append(new IDExQueryBuilder(PUTempTable.tmp_po_4t_01.name())
          .buildSQL(name, bid));
      return this.queryByCond(sql.toString());
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public InitialEstSettleVO[] queryByCond(String cond) throws BusinessException {
    if (StringUtil.isEmptyWithTrim(cond)) {
      return null;
    }
    try {
      DataAccessUtils utils = new DataAccessUtils();
      IRowSet rowset = utils.query(this.getSettleQuerySql(cond));
      String[] ids = rowset.toOneDimensionStringArray();
      // 检查最大返回行数
      int maxRow = Variable.getMaxQueryCount();
      if (ids.length > maxRow) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("common", "04004000-0000", null, new String[] {
              String.valueOf(maxRow)
            })/* 查询结果返回记录数超过{0}条，请修改查询条件减少查询记录数！ */);
      }
      InitialEstSettleVO[] vos =
          new ViewQuery<InitialEstSettleVO>(InitialEstSettleVO.class)
              .query(ids);
      // 计算未结算数量、金额，本次结算数量等
      StockSettleVOUtil.calcStockCanSettle(vos);
      return vos;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;

  }

  @Override
  public StockSettleVO[] queryByOrderBID(String[] bid) throws BusinessException {

    try {
      SqlBuilder sql = this.getFromWhr();// 以无别名的方式返回期初暂估单的主子查询连接条件
      String name = PUEntity.M4T_B_TAB + "." + InitialEstItemVO.PK_ORDER_B;
      sql.append(new IDExQueryBuilder(PUTempTable.tmp_po_4t_02.name())
          .buildSQL(name, bid));
      return this.queryByCond(sql.toString());
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;

  }

  @Override
  public InitialEstVO[] queryInitialEstAggVOs(String[] hids, String[] bids) {
    return null;
  }

  private SqlBuilder getFromWhr() {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" from ");
    sql.append(PUEntity.M4T_H_TAB + " inner join " + PUEntity.M4T_B_TAB);
    sql.append(" on ");
    sql.append(PUEntity.M4T_H_TAB + "." + InitialEstHeaderVO.PK_INITIALEST);
    sql.append("=");
    sql.append(PUEntity.M4T_B_TAB + "." + InitialEstItemVO.PK_INITIALEST);
    sql.append(" where ");
    return sql;
  }

  private String getSettleFixCond(String htname, String btname) {
    SqlBuilder sql = new SqlBuilder();
    sql.startParentheses();
    // sql.append(htname);
    // sql.append(".dr=0 and ");
    // sql.append(btname);
    // sql.append(".dr=0 and ");
    sql.append(htname);
    sql.append(".");
    sql.append(InitialEstHeaderVO.FBILLSTATUS,
        InitialEstStatus.APPROVED.toInteger());// 审批状态
    sql.endParentheses();
    return sql.toString();
  }

  private String getSettleQuerySql(String cond) {
    if (StringUtils.isEmpty(cond)) {
      return cond;
    }
    // 处理子表别名
    FromWhereParseUtil util = new FromWhereParseUtil(cond);
    String btname = util.getTableAlias(PUEntity.M4T_B_TAB);
    String htname = util.getTableAlias(PUEntity.M4T_H_TAB);
    if (StringUtil.isEmptyWithTrim(btname)) {
      btname = PUEntity.M4T_B_TAB;
    }
    if (StringUtil.isEmptyWithTrim(htname)) {
      htname = PUEntity.M4T_H_TAB;
    }
    SqlBuilder sql = new SqlBuilder();
    sql.append("select distinct " + btname + "."
        + InitialEstItemVO.PK_INITIALEST_B);
    sql.append(cond);
    String fixCond = this.getSettleFixCond(htname, btname);
    if (!StringUtil.isEmptyWithTrim(fixCond)) {
      sql.append(" and ");
      sql.append(fixCond);
    }
    return sql.toString();
  }
}
