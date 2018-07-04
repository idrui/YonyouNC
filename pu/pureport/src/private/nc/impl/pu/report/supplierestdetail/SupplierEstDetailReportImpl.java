package nc.impl.pu.report.supplierestdetail;

import nc.impl.pu.report.supplierestdetail.sqlbuilder.M4201EstDetailSqlBuilder;
import nc.impl.pu.report.supplierestdetail.sqlbuilder.M4202EstDetailSqlBuilder;
import nc.impl.pu.report.supplierestdetail.sqlbuilder.M4203EstDetailSqlBuilder;
import nc.impl.pu.report.supplierestdetail.sqlbuilder.M4TEstDetailSqlBuilder;
import nc.itf.pu.report.supplierestdetail.ISupplierEstDetailReport;
import nc.pub.smart.context.SmartContext;
import nc.vo.pu.report.enumeration.PuEstStatContentEnum;
import nc.vo.pu.report.enumeration.SupplierEstQryFieldCode;
import nc.vo.pu.report.queryinfo.PuQueryInfoPara;
import nc.vo.pu.report.queryinfo.supplierest.PuSupplierEstQryInfoPara;
import nc.vo.pubapp.pattern.log.Log;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 供应商暂估明细查询数据加工接口实现类
 * 
 * @since 6.0
 * @version 2011-3-31 下午04:16:03
 * @author yinfy
 */

public class SupplierEstDetailReportImpl implements ISupplierEstDetailReport {
  public static String selectFinalSql =
      " select pk_marbasclass,pk_material,pk_supplier,pk_feematerial,billtype,vbillcode,dbilldate,ccurrencyid,nestnum,nestmny,nsettlenum,nclashestmoney,ngoodsmoney,pk_stockps_b,data_type";

  // private static String groupFinalSql =
  // " group by pk_marbasclass,pk_material,pk_supplier,pk_feematerial,billtype,vbillcode,dbilldate,ccurrencyid,pk_stockps,data_type ";

  private static String selectFinalSumSql =
      " select pk_marbasclass,pk_material,pk_supplier,pk_feematerial,billtype,vbillcode,dbilldate,ccurrencyid,pk_stockps_b,data_type ,sum(isnull(nestnum,0)) tnestnum,sum(isnull(nestmny,0)) tnestmny,sum(isnull(nsettlenum,0)) tnsettlenum,sum(isnull(nclashestmoney,0)) tnclashestmoney,sum(isnull(ngoodsmoney,0)) tngoodsmoney";

  @Override
  public String getQuerySql(SmartContext context) {
    PuSupplierEstQryInfoPara para =
        (PuSupplierEstQryInfoPara) context
            .getAttribute(PuQueryInfoPara.QUERY_PARA);
    if (null == para) {
      para = new PuSupplierEstQryInfoPara();
      para.setBilltypes(new String[] {
        PuEstStatContentEnum.STOCKPS.strValue()
      });
    }
    return this.getQuerySqlByPara(context, para);
  }

  private String getQuerySqlByPara(SmartContext context,
      PuSupplierEstQryInfoPara para) {
    String[] billtypes = para.getBilltypes();

    if (ArrayUtils.isEmpty(billtypes)) {
      return null;
    }
    StringBuilder sb = new StringBuilder();
    sb.append(SupplierEstDetailReportImpl.selectFinalSumSql);
    sb.append(" from ( ");

    int billTypes = 0;

    for (String billtype : billtypes) {
      billTypes++;
      String billSql = "";
      if (PuEstStatContentEnum.STOCKPS.strValue().equals(billtype)) {
        M4201EstDetailSqlBuilder stockbuilder =
            new M4201EstDetailSqlBuilder(para);
        billSql = stockbuilder.getQuerySql();
      }
      else if (PuEstStatContentEnum.SUBCONTIN.strValue().equals(billtype)) {
        M4203EstDetailSqlBuilder subconbuilder =
            new M4203EstDetailSqlBuilder(para);
        billSql = subconbuilder.getQuerySql();
      }
      else if (PuEstStatContentEnum.VMIFI.strValue().equals(billtype)) {
        // 针对查询条件有部门,采购组织等，消耗汇总不存在的字段，不查询消耗汇总
        if (!StringUtils.isEmpty(para.getWheresql())
            && (para.getWheresql().indexOf("eh.") > 0 || para.getWheresql()
                .contains(SupplierEstQryFieldCode.pk_purchaseorg.code()))) {
          continue;
        }
        M4202EstDetailSqlBuilder vmibuilder =
            new M4202EstDetailSqlBuilder(para);
        billSql = vmibuilder.getQuerySql();
      }
      else if (PuEstStatContentEnum.INITEST.strValue().equals(billtype)) {// 添加期初暂估
        M4TEstDetailSqlBuilder initSqlbuilder =
            new M4TEstDetailSqlBuilder(para);
        billSql = initSqlbuilder.getQuerySql();
      }
      if (billTypes > 1) {
        sb.append(" union all ");
      }
      sb.append(billSql);
    }
    sb.append("  ) M420 ");
    sb.append(" group by pk_marbasclass,pk_material,pk_supplier,pk_feematerial,");
    sb.append("          billtype,vbillcode,dbilldate,ccurrencyid,pk_stockps_b,data_type ");
    // 调试信息
    Log.debug("SupplierEstDetailReportImpl log sql=" + sb.toString());
    return sb.toString();
  }
}
