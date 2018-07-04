package nc.pubimpl.pu.tbb.strategy.praybill;

import nc.pubimpl.pu.tbb.strategy.IExecDataProviderStrategy;
import nc.vo.pu.tbb.PuBillBusiSysReg;
import nc.vo.pub.lang.UFDouble;
import nc.vo.tb.obj.NtbParamVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>请购单数量取数策略
 * </ul>
 * <p>
 * <p>
 */
public class PrayBillNumExecDataProviderStrategy implements
    IExecDataProviderStrategy {

  @Override
  public UFDouble[][] getExecDataBatch(NtbParamVO[] param) throws Exception {
    PrayBillGetDataBatchDMO dmo = new PrayBillGetDataBatchDMO();
    return dmo.getExecDataBatch(param, PuBillBusiSysReg.NNUM);
  }

}
