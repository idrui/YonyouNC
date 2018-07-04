package nc.vo.pu.m422x.rule.api;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pu.pub.rule.info.ChkItemInfo;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 *            �����������뵥����ǰ��У���û����봫����ֶ�
 * @scene
 *       �����������뵥����
 * @param
 * 
 *
 * @since 6.5
 * @version 2015-10-31 ����2:17:09
 * @author luojw
 */
public class StoreReqNullItemRule implements IRule<StoreReqAppVO>{
  
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

    ChkItemInfo[] headInfos = this.getHeadInfos();
    ChkItemInfo[] itemInfos = this.getItemInfos();

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
  private void checkBodyItems(StoreReqAppVO vo, ChkItemInfo[] itemInfos,
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
  private void checkEmpty(StoreReqAppVO vo, ChkItemInfo[] headInfos,
      ChkItemInfo[] itemInfos) {
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
  private void checkHeadItems(StoreReqAppVO vo, ChkItemInfo[] headInfos,
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
  private ChkItemInfo[] getHeadInfos() {
    ChkItemInfo[] headInfos = new ChkItemInfo[3];
    headInfos[0] =
        new ChkItemInfo(StoreReqAppHeaderVO.PK_ORG,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
                "UC000-0001825")/* @res "�����֯" */);
    headInfos[1] =
        new ChkItemInfo(StoreReqAppHeaderVO.FREQTYPEFLAG,
            "��������");
    headInfos[2] =
        new ChkItemInfo(StoreReqAppHeaderVO.VTRANTYPECODE,
            "��������");

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
  private ChkItemInfo[] getItemInfos() {
    ChkItemInfo[] itemInfos = new ChkItemInfo[2];
    itemInfos[0] =
        new ChkItemInfo(StoreReqAppItemVO.PK_MATERIAL,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
                "UC000-0002911")/* @res "���ϱ���" */);
    itemInfos[1] =
        new ChkItemInfo(StoreReqAppItemVO.NASTNUM, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("common", "UC000-0002282")/* @res "����" */);
    return itemInfos;
  }

}
