/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-7 上午11:45:20
 */
package nc.vo.pu.m21.entity;

import java.io.Serializable;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>电子商务查询信息VO
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-6-7 上午11:45:20
 */
public class ECOrderQueryVO implements Serializable {

  private static final long serialVersionUID = 1933986447341561236L;

  /** 到货关闭 **/
  private UFBoolean barriveclose;

  /** 开票关闭 **/
  private UFBoolean binvoiceclose;

  /** 付款关闭 **/
  private UFBoolean bpayclose;

  /** 入库关闭 **/
  private UFBoolean bstockclose;

  /** 累计到货数量 **/
  private UFDouble naccumarrvnum;

  /** 累计发票数量 **/
  private UFDouble naccuminvoicenum;

  /** 累计付款金额 **/
  private UFDouble naccumpayorgmny;

  /** 累计入库数量 **/
  private UFDouble naccumstorenum;

  /** 累计退货数量 **/
  private UFDouble nbackarrvnum;

  /** 累计退库数量 **/
  private UFDouble nbackstorenum;

  /** 主数量 **/
  private UFDouble nnum;

  /** 到货关闭 **/
  public UFBoolean getBarriveclose() {
    return this.barriveclose;
  }

  /** 开票关闭 **/
  public UFBoolean getBinvoiceclose() {
    return this.binvoiceclose;
  }

  /** 付款关闭 **/
  public UFBoolean getBpayclose() {
    return this.bpayclose;
  }

  /** 入库关闭 **/
  public UFBoolean getBstockclose() {
    return this.bstockclose;
  }

  /** 累计到货数量 **/
  public UFDouble getNaccumarrvnum() {
    return this.naccumarrvnum;
  }

  /** 累计发票数量 **/
  public UFDouble getNaccuminvoicenum() {
    return this.naccuminvoicenum;
  }

  /** 累计付款金额 **/
  public UFDouble getNaccumpayorgmny() {
    return this.naccumpayorgmny;
  }

  /** 累计入库数量 **/
  public UFDouble getNaccumstorenum() {
    return this.naccumstorenum;
  }

  /** 累计退货数量 **/
  public UFDouble getNbackarrvnum() {
    return this.nbackarrvnum;
  }

  /** 累计退库数量 **/
  public UFDouble getNbackstorenum() {
    return this.nbackstorenum;
  }

  /** 主数量 **/
  public UFDouble getNnum() {
    return this.nnum;
  }

  /** 到货关闭 **/
  public void setBarriveclose(UFBoolean barriveclose) {
    this.barriveclose = barriveclose;
  }

  /** 开票关闭 **/
  public void setBinvoiceclose(UFBoolean binvoiceclose) {
    this.binvoiceclose = binvoiceclose;
  }

  /** 付款关闭 **/
  public void setBpayclose(UFBoolean bpayclose) {
    this.bpayclose = bpayclose;
  }

  /** 入库关闭 **/
  public void setBstockclose(UFBoolean bstockclose) {
    this.bstockclose = bstockclose;
  }

  /** 累计到货数量 **/
  public void setNaccumarrvnum(UFDouble naccumarrvnum) {
    this.naccumarrvnum = naccumarrvnum;
  }

  /** 累计发票数量 **/
  public void setNaccuminvoicenum(UFDouble naccuminvoicenum) {
    this.naccuminvoicenum = naccuminvoicenum;
  }

  /** 累计付款金额 **/
  public void setNaccumpayorgmny(UFDouble naccumpayorgmny) {
    this.naccumpayorgmny = naccumpayorgmny;
  }

  /** 累计入库数量 **/
  public void setNaccumstorenum(UFDouble naccumstorenum) {
    this.naccumstorenum = naccumstorenum;
  }

  /** 累计退货数量 **/
  public void setNbackarrvnum(UFDouble nbackarrvnum) {
    this.nbackarrvnum = nbackarrvnum;
  }

  /** 累计退库数量 **/
  public void setNbackstorenum(UFDouble nbackstorenum) {
    this.nbackstorenum = nbackstorenum;
  }

  /** 主数量 **/
  public void setNnum(UFDouble nnum) {
    this.nnum = nnum;
  }
}
