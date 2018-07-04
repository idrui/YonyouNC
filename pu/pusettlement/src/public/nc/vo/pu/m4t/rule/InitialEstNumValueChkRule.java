
package nc.vo.pu.m4t.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * 
 * @description
 * 主要用于数值型属性检查：
 * 1.折本汇率不能为零
 * 2.数量与金额符号是否一致检查
 * 3.税率不能为负
 * 4.金额(原,本),价税合计(原,本)，数量不能为零
 * 5.单价不能为负
 * @scene
 * 期初暂估单保存
 * @param
 * 无
 *
 * @since 6.3
 * @version 2010-9-8 上午10:03:45
 * @author wuxla
 */


public class InitialEstNumValueChkRule implements IRule<InitialEstVO> {

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

  private ItemChkInfo[] negItems = null;

  private ItemChkInfo[] zeroItems = null;

  public InitialEstNumValueChkRule() {
    this.initNegItems();
    this.initZeroItems();
  }

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(InitialEstVO[] vos) {
    StringBuilder sb = new StringBuilder();
    for (InitialEstVO vo : vos) {
      this.checkHead(vo.getHeader(), sb);
      this.checkItems(vo.getItems(), sb);
    }

    if (sb.length() > 0) {
      ExceptionUtils.wrappBusinessException(sb.toString());
    }
  }

  private void checkHead(InitialEstHeaderVO headerVO, StringBuilder sb) {
    UFDouble nexchangerate = headerVO.getNexchangerate();
    if (null == nexchangerate) {
      sb.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4004060_0", "04004060-0186")/* @res "折本汇率为空\n" */);
    }
    else if (0 == nexchangerate.compareTo(UFDouble.ZERO_DBL)) {
      sb.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4004060_0", "04004060-0187")/* @res "折本汇率为0\n" */);
    }
  }

  private void checkItems(InitialEstItemVO[] itemVOs, StringBuilder sb) {
    for (InitialEstItemVO itemVO : itemVOs) {
      if (MathTool.isDiffSign(itemVO.getNastnum(), itemVO.getNorigtaxmny())) {
        sb.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0",
            "04004060-0231", null, new String[] {
              itemVO.getCrowno()
            })/* 第{0}行数量和金额符号不一致！\n */);
      }

      for (ItemChkInfo negItem : this.negItems) {
        if (MathTool.compareTo(
            (UFDouble) itemVO.getAttributeValue(negItem.getItemCode()),
            UFDouble.ZERO_DBL) < 0) {
          sb.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0",
              "04004060-0232", null, new String[] {
                itemVO.getCrowno(), negItem.getItemName()
              })/* 第{0}行{1}不能为负！ */);
        }
      }
      for (ItemChkInfo negItem : this.zeroItems) {
        if (MathTool.compareTo(
            (UFDouble) itemVO.getAttributeValue(negItem.getItemCode()),
            UFDouble.ZERO_DBL) == 0) {
          sb.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0",
              "04004060-0233", null, new String[] {
                itemVO.getCrowno(), negItem.getItemName()
              })/* 第{0}行{1}不能为0！ */);
        }
      }
    }
  }

  private void initNegItems() {
    this.negItems = new ItemChkInfo[9];
    this.negItems[0] =
        new ItemChkInfo(InitialEstItemVO.NTAXRATE, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("common", "UC000-0003078")/* @res "税率" */);
    this.negItems[1] =
        new ItemChkInfo(InitialEstItemVO.NASTORIGPRICE,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
                "UC000-0002306")/* @res "无税单价" */);
    this.negItems[2] =
        new ItemChkInfo(InitialEstItemVO.NASTORIGTAXPRICE,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
                "UC000-0001160")/* @res "含税单价" */);
    this.negItems[3] =
        new ItemChkInfo(InitialEstItemVO.NASTPRICE, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("common", "UC000-0002606")/*
                                                                  * @res
                                                                  * "本币无税单价"
                                                                  */);
    this.negItems[4] =
        new ItemChkInfo(InitialEstItemVO.NASTTAXPRICE,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
                "UC000-0002603")/* @res "本币含税单价" */);
    this.negItems[5] =
        new ItemChkInfo(InitialEstItemVO.NORIGPRICE,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0",
                "04004060-0188")/* @res "主无税单价 " */);
    this.negItems[6] =
        new ItemChkInfo(InitialEstItemVO.NORIGTAXPRICE,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0",
                "04004060-0189")/* @res "主含税单价" */);
    this.negItems[7] =
        new ItemChkInfo(InitialEstItemVO.NPRICE, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004060_0", "04004060-0190")/*
                                                                     * @res
                                                                     * "主本币无税价 "
                                                                     */);
    this.negItems[8] =
        new ItemChkInfo(InitialEstItemVO.NTAXPRICE, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004060_0", "04004060-0191")/*
                                                                     * @res
                                                                     * "主本币含税价"
                                                                     */);
  }

  private void initZeroItems() {
    this.zeroItems = new ItemChkInfo[6];
    this.zeroItems[0] =
        new ItemChkInfo(InitialEstItemVO.NMNY, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004060_0", "04004060-0192")/*
                                                                     * @res
                                                                     * "本币无税金额 "
                                                                     */);
    this.zeroItems[1] =
        new ItemChkInfo(InitialEstItemVO.NORIGMNY, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("common", "UC000-0002307")/* @res "无税金额" */);
    this.zeroItems[2] =
        new ItemChkInfo(InitialEstItemVO.NORIGTAXMNY,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
                "UC000-0000227")/* @res "价税合计" */);
    this.zeroItems[3] =
        new ItemChkInfo(InitialEstItemVO.NTAXMNY, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("common", "UC000-0002594")/*
                                                                  * @res
                                                                  * "本币价税合计"
                                                                  */);
    this.zeroItems[4] =
        new ItemChkInfo(InitialEstItemVO.NNUM, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004060_0", "04004060-0247")/*
                                                                     * @res
                                                                     * "主数量"
                                                                     */);
    this.zeroItems[5] =
        new ItemChkInfo(InitialEstItemVO.NASTNUM, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004060_0", "04004060-0246")/* @res "数量" */);
    //
  }
}
