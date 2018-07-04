package nc.bs.pu.m23.maintain.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.utils.ArrivePublicUtil;
import nc.vo.pubapp.pattern.data.ValueUtils;

/**
 * 
 * @description
 * �˻����������������
 * @scene
 * 
 * @param
 * ��
 *
 * @since 6.3
 * @version 2010-1-14 ����03:42:46
 * @author hanbin
 */

public class ChkBackArWaitNumRule implements IRule<ArriveVO> {

  @Override
  public void process(ArriveVO[] voArray) {

    for (ArriveVO aggVO : voArray) {
      // �˻����������������
      this.chkBackArWaitNum(aggVO);
    }
  }

  private void chkBackArWaitNum(ArriveVO aggVO) {

    // ������û���˻���־�������������������
    if (!ValueUtils.getBoolean(aggVO.getHVO().getBisback())) {
      return;
    }

    // ί�ⶩ�������
    if (ArrivePublicUtil.isArriveFromSC(aggVO)) {
      return;
    }

    // // TODO hanbin ��ȡ��Դ�������Ƿ����˻���־
    // String[] poBidArray = (String[]) AggVOUtil.getDistinctItemFieldArray(
    // new AggregatedValueObject[] { aggVO }, ArriveItemVO.CSOURCEBID,
    // String.class);
    //
    // // <�ɹ�������ID,�ɹ�������VO>
    // Map<String, CircularlyAccessibleValueObject> poBidToPOVOMap = new
    // HashMap<String, CircularlyAccessibleValueObject>();

    // TODO hanbin �ȴ�����ṩ�ӿ�

  }

}
