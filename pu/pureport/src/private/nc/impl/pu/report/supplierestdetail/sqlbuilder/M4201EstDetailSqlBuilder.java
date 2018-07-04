package nc.impl.pu.report.supplierestdetail.sqlbuilder;

import nc.impl.pu.report.supplierestdetail.SupplierEstDetailReportImpl;
import nc.vo.pu.m4201.enumeration.EnumToIAFlag;
import nc.vo.pu.report.enumeration.PuEstDetailMemoEnum;
import nc.vo.pu.report.queryinfo.supplierest.PuSupplierEstQryInfoPara;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * �ɹ���ⵥ�ݹ���ϸ
 * 
 * @since 6.0
 * @version 2011-3-31 ����06:28:06
 * @author yinfy
 */

public class M4201EstDetailSqlBuilder extends M4203EstDetailSqlBuilder {

  public M4201EstDetailSqlBuilder(PuSupplierEstQryInfoPara para) {
    super(para);
  }

  @Override
  public String getQuerySql() {
    SqlBuilder sb = new SqlBuilder();
    sb.append(SupplierEstDetailReportImpl.selectFinalSql);
    sb.append(" from ( ");
    sb.append(this.getGoodsEst());
    sb.append(" ) M4201T1 union all ");
    sb.append(SupplierEstDetailReportImpl.selectFinalSql);
    sb.append(" from ( ");
    sb.append(this.getFeeEst());
    sb.append(" ) M4201T2 union all ");
    sb.append(SupplierEstDetailReportImpl.selectFinalSql);
    sb.append(" from ( ");
    sb.append(this.getGoodsSettle());
    sb.append(" ) M4201T3 union all ");
    sb.append(SupplierEstDetailReportImpl.selectFinalSql);
    sb.append(" from ( ");
    sb.append(this.getFeeSettle());
    sb.append(" ) M4201T4 ");
    return sb.toString();
  }

  /**
   * ���ý���-�س�ͽ���
   * 
   * @return
   */
  private String getFeeSettle() {
    SqlBuilder sb = new SqlBuilder();
    // ���ûس�
    sb.append(this.getFeeSettleForClash());
    sb.append(" union all ");
    // ���ý���
    sb.append(this.getFeeSettleForSettle());
    return sb.toString();
  }

