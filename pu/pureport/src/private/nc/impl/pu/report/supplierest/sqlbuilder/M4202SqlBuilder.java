package nc.impl.pu.report.supplierest.sqlbuilder;

import nc.impl.pu.report.supplierest.SupplierEstReportImpl;
import nc.vo.pu.m4201.enumeration.EnumToIAFlag;
import nc.vo.pu.report.queryinfo.supplierest.PuSupplierEstQryInfoPara;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.StringUtils;

/**
 * ���Ļ��ܵ�
 * 
 * @since 6.0
 * @version 2011-3-22 ����02:34:42
 * @author yinfy
 */

public class M4202SqlBuilder extends AbstractSupplierEstSqlBuilder {

  /**
   * @param para
   */
  public M4202SqlBuilder(PuSupplierEstQryInfoPara para) {
    super(para);
  }

  @Override
  public String getQuerySql() {
    StringBuilder sb = new StringBuilder();
    sb.append(SupplierEstReportImpl.selectFinalSql);
    sb.append(" from ( ");
    sb.append(this.getInitialSql());
    sb.append(" ) M50T1 union all ");
    sb.append(SupplierEstReportImpl.selectFinalSql);
    sb.append(" from ( ");
    sb.append(this.getCurrEstSql());
    sb.append(" )  M50T2 union all ");
    sb.append(SupplierEstReportImpl.selectFinalSql);
    sb.append(" from ( ");
    sb.append(this.getCurrClashestSql());
    sb.append(" )  M50T3 ");
    return sb.toString();
  }

  /**
   * ���ڻ����ݹ��������������
   * 
   * @return
   */
  private String getCurrGoodClashAndSettleSql() {
    SqlBuilder sb = new SqlBuilder();
    sb.append("    SELECT ");
    sb.append("           eb.pk_supplier pk_supplier, ");
    sb.append("           0 unestnum, ");
    sb.append("           0 unestmny,  ");
    sb.append("           0 estnum, ");
    sb.append("           0 nestmny, ");
    sb.append("           case when isnull(sh.btoia,'N')='Y'");
    sb.append("             then  sb.nsettlenum ");
    sb.append("             else   0 ");
    sb.append("           end nsettlenum, ");// �����ݹ�����������ȷ�ϣ��س�����ֻ�д������ʱ��ͳ�ƣ�
    sb.append("           sb.ngoodsmoney ngoodsmoney, ");// ���������
    sb.append("           case when isnull(sh.btoia,'N')='Y'");
    sb.append("             then  sb.nclashestmoney ");
    sb.append("             else   0 ");
    sb.append("           end nclashestmoney ");// �����ݹ�������ȷ�ϣ��س�����ֻ�д������ʱ��ͳ�ƣ�
    sb.append("      FROM po_settlebill sh INNER JOIN po_settlebill_b sb ON sh.pk_settlebill = sb.pk_settlebill ");
    sb.append("     INNER JOIN po_vmifi eb ON sb.pk_stock_b = eb.pk_stockps_b ");
    // ��ѯ���Ϸ���ʱ�Ż��õ�
    if (this.containMaterial()) {
      sb.append("     INNER JOIN bd_material bd_material ON eb.pk_material = bd_material.pk_material ");
    }
    // ��ѯ��Ӧ�̷���ʱ�Ż��õ�
    if (this.containSupplier()) {
      sb.append("     INNER JOIN bd_supplier bd_supplier ON eb.pk_supplier = bd_supplier.pk_supplier ");
    }
    sb.append("     WHERE eb.dr=0 ");
    sb.append("       and sb.dr=0 ");
    sb.append("       and sh.dr=0 ");
    sb.append("       and isnull(sb.nclashestmoney,0)<>0 ");

    // wuxla
    sb.append("    and eb.ftoiaflag ", new EnumToIAFlag[] {
      EnumToIAFlag.EstimateToIA, EnumToIAFlag.ConfirmToIA
    });// �ݹ��ɱ�����ȷ���˳ɱ�
    sb.append("       and eb.baffectcost", UFBoolean.TRUE);

    sb.append("       and sh.dbilldate", ">=", this.getPara().getBegindate()
        .toString());
    sb.append("       and sh.dbilldate", "<=", this.getPara().getEnddate()
        .toString());
    sb.append("       and ");
    sb.append(this.getGoodCommonWhere());

    return sb.toString();
  }

