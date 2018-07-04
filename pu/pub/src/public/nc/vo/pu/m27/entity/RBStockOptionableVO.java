/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-17 上午11:36:02
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
 * <li>自动结算之红蓝入库单
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-5-17 上午11:36:02
 */
public class RBStockOptionableVO extends SuperVO {

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

  /** 物料相同 **/
  public static final String BMATERIALSAME = "bmaterialsame";

  /** 红蓝入库单数量绝对值相同 **/
  public static final String BNUMABSSAME = "bnumabssame";

  /** 来源同一订单 **/
  public static final String BORDERSAME = "bordersame";

  /** 原币无税单价相同 **/
  public static final String BORIGPRICESAME = "borigpricesame";

  /** 生产厂商相同 **/
  public static final String BPRODUCTORSAME = "bproductorsame";

  /** 项目相同 **/
  public static final String BPROJECTSAME = "bprojectsame";

  /** 供应商相同 **/
  public static final String BSUPPLIERSAME = "bsuppliersame";

  /** 入库类型相同 **/
  public static final String BTRANTYPESAME = "btrantypesame";

  /** 红蓝入库单主键 **/
  public static final String PK_RBSTOCK = "pk_rbstock";

  /** 时间戳 **/
  public static final String TS = "ts";

  private static final long serialVersionUID = 937199485488568826L;

  /** 批次相同 **/
  public UFBoolean getBbatchcodesame() {
    return (UFBoolean) this
        .getAttributeValue(RBStockOptionableVO.BBATCHCODESAME);
  }

  /** 采购员相同 **/
  public UFBoolean getBbuyersame() {
    return (UFBoolean) this.getAttributeValue(RBStockOptionableVO.BBUYERSAME);
  }

  /** 部门相同 **/
  public UFBoolean getBdeptsame() {
    return (UFBoolean) this.getAttributeValue(RBStockOptionableVO.BDEPTSAME);
  }

  /** 财务组织相同 **/
  public UFBoolean getBfinanceorgsame() {
    return (UFBoolean) this
        .getAttributeValue(RBStockOptionableVO.BFINANCEORGSAME);
  }

  /** 自由辅助属性相同 **/
  public UFBoolean getBfreeitemsame() {
    return (UFBoolean) this
        .getAttributeValue(RBStockOptionableVO.BFREEITEMSAME);
  }

  /** 物料相同 **/
  public UFBoolean getBmaterialsame() {
    return (UFBoolean) this
        .getAttributeValue(RBStockOptionableVO.BMATERIALSAME);
  }

  /** 红蓝入库单数量绝对值相同 **/
  public UFBoolean getBnumabssame() {
    return (UFBoolean) this.getAttributeValue(RBStockOptionableVO.BNUMABSSAME);
  }

  /** 来源同一订单 **/
  public UFBoolean getBordersame() {
    return (UFBoolean) this.getAttributeValue(RBStockOptionableVO.BORDERSAME);
  }

  /** 原币无税单价相同 **/
  public UFBoolean getBorigpricesame() {
    return (UFBoolean) this
        .getAttributeValue(RBStockOptionableVO.BORIGPRICESAME);
  }

  /** 生产厂商相同 **/
  public UFBoolean getBproductorsame() {
    return (UFBoolean) this
        .getAttributeValue(RBStockOptionableVO.BPRODUCTORSAME);
  }

  /** 项目相同 **/
  public UFBoolean getBprojectsame() {
    return (UFBoolean) this.getAttributeValue(RBStockOptionableVO.BPROJECTSAME);
  }

  /** 供应商相同 **/
  public UFBoolean getBsuppliersame() {
    return (UFBoolean) this
        .getAttributeValue(RBStockOptionableVO.BSUPPLIERSAME);
  }

  /** 入库类型相同 **/
  public UFBoolean getBtrantypesame() {
    return (UFBoolean) this
        .getAttributeValue(RBStockOptionableVO.BTRANTYPESAME);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pub.SuperVO#getMetaData()
   */
  @Override
  public IVOMeta getMetaData() {
    IVOMeta meta =
        VOMetaFactory.getInstance().getVOMeta("pu.po_autosettle_rbstock");
    return meta;
  }

  /** 红蓝入库单主键 **/
  public String getPk_rbstock() {
    return (String) this.getAttributeValue(RBStockOptionableVO.PK_RBSTOCK);
  }

  /** 时间戳 **/
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(RBStockOptionableVO.TS);
  }

  /** 批次相同 **/
  public void setBbatchcodesame(UFBoolean bbatchcodesame) {
    this.setAttributeValue(RBStockOptionableVO.BBATCHCODESAME, bbatchcodesame);
  }

  /** 采购员相同 **/
  public void setBbuyersame(UFBoolean bbuyersame) {
    this.setAttributeValue(RBStockOptionableVO.BBUYERSAME, bbuyersame);
  }

  /** 部门相同 **/
  public void setBdeptsame(UFBoolean bdeptsame) {
    this.setAttributeValue(RBStockOptionableVO.BDEPTSAME, bdeptsame);
  }

  /** 财务组织相同 **/
  public void setBfinanceorgsame(UFBoolean bfinanceorgsame) {
    this.setAttributeValue(RBStockOptionableVO.BFINANCEORGSAME, bfinanceorgsame);
  }

  /** 自由辅助属性相同 **/
  public void setBfreeitemsame(UFBoolean bfreeitemsame) {
    this.setAttributeValue(RBStockOptionableVO.BFREEITEMSAME, bfreeitemsame);
  }

  /** 物料相同 **/
  public void setBmaterialsame(UFBoolean bmaterialsame) {
    this.setAttributeValue(RBStockOptionableVO.BMATERIALSAME, bmaterialsame);
  }

  /** 红蓝入库单数量绝对值相同 **/
  public void setBnumabssame(UFBoolean bnumabssame) {
    this.setAttributeValue(RBStockOptionableVO.BNUMABSSAME, bnumabssame);
  }

  /** 来源同一订单 **/
  public void setBordersame(UFBoolean bordersame) {
    this.setAttributeValue(RBStockOptionableVO.BORDERSAME, bordersame);
  }

  /** 原币无税单价相同 **/
  public void setBorigpricesame(UFBoolean borigpricesame) {
    this.setAttributeValue(RBStockOptionableVO.BORIGPRICESAME, borigpricesame);
  }

  /** 生产厂商相同 **/
  public void setBproductorsame(UFBoolean bproductorsame) {
    this.setAttributeValue(RBStockOptionableVO.BPRODUCTORSAME, bproductorsame);
  }

  /** 项目相同 **/
  public void setBprojectsame(UFBoolean bprojectsame) {
    this.setAttributeValue(RBStockOptionableVO.BPROJECTSAME, bprojectsame);
  }

  /** 供应商相同 **/
  public void setBsuppliersame(UFBoolean bsuppliersame) {
    this.setAttributeValue(RBStockOptionableVO.BSUPPLIERSAME, bsuppliersame);
  }

  /** 入库类型相同 **/
  public void setBtrantypesame(UFBoolean btrantypesame) {
    this.setAttributeValue(RBStockOptionableVO.BTRANTYPESAME, btrantypesame);
  }

  /** 红蓝入库单主键 **/
  public void setPk_rbstock(String pk_rbstock) {
    this.setAttributeValue(RBStockOptionableVO.PK_RBSTOCK, pk_rbstock);
  }

  /** 时间戳 **/
  public void setTs(UFDateTime ts) {
    this.setAttributeValue(RBStockOptionableVO.TS, ts);
  }

}
