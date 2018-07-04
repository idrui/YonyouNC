package nc.vo.pu.pub.sql;

import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nc.bs.ic.pub.db.SqlIn;
import nc.bs.ic.pub.env.ICBSContext;
import nc.itf.bd.pub.IBDMetaDataIDConst;
import nc.jdbc.framework.SQLParameter;
import nc.jdbc.framework.util.DBConsts;
import nc.md.model.type.IType;
import nc.pubitf.bd.accessor.GeneralAccessorFactory;
import nc.pubitf.bd.accessor.IGeneralAccessor;
import nc.vo.bd.accessor.IBDData;
import nc.vo.bd.material.marbasclass.MarBasClassVO;
import nc.vo.ic.icreport.FilterItemEqOrIn;
import nc.vo.ic.pub.util.CollectionUtils;
import nc.vo.ic.pub.util.StringUtil;
import nc.vo.ic.pub.util.VOEntityUtil;
import nc.vo.ic.pub.util.ValueCheckUtil;
import nc.vo.pub.IAttributeMeta;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.JavaType;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFTime;
import nc.vo.pub.query.ConditionVO;

/**
 * <b> 拼SQL的一些通用工具方法 </b>
 * <p>
 * </p>
 * 创建日期:2009-11-09 10:21:14
 * 
 * @author
 * @version NC60
 */

public class PUSqlUtil {

  public static final String Fun_ABS = "abs";

  // sql函数 COALESCE(num,0.0)
  public static final String Fun_COALESCE = "COALESCE";

  public static final String Fun_ISNULL = "isnull";
  
  public static final String Fun_SUBSTRING = "substring";

  public static void addSqlIntParam(Object value, SQLParameter sqlparam) {
    if (value == null || value instanceof String
        && ((String) value).trim().length() == 0) {
      sqlparam.addNullParam(java.sql.Types.INTEGER);
    }
    else {
      if (value instanceof Integer) {
        sqlparam.addParam(((Integer) value).intValue());
      }
      else if (value instanceof Long) {
        sqlparam.addParam(((Long) value).intValue());
      }
      else if (value instanceof Short) {
        sqlparam.addParam(((Short) value).intValue());
      }
      else if (value instanceof Byte) {
        sqlparam.addParam(((Byte) value).intValue());
      }
      else {
        sqlparam.addParam(new Integer(value.toString()).intValue());
      }
    }
  }

