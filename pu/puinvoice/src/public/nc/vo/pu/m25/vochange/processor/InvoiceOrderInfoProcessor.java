package nc.vo.pu.m25.vochange.processor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.m21.pub.IOrderPubQuery;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 转单来源订单信息处理器
 * 
 * @since 6.0
 * @version 2011-7-2 下午01:44:39
 * @author 田锋涛
 */

public class InvoiceOrderInfoProcessor {

  /**
   * 根据源头采购订单给发票赋默认值
   * 
   * @param vos
   */
  public void fillDataByOrder(InvoiceVO[] vos) {
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
   * 补充数据
   * 
   * @param vos
   * @param viewMap
   */
  private void fillData(InvoiceVO[] vos, Map<String, OrderViewVO> viewMap) {
    for (InvoiceVO vo : vos) {
      boolean filledHead = false;
      for (InvoiceItemVO item : vo.getChildrenVO()) {
        if (viewMap.containsKey(item.getPk_order_b())) {
          OrderViewVO orderView = viewMap.get(item.getPk_order_b());
          this.fillItemData(item, orderView);
          if (!filledHead) {
            this.fillHeadData(vo.getParentVO(), orderView);
            filledHead = true;
          }
        }
      }
    }
  }

  /**
   * 补充表体数据
   * 
   * @param item
   * @param orderView
   */
  private void fillItemData(InvoiceItemVO item, OrderViewVO orderView) {
    String Pk_apliabcenter =
        (String) orderView.getAttributeValue(OrderItemVO.PK_APLIABCENTER);
    String ctCode =
        (String) orderView.getAttributeValue(OrderItemVO.VCONTRACTCODE);
    // add by liangchen1 NC631需求，合同主键和合同行主键也从订单对过来
    String ctId = (String) orderView.getAttributeValue(OrderItemVO.CCONTRACTID);
    String ctRowId =
        (String) orderView.getAttributeValue(OrderItemVO.CCONTRACTROWID);
    // 利润中心
    if (StringUtils.isNotBlank(Pk_apliabcenter)) {
      item.setPk_apliabcenter(Pk_apliabcenter);
      item.setPk_apliabcenter_v((String) orderView
          .getAttributeValue(OrderItemVO.PK_APLIABCENTER_V));// 取最新版本？？
    }
    // 合同号
    if (StringUtils.isNotBlank(ctCode)) {
      item.setVctcode(ctCode);
      // add by liangchen1 NC631需求，合同主键和合同行主键也从订单对过来
      item.setCcontractid(ctId);
      item.setCcontractrowid(ctRowId);
    }
  }

  /**
   * 发票来源的订单bid
   * 
   * @param vos
   * @return
   */
  private String[] getOrderBids(InvoiceVO[] vos) {
    Set<String> orderBIdSet = new HashSet<String>();

    for (InvoiceVO vo : vos) {
      // 如果源头是采购订单，判断表体第一行就行,来源采购订单时vo对照已经处理，这里不再处理
      for (InvoiceItemVO item : vo.getChildrenVO()) {
        if (StringUtils.isNotEmpty(item.getPk_order())
            && !POBillType.Order.getCode().equals(item.getCsourcetypecode())) {
          orderBIdSet.add(item.getPk_order_b());

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

  /**
   * 补充表头数据
   * 
   * @param header
   * @param orderView
   */
  protected void fillHeadData(InvoiceHeaderVO header, OrderViewVO orderView) {
    // 开票方
    String pk_invsupplier =
        (String) orderView.getAttributeValue(OrderHeaderVO.PK_INVCSUPLLIER);
    // 付款协议
    String pk_payterm =
        (String) orderView.getAttributeValue(OrderHeaderVO.PK_PAYTERM);
    // 散户
    String pk_freecust =
        (String) orderView.getAttributeValue(OrderHeaderVO.PK_FREECUST);
    // 银行账户
    String pk_bankdoc =
        (String) orderView.getAttributeValue(OrderHeaderVO.PK_BANKDOC);
    // 贸易术语
    String ctradewordid =
        (String) orderView.getAttributeValue(OrderHeaderVO.CTRADEWORDID);
    // 结算方式
    String pk_balatype =
        (String) orderView.getAttributeValue(OrderHeaderVO.PK_BALATYPE);

    if (StringUtils.isNotBlank(pk_invsupplier)) {
      header.setPk_supplier(pk_invsupplier);// 供应商
      header.setPk_paytosupplier(pk_invsupplier);// 付款单位
    }
    if (StringUtils.isNotBlank(pk_payterm)) {
      header.setPk_payterm(pk_payterm);// 付款协议
    }
    if (StringUtils.isNotBlank(pk_bankdoc)) {
      header.setPk_bankaccbas(pk_bankdoc);// 银行账户
    }
    if (StringUtils.isNotBlank(pk_freecust)) {
      header.setPk_freecust(pk_freecust);// 散户
      header.setPk_bankaccbas(pk_bankdoc);// 银行账户
    }
    if (StringUtils.isNotBlank(ctradewordid)) {
      header.setCtradewordid(ctradewordid);// 贸易术语
    }
    if (StringUtils.isNotBlank(pk_balatype)) {
      header.setPk_balatype(pk_balatype);// 结算方式
    }

  }

}
