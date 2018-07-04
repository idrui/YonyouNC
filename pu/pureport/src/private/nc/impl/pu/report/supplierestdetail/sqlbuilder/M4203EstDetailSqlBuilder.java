package nc.impl.pu.report.supplierestdetail.sqlbuilder;

import nc.impl.pu.report.supplierestdetail.SupplierEstDetailReportImpl;
import nc.vo.pu.m4201.enumeration.EnumToIAFlag;
import nc.vo.pu.report.enumeration.PuEstDetailMemoEnum;
import nc.vo.pu.report.queryinfo.supplierest.PuSupplierEstQryInfoPara;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * ί�мӹ����ݹ���ϸ
 * 
 * @since 6.0
 * @version 2011-3-31 ����04:18:11
 * @author yinfy
 */

public class M4203EstDetailSqlBuilder extends AbstractEstDetailSqlBuilder {

  /**
   * @param para
   */
  public M4203EstDetailSqlBuilder(PuSupplierEstQryInfoPara para) {
    super(para);
  }

  @Override
  public String getQuerySql() {
    SqlBuilder sb = new SqlBuilder();
    sb.append(SupplierEstDetailReportImpl.selectFinalSql);
    sb.append(" from ( ");
    sb.append(this.getGoodsEst());
    sb.append(" ) M4203T1 union all ");
    sb.append(SupplierEstDetailReportImpl.selectFinalSql);
    sb.append(" from ( ");
    sb.append(this.getGoodsSettle());
    sb.append(" ) M4203T2 ");
    return sb.toString();
  }

  protected String getGoodsEst() {
    SqlBuilder sb = new SqlBuilder();
    sb.append(" SELECT bd_material.pk_marbasclass pk_marbasclass,");
    sb.append("        eb.pk_material pk_material,");
    sb.append("        eb.pk_supplier pk_supplier,");
    sb.append("        '' pk_feematerial, ");
    sb.append(PuEstDetailMemoEnum.M47.toInt() + " billtype,");
    sb.append("        eh.vbillcode vbillcode,");
    sb.append("        eh.dbilldate dbilldate,");
    sb.append("        eb.ninnum nestnum,");
    sb.append("        eb.ncostmny nestmny,");
    sb.append("        0 nsettlenum,");
    sb.append("        0 nclashestmoney,");
    sb.append("        0 ngoodsmoney,");
    sb.append("        eb.pk_stockps_b pk_stockps_b,");// �������
    sb.append("        'est' data_type,");// �ݹ�����
    sb.append("        eb.ccurrencyid ccurrencyid ");
    sb.append("   FROM po_subcontinfi eh ");
    sb.append("  INNER JOIN po_subcontinfi_b eb ON eh.pk_stockps = eb.pk_stockps ");
    sb.append("  INNER JOIN bd_material bd_material ON eb.pk_material = bd_material.pk_material ");
    // ��ѯ��Ӧ�̷���ʱ�Ż��õ�
    if (this.containSupplier()) {
      sb.append("  INNER JOIN bd_supplier bd_supplier ON eb.pk_supplier = bd_supplier.pk_supplier ");
    }
    sb.append("  where eb.dr=0  ");
    sb.append("    and eb.ftoiaflag ", new EnumToIAFlag[] {
      EnumToIAFlag.EstimateToIA, EnumToIAFlag.ConfirmToIA
    });// �ݹ��ɱ�����ȷ���˳ɱ�

    // wuxla 2013-4-23 Ӱ��ɱ��ĲŲ�ѯ����
    sb.append("    and eb.baffectcost", UFBoolean.TRUE);

    sb.append("    and eh.dr=0  ");
    sb.append("    and eb.dtocostapdate >='"
        + this.getPara().getBegindate().toString() + "'");
    sb.append("    and eb.dtocostapdate <='"
        + this.getPara().getEnddate().toString() + "'");
    sb.append("    and ");
    sb.append(this.getGoodCommonWhere());
    return sb.toString();
  }

  protected String getGoodsSettle() {
    SqlBuilder sb = new SqlBuilder();
    sb.append(" SELECT bd_material.pk_marbasclass pk_marbasclass,");
    sb.append("        eb.pk_material pk_material,");
    sb.append("        eb.pk_supplier pk_supplier,");
    sb.append("        '' pk_feematerial,");
    sb.append(PuEstDetailMemoEnum.M27.toInt() + " billtype,");
    sb.append("        sh.vbillcode vbillcode,");// ����ȷ����ȡ���㵥��
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
    sb.append("  INNER JOIN po_subcontinfi_b eb ON sb.pk_stock_b = eb.pk_stockps_b ");
    sb.append("  INNER JOIN po_subcontinfi eh ON eb.pk_stockps = eh.pk_stockps ");
    sb.append("  INNER JOIN bd_material bd_material ON eb.pk_material = bd_material.pk_material ");
    // ��ѯ��Ӧ�̷���ʱ�Ż��õ�
    if (this.containSupplier()) {
      sb.append("  INNER JOIN bd_supplier bd_supplier ON eb.pk_supplier = bd_supplier.pk_supplier ");
    }
    sb.append("  where eb.dr=0 ");
    sb.append("    and sh.dr=0  ");
    sb.append("    and sb.dr=0  ");
    sb.append("    and sh.dbilldate>='"
        + this.getPara().getBegindate().toString() + "'");
    sb.append("    and sh.dbilldate<='"
        + this.getPara().getEnddate().toString() + "'");
    sb.append("    AND isnull(sb.bwashest,'N') = 'Y' ");

    // wuxla 2013-4-23 Ӱ��ɱ��ĲŲ�ѯ����
    sb.append("    and eb.ftoiaflag ", new EnumToIAFlag[] {
      EnumToIAFlag.EstimateToIA, EnumToIAFlag.ConfirmToIA
    });// �ݹ��ɱ�����ȷ���˳ɱ�
    sb.append("    and eb.baffectcost", UFBoolean.TRUE);

    sb.append("    and ");
    sb.append(this.getGoodCommonWhere());
    return sb.toString();
  }

}
