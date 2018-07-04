/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-17 上午11:36:33
 */
package nc.vo.pu.m27.entity;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>自动结算之红蓝发票
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-5-17 上午11:36:33
 */
public class RBInvoiceOptionableVO extends SuperVO {

  /** 批次相同 **/
  public static final String BBATCHCODESAME = "bbatchcodesame";

  /** 采购员相同 **/
  public static final String BBUYERSAME = "bbuyersame";

  /** 部门相同 **/
  public static final String BDEPTSAME = "bdeptsame";

  /** 财务组织相同 **/
  public static final String BFINANCEORGSAME = "bfinanceorgsame";

  /** 自由辅助属性相同 **/
  public static final String BFREEITEMSAME = "bfreeitemsame";

  /** 发票类型相同 **/
  public static final String BINVOICETYPESAME = "binvoicetypesame";

  /** 物料相同 **/
  public static final String BMATERIALSAME = "bmaterialsame";

  /** 主主无税单价相同 **/
  public static final String BNORIGPRICESAME = "bnorigpricesame";

  /** 红蓝发票数量绝对值相同 **/
  public static final String BNUMABSSAME = "bnumabssame";

  /** 来源同一订单 **/
  public static final String BORDERSAME = "bordersame";

  /** 生产厂商相同 **/
  public static final String BPRODUCTORSAME = "bproductorsame";

  /** 项目相同 **/
  public static final String BPROJECTSAME = "bprojectsame";

  /** 供应商相同 **/
  public static final String BSUPPLIERSAME = "bsuppliersame";

  /** 红蓝发票主键 **/
  public static final String PK_RBINVOICE = "pk_rbinvoice";

  /** 时间戳 **/
  public static final String TS = "ts";

  private static final long serialVersionUID = 6534286011428107066L;

  /** 批次相同 **/
  public UFBoolean getBbatchcodesame() {
    return (UFBoolean) this
        .getAttributeValue(RBInvoiceOptionableVO.BBATCHCODESAME);
  }

  /** 采购员相同 **/
  public UFBoolean getBbuyersame() {
    return (UFBoolean) this.getAttributeValue(RBInvoiceOptionableVO.BBUYERSAME);
  }

  /** 部门相同 **/
  public UFBoolean getBdeptsame() {
    return (UFBoolean) this.getAttributeValue(RBInvoiceOptionableVO.BDEPTSAME);
  }

  /** 财务组织相同 **/
  public UFBoolean getBfinanceorgsame() {
    return (UFBoolean) this
        .getAttributeValue(RBInvoiceOptionableVO.BFINANCEORGSAME);
  }

  /** 自由辅助属性相同 **/
  public UFBoolean getBfreeitemsame() {
    return (UFBoolean) this
        .getAttributeValue(RBInvoiceOptionableVO.BFREEITEMSAME);
  }

  /** 发票类型相同 **/
  public UFBoolean getBinvoicetypesame() {
    return (UFBoolean) this
        .getAttributeValue(RBInvoiceOptionableVO.BINVOICETYPESAME);
  }

  /** 物料相同 **/
  public UFBoolean getBmaterialsame() {
    return (UFBoolean) this
        .getAttributeValue(RBInvoiceOptionableVO.BMATERIALSAME);
  }

  /** 主主无税单价相同 **/
  public UFBoolean getBnorigpricesame() {
    return (UFBoolean) this
        .getAttributeValue(RBInvoiceOptionableVO.BNORIGPRICESAME);
  }

  /** 红蓝发票数量绝对值相同符号 **/
  public UFBoolean getBnumabssame() {
    return (UFBoolean) this
        .getAttributeValue(RBInvoiceOptionableVO.BNUMABSSAME);
  }

  /** 来源同一订单 **/
  public UFBoolean getBordersame() {
    return (UFBoolean) this.getAttributeValue(RBInvoiceOptionableVO.BORDERSAME);
  }

  /** 生产厂商相同 **/
  public UFBoolean getBproductorsame() {
    return (UFBoolean) this
        .getAttributeValue(RBInvoiceOptionableVO.BPRODUCTORSAME);
  }

