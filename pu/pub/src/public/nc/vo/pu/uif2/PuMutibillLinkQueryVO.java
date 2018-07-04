package nc.vo.pu.uif2;

import nc.vo.pubapp.pattern.model.entity.bill.IBill;

/**
 * 联查多PK单据VO
 * 联查存储key为 89
 * 
 * @version 6.1
 * @since 6.1
 * @author yangtian
 * @time 2011-12-20 上午11:01:08
 */
public class PuMutibillLinkQueryVO {

  /** 多PK联查参数的类型编码 **/
  public static final int INITTYPE = 89;

  // 表头主键
  private String[] billids;

  // 单据类型
  private String billtype;

  // 单据vo
  private IBill[] billvos;

  public PuMutibillLinkQueryVO(String billtype, IBill[] billvos) {
    this.billvos = billvos;
    this.billtype = billtype;
  }

  public PuMutibillLinkQueryVO(String billtype, String[] billids) {
    this.billids = billids;
    this.billtype = billtype;
  }

  public String[] getBillids() {
    return this.billids;
  }

  public String getBilltype() {
    return this.billtype;
  }

  public IBill[] getBillvos() {
    return this.billvos;
  }

  public void setBillids(String[] billids) {
    this.billids = billids;
  }

  public void setBilltype(String billtype) {
    this.billtype = billtype;
  }

  public void setBillvos(IBill[] billvos) {
    this.billvos = billvos;
  }
}
