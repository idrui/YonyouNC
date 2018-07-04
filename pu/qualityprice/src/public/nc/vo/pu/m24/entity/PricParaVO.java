package nc.vo.pu.m24.entity;

import java.io.Serializable;

import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>价格结算单提供价格接口类 为发票和暂估提供，用于优质优价取价
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-8-13 下午01:42:47
 */
public class PricParaVO implements Serializable {

  private static final long serialVersionUID = 4169034276231312609L;

  /**
   * 入库单行ID
   */
  private String cgeneralbid = null;

  /**
   * 价格结算单本币无税单价
   */
  private UFDouble nprice = null;

  /**
   * 价格结算单本币含税单价
   */
  private UFDouble ntaxprice = null;

  /**
   * 获取入库单行ID
   */
  public String getCgeneralbid() {
    return this.cgeneralbid;
  }

  /**
   * 获取价格结算单本币无税单价
   */
  public UFDouble getNprice() {
    return this.nprice;
  }

  /**
   * 获取价格结算单本币含税单价
   */
  public UFDouble getNtaxprice() {
    return this.ntaxprice;
  }

  /**
   * 设置入库单行ID
   */
  public void setCgeneralbid(String newVal) {
    this.cgeneralbid = newVal;
  }

  /**
   * 设置价格结算单本币无税单价
   */
  public void setNprice(UFDouble newVal) {
    this.nprice = newVal;
  }

  /**
   * 设置价格结算单本币含税单价
   */
  public void setNtaxprice(UFDouble newVal) {
    this.ntaxprice = newVal;
  }
}
