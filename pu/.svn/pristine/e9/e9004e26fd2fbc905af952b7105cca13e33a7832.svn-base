package nc.ui.pubapp.billref.src.action;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;

import nc.bs.framework.common.NCLocator;
import nc.bs.uif2.IActionCode;
import nc.funcnode.ui.action.INCAction;
import nc.itf.uap.pf.IPFConfig;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bizflow.IBillReferQueryWithScheme;
import nc.ui.pubapp.billref.src.DefaultBillReferQuery;
import nc.ui.pubapp.billref.src.RefBillModel;
import nc.ui.pubapp.billref.src.RefContext;
import nc.ui.pubapp.billref.src.action.ISuperAction;
import nc.ui.pubapp.uif2app.query2.QueryConditionDLGDelegator;
import nc.ui.pubapp.uif2app.query2.model.IRefQueryService;
import nc.ui.querytemplate.LayoutStyle;
import nc.ui.querytemplate.queryarea.IQueryExecutor;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.actions.ActionInitializer;
import nc.ui.uif2.model.AbstractUIAppModel;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.SuperVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.querytemplate.queryscheme.QuerySchemeVO;

/**
 * @author yangb
 */
@SuppressWarnings("serial")
public class QueryAction extends NCAction implements ISuperAction {
  /**
   * 外置查询方案Action
   */
  public class QuerySchemeAction extends NCAction {

    private static final long serialVersionUID = 12233444444444L;

    private boolean isDefault;// 是否默认查询方案

    private QuerySchemeVO qsvo;

    private AbstractUIAppModel schemeModel = null;

    public QuerySchemeAction(QuerySchemeVO qsvo) {
      this.qsvo = qsvo;   
      if (StringUtil.isEmpty(qsvo.getMultiLangName().toString())) { 
    	  String nameRes = NCLangRes.getInstance().getStrByID("uif2", "ActionRegistry-000008");/*查询*/
    	  this.putValue(Action.NAME, nameRes);
    	  this.putValue(INCAction.CODE, IActionCode.QUERY);
      } else {
    	  this.putValue(Action.NAME, qsvo.getMultiLangName().toString());
    	  this.putValue(INCAction.CODE, IActionCode.QUERY);
      }
    }

    @Override
    public void doAction(ActionEvent e) throws Exception {
      QueryAction.this.doSchemeAction(this.qsvo);
    }

    /**
     * 是否默认查询方案
     */
    public boolean isDefault() {
      return this.isDefault;
    }

    public void setModel(AbstractUIAppModel model) {
      this.schemeModel = model;
      this.schemeModel.addAppEventListener(this);
    }

    @Override
    protected boolean isActionEnable() {
      return true;
    }

    void setDefault(boolean isDefault) {
      this.isDefault = isDefault;
    }
  }

  List<QuerySchemeAction> schemeActions;

  private boolean isInitQuery;

  private IQueryExecutor queryExecutor;

  // private RefBillModel refBillModel;

  // private IQueryDelegator queryDelegator;

  private RefContext refContext;

  public QueryAction() {
    super();
    this.setBtnName(nc.ui.ml.NCLangRes.getInstance().getStrByID("common",
        "UC001-0000006")/* @res "查询" */);
    // setBtnChinaName("查询");
    this.putValue(Action.SHORT_DESCRIPTION, nc.ui.ml.NCLangRes.getInstance()
        .getStrByID("common", "UC001-0000006")/* @res "查询" */);
    ActionInitializer.initializeAction(this, IActionCode.QUERY);
    this.putValue(Action.MNEMONIC_KEY, Integer.valueOf(KeyEvent.VK_F3));
//	putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke("F3"));
  
    
    // 不设这个空model会抛空，无法适配uap的queryaction
    // this.setModel(new BillManageModel());
    this.queryExecutor = new IQueryExecutor() {

      @Override
      public void doQuery(IQueryScheme queryScheme) {
        DefaultBillReferQuery referQuery =
            (DefaultBillReferQuery) QueryAction.this.getRefContext()
                .getRefDialog().getQueyDlg();
        // QueryConditionDLGDelegator qcd = referQuery.getQryDLGDelegator();
        // qcd.fillQuerySheme(queryScheme);
        QueryAction.this.doQuery(queryScheme);
      }

    };
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    IBillReferQueryWithScheme queryCondition =
        (IBillReferQueryWithScheme) this.getRefContext().getRefDialog()
            .getQueyDlg();
    if (!this.isInitQuery()) {
      if (queryCondition.showModal() != UIDialog.ID_OK) {
        return;
      }
    }
    this.queryExecutor.doQuery(queryCondition.getQueryScheme());
  }

