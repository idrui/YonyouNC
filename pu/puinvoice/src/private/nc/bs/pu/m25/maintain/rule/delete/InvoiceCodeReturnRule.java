/**
 * 
 */
package nc.bs.pu.m25.maintain.rule.delete;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.pub.util.PUBillCodeUtils;

/**
 * 
 * @description
 * ���ݺŻ��˴���:���ù������봦��
 * @scene
 * ɾ��
 * @param
 * ��
 *
 * @since 6.3
 * @version 2014-10-22 ����3:08:55
 * @author zhangshqb
 */
public class InvoiceCodeReturnRule implements ICompareRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos, InvoiceVO[] orgVos) {
    if (null != orgVos) {
      PUBillCodeUtils.getInvoiceCode().returnBillCode(orgVos);
    }
  }

}
