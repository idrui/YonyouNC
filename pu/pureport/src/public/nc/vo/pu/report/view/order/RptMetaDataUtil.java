package nc.vo.pu.report.view.order;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import nc.pub.smart.data.DataSet;
import nc.pub.smart.metadata.Field;
import nc.pub.smart.metadata.MetaData;
import nc.vo.pub.IAttributeMeta;
import nc.vo.pub.IColumnMeta;
import nc.vo.pub.JavaType;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.model.meta.table.Column;
import nc.vo.pubapp.pattern.model.meta.table.Table;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.report.SCMReportTempTableUtil;

/**
 * @since 6.0
 * @version 2011-9-16 下午01:38:07
 * @author wuxla
 */

public class RptMetaDataUtil {
  public static String buildTempTable(String tablename, MetaData metaData) {
    Table table = new Table(tablename);
    List<String> keyList = new ArrayList<String>();
    for (Field field : metaData.getFields()) {
      String key = field.getFldname();
      if (keyList.contains(key)) {
        continue;
      }
      keyList.add(key);
      Column column = new Column(table, field.getFldname());
      // 设置长度和精度
      column.setLength(field.getPrecision());
      column.setPrecision(field.getScale());
      // 设置字段类型
      column.setSqlType(field.getDbColumnType());
      column.setNullable(true);
      column.setLabel(field.getFldname());
      table.add(column);
    }
    String tableName = new SCMReportTempTableUtil().createTempTable(table);
    return tableName;
  }

  public static DataSet getDataSet(MetaData metaData, AbstractDataView[] views,
      String[] attr) {
    DataSet ds = null;
    int row = 0;
    if (views != null) {
      row = views.length;
    }
    Object[][] dataObject = new Object[row][attr.length];
    if (null == views || 0 == views.length) {
      return new DataSet(metaData, dataObject);
    }
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < attr.length; j++) {
        dataObject[i][j] = views[i].getAttributeValue(attr[j]);
      }
    }
    ds = new DataSet(metaData, dataObject);
    return ds;
  }

  public static MetaData getMetaData(DataViewMeta viewMeta) {
    List<Field> fields = new ArrayList<Field>();
    for (String attName : viewMeta.getAttributeNames()) {
      IAttributeMeta attrmeta = viewMeta.getAttribute(attName);
      if (null == attrmeta) {
        continue;
      }
      Field field = new Field();
      field.setFldname(attName);
      // 基于元数据的
      IColumnMeta colmeta = attrmeta.getColumn();
      if (colmeta != null) {
        field.setDbColumnType(colmeta.getSqlType());
        field.setPrecision(colmeta.getLength());
        field.setScale(colmeta.getPrecision());
        fields.add(field);
        continue;
      }

      // 自己新增的
      JavaType javatype = attrmeta.getJavaType();
      if (JavaType.String.equals(javatype)) {
        field.setDbColumnType(Types.VARCHAR);
        field.setPrecision(255);
      }
      else if (JavaType.UFDouble.equals(javatype)) {
        field.setDbColumnType(Types.DECIMAL);
        field.setPrecision(28);
        field.setScale(8);
      }
      else if (JavaType.UFDate.equals(javatype)) {
        field.setDbColumnType(Types.VARCHAR);
        field.setPrecision(19);
      }
      else if (JavaType.Integer.equals(javatype)) {
        field.setDbColumnType(Types.INTEGER);
      }
      else if (JavaType.UFBoolean.equals(javatype)) {
        field.setDbColumnType(Types.BOOLEAN);
      }
      else if (JavaType.UFFlag.equals(javatype)) {
        field.setDbColumnType(Types.INTEGER);
      }
      else if (JavaType.UFDateTime.equals(javatype)) {
        field.setDbColumnType(Types.VARCHAR);
        field.setPrecision(19);
      }
      else if (JavaType.BigDecimal.equals(javatype)) {
        field.setDbColumnType(Types.DECIMAL);
        field.setPrecision(28);
        field.setScale(8);
      }
      fields.add(field);
    }
    return new MetaData(fields.toArray(new Field[fields.size()]));
  }

  public static String getQuerySql(String tablename, String[] attrs,
      String order) {
    SqlBuilder sql = new SqlBuilder();
    sql.append("select ");
    for (String attr : attrs) {
      sql.append(attr);
      sql.append(",");
    }
    sql.deleteLastChar();
    sql.append(" from " + tablename);
    if (order != null) {
      sql.append(" order by " + order);
    }
    return sql.toString();
  }

}
