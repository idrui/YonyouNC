package nc.bs.pu.m4203;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.vo.pu.m27.entity.StockSettleVOUtil;
import nc.vo.pu.m27.entity.SubcontInSettleVO;
import nc.vo.pu.m4203.entity.SubcontinFIItemVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.res.Variable;
import nc.vo.scmpub.util.FromWhereParseUtil;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 委托加工入财务的查询BP
 * 
 * @since 6.0
 * @version 2011-1-22 下午06:11:35
 * @author zhyhang
 */
public class SubcontinFIQueryBP {

  /**
   * 根据传入的查询条件，查询可结算的委托加工入结算VO
   * 
   * @param frmWhr 带FROM和WHERE部分的查询SQL
   * @return
   */
  public SubcontInSettleVO[] settleQuery(String frmWhr) {
    if (StringUtils.isEmpty(frmWhr)) {
      return null;
    }
    String sql = this.getSettleQuerySql(frmWhr);
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
    SubcontInSettleVO[] vos =
        new ViewQuery<SubcontInSettleVO>(SubcontInSettleVO.class)
            .query(itemIDs);
    // 计算未结算数量、金额，本次结算数量等
    StockSettleVOUtil.calcStockCanSettle(vos);
    return vos;
  }

  private String getSettleQuerySql(String frmWhr) {
    FromWhereParseUtil util = new FromWhereParseUtil(frmWhr);
    // String halias = util.getTableAlias(PUEntity.SUBCONTIN_H_TAB);
    String balias = util.getTableAlias(PUEntity.SUBCONTIN_B_TAB);
    SqlBuilder builder = new SqlBuilder();
    builder.append("select " + balias + "." + SubcontinFIItemVO.PK_STOCKPS_B);
    builder.append(" " + frmWhr);
    // builder.append(" and ");
    // builder.append(halias);
    // builder.append(".dr", 0);
    // builder.append(" and " + balias + ".dr", 0);
    return builder.toString();
  }

}
