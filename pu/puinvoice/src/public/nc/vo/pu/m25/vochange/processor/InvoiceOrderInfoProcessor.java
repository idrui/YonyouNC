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
 * ת����Դ������Ϣ������
 * 
 * @since 6.0
 * @version 2011-7-2 ����01:44:39
 * @author �����
 */

public class InvoiceOrderInfoProcessor {

  /**
   * ����Դͷ�ɹ���������Ʊ��Ĭ��ֵ
   * 
   * @param vos
   */
  public void fillDataByOrder(InvoiceVO[] vos) {
    // �õ�����bid
    String[] orderBids = this.getOrderBids(vos);
    if (ArrayUtils.isEmpty(orderBids)) {
      return;
    }
    Map<String, OrderViewVO> viewMap = this.queryOrderByBid(orderBids);
    // �������
    this.fillData(vos, viewMap);

  }

  /**
   * ��������
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
   * �����������
   * 
   * @param item
   * @param orderView
   */
  private void fillItemData(InvoiceItemVO item, OrderViewVO orderView) {
    String Pk_apliabcenter =
        (String) orderView.getAttributeValue(OrderItemVO.PK_APLIABCENTER);
    String ctCode =
        (String) orderView.getAttributeValue(OrderItemVO.VCONTRACTCODE);
    // add by liangchen1 NC631���󣬺�ͬ�����ͺ�ͬ������Ҳ�Ӷ����Թ���
    String ctId = (String) orderView.getAttributeValue(OrderItemVO.CCONTRACTID);
    String ctRowId =
        (String) orderView.getAttributeValue(OrderItemVO.CCONTRACTROWID);
    // ��������
    if (StringUtils.isNotBlank(Pk_apliabcenter)) {
      item.setPk_apliabcenter(Pk_apliabcenter);
      item.setPk_apliabcenter_v((String) orderView
          .getAttributeValue(OrderItemVO.PK_APLIABCENTER_V));// ȡ���°汾����
    }
    // ��ͬ��
    if (StringUtils.isNotBlank(ctCode)) {
      item.setVctcode(ctCode);
      // add by liangchen1 NC631���󣬺�ͬ�����ͺ�ͬ������Ҳ�Ӷ����Թ���
      item.setCcontractid(ctId);
      item.setCcontractrowid(ctRowId);
    }
  }

  /**
   * ��Ʊ��Դ�Ķ���bid
   * 
   * @param vos
   * @return
   */
  private String[] getOrderBids(InvoiceVO[] vos) {
    Set<String> orderBIdSet = new HashSet<String>();

    for (InvoiceVO vo : vos) {
      // ���Դͷ�ǲɹ��������жϱ����һ�о���,��Դ�ɹ�����ʱvo�����Ѿ��������ﲻ�ٴ���
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
   * ���ݶ�������id��ѯ��ͼvo
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
   * �����ͷ����
   * 
   * @param header
   * @param orderView
   */
  protected void fillHeadData(InvoiceHeaderVO header, OrderViewVO orderView) {
    // ��Ʊ��
    String pk_invsupplier =
        (String) orderView.getAttributeValue(OrderHeaderVO.PK_INVCSUPLLIER);
    // ����Э��
    String pk_payterm =
        (String) orderView.getAttributeValue(OrderHeaderVO.PK_PAYTERM);
    // ɢ��
    String pk_freecust =
        (String) orderView.getAttributeValue(OrderHeaderVO.PK_FREECUST);
    // �����˻�
    String pk_bankdoc =
        (String) orderView.getAttributeValue(OrderHeaderVO.PK_BANKDOC);
    // ó������
    String ctradewordid =
        (String) orderView.getAttributeValue(OrderHeaderVO.CTRADEWORDID);
    // ���㷽ʽ
    String pk_balatype =
        (String) orderView.getAttributeValue(OrderHeaderVO.PK_BALATYPE);

    if (StringUtils.isNotBlank(pk_invsupplier)) {
      header.setPk_supplier(pk_invsupplier);// ��Ӧ��
      header.setPk_paytosupplier(pk_invsupplier);// ���λ
    }
    if (StringUtils.isNotBlank(pk_payterm)) {
      header.setPk_payterm(pk_payterm);// ����Э��
    }
    if (StringUtils.isNotBlank(pk_bankdoc)) {
      header.setPk_bankaccbas(pk_bankdoc);// �����˻�
    }
    if (StringUtils.isNotBlank(pk_freecust)) {
      header.setPk_freecust(pk_freecust);// ɢ��
      header.setPk_bankaccbas(pk_bankdoc);// �����˻�
    }
    if (StringUtils.isNotBlank(ctradewordid)) {
      header.setCtradewordid(ctradewordid);// ó������
    }
    if (StringUtils.isNotBlank(pk_balatype)) {
      header.setPk_balatype(pk_balatype);// ���㷽ʽ
    }

  }

}
