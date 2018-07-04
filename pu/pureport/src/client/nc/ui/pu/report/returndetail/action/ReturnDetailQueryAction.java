package nc.ui.pu.report.returndetail.action;

import java.awt.Container;
import java.util.HashMap;
import java.util.Map;

import nc.adapter.ic.icreport.base.ICRptBaseScalePrcStrategy;
import nc.itf.iufo.freereport.extend.IQueryCondition;
import nc.ui.pu.report.pub.action.PURptDefaultQueryAction;
import nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer;
import nc.ui.querytemplate.QueryConditionDLG;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.ic.general.define.MetaNameConst;
import nc.vo.ic.m45.entity.PurchaseInBodyVO;
import nc.vo.ic.m45.entity.PurchaseInHeadVO;
import nc.vo.ic.pub.define.ICPubMetaNameConst;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.report.enumeration.ReturntypeEnum;
import nc.vo.pu.report.scale.m23.ReturnDetailScaleStrategy;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.query.ConditionVO;
import nc.vo.pubapp.query2.sql.process.NonMetaQuerySchemeProcessor;

import com.ufida.dataset.IContext;
import com.ufida.report.anareport.model.AbsAnaReportModel;

/**
 * 采购订单退货明细查询查询按钮
 * 
 * @since 6.0
 * @version 2011-3-10 上午09:14:02
 * @author yinfy
 */
public class ReturnDetailQueryAction extends PURptDefaultQueryAction {
  public static final String ARRIVAL_RTN = "arrival_rtn";

  public static final String CMATERIALOID = "PK_SRCMATERIAL";

  public static final String CVENDORID = "PK_SUPPLIER";

  public static final String INV_RTN = "inv_rtn";

  public static final String mainOrg = "PK_ORG";

  public static final String PK_PURCHASEORG = "PK_PURCHASEORG";

  public static final String RETURNTYPE = "RETURNTYPE";

  public static final String SRCMATERIAL = "pk_arriveorder_b."
      + OrderItemVO.PK_SRCMATERIAL;

  @Override
  public IQueryCondition doQueryByScheme(Container parent, IContext context,
      AbsAnaReportModel reportModel, IQueryScheme queryScheme) {
    IQueryCondition cond =
        super.doQueryByScheme(parent, context, reportModel, queryScheme);
    this.setQueryCondition(context, queryScheme);
    return cond;
  }

  @Override
  public IQueryConditionDLGInitializer getQryCondDLGInitializer() {
    return new ReturnDetailQueryDlgInit();
  }

  private Map<String, String> getArriveFieldCodeMap() {
    Map<String, String> map = new HashMap<String, String>();
    map.put(ReturnDetailQueryAction.CMATERIALOID,
        ReturnDetailQueryAction.SRCMATERIAL);
    map.put(ReturnDetailQueryAction.mainOrg, ArriveHeaderVO.PK_ORG);
    map.put(ReturnDetailQueryAction.PK_PURCHASEORG,
        ArriveHeaderVO.PK_PURCHASEORG);
    map.put(ReturnDetailQueryAction.CVENDORID, ArriveHeaderVO.PK_SUPPLIER);
    map.put(ArriveHeaderVO.DBILLDATE.toUpperCase(), ArriveHeaderVO.DBILLDATE);
    return map;
  }

  private Map<String, String> getPurchaseInFieldCodeMap() {
    Map<String, String> map = new HashMap<String, String>();
    map.put(ReturnDetailQueryAction.CMATERIALOID, MetaNameConst.CGENERALBID
        + "." + ICPubMetaNameConst.CMATERIALOID);
    map.put(ReturnDetailQueryAction.mainOrg, ICPubMetaNameConst.PK_ORG);
    map.put(ReturnDetailQueryAction.PK_PURCHASEORG, PurchaseInHeadVO.CPURORGOID);
    map.put(ReturnDetailQueryAction.CVENDORID, ICPubMetaNameConst.CVENDORID);
    map.put(ArriveHeaderVO.DBILLDATE.toUpperCase(),
        ICPubMetaNameConst.DBILLDATE);
    return map;
  }

