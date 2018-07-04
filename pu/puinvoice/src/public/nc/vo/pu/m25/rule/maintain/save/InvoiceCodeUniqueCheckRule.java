/**
 * 
 */
package nc.vo.pu.m25.rule.maintain.save;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.pub.util.PUBillCodeUtils;

/**
 * 
 * @description
 *            �ɹ���Ʊ���ݺ�Ψһ��У��
 * @scene
 *      �ɹ���Ʊ����
 * @param
 * 
 *
 * @since 6.0
 * @version 2011-1-21 ����02:25:43
 * @author �����
 */
public class InvoiceCodeUniqueCheckRule implements IRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos) {
    // ���ݺ�Ψһ��У��
    PUBillCodeUtils.getInvoiceCode().checkUnique(vos);
  }

}
