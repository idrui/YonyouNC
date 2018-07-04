package nc.bs.pu.m20.maintain.rule.close;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.itf.mpp.reference.tbb.TbbCtrlServices;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.scmpub.reference.uap.org.StockOrgPubService;
import nc.pubitf.uapbd.CurrencyRateUtil;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.entity.tbb.PrayBillBudgetCtlVO;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.tbb.BillOperationEnum;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.util.TimeUtils;

/**
 * @description
 *              请购单关闭请购单行，修改采购计划预占数后规则
 * @scene
 *        请购单整单关闭
 * @param 无
 * @since 6.3
 * @version 2014-10-21 上午9:15:24
 * @author yanxm5
 */

public class CloseBudgetCtrlRule implements ICompareRule<PraybillVO> {
  @Override
  public void process(PraybillVO[] vos, PraybillVO[] originVOs) {
    // 判断采购计划是否启用
    if (!SysInitGroupQuery.isMPPEnabled()) {
      return;
    }
    this.budgetCtrl(vos, originVOs);
  }

  protected void budgetCtrl(PraybillVO[] vos, PraybillVO[] originVOs) {
    List<PrayBillBudgetCtlVO> budgets = new ArrayList<PrayBillBudgetCtlVO>();
    List<String> prayBillbids = new ArrayList<String>();
    String[] execbilltypes =
        new String[] {
          POBillType.Order.getCode(), POBillType.Arrive.getCode(),
          POBillType.Invoice.getCode(), ICBillType.PurchaseIn.getCode()
        };
    for (PraybillVO vo : vos) {
      PraybillVO origin =
          this.getOriginBill(originVOs, vo.getHVO().getPk_praybill());
      if (null == origin) {
        continue;
      }
      for (PraybillItemVO item : vo.getBVO()) {
        PraybillItemVO originitem =
            this.getOriginItem(origin.getBVO(), item.getPk_praybill_b());
        if (null == originitem) {
          continue;
        }
        if (UFBoolean.TRUE.equals(item.getBrowclose())
            && UFBoolean.FALSE.equals(originitem.getBrowclose())) {
          prayBillbids.add(item.getPk_praybill_b());
          for (String exec : execbilltypes) {
            PrayBillBudgetCtlVO budget =
                new PrayBillBudgetCtlVO(vo.getHVO(), item,
                    BillOperationEnum.CLOSE.getValue(), exec);
            budgets.add(budget);
          }
        }
      }
      Map<String, UFDouble> bidmnyMap = this.queryOrderMny(prayBillbids);
      for (PrayBillBudgetCtlVO ctrlvo : budgets) {
        UFDouble ordermny = bidmnyMap.get(ctrlvo.getItem().getPk_praybill_b());
        ctrlvo.setOrdermny(ordermny);
      }
    }
    // 调用预算接口，检查和更新预占数
    // 写预占数
    if (budgets.size() > 0) {
      TbbCtrlServices.noCheckUpdateExe(budgets
          .toArray(new PrayBillBudgetCtlVO[budgets.size()]));
    }

  }

  protected Map<String, UFDouble> calOrderMny(IRowSet rowset) {
    Map<String, UFDouble> result = new HashMap<String, UFDouble>();
    List<String> pk_orgs = new ArrayList<String>();
    List<Object[]> rows = new ArrayList<Object[]>();
    while (rowset.next()) {
      Object[] row = new Object[5];
      row[0] = rowset.getString(0);
      row[1] = rowset.getString(1);
      row[2] = rowset.getString(2);
      row[3] = rowset.getString(3);
      row[4] = rowset.getUFDouble(4);
      rows.add(row);
      pk_orgs.add(rowset.getString(0));
    }
    try {
      Map<String, String> finance =
          StockOrgPubService.queryFinanceOrgIDByStockOrgID(pk_orgs
              .toArray(new String[pk_orgs.size()]));
      for (Object[] row : rows) {
        CurrencyRateUtil util =
            CurrencyRateUtil.getInstanceByOrg(finance.get(row[0]));
        // 折算汇率，现在为当前时间的汇率，需要确认
        UFDouble data = result.get(row[1]);
        data =
            MathTool.nvl(
                util.getAmountByOpp((String) row[3], (String) row[2],
                    (UFDouble) row[4], null, TimeUtils.getsrvBaseDate())).add(
                MathTool.nvl(data));
        result.put((String) row[1], data);
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return result;
  }

  protected PraybillVO getOriginBill(PraybillVO[] origins, String pk_bill) {
    for (PraybillVO ite : origins) {
      if (pk_bill.equals(ite.getHVO().getPk_praybill())) {
        return ite;
      }
    }
    return null;
  }

  protected PraybillItemVO getOriginItem(PraybillItemVO[] items, String pk_item) {
    for (PraybillItemVO ite : items) {
      if (pk_item.equals(ite.getPk_praybill_b())) {
        return ite;
      }
    }
    return null;
  }

  protected Map<String, UFDouble> queryOrderMny(List<String> prayBillbids) {
    if (prayBillbids.size() == 0) {
      return new HashMap<String, UFDouble>();
    }
    DataAccessUtils utils = new DataAccessUtils();
    StringBuilder wholeSql = new StringBuilder();
    wholeSql
        .append(" select pb.pk_org,pb.pk_praybill_b,p.ccurrencyid,ob.corigcurrencyid,sum(isnull(norigtaxmny,0)) from po_order_b ob,po_praybill_b pb,po_praybill p where ob.dr=0 and pb.dr=0 and ob.csourcebid=pb.pk_praybill_b and pb.pk_praybill=p.pk_praybill ");
    IDExQueryBuilder idbuilder =
        new IDExQueryBuilder(PUTempTable.tmp_po_20_01.name());
    wholeSql.append(idbuilder.buildSQL(" and pb.pk_praybill_b ",
        prayBillbids.toArray(new String[prayBillbids.size()])));
    wholeSql
        .append(" group by pb.pk_org,pb.pk_praybill_b,p.ccurrencyid,ob.corigcurrencyid order by pb.pk_org,pb.pk_praybill_b,p.ccurrencyid,ob.corigcurrencyid");
    IRowSet rowset = utils.query(wholeSql.toString());
    return this.calOrderMny(rowset);
  }
}