  /**
   * @return
   */
  private String getCurrGoodEstSql() {
    SqlBuilder sb = new SqlBuilder();
    sb.append("    SELECT ");
    sb.append("           eb.pk_supplier pk_supplier, ");
    // sb.append("           bd_supplier.name suppliername, ");
    sb.append("           0 unestnum, ");
    sb.append("           0 unestmny,  ");
    sb.append("           eb.nestnum estnum, ");
    // sb.append("           eb.nestmny nestmny, ");
    sb.append("           eb.nestcalcostmny nestmny, ");// v61�Ƴɱ����
    sb.append("           0 nsettlenum, ");
    sb.append("           0 ngoodsmoney, ");
    sb.append("           0 nclashestmoney ");
    sb.append("     FROM po_vmifi eb ");
    // ��ѯ���Ϸ���ʱ�Ż��õ�
    if (this.containMaterial()) {
      sb.append("    INNER JOIN bd_material bd_material ON eb.pk_material = bd_material.pk_material ");
    }
    // ��ѯ��Ӧ�̷���ʱ�Ż��õ�
    if (this.containSupplier()) {
      sb.append("    INNER JOIN bd_supplier bd_supplier ON eb.pk_supplier = bd_supplier.pk_supplier ");
    }
    sb.append("    where eb.dr=0 ");
    sb.append("      and eb.ftoiaflag ", new EnumToIAFlag[] {
      EnumToIAFlag.EstimateToIA, EnumToIAFlag.ConfirmToIA
    });// �ݹ��ɱ�����ȷ���˳ɱ�

    // wuxla
    sb.append("    and eb.baffectcost", UFBoolean.TRUE);

    // sb.append("       and isnull(eb.nestmny,0) <> 0");// �г���������
    sb.append("       and eb.dtocostapdate", ">=", this.getPara()
        .getBegindate().toString());
    sb.append("       and eb.dtocostapdate", "<=", this.getPara().getEnddate()
        .toString());
    sb.append("       and ");
    sb.append(this.getGoodCommonWhere());

    return sb.toString();
  }

  /**
   * �����ݹ�-���㵫δ���ɱ�����
   * 
   * @return
   */
  private String getInitialGoodEstIAUnClashSql() {
    SqlBuilder sb = new SqlBuilder();
    sb.append("    SELECT ");
    sb.append("           eb.pk_supplier pk_supplier, ");
    sb.append("           sb.nsettlenum unestnum, ");
    sb.append("           sb.nclashestmoney unestmny, ");
    sb.append("           0 estnum, ");
    sb.append("           0 nestmny, ");
    sb.append("           0 nsettlenum, ");
    sb.append("           0 ngoodsmoney, ");
    sb.append("           0 nclashestmoney ");
    sb.append("     FROM po_vmifi eb ");
    sb.append("    inner join po_settlebill_b sb on sb.pk_stock_b=eb.pk_stockps_b ");
    sb.append("    inner join po_settlebill sh on sh.pk_settlebill = sb.pk_settlebill ");
    // ��ѯ���Ϸ���ʱ�Ż��õ�
    if (this.containMaterial()) {
      sb.append("  INNER JOIN bd_material bd_material ON eb.pk_material = bd_material.pk_material ");
    }
    // ��ѯ��Ӧ�̷���ʱ�Ż��õ�
    if (this.containSupplier()) {
      sb.append("  INNER JOIN bd_supplier bd_supplier ON eb.pk_supplier = bd_supplier.pk_supplier ");
    }
    sb.append("    where eb.dr=0 ");
    sb.append("      AND sb.dr = 0 ");
    sb.append("      AND sh.dr = 0 ");

    // wuxla
    sb.append("     and eb.baffectcost", UFBoolean.TRUE);

    sb.append("      and ( isnull(sh.btoia,'N') ='N' ");// �������δ���ɱ�
    sb.append("            or  isnull(sh.btoia,'N') ='Y' ");// ��ѯ����֮��Ľ�����Ĳ��ֲ���ͳ�ƣ�Ҫ�ӽ���
    sb.append("            and sh.dbilldate", ">=", this.getPara()
        .getBegindate().toString());
    sb.append("           ) ");
    sb.append("      and eb.dtocostapdate<='"
        + this.getPara().getBegindate().toString() + "'");
    sb.append("      and ");
    sb.append(this.getGoodCommonWhere());

    return sb.toString();
  }

