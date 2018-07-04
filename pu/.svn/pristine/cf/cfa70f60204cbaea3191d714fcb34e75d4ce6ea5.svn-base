package nc.vo.pu.est.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.m21.pub.IOrderPubQuery;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pu.pub.util.ObjectUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 暂估\确认应付、成本时数据处理工具类
 * 
 * @since 6.0
 * @version 2011-9-28 上午11:00:18
 * @author 田锋涛
 */

public class ToApIaDataProcessor {

  private static final String PK_ORDER_B = OrderItemVO.PK_ORDER_B;

  private static final String PK_SUPPLIER = OrderHeaderVO.PK_SUPPLIER;

  /** 还原供应商旧值 */
  private Map<String, String> idSupplierMap = new HashMap<String, String>();

  /**
   * 根据订单赋默认值
   * 
   * @param vos
   */
  public void fillDataByOrder(AbstractBill[] vos) {
    // 得到订单bid
    String[] orderBids = this.getOrderBids(vos);
    if (ArrayUtils.isEmpty(orderBids)) {
      return;
    }
    Map<String, OrderViewVO> viewMap = this.queryOrderByBid(orderBids);
    // 填充数据
    this.fillData(vos, viewMap);

  }

  /**
   * 还原原来的值，适合fillDataByOrder()之后调用
   * 
   * @param vos
   */
  public void resetData(AbstractBill[] vos) {
    try {
      for (AbstractBill vo : vos) {
        CircularlyAccessibleValueObject header = vo.getParentVO();
        String hid = header.getPrimaryKey();
        // 还原表头供应商
        if (this.idSupplierMap.containsKey(hid)) {
          header.setAttributeValue(ToApIaDataProcessor.PK_SUPPLIER,
              this.idSupplierMap.get(hid));
        }
        for (CircularlyAccessibleValueObject item : vo.getChildrenVO()) {
          // 还原表体供应商
          String bid = item.getPrimaryKey();
          if (this.idSupplierMap.containsKey(bid)) {
            item.setAttributeValue(ToApIaDataProcessor.PK_SUPPLIER,
                this.idSupplierMap.get(bid));
          }
        }
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 补充数据
   * 
   * @param vos
   * @param viewMap
   */
  private void fillData(AbstractBill[] vos, Map<String, OrderViewVO> viewMap) {
    for (AbstractBill vo : vos) {
      boolean filledHead = false;
      for (CircularlyAccessibleValueObject item : vo.getChildrenVO()) {
        String pk_order_b =
            (String) item.getAttributeValue(ToApIaDataProcessor.PK_ORDER_B);
        if (StringUtils.isEmpty(pk_order_b)) {
          continue;
        }
        if (viewMap.containsKey(pk_order_b)) {
          OrderViewVO orderView = viewMap.get(pk_order_b);
          this.fillItemData(item, orderView);
          if (!filledHead) {
            this.fillHeadData(vo.getParentVO(), orderView);
            filledHead = true;
          }
        }
      }
    }
  }

  private void fillHeadData(CircularlyAccessibleValueObject header,
      OrderViewVO orderView) {
    // 处理表头，目前用于暂估的表头供应商
    this.fillItemData(header, orderView);
  }

  /**
   * 补充表体数据
   * 
   * @param item
   * @param orderView
   */
  private void fillItemData(CircularlyAccessibleValueObject item,
      OrderViewVO orderView) {
    String Pk_invcsupplier =
        (String) orderView.getAttributeValue(OrderHeaderVO.PK_INVCSUPLLIER);
    // 供应商取订单开票供应商
    String pk_supplier =
        (String) item.getAttributeValue(ToApIaDataProcessor.PK_SUPPLIER);
    if (StringUtils.isNotBlank(Pk_invcsupplier)
        && StringUtils.isNotBlank(pk_supplier)
        && !Pk_invcsupplier.equals(pk_supplier)) {
      try {
        // 记录旧供应商值
        this.idSupplierMap.put(item.getPrimaryKey(), pk_supplier);
        // 赋值开票供应商
        item.setAttributeValue(ToApIaDataProcessor.PK_SUPPLIER, Pk_invcsupplier);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }
  }

  /**
   * 采购入来源的订单bid
   * 
   * @param vos
   * @return
   */
  private String[] getOrderBids(AbstractBill[] vos) {
    Set<String> orderBIdSet = new HashSet<String>();

    for (AbstractBill vo : vos) {
      for (CircularlyAccessibleValueObject item : vo.getChildrenVO()) {
        //
        Object pk_order_b =
            item.getAttributeValue(ToApIaDataProcessor.PK_ORDER_B);
        if (!ObjectUtil.isEmptyWithTrim(pk_order_b)) {
          orderBIdSet.add(pk_order_b.toString());
        }
      }
    }
    if (orderBIdSet.size() == 0) {
      return null;
    }
    return orderBIdSet.toArray(new String[orderBIdSet.size()]);
  }

  /**
   * 根据订单表体id查询视图vo
   * 
   * @param orderBids
   * @return
   */
  private Map<String, OrderViewVO> queryOrderByBid(String[] orderBids) {
    try {
      OrderViewVO[] views =
          NCLocator.getInstance().lookup(IOrderPubQuery.class)
              .queryOrderView(orderBids);
      Map<String, OrderViewVO> viewMap = new HashMap<String, OrderViewVO>();
      for (OrderViewVO view : views) {
        viewMap.put(view.getPk_order_b(), view);
      }
      return viewMap;
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    return null;
  }

}
