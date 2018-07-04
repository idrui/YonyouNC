/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-12 下午03:42:51
 */
package nc.bs.pu.m21.state.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.mpp.reference.tbb.TbbCtrlServices;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pu.m20.entity.PraybillViewVO;
import nc.vo.pu.m21.entity.OrderCloseVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.tbb.OrderAsPrayBudgetCtrlVO;
import nc.vo.pu.m21.entity.tbb.OrderAsPrayExecBudgetCtrlVO;
import nc.vo.pu.m21.entity.tbb.OrderBudgetCtrlVO;
import nc.vo.pu.m21.entity.tbb.OrderExecBudgetCtrlVO;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.tbb.BillOperationEnum;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              判断是否超预算，并且修改预占数
 * @scene
 *        采购订单行状态打开、关闭
 * @param String closebilltype 订单类型 BillOperationEnum state 订单状态
 * @since 6.3
 * @version 2014-10-21 上午8:31:14
 * @author luojw
 */
public class StateMPPCtrlCheckRule implements IRule<OrderCloseVO> {
  private String closebilltype;

  private BillOperationEnum state;

  public StateMPPCtrlCheckRule(String closebilltype, BillOperationEnum state) {
    this.closebilltype = closebilltype;
    this.state = state;
  }

  @Override
  public void process(OrderCloseVO[] vos) {
    if (!SysInitGroupQuery.isMPPEnabled()) {
      return;
    }
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    this.budgetCtrl(vos);

  }

  /**
   * 判断是否超预算，并且修改预占数
   * 
   * @param vos
   */
  private void budgetCtrl(OrderCloseVO[] vos) {
    List<OrderBudgetCtrlVO> budgets = new ArrayList<OrderBudgetCtrlVO>();
    // 得到订单对应的请购单行一关闭的请购单行view
    Map<String, PraybillViewVO> praybills = this.getPraybillViewVO(vos);
    for (OrderCloseVO vo : vos) {
      if (POBillType.PrayBill.getCode().equals(
          vo.getAttributeValue(OrderItemVO.CSOURCETYPECODE))) {
        PraybillViewVO view =
            praybills.get(vo.getAttributeValue(OrderItemVO.CSOURCEBID));
        // 请购单
        if (view != null) {
          OrderAsPrayExecBudgetCtrlVO budgetasprayexec =
              new OrderAsPrayExecBudgetCtrlVO(
                  (OrderHeaderVO) vo.getVO(OrderHeaderVO.class),
                  (OrderItemVO) vo.getVO(OrderItemVO.class),
                  this.state.getValue(), this.closebilltype, view);
          this.setOriginValue(budgetasprayexec, vo);
          budgets.add(budgetasprayexec);
          OrderAsPrayBudgetCtrlVO budgetaspray =
              new OrderAsPrayBudgetCtrlVO(
                  (OrderHeaderVO) vo.getVO(OrderHeaderVO.class),
                  (OrderItemVO) vo.getVO(OrderItemVO.class),
                  this.state.getValue(), this.closebilltype, view);

          budgets.add(budgetaspray);
        }
      }
      OrderExecBudgetCtrlVO budgetexec =
          new OrderExecBudgetCtrlVO(
              (OrderHeaderVO) vo.getVO(OrderHeaderVO.class),
              (OrderItemVO) vo.getVO(OrderItemVO.class), this.state.getValue(),
              this.closebilltype);
      this.setOriginValue(budgetexec, vo);
      budgets.add(budgetexec);
      OrderBudgetCtrlVO budget =
          new OrderBudgetCtrlVO((OrderHeaderVO) vo.getVO(OrderHeaderVO.class),
              (OrderItemVO) vo.getVO(OrderItemVO.class), this.state.getValue(),
              this.closebilltype);
      budgets.add(budget);
    }
    // 调用预算接口，检查和更新预占数
    if (BillOperationEnum.OPEN.getValue() == this.state.getValue()) {
      TbbCtrlServices.noCheckUpdateExe(budgets
          .toArray(new OrderBudgetCtrlVO[budgets.size()]));
    }
    else {
      TbbCtrlServices.getControlInfo(budgets
          .toArray(new OrderBudgetCtrlVO[budgets.size()]));
    }

  }

  private String[] getPrayBillBids(OrderCloseVO[] vos) {
    Set<String> bids = new HashSet<String>();
    for (OrderCloseVO item : vos) {
      if (POBillType.PrayBill.getCode().equals(
          item.getAttributeValue(OrderItemVO.CSOURCETYPECODE))) {
        bids.add((String) item.getAttributeValue(OrderItemVO.CSOURCEBID));
      }
    }
    return bids.toArray(new String[bids.size()]);
  }

  private Map<String, PraybillViewVO> getPraybillViewVO(OrderCloseVO[] vos) {
    Map<String, PraybillViewVO> result = new HashMap<String, PraybillViewVO>();
    String[] praybillbids = this.getPrayBillBids(vos);
    if (ArrayUtils.isEmpty(praybillbids)) {
      return result;
    }
    DataAccessUtils utils = new DataAccessUtils();
    StringBuilder wholeSql = new StringBuilder();
    wholeSql.append(" select pk_praybill_b from po_praybill_b pb where ");
    IDExQueryBuilder idbuilder =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_08.name());
    wholeSql.append(idbuilder.buildSQL(" pb.pk_praybill_b ", praybillbids));
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

  private void setOriginValue(OrderExecBudgetCtrlVO budgetexec, OrderCloseVO vo) {
    if (POBillType.Arrive.getCode().equals(this.closebilltype)) {
      budgetexec.setNaccumarrvnum_pre(vo.getNaccumarrvnum_pre());
    }
    else if (POBillType.Invoice.getCode().equals(this.closebilltype)) {
      budgetexec.setNaccuminvoicemny_pre(vo.getNaccuminvoicemny_pre());
      budgetexec.setNaccuminvoicenum_pre(vo.getNaccuminvoicenum_pre());
    }
    else if (ICBillType.PurchaseIn.getCode().equals(this.closebilltype)) {
      budgetexec.setNaccumstorenum_pre(vo.getNaccumstorenum_pre());
    }

  }
}