  /** 项目相同 **/
  public UFBoolean getBprojectsame() {
    return (UFBoolean) this
        .getAttributeValue(RBInvoiceOptionableVO.BPROJECTSAME);
  }

  /** 供应商相同 **/
  public UFBoolean getBsuppliersame() {
    return (UFBoolean) this
        .getAttributeValue(RBInvoiceOptionableVO.BSUPPLIERSAME);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pub.SuperVO#getMetaData()
   */
  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta =
        VOMetaFactory.getInstance().getVOMeta("pu.po_autosettle_rbinvoice");
    return meta;
  }

  /** 红蓝发票主键 **/
  public String getPk_rbinvoice() {
    return (String) this.getAttributeValue(RBInvoiceOptionableVO.PK_RBINVOICE);
  }

  /** 时间戳 **/
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(RBInvoiceOptionableVO.TS);
  }

  /** 批次相同 **/
  public void setBbatchcodesame(UFBoolean bbatchcodesame) {
    this.setAttributeValue(RBInvoiceOptionableVO.BBATCHCODESAME, bbatchcodesame);
  }

  /** 采购员相同 **/
  public void setBbuyersame(UFBoolean bbuyersame) {
    this.setAttributeValue(RBInvoiceOptionableVO.BBUYERSAME, bbuyersame);
  }

  /** 部门相同 **/
  public void setBdeptsame(UFBoolean bdeptsame) {
    this.setAttributeValue(RBInvoiceOptionableVO.BDEPTSAME, bdeptsame);
  }

  /** 财务组织相同 **/
  public void setBfinanceorgsame(UFBoolean bfinanceorgsame) {
    this.setAttributeValue(RBInvoiceOptionableVO.BFINANCEORGSAME,
        bfinanceorgsame);
  }

  /** 自由辅助属性相同 **/
  public void setBfreeitemsame(UFBoolean bfreeitemsame) {
    this.setAttributeValue(RBInvoiceOptionableVO.BFREEITEMSAME, bfreeitemsame);
  }

  /** 发票类型相同 **/
  public void setBinvoicetypesame(UFBoolean binvoicetypesame) {
    this.setAttributeValue(RBInvoiceOptionableVO.BINVOICETYPESAME,
        binvoicetypesame);
  }

  /** 物料相同 **/
  public void setBmaterialsame(UFBoolean bmaterialsame) {
    this.setAttributeValue(RBInvoiceOptionableVO.BMATERIALSAME, bmaterialsame);
  }

  /** 主主无税单价相同 **/
  public void setBnorigpricesame(UFBoolean bnorigpricesame) {
    this.setAttributeValue(RBInvoiceOptionableVO.BNORIGPRICESAME,
        bnorigpricesame);
  }

  /** 红蓝发票数量绝对值相同符号 **/
  public void setBnumabssame(UFBoolean bnumabssame) {
    this.setAttributeValue(RBInvoiceOptionableVO.BNUMABSSAME, bnumabssame);
  }

  /** 来源同一订单 **/
  public void setBordersame(UFBoolean bordersame) {
    this.setAttributeValue(RBInvoiceOptionableVO.BORDERSAME, bordersame);
  }

  /** 生产厂商相同 **/
  public void setBproductorsame(UFBoolean bproductorsame) {
    this.setAttributeValue(RBInvoiceOptionableVO.BPRODUCTORSAME, bproductorsame);
  }

  /** 项目相同 **/
  public void setBprojectsame(UFBoolean bprojectsame) {
    this.setAttributeValue(RBInvoiceOptionableVO.BPROJECTSAME, bprojectsame);
  }

  /** 供应商相同 **/
  public void setBsuppliersame(UFBoolean bsuppliersame) {
    this.setAttributeValue(RBInvoiceOptionableVO.BSUPPLIERSAME, bsuppliersame);
  }

  /** 红蓝发票主键 **/
  public void setPk_rbinvoice(String pk_rbinvoice) {
    this.setAttributeValue(RBInvoiceOptionableVO.PK_RBINVOICE, pk_rbinvoice);
  }

  /** 时间戳 **/
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(RBInvoiceOptionableVO.TS, ts);
  }
}
