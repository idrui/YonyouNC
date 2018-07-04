/**
 * $�ļ�˵��$
 * 
 * @author lixyp
 * @version 6.1
 * @see
 * @since 6.1
 * @time 2011-12-28 ����11:02:27
 */
package nc.bs.pu.m20.maintain.rule.pub;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.rule.SetOrdertrantype;
import nc.vo.pu.pub.util.BillHelper;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              �빺����ȫ�������͹���
 * @scene
 *        �ƻ�������ʽ�����빺��������������ʽ�����빺��
 * @param ��
 * @since 6.3
 * @version 2014-10-21 ����2:07:22
 * @author yanxm5
 */
public class SetOrdertrantypeRule implements IRule<PraybillVO> {

  /**
   * ���෽����д
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(PraybillVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    this.setOrdertrantype(vos);
  }

  /**
   * ��ȫ�������͡�
   * <p>
   * <b>����˵��</b>
   * 
   * @since 6.1
   * @author lixyp
   * @time 2011-12-28 ����11:10:11
   */
  public void setOrdertrantype(PraybillVO[] vos) {
    for (PraybillVO vo : vos) {
      new SetOrdertrantype().setOrdertrantype(new BillHelper<PraybillVO>(vo));
    }
  }
}
