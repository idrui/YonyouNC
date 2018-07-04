package nc.vo.pu.report.view.order;

import java.util.HashSet;
import java.util.Set;

import nc.scmmm.vo.scmpub.report.pub.ISCMReportContext;
import nc.scmmm.vo.scmpub.report.viewfactory.sql.PermissionTableInfo;
import nc.scmmm.vo.scmpub.report.viewfactory.sql.SCMPermissionBeanSqlView;
import nc.vo.ic.pub.sql.BeanPath;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.report.enumeration.OrderSummaryDynamicFieldCode;

public class OrderSummaryRptView extends SCMPermissionBeanSqlView {

  private final static String BODYALIAS = "po_order_b";

  private final static String PK_STOREREQ_B = "pk_order_b";

  private static final long serialVersionUID = 8297908062773236040L;

  public OrderSummaryRptView(ISCMReportContext context) {
    super(new OrderHeaderVO(), context);

    // 添加分组字段
    this.addSelectFields(OrderSummaryRptView.PK_STOREREQ_B, new String[] {
      OrderItemVO.PK_MATERIAL, OrderItemVO.CUNITID, OrderItemVO.CCURRENCYID
    }, new String[] {
      OrderItemVO.PK_MATERIAL, OrderItemVO.CUNITID, OrderItemVO.CCURRENCYID
    });

    // 添加合计字段。
    this.addViewSumFields(OrderSummaryRptView.PK_STOREREQ_B, new String[] {
      OrderItemVO.NNUM, OrderItemVO.NMNY, OrderItemVO.NTAXMNY,
      OrderItemVO.NTAX, OrderItemVO.NACCUMARRVNUM, OrderItemVO.NACCUMWASTNUM,
      OrderItemVO.NACCUMSTORENUM, OrderItemVO.NACCUMINVOICENUM,
      OrderItemVO.NBACKARRVNUM
    });

    // 退货金额的动态字段。
    this.addViewSumFieldsByAttrExp(new String[] {
      "sum(po_order_b.nbackarrvnum * norigtaxnetprice)"
    }, new String[] {
      OrderSummaryDynamicFieldCode.returnmny.getCode()
    });
  }

  @Override
  protected Set<PermissionTableInfo> initFixTableAlias() {
    return new HashSet<PermissionTableInfo>();
  }

  @Override
  protected void initFixWhere() {
    // 过滤掉被删除的数据
    this.addWhereByAttrPathExp(BeanPath.DOT + "dr = 0");
    this.addWhereByAttrPathExp(OrderSummaryRptView.BODYALIAS + BeanPath.DOT
        + "dr = 0");
    this.addWhereByAttrPathExp(BeanPath.DOT + "bislatest = 'Y' ");
  }

}
