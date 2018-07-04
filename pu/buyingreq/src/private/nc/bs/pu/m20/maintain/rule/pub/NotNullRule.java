/**
 * $�ļ�˵��$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-18 ����11:04:57
 */
package nc.bs.pu.m20.maintain.rule.pub;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.rule.ChkEmptyWhenSave;

/**
 * @description
 *              �빺���ǿ��������
 * @scene
 *        �빺���������޸�
 * @param ��
 * @since 6.3
 * @version 2014-10-21 ����9:42:23
 * @author yanxm5
 */
public class NotNullRule implements IRule<PraybillVO> {

  @Override
  public void process(PraybillVO[] vos) {
    this.checkNotNull(vos);
  }

  private void checkNotNull(PraybillVO[] vos) {
    if (null == vos || vos.length == 0) {
      return;
    }
    for (PraybillVO vo : vos) {
      new ChkEmptyWhenSave().chkEmpty(vo);
    }
  }
}
