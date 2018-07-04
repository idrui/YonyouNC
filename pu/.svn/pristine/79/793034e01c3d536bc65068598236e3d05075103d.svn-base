package nc.impl.pu.m4201;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.bs.pu.m4203.SubcontinFIQueryBP;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.itf.pu.m4201.IStockFinanceQuery;
import nc.itf.pu.m4202.IVmiFinanceQuery;
import nc.vo.ic.m4a.entity.GeneralInVO;
import nc.vo.ic.m4e.entity.TransInVO;
import nc.vo.pu.m27.entity.PurchaseInSettleVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.entity.StockSettleVOUtil;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pu.m4201.entity.PurchaseinFIVO;
import nc.vo.pu.m4203.entity.SubcontinFIHeaderVO;
import nc.vo.pu.m4203.entity.SubcontinFIItemVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.res.Variable;
import nc.vo.scmpub.util.FromWhereParseUtil;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>库存结算信息查询类
 * <li>
 * <li>...
 * </ul>
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version 本版本号
 * @since 上一版本号
 * @author wangyf
 * @time 2010-4-6 上午09:45:54
 */
public class StockSettleQuerySrvImpl implements IStockFinanceQuery {

  /**
   * 方法功能描述：过滤得到符合结算的itemvo。
   * <p>
   * <b>参数说明</b>
   * 
   * @param itemVOs
   * @return <p>
   * @since 6.0
   * @author tianft
   * @time 2010-1-15 上午11:05:05
   */
  public String[] filterStockManageItemVOs(PurchaseinFIItemVO[] itemVOs) {

    if (ArrayUtils.isEmpty(itemVOs)) {
      return null;
    }
    return null;
  }

  @Override
  public GeneralInVO[] queryGeneralinAggVOs(String[] hids, String[] bids) {
    return null;
  }

