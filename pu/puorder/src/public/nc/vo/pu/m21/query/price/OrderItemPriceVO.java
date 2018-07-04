package nc.vo.pu.m21.query.price;

import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>来源订单价格的VO对象
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-4-29 下午01:09:34
 */
public class OrderItemPriceVO extends AbstractDataView {
  private static final long serialVersionUID = 1L;

  private transient DataViewMeta viewMeta;

  /** 报价单位 getter 方法 */
  public String getCqtunitid() {
    return (String) this.getAttributeValue(OrderItemVO.CQTUNITID);
  }

  /**
   * 方法功能描述：获得原币币种
   * <p>
   * <b>参数说明</b>
   * 
   * @return 原币币种
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-4-29 下午01:36:33
   */
  public String getCurrencyId() {
    return (String) this.getAttributeValue(OrderHeaderVO.CORIGCURRENCYID);
  }

  /**
   * 获得物料的OID
   * 
   * @return 物料OID
   */
  public String getMaterialId() {
    return (String) this.getAttributeValue(OrderItemVO.PK_SRCMATERIAL);
  }

  @Override
  public IDataViewMeta getMetaData() {
    if (this.viewMeta == null) {
      // 表体字段
      String[] itemKeys =
          new String[] {
            OrderItemVO.PK_ORDER_B, OrderItemVO.PK_SRCMATERIAL,
            OrderItemVO.NORIGNETPRICE, OrderItemVO.NORIGTAXNETPRICE,
            OrderItemVO.NNETPRICE, OrderItemVO.NTAXNETPRICE,
            OrderItemVO.NQTORIGNETPRICE, OrderItemVO.NQTORIGPRICE,
            OrderItemVO.NQTORIGTAXNETPRC, OrderItemVO.NQTORIGTAXPRICE,
            OrderItemVO.CQTUNITID
          };
      // 表头字段
      String[] headerKeys = new String[] {
        OrderHeaderVO.CORIGCURRENCYID
      };
      this.viewMeta = new DataViewMeta(OrderItemVO.class, itemKeys);
      this.viewMeta.add(OrderHeaderVO.class, headerKeys);
      this.viewMeta.addRelation(OrderItemVO.class, OrderItemVO.PK_ORDER,
          OrderHeaderVO.class, OrderHeaderVO.PK_ORDER);
    }
    return this.viewMeta;
  }

  /** 无税净价 getter 方法 */
  public UFDouble getNqtorignetprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NQTORIGNETPRICE);
  }

  /** 无税单价 getter 方法 */
  public UFDouble getNqtorigprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NQTORIGPRICE);
  }

  /** 含税净价 getter 方法 */
  public UFDouble getNqtorigtaxnetprc() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NQTORIGTAXNETPRC);
  }

  /** 含税单价 getter 方法 */
  public UFDouble getNqtorigtaxprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NQTORIGTAXPRICE);
  }

  /**
   * 方法功能描述：获得订单明细ID
   * <p>
   * <b>参数说明</b>
   * 
   * @return 订单明细ID
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-4-29 下午01:41:56
   */
  public String getOrderItemId() {
    return (String) this.getAttributeValue(OrderItemVO.PK_ORDER_B);
  }

  /**
   * 方法功能描述：获得原币无税净价
   * <p>
   * <b>参数说明</b>
   * 
   * @return 原币无税净价
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-4-29 下午01:36:49
   */
  public UFDouble getOrigPrice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NORIGNETPRICE);
  }

  /**
   * 方法功能描述：获得原币含税净价
   * <p>
   * <b>参数说明</b>
   * 
   * @return 原币含税净价
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-4-29 下午01:37:11
   */
  public UFDouble getOrigTaxPrice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NORIGTAXNETPRICE);
  }

  /**
   * 方法功能描述：获得本币无税净价
   * <p>
   * <b>参数说明</b>
   * 
   * @return 本币无税净价
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-4-29 下午01:37:34
   */
  public UFDouble getPrice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NNETPRICE);
  }

  /**
   * 方法功能描述：获得本币含税净价
   * <p>
   * <b>参数说明</b>
   * 
   * @return 本币含税净价
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-4-29 下午01:37:55
   */
  public UFDouble getTaxPrice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NTAXNETPRICE);
  }

  public void setCurrencyId(String currencyId) {
    this.setAttributeValue(OrderHeaderVO.CORIGCURRENCYID, currencyId);
  }

  public void setMaterialId(String materialId) {
    this.setAttributeValue(OrderItemVO.PK_SRCMATERIAL, materialId);
  }

  public void setOrderItemId(String orderItemId) {
    this.setAttributeValue(OrderItemVO.PK_ORDER_B, orderItemId);
  }

  public void setOrigPrice(UFDouble origPrice) {
    this.setAttributeValue(OrderItemVO.NORIGNETPRICE, origPrice);
  }

  public void setOrigTaxPrice(UFDouble origTaxPrice) {
    this.setAttributeValue(OrderItemVO.NORIGTAXNETPRICE, origTaxPrice);
  }

  public void setPrice(UFDouble price) {
    this.setAttributeValue(OrderItemVO.NNETPRICE, price);
  }

  public void setTaxPrice(UFDouble taxPrice) {
    this.setAttributeValue(OrderItemVO.NTAXNETPRICE, taxPrice);
  }

}