  private void setArriveReturnWhere(IContext context, IQueryScheme qs) {
    NonMetaQuerySchemeProcessor processor =
        new NonMetaQuerySchemeProcessor(ArriveHeaderVO.class,
            this.getArriveFieldCodeMap(), qs);
    // 拼接上子表
    processor.getTableAliasOfAttribute(ArriveItemVO.PK_ARRIVEORDER_B + ".dr");
    processor.appendCurrentGroup();
    String htab =
        processor.getTableAliasOfAttribute(ArriveHeaderVO.PK_ARRIVEORDER);
    processor.appendWhere(" and " + htab + "." + ArriveHeaderVO.BISBACK + "='"
        + UFBoolean.TRUE + "'");
    String arrivefromwhere = processor.getFinalFromWhere();
    context.setAttribute(ReturnDetailQueryAction.ARRIVAL_RTN, arrivefromwhere);
  }

  private void setPurchaseInReturnWhere(IContext context, IQueryScheme qs) {
    NonMetaQuerySchemeProcessor processor =
        new NonMetaQuerySchemeProcessor(PurchaseInHeadVO.class,
            this.getPurchaseInFieldCodeMap(), qs);
    // 拼接上子表
    processor.getTableAliasOfAttribute(MetaNameConst.CGENERALBID + ".dr");
    processor.getTableAliasOfAttribute(MetaNameConst.CGENERALBID + "."
        + PurchaseInBodyVO.NREPLENISHEDNUM);
    processor.appendCurrentGroup();
    String htab = processor.getTableAliasOfAttribute(MetaNameConst.CGENERALHID);
    processor.appendWhere(" and " + htab + "."
        + PurchaseInHeadVO.FREPLENISHFLAG + "='" + UFBoolean.TRUE + "'");
    String fromwhere = processor.getFinalFromWhere();
    context.setAttribute(ReturnDetailQueryAction.INV_RTN, fromwhere);
  }

  private void setQueryCondition(IContext context, IQueryScheme queryscheme) {
    context.setAttribute(ReturnDetailQueryAction.ARRIVAL_RTN, "");
    context.setAttribute(ReturnDetailQueryAction.INV_RTN, "");
    ConditionVO[] conds =
        (ConditionVO[]) queryscheme.get(IQueryScheme.KEY_LOGICAL_CONDITION);
    ConditionVO rettypecond = null;
    for (ConditionVO cond : conds) {
      if (ReturnDetailQueryAction.RETURNTYPE.equals(cond.getFieldCode())) {
        rettypecond = cond;
        break;
      }

    }
    if (null == rettypecond || rettypecond.getValue().indexOf("(") >= 0) {
      // 退货条件处理
      this.setArriveReturnWhere(context, queryscheme);
      // 退库条件处理
      this.setPurchaseInReturnWhere(context, queryscheme);
    }
    else {
      if (rettypecond.getValue().equals(
          ReturntypeEnum.ARRIVAL_RTN.value().toString())) {
        // 退货条件处理
        this.setArriveReturnWhere(context, queryscheme);
      }
      else if (rettypecond.getValue().equals(
          ReturntypeEnum.INV_RTN.value().toString())) {
        // 退库条件处理
        this.setPurchaseInReturnWhere(context, queryscheme);
      }

    }
  }

  @Override
  protected Map<String, String> getColumnMapping() {
    Map<String, String> columnMapping = new HashMap<String, String>();
    columnMapping.put(ReturnDetailQueryAction.CMATERIALOID,
        ReturnDetailQueryAction.SRCMATERIAL);
    columnMapping.put(ReturnDetailQueryAction.CVENDORID,
        ArriveHeaderVO.PK_SUPPLIER);
    return columnMapping;
  }

  @Override
  protected Class<? extends ISuperVO> getMainClass() {
    return ArriveHeaderVO.class;
  }

  @Override
  protected String getMainOrgKey() {
    // 采购订单退货明细查询主组织是采购组织，查询模板功能权限会对主组织过滤，其它组织不过滤
    return ReturnDetailQueryAction.PK_PURCHASEORG;
  }

  @Override
  protected ICRptBaseScalePrcStrategy getScaleStrategy() {
    return new ReturnDetailScaleStrategy();
  }

  @Override
  protected IQueryCondition setQueryResult(IQueryCondition condition,
      QueryConditionDLG queryDlg, AbsAnaReportModel reportModel,
      IContext context) {
    this.setQueryCondition(context, queryDlg.getQueryScheme());
    return condition;
  }
}
