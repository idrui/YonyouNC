/**
 * $�ļ�˵��$
 * 
 * @author zhyhang
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-19 ����09:15:18
 */
package nc.bs.pu.est.m45.rule.fee;

import nc.bs.pu.est.rule.fee.FeeEstInfoFillRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.est.entity.m45.PurchaseInEstVO;

/**
 * 
 * @description
 * �����ݹ���Ϣ���
 * @scene
 * �ݹ���BP����
 * @param
 * ��
 *
 * @since 6.3
 * @version 2014-10-23 ����9:42:05
 * @author zhangshqb
 */
public class PurchsInFeeEstInfoFillRule extends FeeEstInfoFillRule implements
    IRule<PurchaseInEstVO> {

  @Override
  public void process(PurchaseInEstVO[] vos) {
    super.process(vos);
  }

}
