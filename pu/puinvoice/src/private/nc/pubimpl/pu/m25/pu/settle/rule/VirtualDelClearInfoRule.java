/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-13 ����07:01:08
 */
package nc.pubimpl.pu.m25.pu.settle.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;

/**
 * 
 * @description
 * ���ⷢƱɾ��ʱ���������Ϣ��
 * @scene
 * ɾ�����ⷢƱ
 * @param
 * ��
 *
 * @since 6.3
 * @version 2014-10-22 ����3:06:29
 * @author zhangshqb
 */
public class VirtualDelClearInfoRule implements IRule<InvoiceVO> {

  @Override
  public void process(InvoiceVO[] vos) {
    for (InvoiceVO vo : vos) {
      InvoiceHeaderVO header = vo.getParentVO();
      header.setApprover(null);
      header.setTaudittime(null);
      header.setFbillstatus(POEnumBillStatus.FREE.toInteger());
    }
  }

}
