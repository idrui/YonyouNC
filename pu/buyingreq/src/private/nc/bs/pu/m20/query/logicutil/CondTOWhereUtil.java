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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�빺����Ԫ���ݲ�ѯ����������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author liuchx
 * @time 2010-9-1 ����12:01:59;
 */
public class CondTOWhereUtil {

  /**
   * ���������������Ƿ����δ��������Ϊ0���빺���д���
   * <p>
   * <b>����˵��</b>
   * 
   * @param cond
   * @param where
   * @param itemtb
   *          <p>
   * @since 6.0
   * @author liuchx
   * @time 2010-9-1 ����11:57:00
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
   * ���������������빺���Ŵ���
   * 
   * @param psor
   * @param sql
   * @param headtb
   * @param itemtb
   * @since 6.36
   * @author luojw
   * @time 2015-1-29 ����11:17:00
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
   * ���������������빺���Ŵ���
   * 
   * @param psor
   * @param sql
   * @param headtb
   * @param itemtb
   * @since 6.36
   * @author luojw
   * @time 2015-1-29 ����11:17:00
   */
  public static void buildIsarrange(StringBuffer sql, String headtb,
      String itemtb) {
    String jointb = " tr";
    sql.insert(sql.indexOf(" where"), CondTOWhereUtil.getJoin(headtb, jointb));
    sql.append(CondTOWhereUtil.getFilter(itemtb, jointb));
  }

  /**
   * ���������������빺���Ŵ���
   * 
   * @param psor
   * @param sql
   * @param headtb
   * @param itemtb
   * @since 6.36
   * @author luojw
   * @time 2015-1-29 ����11:17:00
   */
  public static void buildIsarrange(StringBuilder sql, String headtb,
      String itemtb) {
    String jointb = " tr";
    sql.insert(sql.indexOf(" where"), CondTOWhereUtil.getJoin(headtb, jointb));
    sql.append(CondTOWhereUtil.getFilter(itemtb, jointb));
  }

  /**
   * ���������������Ƿ��й�Ӧ����Ч�۸���
   * <p>
   * <b>����˵��</b>
   * 
   * @param cond
   * @param where
   * @param itemtb
   *          <p>
   * @since 6.0
   * @author liuchx
   * @time 2010-9-1 ����11:57:00
   */
  public static void buildIseffectprice(ConditionVO cond, StringBuffer where,
      String itemtb) {
    if (UFBoolean.TRUE.toString().equals(cond.getValue())) {

    }
  }

  /**
   * ���������������Ƿ��Ѿ�����ѯ���۵�����
   * <p>
   * <b>����˵��</b>
   * 
   * @param cond
   * @param where
   * @param itemtb
   *          <p>
   * @since 6.0
   * @author liuchx
   * @time 2010-9-1 ����11:57:00
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
   * ���������������Ƿ��Ѿ����ɲɹ���������
   * <p>
   * <b>����˵��</b>
   * 
   * @param cond
   * @param where
   * @param itemtb
   *          <p>
   * @since 6.0
   * @author liuchx
   * @time 2010-9-1 ����11:57:00
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
   * ���������������Ƿ��Ѿ����ɼ۸�������
   * <p>
   * <b>����˵��</b>
   * 
   * @param cond
   * @param where
   * @param itemtb
   *          <p>
   * @since 6.0
   * @author liuchx
   * @time 2010-9-1 ����11:57:00
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
   * ���������������Ƿ��Ѿ����ɺ�ͬ����
   * <p>
   * <b>����˵��</b>
   * 
   * @param cond
   * @param where
   * @param itemtb
   *          <p>
   * @since 6.0
   * @author liuchx
   * @time 2010-9-1 ����11:57:00
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
   * ���Ҫ��ӵĽ��������߼�
   * 
   * @param itemtb
   * @param jointb
   * @return
   */
  private static String getFilter(String itemtb, String jointb) {
    StringBuilder sql = new StringBuilder();
    sql.append(" and (")
        // �������Ͳ���Ҫ�빺����
        .append(jointb + "." + BuyrTranTypeVO.BNEEDARRANGE + " = 'N'")
        .append(" or (")
        // ����������Ҫ�빺���Ų��ұ�����Ҫ����
        .append(jointb + "." + BuyrTranTypeVO.BNEEDARRANGE + " = 'Y'")
        .append(" and ")
        .append(itemtb + "." + PraybillItemVO.BISARRANGE + " = 'Y'))");
    return sql.toString();
  }

  /**
   * ��ñ�����sql
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
