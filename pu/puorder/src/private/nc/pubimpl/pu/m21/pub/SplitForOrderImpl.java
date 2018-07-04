package nc.pubimpl.pu.m21.pub;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.m21.pub.IOrderPubQuery;
import nc.pubitf.pu.m21.pub.ISplitForOrder;
import nc.pubitf.scmf.sourcing.sour4pu.ISourcePUService;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmf.sourcing.entity.SourceVO;
import nc.vo.scmf.sourcing.entity.SupplierPrice;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.0
 * @version 2011-7-12 ÏÂÎç03:30:20
 * @author liuchx
 */
public class SplitForOrderImpl implements ISplitForOrder {

  @Override
  public List<String> splitBCcurrencyid(AggregatedValueObject vo, String[] keys) {
    return this.splitInBody(vo, keys, false);
  }

  @Override
  public List<String> splitBSupplier(AggregatedValueObject vo, String[] keys) {
    return this.splitInBody(vo, keys, true);
  }

  @Override
  public List<String> splitByCtCode(String[] bids) {
    List<String> retList = new ArrayList<String>();
    IOrderPubQuery service =
        NCLocator.getInstance().lookup(IOrderPubQuery.class);
    try {
      Map<String, String> ctCode = service.getCtCodeByBID(bids);
      if (MapUtils.isEmpty(ctCode)) {
        for (String id : bids) {
          retList.add("null");
        }
        return retList;
      }
      for (String id : bids) {
        if (null == ctCode.get(id)) {
          retList.add("null");
        }
        else {
          retList.add(ctCode.get(id));
        }
      }
      return retList;
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  @Override
  public List<String> splitHCcurrencyid(AggregatedValueObject vo, String[] keys) {
    return this.splitInHead(vo, keys, false);
  }

  @Override
  public List<String> splitHSupplier(AggregatedValueObject vo, String[] keys) {
    return this.splitInHead(vo, keys, true);
  }

  private List<String> splitInBody(AggregatedValueObject vo, String[] keys,
      boolean supplierFlag) {
    try {
      List<String> ret = new ArrayList<String>();
      SourceVO[] paras = new SourceVO[vo.getChildrenVO().length];
      for (int i = 0; i < vo.getChildrenVO().length; i++) {
        String pk_stockorg =
            (String) vo.getChildrenVO()[i].getAttributeValue(keys[0]);
        String material =
            (String) vo.getChildrenVO()[i].getAttributeValue(keys[1]);
        SourceVO para =
            new SourceVO(material, pk_stockorg, UFBoolean.FALSE, null, null);
        paras[i] = para;
      }
      SupplierPrice[] prices =
          NCLocator.getInstance().lookup(ISourcePUService.class)
              .queryForOrderBill(paras);
      for (SupplierPrice price : prices) {
        if (null == price) {
          ret.add("null");
          continue;
        }
        if (supplierFlag) {
          ret.add(price.getPk_supplier());
        }
        else {
          ret.add(price.getCurrency());
        }
      }
      return ret;
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  private List<String> splitInHead(AggregatedValueObject vo, String[] keys,
      boolean supplierFlag) {
    try {
      List<String> ret = new ArrayList<String>();

      String pk_stockorg = (String) vo.getParentVO().getAttributeValue(keys[0]);
      String material = (String) vo.getParentVO().getAttributeValue(keys[1]);
      SourceVO para =
          new SourceVO(material, pk_stockorg, UFBoolean.FALSE, null, null);
      SourceVO[] paras = new SourceVO[] {
        para
      };

      SupplierPrice[] prices =
          NCLocator.getInstance().lookup(ISourcePUService.class)
              .queryForOrderBill(paras);
      if (ArrayUtils.isEmpty(prices)) {
        ret.add("null");
        return ret;
      }
      if (supplierFlag) {
        ret.add(prices[0].getPk_supplier());
      }
      else {
        ret.add(prices[0].getCurrency());
      }

      return ret;
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }
  @Override
  public List<String> splitByStype(String[] hids) {
    List<String> retList = new ArrayList<String>();
    IOrderPubQuery service =
        NCLocator.getInstance().lookup(IOrderPubQuery.class);
    try {
      Map<String, String> ctCode = service.splitByStypeByBID(hids);
      if (MapUtils.isEmpty(ctCode)) {
        for(int i=0;i<hids.length;i++){
        	retList.add("null");
        }
        return retList;
      }
      for (String id : hids) {
        if (null == ctCode.get(id)) {
          retList.add("null");
        }
        else {
          retList.add(ctCode.get(id));
        }
      }
      return retList;
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }
}
