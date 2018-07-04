package nc.vo.pu.m21.rule;

import java.util.ArrayList;
import java.util.List;

import nc.pubitf.uapbd.CurrencyRateUtil;
import nc.vo.pu.m21.entity.PayPlanVO;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.payterm.pay.AbstractPayPlanVO;

/**
 * @since 6.0
 * @version 2011-1-23 下午02:23:57
 * @author wuxla
 */

public class PayPlanRateCalcByMny {
  public static final UFDouble UF100 = new UFDouble(100);

  private UFDouble accMny = UFDouble.ZERO_DBL;

  private UFDouble accOrigMny = UFDouble.ZERO_DBL;

  private UFDouble accRate = UFDouble.ZERO_DBL;

  private String ccurrencyid;

  private String corigcurrencyid;

  private IKeyValue keyValue;

  private UFDouble ntempMny = UFDouble.ZERO_DBL;

  private UFDouble ntotallocalmny = UFDouble.ZERO_DBL;

  private UFDouble ntotalorigmny = UFDouble.ZERO_DBL;

  public PayPlanRateCalcByMny(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  public void calcMnyByRate(int row, UFDouble norigmny) {
    // rows的目的是将同一个订单的所有行拿出来，便于联动
    List<Integer> rows = this.getRows(row);
    this.init(row, norigmny, rows);
    if (MathTool.isZero(this.ntotalorigmny)) {
      return;
    }
    UFDouble nrate = null;
    UFDouble sumrate = UFDouble.ZERO_DBL;
    UFDouble nmny = null;
    if (0 == MathTool.compareTo(this.ntotalorigmny, this.accOrigMny)) {
      nrate = MathTool.sub(PayPlanRateCalcByMny.UF100, this.accRate);
      nmny = MathTool.sub(this.ntotallocalmny, this.accMny);
    }
    else {
      nmny = this.ntempMny;
      int j = 0;
      for(Integer index: rows){
        int i = index.intValue();
        this.keyValue.setBodyValue(i, AbstractPayPlanVO.NTOTALORIGMNY, this.accOrigMny);
        UFDouble indexNorigmny = (UFDouble)this.keyValue.getBodyValue(i, AbstractPayPlanVO.NORIGMNY);
        if(indexNorigmny==null){
        	return;
        }
        nrate = indexNorigmny.div(this.accOrigMny, UFDouble.DEFAULT_POWER)
                .multiply(PayPlanRateCalcByMny.UF100, 2);
        j++;
        if(j == rows.size()){
        	nrate = MathTool.sub(PayPlanRateCalcByMny.UF100, sumrate);
        }
        else{
        	 sumrate = sumrate.add(nrate);
        }    
        this.keyValue.setBodyValue(i, AbstractPayPlanVO.NRATE, nrate);
      }
    }
    this.keyValue.setBodyValue(row, AbstractPayPlanVO.NMNY, nmny);
  }

  private List<Integer> getRows(int row) {
    List<Integer> rows = new ArrayList<>();
    String pk_order = (String)this.keyValue.getBodyValue(row, PayPlanVO.PK_ORDER);
    for(int i = 0; i < this.keyValue.getItemCount(); i++){
      if(pk_order.equals(this.keyValue.getBodyValue(i, PayPlanVO.PK_ORDER))){
        rows.add(Integer.valueOf(i));
      }
    }
    return rows;
  }

  private void init(int row, UFDouble norigmny, List<Integer> rows) {
    for (Integer index: rows) {
      int i = index.intValue();
      if (row == i) {
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
    this.accOrigMny = MathTool.add(this.accOrigMny, norigmny);

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
      this.ntempMny =
          util.getAmountByOpp(this.corigcurrencyid, this.ccurrencyid, norigmny,
              nexchangerate, new UFDate());
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }
}
