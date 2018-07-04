package nc.bs.pu.m23.writeback.pu.m21.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveViewVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 
 * @description
 * ���β����������ڳ����˻����ɲ���������Χ�����򣺲�������<=�˻�����
 * @scene
 * �ɹ������������д
 * @param
 * ��
 *
 * @since 6.3
 * @version 2010-1-11 ����06:43:36
 * @author hanbin
 */

public class ChkReplNumRule implements IRule<ArriveViewVO> {

  @Override
  public void process(ArriveViewVO[] viewVOArray) {
    for (ArriveViewVO view : viewVOArray) {
      ArriveItemVO item = view.getBVO();
      // �˻��������ۼƲ�������
      UFDouble backNum = item.getNnum();
      UFDouble naccumReplNum = item.getNaccumreplnum();

      if (backNum.add(naccumReplNum).doubleValue() > 0) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0097")/*@res "���β����������ڳ����˻����ɲ���������Χ"*/);
      }
    }
  }
}