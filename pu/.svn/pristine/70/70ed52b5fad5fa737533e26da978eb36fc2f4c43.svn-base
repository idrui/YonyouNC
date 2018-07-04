package nc.bs.pu.m20.maintain.rule.publish;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.data.ValueUtils;

/**
 * @description
 *              请购单检查是否可以取消发布到电子商务
 * @scene
 *        请购单取消发布到电子商务
 * @param 无
 * @since 6.3
 * @version 2014-10-21 上午10:40:34
 * @author yanxm5
 */
public class CheckUnPublishRule implements ICompareRule<PraybillVO> {

  private StringBuffer err = new StringBuffer();

  @Override
  public void process(PraybillVO[] voArray, PraybillVO[] originVOArray) {
    this.err = new StringBuffer();
    this.checkBill(voArray, originVOArray);

  }

  private void checkBill(PraybillVO[] voArray, PraybillVO[] originVOArray) {

    for (int i = 0, len = voArray.length; i < len; i++) {
      this.err = new StringBuffer();
      PraybillItemVO[] items = voArray[i].getBVO();
      PraybillItemVO[] olditems = originVOArray[i].getBVO();
      for (int j = 0, lenj = items.length; j < lenj; j++) {
        if (items[j].getStatus() == VOStatus.UPDATED) {
          // 检查是否已经取消
          this.checkCanlBill(items[j], olditems[j]);
        }
      }
      if (this.err.length() > 0) {
        voArray[i].setMsg(this.err.toString());
      }
    }

  }

  private void checkCanlBill(PraybillItemVO newitem, PraybillItemVO item) {

    if (!item.getBpublishtoec().booleanValue()) {
      // 修改提示,modify by wangljc at 2011-6-9 16:53:34
      this.err.append(item.getCrowno()
          + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004020_0",
              "04004020-0034")/* @res "未发布到电子商务,不能取消" */);
      newitem.setBpublishtoec(item.getBpublishtoec());
      newitem.setStatus(VOStatus.UNCHANGED);
    }
    if (ValueUtils.getBoolean(item.getBrowclose())) {
      // 修改提示,modify by wangljc at 2011-6-9 16:53:34
      this.err.append(item.getCrowno()
          + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004020_0",
              "04004020-0101")/* @res "行已经关闭。" */);
      newitem.setBpublishtoec(item.getBpublishtoec());
      newitem.setStatus(VOStatus.UNCHANGED);
    }

  }
}
