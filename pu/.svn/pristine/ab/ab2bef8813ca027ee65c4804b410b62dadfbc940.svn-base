package nc.pubimpl.pu.mpp.data.strategy.orderbill;

import nc.bs.pu.mpp.strategy.orderbill.PurchaseinBatchFetcher;
import nc.pubitf.mpp.data.strategy.IFetchExecDataProviderStrategy;
import nc.vo.pl.tbb.PlBillBusiSysReg;
import nc.vo.pub.lang.UFDouble;
import nc.vo.tb.obj.NtbParamVO;

/**
 * @since 6.0
 * @version 2011-3-19 ÏÂÎç03:29:35
 * @author yinfy
 */

public class PurchaseinNumFetchDataProviderStrategy implements
    IFetchExecDataProviderStrategy {

  @Override
  public UFDouble[][] getExecDataBatch(NtbParamVO[] param) {
    PurchaseinBatchFetcher dmo = new PurchaseinBatchFetcher();
    return dmo.getExecDataBatch(param, PlBillBusiSysReg.NNUM);
  }

}
