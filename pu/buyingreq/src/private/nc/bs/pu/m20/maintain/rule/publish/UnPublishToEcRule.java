/**
 * $文件说明$
 *
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-9 上午09:26:01
 */
package nc.bs.pu.m20.maintain.rule.publish;

import java.util.ArrayList;
import java.util.HashSet;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.itf.scmpub.reference.ec.ECServicesForPUUtil;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              请购单取消发布到电子商务规则
 * @scene
 *        请购单取消发布到电子商务
 * @param 无
 * @since 6.3
 * @version 2014-10-21 上午10:40:54
 * @author yanxm5
 */
public class UnPublishToEcRule implements ICompareRule<PraybillVO> {

  @Override
  public void process(PraybillVO[] voArray, PraybillVO[] originVOArray) {

    String[] pks =
        ECServicesForPUUtil.unPublishToEc(this.getPublishVO(voArray,
            originVOArray));
    this.setMsgAndNotPublishVO(pks, voArray);
  }

  private PraybillVO[] getPublishVO(PraybillVO[] voArray,
      PraybillVO[] originVOArray) {
    PraybillVO[] vos = new PraybillVO[voArray.length];
    for (int i = 0, len = voArray.length; i < len; i++) {
      vos[i] = (PraybillVO) voArray[i].clone();
      PraybillItemVO[] items = vos[i].getBVO();
      PraybillItemVO[] olditems = originVOArray[i].getBVO();
      ArrayList<PraybillItemVO> litems = new ArrayList<PraybillItemVO>();
      for (int j = 0, lenj = items.length; j < lenj; j++) {
        if (!items[j].getBpublishtoec().booleanValue()
            && olditems[j].getBpublishtoec().booleanValue()) {
          litems.add(items[j]);
        }

      }
      vos[i].setBVO(litems.toArray(new PraybillItemVO[litems.size()]));
    }

    return vos;
  }

  private void setMsgAndNotPublishVO(String[] pks, PraybillVO[] voArray) {
    if (ArrayUtils.isEmpty(pks)) {
      return;
    }

    HashSet<String> pkSet = new HashSet<String>();
    for (String pk : pks) {
      pkSet.add(pk);
    }

    StringBuffer err;
    for (int i = 0, len = voArray.length; i < len; i++) {
      err = new StringBuffer();
      PraybillItemVO[] items = voArray[i].getBVO();
      for (int j = 0, lenj = items.length; j < lenj; j++) {
        if (pkSet.contains(items[j].getPk_praybill_b())) {
          items[j].setBpublishtoec(UFBoolean.TRUE);
          items[j].setStatus(VOStatus.UNCHANGED);
          err.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
              "4004020_0", "04004020-0036", null, new String[] {
                items[j].getCrowno()
              })/* @res "第{0}行下游已经处理，不能取消发布。" */);
        }
      }
      if (null == voArray[i].getMsg()) {
        voArray[i].setMsg(err.toString());
      }
      else {
        voArray[i].setMsg(voArray[i].getMsg() + err.toString());
      }
    }

  }
}
