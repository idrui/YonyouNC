package nc.bs.pu.m27.settlebill.rule;

import java.util.HashSet;
import java.util.Set;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.est.entity.FeeEstDistVO;
import nc.vo.pu.est.entity.m45.PurchaseinFIFDVO;
import nc.vo.pu.est.entity.m50.VmiFIFDVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.SettleFeeAllotDetailVO;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.billtype.ICBillType;

/**
 * 
 * @description
 * 结算单删除/取消传存货时，检查是否进行过费用分摊
 * （存在费用结算，或者存在费用暂估分摊过结算明细）；
 * 如果存在费用分摊，需先将费用结算单删除。
 * @scene
 * 取消传存货核算,删除结算单
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-22 下午3:58:52
 * @author zhangshqb
 */
public class FeeSettleCheckRule implements IRule<SettleBillVO> {

  public FeeSettleCheckRule() {
    //
  }

  @Override
  public void process(SettleBillVO[] vos) {
    Set<String> bids = new HashSet<String>();
    Set<String> hids = new HashSet<String>();
    Set<String> m45StlBids = new HashSet<String>();
    Set<String> m50StlBids = new HashSet<String>();
    for (SettleBillVO vo : vos) {
      for (SettleBillItemVO item : vo.getChildrenVO()) {
        bids.add(item.getPk_settlebill_b());
        if (ICBillType.VmiSum.getCode().equals(item.getVstockbilltype())) {
          m50StlBids.add(item.getPk_stock_b());
        }
        else if (ICBillType.PurchaseIn.getCode().equals(
            item.getVstockbilltype())) {
          m45StlBids.add(item.getPk_stock_b());
        }
      }
      hids.add(vo.getParentVO().getPk_settlebill());
    }
    String sql = this.getQuerySql(bids, m45StlBids, m50StlBids, hids);
    DataAccessUtils util = new DataAccessUtils();
    IRowSet rowSet = util.query(sql);
    while (rowSet.next()) {
      int count = rowSet.getInt(0);
      if (count > 0) {
        ExceptionUtils
            .wrappBusinessException(NCLangResOnserver.getInstance().getStrByID(
                "4004060_0", "04004060-0240")/* 存在后续费用结算单或费用暂估，无法执行此操作！ */);
      }
    }
  }

  /**
   * 费用暂估校验，包括采购入和消耗汇总两个。
   * 
   * @param inWhere
   * @param fdClass
   * @return
   */
  private String getFeeEstQuerySql(String inWhere,
      Class<? extends FeeEstDistVO> fdClass) {
    SqlBuilder sqlbuilder = new SqlBuilder();
    try {
      FeeEstDistVO fdVo = fdClass.newInstance();
      String fdTab =
          fdVo.getMetaData().getPrimaryAttribute().getColumn().getTable()
              .getName();
      sqlbuilder.append(" select count(*) from  " + fdTab + " where dr=0 and ");
      sqlbuilder.append(inWhere.replace("po_settle_feeallot."
          + SettleFeeAllotDetailVO.PK_ALLOTBILLBID,
          FeeEstDistVO.PK_DISTBYBILL_B));
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return sqlbuilder.toString();
  }

  /**
   * 结算单bid临时表创建
   * 
   * @param bids
   * @return
   */
  private String getInWhereSql(Set<String> bids) {
    return new IDExQueryBuilder(PUTempTable.tmp_po_27_06.name()).buildSQL(
        " po_settle_feeallot." + SettleFeeAllotDetailVO.PK_ALLOTBILLBID,
        bids.toArray(new String[bids.size()]));

  }

  /**
   * 结算时的费用分摊检查，如果当前检验单已经被其他费用结算分摊过（不包括自己的这一次），就不允许删除或取消传存货。
   * 
   * @param inWhereSql
   * @param hids
   * @return
   */
  private String getM27FeeAllotSql(String inWhereSql, Set<String> hids) {
    SqlBuilder sqlbuilder = new SqlBuilder();
    sqlbuilder.append(" select count(*) from  po_settle_feeallot ");
    sqlbuilder
        .append(" inner join po_settlebill on po_settle_feeallot.pk_settlebill = po_settlebill.pk_settlebill");
    sqlbuilder.append(" where po_settle_feeallot.dr=0 and po_settlebill.dr=0 ");
    IDExQueryBuilder idBuilder =
        new IDExQueryBuilder(PUTempTable.tmp_po_27_07.name());
    sqlbuilder.append(" and ");
    sqlbuilder.append(inWhereSql);
    String[] hid = hids.toArray(new String[hids.size()]);
    sqlbuilder.append(" and ");
    // 不包含本次所选结算单
    if (hids.size() == 1) {
      sqlbuilder.append(" po_settle_feeallot."
          + SettleFeeAllotDetailVO.PK_SETTLEBILL, "<>", hid[0]);
    }
    else {
      String hIdSql =
          idBuilder.buildAnotherSQL(" po_settle_feeallot."
              + SettleFeeAllotDetailVO.PK_SETTLEBILL + " not ",
              hids.toArray(new String[hids.size()]));
      sqlbuilder.append(hIdSql);
    }
    return sqlbuilder.toString();
  }

  /**
   * 校验sql
   * 
   * @param bids 结算单行id
   * @param m45bids 采购入库行id
   * @param m50bids 消耗汇总行id
   * @param hids 结算单头id
   * @return 校验sql
   */
  private String getQuerySql(Set<String> bids, Set<String> m45bids,
      Set<String> m50bids, Set<String> hids) {
    SqlBuilder sqlbuilder = new SqlBuilder();
    String inwhereSql = this.getInWhereSql(bids);
    sqlbuilder.append(this.getM27FeeAllotSql(inwhereSql, hids));
    // 采购入库的费用暂估校验
    if (m45bids.size() > 0) {
      sqlbuilder.append(" union all ");
      sqlbuilder.append(this.getFeeEstQuerySql(inwhereSql,
          PurchaseinFIFDVO.class));
    }
    // 消耗汇总的费用暂估校验
    if (m50bids.size() > 0) {
      sqlbuilder.append(" union all ");
      sqlbuilder.append(this.getFeeEstQuerySql(inwhereSql, VmiFIFDVO.class));
    }

    return sqlbuilder.toString();
  }
}
