/**
 * $�ļ�˵��$
 *
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-25 ����06:45:59
 */
package nc.bs.pu.m20.maintain.rule.approve;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @description
 *              �빺���Ƿ���������
 * @scene
 *        �빺������
 * @param ��
 * @since 6.3
 * @version 2014-10-21 ����8:50:35
 * @author yanxm5
 */
public class CheckApproveRule implements IRule<PraybillVO> {

  /**
   * ���෽����д
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(PraybillVO[] vos) {
    // ����״̬�Ϸ��Լ��
    this.billStatusCheck(vos);
  }

  private void billStatusCheck(PraybillVO[] vos) {
    for (PraybillVO vo : vos) {
      // ״̬�����ɻ������в��ܽ�������
      if (vo.getHVO().getFbillstatus().intValue() != POEnumBillStatus.FREE
          .toInt()
          && vo.getHVO().getFbillstatus().intValue() != POEnumBillStatus.COMMIT
              .toInt()
          && vo.getHVO().getFbillstatus().intValue() != POEnumBillStatus.APPROVING
              .toInt()) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004020_0", "04004020-0024")/*
                                                                     * @res
                                                                     * "����״̬����ȷ������������"
                                                                     */);
      }
    }
  }
}
