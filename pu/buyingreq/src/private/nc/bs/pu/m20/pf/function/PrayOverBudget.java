package nc.bs.pu.m20.pf.function;

import java.util.ArrayList;
import java.util.List;

import nc.bs.pu.pf.AbstractOverBudget;
import nc.itf.tb.control.IAccessableBusiVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.entity.tbb.PrayBillBudgetCtlVO;
import nc.vo.pu.tbb.BillOperationEnum;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * Çë¹º³¬Ô¤Ëã½ð¶î
 * Çë¹º³¬Ô¤ËãÊýÁ¿
 * 
 * @since 6.0
 * @version 2011-4-22 ÏÂÎç03:13:48
 * @author wuxla
 */

public class PrayOverBudget extends AbstractOverBudget {

  @Override
  protected IAccessableBusiVO[] getBusivo(AggregatedValueObject aggVO) {
    PraybillVO vo = (PraybillVO) aggVO;
    List<IAccessableBusiVO> budgetList = new ArrayList<IAccessableBusiVO>();
    String[] execbilltypes =
        new String[] {
          POBillType.Order.getCode(), POBillType.Arrive.getCode(),
          POBillType.Invoice.getCode(), ICBillType.PurchaseIn.getCode()
        };
    for (PraybillItemVO item : vo.getBVO()) {
      for (String exec : execbilltypes) {
        budgetList.add(new PrayBillBudgetCtlVO(vo.getHVO(), item,
            BillOperationEnum.APPROVE.getValue(), exec));
      }
    }

    return budgetList.toArray(new IAccessableBusiVO[budgetList.size()]);
  }

}
