package nc.impl.pu.report.supplierestdetail.sqlbuilder;

import nc.impl.pu.report.supplierestdetail.SupplierEstDetailReportImpl;
import nc.vo.pu.m4201.enumeration.EnumToIAFlag;
import nc.vo.pu.report.enumeration.PuEstDetailMemoEnum;
import nc.vo.pu.report.queryinfo.supplierest.PuSupplierEstQryInfoPara;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.StringUtils;

/**
 * ���Ļ��ܵ��ݹ���ϸ
 * 
 * @since 6.0
 * @version 2011-3-31 ����08:36:32
 * @author yinfy
 */

public class M4202EstDetailSqlBuilder extends AbstractEstDetailSqlBuilder {

  /**
   * @param para
   */
  public M4202EstDetailSqlBuilder(PuSupplierEstQryInfoPara para) {
    super(para);
  }

  @Override
  public String getQuerySql() {
    SqlBuilder sb = new SqlBuilder();
    sb.append(SupplierEstDetailReportImpl.selectFinalSql);
    sb.append(" from ( ");
    sb.append(this.getGoodsEst());
    sb.append(" ) M4202T1 union all ");
    sb.append(SupplierEstDetailReportImpl.selectFinalSql);
    sb.append(" from ( ");
    sb.append(this.getFeeEst());
    sb.append(" ) M4202T2 union all ");
    sb.append(SupplierEstDetailReportImpl.selectFinalSql);
    sb.append(" from ( ");
    sb.append(this.getGoodsSettle());
    sb.append(" ) M4202T3 union all ");
    sb.append(SupplierEstDetailReportImpl.selectFinalSql);
    sb.append(" from ( ");
    sb.append(this.getFeeSettle());
    sb.append(" ) M4202T4 ");
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
    sql.append(" SELECT bd_material.pk_marbasclass pk_marbasclass,");
    sql.append("        eb.pk_material pk_material,");
    sql.append("        fe.pk_supplier pk_supplier,");
    sql.append("        fe.pk_feematerial pk_feematerial,");
    sql.append(PuEstDetailMemoEnum.M50.toInt() + " billtype,");
    sql.append("        eb.vbillcode vbillcode,");
    sql.append("        eb.dbilldate dbilldate,");
    sql.append("        0 nestnum,");
    // sql.append("        fe.nestmny nestmny,");
    sql.append("        fe.ncalcostmny nestmny,");// v61 �Ƴɱ����
    sql.append("        0 nsettlenum,");
    sql.append("        0 nclashestmoney,");
    sql.append("        0 ngoodsmoney,");
    sql.append("        eb.pk_stockps_b pk_stockps_b,");// �������
    sql.append("        'est' data_type,");// ��������
    sql.append("        eb.ccurrencyid ccurrencyid ");
    sql.append("   FROM po_vmifi eb ");
    sql.append("  INNER JOIN bd_material bd_material ON eb.pk_material = bd_material.pk_material ");
    // ��ѯ��Ӧ�̷���ʱ�Ż��õ�
    if (this.containSupplier()) {
      sql.append("  INNER JOIN bd_supplier bd_supplier ON eb.pk_supplier = bd_supplier.pk_supplier ");
    }
    sql.append("  INNER JOIN po_vmifi_fee fe ON eb.pk_stockps_b = fe.pk_stockps_b ");
    sql.append("  where eb.dr=0 ");
    sql.append("    and eb.ftoiaflag ", new EnumToIAFlag[] {
      EnumToIAFlag.EstimateToIA, EnumToIAFlag.ConfirmToIA
    });// �ݹ��ɱ�����ȷ���˳ɱ�
    sql.append("    and fe.dr=0 ");
    sql.append("    and fe.destdate>='"
        + this.getPara().getBegindate().toString() + "'");
    sql.append("    and fe.destdate<='"
        + this.getPara().getEnddate().toString() + "' ");
    sql.append("       and ");
    sql.append(this.getFeeEstCommonWhere());
    return sql.toString();
  }

