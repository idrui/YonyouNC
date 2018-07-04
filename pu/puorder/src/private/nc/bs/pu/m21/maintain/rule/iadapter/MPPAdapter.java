package nc.bs.pu.m21.maintain.rule.iadapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import nc.vo.pu.m20.entity.PraybillViewVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.tbb.OrderAsPrayBudgetCtrlVO;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.tbb.BillOperationEnum;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;

import nc.itf.mpp.reference.tbb.TbbCtrlServices;

import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.ms.tb.control.BudgetControlServiceGetter;

public class MPPAdapter {

  /**
   * 修改预占数
   * 
   * @param vos
   */
  public void budgetCtrl(OrderVO[] vos, OrderVO[] originVOs) {
    if (ArrayUtils.isEmpty(vos)) {
      // vos为空则为删除操作
      this.processDeleteOrder(originVOs);
      return;
    }
    String[] execbilltypes =
        new String[] {
          POBillType.Order.getCode(), POBillType.Arrive.getCode(),
          POBillType.Invoice.getCode(), ICBillType.PurchaseIn.getCode()
        };
    List<OrderAsPrayBudgetCtrlVO> budgets =
        new ArrayList<OrderAsPrayBudgetCtrlVO>();
    // 得到订单对应的请购单行一关闭的请购单行view
    Map<String, PraybillViewVO> praybills = this.getPraybillViewVO(vos);
    for (OrderVO vo : vos) {
      OrderVO origin = this.getOriginBill(originVOs, vo.getHVO().getPk_order());
      for (OrderItemVO item : vo.getBVO()) {
        if (POBillType.PrayBill.getCode().equals(item.getCsourcetypecode())) {
          PraybillViewVO view = praybills.get(item.getCsourcebid());
          // 没有参照请购单或者为审批后的单据，不修改计划的预占数
          if (view == null
              || POEnumBillStatus.APPROVE.toInteger().equals(
                  vo.getHVO().getForderstatus())) {
            continue;
          }
          if (item.getStatus() == VOStatus.DELETED) {
            // 行删除
            for (String exec : execbilltypes) {
              OrderAsPrayBudgetCtrlVO budget =
                  new OrderAsPrayBudgetCtrlVO(vo.getHVO(), item,
                      BillOperationEnum.DELETE.getValue(), exec, view);
              budgets.add(budget);
            }
          }
          else if (item.getStatus() == VOStatus.UPDATED) {
            // 行更新
            if (null == origin) {
              continue;
            }
            OrderItemVO originitem =
                this.getOriginItem(origin.getBVO(), item.getPk_order_b());
            for (String exec : execbilltypes) {
              OrderAsPrayBudgetCtrlVO budget =
                  new OrderAsPrayBudgetCtrlVO(vo.getHVO(), item,
                      BillOperationEnum.MODIFY.getValue(), exec, view,
                      originitem);
              budgets.add(budget);
            }
          }
        }
      }
    }
    if (budgets.size() == 0) {
      return;
    }
    // 调用预算接口，检查和更新预占数
    // 写预占数
    TbbCtrlServices.noCheckUpdateExe(budgets
        .toArray(new OrderAsPrayBudgetCtrlVO[budgets.size()]));

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
    wholeSql
        .append(" select pk_praybill_b from po_praybill_b pb where pb.browclose='"
            + UFBoolean.TRUE.toString() + "' and ");
    IDExQueryBuilder idbuilder =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_02.name());
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

  private void processDeleteOrder(OrderVO[] originVOs) {
    List<OrderAsPrayBudgetCtrlVO> budgets =
        new ArrayList<OrderAsPrayBudgetCtrlVO>();
    String[] execbilltypes =
        new String[] {
          POBillType.Order.getCode(), POBillType.Arrive.getCode(),
          POBillType.Invoice.getCode(), ICBillType.PurchaseIn.getCode()
        };
    // 得到订单对应的请购单行一关闭的请购单行view
    Map<String, PraybillViewVO> praybills = this.getPraybillViewVO(originVOs);
    for (OrderVO vo : originVOs) {
      for (OrderItemVO item : vo.getBVO()) {
        if (POBillType.PrayBill.getCode().equals(item.getCsourcetypecode())) {
          PraybillViewVO view = praybills.get(item.getCsourcebid());
          // 没有参照请购单或者为审批后的单据，不修改计划的预占数
          if (view == null
              || POEnumBillStatus.APPROVE.toInteger().equals(
                  vo.getHVO().getForderstatus())) {
            continue;
          }
          for (String exec : execbilltypes) {
            OrderAsPrayBudgetCtrlVO budget =
                new OrderAsPrayBudgetCtrlVO(vo.getHVO(), item,
                    BillOperationEnum.DELETE.getValue(), exec, view);
            budgets.add(budget);
          }
        }
      }
    }
    if (budgets.size() == 0) {
      return;
    }
    // 调用预算接口，检查和更新预占数
    // 写预占数
    try {
      BudgetControlServiceGetter.getIBudgetControl().noCheckUpdateExe(
          budgets.toArray(new OrderAsPrayBudgetCtrlVO[budgets.size()]));
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

  }

  protected OrderVO getOriginBill(OrderVO[] origins, String pk_bill) {
    if (ArrayUtils.isEmpty(origins) || StringUtils.isEmpty(pk_bill)) {
      return null;
    }
    for (OrderVO ite : origins) {
      if (pk_bill.equals(ite.getHVO().getPk_order())) {
        return ite;
      }
    }
    return null;
  }

  protected OrderItemVO getOriginItem(OrderItemVO[] items, String pk_item) {
    for (OrderItemVO ite : items) {
      if (pk_item.equals(ite.getPk_order_b())) {
        return ite;
      }
    }
    return null;
  }

}
