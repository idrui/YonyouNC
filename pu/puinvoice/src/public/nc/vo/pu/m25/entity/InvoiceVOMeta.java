/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version
 * @see
 * @since
 * @time 2009-6-26 下午02:58:33
 */
package nc.vo.pu.m25.entity;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>功能条目1
 * <li>功能条目2
 * <li>...
 * </ul>
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version 本版本号
 * @since 上一版本号
 * @author zhaoyha
 * @time 2009-6-26 下午02:58:33
 */
public class InvoiceVOMeta extends AbstractBillMeta {

  /**
   * InvoiceVOMeta 的构造子
   */
  public InvoiceVOMeta() {
    this.init();
  }

  private void init() {
    this.setParent(InvoiceHeaderVO.class);
    this.addChildren(InvoiceItemVO.class);
    this.addChildren(InvoiceSettleItemVO.class);
  }
}