  /**
   * �����ݹ�-����δ��������
   * 
   * @return
   */
  private String getInitialGoodEstSettleUnClashSql() {
    SqlBuilder sb = new SqlBuilder();
    sb.append("    SELECT ");
    sb.append("           eb.pk_supplier pk_supplier, ");
    sb.append("           (isnull(eb.nestnum,0)-isnull(eb.naccestcoststtlnum,0)) unestnum, ");
    // sb.append("           (isnull(eb.nestmny,0)-isnull(eb.naccestcostwashmny,0)) unestmny,  ");
    sb.append("           (isnull(eb.nestcalcostmny,0)-isnull(eb.naccestcostwashmny,0)) unestmny,  ");// v61�Ƴɱ����
    sb.append("           0 estnum, ");
    sb.append("           0 nestmny, ");
    sb.append("           0 nsettlenum, ");
    sb.append("           0 ngoodsmoney, ");
    sb.append("           0 nclashestmoney ");
    sb.append("     FROM po_vmifi eb ");
    // ��ѯ���Ϸ���ʱ�Ż��õ�
    if (this.containMaterial()) {
      sb.append("    INNER JOIN bd_material bd_material ON eb.pk_material = bd_material.pk_material ");
    }
    // ��ѯ��Ӧ�̷���ʱ�Ż��õ�
    if (this.containSupplier()) {
      sb.append("    INNER JOIN bd_supplier bd_supplier ON eb.pk_supplier = bd_supplier.pk_supplier ");
    }
    sb.append("    where eb.dr=0 ");
    sb.append("      and eb.ftoiaflag ", new EnumToIAFlag[] {
      EnumToIAFlag.EstimateToIA, EnumToIAFlag.ConfirmToIA
    });// �ݹ��ɱ�����ȷ���˳ɱ�

    // wuxla
    sb.append("     and eb.baffectcost", UFBoolean.TRUE);

    sb.append("       and (isnull(eb.nestcalcostmny,0)-isnull(eb.naccestcostwashmny,0)) <> 0");// �г���������
    sb.append("       and eb.dtocostapdate<='"
        + this.getPara().getBegindate().toString() + "'");
    sb.append("       and ");
    sb.append(this.getGoodCommonWhere());

    return sb.toString();
  }

  /**
   * �����ݹ�����ͳ��
   * 
   * @return
   */
  private String getInitialGoodEstSql() {
    StringBuilder sb = new StringBuilder();
    sb.append(this.getInitialGoodEstSettleUnClashSql());
    sb.append(" union all ");
    sb.append(this.getInitialGoodEstIAUnClashSql());
    return sb.toString();
  }

  protected String getCurrClashestSql() {
    StringBuilder sb = new StringBuilder();
    // �������������
    sb.append(this.getCurrGoodClashAndSettleSql());
    sb.append(" union all ");
    // ���ó���������
    sb.append(this.getCurrFeeClashAndSettleSql());
    return sb.toString();
  }

  protected String getCurrEstSql() {
    StringBuilder sb = new StringBuilder();
    // �����ݹ�����
    sb.append(this.getCurrGoodEstSql());
    sb.append(" union all ");
    // �����ݹ�����
    sb.append(this.getCurrFeeEstSql());
    return sb.toString();
  }

  /**
   * �����ݹ����������ý���
   * 
   * @return
   */
  protected String getCurrFeeClashAndSettleSql() {
    SqlBuilder sb = new SqlBuilder();
    // �����ݹ�����
    sb.append(this.getCurrFeeClashSql());
    sb.append(" union all ");
    // ���ý���
    sb.append(this.getCurrFeeSettleSql());
    return sb.toString();
  }

