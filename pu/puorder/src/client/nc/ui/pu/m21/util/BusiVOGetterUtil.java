package nc.ui.pu.m21.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.itf.tb.control.IAccessableBusiVO;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.entity.PraybillViewVO;
import nc.vo.pu.m20.entity.tbb.PrayBillBudgetCtlVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.tbb.OrderAsPrayBudgetCtrlVO;
import nc.vo.pu.m21.entity.tbb.OrderAsPrayExecBudgetCtrlVO;
import nc.vo.pu.m21.entity.tbb.OrderBudgetCtrlVO;
import nc.vo.pu.m21.entity.tbb.OrderExecBudgetCtrlVO;
import nc.vo.pu.tbb.BillOperationEnum;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;

import nc.pubitf.pu.m20.pub.IQueryPrayBill;

import nc.bs.framework.common.NCLocator;

import nc.ui.pu.reference.mpp.MppUIServices;

public class BusiVOGetterUtil {

  public static void linkQueryMpp(OrderVO vo) {
    MppUIServices.linkQueryMpp(BusiVOGetterUtil.getBusiVOFor21(vo));
  }

  public static IAccessableBusiVO[] getBusiVOFor21(OrderVO vo) {

    List<IAccessableBusiVO> budgets = new ArrayList<IAccessableBusiVO>();
    String[] execbilltypes =
        new String[] {
          POBillType.Order.getCode(), POBillType.Arrive.getCode(),
          POBillType.Invoice.getCode(), ICBillType.PurchaseIn.getCode()
        };
    List<String> courcebidList = new ArrayList<String>();
    Map<String, OrderHeaderVO> headMap = new HashMap<String, OrderHeaderVO>();
    Map<String, OrderItemVO> itemMap = new HashMap<String, OrderItemVO>();
    OrderHeaderVO headvo = vo.getHVO();
    for (OrderItemVO item : vo.getBVO()) {
      if (POBillType.PrayBill.getCode().equals(item.getCsourcetypecode())) {
        String csourcebid = item.getCsourcebid();
        courcebidList.add(csourcebid);
        headMap.put(csourcebid, headvo);
        itemMap.put(csourcebid, item);
      }
      for (String exec : execbilltypes) {
        budgets.add(new OrderBudgetCtrlVO(vo.getHVO(), item,
            BillOperationEnum.APPROVE.getValue(), exec));
        budgets.add(new OrderExecBudgetCtrlVO(vo.getHVO(), item,
            BillOperationEnum.APPROVE.getValue(), exec));
      }
    }

    if (courcebidList.size() > 0) {
      String[] courcebids =
          courcebidList.toArray(new String[courcebidList.size()]);
      IQueryPrayBill service =
          NCLocator.getInstance().lookup(IQueryPrayBill.class);

      try {
        PraybillVO[] prayVOs = service.queryVOByBids(courcebids);
        for (PraybillVO prayvo : prayVOs) {
          PraybillHeaderVO prayheadvo = prayvo.getHVO();
          for (PraybillItemVO item : prayvo.getBVO()) {
            String bid = item.getPk_praybill_b();
            OrderHeaderVO orderheadervo = headMap.get(bid);
            OrderItemVO orderitemvo = itemMap.get(bid);
            PraybillViewVO prayviewvo = new PraybillViewVO();
            prayviewvo.setHead(prayheadvo);
            prayviewvo.setItem(item);
            for (String exec : execbilltypes) {
              budgets.add(new PrayBillBudgetCtlVO(prayvo.getHVO(), item,
                  BillOperationEnum.APPROVE.getValue(), exec));
              budgets.add(new OrderAsPrayBudgetCtrlVO(orderheadervo,
                  orderitemvo, BillOperationEnum.APPROVE.getValue(), exec,
                  prayviewvo));
              budgets.add(new OrderAsPrayExecBudgetCtrlVO(orderheadervo,
                  orderitemvo, BillOperationEnum.APPROVE.getValue(), exec,
                  prayviewvo));
            }
          }
        }
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }

    return budgets.toArray(new IAccessableBusiVO[budgets.size()]);

  }

}
