/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-24 下午03:42:17
 */
package nc.bs.pu.est.m50;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import nc.bs.ml.NCLangResOnserver;
import nc.bs.pu.est.EstVOQueryBP;
import nc.bs.pu.est.m50.rule.DefaultGoodsEstInfoSetRule;
import nc.bs.pu.est.m50.rule.VMIEstFeeProcRule;
import nc.bs.pu.est.m50.rule.VMIEstPriceQryRule;
import nc.bs.pu.est.plugin.VMIEstPluginPoint;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.rule.plugin.IPluginPoint;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.est.entity.m50.VmiEstHeaderVO;
import nc.vo.pu.est.entity.m50.VmiEstVO;
import nc.vo.pu.m4202.entity.VmiFIFeeVO;
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
public class VMIGoodsEstQueryBP {

  public VmiEstVO[] query(String cond, String pk_fiorg) {
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
    EstVOQueryBP<VmiEstVO> query =
        new EstVOQueryBP<VmiEstVO>(VmiEstVO.class, VmiEstHeaderVO.class,
            VmiFIFeeVO.class);
    VmiEstVO[] vos = query.query(ids);
    AroundProcesser<VmiEstVO> prcr =
        new AroundProcesser<VmiEstVO>(this.getPluginPoint());
    this.addAfterRule(prcr);
    prcr.after(vos);
    return vos;
  }

  protected void addAfterRule(AroundProcesser<VmiEstVO> prcr) {
    prcr.addAfterFinalRule(new DefaultGoodsEstInfoSetRule());// 设置默认值
    prcr.addAfterFinalRule(new VMIEstPriceQryRule());// 暂估询价
    prcr.addAfterRule(new VMIEstFeeProcRule());// 对暂估费用项的处理
  }

  protected IPluginPoint getPluginPoint() {
    return VMIEstPluginPoint.GOODS_QUERY;
  }

  protected String getSolidWhere(String hname) {
    SqlBuilder sb = new SqlBuilder();
    sb.append(hname);
    sb.append(".pk_group", BSContext.getInstance().getGroupID());
    sb.append(" and ");
    sb.append(hname);
    sb.append(".dr=0 ");
    return sb.toString();
  }

  /**
   * @param feename
   */
  protected String getSpecialWhere(String hname, String feename, String pk_fiorg) {
    SqlBuilder sb = new SqlBuilder();
    sb.append(hname);
    sb.append(".");
    sb.append(GoodsEstVO.BSETTLEFINISH, UFBoolean.FALSE);// 未结算完毕
    sb.append(" and ");
    sb.append(hname);
    sb.append(".");
    sb.append(GoodsEstVO.NESTNUM, 0); // 未暂估过
    // 数量不为零
    sb.append(" and ");
    sb.append(hname);
    sb.append(".");
    sb.append(GoodsEstVO.NINNUM, "<>", 0);
    if (null != pk_fiorg &&
    // 不暂估应付且不影响成本，则暂估时不查出
        !ValueUtils.getBoolean(PUSysParamUtil.getPO52(pk_fiorg))) {
      sb.append(" and ");
      sb.append(hname);
      sb.append(".");
      sb.append(GoodsEstVO.BAFFECTCOST, UFBoolean.TRUE);
    }
    return sb.toString();
  }

  protected String getSql(String custCond, String pk_fiorg) {
    FromWhereParseUtil util = new FromWhereParseUtil(custCond);
    String hname = util.getTableAlias(PUEntity.VMIFI_H_TAB);
    SqlBuilder sb = new SqlBuilder();
    sb.append("select ");
    sb.append(hname);
    sb.append(".");
    sb.append(GoodsEstVO.PK_STOCKPS_B);
    sb.append(custCond);
    String solidSql = this.getSolidWhere(hname);
    if (!StringUtil.isEmptyWithTrim(solidSql)) {
      sb.append(" and (");
      sb.append(solidSql);
      sb.append(")");
    }
    String feename = util.getTableAlias(PUEntity.VMIFI_Fee_TAB);
    String specialSql = this.getSpecialWhere(hname, feename, pk_fiorg);
    if (!StringUtil.isEmptyWithTrim(specialSql)) {
      sb.append(" and (");
      sb.append(specialSql);
      sb.append(")");
    }
    return sb.toString();
  }

}
