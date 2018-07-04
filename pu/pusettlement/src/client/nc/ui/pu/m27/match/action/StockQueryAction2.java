package nc.ui.pu.m27.match.action;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m27.ISettleBillQuery;
import nc.ui.pu.m27.match.model.MatchManageModel;
import nc.ui.pu.m27.match.view.query.SettleQueryInfo;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>构造"入库单"查询按钮
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-1-6 下午02:05:38
 */
public class StockQueryAction2 extends AbstractStockQueryAction2 {
  private static final long serialVersionUID = -5667491871111125098L;

  private SettleQueryInfo m45QueryInfo;

  private SettleQueryInfo m47QueryInfo;

  private SettleQueryInfo m4AQueryInfo;

  private SettleQueryInfo m4TQueryInfo;

  public StockQueryAction2() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.PU_STOCKQUERY);
  }

  public SettleQueryInfo getM45QueryInfo() {
    return this.m45QueryInfo;
  }

  public SettleQueryInfo getM47QueryInfo() {
    return this.m47QueryInfo;
  }

  public SettleQueryInfo getM4AQueryInfo() {
    return this.m4AQueryInfo;
  }

  public SettleQueryInfo getM4TQueryInfo() {
    return this.m4TQueryInfo;
  }

  public void setM45QueryInfo(SettleQueryInfo m45QueryInfo) {
    this.m45QueryInfo = m45QueryInfo;
  }

  public void setM47QueryInfo(SettleQueryInfo m47QueryInfo) {
    this.m47QueryInfo = m47QueryInfo;
  }

  public void setM4AQueryInfo(SettleQueryInfo m4aQueryInfo) {
    this.m4AQueryInfo = m4aQueryInfo;
  }

  public void setM4TQueryInfo(SettleQueryInfo m4tQueryInfo) {
    this.m4TQueryInfo = m4tQueryInfo;
  }

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
    ISettleBillQuery service =
        NCLocator.getInstance().lookup(ISettleBillQuery.class);
    try {
      // 将各种单据类型的字段对照放到scheme里
      queryScheme.put(ICBillType.SubContinIn.getCode(), this.getM47QueryInfo()
          .getAttrPathMap());
      queryScheme.put(POBillType.InitEstimate.getCode(), this.getM4TQueryInfo()
          .getAttrPathMap());
      queryScheme.put(ICBillType.GeneralIn.getCode(), this.getM4AQueryInfo()
          .getAttrPathMap());
      queryScheme.put(ICBillType.PurchaseIn.getCode(), this.getM45QueryInfo()
          .getAttrPathMap());
      // 查询入库单VO
      StockSettleVO[] stockVOs = service.queryStockByQueryScheme(queryScheme);

      // 把入库单VO放到Model中
      ((MatchManageModel) this.getModel()).initStock(stockVOs);
      this.setCurQueryScheme(queryScheme);
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
  }
  
  @Override
  protected boolean hideAdvancePanel(){
    return false;
  }
}
