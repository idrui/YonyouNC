/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-11 上午10:51:20
 */
package nc.vo.pu.m21.entity;

import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-11 上午10:51:20
 */
public class OrderCloseVO extends AbstractDataView {

  /**
   * 
   */
  private static final long serialVersionUID = 3288216929227081880L;

  /** 到货回写前的累积到货数量 */
  private UFDouble naccumarrvnum_pre;

  /** 发票回写前的累积开票金额 */
  private UFDouble naccuminvoicemny_pre;

  /** 发票回写前的累积开票数量 */
  private UFDouble naccuminvoicenum_pre;

  /** 入库回写前的累积入库数量 */
  private UFDouble naccumstorenum_pre;

  /** 由关闭VO得到订单VO **/
  public static OrderVO[] getOrderVO(OrderCloseVO[] vos) {
    return OrderViewVO.getOrderVO(vos);
  }

  /** 到货关闭 **/
  public UFBoolean getBarriveclose() {
    return (UFBoolean) this.getAttributeValue(OrderItemVO.BARRIVECLOSE);
  }

  /** 最终关闭 **/
  public UFBoolean getBfinalclose() {
    return (UFBoolean) this.getAttributeValue(OrderHeaderVO.BFINALCLOSE);
  }

  /** 开票关闭 **/
  public UFBoolean getBinvoiceclose() {
    return (UFBoolean) this.getAttributeValue(OrderItemVO.BINVOICECLOSE);
  }

  /** 付款关闭 **/
  public UFBoolean getBpayclose() {
    return (UFBoolean) this.getAttributeValue(OrderItemVO.BPAYCLOSE);
  }

