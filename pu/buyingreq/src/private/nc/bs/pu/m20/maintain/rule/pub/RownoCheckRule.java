/**
 * $�ļ�˵��$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-26 ����01:09:42
 */
package nc.bs.pu.m20.maintain.rule.pub;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.rule.RowNoCheckRule;

/**
 * @description
 *              �빺���кż��
 * @scene
 *        �빺���������޸�
 * @param ��
 * @since 6.3
 * @version 2014-10-21 ����9:57:03
 * @author yanxm5
 */
public class RownoCheckRule implements IRule<PraybillVO> {

  @Override
  public void process(PraybillVO[] vos) {
    new RowNoCheckRule().checkRowNo(vos, PraybillItemVO.CROWNO);
  }
}
