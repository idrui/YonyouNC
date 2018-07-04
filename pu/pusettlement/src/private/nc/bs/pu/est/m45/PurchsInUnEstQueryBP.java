/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-5 下午04:04:16
 */
package nc.bs.pu.est.m45;

import nc.bs.pu.est.plugin.PurchsInEstPluginPoint;
import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.est.entity.FeeEstVO;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.est.entity.m45.PurchaseInEstVO;
import nc.vo.pu.m27.entity.SettleFeeAllotDetailVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>取消暂估的查询BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-7-5 下午04:04:16
 */
public class PurchsInUnEstQueryBP extends PurchsInGoodsEstQueryBP {

  private void getFeeSql(String bname, SqlBuilder sb) {
    sb.append("select ");
    sb.append(FeeEstVO.PK_STOCKPS_FEE);
    sb.append(" from ");
    sb.append(PUEntity.PurchaseinFI_Fee_TAB);
    sb.append(" where ");
    sb.append(FeeEstVO.PK_STOCKPS_B);
    sb.append("=");
    sb.append(bname);
    sb.append(".");
    sb.append(GoodsEstVO.PK_STOCKPS_B);
    sb.append(" and ");
    sb.appendIDIsNull(FeeEstVO.PK_FIRSTSETTLE_B);
    sb.append(" and ");
    sb.append("dr", 0);
  }

  @Override
  protected void addAfterRule(AroundProcesser<PurchaseInEstVO> prcr) {
    //
  }

  @Override
  protected IPluginPoint getPluginPoint() {
    return PurchsInEstPluginPoint.UNEST_BP;
  }

  @Override
  protected String getSpecialWhere(String hname, String bname, String feename,
      String pk_fiorg) {
    SqlBuilder sb = new SqlBuilder();
    sb.startParentheses();
    sb.append(bname);
    sb.append(".");
    sb.append(GoodsEstVO.NESTNUM, "<>", 0);// 进行过暂估
    sb.append(" and ");
    sb.append(bname + "." + GoodsEstVO.NACCESTCOSTSTTLNUM, 0);// 未货物结算
    sb.append(" or exists");
    sb.startParentheses();
    this.getFeeSql(bname, sb);// 或进行过费用暂估
    sb.endParentheses();
    sb.endParentheses();
    // 未费用结算（需要查费用分摊表，V61考虑增加冗余字段－费用结算次数，货物结算前费用结算次数来处理）
    sb.append(" and not exists(select feeallot."
        + SettleFeeAllotDetailVO.PK_SETTLE_FEEALLOT);
    sb.append(" from " + PUEntity.SettleBill_FeeDetail_TAB + " feeallot ");
    sb.append(" where feeallot.dr=0 and ");
    sb.append(" feeallot." + SettleFeeAllotDetailVO.PK_ALLOTBILLBID + "=");
    sb.append(bname + "." + GoodsEstVO.PK_STOCKPS_B + ")");

    String apSql = this.getSqlByEstAPFlag(bname, pk_fiorg);
    if (!StringUtil.isEmptyWithTrim(apSql)) {
      sb.append(" and ");
      sb.startParentheses();
      sb.append(apSql);
      sb.endParentheses();
    }

    return sb.toString();
  }

}