  @Override
  public PurchaseinFIVO[] queryPurchaseinAggVOs(String[] hids, String[] bids) {
    // WYF 需再根据bids过滤
    try {
      PurchaseinFIVO[] stockVOs =
          new BillQuery<PurchaseinFIVO>(PurchaseinFIVO.class).query(hids);

      return stockVOs;
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  @Override
  public StockSettleVO[] queryPurchaseInByBID(String[] bid)
      throws BusinessException {
    if (bid == null) {
      return null;
    }
    try {
      ViewQuery<PurchaseInSettleVO> vQuery =
          new ViewQuery<PurchaseInSettleVO>(PurchaseInSettleVO.class);
      StockSettleVO[] voaStock = vQuery.query(bid);
      if (voaStock != null) {
        // 计算未结算数量、金额
        StockSettleVOUtil.calcStockCanSettle(voaStock);
      }
      return voaStock;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public StockSettleVO[] queryPurchaseInByOrderBID(String[] bid)
      throws BusinessException {
    try {
      IDExQueryBuilder ids =
          new IDExQueryBuilder(PUTempTable.tmp_po_4203_2.name());
      SqlBuilder sql = new SqlBuilder();
      sql.append("select distinct pk_stockps_b from po_purchaseinfi_b where dr=0 and ");
      sql.append(ids.buildSQL("pk_order_b", bid));
      DataAccessUtils util = new DataAccessUtils();
      IRowSet result = util.query(sql.toString());
      return this.queryPurchaseInByBID(result.toOneDimensionStringArray());
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public StockSettleVO[] queryPurchaseInByWhereSql(String fandw)
      throws BusinessException {
    try {
      String sql = this.getM45QuerySql(fandw);
      if (StringUtils.isEmpty(sql)) {
        return null;
      }

      DataAccessUtils dataAccessUtils = new DataAccessUtils();
      // 根据查询条件得到主表ID数组
      String[] itemIDs = dataAccessUtils.query(sql).toOneDimensionStringArray();

      if (ArrayUtils.isEmpty(itemIDs)) {
        return null;
      }
      // 检查最大返回行数
      int maxRow = Variable.getMaxQueryCount();
      if (itemIDs.length > maxRow) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("common", "04004000-0000", null, new String[] {
              String.valueOf(maxRow)
            })/* 查询结果返回记录数超过{0}条，请修改查询条件减少查询记录数！ */);
      }

      // 根据子表ID查询
      StockSettleVO[] stockSettVOs =
          new ViewQuery<PurchaseInSettleVO>(PurchaseInSettleVO.class)
              .query(itemIDs);
      // 计算未结算数量、金额
      StockSettleVOUtil.calcStockCanSettle(stockSettVOs);

      return stockSettVOs;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public PurchaseinFIVO[] querySubcontractAggVOs(String[] hids, String[] bids) {
    // WYF PurchaseinFIVO[]需要替换为具体的委外入VO[]
    return null;
  }

  @Override
  public StockSettleVO[] querySubcontractByBID(String[] bid)
      throws BusinessException {
    try {
      SqlBuilder frmWhr = this.getFromWhr();// 以无别名的方式返回委托加工财务主子查询连接条件
      IDExQueryBuilder idqb =
          new IDExQueryBuilder(PUTempTable.tmp_po_4203_3.name());
      String name =
          PUEntity.SUBCONTIN_B_TAB + "." + SubcontinFIItemVO.PK_STOCKPS_B;
      idqb.buildSQL(name, bid);
      frmWhr.append(idqb.buildSQL(name, bid));
      return new SubcontinFIQueryBP().settleQuery(frmWhr.toString());
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public StockSettleVO[] querySubcontractByOrderBID(String[] bid)
      throws BusinessException {
    try {
      SqlBuilder frmWhr = this.getFromWhr();// 以无别名的方式返回委托加工财务主子查询连接条件
      IDExQueryBuilder idqb =
          new IDExQueryBuilder(PUTempTable.tmp_po_4203_4.name());
      String name =
          PUEntity.SUBCONTIN_B_TAB + "." + SubcontinFIItemVO.PK_ORDER_B;
      idqb.buildSQL(name, bid);
      frmWhr.append(idqb.buildSQL(name, bid));
      return new SubcontinFIQueryBP().settleQuery(frmWhr.toString());
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;

  }

  @Override
  public TransInVO[] queryTransinAggVOs(String[] hids, String[] bids) {
    return null;
  }

  @Override
  public PurchaseinFIVO[] queryVoiconsumeAggVOs(String[] hids, String[] bids) {
    // WYF PurchaseinFIVO[]需要替换为具体消耗汇总VO[]
    return null;
  }

  @Override
  public StockSettleVO[] queryVoiconsumeByBID(String[] bid)
      throws BusinessException {
    try {
      return NCLocator.getInstance().lookup(IVmiFinanceQuery.class)
          .queryVMIByIds(bid);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  private SqlBuilder getFromWhr() {
    SqlBuilder frmWhr = new SqlBuilder();
    frmWhr.append(" from ");
    frmWhr.append(PUEntity.SUBCONTIN_H_TAB);
    frmWhr.append(" inner join ");
    frmWhr.append(PUEntity.SUBCONTIN_B_TAB);
    frmWhr.append(" on ");
    frmWhr.append(PUEntity.SUBCONTIN_H_TAB);
    frmWhr.append(".");
    frmWhr.append(SubcontinFIHeaderVO.PK_STOCKPS);
    frmWhr.append("=");
    frmWhr.append(PUEntity.SUBCONTIN_B_TAB);
    frmWhr.append(".");
    frmWhr.append(SubcontinFIItemVO.PK_STOCKPS);
    frmWhr.append(" where ");
    return frmWhr;
  }

  /**
   * 方法功能描述：获得查询SQL语句<br>
   * a)
   * 采购入库单“寄存供应商”字段为空的采购入库单（“寄存供应商”字段非空则为VMI采购业务：即将原来根据VMI仓库的属性判断是否可参与采购结算、可暂估，
   * 调整为根据采购入库单该字段是否非空来判断）<br>
   * 副本生成时已经过滤掉，这里不用再考虑 by zhaoyha at 2011.1.29<br>
   * b) 已全部结算的采购入库单只能参与费用结算（因此此处不进行过滤，仍然需要查询出来）<br>
   * c) 资产类存货入资产仓满足以上条件也可以参与结算（因此此处不进行过滤，仍然需要查询出来）<br>
   * d) 非赠品<br>
   * <p>
   * <b>参数说明</b>
   * 
   * @param sqlWhere
   * @return <p>
   * @since 6.0
   * @author duy
   * @time 2010-7-3 下午02:31:08
   */
  private String getM45QuerySql(String sqlWhere) {
    if (StringUtils.isEmpty(sqlWhere)) {
      return null;
    }
    FromWhereParseUtil util = new FromWhereParseUtil(sqlWhere);
    String alias = util.getTableAlias("po_purchaseinfi_b");

    SqlBuilder builder = new SqlBuilder();
    builder.append("select " + alias + ".pk_stockps_b ");
    builder.append(sqlWhere);
    builder.append(" and po_purchaseinfi.dr", 0);
    builder.append(" and " + alias + ".dr", 0);
    // 非赠品
    builder.append(" and isnull(" + alias + ".blargess, 'N')", "N");
    return builder.toString();
  }

}
