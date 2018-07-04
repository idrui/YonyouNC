package nc.pubimpl.pu.m21.arap.mf1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.arap.util.IArapBillTypeCons;
import nc.bs.framework.common.NCLocator;
import nc.itf.pu.reference.ic.M45PUServices;
import nc.itf.scmpub.reference.arap.ArapServicesForPUUtil;
import nc.pubift.pu.m25.pu.m21.IInvoiceQueryFor21;
import nc.pubitf.arap.pub.IArap4VerifyQryBill;
import nc.vo.arap.pay.AggPayBillVO;
import nc.vo.arap.pay.PayBillItemVO;
import nc.vo.arap.pay.PayBillVO;
import nc.vo.arap.pfflow.ArapBillMapVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 自动核销查询
 * 
 * @since 6.0
 * @version 2011-3-6 下午03:09:45
 * @author wuxla
 */

public class OrderQueryForVerifyImpl implements IArap4VerifyQryBill {

  @Override
  public Map<ArapBillMapVO, Collection<ArapBillMapVO>> queryArapBillmap(
      ArapBillMapVO[] mapvo) throws BusinessException {
    try {
      if (ArrayUtils.isEmpty(mapvo)) {
        return null;
      }

      Set<String> invoiceIdSet = new HashSet<String>();
      for (ArapBillMapVO vo : mapvo) {
        invoiceIdSet.add(vo.getS_itemid());
      }
      if (0 == invoiceIdSet.size()) {
        return null;
      }

      String[] invoiceIds =
          invoiceIdSet.toArray(new String[invoiceIdSet.size()]);
      IInvoiceQueryFor21 invoiceQueryFor21 =
          NCLocator.getInstance().lookup(IInvoiceQueryFor21.class);

      Map<String, String> invoiceOrderMap = new HashMap<String, String>();
      Map<String, String> invoiceMap =
          invoiceQueryFor21.queryOrderBidByInvoiceBid(invoiceIds);
      if (invoiceMap != null) {
        invoiceOrderMap.putAll(invoiceMap);
      }
      Map<String, String> icOrderMap =
          M45PUServices.queryOrderCodeByPurchaseInBid(invoiceIds);
      if (icOrderMap != null) {
        invoiceOrderMap.putAll(icOrderMap);
      }
      if (0 == invoiceOrderMap.size()) {
        return null;
      }

      Set<String> codeSet = new HashSet<String>();
      for (String coce : invoiceOrderMap.values()) {
        codeSet.add(coce);
      }

      String[] codes = codeSet.toArray(new String[codeSet.size()]);
      if (null == codes) {
        return null;
      }
      AggPayBillVO[] payBills = ArapServicesForPUUtil.getVOsByOrderCodes(codes);
      if (ArrayUtils.isEmpty(payBills)) {
        return null;
      }

      Map<ArapBillMapVO, Collection<ArapBillMapVO>> returnMap =
          new HashMap<ArapBillMapVO, Collection<ArapBillMapVO>>();
      for (ArapBillMapVO vo : mapvo) {
        List<ArapBillMapVO> list = new ArrayList<ArapBillMapVO>();
        String invoiceId = vo.getS_itemid();
        String ordercode = invoiceOrderMap.get(invoiceId);
        if (null == ordercode) {
          continue;
        }

        for (AggPayBillVO paybill : payBills) {
          PayBillVO parentVO = (PayBillVO) paybill.getParentVO();
          PayBillItemVO[] payitemVOs =
              (PayBillItemVO[]) paybill.getChildrenVO();
          for (PayBillItemVO payitemVO : payitemVOs) {
            if (ordercode.equals(payitemVO.getPurchaseorder())) {
              ArapBillMapVO returnVO = new ArapBillMapVO();
              returnVO.setS_system(4);
              returnVO.setS_billid(parentVO.getPrimaryKey());
              returnVO.setS_itemid(payitemVO.getPrimaryKey());
              returnVO.setS_billtype(IArapBillTypeCons.D3);
              returnVO.setYbje(payitemVO.getMoney_bal());
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
}
