package nc.pubimpl.pu.tbb.strategy.praybill;

import nc.pubimpl.pu.tbb.strategy.IExecDataProviderStrategy;
import nc.vo.pu.tbb.PuBillBusiSysReg;
import nc.vo.pub.lang.UFDouble;
import nc.vo.tb.obj.NtbParamVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�빺������ȡ������
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
