package nc.pubimpl.pu.mpp.data.strategy.orderbill;

import nc.bs.pu.mpp.strategy.orderbill.InvoiceBatchFetcher;
import nc.pubitf.mpp.data.strategy.IFetchExecDataProviderStrategy;
import nc.vo.pl.tbb.PlBillBusiSysReg;
import nc.vo.pub.lang.UFDouble;
import nc.vo.tb.obj.NtbParamVO;

/**
 * @since 6.0
 * @version 2011-3-19 обнГ03:50:55
 * @author yinfy
 */

public class InvoiceNumFetchDataProviderStrategy implements
    IFetchExecDataProviderStrategy {

  @Override
  public UFDouble[][] getExecDataBatch(NtbParamVO[] param) {
    InvoiceBatchFetcher dmo = new InvoiceBatchFetcher();
    return dmo.getExecDataBatch(param, PlBillBusiSysReg.NNUM);
  }

}
