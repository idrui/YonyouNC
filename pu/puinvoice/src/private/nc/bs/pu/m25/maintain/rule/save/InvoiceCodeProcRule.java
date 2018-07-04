/**
 * 
 */
package nc.bs.pu.m25.maintain.rule.save;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.pub.util.PUBillCodeUtils;

/**
 * 
 * @description
 * ���ݺŴ���:���ù������봦��
 * @scene
 * �����BP
 * @param
 * ��
 *
 * @since 6.3
 * @version 2014-10-22 ����3:20:48
 * @author zhangshqb
 */
public class InvoiceCodeProcRule implements ICompareRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos, InvoiceVO[] orgVos) {
    if (null == orgVos) {// ��������
      this.handleInsertVO(vos);
    }
    else {// �޸ı���
      this.handleUpdateVO(vos, orgVos);
    }
  }

  private void handleInsertVO(InvoiceVO[] vos) {
    PUBillCodeUtils.getInvoiceCode().createBillCode(vos);
  }

  private void handleUpdateVO(InvoiceVO[] vos, InvoiceVO[] orgVos) {
    PUBillCodeUtils.getInvoiceCode().upadteBillCode(vos, orgVos);
  }

}
