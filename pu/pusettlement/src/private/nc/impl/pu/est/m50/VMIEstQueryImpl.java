/**
 * $文件说明$
 *
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-6 下午03:11:40
 */
package nc.impl.pu.est.m50;

import nc.bs.pu.est.m50.VMICancelEstQueryBP;
import nc.bs.pu.est.m50.VMIFeeEstQueryBP;
import nc.bs.pu.est.m50.VMIGoodsEstQueryBP;
import nc.itf.pu.est.m50.IVMIEstQuery;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.est.entity.m50.VmiEstVO;
import nc.vo.pu.est.enumeration.QueryEstType;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>消耗汇总暂估(取消暂估)
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-8-6 下午03:11:40
 */

public class VMIEstQueryImpl implements IVMIEstQuery {

  @Override
  public VmiEstVO[] cancelEstQuery(IQueryScheme queryScheme, String pk_fiorg)
      throws BusinessException {
    VmiEstVO[] vos = null;
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    try {
      this.checkFinaceOrg(pk_fiorg);
      String cond =
          this.getQueryCond(qrySchemeProcessor, QueryEstType.UN_EST, pk_fiorg);
      vos = new VMICancelEstQueryBP().query(cond, pk_fiorg);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return vos;
  }

  @Override
  public VmiEstVO[] cancelEstQuery(String cond, String pk_fiorg)
      throws BusinessException {
    VmiEstVO[] vos = null;
    try {
      this.checkFinaceOrg(pk_fiorg);
      vos = new VMICancelEstQueryBP().query(cond, pk_fiorg);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return vos;
  }

  @Override
  public VmiEstVO[] feeEstQuery(IQueryScheme queryScheme,
      UFBoolean incFeeEstFlag, String pk_fiorg) throws BusinessException {
    VmiEstVO[] vos = null;
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    try {
      this.checkFinaceOrg(pk_fiorg);
      String cond =
          this.getQueryCond(qrySchemeProcessor, QueryEstType.FEE_EST, pk_fiorg);
      vos = new VMIFeeEstQueryBP(incFeeEstFlag).query(cond, pk_fiorg);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return vos;
  }

  @Override
  public VmiEstVO[] feeEstQuery(String sqlWhere, UFBoolean incFeeEstFlag,
      String pk_fiorg) throws BusinessException {
    VmiEstVO[] vos = null;
    try {
      this.checkFinaceOrg(pk_fiorg);
      vos = new VMIFeeEstQueryBP(incFeeEstFlag).query(sqlWhere, pk_fiorg);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return vos;
  }

  @Override
  public VmiEstVO[] goodsEstQuery(IQueryScheme queryScheme, String pk_fiorg)
      throws BusinessException {
    VmiEstVO[] vos = null;
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    try {
      this.checkFinaceOrg(pk_fiorg);
      String cond =
          this.getQueryCond(qrySchemeProcessor, QueryEstType.GOODS_EST,
              pk_fiorg);
      vos = new VMIGoodsEstQueryBP().query(cond, pk_fiorg);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return vos;
  }

  @Override
  public VmiEstVO[] goodsEstQuery(String cond, String pk_fiorg)
      throws BusinessException {
    VmiEstVO[] vos = null;
    try {
      this.checkFinaceOrg(pk_fiorg);
      vos = new VMIGoodsEstQueryBP().query(cond, pk_fiorg);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return vos;
  }

  private void checkFinaceOrg(String pk_fiorg) {
    if (StringUtil.isEmptyWithTrim(pk_fiorg)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0107")/*@res "查询暂估数据时必须指定结算财务组织!"*/);
    }
  }

  private String getQueryCond(QuerySchemeProcessor qrySchemeProcessor,
      QueryEstType estQryType, String pk_fiorg) {
    String bname = qrySchemeProcessor.getMainTableAlias();
    if (QueryEstType.FEE_EST == estQryType) {
      StringBuilder sb = new StringBuilder();
      sb.append(" left outer join ");
      sb.append(PUEntity.VMIFI_Fee_TAB);
      sb.append(" on ");
      sb.append(bname);
      sb.append(".");
      sb.append(GoodsEstVO.PK_STOCKPS_B);
      sb.append("=");
      sb.append(PUEntity.VMIFI_Fee_TAB);
      sb.append(".");
      sb.append(GoodsEstVO.PK_STOCKPS_B);
      qrySchemeProcessor.appendFrom(sb.toString());
    }
    qrySchemeProcessor.appendWhere(" and " + bname + "."
        + GoodsEstVO.PK_FINANCEORG + "='" + pk_fiorg + "' ");
    return qrySchemeProcessor.getFinalFromWhere();
  }
}