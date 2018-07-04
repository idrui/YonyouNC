/**
 * $�ļ�˵��$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-26 ����11:25:38
 */
package nc.bs.pu.m20.maintain.rule.pub;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.rule.ChkDate;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�빺���ڡ��������ڡ����鶩�����ڼ�����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-3-26 ����11:25:38
 */
public class ChkDateRule implements IRule<PraybillVO> {

  @Override
  public void process(PraybillVO[] vos) {
    this.ChkDate(vos);
  }

  private void ChkDate(PraybillVO[] vos) {
    if ((null == vos) || (vos.length == 0)) {
      return;
    }
    for (PraybillVO vo : vos) {
      new ChkDate().checkDate(vo);
    }
  }

}
