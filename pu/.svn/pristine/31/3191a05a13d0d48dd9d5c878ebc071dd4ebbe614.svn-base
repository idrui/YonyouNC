package nc.pubift.pu.m25.ec;

import nc.vo.pu.ec.BasePUQueryECCondVO;
import nc.vo.pubapp.query2.sql.process.QueryCondition;

/**
 * 输入：供应商主键、发票号、发票日期、票到日期、采购组织、采购部门、订单号
 * 查询条件应该是：发票号、订单号、采购组织、发票日期和票到日期，而现在是票到日期、币种、采购部门、发票号、付款单位
 * 
 * @since 6.0
 * @version 2011-5-16 上午11:20:46
 * @author wuxla
 */

public class InvoicePublishedQueryCondVO extends BasePUQueryECCondVO {

  private static final long serialVersionUID = 1L;

  // 票到日期
  private QueryCondition arriveDateCond;

  // // 币种
  // private QueryCondition ccurrencyidCond;

  // // 采购部门
  // private QueryCondition deptCond;

  // 发票号
  private QueryCondition invoiceCodeCond;

  // 订单号
  private QueryCondition orderCodeCond;

  // // 付款单位
  // private QueryCondition paytosupplierCond;

  public QueryCondition getArriveDateCond() {
    return this.arriveDateCond;
  }

  public QueryCondition getInvoiceCodeCond() {
    return this.invoiceCodeCond;
  }

  public QueryCondition getOrderCodeCond() {
    return this.orderCodeCond;
  }

  public void setArriveDateCond(QueryCondition arriveDateCond) {
    this.arriveDateCond = arriveDateCond;
  }

  public void setInvoiceCodeCond(QueryCondition invoiceCodeCond) {
    this.invoiceCodeCond = invoiceCodeCond;
  }

  public void setOrderCodeCond(QueryCondition orderCodeCond) {
    this.orderCodeCond = orderCodeCond;
  }

}
