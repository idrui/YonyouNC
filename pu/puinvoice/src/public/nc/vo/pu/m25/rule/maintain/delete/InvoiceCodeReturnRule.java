/**
 * 
 */
package nc.vo.pu.m25.rule.maintain.delete;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.pub.util.PUBillCodeUtils;

/**
 * 
 * @description
 *		���ݺŻ��˴���:���ù������봦��
 * @scene
 * 		���ݺŻ��˴���:���ù������봦��
 * @param
 * 
 * @since 6.5
 * @version 2015-11-9 ����4:16:40
 * @author wandl
 */
public class InvoiceCodeReturnRule implements ICompareRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos, InvoiceVO[] orgVos) {
    if (null != orgVos) {
      PUBillCodeUtils.getInvoiceCode().returnBillCode(orgVos);
    }

  }

}
