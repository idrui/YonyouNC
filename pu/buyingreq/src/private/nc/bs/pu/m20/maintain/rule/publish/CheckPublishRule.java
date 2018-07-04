/**
 * $�ļ�˵��$
 *
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-9 ����03:31:25
 */
package nc.bs.pu.m20.maintain.rule.publish;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.data.ValueUtils;

/**
 * @description
 *              �빺������Ƿ���Է�������������
 * @scene
 *        �빺����������������
 * @param ��
 * @since 6.3
 * @version 2014-10-21 ����10:18:49
 * @author yanxm5
 */
public class CheckPublishRule implements ICompareRule<PraybillVO> {

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
          // ����Ƿ������ε���
          this.checkHasNextBill(items[j], olditems[j]);
          // Ӧ�ÿ���ֻ�����ϻ�������:�Ƿ������������Ϊ�ǵ����ϲ��ܷ�������������
          // this.checkMarClassInfo(items[j], olditems[j]);
          // ������Ϸ���������
          //
        }

      }
      // Ӧ�ÿ���ֻ�����ϻ�������:�Ƿ������������Ϊ�ǵ����ϲ��ܷ�������������
      this.checkMarClassInfo(items, olditems);
      if (this.err.length() > 0) {
        voArray[i].setMsg(this.err.toString());
      }
    }

  }

  private void checkHasNextBill(PraybillItemVO newitem, PraybillItemVO item) {
    if (ValueUtils.getBoolean(item.getBrowclose())) {
      this.err.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4004020_0", "04004020-0101", null, new String[] {
            item.getCrowno()
          })/* @res "��{0}���Ѿ��رա�" */);
      this.err.append("\n");
      newitem.setBpublishtoec(item.getBpublishtoec());
      newitem.setStatus(VOStatus.UNCHANGED);
      return;
    }
    if (ValueUtils.getBoolean(item.getBpublishtoec())) {
      this.err.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4004020_0", "04004020-0100", null, new String[] {
            item.getCrowno()
          })/* @res "��{0}���Ѿ���������������" */);
      this.err.append("\n");
      newitem.setBpublishtoec(item.getBpublishtoec());
      newitem.setStatus(VOStatus.UNCHANGED);
      return;
    }
    if (item.getNaccumulatenum() != null
        && item.getNaccumulatenum().doubleValue() > 0) {
      this.err.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4004020_0", "04004020-0029", null, new String[] {
            item.getCrowno()
          })/* @res "��{0}���Ѿ����ɶ�����" */);
      this.err.append("\n");
      newitem.setBpublishtoec(item.getBpublishtoec());
      newitem.setStatus(VOStatus.UNCHANGED);
      return;
    }
    if (item.getNgenct() != null && item.getNgenct().doubleValue() > 0) {
      newitem.setBpublishtoec(item.getBpublishtoec());
      newitem.setStatus(VOStatus.UNCHANGED);
      this.err.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4004020_0", "04004020-0030", null, new String[] {
            item.getCrowno()
          })/* @res "��{0}���Ѿ����ɺ�ͬ��" */);
      this.err.append("\n");
      return;
    }
    if (item.getNpriceauditbill() != null
        && item.getNpriceauditbill().doubleValue() > 0) {
      newitem.setBpublishtoec(item.getBpublishtoec());
      newitem.setStatus(VOStatus.UNCHANGED);
      this.err.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4004020_0", "04004020-0031", null, new String[] {
            item.getCrowno()
          })/* @res "��{0}���Ѿ����ɼ۸���������" */);
      this.err.append("\n");
      return;
    }
    if (item.getNquotebill() != null && item.getNquotebill().doubleValue() > 0) {
      newitem.setBpublishtoec(item.getBpublishtoec());
      newitem.setStatus(VOStatus.UNCHANGED);
      this.err.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4004020_0", "04004020-0032", null, new String[] {
            item.getCrowno()
          })/* @res "��{0}���Ѿ�����ѯ���۵���" */);
      this.err.append("\n");
      return;
    }
    if (item.getBisgensaorder() != null
        && item.getBisgensaorder().booleanValue()) {
      newitem.setBpublishtoec(item.getBpublishtoec());
      newitem.setStatus(VOStatus.UNCHANGED);
      this.err.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4004020_0", "04004020-0106", null, new String[] {
            item.getCrowno()
          })/* @res "��{0}���Ѿ���������������" */);
      this.err.append("\n");
      return;
    }

  }

  // Ӧ�ÿ���ֻ�����ϻ�������:�Ƿ������������Ϊ�ǵ����ϲ��ܷ�������������
  private void checkMarClassInfo(PraybillItemVO[] newitems,
      PraybillItemVO[] items) {
    List<String> materials = new ArrayList<String>();
    for (int i = 0, lenj = items.length; i < lenj; i++) {
      materials.add(items[i].getPk_material());
    }
    Map<String, MaterialVO> materialInfo =
        MaterialPubService.queryMaterialBaseInfo(
            materials.toArray(new String[0]), new String[] {
              MaterialVO.ISELECTRANS
            });
    if (null == materialInfo) {
      return;
    }
    for (int i = 0, lenj = items.length; i < lenj; i++) {
      if (newitems[i].getStatus() == VOStatus.UPDATED) {
        MaterialVO baseMaterial = materialInfo.get(items[i].getPk_material());
        if (baseMaterial != null
            && !baseMaterial.getIselectrans().booleanValue()) {
          this.err.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4004020_0", "04004020-0033", null, new String[] {
                items[i].getCrowno()
              })/* @res "��{0}�����ϵĻ��������У���������û�й�ѡ�����ܷ�������������" */);
          this.err.append("\n");
          newitems[i].setBpublishtoec(items[i].getBpublishtoec());
          newitems[i].setStatus(VOStatus.UNCHANGED);
        }
      }
    }
  }

}
