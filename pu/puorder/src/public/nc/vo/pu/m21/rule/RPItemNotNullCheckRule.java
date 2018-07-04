package nc.vo.pu.m21.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.pub.OrderReceivePlanUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * �����ƻ��ǿ�У��
 * 
 * @since 6.0
 * @version 2011-5-27 ����08:17:24
 * @author wuxla
 */

public class RPItemNotNullCheckRule implements IRule<BatchOperateVO> {
  private static class ItemChkInfo {

    private String itemCode;

    private String itemName;

    public ItemChkInfo(String itemCode, String itemName) {
      this.itemCode = itemCode;
      this.itemName = itemName;
    }

    public String getItemCode() {
      return this.itemCode;
    }

    public String getItemName() {
      return this.itemName;
    }
  }

  @Override
  public void process(BatchOperateVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    OrderReceivePlanVO[] rpVOs = OrderReceivePlanUtils.getAddAndUpVOs(vos[0]);
    if (ArrayUtils.isEmpty(rpVOs)) {
      return;
    }

    ItemChkInfo[] itemInfos = new ItemChkInfo[17];
    itemInfos[0] =
        new ItemChkInfo(OrderReceivePlanVO.VBILLCODE,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_2",
                "2400403002-0151")/*
                                   * @res
                                   * "�����ƻ���"
                                   */);
    itemInfos[1] =
        new ItemChkInfo(OrderReceivePlanVO.PK_ORDER,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
                "04004030-0037")/*
                                 * @res
                                 * "�ɹ�����"
                                 */);
    itemInfos[2] =
        new ItemChkInfo(OrderReceivePlanVO.PK_ORDER_B,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_2",
                "2400403002-0158")/*
                                   * @res
                                   * "�ɹ�������ϸ"
                                   */);
    itemInfos[3] =
        new ItemChkInfo(OrderReceivePlanVO.PK_MATERIAL,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
                "UC000-0002911")/* @res "���ϱ���" */);
    itemInfos[4] =
        new ItemChkInfo(OrderReceivePlanVO.PK_SRCMATERIAL,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
                "04004030-0186")/*
                                 * @res
                                 * "���ϰ汾��Ϣ"
                                 */);
    itemInfos[5] =
        new ItemChkInfo(OrderReceivePlanVO.DPLANARRVDATE,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_2",
                "2400403001-0021")/*
                                   * @res
                                   * "�ƻ���������"
                                   */);
    itemInfos[6] =
        new ItemChkInfo(OrderReceivePlanVO.NNUM, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0189")/*
                                                                     * @res
                                                                     * "������"
                                                                     */);
    itemInfos[7] =
        new ItemChkInfo(OrderReceivePlanVO.PK_GROUP,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
                "UC001-0000072")/* @res "����" */);
    itemInfos[8] =
        new ItemChkInfo(OrderReceivePlanVO.PK_ORG, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0185")/*
                                                                     * @res
                                                                     * "�ɹ���֯���°�"
                                                                     */);
    itemInfos[9] =
        new ItemChkInfo(OrderReceivePlanVO.PK_ORG_V,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
                "UC000-0004091")/* @res "�ɹ���֯" */);
    itemInfos[10] =
        new ItemChkInfo(OrderReceivePlanVO.CUNITID, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0194")/*
                                                                     * @res
                                                                     * "����λ"
                                                                     */);
    itemInfos[11] =
        new ItemChkInfo(OrderReceivePlanVO.CASTUNITID,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
                "04004030-0193")/* @res "��λ" */);
    itemInfos[12] =
        new ItemChkInfo(OrderReceivePlanVO.VCHANGERATE,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
                "UC000-0002161")/* @res "������" */);
    itemInfos[13] =
        new ItemChkInfo(OrderReceivePlanVO.CQTUNITID,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
                "04004030-0195")/*
                                 * @res
                                 * "���۵�λ"
                                 */);
    itemInfos[14] =
        new ItemChkInfo(OrderReceivePlanVO.VQTUNITRATE,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
                "UC000-0002116")/* @res "���ۻ�����" */);
    itemInfos[15] =
        new ItemChkInfo(OrderReceivePlanVO.PK_ARRVSTOORG,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_2",
                "2400403002-0117")/*
                                   * @res
                                   * "�ջ������֯�汾��Ϣ"
                                   */);
    itemInfos[16] =
        new ItemChkInfo(OrderReceivePlanVO.PK_ARRVSTOORG_V,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_2",
                "2400403002-0117")/*
                                   * @res
                                   * "�ջ������֯"
                                   */);
    StringBuilder sb = new StringBuilder();
    for (OrderReceivePlanVO vo : rpVOs) {
      StringBuilder itemBuilder = new StringBuilder();
      for (int i = 0; i < itemInfos.length; ++i) {
        if (vo.getAttributeValue(itemInfos[i].getItemCode()) != null) {
          continue;
        }
        itemBuilder.append(itemInfos[i].getItemName()).append(",");
      }
      if (itemBuilder.length() > 0) {
        itemBuilder.deleteCharAt(itemBuilder.length() - 1);
        sb.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
            "04004030-0345", null, new String[] {
              vo.getVbillcode(), itemBuilder.toString()
            })/* �����ƻ���{0}��{1}\n */);
      }
    }
    if (sb.length() > 0) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004030_0", "04004030-0304", null, new String[] {
            sb.toString()
          })/* �����ֶγ��ֿ�ֵ��\n{0} */);
    }
  }

}
