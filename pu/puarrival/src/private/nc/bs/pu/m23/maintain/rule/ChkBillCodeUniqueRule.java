package nc.bs.pu.m23.maintain.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.pub.util.PUBillCodeUtils;

/**
 * 
 * @description
 * ������Ҫ������¹��ܣ�
 * �������޸ı�����飬��鵥�ݺ��Ƿ�����ظ�
 * @scene
 * �������������޸ı����
 * @param
 * ��
 *
 * @since 6.3
 * @version 2010-1-26 ����02:39:36
 * @author hanbin
 */

public class ChkBillCodeUniqueRule implements IRule<ArriveVO> {

  @Override
  public void process(ArriveVO[] vos) {
    PUBillCodeUtils.getArriveCode().checkUnique(vos);
  }
}
