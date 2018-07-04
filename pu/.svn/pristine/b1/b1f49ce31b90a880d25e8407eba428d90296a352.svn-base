/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-5 下午04:04:16
 */
package nc.bs.pu.est.m50;

import nc.bs.pu.est.plugin.VMIEstPluginPoint;
import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.est.entity.FeeEstVO;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.est.entity.m50.VmiEstVO;
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
public class VMICancelEstQueryBP extends VMIGoodsEstQueryBP {

  private void getFeeSql(String feename, SqlBuilder sb) {
    sb.append("select ");
    sb.append(FeeEstVO.PK_STOCKPS_FEE);
    sb.append(" from ");
    sb.append(PUEntity.VMIFI_Fee_TAB);
    sb.append(" where ");
    sb.append(FeeEstVO.PK_STOCKPS_B);
    sb.append("=");
    sb.append(feename);
    sb.append(".");
    sb.append(GoodsEstVO.PK_STOCKPS_B);
    sb.append(" and ");
    sb.appendIDIsNull(FeeEstVO.PK_FIRSTSETTLE_B);
    sb.append(" and ");
    sb.append("dr", 0);
  }

  @Override
  protected void addAfterRule(AroundProcesser<VmiEstVO> prcr) {
    //
  }

  @Override
  protected IPluginPoint getPluginPoint() {
    return VMIEstPluginPoint.UNEST_QUERY_BP;
  }

  @Override
  protected String getSpecialWhere(String hname, String feename, String pk_fiorg) {
    SqlBuilder sb = new SqlBuilder();
    sb.startParentheses();
    sb.append(hname);
    sb.append(".");
    sb.append(GoodsEstVO.NESTNUM, "<>", 0);
    sb.append(" and ");
    sb.append(GoodsEstVO.NACCESTCOSTSTTLNUM, 0);
    sb.append(" or exists");
    sb.startParentheses();
    this.getFeeSql(hname, sb);
    sb.endParentheses();
    sb.endParentheses();
    // 未费用结算（需要查费用分摊表，V61考虑增加冗余字段－费用结算次数，货物结算前费用结算次数来处理）
    sb.append(" and not exists(select feeallot."
        + SettleFeeAllotDetailVO.PK_SETTLE_FEEALLOT);
    sb.append(" from " + PUEntity.SettleBill_FeeDetail_TAB + " feeallot ");
    sb.append(" where feeallot.dr=0 and ");
    sb.append(" feeallot." + SettleFeeAllotDetailVO.PK_ALLOTBILLBID + "=");
    sb.append(hname + "." + GoodsEstVO.PK_STOCKPS_B + ")");
    return sb.toString();
  }

}
