package nc.impl.pu.report.estmothstat;

import nc.impl.pu.report.estmothstat.sqlbuilder.CurMonthStockInAndEst;
import nc.impl.pu.report.estmothstat.sqlbuilder.CurMonthStockInAndSettle;
import nc.impl.pu.report.estmothstat.sqlbuilder.PreMonthEstAndNoSettle;
import nc.impl.pu.report.estmothstat.sqlbuilder.PreMonthEstAndSettle;
import nc.itf.pu.report.estmothstat.IEstMonthStatReport;
import nc.pub.smart.context.SmartContext;
import nc.vo.pu.report.queryinfo.PuQueryInfoPara;
import nc.vo.pu.report.queryinfo.eststat.PuEstStatQryInfoPara;
import nc.vo.pubapp.pattern.log.Log;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * �ݹ���ͳ�����ݼӹ�ʵ��
 * 
 * @since 6.0
 * @version 2011-8-22 ����12:02:41
 * @author �����
 */

public class EstMonthStatReportImpl implements IEstMonthStatReport {

  @Override
  public String getQuerySql(SmartContext context) {

    PuEstStatQryInfoPara queryPara =
        (PuEstStatQryInfoPara) context.getAttribute(PuQueryInfoPara.QUERY_PARA);
    SqlBuilder sql = new SqlBuilder();
    sql.append(" select ");
    sql.append("   pk_storeorg , ");
    sql.append("   pk_storeorg_v , ");
    sql.append("   pk_financeorg , ");
    sql.append("   pk_financeorg_v , ");
    sql.append("   pk_purchaseorg , ");
    sql.append("   pk_purchaseorg_v , ");
    sql.append("   pk_marbasclass , ");
    sql.append("   cunitid , ");
    sql.append("   pk_material , ");
    sql.append("   pk_supplier , ");
    // sql.append("   pk_areacl , ");
    sql.append("   pk_dept , ");
    sql.append("   pk_dept_v , ");
    sql.append("   pk_psndoc , ");
    sql.append("   pk_stordoc , ");
    sql.append("   cwhsmanagerid , ");
    // ����ǰ�ݹ����½���
    // 1 PRENSETTLENUM ����ǰ - ��������
    // 2 PRENGOODSMONEY ����ǰ - ������
    // 3 PRENCLASHESTMONEY ����ǰ - �����ݹ����
    sql.append("   prensettlenum, ");
    sql.append("   prengoodsmoney, ");
    sql.append("   prenclashestmoney, ");
    // ������Ȿ�½���
    // 1 CURRNSETTLENUM ���� - ��������
    // 2 CURRNGOODSMONEY ���� - ������
    // 3 CURRNCLASHESTMONEY ���� - �����ݹ����
    sql.append("   currnsettlenum, ");
    sql.append("   currngoodsmoney, ");
    sql.append("   currnclashestmoney, ");
    // ������Ȿ���ݹ�
    // 1 CURRUNESTNUM ���� - δ�����ݹ�����
    // 2 CURRUNESTMNY ���� - δ�����ݹ����
    sql.append("   currunestnum, ");
    sql.append("   currunestmny, ");

    // ����ǰ�ݹ�����δ����
    // 1 PREUNESTNUM ����ǰ - δ�����ݹ�����
    // 2 PREUNESTMNY ����ǰ - δ�����ݹ����
    sql.append("   preunestnum, ");
    sql.append("   preunestmny, ");

    sql.append("   ccurrencyid ");
    sql.append(" from ");
    sql.append("   ( ");
    sql.append("     ( ");
    sql.append(new CurMonthStockInAndEst(queryPara).getQuerySql());// ������Ȿ���ݹ�
    sql.append("     )  ");
    sql.append("     union all  ");
    sql.append("     ( ");
    sql.append(new PreMonthEstAndSettle(queryPara).getQuerySql());// ����ǰ�ݹ����½���
    sql.append("     )  ");
    sql.append("     union all  ");
    sql.append("     ( ");
    sql.append(new CurMonthStockInAndSettle(queryPara).getQuerySql());// ������Ȿ�½���
    sql.append("     )  ");
    sql.append("     union all  ");
    sql.append("     ( ");
    sql.append(new PreMonthEstAndNoSettle(queryPara).getQuerySql());// ����ǰ�ݹ�����δ����
    sql.append("     )  ");
    sql.append("   )  SETTLESTAT ");
    Log.debug(" EstMonthStatReportImpl sql=" + sql.toString());
    return sql.toString();
  }

}
