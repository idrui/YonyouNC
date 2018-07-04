package nc.vo.pu.ec;

import nc.vo.pu.pub.constant.PUQueryConst;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QueryCondition;

import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.0
 * @version 2011-5-16 ����10:35:30
 * @author wuxla
 */

public class QueryForECUtil {
  public static void checkDateCond(BasePUQueryECCondVO vo) {
    QueryCondition billdateCond = vo.getBilldateCond();
    if (null == billdateCond) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004000_0", "04004000-0057")/*
                                                                   * @res
                                                                   * "������������Ϊ��"
                                                                   */);
      return;
    }
    Object[] values = billdateCond.getValues();
    if (ArrayUtils.isEmpty(values)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004000_0", "04004000-0057")/*
                                                                   * @res
                                                                   * "������������Ϊ��"
                                                                   */);
    }
    if (null == values[0]) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004000_0", "04004000-0058")/*
                                                                   * @res
                                                                   * "��ʼ���ڲ���Ϊ��"
                                                                   */);
    }
    if (null == values[1]) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004000_0", "04004000-0059")/*
                                                                   * @res
                                                                   * "�������ڲ���Ϊ��"
                                                                   */);
    }
  }

  public static void checkMatAndSupplierCond(String[] pk_materials,
      String pk_supplier, UFDate beginDate, UFDate endDate) {
    // ��ʼ����
    if (beginDate == null) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004000_0", "04004000-0134")/*
                                                                   * @res
                                                                   * "��ʼ���ڲ���Ϊ��"
                                                                   */);
    }
    // ��������
    if (endDate == null) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004000_0", "04004000-0135")/*
                                                                   * @res
                                                                   * "�������ڲ���Ϊ��"
                                                                   */);
    }
    // ��Ӧ������
    if (null == pk_supplier) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004000_0", "04004000-0060")/*
                                                                   * @res
                                                                   * "��Ӧ�̲���Ϊ��"
                                                                   */);
      return;
    }
    // ��Ʒ����
    if (ArrayUtils.isEmpty(pk_materials) || null == pk_materials[0]) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004000_0", "04004000-0061")/*
                                                                   * @res
                                                                   * "��Ʒ����Ϊ��"
                                                                   */);
    }
  }

  public static void checkSupplierCond(BasePUQueryECCondVO vo) {
    // ��Ӧ������
    QueryCondition supplierCond = vo.getSupplierCond();
    if (null == supplierCond) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004000_0", "04004000-0060")/*
                                                                   * @res
                                                                   * "��Ӧ�̲���Ϊ��"
                                                                   */);
      return;
    }
    // ����
    Object[] values = supplierCond.getValues();
    if (ArrayUtils.isEmpty(values) || null == values[0]) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004000_0", "04004000-0060")/*
                                                                   * @res
                                                                   * "��Ӧ�̲���Ϊ��"
                                                                   */);
    }
  }

  public static String getDateWherePart(String attr, QueryCondition billdateCond) {
    SqlBuilder wherePart = new SqlBuilder();
    Object[] values = billdateCond.getValues();
    if (values[0] != null) {
      wherePart.append(" and " + attr, ">=", values[0].toString());
    }
    if (values[1] != null) {
      wherePart.append(" and " + attr, "<=", values[1].toString());
    }
    return wherePart.toString();
  }

  public static String getPubWherePart(String hname, BasePUQueryECCondVO condVO) {
    SqlBuilder wherePart = new SqlBuilder();
    QueryCondition supplierCond = condVO.getSupplierCond();
    wherePart.append(" and " + hname + "." + PUQueryConst.SUPPLIER,
        supplierCond.getValues()[0].toString());
    QueryCondition billdateCond = condVO.getBilldateCond();
    if (billdateCond != null) {
      wherePart.append(QueryForECUtil.getDateWherePart(hname + "."
          + PUQueryConst.DBILLDATE, billdateCond));
    }
    return wherePart.toString();
  }

}
