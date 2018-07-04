package nc.bs.pu.est.m50;

import nc.bs.pu.est.m50.rule.VMIEstFeeProcRule;
import nc.bs.pu.est.plugin.VMIEstPluginPoint;
import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.jdbc.framework.util.DBConsts;
import nc.vo.pu.est.entity.FeeEstVO;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.est.entity.m50.VmiEstVO;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>消耗汇总费用暂估查询
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-7-1 上午10:13:23
 */
public class VMIFeeEstQueryBP extends VMIGoodsEstQueryBP {
  /** 是否包含已经做费用暂估的记录 **/
  private UFBoolean includeEstimated = UFBoolean.TRUE;

  /**
   * PurchsInFeeEstQueryBP 的构造子
   * 
   * @param includeEstimated 是否包含已经做费用暂估的记录
   */
  public VMIFeeEstQueryBP(UFBoolean includeEstimated) {
    this.includeEstimated = includeEstimated;
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
        .append(FeeEstVO.PK_STOCKPS_FEE)
        .append(",'" + DBConsts.NULL_WAVE + "')")
        .append("='" + DBConsts.NULL_WAVE + "'").toString();
  }

  @Override
  protected void addAfterRule(AroundProcesser<VmiEstVO> prcr) {
    // prcr.addAfterFinalRule(new DefaultGoodsEstInfoSetRule());// 设置默认值,vat相关
    prcr.addAfterRule(new VMIEstFeeProcRule());// 对暂估费用项的处理
  }

  /**
   * 父类方法重写
   * 
   * @see nc.bs.pu.est.m45.PurchsInGoodsEstQueryBP#getPluginPoint()
   */
  @Override
  protected IPluginPoint getPluginPoint() {
    return VMIEstPluginPoint.FEE_QUERY;
  }

  @Override
  protected String getSpecialWhere(String hname, String feename, String pk_fiorg) {
    SqlBuilder sb = new SqlBuilder();
    sb.startParentheses();
    sb.append(hname);
    sb.append(".");
    sb.append(GoodsEstVO.BSETTLEFINISH, UFBoolean.TRUE);// 结算完毕
    sb.append(" or ");
    sb.append(hname);
    sb.append(".");
    sb.append(GoodsEstVO.NESTNUM, "<>", 0); // 暂估过
    sb.endParentheses();
    if (null != pk_fiorg &&
    // 不暂估应付且不影响成本，则暂估时不查出
        !ValueUtils.getBoolean(PUSysParamUtil.getPO52(pk_fiorg))) {
      sb.append(" and ");
      sb.append(hname);
      sb.append(".");
      sb.append(GoodsEstVO.BAFFECTCOST, UFBoolean.TRUE);
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

}
