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
 * �빺��ִ�в�ѯ
 * ���ݼӹ�
 * 
 * @since 6.0
 * @version 2011-8-23 ����11:06:11
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
    // �빺����ִ����ϸ���빺����ִ�л��ܡ�����ִ�л��� PrayBillGroupEnum

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

    /** ���빺����ϸ **/
    if (PrayBillGroupEnum.EXEDETAIL.value().equals(grouptype)) {
      String sql =
          new PrayBillDetailSqlGetter(bsc, conds).getReportSql(context);
      return sql + rptPermissionPart + " order by po_praybill.vbillcode";
    }

    /** ���빺������ **/
    if (PrayBillGroupEnum.EXEGROUP.value().equals(grouptype)) {
      return new PrayBillExeSqlGetter(bsc, rptPermissionPart, conds)
          .getReportSql(context);
    }

    /** �����ϻ��� **/
    if (PrayBillGroupEnum.MAR.value().equals(grouptype)) {
      return new PrayBillMarSqlGetter(bsc, rptPermissionPart, conds)
          .getReportSql(context);
    }
    // ���빺����ϸ
    return new PrayBillDetailSqlGetter(null, null).getPrayBillWhenNull()
        + rptPermissionPart;
  }
}
