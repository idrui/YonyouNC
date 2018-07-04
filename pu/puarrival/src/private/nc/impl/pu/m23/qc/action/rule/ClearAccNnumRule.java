package nc.impl.pu.m23.qc.action.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.lang.UFDouble;

/**
 * 
 * @description
 *�����Ӧ�е��ۼƱ����������ۼƺϸ��������ۼƲ��ϸ�����
 * @scene
 * ����������
 * @param
 * ��
 *
 * @since 6.3
 * @version 2011-4-22 ����06:30:06
 * @author liuchx
 */

public class ClearAccNnumRule implements IRule<ArriveVO> {


  @Override
  public void process(ArriveVO[] vos) {
    for (ArriveVO bill : vos) {
      ArriveItemVO[] children = bill.getBVO();
      for (ArriveItemVO child : children) {
        String[] clearKeys =
            {
              ArriveItemVO.NACCUMCHECKNUM, ArriveItemVO.NELIGNUM,
              ArriveItemVO.NNOTELIGNUM
            };

        this.clearValue(child, clearKeys);
      }

    }
  }
  /**
   * �����Ӧ�е��ۼƱ����������ۼƺϸ��������ۼƲ��ϸ�����
   */
  private void clearValue(ArriveItemVO child, String[] clearKeys) {
    for (String key : clearKeys) {
      child.setAttributeValue(key, UFDouble.ZERO_DBL);
    }
  }

}
