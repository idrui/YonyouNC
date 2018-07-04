/**
 * $�ļ�˵��$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-6 ����08:03:30
 */
package nc.vo.pu.m422x.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 *            �����������뵥����ʱ���ǿ�����
 * @scene
 *      �����������뵥����
 * @param
 * 
 *
 * @since 6.0
 * @version 2010-8-6 ����08:03:30
 * @author wuxla
 */
public class ItemEmptyRule implements IRule<StoreReqAppVO> {

  static class ItemChkInfo {

    private String itemCode;

    private String itemName;

    ItemChkInfo(String itemCode, String itemName) {
      this.itemCode = itemCode;
      this.itemName = itemName;
    }

    public String getItemCode() {
      return this.itemCode;
    }

    public String getItemName() {
      return this.itemName;
    }

    public void setItemCode(String itemCode) {
      this.itemCode = itemCode;
    }

    public void setItemName(String itemName) {
      this.itemName = itemName;
    }
  }

  /**
   * ���෽����д
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(StoreReqAppVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    ItemChkInfo[] headInfos = this.getHeadInfos();
    ItemChkInfo[] itemInfos = this.getItemInfos();

    for (StoreReqAppVO vo : vos) {
      this.checkEmpty(vo, headInfos, itemInfos);
    }
  }

  /**
   * ��������������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param vo
   * @param itemInfos
   * @param sb
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-8-6 ����08:16:07
   */
  private void checkBodyItems(StoreReqAppVO vo, ItemChkInfo[] itemInfos,
      StringBuilder sb) {
    StoreReqAppItemVO[] itemVOs = vo.getBVO();
    if (ArrayUtils.isEmpty(itemVOs)) {
      return;
    }

    for (StoreReqAppItemVO itemVO : itemVOs) {
      StringBuilder itemBuilder = new StringBuilder();
      for (int i = 0; i < itemInfos.length; ++i) {
        if (itemVO.getAttributeValue(itemInfos[i].getItemCode()) != null) {
          continue;
        }

        itemBuilder.append(itemInfos[i].getItemName()).append(",");
      }

      if (itemBuilder.length() > 0) {
        itemBuilder.deleteCharAt(itemBuilder.length() - 1);
        sb.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004010_0",
            "04004010-0023", null, new String[] {
              itemVO.getCrowno(), itemBuilder.toString()
            })/* �����{0}�е������ֶβ�����Ϊ�գ�{1}\n */);
      }
    }
  }

  /**
   * �����������������ǿ���
   * <p>
   * <b>����˵��</b>
   * 
   * @param vo
   * @param headInfos
   * @param itemInfos
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-8-6 ����08:14:38
   */
  private void checkEmpty(StoreReqAppVO vo, ItemChkInfo[] headInfos,
      ItemChkInfo[] itemInfos) {
    StringBuilder sb = new StringBuilder();
    this.checkHeadItems(vo, headInfos, sb);
    this.checkBodyItems(vo, itemInfos, sb);

    if (sb.length() > 0) {
      ExceptionUtils.wrappBusinessException(sb.toString());
    }
  }

  /**
   * ������������������ͷ
   * <p>
   * <b>����˵��</b>
   * 
   * @param vo
   * @param headInfos
   * @param sb
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-8-6 ����08:15:53
   */
  private void checkHeadItems(StoreReqAppVO vo, ItemChkInfo[] headInfos,
      StringBuilder sb) {
    StringBuilder headBuilder = new StringBuilder();
    StoreReqAppHeaderVO headerVO = vo.getHVO();
    for (int i = 0; i < headInfos.length; ++i) {
      if (headerVO.getAttributeValue(headInfos[i].getItemCode()) != null) {
        continue;
      }

      headBuilder.append(headInfos[i].getItemName()).append(",");
    }

    if (headBuilder.length() > 0) {
      headBuilder.deleteCharAt(headBuilder.length() - 1);
      sb.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004010_0",
          "04004010-0024", null, new String[] {
            headBuilder.toString()
          })/* ��ͷ�����ֶβ�����Ϊ�գ�{0}\n */);
    }
  }

  /**
   * ����������������ͷ�ǿ���
   * <p>
   * <b>����˵��</b>
   * 
   * @return
   *         <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-8-6 ����08:08:48
   */
  private ItemChkInfo[] getHeadInfos() {
    ItemChkInfo[] headInfos = new ItemChkInfo[4];
    headInfos[0] =
        new ItemChkInfo(StoreReqAppHeaderVO.PK_ORG, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("common", "UC000-0001825")/* @res "�����֯" */);
    headInfos[1] =
        new ItemChkInfo(StoreReqAppHeaderVO.PK_ORG_V,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
                "UC000-0001825")/* @res "�����֯" */);
    headInfos[2] =
        new ItemChkInfo(StoreReqAppHeaderVO.DBILLDATE,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
                "UC000-0002970")/* @res "��������" */);
    headInfos[3] =
        new ItemChkInfo(StoreReqAppHeaderVO.PK_GROUP,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
                "UC001-0000072")/* @res "����" */);

    return headInfos;
  }

  /**
   * ������������������ǿ���
   * <p>
   * <b>����˵��</b>
   * 
   * @return
   *         <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-8-6 ����08:13:27
   */
  private ItemChkInfo[] getItemInfos() {
    ItemChkInfo[] itemInfos = new ItemChkInfo[4];
    itemInfos[0] =
        new ItemChkInfo(StoreReqAppItemVO.CROWNO, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("common", "UC000-0003389")/* @res "�к�" */);
    itemInfos[1] =
        new ItemChkInfo(StoreReqAppItemVO.PK_MATERIAL,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
                "UC000-0002911")/* @res "���ϱ���" */);
    itemInfos[2] =
        new ItemChkInfo(StoreReqAppItemVO.PK_SRCMATERIAL,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004010_0",
                "04004010-0015")/* @res "���ϰ汾��Ϣ" */);
    itemInfos[3] =
        new ItemChkInfo(StoreReqAppItemVO.NASTNUM, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("common", "UC000-0002282")/* @res "����" */);
    return itemInfos;
  }
}
