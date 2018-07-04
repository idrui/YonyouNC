package nc.pubimpl.pu.mpp.data.strategy.praybill;

import nc.bs.pu.mpp.strategy.orderaspraybill.OrderAsPrayBillBatchFetcher;
import nc.bs.pu.mpp.strategy.orderaspraybill.OrderCloseAsPrayBillBatchFetcher;
import nc.bs.pu.mpp.strategy.praybill.PrayBillBatchFetcher;
import nc.pubitf.mpp.data.strategy.IFetchReadyDataProviderStrategy;
import nc.vo.pl.tbb.PlBillBusiSysReg;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.tb.obj.NtbParamVO;

/**
 * @since 6.0
 * @version 2011-3-19 下午12:39:46
 * @author yinfy
 */

public abstract class PrayBillTaxMnyFetchDataProviderStrategy implements
    IFetchReadyDataProviderStrategy {

  @Override
  public UFDouble[][] getReadyDataBatch(NtbParamVO[] param) {
    return null;
  }

  private UFDouble getZeroIfLessZero(UFDouble value) {
    if (MathTool.compareTo(value, UFDouble.ZERO_DBL) < 0) {
      return UFDouble.ZERO_DBL;
    }
    return value;
  }

  protected UFDouble[][] getReadyDataBatch(NtbParamVO[] param,
      String exebilltype) {
    UFDouble[][] result = new UFDouble[param.length][4];

    PrayBillBatchFetcher dmo = new PrayBillBatchFetcher();

    OrderAsPrayBillBatchFetcher orderasPray = new OrderAsPrayBillBatchFetcher();
    orderasPray.setExebilltype(exebilltype);

    OrderCloseAsPrayBillBatchFetcher ordercloseasPray =
        new OrderCloseAsPrayBillBatchFetcher();
    ordercloseasPray.setExebilltype(exebilltype);

    // 取未关闭的请购单的数量
    UFDouble[][] part = dmo.getReadyDataBatch(param, PlBillBusiSysReg.NTAXMNY);
    if (null != part) {
      for (int i = 0; i < result.length; i++) {
        if (null != part[i]) {
          result[i][0] = MathTool.add(result[i][0], part[i][0]);
          result[i][1] = MathTool.add(result[i][1], part[i][1]);
          result[i][2] = MathTool.add(result[i][2], part[i][2]);
          result[i][3] = MathTool.add(result[i][3], part[i][3]);
        }
      }
    }
    // 取已关闭的请购单的累积订单数量
    part = orderasPray.getReadyDataBatch(param, PlBillBusiSysReg.NTAXMNY);
    // 两个结果累加
    if (null != part) {
      for (int i = 0; i < result.length; i++) {
        if (null != part[i]) {
          result[i][0] = MathTool.add(result[i][0], part[i][0]);
          result[i][1] = MathTool.add(result[i][1], part[i][1]);
          result[i][2] = MathTool.add(result[i][2], part[i][2]);
          result[i][3] = MathTool.add(result[i][3], part[i][3]);
        }
      }
    }

    part = ordercloseasPray.getReadyDataBatch(param, PlBillBusiSysReg.NTAXMNY);
    // 两个结果累加
    if (null != part) {
      for (int i = 0; i < result.length; i++) {
        if (null != part[i]) {
          result[i][0] = MathTool.add(result[i][0], part[i][0]);
          result[i][1] = MathTool.add(result[i][1], part[i][1]);
          result[i][2] = MathTool.add(result[i][2], part[i][2]);
          result[i][3] = MathTool.add(result[i][3], part[i][3]);
        }
      }
    }
    for (UFDouble[] values : result) {
      for (UFDouble value : values) {
        value = this.getZeroIfLessZero(value);
      }
    }
    return result;
  }
}
