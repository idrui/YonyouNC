package nc.bs.pu.m23.maintain.rule.delete;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.pub.util.PUBillCodeUtils;

/**
 * 
 * @description
 * ɾ������ʱ���˻����ݺŹ��򣬻��˵��ݺ�
 * @scene
 * ������ɾ��
 * @param
 * ��
 *
 * @since 6.3
 * @version 2010-1-26 ����02:33:55
 * @author hanbin
 */
public class DeleteBillCodeRule implements IRule<ArriveVO> {

  @Override
  public void process(ArriveVO[] vos) {
    PUBillCodeUtils.getArriveCode().returnBillCode(vos);
  }
}
