package nc.ui.pu.m23.refmodel;

import nc.ui.bd.ref.AbstractRefModel;
import nc.ui.pub.bill.BillCardPanel;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���������Զ������ϲ��գ������滻��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-3-8 ����10:41:10
 */
public class MaterialRefModel extends AbstractRefModel {

  private String commonSelect =
      " select bd_material.code code,bd_material.name name,bd_material.name2 name2,bd_material.name3 name3,bd_material.name4 name4,bd_material.name5 name5,bd_material.name6 name6,bd_material.version version,bd_material.pk_material pk_material";

  private String commonWhere =
      "UPPER(bd_material.fee)<>'Y' and UPPER(bd_material.discountflag)<>'Y' and bd_material.enablestate='2'";

  private MaterialRefModelHelper helper;

  private String tableName;

  public MaterialRefModel() {
    super();
    this.setMatchPkWithWherePart(true);
  }

  @Override
  public int getDefaultFieldCount() {
    return this.getFieldCode().length;
  }

  @Override
  public java.lang.String[] getFieldCode() {
    return new String[] {
      "mytable.code", "mytable.name", "mytable.version"
    };
  }

  @Override
  public java.lang.String[] getFieldName() {
    return new String[] {
      nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
          "04004040-0057")/* @res "�滻������" */,
      nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
          "04004040-0058")/* @res "�滻������" */,
      nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
          "04004040-0059")
    /* @res "�汾" */
    };
  }

  @Override
  public String[] getHiddenFieldCode() {

    return new String[] {
      "mytable.pk_material"
    };
  }

  @Override
  public String getOrderPart() {
    return null;
  }

  @Override
  public String getPkFieldCode() {
    return "mytable.pk_material";
  }

  @Override
  public String getRefTitle() {
    return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
        "04004040-0060")/* @res "�����滻������" */;
  }

  @Override
  public String getStrPatch() {
    return " distinct ";
  }

  @Override
  public String getTableName() {
    return this.tableName;
  }

  public void initialize(BillCardPanel card, int rowNo) {
    this.helper = new MaterialRefModelHelper(card, rowNo);

    String pk_mrl = this.helper.getSourceMaterialPK();
    String pk_stock = this.helper.getPk_stockorg();

    StringBuilder builder = new StringBuilder();
    builder.append(" (");
    if (StringUtils.isNotEmpty(this.helper.getSourceSubQuery())) {
      // ��Ӧ��Դ���ݵı�������
      builder.append(this.commonSelect);
      builder.append(" from bd_material ");
      builder.append(" where ");
      builder.append(this.commonWhere);
      builder.append(" and bd_material.pk_material in");
      builder.append(this.helper.getSourceSubQuery());
      builder.append(" union all ");
    }

    if (this.helper.isQueryAllMaterial()) {
      // �滻��
      builder.append(this.commonSelect);
      builder.append(" from bd_material ");
      builder.append(" where ");
      builder.append(this.commonWhere);
      builder.append(" and bd_material.pk_material in");
      builder.append("(");
      builder.append("select bd_materialrepl.pk_replace from");
      builder.append(" bd_material left outer join bd_materialrepl");
      builder.append(" on bd_materialrepl.pk_material=bd_material.pk_material");
      builder.append(" where bd_materialrepl.pk_material='" + pk_mrl + "'");
      // ������Ѿ��Ƶ��ƻ�ҳǩ��ʹ��pk_org�ˡ�
      builder.append(" and bd_materialrepl.pk_org='" + pk_stock + "'");
      builder.append(")");
      // ȫ������
      builder.append(" union all ");
      builder.append(this.commonSelect);
      builder.append(" from bd_material ");
      builder.append(" where ");
      builder.append(this.commonWhere);
    }
    else {
      // �滻��
      builder.append(this.commonSelect);
      builder.append(" from bd_material ");
      builder.append(" where ");
      builder.append(this.commonWhere);
      builder.append(" and bd_material.pk_material in");
      builder.append("(");
      builder.append("select bd_materialrepl.pk_replace from");
      builder.append(" bd_material left outer join bd_materialrepl");
      builder.append(" on bd_materialrepl.pk_material=bd_material.pk_material");
      builder.append(" where bd_materialrepl.pk_material='" + pk_mrl + "'");
      builder.append(" and bd_materialrepl.pk_org='" + pk_stock + "'");
      builder.append(")");
    }
    builder.append(" ) mytable");
    this.tableName = builder.toString();
  }

  @Override
  protected String getSql(String strPatch, String[] strFieldCode,
      String[] hiddenFields, String strTableName, String strWherePart,
      String strGroupField, String strOrderField) {
    int i;
    if (strTableName == null || ArrayUtils.isEmpty(strFieldCode)) {
      return null;
    }
    int iSelectFieldCount = strFieldCode.length;
    StringBuilder strSql = new StringBuilder();
    strSql.append("select " + strPatch + " ");
    for (i = 0; i < iSelectFieldCount; i++) {
      strSql.append(strFieldCode[i]);
      if (i < iSelectFieldCount - 1) {
        strSql.append(",");
      }
    }
    if (hiddenFields != null && hiddenFields.length > 0) {
      for (int k = 0; k < hiddenFields.length; k++) {
        if (hiddenFields[k] != null && hiddenFields[k].trim().length() > 0) {
          strSql.append(",");
          strSql.append(hiddenFields[k]);
        }
      }
    }
    // ����FROM�Ӿ�
    strSql.append(" from " + strTableName);
    // ����WHERE�Ӿ�
    String wherePart;
    if (strWherePart != null && strWherePart.trim().length() != 0) {
      wherePart = " where " + strWherePart + " ";
    }
    else {
      wherePart = " where 11=11 ";
    }
    strSql.append(" " + wherePart);
    // ����ģ��---���ǲ�����WherePart
    if (this.getBlurFields() != null
        && this.getFieldIndex(this.getBlurFields()[0]) >= 0
        && this.getBlurValue() != null) {
      if (this.getBlurValue().indexOf('*') != -1
          || this.getBlurValue().indexOf('?') != -1) {
        // Ԥ����(��ͨ���"*"��"?")
        strSql.append(" and ( " + this.getBlurFields()[0] + " like '");
        strSql.append(this.getBlurValue().replace('*', '%').replace('?', '_'));
        strSql.append("' )");
      }
    }

    // ����Group�Ӿ�
    if (strGroupField != null) {
      strSql.append("  group by  " + strGroupField);
    }

    // ����ORDER�Ӿ�
    if (strOrderField != null) {
      strSql.append(" order by " + strOrderField);
    }

    return strSql.toString();
  }
}
