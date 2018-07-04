package nc.pubimpl.pu.mpp.data.strategy.orderaspraybill;

import nc.bs.pu.mpp.strategy.orderaspraybill.ArrivalAsPrayBillBatchFetcher;
import nc.pubitf.mpp.data.strategy.IFetchExecDataProviderStrategy;
import nc.vo.pl.tbb.PlBillBusiSysReg;
import nc.vo.pub.lang.UFDouble;
import nc.vo.tb.obj.NtbParamVO;

/**
 * @since 6.0
 * @version 2011-3-31 ÉÏÎç10:39:52
 * @author yinfy
 */

public class ArrivalAsPrayNumFetchDataProviderStrategy implements
    IFetchExecDataProviderStrategy {

  @Override
  public UFDouble[][] getExecDataBatch(NtbParamVO[] param) {
    ArrivalAsPrayBillBatchFetcher dmo = new ArrivalAsPrayBillBatchFetcher();
    return dmo.getExecDataBatch(param, PlBillBusiSysReg.NNUM);
  }

}
