/**
 * 
 */
package nc.bs.pu.m25.maintain.rule.save;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.pub.util.PUBillCodeUtils;

/**
 * 
 * @description
 * ���ݺ�Ψһ��У��
 * @scene
 * �����BP
 * @param
 * ��
 *
 * @since 6.3
 * @version 2014-10-22 ����3:34:58
 * @author zhangshqb
 */

public class InvoiceCodeUniqueCheckRule implements IRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos) {
    // ���ݺ�Ψһ��У��
    PUBillCodeUtils.getInvoiceCode().checkUnique(vos);
  }

}