  /**
   * ���ڷ��ó����ݹ����
   * 
   * @return
   */
  protected String getCurrFeeClashSql() {
    SqlBuilder sb = new SqlBuilder();
    sb.append("    SELECT ");
    sb.append("           ef.pk_supplier pk_supplier, ");
    sb.append("           0 unestnum, ");
    sb.append("           0 unestmny,  ");
    sb.append("           0 estnum, ");
    sb.append("           0 nestmny, ");
    sb.append("           0 nsettlenum, ");
    sb.append("           0 ngoodsmoney, ");// ���÷�̯���
    // sb.append("           ef.nestmny nclashestmoney ");// �����ݹ����
    sb.append("           ef.ncalcostmny nclashestmoney ");// �����ݹ����v61�Ƴɱ����
    sb.append("      FROM po_settlebill sh ");
    sb.append("     INNER JOIN po_settlebill_b sb ON sh.pk_settlebill = sb.pk_settlebill ");
    sb.append("     INNER JOIN po_vmifi_fee ef ON  sb.pk_settlebill_b=ef.pk_firstsettle_b  ");
    sb.append("     INNER JOIN po_vmifi eb ON ef.pk_stockps_b = eb.pk_stockps_b ");
    // ��ѯ���Ϸ���ʱ�Ż��õ�
    if (this.containMaterial()) {
      sb.append("     INNER JOIN bd_material bd_material ON ef.pk_feematerial = bd_material.pk_material ");
    }
    // ��ѯ��Ӧ�̷���ʱ�Ż��õ�
    if (this.containSupplier()) {
      sb.append("     INNER JOIN bd_supplier bd_supplier ON ef.pk_supplier = bd_supplier.pk_supplier ");
    }
    sb.append("     WHERE eb.dr=0 ");
    sb.append("       and sb.dr=0 ");
    sb.append("       and sh.dr=0 ");
    sb.append("       and ef.dr=0 ");
    sb.append("       and isnull(sh.btoia,'N')='Y' "); // ����ȷ�ϣ��س�����ֻ�д������ʱ��ͳ��
    sb.append("       and sh.dbilldate", ">=", this.getPara().getBegindate()
        .toString());
    sb.append("       and sh.dbilldate", "<=", this.getPara().getEnddate()
        .toString());
    sb.appendIDIsNotNull(" and ef.pk_firstsettle");// ֻͳ���ݹ�������
    sb.append("       and ");
    sb.append(this.getFeeSettleCommonWhere());
    return sb.toString();
  }

  protected String getCurrFeeEstSql() {
    SqlBuilder sb = new SqlBuilder();
    sb.append(" SELECT ");
    sb.append("       fe.pk_supplier pk_supplier, ");
    // sb.append("       bd_supplier.name suppliername, ");
    sb.append("       0 unestnum, ");
    sb.append("       0 unnestmny, ");
    sb.append("       0 estnum, ");// �����ݹ�����Ϊ0
    // sb.append("       isnull(fe.nestmny,0) estmny,  ");// �����ݹ���δ�����Ľ��
    sb.append("       isnull(fe.ncalcostmny,0) estmny,  ");// �����ݹ���δ�����Ľ��v61�Ƴɱ����
    sb.append("       0 nsettlenum, ");
    sb.append("       0 ngoodsmoney, ");
    sb.append("       0 nclashestmoney ");
    sb.append("  FROM po_vmifi eb ");
    sb.append(" INNER JOIN po_vmifi_fee fe ON eb.pk_stockps_b = fe.pk_stockps_b ");
    // ��ѯ���Ϸ���ʱ�Ż��õ�
    if (this.containMaterial()) {
      sb.append(" INNER JOIN bd_material bd_material ON eb.pk_material = bd_material.pk_material ");
    }
    // ��ѯ��Ӧ�̷���ʱ�Ż��õ�
    if (this.containSupplier()) {
      sb.append(" INNER JOIN bd_supplier bd_supplier ON fe.pk_supplier = bd_supplier.pk_supplier ");
    }
    sb.append(" where eb.dr=0 and fe.dr=0 ");
    sb.append("   AND isnull(fe.btoia,'N')   = 'Y' ");
    // �����ݹ�����
    sb.append("   and fe.destdate>='"
        + this.getPara().getBegindate().toString() + "'");
    sb.append("   and fe.destdate<='" + this.getPara().getEnddate().toString()
        + "'");
    sb.append("       and ");
    sb.append(this.getFeeEstCommonWhere());
    return sb.toString();
  }

