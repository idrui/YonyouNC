/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-24 下午03:42:17
 */
package nc.bs.pu.est.m45;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import nc.bs.ml.NCLangResOnserver;
import nc.bs.pu.est.EstVOQueryBP;
import nc.bs.pu.est.m45.rule.DefaultGoodsEstInfoSetRule;
import nc.bs.pu.est.m45.rule.PUImportInEstPriceQryRule;
import nc.bs.pu.est.m45.rule.PurchsInEstFeeProcRule;
import nc.bs.pu.est.m45.rule.PurchsInEstPriceQryRule;
import nc.bs.pu.est.plugin.PurchsInEstPluginPoint;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.est.entity.m45.PurchaseInEstHeaderVO;
import nc.vo.pu.est.entity.m45.PurchaseInEstVO;
import nc.vo.pu.m4201.entity.PurchaseinFIFeeVO;
import nc.vo.pu.m4201.entity.PurchaseinFIHeaderVO;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.res.Variable;
import nc.vo.scmpub.util.FromWhereParseUtil;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>暂估查询BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-24 下午03:42:17
 */
public class PurchsInGoodsEstQueryBP {

  /** 是否来源进口 **/
  private UFBoolean isIT = UFBoolean.FALSE;

  public UFBoolean getIsIT() {
    return this.isIT;
  }

  public PurchaseInEstVO[] query(String cond, String pk_fiorg) {
    DataAccessUtils util = new DataAccessUtils();
    IRowSet rset = util.query(this.getSql(cond, pk_fiorg));
    if (0 == rset.size()) {
      return null;
    }
    String[] ids = rset.toOneDimensionStringArray();
    Set<String> distnctIDs = new HashSet<String>(Arrays.asList(ids));
    ids = distnctIDs.toArray(new String[distnctIDs.size()]);
    // 检查最大返回行数
    int maxRow = 2 * Variable.getMaxQueryCount();
    if (ids.length > maxRow) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("common", "04004000-0000", null, new String[] {
            String.valueOf(maxRow)
          })/* 查询结果返回记录数超过{0}条，请修改查询条件减少查询记录数！ */);
    }
    EstVOQueryBP<PurchaseInEstVO> query =
        new EstVOQueryBP<PurchaseInEstVO>(PurchaseInEstVO.class,
            PurchaseInEstHeaderVO.class, PurchaseinFIFeeVO.class);
    PurchaseInEstVO[] vos = query.query(ids);
    AroundProcesser<PurchaseInEstVO> prcr =
        new AroundProcesser<PurchaseInEstVO>(this.getPluginPoint());
    this.addAfterRule(prcr);
    prcr.after(vos);
    return vos;
  }

  public void setIsIT(UFBoolean isIT) {
    this.isIT = isIT;
  }

  /**
   * mengjian 区分进口还是采购
   * 
   * @param field
   * @return
   */
  private Object getPUorITWhere(String field) {
    String where = "";
    // 进口
    if (this.getIsIT().booleanValue()) {
      where = field + " = 'Y' ";
    }// 采购
    else {
      where = " isnull(" + field + ", 'N') = 'N'";
    }
    return " and " + where;
  }

  protected void addAfterRule(AroundProcesser<PurchaseInEstVO> prcr) {
    prcr.addAfterFinalRule(new DefaultGoodsEstInfoSetRule());// 设置默认值
    if (this.getIsIT().booleanValue()) {
      prcr.addAfterFinalRule(new PUImportInEstPriceQryRule());// 进口暂估询价
    }
    else {
      prcr.addAfterFinalRule(new PurchsInEstPriceQryRule());// 暂估询价
    }
    prcr.addAfterRule(new PurchsInEstFeeProcRule());// 对暂估费用项的处理
  }

  protected IPluginPoint getPluginPoint() {
    return PurchsInEstPluginPoint.GOODS_QUERY;
  }

  /**
   * TODO tianft 考虑以后去掉，调用公共方法
   */
  protected String getSolidWhere(String hname, String bname) {
    SqlBuilder sb = new SqlBuilder();
    sb.append(hname);
    sb.append(".pk_group=");
    sb.append(bname);
    sb.append(".pk_group");
    sb.append(" and ");
    sb.append(bname);
    sb.append(".pk_group", BSContext.getInstance().getGroupID());
    sb.append(" and ");
    sb.append(hname);
    sb.append(".dr=0 and ");
    sb.append(bname);
    sb.append(".dr=0 ");
    return sb.toString();
  }

  /**
   * @param feename
   */
  protected String getSpecialWhere(String hname, String bname, String feename,
      String pk_fiorg) {
    SqlBuilder sb = new SqlBuilder();
    sb.append(bname);
    sb.append(".");
    sb.append(PurchaseinFIItemVO.BSETTLEFINISH, UFBoolean.FALSE);// 未结算完毕
    sb.append(" and ");
    sb.append(bname);
    sb.append(".");
    sb.append(PurchaseinFIItemVO.NESTNUM, 0); // 未暂估过
    sb.append(" and ");
    sb.append(bname);
    sb.append(".");
    sb.append(PurchaseinFIItemVO.NINNUM, "<>", 0); // 去掉主数量为0的行
    sb.append(" and ");
    sb.append(hname);
    sb.append(".");
    sb.append(PurchaseinFIHeaderVO.BAUTOTOFI, UFBoolean.FALSE);// 未直接确认过成本
    String apSql = this.getSqlByEstAPFlag(bname, pk_fiorg);
    if (!StringUtil.isEmptyWithTrim(apSql)) {
      sb.append(" and ");
      sb.startParentheses();
      sb.append(apSql);
      sb.endParentheses();
    }
    return sb.toString();
  }

  protected String getSql(String custCond, String pk_fiorg) {
    FromWhereParseUtil util = new FromWhereParseUtil(custCond);
    String bname = util.getTableAlias(PUEntity.PurchaseinFI_B_TAB);
    SqlBuilder sb = new SqlBuilder();
    sb.append("select ");
    sb.append(bname);
    sb.append(".");
    sb.append(PurchaseinFIItemVO.PK_STOCKPS_B);
    sb.append(custCond);
    String hname = util.getTableAlias(PUEntity.PurchaseinFI_H_TAB);
    // mengjian 区分进口还是采购
    sb.append(this.getPUorITWhere(hname + "." + PurchaseinFIHeaderVO.BITINBILL));
    String solidSql = this.getSolidWhere(hname, bname);
    if (!StringUtil.isEmptyWithTrim(solidSql)) {
      sb.append(" and (");
      sb.append(solidSql);
      sb.append(")");
    }
    String feename = util.getTableAlias(PUEntity.PurchaseinFI_Fee_TAB);
    String specialSql = this.getSpecialWhere(hname, bname, feename, pk_fiorg);
    if (!StringUtil.isEmptyWithTrim(specialSql)) {
      sb.append(" and (");
      sb.append(specialSql);
      sb.append(")");
    }
    return sb.toString();
  }

  /** 根据是否暂估应付的标志拼SQL片断 **/
  protected String getSqlByEstAPFlag(String bname, String pk_fiorg) {
    SqlBuilder sb = new SqlBuilder();
    if (null != pk_fiorg &&
    // 不暂估应付且不影响成本，则暂估时不查出
        !ValueUtils.getBoolean(PUSysParamUtil.getPO52(pk_fiorg))) {
      sb.append(bname);
      sb.append(".");
      sb.append(PurchaseinFIItemVO.BAFFECTCOST, UFBoolean.TRUE);// 是否影响成本标志
    }
    return sb.toString();
  }

}
