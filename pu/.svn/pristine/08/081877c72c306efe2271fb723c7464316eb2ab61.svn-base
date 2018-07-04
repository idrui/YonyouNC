package nc.bs.pu.m20.query.logicutil;

import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20trantype.entity.BuyrTranTypeVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.query.ConditionVO;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>请购单非元数据查询条件处理类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author liuchx
 * @time 2010-9-1 下午12:01:59;
 */
public class CondTOWhereUtil {

  /**
   * 方法功能描述：是否过滤未购辅数量为0的请购单行处理
   * <p>
   * <b>参数说明</b>
   * 
   * @param cond
   * @param where
   * @param itemtb
   *          <p>
   * @since 6.0
   * @author liuchx
   * @time 2010-9-1 上午11:57:00
   */
  public static void buildFilterzeroflag(QuerySchemeProcessor psor,
      StringBuffer sql, String itemtb) {

    QueryCondition con =
        psor.getQueryCondition(PraybillHeaderVO.BFILTERZEROFLAG);
    if (con != null && con.getValues() != null) {
      String isGenCT = con.getValues()[0];
      if (UFBoolean.TRUE.equals(UFBoolean.valueOf(isGenCT))) {
        sql.append(" and ");
        sql.append(itemtb + "." + PraybillItemVO.NNUM + " > " + "isnull("
            + itemtb + "." + PraybillItemVO.NACCUMULATENUM + ",0)");
      }
    }
  }

  /**
   * 方法功能描述：请购安排处理
   * 
   * @param psor
   * @param sql
   * @param headtb
   * @param itemtb
   * @since 6.36
   * @author luojw
   * @time 2015-1-29 上午11:17:00
   */
  public static SqlBuilder buildIsarrange(SqlBuilder sql, String headtb,
      String itemtb) {
    StringBuilder builder = new StringBuilder(sql.toString());
    CondTOWhereUtil.buildIsarrange(builder, headtb, itemtb);
    SqlBuilder newSql = new SqlBuilder();
    newSql.append(builder.toString());
    return newSql;
  }

  /**
   * 方法功能描述：请购安排处理
   * 
   * @param psor
   * @param sql
   * @param headtb
   * @param itemtb
   * @since 6.36
   * @author luojw
   * @time 2015-1-29 上午11:17:00
   */
  public static void buildIsarrange(StringBuffer sql, String headtb,
      String itemtb) {
    String jointb = " tr";
    sql.insert(sql.indexOf(" where"), CondTOWhereUtil.getJoin(headtb, jointb));
    sql.append(CondTOWhereUtil.getFilter(itemtb, jointb));
  }

  /**
   * 方法功能描述：请购安排处理
   * 
   * @param psor
   * @param sql
   * @param headtb
   * @param itemtb
   * @since 6.36
   * @author luojw
   * @time 2015-1-29 上午11:17:00
   */
  public static void buildIsarrange(StringBuilder sql, String headtb,
      String itemtb) {
    String jointb = " tr";
    sql.insert(sql.indexOf(" where"), CondTOWhereUtil.getJoin(headtb, jointb));
    sql.append(CondTOWhereUtil.getFilter(itemtb, jointb));
  }

  /**
   * 方法功能描述：是否有供应商有效价格处理
   * <p>
   * <b>参数说明</b>
   * 
   * @param cond
   * @param where
   * @param itemtb
   *          <p>
   * @since 6.0
   * @author liuchx
   * @time 2010-9-1 上午11:57:00
   */
  public static void buildIseffectprice(ConditionVO cond, StringBuffer where,
      String itemtb) {
    if (UFBoolean.TRUE.toString().equals(cond.getValue())) {

    }
  }

  /**
   * 方法功能描述：是否已经生成询报价单处理
   * <p>
   * <b>参数说明</b>
   * 
   * @param cond
   * @param where
   * @param itemtb
   *          <p>
   * @since 6.0
   * @author liuchx
   * @time 2010-9-1 上午11:57:00
   */
  public static void buildIsgenaskbill(QuerySchemeProcessor psor,
      StringBuffer sql, String itemtb) {
    QueryCondition con = psor.getQueryCondition(PraybillHeaderVO.BISGENASKBILL);
    if (con != null && con.getValues() != null) {
      String isGenCT = con.getValues()[0];
      if (UFBoolean.TRUE.equals(UFBoolean.valueOf(isGenCT))) {
        sql.append(" and ");
        sql.append(itemtb + "." + PraybillItemVO.NQUOTEBILL + " > 0");
      }
      else if (UFBoolean.FALSE.equals(UFBoolean.valueOf(isGenCT))) {
        sql.append(" and ");
        sql.append(itemtb + "." + PraybillItemVO.NQUOTEBILL + " = 0");
      }
    }
  }

