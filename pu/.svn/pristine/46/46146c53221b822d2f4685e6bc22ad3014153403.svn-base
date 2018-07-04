package nc.ui.pu.report.settlestat;

import nc.ui.pu.report.pub.action.PuReportSubscribeAction;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;

/**
 * 暂估月统计订阅查询设置
 * 
 * @since 6.0
 * @version 2011-8-2 下午04:49:42
 * @author 田锋涛
 */
public class SettleStatSubscribeAction extends PuReportSubscribeAction {

  public static final String CWHSMANAGERID = "cwhsmanagerid";

  public static final String mainOrg = "pk_financeorg";

  public static final String MATERIALNAME = "materialname";

  public static final String PK_DEPT = "pk_dept";

  public static final String PK_MARBASCLASS = "pk_marbasclass";

  public static final String PK_PSNDOC = "pk_psndoc";

  public static final String PK_PURCHASEORG = "pk_purchaseorg";

  public static final String PK_SRCMATERIAL = "pk_srcmaterial";

  public static final String PK_STORDOC = "pk_stordoc";

  public static final String PK_STOREORG = "pk_storeorg";

  public static final String PK_SUPPLIER = "pk_supplier";

  public static final String SUPPLIERNAME = "suppliername";

  @Override
  protected IQueryConditionDLGInitializer getQueryConditionDLGInitializer() {
    return new SettleStatQryDlgInitializer();
  }

  /**
   * 初始化查询代理（如：校验类注册）
   */
  @Override
  protected void initQueryConditionDLGDelegator() {
    //
  }
}
