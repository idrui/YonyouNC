package nc.pubimpl.pu.tbb.strategy.invoicebill;

import nc.pubimpl.pu.tbb.strategy.IExecDataProviderStrategy;
import nc.vo.pu.tbb.PuBillBusiSysReg;
import nc.vo.pub.lang.UFDouble;
import nc.vo.tb.obj.NtbParamVO;

/**
 * 采购发票金额取数
 * 
 * @since 6.0
 * @version 2011-3-8 上午10:36:13
 * @author yinfy
 */

public class InvoiceMnyExecDataProviderStrategy implements
    IExecDataProviderStrategy {

  @Override
  public UFDouble[][] getExecDataBatch(NtbParamVO[] param) throws Exception {
    InvoiceBillGetDataBatchDMO dmo = new InvoiceBillGetDataBatchDMO();
    return dmo.getExecDataBatch(param, PuBillBusiSysReg.NMNY);
  }

}