  /**
   * ���ڷ��ý�����
   * 
   * @return
   */
  protected String getCurrFeeSettleSql() {
    SqlBuilder sb = new SqlBuilder();
    sb.append("    SELECT ");
    sb.append("           ef.pk_supplier pk_supplier, ");
    sb.append("           0 unestnum, ");
    sb.append("           0 unestmny,  ");
    sb.append("           0 estnum, ");
    sb.append("           0 nestmny, ");
    sb.append("           0 nsettlenum, ");
    sb.append("           fe.nallotmoney ngoodsmoney, ");// ���÷�̯���
    sb.append("           0 nclashestmoney ");// �����ݹ����
    sb.append("      FROM po_settlebill sh ");
    sb.append("     INNER JOIN po_settlebill_b sb ON sh.pk_settlebill = sb.pk_settlebill ");
    sb.append("     INNER JOIN po_settle_feeallot fe ON sb.pk_settlebill_b = fe.pk_settlebill_b ");
    sb.append("     INNER JOIN po_vmifi_fee ef ON  ef.pk_srcfeematerial=fe.pk_srcmaterial ");
    sb.append("     INNER JOIN po_vmifi eb ON ef.pk_stockps_b = eb.pk_stockps_b and sb.pk_stock_b = eb.pk_stockps_b ");
    // ��ѯ���Ϸ���ʱ�Ż��õ�
    if (this.containMaterial()) {
      sb.append("     INNER JOIN bd_material bd_material ON ef.pk_feematerial = bd_material.pk_material ");
    }
    // ��ѯ��Ӧ�̷���ʱ�Ż��õ�
    if (this.containSupplier()) {
      sb.append("     INNER JOIN bd_supplier bd_supplier ON ef.pk_supplier = bd_supplier.pk_supplier ");
    }
    sb.append("     WHERE eb.dr=0 ");
    sb.append("       and ef.dr=0 ");
    sb.append("   AND isnull(ef.btoia,'N')   = 'Y' ");
    sb.append("       and sb.dr=0 ");
    sb.append("       and sh.dr=0 ");
    sb.append("       and fe.dr=0 ");
    sb.append("       and sh.dbilldate", ">=", this.getPara().getBegindate()
        .toString());
    sb.append("       and sh.dbilldate", "<=", this.getPara().getEnddate()
        .toString());
    sb.appendIDIsNotNull(" and ef.pk_firstsettle");// ֻͳ���ݹ�������
    sb.append("       and ");
    sb.append(this.getFeeSettleCommonWhere());
    return sb.toString();
  }

  protected String getInitialFeeEstSql() {
    SqlBuilder sb = new SqlBuilder();
    sb.append(" SELECT ");
    sb.append("       fe.pk_supplier pk_supplier, ");
    sb.append("       0 unestnum, ");// �����ݹ�����Ϊ0
    // sb.append("       isnull(eb.nestmny,0) unestmny,  ");// �����ݹ���δ�����Ľ��
    sb.append("       isnull(eb.nestcalcostmny,0) unestmny,  ");// �����ݹ���δ�����Ľ��v61�Ƴɱ����
    sb.append("       0 estnum, ");
    sb.append("       0 nestmny, ");
    sb.append("       0 nsettlenum, ");
    sb.append("       0 ngoodsmoney, ");
    sb.append("       0 nclashestmoney ");
    sb.append("  FROM po_vmifi eb ");
    sb.append(" INNER JOIN po_vmifi_fee fe ON eb.pk_stockps_b = fe.pk_stockps_b ");
    // ��ѯ���Ϸ���ʱ�Ż��õ�
    if (this.containMaterial()) {
      sb.append(" INNER JOIN bd_material bd_material ON eb.pk_material = bd_material.pk_material ");
    }
    // ��ѯ��Ӧ�̷���ʱ�Ż��õ�
    if (this.containSupplier()) {
      sb.append(" INNER JOIN bd_supplier bd_supplier ON fe.pk_supplier = bd_supplier.pk_supplier ");
    }
    sb.append(" where eb.dr=0 and fe.dr=0 ");
    sb.append("   AND isnull(fe.btoia,'N')   = 'Y' ");
    // �����ݹ�����
    sb.append("   and fe.destdate<='"
        + this.getPara().getBegindate().toString() + "'");
    sb.append("   and ");
    sb.append(this.getFeeUnclashAddWhere()); // ����δ������߽����˵�δ���ɱ�
    sb.append("   and ");
    sb.append(this.getFeeEstCommonWhere());
    return sb.toString();
  }

  protected String getInitialSql() {
    StringBuilder sb = new StringBuilder();
    sb.append(this.getInitialGoodEstSql());
    sb.append("    union all ");
    sb.append(this.getInitialFeeEstSql());
    return sb.toString();
  }

  @Override
  protected String getQryDlgWhereSql() {

    String origsql = this.getPara().getWheresql();
    if (StringUtils.isEmpty(origsql)) {
      return "";
    }
    String result;
    result = StringUtils.replace(origsql, "eb.pk_org", "eb.pk_storeorg");
    result = StringUtils.replace(result, "eb.pk_org_v", "eb.pk_storeorg_v");
    result = StringUtils.replace(result, "pk_group", "eb.pk_group");
    return " and " + result;
  }

}