  public static void addSqlParam(IAttributeMeta attr, Object value,
      SQLParameter sqlparam) throws java.sql.SQLException {
    if (sqlparam == null) {
      return;
    }
    JavaType jtype = attr.getJavaType();
    if (jtype == JavaType.Integer || jtype == JavaType.UFFlag) {
      PUSqlUtil.addSqlIntParam(value, sqlparam);
    }
    else if (jtype == JavaType.UFDate || jtype == JavaType.UFDateTime
        || jtype == JavaType.UFTime) {
      PUSqlUtil.addSqlUFDateOrTimeParam(value, sqlparam);
    }
    else if (jtype == JavaType.String || jtype == JavaType.UFStringEnum) {
      PUSqlUtil.addSqlStrParam(attr, value, sqlparam);
    }
    else if (jtype == JavaType.UFBoolean) {
      PUSqlUtil.addSqlUFBooleanParam(value, sqlparam);
    }
    else if (jtype == JavaType.UFDouble || jtype == JavaType.BigDecimal) {
      PUSqlUtil.addSqlUFDoubleParam(value, sqlparam);
    }
    else {
      throw new SQLException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4008001_0", "04008001-0644")/*
                                                    * @res
                                                    * "程序错误：元数据中出现了未预定义的JAVA-NC数据类型。"
                                                    */);
    }
  }

  public static void addSqlStrParam(IAttributeMeta attr, Object val,
      SQLParameter sqlparam) {
    Object value = val;
    if (value == null || value.getClass() == String.class
        && ((String) value).trim().length() == 0) {
      value = PUSqlUtil.convertNull(attr);
    }
    if (value == null) {
      sqlparam.addNullParam(java.sql.Types.CHAR);
    }
    else {
      if (value.getClass() == String.class) {
        sqlparam.addParam((String) value);
      }
      else {
        sqlparam.addParam(value.toString());
      }
    }
  }

  public static void addSqlUFBooleanParam(Object value, SQLParameter sqlparam) {
    if (value == null || value instanceof String
        && ((String) value).trim().length() == 0) {
      sqlparam.addNullParam(java.sql.Types.CHAR);
    }
    else {
      if (value instanceof String) {
        sqlparam
            .addParam(((String) value).trim().toUpperCase().equals("Y") ? "Y"
                : "N");
      }
      else if (value instanceof Boolean) {
        sqlparam.addParam(((Boolean) value).booleanValue() ? "Y" : "N");
      }
      else {
        sqlparam.addParam(value.toString());
      }
    }
  }

  public static void addSqlUFDateOrTimeParam(Object value, SQLParameter sqlparam) {
    if (value == null) {
      sqlparam.addNullParam(java.sql.Types.CHAR);
    }
    else {
      if (value instanceof UFDate) {
        sqlparam.addParam((UFDate) value);
      }
      else if (value instanceof UFDateTime) {
        sqlparam.addParam((UFDateTime) value);
      }
      else if (value instanceof UFTime) {
        sqlparam.addParam((UFTime) value);
      }
      else {
        sqlparam.addParam(value.toString());
      }
    }
  }

  public static void addSqlUFDoubleParam(Object value, SQLParameter sqlparam) {
    if (value == null || value instanceof String
        && ((String) value).trim().length() == 0) {
      sqlparam.addNullParam(java.sql.Types.NUMERIC);
    }
    else {
      if (value instanceof nc.vo.pub.lang.UFDouble) {
        sqlparam.addParam((nc.vo.pub.lang.UFDouble) value);
      }
      else if (value instanceof String) {
        sqlparam.addParam(new nc.vo.pub.lang.UFDouble((String) value));
      }
      else if (value instanceof Double) {
        sqlparam.addParam(new nc.vo.pub.lang.UFDouble(((Double) value)
            .doubleValue()));
      }
      else if (value instanceof Float) {
        sqlparam.addParam(new nc.vo.pub.lang.UFDouble(((Float) value)
            .doubleValue()));
      }
      else if (value instanceof java.math.BigDecimal) {
        sqlparam.addParam(value);
      }
      else {
        sqlparam.addParam(new nc.vo.pub.lang.UFDouble(value.toString()));
      }
    }
  }

  /**
   * @return boolean
   * @param java .lang.String
   */
  public static String andTowWhere(String swhere1, String swhere2) {
    if (swhere1 == null || swhere1.trim().length() <= 0) {
      return swhere2;
    }
    if (swhere2 == null || swhere2.trim().length() <= 0) {
      return swhere1;
    }
    if (PUSqlUtil.isStartWithAndOr(swhere2)) {
      return swhere1 + " " + swhere2;
    }
    return swhere1 + " and " + swhere2;
  }

  /**
   * @return boolean
   * @param java .lang.String
   */
  public static void andTowWhere(StringBuilder swhere1, String swhere2) {
    if (swhere1 == null) {
      return;
    }
    if (swhere2 == null || swhere2.trim().length() <= 0) {
      return;
    }
    if (PUSqlUtil.isStartWithAndOr(swhere2)) {
      if (swhere1.toString().trim().length() <= 0) {
        swhere1.append(" 1=1 " + swhere2);
      }
      else {
        swhere1.append(" " + swhere2);
      }
    }
    else {
      if (swhere1.toString().trim().length() <= 0) {
        swhere1.append(" " + swhere2);
      }
      else {
        swhere1.append(" and " + swhere2);
      }
    }
  }

  /**
   * coalesce(exp ,'~')
   */
  public static String coalesceConstNull(String exp) {
    return PUSqlUtil.Fun_COALESCE + "(" + exp + ",'" + DBConsts.NULL_WAVE + "') ";
  }

  public static Object convertNull(IAttributeMeta attr) {
    Object value = null;
    if (PUSqlUtil.isNullAsWave(attr)) {
      value = DBConsts.NULL_WAVE;
    }
    return value;
  }

  /**
   * 物理删除sql
   */
  public static String getDeleteSql(String table, String[] whereFields,
      String where) {
    StringBuilder sql = new StringBuilder(" delete from ");
    sql.append(table);
    sql.append(" where ");
    boolean bflag = false;
    if (whereFields != null && whereFields.length > 0) {
      int count = 0;
      for (String field : whereFields) {
        if (count > 0) {
          sql.append(" and ");
        }
        sql.append(field + " = ");
        sql.append("? ");
        count++;
      }
      bflag = true;
    }
    if (where != null) {
      if (!bflag) {
        sql.append(where);
      }
      else {
        if (PUSqlUtil.isStartWithAndOr(where)) {
          sql.append(where);
        }
        else {
          sql.append(" and " + where);
        }
      }
    }
    return sql.toString();
  }

  public static String getFun_ABS(String field) {
    return PUSqlUtil.Fun_ABS + "(" + field + ")";
  }

  /**
   *
   */
  public static String getFun_COALESCE(String field, String defaultvalue) {
    return PUSqlUtil.Fun_COALESCE + "(" + field + "," + defaultvalue + ")";
  }

  /**
   * case when ?? then e1 else e2 end
   */
  public static String getFun_IF(String ifexp, String thenexp, String elseexp) {
    return " case when " + ifexp + " then " + thenexp + " else " + elseexp
        + " end ";
  }
  
  public static String getFun_SUBSTRING(String field, int bindex, int eindex) {
	    return PUSqlUtil.Fun_SUBSTRING + "(" + field + "," + bindex + "," + eindex +")";
	  }

  /**
   * 返回isnull函数字符串，针对字符条件
   * 
   * @param prefix
   * @param columnName
   * @return
   */
  public static String getFun_ISNULL_For_CASE(String prefix, String columnName) {
    if (StringUtil.isSEmptyOrNull(prefix)) {
      return "COALESCE(" + columnName + ",'" + DBConsts.NULL_WAVE + "') = '"
          + DBConsts.NULL_WAVE + "' ";
    }
    return "COALESCE(" + prefix + "." + columnName + ",'" + DBConsts.NULL_WAVE
        + "') = '" + DBConsts.NULL_WAVE + "' ";
  }

  /**
   * 返回isnull函数字符串，针对数值条件
   * 
   * @param prefix
   * @param columnName
   * @return
   */
  public static String getFun_ISNULL_For_Number(String prefix, String columnName) {
    if (StringUtil.isSEmptyOrNull(prefix)) {
      return PUSqlUtil.Fun_ISNULL + "(" + columnName + ",0.0) = 0.0 ";
    }
    return PUSqlUtil.Fun_ISNULL + "(" + prefix + "." + columnName
        + ",0.0) = 0.0 ";
  }

  /**
   * 返回isnull函数字符串，针对字符条件
   * 
   * @param prefix
   * @param columnName
   * @return
   */
  public static String getFun_ISNULL_For_String(String prefix, String columnName) {
    if (StringUtil.isSEmptyOrNull(prefix)) {
      return columnName + " = '" + DBConsts.NULL_WAVE + "' ";
    }
    return prefix + "." + columnName + " = '" + DBConsts.NULL_WAVE + "' ";
  }

  /**
   * 如： insert into
   * ic_onhanddimb(pk_onhanddimb,cvendorid,cvmivenderid,vhashcode) ( select
   * 'ybybyb',cvendorid,'kkkkkk',vhashcode from ic_onhanddimb where
   * pk_onhanddimb = '0001ZZ1000000000SUUZ' )
   */
  public static String getInsertForSelectSql(String inserttable,
      String insertfields, String selectsql) {
    StringBuilder sql = new StringBuilder(" insert into ");
    sql.append(inserttable);
    sql.append("( ");
    sql.append(insertfields);
    sql.append(" ) ");
    sql.append(" ( ");
    sql.append(selectsql);
    sql.append(" ) ");
    return sql.toString();
  }

  /**
   *
   */
  public static String getInsertSql(String table, String[] fields) {
    StringBuilder sql = new StringBuilder(" insert into ");
    sql.append(table);
    sql.append(" (");
    StringBuilder valuesql = new StringBuilder(" values(");
    for (int i = 0; i < fields.length; i++) {
      if (i > 0) {
        sql.append(",");
        valuesql.append(",");
      }
      sql.append(fields[i]);
      valuesql.append("?");
    }
    sql.append(") ");
    valuesql.append(") ");
    sql.append(valuesql.toString());
    return sql.toString();
  }

  /**
   * 逻辑删除sql
   */
  public static String getLogDeleteSql(String table, String[] whereFields,
      String where) {
    StringBuilder sql = new StringBuilder(" update ");
    sql.append(table);
    sql.append(" set dr=1 ");
    sql.append(" where ");
    int count = 0;
    for (String field : whereFields) {
      if (count > 0) {
        sql.append(" and ");
      }
      sql.append(field + " = ");
      sql.append("? ");
      count++;
    }
    if (where != null) {
      if (PUSqlUtil.isStartWithAndOr(where)) {
        sql.append(where);
      }
      else {
        sql.append(" and " + where);
      }
    }
    return sql.toString();
  }

  /**
   *
   */
  public static String getSelectFieldString(String table, String[] fields) {
    StringBuilder sf = new StringBuilder(" ");
    for (int i = 0; i < fields.length; i++) {
      if (i > 0) {
        sf.append(",");
      }
      if (!StringUtil.isSEmptyOrNull(table)) {
        sf.append(table + ".");
      }
      sf.append(fields[i]);
    }
    sf.append(" ");
    return sf.toString();
  }

  /**
   *
   */
  public static String getSelectSql(IVOMeta meta, String where) {
    return PUSqlUtil.getSelectSql(VOEntityUtil.getDBFieldAttributeMetas(meta,
        null).toArray(new String[0]), meta, where);
  }

  /**
   *
   */
  public static String getSelectSql(String[] selfields, IVOMeta meta,
      String where) {
    String[] selectfields = selfields;
    if (selectfields == null || selectfields.length <= 0) {
      List<String> lfs = VOEntityUtil.getDBFieldNames(meta, null);
      selectfields = lfs.toArray(new String[lfs.size()]);
    }
    StringBuilder bf = new StringBuilder(" select ");
    for (int i = 0; i < selectfields.length; i++) {
      if (i > 0) {
        bf.append(",");
      }
      bf.append(selectfields[i]);
    }
    bf.append(" from ");
    bf.append(meta.getPrimaryAttribute().getColumn().getTable().getName());
    bf.append(" where ");
    if (PUSqlUtil.isStartWithAndOr(where)) {
      bf.append(" 1=1 " + where);
    }
    else {
      bf.append(" " + where);
    }
    return bf.toString();
  }

  /*
   *
   */
  public static SQLParameter getSQLParameter(IAttributeMeta[] types,
      Object[] values) throws java.sql.SQLException {
    if (types == null || values == null) {
      return null;
    }
    SQLParameter ret = new SQLParameter();
    for (int i = 0; i < types.length; i++) {
      PUSqlUtil.addSqlParam(types[i], values[i], ret);
    }
    return ret;
  }

  /**
   *
   */
  public static String getUpdateSql(String table, String[] updateFields,
      String[] updateRightValues, String[] whereFields,
      String[] whereRightValues, String where) {
    StringBuilder sql = new StringBuilder(" update ");
    sql.append(table);
    sql.append(" set ");
    int count = 0;
    boolean bflag = false;
    if (updateRightValues != null
        && updateRightValues.length == updateFields.length) {
      bflag = true;
    }
    for (String field : updateFields) {
      if (count > 0) {
        sql.append(",");
      }
      sql.append(field + " = ");
      if (bflag && updateRightValues != null
          && updateRightValues[count] != null) {
        sql.append(updateRightValues[count]);
      }
      else {
        sql.append("? ");
      }
      count++;
    }
    sql.append(" where ");
    if (whereFields != null && whereFields.length > 0) {
      bflag = false;
      if (whereRightValues != null
          && whereRightValues.length == whereFields.length) {
        bflag = true;
      }
      count = 0;
      for (String field : whereFields) {
        if (count > 0) {
          sql.append(" and ");
        }
        sql.append(field + " = ");
        if (bflag && whereRightValues != null
            && !StringUtil.isSEmptyOrNull(whereRightValues[count])) {
          sql.append(whereRightValues[count]);
        }
        else {
          sql.append("? ");
        }
        count++;
      }
    }
    if (where != null) {
      if (PUSqlUtil.isStartWithAndOr(where)) {
        sql.append(where);
      }
      else {
        sql.append(" and " + where);
      }
    }
    return sql.toString();
  }

  /**
   * is COALESCE(null)
   */
  public static String isCoalesceNull(String exp) {
    return PUSqlUtil.Fun_COALESCE + "(" + exp + ",'" + DBConsts.NULL_WAVE
        + "') = '" + DBConsts.NULL_WAVE + "' ";
  }

  /**
   * is not null
   */
  public static String isNotNull(String exp) {
    return exp + " <> '" + DBConsts.NULL_WAVE + "' ";
  }

  /**
   * is null
   */
  public static String isNull(String exp) {
    return exp + " = '" + DBConsts.NULL_WAVE + "' ";
  }

  public static boolean isNullAsWave(IAttributeMeta attr) {
    if (attr.getModelType() == IType.REF
        || attr.getModelType() == IType.TYPE_UFID) {
      return true;
    }
    else if (attr.getColumn() != null
        && attr.getColumn().getSqlType() == Types.VARCHAR) {
      int size = attr.getColumn().getLength();
      if (size == 20 || size == 101 || size == 36) {
        return true;
      }
    }
    return false;
  }

  /**
   * @return boolean
   * @param java .lang.String
   */
  public static boolean isStartWithAnd(String swhere) {
    if (swhere == null) {
      return false;
    }
    String trimwhere = swhere.trim();
    if (trimwhere.length() <= 0) {
      return false;
    }
    if (trimwhere.startsWith("and") || trimwhere.startsWith("AND")
        || trimwhere.startsWith("And")) {
      return true;
    }
    return false;
  }

  /**
   * @return boolean
   * @param java .lang.String
   */
  public static boolean isStartWithAndOr(String swhere) {
    return PUSqlUtil.isStartWithAnd(swhere) || PUSqlUtil.isStartWithOr(swhere);
  }

  /**
   * @return boolean
   * @param java .lang.String
   */
  public static boolean isStartWithOr(String swhere) {
    if (swhere == null) {
      return false;
    }
    String trimwhere = swhere.trim();
    if (trimwhere.length() <= 0) {
      return false;
    }
    if (trimwhere.startsWith("or") || trimwhere.startsWith("OR")
        || trimwhere.startsWith("Or")) {
      return true;
    }
    return false;
  }

  public static String replaceTableAliasForFrom(String srcStr, String srcAlias,
      String targetAlias) {
    if (srcStr == null || srcAlias == null || targetAlias == null) {
      return srcStr;
    }
    Pattern pt = Pattern.compile(srcAlias + "[\\.|\\s|,]?");
    Matcher mt = pt.matcher(srcStr);
    StringBuffer sf = new StringBuffer();
    int endpos = srcStr.length();
    boolean boneflag = false;
    while (mt.find()) {
      String path = mt.group();
      //
      if (!boneflag) {
        boneflag = true;
        if (mt.start() - 1 < 0) {
          continue;
        }
        String ssubstr =
            srcStr.substring(0, mt.start() - 1).toLowerCase().trim();
        if (ssubstr.length() == 0 || ssubstr.endsWith("from")
            || ssubstr.endsWith("join") || ssubstr.endsWith(",")) {
          continue;
        }
      }
      String stemp = " " + targetAlias;
      char endchar = path.charAt(path.length() - 1);
      // 替换到当前字符串
      if (endchar == '.') {
        stemp = stemp + ".";
        mt.appendReplacement(sf, stemp);
      }
      else if (endchar == ',') {
        stemp = stemp + ",";
        mt.appendReplacement(sf, stemp);
      }
      else if (endchar == ' ' || mt.end() == endpos) {
        stemp = stemp + " ";
        mt.appendReplacement(sf, stemp);
      }
    }
    // 追加后面部分字符串
    mt.appendTail(sf);
    return sf.toString();
  }

  public static String replaceTableAliasForWhere(String srcStr,
      String srcAlias, String targetAlias) {
    if (srcStr == null || srcAlias == null || targetAlias == null) {
      return srcStr;
    }
    return nc.vo.jcom.lang.StringUtil.replaceAllString(srcStr, srcAlias + ".",
        targetAlias + ".");
  }
  /**
   * 处理试图view的查询条件，物料基本分类特殊处理，查询该分类及所有下属分类物料
   * <p>
   * 
   * </p>
   * 
   * @param attrPath 物料-物料基本分类元数据路径如：cmaterialoid.pk_marbasclass
   * @param vos 查询条件
   */
  public static void  dealConsByMutiMaterialClass(PUBeanSqlView view,String attrPath, ConditionVO[] vos) {
    if(ValueCheckUtil.isNullORZeroLength(vos))
      return;
    String rfield = attrPath;
    List<ConditionVO> condList = new ArrayList<ConditionVO>();
    ConditionVO mvo = null;
    for(ConditionVO vo : vos){
      if(vo.getFieldCode().indexOf(MarBasClassVO.PK_MARBASCLASS)>-1){
        mvo = vo;
      }else{
        condList.add(vo);
      }
    }
    if(!ValueCheckUtil.isNullORZeroLength(condList))
     view.processFromAndWhere(CollectionUtils.listToArray(condList));
    if(mvo==null)
      return;
    if(StringUtil.isSEmptyOrNull(rfield))
    	rfield = mvo.getFieldCode();
    String[] pk_classes = queryMaterilClass(FilterItemEqOrIn.getValues(mvo));
    if (ValueCheckUtil.isNullORZeroLength(pk_classes)) {
      return;
    }
   
    view.addJoinByAttrPath(rfield, false);
    PUBeanPath path = new PUBeanPath(view.getBean(), rfield);
    String whereSql =
        SqlIn.formInSQLWithoutAnd(
            view.getAliasAndField(path.getParentPath(), path.getLastPath()),
            pk_classes);
    view.addWhere(whereSql);
  }
  
  /**
   * 添加非末级物料基本分类条件，查询本级及本级以下的物料基本分类
   * <p>
   * 不管isAutoJoinn是否预置，都会强制关联
   * </p>
   * 
   * @param attrPath 物料-物料基本分类元数据路径如：cmaterialoid.pk_marbasclass
   * @param pk_docs 物料基本分类PK
   */
  public static void  setMutiMaterialClass(PUBeanSqlView view,String attrPath, String[] pk_docs) {
    String[] pk_classes = queryMaterilClass(pk_docs);
    if (ValueCheckUtil.isNullORZeroLength(pk_classes)) {
      return;
    }
    view.addJoinByAttrPath(attrPath, false);
    PUBeanPath path = new PUBeanPath(view.getBean(), attrPath);
    String whereSql =
        SqlIn.formInSQLWithoutAnd(
        		view.getAliasAndField(path.getParentPath(), path.getLastPath()),
            pk_classes);
    view.addWhere(whereSql);
  }
  
  /**
   * 根据物料基本分类PK查询该分类所有下级物料基本分类PK
   * 
   * @param pk_docs
   */
  public static String[] queryMaterilClass(String[] pk_docs) {
    String pk_org = new ICBSContext().getPk_group();
    IGeneralAccessor ass =
        GeneralAccessorFactory.getAccessor(IBDMetaDataIDConst.MARBASCLASS);
    List<IBDData> bdDataList = new ArrayList<IBDData>();
    for (String pk_doc : pk_docs) {
      List<IBDData> dblist = ass.getChildDocs(pk_org, pk_doc, true);
      // 当物料分类为组织级的，用Pk_group查组织级的物料及其下级时，会查不到，应该根据自己所属的组织查询
      if (dblist == null || dblist.size() == 0) {
        IBDData bd = ass.getDocByPk(pk_doc);
        dblist = ass.getChildDocs(bd.getPk_org(), pk_doc, true);
      }
      bdDataList.addAll(dblist);
    }
    if (ValueCheckUtil.isNullORZeroLength(bdDataList)) {
      return pk_docs;
    }
    List<String> pkList = new ArrayList<String>();
    for (IBDData bdData : bdDataList) {
      pkList.add(bdData.getPk());
    }
    return pkList.toArray(new String[0]);
  }
  
}