  /**
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
    sql.append("        ef.ncalcostmny  nclashestmoney,");// �����ݹ����ȫ������ v61�Ƴɱ����
    sql.append("        0 ngoodsmoney,");
    sql.append("        eb.pk_stockps_b pk_stockps_b,");// �������
    sql.append("        'settle' data_type,");// ��������
    sql.append("        eb.ccurrencyid ccurrencyid ");
    sql.append("   FROM po_settlebill sh ");
    sql.append("  INNER JOIN po_settlebill_b sb ON sh.pk_settlebill = sb.pk_settlebill ");
    sql.append("  INNER JOIN po_vmifi_fee ef ON sb.pk_settlebill_b= ef.pk_firstsettle_b   ");
    sql.append("  INNER JOIN po_vmifi eb ON ef.pk_stockps_b = eb.pk_stockps_b ");
    sql.append("  INNER JOIN bd_material bd_material ON eb.pk_material = bd_material.pk_material ");
    if (this.containSupplier()) {
      sql.append("  INNER JOIN bd_supplier bd_supplier ON ef.pk_supplier = bd_supplier.pk_supplier ");
    }
    sql.append("  where eb.dr=0 ");
    sql.append("    and sh.dr=0 ");
    sql.append("    and isnull(sh.btoia,'N')='Y' "); // ����ȷ�ϣ��س�����ֻ�д������ʱ��ͳ��
    sql.append("    and ef.dr=0 ");
    sql.append("    and sb.dr=0 ");
    sql.append("    and sh.dbilldate>='"
        + this.getPara().getBegindate().toString() + "'");
    sql.append("    and sh.dbilldate<='"
        + this.getPara().getEnddate().toString() + "'");
    sql.appendIDIsNotNull(" and ef.pk_firstsettle");// ֻͳ���ݹ�������
    sql.append("       and ");
    sql.append(this.getFeeSettleCommonWhere());
    return sql.toString();
  }

  /**
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
    sql.append("        fe.nallotmoney ngoodsmoney,");
    sql.append("        eb.pk_stockps_b pk_stockps_b,");// �������
    sql.append("        'settle' data_type,");// ��������
    sql.append("        eb.ccurrencyid ccurrencyid ");
    sql.append("   FROM po_settlebill sh ");
    sql.append("  INNER JOIN po_settlebill_b sb ON sh.pk_settlebill = sb.pk_settlebill ");
    sql.append("  INNER JOIN po_vmifi eb ON sb.pk_stock_b = eb.pk_stockps_b ");
    sql.append("  INNER JOIN po_vmifi_fee ef ON ef.pk_stockps_b= eb.pk_stockps_b   ");
    sql.append("  INNER JOIN po_settle_feeallot fe ON sb.pk_settlebill_b = fe.pk_settlebill_b and ef.pk_srcfeematerial=fe.pk_srcmaterial ");
    sql.append("  INNER JOIN bd_material bd_material ON eb.pk_material = bd_material.pk_material ");
    if (this.containSupplier()) {
      sql.append("  INNER JOIN bd_supplier bd_supplier ON ef.pk_supplier = bd_supplier.pk_supplier ");
    }
    sql.append("  where eb.dr=0 ");
    sql.append("    and sb.dr=0 ");
    sql.append("    AND isnull(ef.btoia,'N')   = 'Y' ");
    sql.append("    and sh.dr=0 ");
    sql.append("    and fe.dr=0 ");
    sql.append("    and ef.dr=0 ");
    sql.append("    and sh.dbilldate>='"
        + this.getPara().getBegindate().toString() + "'");
    sql.append("    and sh.dbilldate<='"
        + this.getPara().getEnddate().toString() + "'");
    sql.appendIDIsNotNull(" and ef.pk_firstsettle");// ֻͳ���ݹ�������
    sql.append("       and ");
    sql.append(this.getFeeSettleCommonWhere());
    return sql.toString();
  }

  protected String getGoodsEst() {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" SELECT bd_material.pk_marbasclass pk_marbasclass,");
    sql.append("        eb.pk_material pk_material,");
    sql.append("        eb.pk_supplier pk_supplier,");
    sql.append("        '' pk_feematerial,");
    sql.append(PuEstDetailMemoEnum.M50.toInt() + " billtype,");
    sql.append("        eb.vbillcode vbillcode,");
    sql.append("        eb.dbilldate dbilldate,");
    sql.append("        eb.nestnum nestnum,");
    // sql.append("        eb.nestmny nestmny,");
    sql.append("        eb.nestcalcostmny nestmny,");// v61�Ƴɱ����
    sql.append("        0 nsettlenum,");
    sql.append("        0 nclashestmoney,");
    sql.append("        0 ngoodsmoney,");
    sql.append("        eb.pk_stockps_b pk_stockps_b,");// �������
    sql.append("        'est' data_type,");// �ݹ�����
    sql.append("        eb.ccurrencyid ccurrencyid ");
    sql.append("   FROM po_vmifi eb ");
    sql.append("  INNER JOIN bd_material bd_material ON eb.pk_material = bd_material.pk_material ");
    if (this.containSupplier()) {
      sql.append("  INNER JOIN bd_supplier bd_supplier ON eb.pk_supplier = bd_supplier.pk_supplier ");
    }
    sql.append("  where eb.dr=0 ");
    sql.append("    and eb.ftoiaflag ", new EnumToIAFlag[] {
      EnumToIAFlag.EstimateToIA, EnumToIAFlag.ConfirmToIA
    });// �ݹ��ɱ�����ȷ���˳ɱ�

    // wuxla 2013-4-23 Ӱ��ɱ��ĲŲ�ѯ����
    sql.append("    and eb.baffectcost", UFBoolean.TRUE);

    sql.append("    and eb.dtocostapdate >='"
        + this.getPara().getBegindate().toString() + "'");
    sql.append("    and eb.dtocostapdate <='"
        + this.getPara().getEnddate().toString() + "'");
    sql.append("    and isnull(eb.nestcalcostmny,0)<>0 ");
    sql.append("    and ");
    sql.append(this.getGoodCommonWhere());
    return sql.toString();
  }

  protected String getGoodsSettle() {
    SqlBuilder sb = new SqlBuilder();
    sb.append(" SELECT bd_material.pk_marbasclass pk_marbasclass,");
    sb.append("        eb.pk_material pk_material,");
    sb.append("        eb.pk_supplier pk_supplier,");
    sb.append("        '' pk_feematerial,");
    sb.append(PuEstDetailMemoEnum.M27.toInt() + " billtype,");
    sb.append("        sh.vbillcode vbillcode,");// ���㵥��
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
    sb.append("  INNER JOIN po_vmifi eb ON sb.pk_stock_b = eb.pk_stockps_b ");
    sb.append("  INNER JOIN bd_material bd_material ON eb.pk_material = bd_material.pk_material ");
    // ��ѯ��Ӧ�̷���ʱ�Ż��õ�
    if (this.containSupplier()) {
      sb.append("  INNER JOIN bd_supplier bd_supplier ON eb.pk_supplier = bd_supplier.pk_supplier ");
    }
    sb.append("  where eb.dr=0 ");
    sb.append("    and sb.dr=0 ");
    sb.append("    and sh.dr=0 ");
    sb.append("    and sh.dbilldate>='");
    sb.append(this.getPara().getBegindate().toString() + "'");
    sb.append("    and sh.dbilldate<='"
        + this.getPara().getEnddate().toString() + "'");
    sb.append("    and isnull(sb.nclashestmoney,0)<>0 ");// �����ݹ���

    // wuxla 2013-4-23 Ӱ��ɱ��ĲŲ�ѯ����
    sb.append("    and eb.ftoiaflag ", new EnumToIAFlag[] {
      EnumToIAFlag.EstimateToIA, EnumToIAFlag.ConfirmToIA
    });// �ݹ��ɱ�����ȷ���˳ɱ�
    sb.append("    and eb.baffectcost", UFBoolean.TRUE);

    sb.append("    and ");
    sb.append(this.getGoodCommonWhere());
    return sb.toString();
  }

  @Override
  protected String getQryDlgWhereSql() {
    String origsql = this.getPara().getWheresql();
    if (StringUtils.isEmpty(origsql)) {
      return "";
    }
    String result;
    result =
        StringUtils.replace(origsql, "eb.pk_org", "eb.pk_storeorg").replace(
            "pk_group", "eb.pk_group");
    return " and " + result;
  }

}
