package nc.pubift.pu.m25.ec;

import nc.vo.pu.ec.BasePUQueryECCondVO;
import nc.vo.pubapp.query2.sql.process.QueryCondition;

public class InvoiceSupplyDetailQueryCondVO extends BasePUQueryECCondVO {
  // ��Ӧ����������Ʒ���ơ���������,�������ڡ��˻����ڡ�������ڡ��˿� ���ڡ���Ʊ����
  private static final long serialVersionUID = 4942879772763982254L;

  private QueryCondition arriveDateCond;

  private QueryCondition backArriveDateCond;

  private QueryCondition inDateCond;

  private QueryCondition invoiceDateCond;

  private QueryCondition outDateCond;

  public QueryCondition getArriveDateCond() {
    return this.arriveDateCond;
  }

  public QueryCondition getBackArriveDateCond() {
    return this.backArriveDateCond;
  }

  public QueryCondition getInDateCond() {
    return this.inDateCond;
  }

  public QueryCondition getInvoiceDateCond() {
    return this.invoiceDateCond;
  }

  public QueryCondition getOutDateCond() {
    return this.outDateCond;
  }

  public void setArriveDateCond(QueryCondition arriveDateCond) {
    this.arriveDateCond = arriveDateCond;
  }

  public void setBackArriveDateCond(QueryCondition backArriveDateCond) {
    this.backArriveDateCond = backArriveDateCond;
  }

  public void setInDateCond(QueryCondition inDateCond) {
    this.inDateCond = inDateCond;
  }

  public void setInvoiceDateCond(QueryCondition invoiceDateCond) {
    this.invoiceDateCond = invoiceDateCond;
  }

  public void setOutDateCond(QueryCondition outDateCond) {
    this.outDateCond = outDateCond;
  }
}
