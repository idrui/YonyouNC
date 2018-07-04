package nc.impl.pu.report.journal;

import nc.vo.pu.report.queryinfo.journal.JournalConstant;
import nc.vo.pub.query.ConditionVO;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/*
 * ���ϻ������� * ���� * �ɹ���֯ * �ɹ����� * �ɹ�Ա *
 *  �·� * ���� ��Ӧ�� ����������  * ������˰���� * ������˰���* 
 *  ����˰�� ������˰�ϼ� �������ٷֱ�(%) ������ ������ٷֱ�(%)  
 *  �˻������� ����������  �������  �˿�������  ���������  �����  ��Ʊ������
 *    ��Ʊ��˰����  ��Ʊ��˰��� ��Ʊ˰��,��Ʊ��˰�ϼ�,��Ʊ���ٷֱ�(%)
 *    ,��������,���㵥��, ������
 * ���ܿھ�  ��// ���ϻ������� // ���� // �ɹ���֯ // �ɹ����� // �ɹ�Ա// �·� ���� ��Ӧ��
 */
public class SettleSqlGetter implements ISqlGetter {
  private ConditionVO[] conditions;

  // po_settlebill
  private String Join_27 =
      "  from po_invoice po_invoice   INNER JOIN po_invoice_b po_invoice_b  "
          + "ON po_invoice.pk_invoice = po_invoice_b.pk_invoice and po_invoice_b.dr=0 "
          + " inner join po_settlebill_b po_settlebill_b "
          + " on po_invoice_b.pk_invoice_b = po_settlebill_b.pk_invoice_b and po_settlebill_b.dr=0 "
          + " inner join po_settlebill po_settlebill "
          + " on po_settlebill.pk_settlebill = po_settlebill_b.pk_settlebill"
          + " inner join bd_material bd_material "
          + " on po_settlebill_b.pk_material = bd_material.pk_material "
          + " inner join bd_marbasclass bd_marbasclass "
          + " on bd_marbasclass.pk_marbasclass = bd_material.pk_marbasclass "
          + " inner join BD_SUPPLIER BD_SUPPLIER "
          + " on BD_SUPPLIER.pk_supplier = po_settlebill_b.pk_supplier ";

  private String SELECT_Feild = "select "
      + " BD_MARBASCLASS.pk_marbasclass pk_marbasclass , "
      + " BD_MATERIAL.pk_material pk_material,"
      + " po_settlebill.pk_org_v pk_org_v,"
      + " po_settlebill_b.PK_DEPT_V pk_dept_v , "
      + " po_settlebill_b.pk_psndoc pk_psndoc,"
      + " substring(po_settlebill.dbilldate,6,2) monthvalue,"
      + " substring(po_settlebill.dbilldate,1,10) dbilldate, "
      + " po_settlebill_b.PK_SUPPLIER PK_SUPPLIER, "
      + " po_settlebill.ccurrencyid corigcurrencyid ," // ����
      + " 0 ordernnum, "
      + " 0 ordernmny , " + " 0 orderntax , 0 nacccancelinvmny ,"
      + " 0 arrivennum," + " 0 arrivebacknnum," + " 0 arrivenmny ,"
      + " 0 purnnum ," + " 0 purbacknnum ," + " 0 purnmny ,"
      + " 0 invoicennum ," + " 0 invoicenmny ," + " 0 invoicetax ,"
      + " po_settlebill_b.nsettlenum settlennum ,"
      + " po_settlebill_b.nmoney settlenmny ";

  private String whereConds;

  public SettleSqlGetter(String whereConds, ConditionVO[] conditions) {
    this.whereConds = whereConds;
    this.conditions = conditions;
  }

  @Override
  public String getSql(String[] groups) {
    SqlBuilder sqlSelect = new SqlBuilder();
    sqlSelect.append(this.SELECT_Feild);
    sqlSelect.append(this.Join_27);
    sqlSelect.append(" where ");
    sqlSelect.append(this.replaceWhere());
    return sqlSelect.toString();
  }

  /**
   * �滻sql
   * 
   * @return
   */
  private String replaceWhere() {

    String whereSql =
        this.whereConds.replace(JournalConstant.DBILLDATE,
            "po_settlebill.dbilldate");// ��������
    whereSql =
        whereSql.replace(JournalConstant.PK_ORG, "po_invoice.pk_purchaseorg");// �ɹ���֯
    whereSql =
        whereSql.replace(JournalConstant.PK_STOCKORG, "po_invoice.pk_stockorg");// �滻�ջ������֯
    whereSql =
        whereSql.replace(JournalConstant.PK_PSNDOC, "po_invoice.pk_bizpsn");// ҵ��Ա
    whereSql = whereSql.replace(JournalConstant.PK_DEPT, "po_invoice.pk_dept");// ����
    whereSql = whereSql.replace(JournalConstant.VFREE, "po_invoice_b.vfree");// ���ɸ�������
    whereSql =
        whereSql.replace(JournalConstant.CPRODUCTORID,
            "po_invoice_b.cproductorid");// ��������
    whereSql =
        whereSql.replace(JournalConstant.CPROJECTID, "po_invoice_b.cprojectid");// ��Ŀ
    whereSql =
        whereSql.replace(JournalConstant.PK_GROUP, "po_invoice_b.pk_group");// ����

    return whereSql;
  }

}