  /** 入库关闭 **/
  public UFBoolean getBstockclose() {
    return (UFBoolean) this.getAttributeValue(OrderItemVO.BSTOCKCLOSE);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pubapp.pattern.model.entity.view.AbstractDataView#getMetaData()
   */
  @Override
  public IDataViewMeta getMetaData() {
    return DataViewMetaFactory.getInstance().getBillViewMeta(OrderVO.class);
  }

  /** 累计已核销本币开票金额 getter 方法 */
  public UFDouble getNacccancelinvmny() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NACCCANCELINVMNY);
  }

  /** 累计到货主数量 getter 方法 */
  public UFDouble getNaccumarrvnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NACCUMARRVNUM);
  }

  public UFDouble getNaccumarrvnum_pre() {
    return this.naccumarrvnum_pre;
  }

  /** 累计本币开票金额 getter 方法 */
  public UFDouble getNaccuminvoicemny() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NACCUMINVOICEMNY);
  }

  public UFDouble getNaccuminvoicemny_pre() {
    return this.naccuminvoicemny_pre;
  }

  /** 累计开票主数量 getter 方法 */
  public UFDouble getNaccuminvoicenum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NACCUMINVOICENUM);
  }

  public UFDouble getNaccuminvoicenum_pre() {
    return this.naccuminvoicenum_pre;
  }

  /** 累计入库主数量 getter 方法 */
  public UFDouble getNaccumstorenum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NACCUMSTORENUM);
  }

  public UFDouble getNaccumstorenum_pre() {
    return this.naccumstorenum_pre;
  }

  /** 可到货数量 getter 方法 */
  public UFDouble getNcanarrivenum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NCANARRIVENUM);
  }

  /** 可入库数量 getter 方法 */
  public UFDouble getNcaninnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NCANINNUM);
  }

  /** 可开票数量 getter 方法 */
  public UFDouble getNcaninvoicenum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NCANINVOICENUM);
  }

  /** 未付款金额 getter 方法 */
  public UFDouble getNnopayorgmny() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NNOPAYORGMNY);
  }

  /** 主数量 getter 方法 */
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NNUM);
  }

  /** 价税合计 getter 方法 */
  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NORIGTAXMNY);
  }

  /** 本币价税合计 getter 方法 */
  public UFDouble getNtaxmny() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NTAXMNY);
  }

  /** 采购订单 getter 方法 */
  public String getPk_order() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_ORDER);
  }

  /** 采购订单明细 getter 方法 */
  public String getPk_order_b() {
    return (String) this.getAttributeValue(OrderItemVO.PK_ORDER_B);
  }

  /** 采购组织 **/
  public String getPk_org() {
    return (String) this.getAttributeValue(OrderItemVO.PK_ORG);
  }

  /** 获得订单单据号 */
  public String getVbillcode() {
    return (String) this.getAttributeValue(OrderHeaderVO.VBILLCODE);
  }

  /** 到货关闭 **/
  public void setBarriveclose(UFBoolean barriveclose) {
    this.setAttributeValue(OrderItemVO.BARRIVECLOSE, barriveclose);
  }

  /** 最终关闭 **/
  public void setBfinalclose(UFBoolean bfinalclose) {
    this.setAttributeValue(OrderHeaderVO.BFINALCLOSE, bfinalclose);
  }

  /** 开票关闭 **/
  public void setBinvoiceclose(UFBoolean binvoiceclose) {
    this.setAttributeValue(OrderItemVO.BINVOICECLOSE, binvoiceclose);
  }

  /** 付款关闭 **/
  public void setBpayclose(UFBoolean bpayclose) {
    this.setAttributeValue(OrderItemVO.BPAYCLOSE, bpayclose);
  }

  /** 入库关闭 **/
  public void setBstockclose(UFBoolean bstockclose) {
    this.setAttributeValue(OrderItemVO.BSTOCKCLOSE, bstockclose);
  }

  /** 累计到货主数量 setter 方法 */
  public void setNaccumarrvnum(UFDouble naccumarrvnum) {
    this.setAttributeValue(OrderItemVO.NACCUMARRVNUM, naccumarrvnum);
  }

  public void setNaccumarrvnum_pre(UFDouble naccumarrvnum_pre) {
    this.naccumarrvnum_pre = naccumarrvnum_pre;
  }

  public void setNaccuminvoicemny_pre(UFDouble naccuminvoicemny_pre) {
    this.naccuminvoicemny_pre = naccuminvoicemny_pre;
  }

  /** 累计开票主数量 setter 方法 */
  public void setNaccuminvoicenum(UFDouble naccuminvoicenum) {
    this.setAttributeValue(OrderItemVO.NACCUMINVOICENUM, naccuminvoicenum);
  }

  public void setNaccuminvoicenum_pre(UFDouble naccuminvoicenum_pre) {
    this.naccuminvoicenum_pre = naccuminvoicenum_pre;
  }

  /** 累计入库主数量 setter 方法 */
  public void setNaccumstorenum(UFDouble naccumstorenum) {
    this.setAttributeValue(OrderItemVO.NACCUMSTORENUM, naccumstorenum);
  }

  public void setNaccumstorenum_pre(UFDouble naccumstorenum_pre) {
    this.naccumstorenum_pre = naccumstorenum_pre;
  }

  /** 可到货数量 setter 方法 */
  public void setNcanarrivenum(UFDouble ncanarrivenum) {
    this.setAttributeValue(OrderItemVO.NCANARRIVENUM, ncanarrivenum);
  }

  /** 可入库数量 setter 方法 */
  public void setNcaninnum(UFDouble ncaninnum) {
    this.setAttributeValue(OrderItemVO.NCANINNUM, ncaninnum);
  }

  /** 可开票数量 setter 方法 */
  public void setNcaninvoicenum(UFDouble ncaninvoicenum) {
    this.setAttributeValue(OrderItemVO.NCANINVOICENUM, ncaninvoicenum);
  }

  /** 未付款金额 setter 方法 */
  public void setNnopayorgmny(UFDouble nnopayorgmny) {
    this.setAttributeValue(OrderItemVO.NNOPAYORGMNY, nnopayorgmny);
  }

  /** 采购组织 **/
  public void setPk_org(String pk_org) {
    this.setAttributeValue(OrderItemVO.PK_ORG, pk_org);
  }

  public void setVbillcode(String vbillcode) {
    this.setAttributeValue(OrderHeaderVO.VBILLCODE, vbillcode);
  }

}
