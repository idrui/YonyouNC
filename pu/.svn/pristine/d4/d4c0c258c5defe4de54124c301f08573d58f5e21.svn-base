package nc.pubimpl.pu.m25.arap.f3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.bs.arap.util.IArapBillTypeCons;
import nc.bs.pu.m25.query.InvoiceQueryByOrderCodeBP;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.itf.pu.reference.ic.M45PUServices;
import nc.itf.scmpub.reference.arap.ArapServicesForPUUtil;
import nc.pubitf.arap.pub.IArap4VerifyQryBill;
import nc.vo.arap.payable.AggPayableBillVO;
import nc.vo.arap.payable.PayableBillItemVO;
import nc.vo.arap.payable.PayableBillVO;
import nc.vo.arap.pfflow.ArapBillMapVO;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.0
 * @version 2011-4-7 上午08:19:16
 * @author wuxla
 */

public class InvoiceQueryForVerifyImpl implements IArap4VerifyQryBill {

  @Override
  public Map<ArapBillMapVO, Collection<ArapBillMapVO>> queryArapBillmap(
      ArapBillMapVO[] mapvo) throws BusinessException {
    try {
      if (ArrayUtils.isEmpty(mapvo)) {
        return null;
      }
      // 订单主键
      Set<String> orderIdSet = new HashSet<String>();
      for (ArapBillMapVO vo : mapvo) {
        orderIdSet.add(vo.getS_billid());
      }
      if (0 == orderIdSet.size()) {
        return null;
      }
      // 根据订单主键获取订单表体主键, bid为key
      Map<String, String> idMap = this.getBidsByIds(orderIdSet);
      String[] orderBids = idMap.keySet().toArray(new String[0]);
      MapList<String, String> mapList = new MapList<String, String>();
      MapList<String, String> invoiceMapList =
          new InvoiceQueryByOrderCodeBP().queryInvoiceBidByOrderBid(orderBids);
      if (invoiceMapList != null) {
        for (Entry<String, List<String>> entry : invoiceMapList.entrySet()) {
          mapList.putAll(idMap.get(entry.getKey()), entry.getValue());
        }
      }
      MapList<String, String> icMapList =
          M45PUServices.queryBidByOrderBid(orderBids);
      if (icMapList != null) {
        for (Entry<String, List<String>> entry : icMapList.entrySet()) {
          mapList.putAll(idMap.get(entry.getKey()), entry.getValue());
        }
      }
      if (0 == mapList.size()) {
        return null;
      }

      List<String> invoiceBidList = new ArrayList<String>();
      for (Entry<String, List<String>> entry : mapList.entrySet()) {
        List<String> value = entry.getValue();
        invoiceBidList.addAll(value);
      }
      String[] bids = invoiceBidList.toArray(new String[invoiceBidList.size()]);
      AggPayableBillVO[] payableBills =
          ArapServicesForPUUtil.getPayableVOsByInvoiceBids(bids);
      if (ArrayUtils.isEmpty(payableBills)) {
        return null;
      }
      Map<ArapBillMapVO, Collection<ArapBillMapVO>> returnMap =
          new HashMap<ArapBillMapVO, Collection<ArapBillMapVO>>();
      for (ArapBillMapVO vo : mapvo) {
        List<ArapBillMapVO> list = new ArrayList<ArapBillMapVO>();
        String orderId = vo.getS_billid();
        List<String> invoiceBid = mapList.get(orderId);
        if (CollectionUtils.isEmpty(invoiceBid)) {
          continue;
        }

        for (AggPayableBillVO payableBill : payableBills) {
          PayableBillVO parentVO = payableBill.getHeadVO();
          for (PayableBillItemVO itemVO : payableBill.getBodyVOs()) {
            String topbid = itemVO.getTop_itemid();
            if (invoiceBid.contains(topbid)) {
              ArapBillMapVO returnVO = new ArapBillMapVO();
              returnVO.setS_system(4);
              returnVO.setS_billid(parentVO.getPrimaryKey());
              returnVO.setS_itemid(itemVO.getPrimaryKey());
              returnVO.setS_billtype(IArapBillTypeCons.D1);
              returnVO.setYbje(itemVO.getMoney_bal());
              list.add(returnVO);
            }
          }
        }

        returnMap.put(vo, list);
      }
      return returnMap;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  private Map<String, String> getBidsByIds(Set<String> orderIdSet) {
    DataAccessUtils util = new DataAccessUtils();
    Map<String, String> idMap = new HashMap<>();
    IDExQueryBuilder idbuilder =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_11.name());
    String sql = " select pk_order, pk_order_b from po_order_b where " 
        + idbuilder.buildSQL("pk_order", orderIdSet.toArray(new String[0]));
    IRowSet rowSet = util.query(sql);
    while(rowSet.next()){
      idMap.put(rowSet.getString(1), rowSet.getString(0));
    }
    return idMap;
  }
}
