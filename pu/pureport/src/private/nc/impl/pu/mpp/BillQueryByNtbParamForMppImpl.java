package nc.impl.pu.mpp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.bs.pu.mpp.strategy.orderaspraybill.ArrivalAsPrayBillBatchLinkQuery;
import nc.bs.pu.mpp.strategy.orderaspraybill.InvoiceAsPrayBillBatchLinkQuery;
import nc.bs.pu.mpp.strategy.orderaspraybill.OrderAsPrayBillBatchLinkQuery;
import nc.bs.pu.mpp.strategy.orderaspraybill.OrderCloseAsPrayBillBatchLinkQuery;
import nc.bs.pu.mpp.strategy.orderaspraybill.PurchaseinAsPrayBillBatchLinkQuery;
import nc.bs.pu.mpp.strategy.orderbill.ArrivalBatchLinkQuery;
import nc.bs.pu.mpp.strategy.orderbill.InvoiceBatchLinkQuery;
import nc.bs.pu.mpp.strategy.orderbill.OrderBatchLinkQuery;
import nc.bs.pu.mpp.strategy.orderbill.PurchaseinBatchLinkQuery;
import nc.bs.pu.mpp.strategy.praybill.PrayBillBatchLinkQuery;
import nc.itf.pu.mpp.IBillQueryByNtbParamForMpp;
import nc.pubitf.pu.m20.pub.IQueryPrayBill;
import nc.pubitf.pu.m21.pub.IOrderPubQuery;
import nc.vo.ic.m45.entity.PurchaseInViewVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.tb.obj.NtbParamVO;

import org.apache.commons.lang.ArrayUtils;

public class BillQueryByNtbParamForMppImpl implements
    IBillQueryByNtbParamForMpp {

  @Override
  public OrderVO[] execLinkQuery21(NtbParamVO param, String readyBilltype)
      throws BusinessException {
    if (POBillType.PrayBill.getCode().equals(readyBilltype)) {
      OrderAsPrayBillBatchLinkQuery query = new OrderAsPrayBillBatchLinkQuery();
      return query.execLinkQuery21ForTbb(param);
    }
    else if (POBillType.Order.getCode().equals(readyBilltype)) {
      OrderBatchLinkQuery query = new OrderBatchLinkQuery();
      return query.execLinkQuery21(param);
    }
    return null;
  }

  @Override
  public ArriveVO[] execLinkQuery23(NtbParamVO param, String readyBilltype)
      throws BusinessException {
    if (POBillType.PrayBill.getCode().equals(readyBilltype)) {
      ArrivalAsPrayBillBatchLinkQuery query =
          new ArrivalAsPrayBillBatchLinkQuery();
      return query.execLinkQuery23(param);
    }
    else if (POBillType.Order.getCode().equals(readyBilltype)) {
      ArrivalBatchLinkQuery query = new ArrivalBatchLinkQuery();
      return query.execLinkQuery23(param);
    }
    return null;
  }

  @Override
  public InvoiceVO[] execLinkQuery25(NtbParamVO param, String readyBilltype)
      throws BusinessException {
    if (POBillType.PrayBill.getCode().equals(readyBilltype)) {
      InvoiceAsPrayBillBatchLinkQuery query =
          new InvoiceAsPrayBillBatchLinkQuery();
      return query.execLinkQuery25(param);
    }
    else if (POBillType.Order.getCode().equals(readyBilltype)) {
      InvoiceBatchLinkQuery query = new InvoiceBatchLinkQuery();
      return query.execLinkQuery25(param);
    }
    return null;
  }

  @Override
  public PurchaseInViewVO[] execLinkQuery45(NtbParamVO param,
      String readyBilltype) throws BusinessException {
    if (POBillType.PrayBill.getCode().equals(readyBilltype)) {
      PurchaseinAsPrayBillBatchLinkQuery query =
          new PurchaseinAsPrayBillBatchLinkQuery();
      return query.execLinkQuery45(param);
    }
    else if (POBillType.Order.getCode().equals(readyBilltype)) {
      PurchaseinBatchLinkQuery query = new PurchaseinBatchLinkQuery();
      return query.execLinkQuery45(param);
    }
    return null;
  }

  @Override
  public OrderVO[] readyLinkQuery20CloseForTbb(NtbParamVO param,
      String exebilltype) throws BusinessException {
    List<String> list = new ArrayList<String>();
    OrderAsPrayBillBatchLinkQuery query = new OrderAsPrayBillBatchLinkQuery();
    query.setExebilltype(exebilltype);
    String[] orderaspray = query.readyLinkQuery20CloseForTbb(param);
    if (!ArrayUtils.isEmpty(orderaspray)) {
      list.addAll(Arrays.asList(orderaspray));
    }

    OrderCloseAsPrayBillBatchLinkQuery orderquery =
        new OrderCloseAsPrayBillBatchLinkQuery();
    orderquery.setExebilltype(exebilltype);
    String[] prayorderids = orderquery.readyLinkQuery20CloseForTbb(param);
    if (!ArrayUtils.isEmpty(prayorderids)) {
      list.addAll(Arrays.asList(prayorderids));
    }

    if (list.size() == 0) {
      return null;
    }

    String[] bids = list.toArray(new String[list.size()]);
    IOrderPubQuery service =
        NCLocator.getInstance().lookup(IOrderPubQuery.class);
    return service.queryOrderVOByBids(bids);
  }

  @Override
  public PraybillVO[] readyLinkQuery20ForTbb(NtbParamVO param,
      String exebilltype) throws BusinessException {
    List<String> list = new ArrayList<String>();
    PrayBillBatchLinkQuery query = new PrayBillBatchLinkQuery();
    String[] praybids = query.readyLinkQuery20ForTbb(param);
    if (!ArrayUtils.isEmpty(praybids)) {
      list.addAll(Arrays.asList(praybids));
    }

    if (list.size() == 0) {
      return null;
    }
    String[] bids = list.toArray(new String[list.size()]);
    IQueryPrayBill service =
        NCLocator.getInstance().lookup(IQueryPrayBill.class);
    PraybillVO[] vos = service.queryVOByBids(bids);
    return vos;
  }

  @Override
  public OrderVO[] readyLinkQuery21ForTbb(NtbParamVO param, String exebilltype)
      throws BusinessException {
    try {
      OrderBatchLinkQuery query = new OrderBatchLinkQuery();
      query.setExebilltype(exebilltype);
      OrderVO[] vos = query.readyLinkQuery21(param);
      return vos;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

}
