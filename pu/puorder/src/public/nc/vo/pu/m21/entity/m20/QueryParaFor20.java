package nc.vo.pu.m21.entity.m20;

import nc.vo.pu.m21.query.price.PubPriceQueryPara;

/**
 * Ϊ�빺���ṩ�Ĳ�ѯ����
 * 
 * @since 6.0
 * @version 2011-4-19 ����03:14:36
 * @author �����
 */

public class QueryParaFor20 extends PubPriceQueryPara {
  private static final long serialVersionUID = 1L;

  // �����������ͣ���Ӧ�빺�����嶩������
  private String pk_ordertrantype;

  // �빺����id
  private String pk_praybill_b;

  public String getPk_ordertrantype() {
    return this.pk_ordertrantype;
  }

  public String getPk_praybill_b() {
    return this.pk_praybill_b;
  }

  public void setPk_ordertrantype(String pk_ordertrantype) {
    this.pk_ordertrantype = pk_ordertrantype;
  }

  public void setPk_praybill_b(String pk_praybill_b) {
    this.pk_praybill_b = pk_praybill_b;
  }

}
