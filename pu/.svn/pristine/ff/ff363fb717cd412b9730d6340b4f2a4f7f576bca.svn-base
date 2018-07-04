package nc.pubimpl.pu.tbb.strategy.invoicebill;

import nc.pubimpl.pu.tbb.strategy.IExecDataProviderStrategy;
import nc.vo.pu.tbb.PuBillBusiSysReg;
import nc.vo.pub.lang.UFDouble;
import nc.vo.tb.obj.NtbParamVO;

/**
 * @since 6.0
 * @version 2011-3-8 ионГ11:09:16
 * @author yinfy
 */

public class InvoiceTaxMnyExecDataProviderStrategy implements
    IExecDataProviderStrategy {

  @Override
  public UFDouble[][] getExecDataBatch(NtbParamVO[] param) throws Exception {
    InvoiceBillGetDataBatchDMO dmo = new InvoiceBillGetDataBatchDMO();
    return dmo.getExecDataBatch(param, PuBillBusiSysReg.NTAXMNY);
  }

}
