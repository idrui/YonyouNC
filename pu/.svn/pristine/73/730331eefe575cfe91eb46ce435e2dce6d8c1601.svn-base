package nc.impl.pu.m21;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.pu.m21.query.PayPlanQueryBP;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.pu.m21.IOrderPayPlanQuery;
import nc.itf.pu.reference.ct.Z2CTServices;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m21.entity.AggPayPlanVO;
import nc.vo.pu.m21.entity.PayPlanViewVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pub.BusinessException;
import nc.vo.pub.para.SysInitVO;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.scmpub.payterm.pay.AbstractPayPlanVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.0
 * @version 2011-1-4 上午09:01:38
 * @author wuxla
 */

public class OrderPayPlanQueryImpl implements IOrderPayPlanQuery {

  @Override
  public boolean checkPO88Para(SysInitVO vo) throws BusinessException {
    try {
      String pk_org = vo.getPk_org();
      SqlBuilder sql = new SqlBuilder();
      sql.append("select count(*) from " + PUEntity.M21_PAYPLAN_TABLE
          + " where ");
      sql.append(AbstractPayPlanVO.PK_FINANCEORG, pk_org);
      String pk_group = BSContext.getInstance().getGroupID();
      sql.append(" and " + AbstractPayPlanVO.PK_GROUP, pk_group);
      sql.append(" and " + AbstractPayPlanVO.DR, 0);
      sql.append(" and (" + AbstractPayPlanVO.NACCUMPAYAPPORGMNY, ">", 0);
      sql.append(" or " + AbstractPayPlanVO.NACCUMPAYORGMNY, ">", 0);
      sql.append(")");

      IRowSet rs = new DataAccessUtils().query(sql.toString());
      if (0 == rs.size()) {
        return true;
      }

      while (rs.next()) {
        if (rs.getInt(0) > 0) {
          throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4004030_0", "04004030-0171")/*
                                                                       * @res
                                                                       * "订单付款计划已经生成付款申请或者付款单，不允许修改"
                                                                       */);
        }
      }
      if (SysInitGroupQuery.isCTEnabled()) {
    	  return Z2CTServices.checkPO88Para(pk_org);
      }
      return true;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return true;
  }

  // @Override
  // public PayPlanViewVO[] queryPayPlanVOs(String cond) throws
  // BusinessException {
  // try {
  // DataAccessUtils utils = new DataAccessUtils();
  // IRowSet rowset = utils.query(this.createQuerySql(cond));
  // AggPayPlanVO[] vos =
  // new PayPlanQueryBP().queryPayPlanVOs(rowset
  // .toOneDimensionStringArray());
  // if (ArrayUtils.isEmpty(vos)) {
  // return null;
  // }
  // return AggPayPlanVO.getPayPlanViewVO(vos);
  // }
  // catch (Exception e) {
  // ExceptionUtils.marsh(e);
  // return null;
  // }
  // }

  @Override
  public PayPlanViewVO[] queryByQueryScheme(IQueryScheme queryScheme)
      throws BusinessException {
    try {
      DataAccessUtils utils = new DataAccessUtils();
      // 执行sql，查询表头id
      IRowSet rowset = utils.query(this.createWholeSql(queryScheme));
      AggPayPlanVO[] vos =
          new PayPlanQueryBP().queryPayPlanVOs(rowset
              .toOneDimensionStringArray());
      if (ArrayUtils.isEmpty(vos)) {
        return null;
      }
      return AggPayPlanVO.getPayPlanViewVO(vos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  // private String createQuerySql(String cond) {
  // SqlBuilder sql = new SqlBuilder();
  // sql.append("select distinct pk_order ");
  // sql.append("from po_order where ");
  // sql.append(OrderHeaderVO.DR, 0);
  // sql.append(" and " + OrderHeaderVO.BISLATEST, UFBoolean.TRUE);
  // if (StringUtils.isNotBlank(cond)) {
  // sql.append(" and " + cond);
  // }
  // if (cond != null && cond.indexOf("pk_group") < 0) {
  // sql.append(" and " + OrderHeaderVO.PK_GROUP, InvocationInfoProxy
  // .getInstance().getGroupId());
  // }
  // return sql.toString();
  // }

  @Override
  public PayPlanViewVO[] queryPayPlanViews(String[] bids)
      throws BusinessException {
    if (ArrayUtils.isEmpty(bids)) {
      return null;
    }
    try {
      ViewQuery<PayPlanViewVO> query =
          new ViewQuery<PayPlanViewVO>(PayPlanViewVO.class);
      return query.query(bids);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public AggPayPlanVO[] queryPayPlanVOs(String[] hids) throws BusinessException {
    if (ArrayUtils.isEmpty(hids)) {
      return null;
    }

    try {
      return new PayPlanQueryBP().queryPayPlanVOs(hids);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return null;
  }

  private String createWholeSql(IQueryScheme queryScheme) {
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);

    String mainTableAlias = qrySchemeProcessor.getMainTableAlias();
    StringBuilder sql = new StringBuilder();
    sql.append("select " + mainTableAlias + ".pk_order ");
    qrySchemeProcessor.appendWhere(" and " + mainTableAlias + ".dr = 0 ");
    qrySchemeProcessor.appendWhere(" and " + mainTableAlias
        + ".bislatest= 'Y' ");
    qrySchemeProcessor.appendWhere(" and " + mainTableAlias + ".pk_group='"
        + InvocationInfoProxy.getInstance().getGroupId() + "'");
    sql.append(qrySchemeProcessor.getFinalFromWhere());
    sql.append(" order by po_order.vbillcode");
    return sql.toString();
  }
}
