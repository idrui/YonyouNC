package nc.vo.pu.m23.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.pu.reference.ic.ATPServices;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * 
 * @description
 * �������Ŀ��������������࣬��Ϊ����ǰ�����������
 * @scene
 * ����������
 * @param
 * isBeforeRule
 *
 * @since 6.3
 * @version 2010-7-2 ����10:35:40
 * @author hanbin
 */

public class ArriveATPUpdateRule implements IRule<ArriveVO> {

  private String cbilltype = POBillType.Arrive.getCode();

  private boolean isBeforeRule;

  public ArriveATPUpdateRule(boolean isBeforeRule) {
    this.isBeforeRule = isBeforeRule;
  }

  @Override
  public void process(ArriveVO[] vos) {
    if (this.isBeforeRule) {
      this.modifyATPBefore(vos);
    }
    else {
      this.modifyATPAfter(vos);
    }
  }
  /*
   * ���������
   */
  private void modifyATPAfter(AggregatedValueObject[] bills) {
    ATPServices.modifyATPAfter(this.cbilltype, bills);
  }

  /*
   * ����ǰ����
   */
  private void modifyATPBefore(AggregatedValueObject[] bills) {
    ATPServices.modifyATPBefore(this.cbilltype, bills);
  }
}
