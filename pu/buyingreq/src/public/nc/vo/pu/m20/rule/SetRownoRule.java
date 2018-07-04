package nc.vo.pu.m20.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;

/**
 * @description
 *              �빺����ȫ�кŹ���
 * @scene
 *        �ƻ�������ʽ�����빺��������������ʽ�����빺��
 * @param ��
 * @since 6.3
 * @version 2014-10-21 ����2:04:55
 * @author yanxm5
 */
public class SetRownoRule implements IRule<PraybillVO> {

  /**
   * ���෽����д
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(PraybillVO[] vos) {
    this.setRowno(vos);
  }

  private void setRowno(PraybillVO[] vos) {
    for (PraybillVO vo : vos) {
      PraybillItemVO[] items = vo.getBVO();

      for (int i = 0, len = items.length; i < len; i++) {
        items[i].setCrowno("" + (i + 1) * 10);
      }
    }
  }
}
