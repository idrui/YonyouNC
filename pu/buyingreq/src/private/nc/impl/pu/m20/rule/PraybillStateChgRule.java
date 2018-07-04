/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-9 ����11:54:56
 */
package nc.impl.pu.m20.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.VOStatus;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>����ʱ���빺��״̬��Ϊ�ύ
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-4-26 ����11:33:40
 */
public class PraybillStateChgRule implements IRule<PraybillVO> {

  @Override
  public void process(PraybillVO[] vos) {
    for (PraybillVO vo : vos) {
      vo.getHVO().setStatus(VOStatus.UPDATED);
      vo.getHVO().setFbillstatus(POEnumBillStatus.APPROVING.toInteger());
    }
  }

}
