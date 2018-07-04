package nc.bs.pu.m20.rewrite.pu.rule;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillViewVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUParaValue.ctrltype;
import nc.vo.pu.pub.rule.ToleranceCalcRule;
import nc.vo.pub.lang.UFBoolean;

/**
 * @description
 *              �빺���Ƚ��ݲ��д�빺����
 * @scene
 *        �빺����д��������
 * @param String tolerFiled �ݲ��ֶ���
 *          ctrltype tolerPara �ݲ����
 *          String wbNumField ��д���ֶ���
 *          UFBoolean isUserConfirm �û�ȷ��ѡ��
 * @since 6.3
 * @version 2014-10-21 ����10:20:54
 * @author yanxm5
 */

public class PrayOrderWriteBackTolerRule extends ToleranceCalcRule implements
    IRule<PraybillViewVO> {
  private UFBoolean isUserConfirm;

  private String tolerFiled;

  private ctrltype tolerPara;

  private String wbNumField;

  public PrayOrderWriteBackTolerRule(String tolerFiled, ctrltype tolerPara,
      String wbNumField, UFBoolean isUserConfirm) {
    super();
    this.tolerFiled = tolerFiled;
    this.tolerPara = tolerPara;
    this.wbNumField = wbNumField;
    this.isUserConfirm = isUserConfirm;
  }

  @Override
  public String getBidFiled() {
    return PraybillItemVO.PK_PRAYBILL_B;
  }

  @Override
  public String getNumField() {
    return PraybillItemVO.NNUM;
  }

  @Override
  public String getTable() {
    return PUEntity.M20_B_TABLE;
  }

  @Override
  public void process(PraybillViewVO[] vos) {
    List<String> srcBidList = new ArrayList<String>();
    for (PraybillViewVO vo : vos) {
      srcBidList.add(vo.getPk_praybill_b());
    }
    this.toleranceCompare(this.wbNumField,
        srcBidList.toArray(new String[srcBidList.size()]), this.tolerFiled,
        this.tolerPara, this.isUserConfirm);
  }

}