  /**
   * 方法功能描述：是否已经生成采购订单处理
   * <p>
   * <b>参数说明</b>
   * 
   * @param cond
   * @param where
   * @param itemtb
   *          <p>
   * @since 6.0
   * @author liuchx
   * @time 2010-9-1 上午11:57:00
   */
  public static void buildIsgenorderbill(QuerySchemeProcessor psor,
      StringBuffer sql, String itemtb) {
    QueryCondition con =
        psor.getQueryCondition(PraybillHeaderVO.BISGENORDERBILL);
    if (con != null && con.getValues() != null) {
      String isGenCT = con.getValues()[0];
      if (UFBoolean.TRUE.equals(UFBoolean.valueOf(isGenCT))) {
        sql.append(" and ");
        sql.append(itemtb + "." + PraybillItemVO.NACCUMULATENUM + " > 0");
      }
      else if (UFBoolean.FALSE.equals(UFBoolean.valueOf(isGenCT))) {
        sql.append(" and ");
        sql.append(itemtb + "." + PraybillItemVO.NACCUMULATENUM + " = 0");
      }
    }
  }

  /**
   * 方法功能描述：是否已经生成价格审批单
   * <p>
   * <b>参数说明</b>
   * 
   * @param cond
   * @param where
   * @param itemtb
   *          <p>
   * @since 6.0
   * @author liuchx
   * @time 2010-9-1 上午11:57:00
   */
  public static void buildIsngenAudit(QuerySchemeProcessor psor,
      StringBuffer sql, String itemtb) {
    QueryCondition con =
        psor.getQueryCondition(PraybillHeaderVO.BISGENPRICEAUDITBILL);
    if (con != null && con.getValues() != null) {
      String isGenCT = con.getValues()[0];
      if (UFBoolean.TRUE.equals(UFBoolean.valueOf(isGenCT))) {
        sql.append(" and ");
        sql.append(itemtb + "." + PraybillItemVO.NPRICEAUDITBILL + " > 0");
      }
      else if (UFBoolean.FALSE.equals(UFBoolean.valueOf(isGenCT))) {
        sql.append(" and ");
        sql.append(itemtb + "." + PraybillItemVO.NPRICEAUDITBILL + " = 0");
      }
    }
  }

  /**
   * 方法功能描述：是否已经生成合同处理
   * <p>
   * <b>参数说明</b>
   * 
   * @param cond
   * @param where
   * @param itemtb
   *          <p>
   * @since 6.0
   * @author liuchx
   * @time 2010-9-1 上午11:57:00
   */
  public static void buildIsngenct(QuerySchemeProcessor psor, StringBuffer sql,
      String itemtb) {
    QueryCondition con = psor.getQueryCondition(PraybillHeaderVO.BISNGENCT);
    if (con != null && con.getValues() != null) {
      String isGenCT = con.getValues()[0];
      if (UFBoolean.TRUE.equals(UFBoolean.valueOf(isGenCT))) {
        sql.append(" and ");
        sql.append(itemtb + "." + PraybillItemVO.NGENCT + " > 0");
      }
      else if (UFBoolean.FALSE.equals(UFBoolean.valueOf(isGenCT))) {
        sql.append(" and ");
        sql.append(itemtb + "." + PraybillItemVO.NGENCT + " = 0");
      }
    }
  }

  /**
   * 获得要添加的交易类型逻辑
   * 
   * @param itemtb
   * @param jointb
   * @return
   */
  private static String getFilter(String itemtb, String jointb) {
    StringBuilder sql = new StringBuilder();
    sql.append(" and (")
        // 交易类型不需要请购安排
        .append(jointb + "." + BuyrTranTypeVO.BNEEDARRANGE + " = 'N'")
        .append(" or (")
        // 交易类型需要请购安排并且表体需要安排
        .append(jointb + "." + BuyrTranTypeVO.BNEEDARRANGE + " = 'Y'")
        .append(" and ")
        .append(itemtb + "." + PraybillItemVO.BISARRANGE + " = 'Y'))");
    return sql.toString();
  }

  /**
   * 获得表连接sql
   * 
   * @param headtb
   * @param jointb
   * @return
   */
  private static String getJoin(String headtb, String jointb) {
    String join =
        " join " + PUEntity.M20_TRANTYPE_TABLE + jointb + " on " + headtb + "."
            + PraybillHeaderVO.CTRANTYPEID + " =" + jointb + "."
            + BuyrTranTypeVO.CTRANTYPEID;
    return join;
  }

}