  @Override
  public RefBillModel getRefBillModel() {
    return this.refContext.getRefBill().getRefBillModel();
  }

  @Override
  public RefContext getRefContext() {
    return this.refContext;
  }

  /**
   * 返回所有查询方案的Action列表
   */
  public List<QuerySchemeAction> getSchemeActions() {
    if (this.schemeActions == null) {
      this.schemeActions = new ArrayList<QuerySchemeAction>();
      DefaultBillReferQuery referQuery =
          (DefaultBillReferQuery) this.getRefContext().getRefDialog()
              .getQueyDlg();
      QueryConditionDLGDelegator queryCondDLGDelegator =
          referQuery.getQryDLGDelegator();
      QuerySchemeVO[] vos = queryCondDLGDelegator.getQuerySchemeVOs();
      if (vos != null) {
        for (QuerySchemeVO vo : vos) {
          QuerySchemeAction action = new QuerySchemeAction(vo);
          action.setDefault(vo.isDefault());
          // action.setModel(this.model);
          this.schemeActions.add(action);
        }
      }

    }
    return this.schemeActions;
  }

  // @Override
  // public IQueryDelegator getQueryDelegator() {
  // if (null == this.queryDelegator) {
  // this.queryDelegator = new DefaultQueryDelegator() {
  // @Override
  // public QueryConditionDLG getQueryDlg() {
  // return ((DefaultBillReferQuery) QueryAction.this.getRefContext()
  // .getRefDialog().getQueyDlg()).getQueryDlg();
  // }
  //
  // @Override
  // public TemplateInfo getTemplateInfo() {
  // return this.getQueryDlg().getTempInfo();
  // }
  // };
  // this.setQueryDelegator(this.queryDelegator);
  // }
  // return this.queryDelegator;
  // }

  public boolean isInitQuery() {
    return this.isInitQuery;
  }

  public void setInitQuery(boolean initQuery) {
    this.isInitQuery = initQuery;
  }

  @Override
  public void setRefBillModel(RefBillModel refBillModel) {
    // this.refBillModel = refBillModel;
  }

  @Override
  public void setRefContext(RefContext refContext) {
    this.refContext = refContext;
  }

  // @Override
  // public void setQueryDelegator(IQueryDelegator queryDelegator) {
  // this.queryDelegator = queryDelegator;
  // }

  // @Override
  protected void executeQuery(IQueryScheme queryScheme) {
    try {
      DefaultBillReferQuery referQuery =
          (DefaultBillReferQuery) this.getRefContext().getRefDialog()
              .getQueyDlg();
      Object queryService = this.getRefContext().getRefInfo().getQueryService();
      if (queryService != null) {
        if (queryService instanceof IRefQueryService) {
          AggregatedValueObject[] billvos =
              (AggregatedValueObject[]) ((IRefQueryService) queryService)
                  .queryByQueryScheme(queryScheme);
          this.getRefBillModel().setBillVOs(billvos);
        }
        return;
      }
      this.getRefBillModel()
          .setSqlWhere(referQuery.getQueryDlg().getWhereSQL());
      this.getRefBillModel().setQueryScheme(referQuery.getQueryScheme());
      IPFConfig pfConfig =
          (IPFConfig) NCLocator.getInstance().lookup(IPFConfig.class.getName());
      CircularlyAccessibleValueObject[] tmpHeadVo =
          pfConfig.queryHeadAllData(this.getRefContext().getRefInfo()
              .getBillSrcVar().getBillType(), referQuery.getWhereSQL());
      SuperVO[] superHeadVOs = null;
      if (tmpHeadVo != null && tmpHeadVo.length > 0) {
        superHeadVOs =
            (SuperVO[]) Array.newInstance(tmpHeadVo[0].getClass(),
                tmpHeadVo.length);
        System.arraycopy(tmpHeadVo, 0, superHeadVOs, 0, tmpHeadVo.length);
      }

      this.getRefBillModel().setHeadVOs(superHeadVOs);
    }
    catch (Exception ex) {
      ExceptionUtils.wrappException(ex);
    }
    finally {
      this.setInitQuery(false);
    }
  }

