package nc.vo.pu.report.convertor;

import java.io.Serializable;
import java.util.TimeZone;

import nc.itf.iufo.freereport.extend.IQueryCondition;
import nc.itf.iufo.freereport.extend.ISubscribeQueryCondition;
import nc.scmmm.pub.scmpub.report.rptutil.SCMRptQuerySchemeUtils;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.report.pub.PuReportSubConvertor;
import nc.vo.pu.report.queryinfo.supplierstatcomprate.SupplierStatCompRateQryInfoPara;
import nc.vo.pubapp.AppContext;

import com.ufida.dataset.IContext;
import com.ufida.report.anareport.FreeReportContextKey;
import com.ufida.report.anareport.base.BaseQueryCondition;
import com.ufida.report.anareport.base.BaseSubscribeCondition;
import com.ufida.report.anareport.model.AbsAnaReportModel;

/**
 * ����Ӧ��ͳ�ƶ��������-����������������
 * 
 * @since 6.3
 * @version 2012-12-20 ����10:38:08
 * @author fanly3
 */
public class SupplierStatCompRateConvertor extends PuReportSubConvertor
    implements Serializable {

  /** ��������������ֶ� */
  private static final String DBILLDATE_WITH_ALIAS = "po_order_b.dbilldate";

  /** ��ѯģ�崫�����Ĳ���������������ֶ� */
  private static final String DBILLDATE_WITHOUT_ALIAS = "dbilldate";

  /** ��������ļ����ֶ� */
  private static final String PK_GROUP_WITH_ALIAS = "po_order_b.pk_group";

  /** ��ѯģ�崫�����Ĳ���������ļ����ֶ� */
  private static final String PK_GROUP_WITHOUT_ALIAS = "pk_group";

  /** ��ѯģ����֯�ֶ��� */
  private static final String PK_ORG = "po_order_b.pk_org";

  private static final long serialVersionUID = -8316012396149920384L;

  /**
   * ���ò�ѯ����
   * 
   * @param context ������context����
   * @param wherePart ��ѯ�Ի����е�where����
   */
  private void processQueryPara(IContext context) {
    String wherePart = this.getTranMap().getWherePart();
    String newWherePart = wherePart;
    // �����ֶμӱ����
    if (newWherePart
        .contains(SupplierStatCompRateConvertor.PK_GROUP_WITHOUT_ALIAS)) {
      newWherePart =
          wherePart.replace(
              SupplierStatCompRateConvertor.PK_GROUP_WITHOUT_ALIAS,
              SupplierStatCompRateConvertor.PK_GROUP_WITH_ALIAS);
    }
    // �����ֶμӱ����
    if (newWherePart
        .contains(SupplierStatCompRateConvertor.DBILLDATE_WITHOUT_ALIAS)) {
      newWherePart =
          newWherePart.replace(
              SupplierStatCompRateConvertor.DBILLDATE_WITHOUT_ALIAS,
              SupplierStatCompRateConvertor.DBILLDATE_WITH_ALIAS);
    }
    context.setAttribute(SupplierStatCompRateQryInfoPara.QUERY_WHERE,
        newWherePart);
    // ��ֹ����ȡ��ǰҵ������
    String deadlineDate =
        AppContext.getInstance().getBusiDate().asLocalEnd().toString();
    context.setAttribute(SupplierStatCompRateQryInfoPara.DEADLINEDATE,
        deadlineDate);
  }

  /**
   * �Լ������where����������̨��
   * 
   * @param condition ��ѯ�������ص�����
   * @param context ������context����
   */
  private void setQueryResult(IQueryCondition condition, IContext context) {
    if (condition == null || !(condition instanceof BaseQueryCondition)) {
      return;
    }
    // ��ȡ��ѯģ������
    this.processQueryPara(context);
    this.setCondOrgAttribute(context, SupplierStatCompRateConvertor.PK_ORG);
  }

  @Override
  protected void doBusinessProcess(IQueryCondition condition, IContext context) {
    super.doBusinessProcess(condition, context);
    this.setQueryResult(condition, context);
  }
  
  @Override
  public IQueryCondition getQueryCondition(
      ISubscribeQueryCondition subscribCondition, IContext context,
      AbsAnaReportModel rptModel) {
    IQueryCondition condition = super.getQueryCondition(subscribCondition, context, rptModel);
    BaseSubscribeCondition subCondition =
        (BaseSubscribeCondition) subscribCondition;
    // ����������õ�ִ��ʱ��
    TimeZone timezone =
        (TimeZone) context
            .getAttribute(FreeReportContextKey.REPORT_EXEC_TIMEZONE);
    // ��ö��ĵķ���
    IQueryScheme queryScheme = subCondition.getScheme();
    SCMRptQuerySchemeUtils.setDescription(this.getTranMap(), queryScheme, timezone);
    return condition;
  }
}
