/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-17 上午11:10:18
 */
package nc.ui.pu.est.action.m50;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.est.m50.IVMIEstQuery;
import nc.ui.pu.est.model.EstUIContext;
import nc.ui.pu.est.util.EstRefFilterUtil;
import nc.ui.pu.est.view.EstEditor;
import nc.ui.pu.est.view.EstEditorInitializer;
import nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.uif2.model.ModelDataDescriptor;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.est.entity.FeeEstVO;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.est.entity.m50.VmiEstVO;
import nc.vo.pu.est.enumeration.QueryEstType;
import nc.vo.pu.est.enumeration.QueryNonMetaFieldCode;
import nc.vo.pu.est.util.EstVOUtil;
import nc.vo.pu.m4202.entity.VmiFIHeaderVO;
import nc.vo.pu.pub.util.VOSortUtils;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购入库单暂估（取消暂估）查询action
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-17 上午11:10:18
 */
public class VMIEstQueryAction extends DefaultQueryAction {

  /**
   * 
   */
  private static final long serialVersionUID = -3851502025465825217L;

  private EstEditor editor;

  protected IQueryScheme queryScheme = null;

  /**
   * @return editor
   */
  public EstEditor getEditor() {
    return this.editor;
  }

  /** 得到用户所选择的结算财务组织(单选必输) **/
  public String getFIOrg(QuerySchemeProcessor qrySchemeProcessor) {

    QueryCondition queryCondition =
        qrySchemeProcessor.getQueryCondition(GoodsEstVO.PK_FINANCEORG);
    if (queryCondition == null) {
      return null;
    }
    if (!ArrayUtils.isEmpty(queryCondition.getValues())) {
      return queryCondition.getValues()[0];
    }
    return null;
  }

  public IQueryScheme getQueryScheme() {
    return this.queryScheme;
  }

  /**
   * @param editor 要设置的 editor
   */
  public void setEditor(EstEditor editor) {
    this.editor = editor;
  }

  /** 查询时设置的暂估类型 */
  private QueryEstType getEstQryType(QuerySchemeProcessor qrySchemeProcessor) {
    QueryCondition vo =
        qrySchemeProcessor.getQueryCondition(QueryNonMetaFieldCode.festtype
            .name());
    if (vo == null) {
      return QueryEstType.GOODS_EST;
    }
    Object[] cond = vo.getValues();

    if (!ArrayUtils.isEmpty(cond)) {
      return QueryEstType.valueOf((String) cond[0]);
    }
    return QueryEstType.GOODS_EST;
  }

  private void initAfterQuery(VmiEstVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    // 计算合计相关
    EstVOUtil.calculateTotal(vos);
    this.initContext(vos);
    this.initRef();
  }

  private void initBeforeQuery(QueryEstType queryEstType) {
    this.initContext(queryEstType);
    this.initUI();
  }

  private void initContext(QueryEstType queryEstType) {
    EstUIContext context = this.getContext();
    // 货物暂估和费用暂估标志
    context.setEsttype(queryEstType);
  }

  private void initRef() {
    EstRefFilterUtil util =
        new EstRefFilterUtil(this.editor.getBillCardPanel());
    String pk_fiorg = this.getContext().getPk_fiorg();
    // 表体供应商
    util.filterSupplier(FeeEstVO.PK_SUPPLIER, pk_fiorg);
  }

  /** 查询是否包括已经进行过费用暂估过的行 **/
  private UFBoolean isQryFeeEstimatedData(
      QuerySchemeProcessor qrySchemeProcessor) {
    QueryCondition vo =
        qrySchemeProcessor.getQueryCondition(QueryNonMetaFieldCode.bestfee
            .name());
    if (vo == null) {
      return UFBoolean.FALSE;
    }
    Object[] cond = vo.getValues();
    if (!ArrayUtils.isEmpty(cond)) {
      return ValueUtils.getUFBoolean(cond[0]);
    }
    return UFBoolean.FALSE;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.actions.QueryAction#executeQuery(java.lang.String)
   */
  @Override
  protected void executeQuery(IQueryScheme queryScheme) {
    this.queryScheme = queryScheme;

    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    QueryEstType queryEstType = this.getEstQryType(qrySchemeProcessor);
    this.initBeforeQuery(queryEstType);
    IVMIEstQuery srv = NCLocator.getInstance().lookup(IVMIEstQuery.class);
    VmiEstVO[] vos = null;
    try {
      String pk_fiorg = this.getFIOrg(qrySchemeProcessor);
      if (QueryEstType.GOODS_EST == this.getContext().getEsttype()) {
        vos = srv.goodsEstQuery(queryScheme, pk_fiorg);
      }
      else {
        vos =
            srv.feeEstQuery(queryScheme,
                this.isQryFeeEstimatedData(qrySchemeProcessor), pk_fiorg); // 费用暂估查询
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    VOSortUtils.sortVOs(vos, VmiFIHeaderVO.VBILLCODE);
    this.initAfterQuery(vos);

    String schemeName = queryScheme.getName();
    ModelDataDescriptor modelDataDescriptor = null;
    if (!StringUtil.isEmptyWithTrim(schemeName)) {
      modelDataDescriptor = new ModelDataDescriptor(schemeName);
    }
    else {
      modelDataDescriptor =
          new ModelDataDescriptor(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("pubapp_0", "0pubapp-0144")/*@res "查询结果"*/);
    }
    this.getModel().initModel(vos, modelDataDescriptor);
  }

  protected EstUIContext getContext() {
    EstUIContext context = (EstUIContext) this.getModel().getContext();
    return context;
  }

  protected void initContext(VmiEstVO[] vos) {
    EstUIContext context = this.getContext();
    // 当前财务组织
    context.setPk_fiorg(vos[0].getParentVO().getPk_financeorg());
  }

  protected void initUI() {
    EstEditorInitializer initializer = new EstEditorInitializer(this.editor);
    // 初始化模板一些数据
    initializer.setEditable();
    // 初始化模板头（货物暂估精度+单据本身字段精度）
    initializer.setStockScale(EstVOUtil.getGoodsEstScaleKeyInfo(),
        EstVOUtil.getVmiStockScaleKeyInfo());
    // 初始化模板体(费用暂估精度)
    initializer.setFeeScale(EstVOUtil.getFeeEstScaleKeyInfo());
  }
}
