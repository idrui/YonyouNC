package nc.bs.pu.m20.maintain.rule;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.itf.mpp.reference.tbb.TbbCtrlServices;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.entity.tbb.PrayBillBudgetCtlVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.tbb.BillOperationEnum;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * @description
 *              �빺���޶���д�ƻ�ռ�ñ仯��=�빺���޶�����빺���� - �빺���޶�ǰ���빺������
 * @scene
 *        �빺���޸�
 * @param ��
 * @since 6.3
 * @version 2014-10-21 ����10:49:09
 * @author yanxm5
 */

public class ReviseBudgetCtrlRule implements ICompareRule<PraybillVO> {

  @Override
  public void process(PraybillVO[] vos, PraybillVO[] originVOs) {
    // �жϲɹ��ƻ��Ƿ�����
    if (!SysInitGroupQuery.isMPPEnabled()) {
      return;
    }
    this.budgetCtrl(vos, originVOs);
  }

  /**
   * �ж��Ƿ�Ԥ�㣬�����޸�Ԥռ��
   * 
   * @param vos
   */
  private void budgetCtrl(PraybillVO[] vos, PraybillVO[] origins) {
    List<PrayBillBudgetCtlVO> budgets = new ArrayList<PrayBillBudgetCtlVO>();
    String[] execbilltypes =
        new String[] {
          POBillType.Order.getCode(), POBillType.Arrive.getCode(),
          POBillType.Invoice.getCode(), ICBillType.PurchaseIn.getCode()
        };
    for (PraybillVO vo : vos) {
      // ����ͨ���ĵ���
      if (POEnumBillStatus.APPROVE.toInteger().equals(
          vo.getHVO().getFbillstatus())) {
        PraybillVO origin =
            this.getOriginBill(origins, vo.getHVO().getPk_praybill());
        if (null == origin) {
          continue;
        }
        for (PraybillItemVO item : vo.getBVO()) {
          PraybillItemVO originitem =
              this.getOriginItem(origin.getBVO(), item.getPk_praybill_b());
          for (String exec : execbilltypes) {
            PrayBillBudgetCtlVO budget =
                new PrayBillBudgetCtlVO(vo.getHVO(), item,
                    BillOperationEnum.REVISE.getValue(), exec, originitem);
            budgets.add(budget);
          }
        }

      }
    }
    // ����Ԥ��ӿڣ�����Ԥռ��
    if (budgets.size() > 0) {
      TbbCtrlServices.getControlInfo(budgets
          .toArray(new PrayBillBudgetCtlVO[budgets.size()]));
    }

  }

  private PraybillVO getOriginBill(PraybillVO[] origins, String pk_bill) {
    for (PraybillVO ite : origins) {
      if (pk_bill.equals(ite.getHVO().getPk_praybill())) {
        return ite;
      }
    }
    return null;
  }

  private PraybillItemVO getOriginItem(PraybillItemVO[] items, String pk_item) {
    for (PraybillItemVO ite : items) {
      if (pk_item.equals(ite.getPk_praybill_b())) {
        return ite;
      }
    }
    return null;
  }
}
