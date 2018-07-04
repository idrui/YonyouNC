package nc.impl.pu.report.supplierest.sqlbuilder;

import nc.impl.pu.report.supplierestdetail.sqlbuilder.AbstractEstDetailSqlBuilder;
import nc.vo.pu.report.queryinfo.supplierest.PuSupplierEstQryInfoPara;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * ��Ӧ���ݹ����ĳ�����
 * 
 * @since 6.0
 * @version 2011-5-21 ����10:17:04
 * @author �����
 */

public abstract class AbstractSupplierEstSqlBuilder extends
    AbstractEstDetailSqlBuilder {

  /**
   * @param para
   */
  public AbstractSupplierEstSqlBuilder(PuSupplierEstQryInfoPara para) {
    super(para);
  }

  /**
   * ͳ�Ʒ���δ����ʱ���������������
   * 
   * @return
   */
  public String getFeeUnclashAddWhere() {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" (");
    // ����δ������߽����˵�δ���ɱ�
    sql.appendIDIsNull("fe.pk_firstsettle");//
    sql.append("     or not exists ");
    sql.append("      ( select 1 from po_settlebill_b sb2 ");
    sql.append("         inner join po_settlebill sh2 on sb2.pk_settlebill=sh2.pk_settlebill");
    sql.append("         where sb2.dr=0 and sh2.dr=0 and sb2.pk_settlebill_b = fe.pk_firstsettle_b  ");
    sql.append("          and isnull(sh2.btoia,'N')='Y'");
    // ����֮ǰ����Ĳ�ͳ��
    sql.append("          and sh2.dbilldate", "<", this.getPara()
        .getBegindate().toString());
    sql.append("      )");
    sql.append("  )");
    return sql.toString();
  }

  /**
   * �����ݹ�ʱ��where��ƴ���õ����Ϻ͹�Ӧ��
   * 
   * @return
   */
  @Override
  protected String getFeeEstCommonWhere() {
    String feeWhere = this.getGoodCommonWhere();
    feeWhere = feeWhere.replace("eb.pk_supplier", "fe.pk_supplier");
    // �����ݹ��滻
    feeWhere = feeWhere.replace("eb.pk_srcmaterial", "fe.pk_srcfeematerial");
    return feeWhere;
  }

}
