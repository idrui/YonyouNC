package nc.bs.pu.m20.maintain.rule.close;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.itf.mpp.reference.tbb.TbbCtrlServices;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.entity.tbb.PrayBillBudgetCtlVO;
import nc.vo.pu.tbb.BillOperationEnum;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * @description
 *              请购单整单打开时的预算控制
 * @scene
 *        请购单整单打开
 * @param 无
 * @since 6.3
 * @version 2014-10-21 上午10:16:44
 * @author yanxm5
 */

public class OpenBudgetCtrlRule extends CloseBudgetCtrlRule {
  @Override
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
        if (UFBoolean.FALSE.equals(item.getBrowclose())
            && UFBoolean.TRUE.equals(originitem.getBrowclose())) {
          prayBillbids.add(item.getPk_praybill_b());
          for (String exec : execbilltypes) {
            PrayBillBudgetCtlVO budget =
                new PrayBillBudgetCtlVO(vo.getHVO(), item,
                    BillOperationEnum.OPEN.getValue(), exec);
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
}
