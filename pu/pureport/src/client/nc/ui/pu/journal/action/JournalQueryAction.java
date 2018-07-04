package nc.ui.pu.journal.action;

import nc.ui.pu.journal.dlg.JournalQueryDlgInit;
import nc.ui.pu.report.pub.action.PuReportSubscribeAction;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;

/**
 * 综合日报 查询action
 * 
 * @since 6.0
 * @version 2011-4-7 下午04:56:07
 * @author liuchx
 */
public class JournalQueryAction extends PuReportSubscribeAction {

  public static final String CEMPLOYEEID = "cemployeeid";

  public static final String mainOrg = "pk_org";

  public static final String PK_DEPT = "pk_dept";

  public static final String PK_SUPPLIER = "pk_supplier";

  @Override
  protected IQueryConditionDLGInitializer getQueryConditionDLGInitializer() {
    return new JournalQueryDlgInit();
  }

}
