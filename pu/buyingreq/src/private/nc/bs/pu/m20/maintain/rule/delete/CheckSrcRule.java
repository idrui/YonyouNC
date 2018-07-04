package nc.bs.pu.m20.maintain.rule.delete;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.enumeration.PuRefBillTypeIdEnum;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @description
 *              检查请购单是否来源于资产配置申请
 * @scene
 *        请购单删除
 * @since 6.36
 * @version 2015-4-8 下午1:48:59
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
            })/* @res "{0}号请购单来源资产配置申请不能删除！\n" */);
      }
    }
    if (sMessage.length() > 0) {
      ExceptionUtils.wrappBusinessException(sMessage.toString());
    }
  }
}
