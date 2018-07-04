package nc.bs.pu.m24.maintain.rule;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m24.entity.PricestlVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @description
 *              �۸���㵥ɾ��ʱ״̬���
 * @scene
 *        �۸���㵥ɾ��
 * @param ��
 * @since 6.3
 * @version 2014-10-22 ����4:17:25
 * @author luojw
 */
public class BillStatusRule implements IRule<PricestlVO> {

  @Override
  public void process(PricestlVO[] voArray) {

    // ����빺��״̬,���������֮һ����������: �����С�����ͨ��
    this.check(voArray);

  }

  /**
   * ������������������빺��״̬,���������֮һ����������: �����С�����ͨ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param aggVO
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-8-8 ����09:44:46
   */
  private void check(PricestlVO[] vos) {
    StringBuffer sMessage = new StringBuffer();
    int nBillStatus;
    for (PricestlVO vo : vos) {

      nBillStatus = vo.getHVO().getFbillstatus().intValue();
      if (nBillStatus == POEnumBillStatus.APPROVING.toInt()) {
        sMessage.append(NCLangResOnserver.getInstance().getStrByID("4004070_0",
            "04004070-0011", null, new String[] {
              vo.getHVO().getVbillcode()
            })/* {0} �ż۸���㵥�����У� �������� ��\n */);
      }
      else if (nBillStatus == POEnumBillStatus.APPROVE.toInt()) {
        sMessage.append(NCLangResOnserver.getInstance().getStrByID("4004070_0",
            "04004070-0012", null, new String[] {
              vo.getHVO().getVbillcode()
            })/* {0} �ż۸���㵥�Ѿ������� �������� ��\n */);
      }
    }
    if (sMessage.length() > 0) {
      ExceptionUtils.wrappBusinessException(sMessage.toString());
    }
  }
}
