package nc.vo.pu.m20.rule;

import java.util.ArrayList;
import java.util.List;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>新增、修改保存时保存检查VO里面的非空项
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-3-19 下午05:01:13
 */
public class ChkEmptyWhenSave {

  /**
   * 方法功能描述：检查是否为空。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-8-18 下午04:27:21
   */
  public void chkEmpty(PraybillVO vo) {

    this.checkHeadItem(vo.getHVO());
    this.checkBodyItem(vo.getBVO());
  }

  private void checkBodyItem(PraybillItemVO[] bvo) {
    List<ChkItemInfo> itemArray = new ArrayList<>();

    itemArray.add(
        new ChkItemInfo(PraybillItemVO.PK_MATERIAL, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("common", "UC000-0002911")/* @res "物料编码" */));
    itemArray.add(
        new ChkItemInfo(PraybillItemVO.CASTUNITID, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004020_0", "04004020-0058")/* @res "单位" */));
    itemArray.add(
        new ChkItemInfo(PraybillItemVO.NASTNUM, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("common", "UC000-0002282")/* @res "数量" */));
    itemArray.add(
        new ChkItemInfo(PraybillItemVO.PK_PURCHASEORG,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
                "UC000-0004091")/* @res "采购组织" */));
    itemArray.add(
        new ChkItemInfo(PraybillItemVO.PK_SRCMATERIAL,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004020_0",
                "04004020-0059")/* @res "物料(OID)" */));
    itemArray.add(
        new ChkItemInfo(PraybillItemVO.PK_ORG, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004020_0", "04004020-0060")/*
                                                                     * @res
                                                                     * "库存组织版本"
                                                                     */));
    itemArray.add(
        new ChkItemInfo(PraybillItemVO.PK_ORG_V, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("common", "UC000-0001825")/* @res "库存组织" */));
    itemArray.add(
        new ChkItemInfo(PraybillItemVO.CUNITID, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004020_0", "04004020-0061")/*
                                                                     * @res
                                                                     * "主计量单位 "
                                                                     */));
    itemArray.add(
        new ChkItemInfo(PraybillItemVO.VCHANGERATE, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004020_0", "04004020-0062")/*
                                                                     * @res
                                                                     * "换算率 "
                                                                     */));
    itemArray.add(
        new ChkItemInfo(PraybillItemVO.PK_GROUP, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004020_0", "04004020-0063")/*
                                                                     * @res
                                                                     * "所属集团"
                                                                     */));
    itemArray.add(
        new ChkItemInfo(PraybillItemVO.PK_PURCHASEORG_V,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004020_0",
                "04004020-0064")/* @res "采购组织版本" */));
    itemArray.add(
        new ChkItemInfo(PraybillItemVO.NNUM, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004020_0", "04004020-0065")/*
                                                                     * @res
                                                                     * "主数量"
                                                                     */));

    StringBuilder builder = new StringBuilder();
    for (PraybillItemVO itemVO : bvo) {
      for (ChkItemInfo chkItem : itemArray) {
        if (itemVO.getAttributeValue(chkItem.getItemCode()) != null) {
          continue;
        }
        builder.append(chkItem.getItemName()).append("\n");
      }
      if (builder.length() != 0) {
        String sRowNo = itemVO.getCrowno();
        String errorMsg =
            NCLangRes4VoTransl.getNCLangRes().getStrByID("4004020_0",
                "04004020-0095", null, new String[] {
                  sRowNo, builder.toString()
                })/* 第{0}行的以下字段不允许为空：\n{1} */;
        ExceptionUtils.wrappBusinessException(errorMsg);
      }
    }
  }

  private void checkHeadItem(PraybillHeaderVO hvo) {
    ChkItemInfo[] itemArray = new ChkItemInfo[10];
    itemArray[0] =
        new ChkItemInfo(PraybillHeaderVO.PK_ORG, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004020_0", "04004020-0060")/*
                                                                     * @res
                                                                     * "库存组织版本"
                                                                     */);
    itemArray[1] =
        new ChkItemInfo(PraybillHeaderVO.PK_ORG_V, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("common", "UC000-0001825")/* @res "库存组织" */);
    itemArray[2] =
        new ChkItemInfo(PraybillHeaderVO.CTRANTYPEID,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
                "UC000-0003668")/* @res "请购类型" */);
    itemArray[3] =
        new ChkItemInfo(PraybillHeaderVO.FPRAYSOURCE,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
                "UC000-0003667")/* @res "请购来源" */);
    itemArray[4] =
        new ChkItemInfo(PraybillHeaderVO.DBILLDATE, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("common", "UC000-0003665")/* @res "请购日期" */);
    itemArray[5] =
        new ChkItemInfo(PraybillHeaderVO.CCURRENCYID,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004020_0",
                "04004020-0066")/* @res "本币币种" */);
    itemArray[6] =
        new ChkItemInfo(PraybillHeaderVO.FBILLSTATUS,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
                "UC000-0000804")/* @res "单据状态" */);
    // itemArray[7] = new ChkItemInfo(PraybillHeaderVO.NTOTALASTNUM, "总数量");
    // itemArray[8] = new ChkItemInfo(PraybillHeaderVO.NTOTALTAXMNY, "本币价税合计");
    itemArray[7] =
        new ChkItemInfo(PraybillHeaderVO.NVERSION, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("common", "UC000-0002905")/* @res "版本号" */);
    itemArray[8] =
        new ChkItemInfo(PraybillHeaderVO.BSCTYPE, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004020_0", "04004020-0067")/* @res "委外" */);
    itemArray[9] =
        new ChkItemInfo(PraybillHeaderVO.BDIRECTTRANSIT,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004020_0",
                "04004020-0068")/* @res "直运" */);

    StringBuilder builder = new StringBuilder();
    for (ChkItemInfo chkItem : itemArray) {
      if (hvo.getAttributeValue(chkItem.getItemCode()) != null) {
        continue;
      }
      builder.append(chkItem.getItemName()).append("\n");
    }
    if (builder.length() != 0) {
      String errorMsg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004020_0",
              "04004020-0069")/* @res "以下字段不允许为空：\n" */
              + builder.toString();
      ExceptionUtils.wrappBusinessException(errorMsg);
    }
  }
}

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>用于检查的字段信息类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-3-25 上午09:21:54
 */
class ChkItemInfo {

  private String itemCode;

  private String itemName;

  public ChkItemInfo(String itemCode, String itemName) {
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
