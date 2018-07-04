/**
 * $文件说明$
 *
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-9 下午03:31:25
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
 *              请购单检查是否可以发布到电子商务
 * @scene
 *        请购单发布到电子商务
 * @param 无
 * @since 6.3
 * @version 2014-10-21 上午10:18:49
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
          // 检查是否有下游单据
          this.checkHasNextBill(items[j], olditems[j]);
          // 应该控制只有物料基本分类:是否电子商务属性为是的物料才能发布到电子商务
          // this.checkMarClassInfo(items[j], olditems[j]);
          // 检查物料分类下游做
          //
        }

      }
      // 应该控制只有物料基本分类:是否电子商务属性为是的物料才能发布到电子商务
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
          })/* @res "第{0}行已经关闭。" */);
      this.err.append("\n");
      newitem.setBpublishtoec(item.getBpublishtoec());
      newitem.setStatus(VOStatus.UNCHANGED);
      return;
    }
    if (ValueUtils.getBoolean(item.getBpublishtoec())) {
      this.err.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4004020_0", "04004020-0100", null, new String[] {
            item.getCrowno()
          })/* @res "第{0}行已经发布到电子商务。" */);
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
          })/* @res "第{0}行已经生成订单。" */);
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
          })/* @res "第{0}行已经生成合同。" */);
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
          })/* @res "第{0}行已经生成价格审批单。" */);
      this.err.append("\n");
      return;
    }
    if (item.getNquotebill() != null && item.getNquotebill().doubleValue() > 0) {
      newitem.setBpublishtoec(item.getBpublishtoec());
      newitem.setStatus(VOStatus.UNCHANGED);
      this.err.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4004020_0", "04004020-0032", null, new String[] {
            item.getCrowno()
          })/* @res "第{0}行已经生成询报价单。" */);
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
          })/* @res "第{0}行已经生成总括订单。" */);
      this.err.append("\n");
      return;
    }

  }

  // 应该控制只有物料基本分类:是否电子商务属性为是的物料才能发布到电子商务
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
              })/* @res "第{0}行物料的基本属性中，电子商务没有勾选，不能发布到电子商务。" */);
          this.err.append("\n");
          newitems[i].setBpublishtoec(items[i].getBpublishtoec());
          newitems[i].setStatus(VOStatus.UNCHANGED);
        }
      }
    }
  }

}
