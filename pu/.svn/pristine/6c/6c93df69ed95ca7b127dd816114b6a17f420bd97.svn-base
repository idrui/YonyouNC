/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-1 上午10:13:23
 */
package nc.bs.pu.est.m45;

import nc.bs.pu.est.m45.rule.PurchsInEstFeeProcRule;
import nc.bs.pu.est.plugin.PurchsInEstPluginPoint;
import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.jdbc.framework.util.DBConsts;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.est.entity.FeeEstVO;
import nc.vo.pu.est.entity.m45.PurchaseInEstVO;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>费用暂估查询
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-7-1 上午10:13:23
 */
public class PurchsInFeeEstQueryBP extends PurchsInGoodsEstQueryBP {
  /** 是否包含已经做费用暂估的记录 **/
  private UFBoolean includeEstimated = UFBoolean.TRUE;

  /**
   * PurchsInFeeEstQueryBP 的构造子
   * 
   * @param includeEstimated 是否包含已经做费用暂估的记录
   */
  public PurchsInFeeEstQueryBP(UFBoolean includeEstimated) {
    this.includeEstimated = includeEstimated;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.bs.pu.est.m45.PurchsInGoodsEstQueryBP#addAfterRule(nc.impl.pubapp.pattern.rule.processer.AroundProcesser)
   */
  @Override
  protected void addAfterRule(AroundProcesser<PurchaseInEstVO> prcr) {
    prcr.addAfterRule(new PurchsInEstFeeProcRule());// 对暂估费用项的处理
  }

  /**
   * 父类方法重写
   * 
   * @see nc.bs.pu.est.m45.PurchsInGoodsEstQueryBP#getPluginPoint()
   */
  @Override
  protected IPluginPoint getPluginPoint() {
    return PurchsInEstPluginPoint.FEE_QUERY;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.bs.pu.est.m45.PurchsInGoodsEstQueryBP#getSpecialWhere(java.lang.String,
   *      java.lang.String, java.lang.String)
   */
  @Override
  protected String getSpecialWhere(String hname, String bname, String feename,
      String pk_fiorg) {
    SqlBuilder sb = new SqlBuilder();
    sb.startParentheses();
    sb.append(bname);
    sb.append(".");
    sb.append(PurchaseinFIItemVO.BSETTLEFINISH, UFBoolean.TRUE);// 结算完毕
    sb.append(" or ");
    sb.append(bname);
    sb.append(".");
    sb.append(PurchaseinFIItemVO.NESTNUM, "<>", 0); // 暂估过
    sb.endParentheses();
    String apSql = this.getSqlByEstAPFlag(bname, pk_fiorg);
    if (!StringUtil.isEmptyWithTrim(apSql)) {
      sb.append(" and ");
      sb.startParentheses();
      sb.append(apSql);
      sb.endParentheses();
    }
    String incEstSql = this.getIncludeFeeEstimtedSql(feename);
    if (null != incEstSql) {
      sb.append(" and ");
      sb.startParentheses();
      sb.append(incEstSql);
      sb.endParentheses();
    }
    return sb.toString();
  }

  private String getIncludeFeeEstimtedSql(String feename) {
    // 如果查已经做过费用暂估的记录
    if (this.includeEstimated.booleanValue()) {
      SqlBuilder whr = new SqlBuilder();
      whr.appendIDIsNotNull(feename + "." + FeeEstVO.PK_STOCKPS_FEE);
      return whr.toString();
    }
    // 如果查未已经做费用暂估的记录，则拼子查询
    return new StringBuilder().append("isnull(").append(feename).append(".")
        .append(FeeEstVO.PK_STOCKPS_FEE).append(
            ",'" + DBConsts.NULL_WAVE + "')").append(
            "='" + DBConsts.NULL_WAVE + "'").toString();
  }

}