  protected String getFeeEst() {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" SELECT bd_material.pk_marbasclass pk_marbasclass,");// �������Ϸ���
    sql.append("        eb.pk_material pk_material,");// ����
    sql.append("        fe.pk_supplier pk_supplier,");// ���ù�Ӧ��
    sql.append("        fe.pk_feematerial pk_feematerial,");// ����
    sql.append(PuEstDetailMemoEnum.M45.toInt() + " billtype,");
    sql.append("        eh.vbillcode vbillcode,");
    sql.append("        eh.dbilldate dbilldate,");
    sql.append("        0 nestnum,");// �ݹ�����
    // sql.append("        fe.nestmny nestmny,");// �ݹ����
    sql.append("        fe.ncalcostmny nestmny,");// �ݹ���v61�Ƴɱ����
    sql.append("        0 nsettlenum,");// �����ݹ�����
    sql.append("        0 nclashestmoney,");// �����ݹ����
    sql.append("        0 ngoodsmoney,");// ������
    sql.append("        eb.pk_stockps_b pk_stockps_b,");// �������
    sql.append("        'est' data_type,");// �ݹ�����
    sql.append("        eb.ccurrencyid ccurrencyid ");
    sql.append("  FROM po_purchaseinfi eh ");
    sql.append(" INNER JOIN po_purchaseinfi_b eb ON eh.pk_stockps = eb.pk_stockps ");
    sql.append(" INNER JOIN bd_material bd_material ON eb.pk_material = bd_material.pk_material ");
    sql.append(" INNER JOIN po_purchaseinfi_fee fe ON eb.pk_stockps_b = fe.pk_stockps_b ");
    // ��ѯ��Ӧ�̷���ʱ�Ż��õ�
    if (this.containSupplier()) {
      sql.append(" INNER JOIN bd_supplier bd_supplier ON fe.pk_supplier = bd_supplier.pk_supplier ");
    }
    sql.append(" where eb.dr=0 ");
    sql.append("   and eb.ftoiaflag ", new EnumToIAFlag[] {
      EnumToIAFlag.EstimateToIA, EnumToIAFlag.ConfirmToIA
    });// �ݹ��ɱ�����ȷ���˳ɱ�
    sql.append("   and eh.dr=0 ");
    sql.append("   and fe.dr=0 ");
    sql.append("   and fe.destdate>='"
        + this.getPara().getBegindate().toString() + "'");
    sql.append("   and fe.destdate<='" + this.getPara().getEnddate().toString()
        + "' ");
    sql.append("   and isnull(fe.ncalcostmny,0) <> 0 ");// �ݹ�����
    sql.append("       and ");
    sql.append(this.getFeeEstCommonWhere());
    return sql.toString();
  }

  /**
   * ���ý��㲿��-�س����ͳ��
   * 
   * @return
   */
  protected String getFeeSettleForClash() {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" select bd_material.pk_marbasclass pk_marbasclass,");
    sql.append("        eb.pk_material pk_material,");
    sql.append("        ef.pk_supplier pk_supplier,");
    sql.append("        ef.pk_feematerial pk_feematerial,");
    sql.append(PuEstDetailMemoEnum.M27.toInt() + " billtype,");
    sql.append("        sh.vbillcode vbillcode,");
    sql.append("        sh.dbilldate dbilldate,");
    sql.append("        0 nestnum,");
    sql.append("        0 nestmny,");
    sql.append("        0 nsettlenum,");
    // 2011-09-16 ������(wangyinfen)�Ͳ��ԣ�suncong������ȷ�ϣ����ó�������¼��һ�γ����Ľ�
    // ������ȡ��ǰ�з��õĽ�������������п��ܳ��ֳ������Ƚ����������
    // sql.append("        ef.nestmny  nclashestmoney,");// �����ݹ����ȫ������
    sql.append("        ef.ncalcostmny  nclashestmoney,");// �����ݹ����ȫ������v61ȡ�Ƴɱ����
    sql.append("        0 ngoodsmoney,");
    sql.append("        eb.pk_stockps_b pk_stockps_b,");// �������
    sql.append("        'settle' data_type,");// ��������
    sql.append("        eb.ccurrencyid ccurrencyid ");
    sql.append("   FROM po_settlebill sh ");
    sql.append("  INNER JOIN po_settlebill_b sb ON sh.pk_settlebill = sb.pk_settlebill ");
    sql.append("  INNER JOIN po_purchaseinfi_fee ef ON sb.pk_settlebill_b=ef.pk_firstsettle_b ");// ������һ�ν���ķ��÷�Ʊ
    sql.append("  INNER JOIN po_purchaseinfi_b eb ON  ef.pk_stockps_b =  eb.pk_stockps_b ");
    sql.append("  INNER JOIN po_purchaseinfi eh ON eh.pk_stockps=eb.pk_stockps ");
    sql.append("  INNER JOIN bd_material bd_material ON eb.pk_material = bd_material.pk_material ");
    // ��ѯ��Ӧ�̷���ʱ�Ż��õ�
    if (this.containSupplier()) {
      sql.append("INNER JOIN bd_supplier bd_supplier ON ef.pk_supplier = bd_supplier.pk_supplier ");
    }
    sql.append("  where eb.dr=0 ");
    sql.append("    and eh.dr=0 ");
    sql.append("    and sh.dr=0 ");
    sql.append("    and isnull(sh.btoia,'N')='Y' "); // ����ȷ�ϣ��س�����ֻ�д������ʱ��ͳ��

    // wuxla 2013-4-23 Ӱ��ɱ��ĲŲ�ѯ����
    sql.append("    and eb.ftoiaflag ", new EnumToIAFlag[] {
      EnumToIAFlag.EstimateToIA, EnumToIAFlag.ConfirmToIA
    });// �ݹ��ɱ�����ȷ���˳ɱ�
    sql.append("    and eb.baffectcost", UFBoolean.TRUE);

    sql.append("    and ef.dr=0 ");
    sql.append("    and sb.dr=0 ");
    sql.append("    and sh.dbilldate", ">=", this.getPara().getBegindate()
        .toString());
    sql.append("    and sh.dbilldate", "<=", this.getPara().getEnddate()
        .toString());
    sql.appendIDIsNotNull(" and ef.pk_firstsettle");// ֻͳ���ݹ�������
    sql.append("    and ");
    sql.append(this.getFeeSettleCommonWhere());
    return sql.toString();
  }

  /**
   * ���ý��㲿��-������ͳ��
   * ������ڳ�����ĳһ���ã��˷��ñ����ڵ����н����Ҫͳ��
   * 
   * @return
   */
  protected String getFeeSettleForSettle() {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" select bd_material.pk_marbasclass pk_marbasclass,");
    sql.append("        eb.pk_material pk_material,");
    sql.append("        ef.pk_supplier pk_supplier,");
    sql.append("        ef.pk_feematerial pk_feematerial,");
    sql.append(PuEstDetailMemoEnum.M27.toInt() + " billtype,");
    sql.append("        sh.vbillcode vbillcode,");
    sql.append("        sh.dbilldate dbilldate,");
    sql.append("        0 nestnum,");
    sql.append("        0 nestmny,");
    sql.append("        0 nsettlenum,");
    sql.append("        0  nclashestmoney,");
    sql.append("        fe.nallotmoney ngoodsmoney,");// ���÷�̯��������
    sql.append("        eb.pk_stockps_b pk_stockps_b,");// �������
    sql.append("        'settle' data_type,");// ��������
    sql.append("        eb.ccurrencyid ccurrencyid ");
    sql.append("   FROM po_settlebill sh ");
    sql.append("  INNER JOIN po_settlebill_b sb ON sh.pk_settlebill = sb.pk_settlebill ");
    sql.append("  INNER JOIN po_purchaseinfi_b eb ON sb.pk_stock_b = eb.pk_stockps_b ");
    sql.append("  INNER JOIN po_purchaseinfi eh ON eb.pk_stockps = eh.pk_stockps ");
    sql.append("  INNER JOIN bd_material bd_material ON eb.pk_material = bd_material.pk_material ");
    sql.append("  INNER JOIN po_settle_feeallot fe ON fe.pk_settlebill_b = sb.pk_settlebill_b");
    sql.append("  INNER JOIN po_purchaseinfi_fee ef ON eb.pk_stockps_b = ef.pk_stockps_b ");
    sql.append("             and ef.pk_srcfeematerial=fe.pk_srcmaterial ");
    // ��ѯ��Ӧ�̷���ʱ�Ż��õ�
    if (this.containSupplier()) {
      sql.append("INNER JOIN bd_supplier bd_supplier ON ef.pk_supplier = bd_supplier.pk_supplier ");
    }
    sql.append("  where eb.dr=0 ");
    sql.append("    and eh.dr=0 ");
    sql.append("    AND isnull(ef.btoia,'N')   = 'Y' ");
    sql.append("    and sb.dr=0 ");
    sql.append("    and fe.dr=0 ");
    sql.append("    and ef.dr=0 ");
    sql.append("    and sh.dbilldate", ">=", this.getPara().getBegindate()
        .toString());
    sql.append("    and sh.dbilldate", "<=", this.getPara().getEnddate()
        .toString());
    sql.appendIDIsNotNull(" and ef.pk_firstsettle");// ֻͳ���ݹ�������
    sql.append("    and ");
    sql.append(this.getFeeSettleCommonWhere());
    return sql.toString();
  }

  @Override
  protected String getGoodsEst() {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" SELECT   ");
    sql.append("        bd_material.pk_marbasclass pk_marbasclass,");
    sql.append("        eb.pk_material pk_material,");
    sql.append("        eb.pk_supplier pk_supplier,");
    sql.append("        '' pk_feematerial,");
    sql.append(PuEstDetailMemoEnum.M45.toInt() + " billtype,");
    sql.append("        eh.vbillcode vbillcode,");
    sql.append("        eh.dbilldate dbilldate,");
    sql.append("        eb.nestnum nestnum,");
    // sql.append("        eb.nestmny nestmny,");
    sql.append("        eb.nestcalcostmny nestmny,");// v61�Ƴɱ����
    sql.append("        0 nsettlenum,");
    sql.append("        0 nclashestmoney,");
    sql.append("        0 ngoodsmoney,");
    sql.append("        eb.pk_stockps_b pk_stockps_b,");// �������
    sql.append("        'est' data_type,");// �ݹ�����
    sql.append("        eb.ccurrencyid ccurrencyid ");
    sql.append("   FROM po_purchaseinfi eh ");
    sql.append("  INNER JOIN po_purchaseinfi_b eb ON eh.pk_stockps = eb.pk_stockps ");
    sql.append("  INNER JOIN bd_material bd_material ON eb.pk_material = bd_material.pk_material ");
    // ��ѯ��Ӧ�̷���ʱ�Ż��õ�
    if (this.containSupplier()) {
      sql.append(" INNER JOIN bd_supplier bd_supplier ON eb.pk_supplier = bd_supplier.pk_supplier ");
    }
    sql.append("  where eb.dr=0  ");
    sql.append("    and eb.ftoiaflag ", new EnumToIAFlag[] {
      EnumToIAFlag.EstimateToIA, EnumToIAFlag.ConfirmToIA
    });// �ݹ��ɱ�����ȷ���˳ɱ�

    // wuxla 2013-4-23 Ӱ��ɱ��ĲŲ�ѯ����
    sql.append("    and eb.baffectcost", UFBoolean.TRUE);

    sql.append("    and eh.dr=0  ");
    sql.append("    and eb.dtocostapdate>='"
        + this.getPara().getBegindate().toString() + "'");
    sql.append("    and eb.dtocostapdate <='"
        + this.getPara().getEnddate().toString() + "'");
    sql.append(" and isnull(eb.nestcalcostmny,0)<>0 ");// �ݹ�����
    sql.append("       and ");
    sql.append(this.getGoodCommonWhere());
    return sql.toString();
  }

  @Override
  protected String getGoodsSettle() {
    SqlBuilder sb = new SqlBuilder();
    sb.append(" SELECT bd_material.pk_marbasclass pk_marbasclass,");
    sb.append("        eb.pk_material pk_material,");
    sb.append("        eb.pk_supplier pk_supplier,");
    sb.append("        '' pk_feematerial,");
    sb.append(PuEstDetailMemoEnum.M27.toInt() + " billtype,");
    sb.append("        sh.vbillcode vbillcode,");
    sb.append("        sh.dbilldate dbilldate,");
    sb.append("        0 nestnum,");
    sb.append("        0 nestmny,");
    sb.append("        case when isnull(sh.btoia,'N')='Y'");
    sb.append("             then  sb.nsettlenum ");
    sb.append("             else   0 ");
    sb.append("        end nsettlenum, ");// �����ݹ�����������ȷ�ϣ��س�����ֻ�д������ʱ��ͳ�ƣ�
    sb.append("        case when isnull(sh.btoia,'N')='Y'");
    sb.append("             then  sb.nclashestmoney ");
    sb.append("             else   0 ");
    sb.append("        end nclashestmoney, ");// �����ݹ�������ȷ�ϣ��س�����ֻ�д������ʱ��ͳ�ƣ�
    sb.append("        sb.ngoodsmoney ngoodsmoney,");
    sb.append("        eb.pk_stockps_b pk_stockps_b,");// �������
    sb.append("        'settle' data_type,");// ��������
    sb.append("        eb.ccurrencyid ccurrencyid ");
    sb.append("   FROM po_settlebill sh ");
    sb.append("  INNER JOIN po_settlebill_b sb ON sh.pk_settlebill = sb.pk_settlebill ");
    sb.append("  INNER JOIN po_purchaseinfi_b eb ON sb.pk_stock_b = eb.pk_stockps_b ");
    sb.append("  INNER JOIN po_purchaseinfi eh ON sb.pk_stock = eh.pk_stockps ");
    sb.append("  INNER JOIN bd_material bd_material ON eb.pk_material = bd_material.pk_material ");
    // ��ѯ��Ӧ�̷���ʱ�Ż��õ�
    if (this.containSupplier()) {
      sb.append("  INNER JOIN bd_supplier bd_supplier ON eb.pk_supplier = bd_supplier.pk_supplier ");
    }
    sb.append("  where eb.dr=0 ");
    sb.append("    and eh.dr=0 ");
    sb.append("    and sb.dr=0 ");
    sb.append("    and sh.dbilldate>='");
    sb.append(this.getPara().getBegindate().toString() + "'");
    sb.append("    and sh.dbilldate<='"
        + this.getPara().getEnddate().toString() + "'");
    sb.append("    and isnull(sb.nclashestmoney,0)<>0 ");// �ݹ����һس�����ݣ������ݹ���Ϊ��

    sb.append("    and eb.ftoiaflag ", new EnumToIAFlag[] {
      EnumToIAFlag.EstimateToIA, EnumToIAFlag.ConfirmToIA
    });// �ݹ��ɱ�����ȷ���˳ɱ�
    // wuxla 2013-4-23 Ӱ��ɱ��ĲŲ�ѯ����
    sb.append("    and eb.baffectcost", UFBoolean.TRUE);

    sb.append("    and ");
    sb.append(this.getGoodCommonWhere());
    return sb.toString();
  }

}
