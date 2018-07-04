package nc.bs.pu.m20.maintain.rule.publish;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.data.ValueUtils;

/**
 * @description
 *              �빺������Ƿ����ȡ����������������
 * @scene
 *        �빺��ȡ����������������
 * @param ��
 * @since 6.3
 * @version 2014-10-21 ����10:40:34
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
          // ����Ƿ��Ѿ�ȡ��
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
      // �޸���ʾ,modify by wangljc at 2011-6-9 16:53:34
      this.err.append(item.getCrowno()
          + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004020_0",
              "04004020-0034")/* @res "δ��������������,����ȡ��" */);
      newitem.setBpublishtoec(item.getBpublishtoec());
      newitem.setStatus(VOStatus.UNCHANGED);
    }
    if (ValueUtils.getBoolean(item.getBrowclose())) {
      // �޸���ʾ,modify by wangljc at 2011-6-9 16:53:34
      this.err.append(item.getCrowno()
          + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004020_0",
              "04004020-0101")/* @res "���Ѿ��رա�" */);
      newitem.setBpublishtoec(item.getBpublishtoec());
      newitem.setStatus(VOStatus.UNCHANGED);
    }

  }
}
