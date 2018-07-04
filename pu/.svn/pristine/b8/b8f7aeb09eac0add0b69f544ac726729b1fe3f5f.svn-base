package nc.impl.pu.m21.action.rule.revise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.itf.mpp.reference.tbb.TbbCtrlServices;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pu.m20.entity.PraybillViewVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.tbb.OrderAsPrayBudgetCtrlVO;
import nc.vo.pu.m21.entity.tbb.OrderBudgetCtrlVO;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.tbb.BillOperationEnum;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              判断是否超预算，并且修改预占数
 * @scene
 *        采购订单修订
 * @param 无
 * @since 6.3
 * @version 2014-10-22 上午10:44:00
 * @author luojw
 */

public class ReviseBudgetCtrlRule implements ICompareRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos, OrderVO[] originVOs) {
    if (ArrayUtils.isEmpty(vos) || ArrayUtils.isEmpty(originVOs)) {
      return;
    }
    if (!SysInitGroupQuery.isMPPEnabled()) {
      return;
    }
    this.budgetCtrl(vos, originVOs);
  }

  /**
   * 判断是否超预算，并且修改预占数
   * 
   * @param vos
   */
  private void budgetCtrl(OrderVO[] vos, OrderVO[] origins) {
    List<OrderBudgetCtrlVO> budgets = new ArrayList<OrderBudgetCtrlVO>();
    String[] execbilltypes =
        new String[] {
          POBillType.Order.getCode(), POBillType.Arrive.getCode(),
          POBillType.Invoice.getCode(), ICBillType.PurchaseIn.getCode()
        };
    // 得到订单对应的请购单行一关闭的请购单行view
    Map<String, PraybillViewVO> praybills = this.getPraybillViewVO(vos);
    for (OrderVO vo : vos) {
      // 审批通过的单据
      if (POEnumBillStatus.APPROVE.toInteger().equals(
          vo.getHVO().getForderstatus())) {
        OrderVO origin = this.getOriginBill(origins, vo.getHVO().getPk_order());
        if (null == origin) {
          continue;
        }
        for (OrderItemVO item : vo.getBVO()) {
          OrderItemVO originitem =
              this.getOriginItem(origin.getBVO(), item.getPk_order_b());
          if (POBillType.PrayBill.getCode().equals(item.getCsourcetypecode())) {
            PraybillViewVO view = praybills.get(item.getCsourcebid());
            if (view != null) {
              for (String exec : execbilltypes) {
                OrderAsPrayBudgetCtrlVO budgetaspray =
                    new OrderAsPrayBudgetCtrlVO(vo.getHVO(), item,
                        BillOperationEnum.REVISE.getValue(), exec, view,
                        originitem);
                budgets.add(budgetaspray);
              }
            }
          }
          for (String exec : execbilltypes) {
            OrderBudgetCtrlVO budget =
                new OrderBudgetCtrlVO(vo.getHVO(), item,
                    BillOperationEnum.REVISE.getValue(), exec, originitem);
            budgets.add(budget);
          }
        }
      }
    }
    // 调用预算接口，检查和更新预占数
    // 写预占数
    if (budgets.size() > 0) {
      TbbCtrlServices.getControlInfo(budgets
          .toArray(new OrderBudgetCtrlVO[budgets.size()]));
    }

  }

  private OrderVO getOriginBill(OrderVO[] origins, String pk_bill) {
    for (OrderVO ite : origins) {
      if (pk_bill.equals(ite.getHVO().getPk_order())) {
        return ite;
      }
    }
    return null;
  }

  private OrderItemVO getOriginItem(OrderItemVO[] items, String pk_item) {
    for (OrderItemVO ite : items) {
      if (pk_item.equals(ite.getPk_order_b())) {
        return ite;
      }
    }
    return null;
  }

  private String[] getPrayBillBids(OrderVO[] vos) {
    Set<String> bids = new HashSet<String>();
    for (OrderVO vo : vos) {
      for (OrderItemVO item : vo.getBVO()) {
        if (POBillType.PrayBill.getCode().equals(item.getCsourcetypecode())) {
          bids.add(item.getCsourcebid());
        }
      }
    }
    return bids.toArray(new String[bids.size()]);
  }

  private Map<String, PraybillViewVO> getPraybillViewVO(OrderVO[] vos) {
    Map<String, PraybillViewVO> result = new HashMap<String, PraybillViewVO>();
    String[] prayBillBids = this.getPrayBillBids(vos);
    if (ArrayUtils.isEmpty(prayBillBids)) {
      return result;
    }
    DataAccessUtils utils = new DataAccessUtils();
    StringBuilder wholeSql = new StringBuilder();
    wholeSql.append(" select pk_praybill_b from po_praybill_b pb where ");
    IDExQueryBuilder idbuilder =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_14.name());
    wholeSql.append(idbuilder.buildSQL(" pb.pk_praybill_b ", prayBillBids));
    IRowSet rowset = utils.query(wholeSql.toString());
    ViewQuery<PraybillViewVO> viewqry =
        new ViewQuery<PraybillViewVO>(PraybillViewVO.class);
    PraybillViewVO[] praybills =
        viewqry.query(rowset.toOneDimensionStringArray());
    if (ArrayUtils.isEmpty(praybills)) {
      return result;
    }
    for (PraybillViewVO view : praybills) {
      result.put(view.getItem().getPk_praybill_b(), view);
    }
    return result;
  }
}
