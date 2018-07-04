package nc.pubimpl.pu.m21.ic.report;

import nc.impl.pubapp.pattern.data.view.SchemeViewQuery;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.pubitf.pu.m21.ic.report.IOrderQueryForICReport;
import nc.pubitf.pu.m21.ic.report.OrderViewForICBorrowMemo;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUEntity.RelationAttrName;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.pub.util.QuerySchemeUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.scmpub.res.billtype.ICBillType;

/**
 * 采购订单给库存报表提供的服务
 * 
 * @since 6.0
 * @version 2011-12-6 上午11:41:08
 * @author zhaoyha
 */
public class OrderQueryForICReportImpl implements IOrderQueryForICReport {

  @Override
  public OrderViewForICBorrowMemo[] queryForBorrowMemo(String[] borrowHIDs,
      String[] borrowBIDs) throws BusinessException {
    try {
      return new SchemeViewQuery<OrderViewForICBorrowMemo>(
          OrderViewForICBorrowMemo.class).query(
          this.getBorrowMemoQs(borrowHIDs, borrowBIDs), null);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  private IQueryScheme getBorrowMemoQs(String[] borrowHIDs, String[] borrowBIDs) {
    IQueryScheme qs = QuerySchemeUtil.createQueryScheme(PUEntity.M21_H);
    QuerySchemeProcessor qsp = new QuerySchemeProcessor(qs);
    String balias =
        qsp.getTableAliasOfAttribute(RelationAttrName.pk_order_b.name() + "."
            + OrderItemVO.PK_ORDER_B);
    SqlBuilder where = new SqlBuilder();
    IDExQueryBuilder idq =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_26.name());
    where.append(" and " + balias + "." + OrderItemVO.CSOURCETYPECODE,
        ICBillType.BorrowIn.getCode());
    where.append(" and ");
    where
        .append(idq.buildSQL(balias + "." + OrderItemVO.CSOURCEID, borrowHIDs));
    where.append(" and ");
    where.append(idq
        .buildSQL(balias + "." + OrderItemVO.CSOURCEBID, borrowBIDs));
    qsp.appendWhere(where.toString());
    return qs;
  }

}
