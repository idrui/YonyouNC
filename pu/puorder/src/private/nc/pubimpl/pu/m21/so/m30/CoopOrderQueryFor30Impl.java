/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-28 下午06:38:04
 */
package nc.pubimpl.pu.m21.so.m30;

import nc.bs.pu.m21.query.so.CoopOrderQueryFor30BP;
import nc.pubitf.pu.m21.so.m30.ICoopOrderQueryFor30;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.scmf.coop.entity.CoopHeaderVO;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>销售订单参照协同采购订单查询实现类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-28 下午06:38:04
 */
public class CoopOrderQueryFor30Impl implements ICoopOrderQueryFor30 {

  @Override
  public OrderVO[] coopOrderQueryFor30(IQueryScheme queryScheme)
      throws BusinessException {
    try {
      QuerySchemeProcessor qrySchemeProcessor =
          new QuerySchemeProcessor(queryScheme);

      QueryCondition saleorgCond =
          qrySchemeProcessor.getQueryCondition("pk_saleorg");
      QueryCondition puorgCond =
          qrySchemeProcessor.getQueryCondition(OrderHeaderVO.PK_ORG);
      Object[] saleorgValues = saleorgCond.getValues();
      String pk_saleorg = (String) saleorgValues[0];
      Object[] puorgValues = puorgCond.getValues();
      String pk_puorg = (String) puorgValues[0];

      String mainTableAlias = qrySchemeProcessor.getMainTableAlias();
      SqlBuilder part = new SqlBuilder();
      part.append(" and " + mainTableAlias + "." + OrderHeaderVO.BISLATEST,
          UFBoolean.TRUE);
      part.append(" and " + mainTableAlias + "." + OrderHeaderVO.BCOOPTOSO,
          UFBoolean.FALSE);
      part.append(" and " + mainTableAlias + "." + OrderHeaderVO.BSOCOOPTOME,
          UFBoolean.FALSE);
      part.append(" and " + mainTableAlias + "." + OrderHeaderVO.FORDERSTATUS,
          POEnumBillStatus.APPROVE.toInt());
      part.append(" and " + mainTableAlias + "." + OrderHeaderVO.BFINALCLOSE,
          UFBoolean.FALSE);
      part.append(" and " + mainTableAlias + "." + OrderHeaderVO.CTRANTYPEID);
      part.append(" in (");
      part.append("select " + CoopHeaderVO.CTRANTYPEID_SRC);
      part.append(" from scm_coopsetup where ");
      part.append("dr", 0);
      part.append(" and " + CoopHeaderVO.PK_ORG_SRC, pk_puorg);
      part.append(" and " + CoopHeaderVO.PK_ORG_DEST, pk_saleorg);
      part.append(" and " + CoopHeaderVO.VBILLTYPE_SRC,
          POBillType.Order.getCode());
      part.append(")");

      qrySchemeProcessor.appendWhere(part.toString());

      SqlBuilder wholeSql = new SqlBuilder();
      wholeSql.append("select distinct " + mainTableAlias + "."
          + OrderHeaderVO.PK_ORDER);

      wholeSql.append(qrySchemeProcessor.getFinalFromWhere());
      return new CoopOrderQueryFor30BP().coopOrderQueryFor30(
          wholeSql.toString(), pk_puorg, pk_saleorg);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

}
