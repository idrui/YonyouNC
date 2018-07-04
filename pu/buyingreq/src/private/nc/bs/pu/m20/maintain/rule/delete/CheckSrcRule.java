package nc.bs.pu.m20.maintain.rule.delete;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.enumeration.PuRefBillTypeIdEnum;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @description
 *              ����빺���Ƿ���Դ���ʲ���������
 * @scene
 *        �빺��ɾ��
 * @since 6.36
 * @version 2015-4-8 ����1:48:59
 * @author mengjian
 */
public class CheckSrcRule implements IRule<PraybillVO> {

  @Override
  public void process(PraybillVO[] voArray) {
    this.check(voArray);

  }

  private void check(PraybillVO[] vos) {
    StringBuffer sMessage = new StringBuffer();
    boolean flag = false;
    for (PraybillVO praybillVO : vos) {
      PraybillItemVO[] bvos = praybillVO.getBVO();
      for (PraybillItemVO itemVO : bvos) {
        if (PuRefBillTypeIdEnum.M4A08.getBillTypeId().equals(
            itemVO.getCsourcetypecode())) {
          flag = true;
          break;
        }
      }
      if (flag) {
        sMessage.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
            "4004020_0", "04004020-0113", null, new String[] {
              praybillVO.getHVO().getVbillcode()
            })/* @res "{0}���빺����Դ�ʲ��������벻��ɾ����\n" */);
      }
    }
    if (sMessage.length() > 0) {
      ExceptionUtils.wrappBusinessException(sMessage.toString());
    }
  }
}
