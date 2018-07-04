package nc.vo.pu.m21.rule;

import nc.pubitf.uapbd.CurrencyRateUtil;
import nc.vo.pu.m21.entity.PayPlanVO;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleObjectFactory;
import nc.vo.scmpub.payterm.pay.AbstractPayPlanVO;

/**
 * @since 6.0
 * @version 2011-1-23 下午01:47:49
 * @author wuxla
 */

public class PayPlanMnyCalcByRate {
  public static final UFDouble UF100 = new UFDouble(100);

  private UFDouble accMny = UFDouble.ZERO_DBL;

  private UFDouble accOrigMny = UFDouble.ZERO_DBL;

  private UFDouble accRate = UFDouble.ZERO_DBL;

  private String ccurrencyid;

  private String corigcurrencyid;

  private IKeyValue keyValue;

  private UFDouble ntotallocalmny = UFDouble.ZERO_DBL;

  private UFDouble ntotalorigmny = UFDouble.ZERO_DBL;

  public PayPlanMnyCalcByRate(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  public void calcMnyByRate(int row, UFDouble nrate) {
    this.init(row, nrate);

    UFDouble norigmny = null;
    UFDouble nmny = null;
    if (MathTool.compareTo(this.accRate, PayPlanMnyCalcByRate.UF100) == 0) {
      norigmny = MathTool.sub(this.ntotalorigmny, this.accOrigMny);
      nmny = MathTool.sub(this.ntotallocalmny, this.accMny);
    }
    else {
      int orgDigit =
          new ScaleObjectFactory.CurrtypeScaleObject(2, 4)
              .getDigit(this.corigcurrencyid);
      int digit =
          new ScaleObjectFactory.CurrtypeScaleObject(2, 4)
              .getDigit(this.ccurrencyid);
      norigmny =
          this.ntotalorigmny.multiply(
              nrate.div(PayPlanMnyCalcByRate.UF100, UFDouble.DEFAULT_POWER),
              orgDigit);
      nmny =
          this.ntotallocalmny.multiply(
              nrate.div(PayPlanMnyCalcByRate.UF100, UFDouble.DEFAULT_POWER),
              digit);
    }

    this.keyValue.setBodyValue(row, AbstractPayPlanVO.NORIGMNY, norigmny);
    this.keyValue.setBodyValue(row, AbstractPayPlanVO.NMNY, nmny);
  }

  private void init(int row, UFDouble nrate) {
    String pk_order =
        (String) this.keyValue.getBodyValue(row, PayPlanVO.PK_ORDER);
    for (int i = 0; i < this.keyValue.getItemCount(); ++i) {
      if (row == i) {
        continue;
      }
      String iorder =
          (String) this.keyValue.getBodyValue(i, PayPlanVO.PK_ORDER);
      if (!pk_order.equals(iorder)) {
        continue;
      }
      UFDouble irate =
          (UFDouble) this.keyValue.getBodyValue(i, AbstractPayPlanVO.NRATE);
      this.accRate = MathTool.add(this.accRate, irate);
      UFDouble inorigmny =
          (UFDouble) this.keyValue.getBodyValue(i, AbstractPayPlanVO.NORIGMNY);
      this.accOrigMny = MathTool.add(this.accOrigMny, inorigmny);
      UFDouble inmny =
          (UFDouble) this.keyValue.getBodyValue(i, AbstractPayPlanVO.NMNY);
      this.accMny = MathTool.add(this.accMny, inmny);
    }
    // 加上操作行
    this.accRate = MathTool.add(this.accRate, nrate);

    this.ntotalorigmny =
        (UFDouble) this.keyValue.getBodyValue(row,
            AbstractPayPlanVO.NTOTALORIGMNY);
    this.corigcurrencyid =
        (String) this.keyValue.getBodyValue(row,
            AbstractPayPlanVO.CORIGCURRENCYID);
    this.ccurrencyid =
        (String) this.keyValue.getBodyValue(row, AbstractPayPlanVO.CCURRENCYID);
    UFDouble nexchangerate =
        (UFDouble) this.keyValue.getBodyValue(row,
            AbstractPayPlanVO.NEXCHANGERATE);
    String pk_fiorg =
        (String) this.keyValue.getBodyValue(row,
            AbstractPayPlanVO.PK_FINANCEORG);
    CurrencyRateUtil util = CurrencyRateUtil.getInstanceByOrg(pk_fiorg);
    try {
      this.ntotallocalmny =
          MathTool.nvl(util
              .getAmountByOpp(this.corigcurrencyid, this.ccurrencyid,
                  this.ntotalorigmny, nexchangerate, new UFDate()));
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

}
