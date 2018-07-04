/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version
 * @see
 * @since
 * @time 2009-6-26 ����02:58:33
 */
package nc.vo.pu.m25.entity;

import nc.vo.pubapp.pattern.model.meta.entity.bill.AbstractBillMeta;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������Ŀ1
 * <li>������Ŀ2
 * <li>...
 * </ul>
 * <p>
 * <b>�����ʷ����ѡ����</b>
 * <p>
 * XXX�汾����XXX��֧�֡�
 * <p>
 * <p>
 * 
 * @version ���汾��
 * @since ��һ�汾��
 * @author zhaoyha
 * @time 2009-6-26 ����02:58:33
 */
public class InvoiceVOMeta extends AbstractBillMeta {

  /**
   * InvoiceVOMeta �Ĺ�����
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
