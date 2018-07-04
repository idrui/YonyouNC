package nc.vo.pu.m21.rule;

import nc.vo.pu.pub.rule.IScaleProcessor;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.PosEnum;
import nc.vo.pubapp.scale.TotalValueScale;
import nc.vo.scmpub.payterm.pay.AbstractPayPlanVO;

/**
 * @since 6.0
 * @version 2011-1-6 下午07:54:18
 * @author wuxla
 */

public class PayPlanScaleRule implements IScaleProcessor {

  @Override
  public void setScale(BillScaleProcessor scale, TotalValueScale totalScale) {
    if (scale != null) {
      this.setBodyScale(scale);
    }
  }

  @Override
  public void setScaleForSingleTable(BillScaleProcessor scale,
      TotalValueScale totalScale) {
    if (scale != null) {
      this.setScale(scale, PosEnum.head);
    }
  }

  private void setBodyScale(BillScaleProcessor scale) {
    this.setScale(scale, PosEnum.body);
  }

  private void setScale(BillScaleProcessor scale, PosEnum posEnum) {
    String[] mnykeys =
        new String[] {
          AbstractPayPlanVO.NACCUMPAYMNY, AbstractPayPlanVO.NACCUMPAYAPPMNY,
          AbstractPayPlanVO.NMNY
        };

    String[] orgmnykeys =
        new String[] {
          AbstractPayPlanVO.NACCUMPAYORGMNY,
          AbstractPayPlanVO.NACCUMPAYAPPORGMNY, AbstractPayPlanVO.NORIGMNY,
          AbstractPayPlanVO.NTOTALORIGMNY
        };

    // 本币金额精度
    scale.setMnyCtlInfo(mnykeys, posEnum, null, AbstractPayPlanVO.CCURRENCYID,
        posEnum, null);
    // 原币金额精度
    scale.setMnyCtlInfo(orgmnykeys, posEnum, null,
        AbstractPayPlanVO.CORIGCURRENCYID, posEnum, null);

    scale.process();
  }
}
