/**
 * $文件说明$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-6 下午08:03:30
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
 *            物资需求申请单保存时，非空项检查
 * @scene
 *      物资需求申请单保存
 * @param
 * 
 *
 * @since 6.0
 * @version 2010-8-6 下午08:03:30
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
   * 父类方法重写
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
   * 方法功能描述：检查表体
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   * @param itemInfos
   * @param sb
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-8-6 下午08:16:07
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
            })/* 表体第{0}行的以下字段不允许为空：{1}\n */);
      }
    }
  }

  /**
   * 方法功能描述：检查非空项
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   * @param headInfos
   * @param itemInfos
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-8-6 下午08:14:38
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
   * 方法功能描述：检查表头
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   * @param headInfos
   * @param sb
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-8-6 下午08:15:53
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
          })/* 表头以下字段不允许为空：{0}\n */);
    }
  }

  /**
   * 方法功能描述：表头非空项
   * <p>
   * <b>参数说明</b>
   * 
   * @return
   *         <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-8-6 下午08:08:48
   */
  private ItemChkInfo[] getHeadInfos() {
    ItemChkInfo[] headInfos = new ItemChkInfo[4];
    headInfos[0] =
        new ItemChkInfo(StoreReqAppHeaderVO.PK_ORG, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("common", "UC000-0001825")/* @res "库存组织" */);
    headInfos[1] =
        new ItemChkInfo(StoreReqAppHeaderVO.PK_ORG_V,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
                "UC000-0001825")/* @res "库存组织" */);
    headInfos[2] =
        new ItemChkInfo(StoreReqAppHeaderVO.DBILLDATE,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
                "UC000-0002970")/* @res "申请日期" */);
    headInfos[3] =
        new ItemChkInfo(StoreReqAppHeaderVO.PK_GROUP,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
                "UC001-0000072")/* @res "集团" */);

    return headInfos;
  }

  /**
   * 方法功能描述：表体非空项
   * <p>
   * <b>参数说明</b>
   * 
   * @return
   *         <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-8-6 下午08:13:27
   */
  private ItemChkInfo[] getItemInfos() {
    ItemChkInfo[] itemInfos = new ItemChkInfo[4];
    itemInfos[0] =
        new ItemChkInfo(StoreReqAppItemVO.CROWNO, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("common", "UC000-0003389")/* @res "行号" */);
    itemInfos[1] =
        new ItemChkInfo(StoreReqAppItemVO.PK_MATERIAL,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
                "UC000-0002911")/* @res "物料编码" */);
    itemInfos[2] =
        new ItemChkInfo(StoreReqAppItemVO.PK_SRCMATERIAL,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004010_0",
                "04004010-0015")/* @res "物料版本信息" */);
    itemInfos[3] =
        new ItemChkInfo(StoreReqAppItemVO.NASTNUM, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("common", "UC000-0002282")/* @res "数量" */);
    return itemInfos;
  }
}
