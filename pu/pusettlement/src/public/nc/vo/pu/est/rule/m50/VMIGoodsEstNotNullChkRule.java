/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-24 ����01:30:45
 */
package nc.vo.pu.est.rule.m50;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.est.entity.m50.VmiEstVO;
import nc.vo.pu.est.rule.GoodsEstNotNullChkRule;

/**
 * 
 * @description
 * �����ݹ�ʱ������
 * ��̨Ӧ�÷ŵ������ݹ����µ�ǰ������
 * @scene
 * �ݹ���BP����
 * @param
 * ��
 *
 * @since 6.3
 * @version 2014-10-23 ����9:56:06
 * @author zhangshqb
 */
public class VMIGoodsEstNotNullChkRule implements IRule<VmiEstVO> {

  @Override
  public void process(VmiEstVO[] vos) {
    new GoodsEstNotNullChkRule().process(vos);
  }

}
