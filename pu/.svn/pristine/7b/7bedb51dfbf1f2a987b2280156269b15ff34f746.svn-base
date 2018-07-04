package nc.pubimpl.pu.mpp.data.strategy.orderaspraybill;

import nc.bs.pu.mpp.strategy.orderaspraybill.PurchaseinAsPrayBillBatchFetcher;
import nc.pubitf.mpp.data.strategy.IFetchExecDataProviderStrategy;
import nc.vo.pl.tbb.PlBillBusiSysReg;
import nc.vo.pub.lang.UFDouble;
import nc.vo.tb.obj.NtbParamVO;

/**
 * @since 6.0
 * @version 2011-3-31 ÉÏÎç10:47:46
 * @author yinfy
 */

public class PurchaseinAsPrayTaxMnyFetchDataProviderStrategy implements
    IFetchExecDataProviderStrategy {

  @Override
  public UFDouble[][] getExecDataBatch(NtbParamVO[] param) {
    PurchaseinAsPrayBillBatchFetcher dmo =
        new PurchaseinAsPrayBillBatchFetcher();
    return dmo.getExecDataBatch(param, PlBillBusiSysReg.NTAXMNY);
  }

}
