package nc.ui.pu.m27.match.action;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m4202.IVmiFinanceQuery;
import nc.ui.pu.m27.match.model.MatchManageModel;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m4202.entity.VmiFIHeaderVO;
import nc.vo.pu.pub.enumeration.PuNodeKey;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.SCMActionCode;

/**
 * 消耗汇总查询的Action，走正规的元数据查询
 * 
 * @since 6.0
 * @version 2011-3-29 下午08:51:07
 * @author 田锋涛
 */
public class VmiQueryAction2 extends AbstractStockQueryAction2 {
  private static final long serialVersionUID = -5667491871111125098L;

  public VmiQueryAction2() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.PU_VMIQUERY);
  }

  @Override
  public String getNodeKey() {
    return PuNodeKey.n4004140201.code();
  }

  /**
   * 这里做查询前的一些处理
   * 
   * @param queryScheme
   */
  @Override
  protected void beforeExecuteQuery(IQueryScheme queryScheme) {
    this.setInvoiceMaterialForScheme(queryScheme);
  }

  @Override
  protected void executeQuery(IQueryScheme queryScheme) {
    if (null == queryScheme) {
      return;
    }
    this.beforeExecuteQuery(queryScheme);
    IVmiFinanceQuery query =
        NCLocator.getInstance().lookup(IVmiFinanceQuery.class);

    try {
      StockSettleVO[] stockVos = query.queryVMIByScheme(queryScheme);
      // 把入库单VO放到Model中
      ((MatchManageModel) this.getModel()).initStock(stockVos);
      // 显示查询信息
      this.showQueryInfo();
      // 更新querySchme
      this.setCurQueryScheme(queryScheme);

    }
    catch (Exception e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
  }

  @Override
  protected String getFiOrgFieldName() {
    return VmiFIHeaderVO.PK_FINANCEORG;
  }

  @Override
  protected boolean hideAdvancePanel() {
    return false;
  }

  @Override
  protected boolean isActionEnable() {
    return this.getModel().getContext().getPk_org() != null;
  }
}