  protected void executeQuery(String sqlWhere) {
    try {
      DefaultBillReferQuery referQuery =
          (DefaultBillReferQuery) this.getRefContext().getRefDialog()
              .getQueyDlg();
      if (this.getRefContext().getRefInfo().getQueryService() != null) {
        nc.ui.pubapp.uif2app.model.IQueryService queryService =
            this.getRefContext().getRefInfo().getQueryService();
        AggregatedValueObject[] billvos =
            (AggregatedValueObject[]) queryService.queryByWhereSql(sqlWhere);
        this.getRefBillModel().setBillVOs(billvos);
        return;
      }
      this.getRefBillModel()
          .setSqlWhere(referQuery.getQueryDlg().getWhereSQL());
      this.getRefBillModel().setQueryScheme(referQuery.getQueryScheme());
      IPFConfig pfConfig =
          (IPFConfig) NCLocator.getInstance().lookup(IPFConfig.class.getName());
      CircularlyAccessibleValueObject[] tmpHeadVo =
          pfConfig.queryHeadAllData(this.getRefContext().getRefInfo()
              .getBillSrcVar().getBillType(), referQuery.getWhereSQL());
      SuperVO[] superHeadVOs = null;
      if (tmpHeadVo != null && tmpHeadVo.length > 0) {
        superHeadVOs =
            (SuperVO[]) Array.newInstance(tmpHeadVo[0].getClass(),
                tmpHeadVo.length);
        System.arraycopy(tmpHeadVo, 0, superHeadVOs, 0, tmpHeadVo.length);
      }

      this.getRefBillModel().setHeadVOs(superHeadVOs);
    }
    catch (Exception ex) {
      ExceptionUtils.wrappException(ex);
    }
    finally {
      this.setInitQuery(false);
    }
  }

  @Override
  protected boolean isActionEnable() {
    return true;
  }

  /*
   * (non-Javadoc)
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  void doQuery(IQueryScheme queryScheme) {
    DefaultBillReferQuery referQuery =
        (DefaultBillReferQuery) this.getRefContext().getRefDialog()
            .getQueyDlg();
    // IBillReferQueryWithScheme queryCondition =
    // (IBillReferQueryWithScheme) this.getRefContext().getRefDialog()
    // .getQueyDlg();
    try {
      Object queryService = this.getRefContext().getRefInfo().getQueryService();
      if (queryService != null) {
        if (queryService instanceof IRefQueryService) {
          this.executeQuery(queryScheme);
        }
        else {
          this.executeQuery(referQuery.getQueryDlg().getWhereSQL());
        }
      }
      else {
        this.executeQuery(referQuery.getQueryDlg().getWhereSQL());

      }

      // String swhere = queryCondition.getWhereSQL();
      // if (swhere == null) {
      // swhere = "1=1";
      // }
    }
    catch (Exception ex) {
      ExceptionUtils.wrappException(ex);
    }
    finally {
      this.setInitQuery(false);
    }
  }

  void doSchemeAction(QuerySchemeVO vo) {
    DefaultBillReferQuery referQuery =
        (DefaultBillReferQuery) this.getRefContext().getRefDialog()
            .getQueyDlg();
    QueryConditionDLGDelegator qcd = referQuery.getQryDLGDelegator();
    if (qcd != null) {
      //拉单的查询按钮组下的新增查询方案对应查询按钮需通过此方式来获取IQueryScheme
      IQueryScheme scheme = qcd.getQuerySchemeByObject(vo.getQSObject4Blob());
      boolean validityFlag = checkCondition(qcd);
      if (validityFlag) {
    	  this.queryExecutor.doQuery(scheme);
      }
    }
  }
  
  protected boolean checkCondition(QueryConditionDLGDelegator qcd) {
		// 进行数据检查
		String msg = qcd.getQueryConditionDLG().checkCondition();

		if (msg != null) {
			int res = MessageDialog.showWarningDlg(null, nc.ui.ml.NCLangRes.getInstance().getStrByID("_Template","UPP_Template-000037")/* 警告 */, msg);
			if (res == MessageDialog.ID_CANCEL) {// 强行关闭警告提示框,认为取消查询 
				// 如果单击确定,则聚焦未完成必输项
				qcd.getQueryConditionDLG().closeCancel();
				return false;
			}
		} else {
			qcd.getQueryConditionDLG().closeOK();
			qcd.getQueryConditionDLG().getQryCondEditor().getQueryContext().setLayoutStyle(LayoutStyle.QUERY_AREA);
			return true;
		}
		
		return false;
	}
}
