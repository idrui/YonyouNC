package nc.vo.pu.m23.rule;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.pub.rule.RowNoCheckRule;
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
 * @author hanbin
 * @time 2010-3-19 下午05:01:13
 */
public class ChkEmptyWhenSave {

  public void chkEmpty(ArriveVO vo) {
    // 检查表头
    this.checkHeadItem(vo.getHVO());
    // 检查表体行号
    new RowNoCheckRule().checkRowNo(new ArriveVO[] {
      vo
    }, ArriveItemVO.CROWNO);
    // 检查表体
    this.checkBodyItem(vo.getBVO());
  }

  private void checkBodyItem(ArriveItemVO[] bvo) {
    ChkItemInfo[] itemArray = new ChkItemInfo[5];
    itemArray[0] =
        new ChkItemInfo(ArriveItemVO.CROWNO, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("common", "UC000-0003389")/* @res "行号" */);
    itemArray[1] =
        new ChkItemInfo(ArriveItemVO.PK_MATERIAL, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("common", "UC000-0002911")/* @res "物料编码" */);
    itemArray[2] =
        new ChkItemInfo(ArriveItemVO.CASTUNITID, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004040_0", "04004040-0147")/* @res "单位" */);
    itemArray[3] =
        new ChkItemInfo(ArriveItemVO.NASTNUM, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("common", "UC000-0002282")/* @res "数量" */);
    itemArray[4] =
        new ChkItemInfo(ArriveItemVO.NPLANASTNUM, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("common", "UC000-0001855")/* @res "应到数量" */);

    StringBuilder builder = new StringBuilder();
    for (ArriveItemVO itemVO : bvo) {
      for (ChkItemInfo chkItem : itemArray) {
        if (itemVO.getAttributeValue(chkItem.getItemCode()) != null) {
          continue;
        }
        builder.append(chkItem.getItemName()).append("\n");
      }
      if (builder.length() != 0) {
        String msg =
            NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
                "04004040-0174", null, new String[] {
                  itemVO.getCrowno(), builder.toString()
                })/* 第{0}行的以下字段不允许为空：\n{1} */;
        ExceptionUtils.wrappBusinessException(msg);
      }
    }
  }

  private void checkHeadItem(ArriveHeaderVO hvo) {
    ChkItemInfo[] itemArray = new ChkItemInfo[5];
    itemArray[0] =
        new ChkItemInfo(ArriveHeaderVO.PK_ORG, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("common", "UC000-0001825")/* @res "库存组织" */);
    itemArray[1] =
        new ChkItemInfo(ArriveHeaderVO.PK_PURCHASEORG,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
                "UC000-0004091")/* @res "采购组织" */);
    itemArray[2] =
        new ChkItemInfo(ArriveHeaderVO.VTRANTYPECODE,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
                "04004040-0148")/* @res "到货类型" */);
    itemArray[3] =
        new ChkItemInfo(ArriveHeaderVO.DBILLDATE, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("common", "UC000-0000650")/* @res "到货日期" */);
    itemArray[4] =
        new ChkItemInfo(ArriveHeaderVO.PK_DEPT, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("common", "UC000-0004099")/* @res "采购部门" */);

    StringBuilder builder = new StringBuilder();
    for (ChkItemInfo chkItem : itemArray) {
      if (hvo.getAttributeValue(chkItem.getItemCode()) != null) {
        continue;
      }
      builder.append(chkItem.getItemName()).append("\n");
    }
    if (builder.length() != 0) {
      String errorMsg =
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
              "04004040-0149")/* @res "以下字段不允许为空：\n" */
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
 * @author hanbin
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
