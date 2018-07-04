package nc.impl.pu.m21.action;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pu.m21.enumeration.EnumBillStatus;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.pub.rule.PUBillLazyQuery;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单修订查询
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-3-15 下午01:12:02
 */
public class OrderReviseQueryAction {

  public OrderVO[] query(IQueryScheme queryScheme) {
    return this.lazyQuery(queryScheme);
  }

  private OrderVO[] lazyQuery(IQueryScheme queryScheme) {
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    String mainTableAlias = qrySchemeProcessor.getMainTableAlias();
    // 临时拼上订单表头的EC扩展表
    qrySchemeProcessor.getTableAliasOfAttribute(OrderHeaderVO.VREASON);
    SqlBuilder sql = new SqlBuilder();
    sql.append(" and " + mainTableAlias + "." + OrderHeaderVO.BISLATEST,
        UFBoolean.TRUE);
    sql.append(" and " + mainTableAlias + "." + OrderHeaderVO.FORDERSTATUS,
        new Integer[] {
          (Integer) POEnumBillStatus.APPROVE.value(),
          (Integer) EnumBillStatus.EXPORT.value()
        });
    sql.append(" and " + mainTableAlias + ".pk_order in (");
    sql.append("select distinct po_order_b.pk_order from po_order_b po_order_b ");
    sql.append("where po_order_b.dr=0 and po_order_b.fisactive=");
    // 激活
    sql.append(EnumActive.ACTIVE.value());
    sql.append(") ");

    qrySchemeProcessor.appendWhere(sql.toString());
    qrySchemeProcessor.appendCurrentGroup();
    qrySchemeProcessor.appendFuncPermissionOrgSql();
    return (OrderVO[]) new PUBillLazyQuery<OrderVO>(OrderVO.class,
        POBillType.Order).queryOrderByBillcode(queryScheme);
  }
}
