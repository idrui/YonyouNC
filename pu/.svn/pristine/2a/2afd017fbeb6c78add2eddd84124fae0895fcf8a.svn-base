package nc.impl.pu.report.praybill;

import java.util.HashMap;
import java.util.Map;

import nc.bs.pubapp.report.ReportPermissionUtils;
import nc.itf.pu.report.praybill.IPraybillReport;
import nc.pub.smart.context.SmartContext;
import nc.pub.smart.exception.SmartException;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.report.enumeration.PrayBillGroupEnum;
import nc.vo.pu.report.queryinfo.PuQueryInfoPara;
import nc.vo.pu.report.queryinfo.praybill.PrayBillQryInfoPara;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.query.ConditionVO;

/**
 * 请购单执行查询
 * 数据加工
 * 
 * @since 6.0
 * @version 2011-8-23 上午11:06:11
 * @author liuchx
 */
public class PraybillReportImpl implements IPraybillReport {
  
  @Override
  public String getPrayBillExecSql(SmartContext context) throws SmartException {
    
    ConditionVO[] conds = (ConditionVO[])context.getAttribute(PuQueryInfoPara.QUERY_CONDS);
    Map<Class<? extends ISuperVO>, String> beanMap =
        new HashMap<Class<? extends ISuperVO>, String>();
    beanMap.put(PraybillHeaderVO.class, PUEntity.M20_H_TABLE);
    beanMap.put(PraybillItemVO.class, PUEntity.M20_B_TABLE);
    String rptPermissionPart =
        new ReportPermissionUtils(context).getPermissionSQL(beanMap);
    // 请购单行执行明细、请购单行执行汇总、物料执行汇总 PrayBillGroupEnum

    PrayBillQryInfoPara para =
        (PrayBillQryInfoPara) context.getAttribute(PuQueryInfoPara.QUERY_PARA);
    if (null == conds || null == para) {
      String prayBillSqlWhenNull =
          new PrayBillDetailSqlGetter(null, null).getPrayBillWhenNull();
      return prayBillSqlWhenNull + rptPermissionPart;
    }

    Integer grouptype = Integer.valueOf(para.getGroupcondtion());

    String bsc = "ALL";

    bsc = para.getBsc();

    /** 按请购单明细 **/
    if (PrayBillGroupEnum.EXEDETAIL.value().equals(grouptype)) {
      String sql =
          new PrayBillDetailSqlGetter(bsc, conds).getReportSql(context);
      return sql + rptPermissionPart + " order by po_praybill.vbillcode";
    }

    /** 按请购单汇总 **/
    if (PrayBillGroupEnum.EXEGROUP.value().equals(grouptype)) {
      return new PrayBillExeSqlGetter(bsc, rptPermissionPart, conds)
          .getReportSql(context);
    }

    /** 按物料汇总 **/
    if (PrayBillGroupEnum.MAR.value().equals(grouptype)) {
      return new PrayBillMarSqlGetter(bsc, rptPermissionPart, conds)
          .getReportSql(context);
    }
    // 按请购单明细
    return new PrayBillDetailSqlGetter(null, null).getPrayBillWhenNull()
        + rptPermissionPart;
  }
}
