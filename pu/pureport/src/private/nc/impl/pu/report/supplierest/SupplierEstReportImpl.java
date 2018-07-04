package nc.impl.pu.report.supplierest;

import nc.impl.pu.report.supplierest.sqlbuilder.M4201SqlBuilder;
import nc.impl.pu.report.supplierest.sqlbuilder.M4202SqlBuilder;
import nc.impl.pu.report.supplierest.sqlbuilder.M4203SqlBuilder;
import nc.impl.pu.report.supplierest.sqlbuilder.M4TSqlBuilder;
import nc.itf.pu.report.supplierest.ISupplierEstReport;
import nc.pub.smart.context.SmartContext;
import nc.vo.pu.report.enumeration.PuEstStatContentEnum;
import nc.vo.pu.report.enumeration.SupplierEstQryFieldCode;
import nc.vo.pu.report.queryinfo.PuQueryInfoPara;
import nc.vo.pu.report.queryinfo.supplierest.PuSupplierEstQryInfoPara;
import nc.vo.pubapp.pattern.log.Log;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * @since 6.0
 * @version 2011-3-22 上午09:44:29
 * @author yinfy
 */

public class SupplierEstReportImpl implements ISupplierEstReport {

  public static final String selectFinalSql =
      " select pk_supplier,unestnum,unestmny,estnum,nestmny,nsettlenum,nclashestmoney,ngoodsmoney";

  public static final String selectFinalSumSql =
      " select pk_supplier,sum(unestnum) tunestnum,sum(unestmny) tunestmny,sum(estnum) testnum,sum(nestmny) tnestmny,sum(nsettlenum) tnsettlenum,sum(nclashestmoney) tnclashestmoney,sum(ngoodsmoney) tngoodsmoney,sum(unestnum)+sum(estnum)-sum(nsettlenum) tendunestnum,sum(unestmny)+sum(nestmny)-sum(nclashestmoney) tendunestmny";

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
    sb.append(SupplierEstReportImpl.selectFinalSumSql);
    sb.append(" from ( ");
    int billTypes = 0;

    for (String billtype : billtypes) {
      billTypes++;
      String billSql = "";
      if (PuEstStatContentEnum.STOCKPS.strValue().equals(billtype)) {
        M4201SqlBuilder stockbuilder = new M4201SqlBuilder(para);
        billSql = stockbuilder.getQuerySql();
      }
      else if (PuEstStatContentEnum.SUBCONTIN.strValue().equals(billtype)) {
        M4203SqlBuilder subconbuilder = new M4203SqlBuilder(para);
        billSql = subconbuilder.getQuerySql();
      }
      else if (PuEstStatContentEnum.VMIFI.strValue().equals(billtype)) {
        // 针对查询条件有部门等，消耗汇总不存在的字段，不查询消耗汇总
        if (!StringUtils.isEmpty(para.getWheresql())
            && !this.isQueryVmi(para.getWheresql())) {
          continue;
        }
        M4202SqlBuilder vmibuilder = new M4202SqlBuilder(para);
        billSql = vmibuilder.getQuerySql();
      }
      else if (PuEstStatContentEnum.INITEST.strValue().equals(billtype)) {// 添加期初暂估
        M4TSqlBuilder initSqlBuilder = new M4TSqlBuilder(para);
        billSql = initSqlBuilder.getQuerySql();
      }
      if (billTypes > 1) {// 第一次不用拼union all
        sb.append(" union all ");
      }
      sb.append(billSql);
    }
    sb.append(") M4T group by pk_supplier ");
    // 调试信息
    Log.debug("SupplierEstReportImpl log sql=" + sb.toString());
    return sb.toString();
  }

  /**
   * 是否查询消耗汇总
   * 
   * @param dlgWhere
   * @return
   */
  private boolean isQueryVmi(String dlgWhere) {
    if (StringUtils.isEmpty(dlgWhere)) {
      return true;
    }
    // 查询包含采购组织，部门，人员时不查询
    if (dlgWhere.contains(SupplierEstQryFieldCode.pk_purchaseorg.code())
        || dlgWhere.contains(SupplierEstQryFieldCode.pk_psndoc.code())
        || dlgWhere.contains(SupplierEstQryFieldCode.pk_dept.code())) {
      return false;
    }
    return true;
  }
}
