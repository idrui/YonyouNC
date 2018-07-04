package nc.impl.pu.m4202;

import java.util.Map;
import java.util.Set;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.itf.pu.m4202.IVmiFinanceQuery;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.entity.StockSettleVOUtil;
import nc.vo.pu.m27.entity.VoiConsumeSettleVO;
import nc.vo.pu.m4201.enumeration.StockQryFieldCode;
import nc.vo.pu.m4202.entity.VmiFIHeaderVO;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QueryConstants;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.pubapp.res.Variable;

/**
 * 消耗汇总财务信息的查询服务实现类
 * 
 * @since 6.0
 * @version 2010-11-13 下午03:50:37
 * @author duy
 */
public class VmiFinanceQueryImpl implements IVmiFinanceQuery {

  @Override
  public StockSettleVO[] queryVMIByIds(String[] ids) throws BusinessException {
    try {
      ViewQuery<VoiConsumeSettleVO> query =
          new ViewQuery<VoiConsumeSettleVO>(VoiConsumeSettleVO.class);
      VoiConsumeSettleVO[] vos = query.query(ids);
      StockSettleVOUtil.calcStockCanSettle(vos);
      return vos;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public StockSettleVO[] queryVMIByScheme(IQueryScheme queryScheme)
      throws BusinessException {
    QuerySchemeProcessor processor = new QuerySchemeProcessor(queryScheme);
    String halias =
        processor.getTableAliasOfAttribute(VmiFIHeaderVO.PK_FINANCEORG);
    // String balias =
    // processor.getTableAliasOfAttribute(PUEntity.VMIFI_Fee_TAB + "."
    // + FeeEstVO.PK_FINANCEORG);
    SqlBuilder sql = new SqlBuilder();
    // sql.append("select po_vmifi.pk_stockps_b ");
    sql.append("select " + halias + ".");
    sql.append(VmiFIHeaderVO.PK_STOCKPS_B + " ");

    sql.append(queryScheme.getTableJoinFromWhereSQL());
    sql.append(this.getSettledWhere(queryScheme, halias));// 是否已经结算
    sql.append(this.getMaterialFilterWhere(queryScheme, halias));// 根据所选发票的物料过滤消耗汇总
    sql.append(" and isnull(" + halias + "." + VmiFIHeaderVO.NINNUM
        + ",0) <> 0 ");
    return this.queryVMIBySql(sql.toString());
  }

  @Override
  public StockSettleVO[] queryVMIBySql(String sql) throws BusinessException {
    try {
      DataAccessUtils utils = new DataAccessUtils();
      IRowSet rs = utils.query(sql);
      String[] ids = rs.toOneDimensionStringArray();
      // 检查最大返回行数
      int maxRow = Variable.getMaxQueryCount();
      if (ids.length > maxRow) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("common", "04004000-0000", null, new String[] {
              String.valueOf(maxRow)
            })/* 查询结果返回记录数超过{0}条，请修改查询条件减少查询记录数！ */);
      }
      return this.queryVMIByIds(ids);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   * 根据所选发票的物料过滤
   * 
   * @param queryScheme
   * @param halias
   * @return
   */
  private String getMaterialFilterWhere(IQueryScheme queryScheme, String halias) {
    Set<String> materialIds =
        (Set<String>) queryScheme.get(StockQryFieldCode.invoice_matrial_id
            .code());
    if (materialIds.size() == 0) {
      return "";
    }
    String[] invoiceMaterialIDs =
        materialIds.toArray(new String[materialIds.size()]);
    String qryName = halias + "." + VmiFIHeaderVO.PK_MATERIAL;
    String marInWhr =
        new IDExQueryBuilder(PUTempTable.tmp_po_4202_1.name()).buildSQL(
            qryName, invoiceMaterialIDs);
    return " and " + marInWhr;
  }

  /**
   * 是否已经结算
   * 
   * @param queryScheme
   * @param halias
   * @return
   */
  private String getSettledWhere(IQueryScheme queryScheme, String halias) {
    // 用户输入的所有查询条件
    Map<String, QueryCondition> logicalConditionMap =
        (Map<String, QueryCondition>) queryScheme
            .get(QueryConstants.QUERY_CONDITION);
    // 是否按已查出的发票过滤
    QueryCondition settledCond =
        logicalConditionMap.get(StockQryFieldCode.bsettled.code());
    if (settledCond == null) {
      return "";
    }
    String[] values = settledCond.getValues();
    // 已结算，累计结算数量大于0
    if (UFBoolean.valueOf(values[0]).booleanValue()) {
      return " and " + halias + "." + VmiFIHeaderVO.NACCUMSETTLENUM + " <> 0 ";
    }
    // 未结算，累计结算数量等于0
    return " and " + halias + "." + VmiFIHeaderVO.NACCUMSETTLENUM + " = 0 ";

  }

}
