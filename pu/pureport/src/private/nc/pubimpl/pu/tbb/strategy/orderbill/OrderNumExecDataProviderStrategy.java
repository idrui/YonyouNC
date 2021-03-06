package nc.pubimpl.pu.tbb.strategy.orderbill;

import nc.pubimpl.pu.tbb.strategy.IExecDataProviderStrategy;
import nc.vo.pu.tbb.PuBillBusiSysReg;
import nc.vo.pub.lang.UFDouble;
import nc.vo.tb.obj.NtbParamVO;

/**
 * 订单数量取数策略
 * 
 * @since 6.0
 * @version 2011-3-7 下午04:50:20
 * @author yinfy
 */
public class OrderNumExecDataProviderStrategy implements
    IExecDataProviderStrategy {

  @Override
  public UFDouble[][] getExecDataBatch(NtbParamVO[] param) throws Exception {
    OrderBillGetDataBatchDMO dmo = new OrderBillGetDataBatchDMO();
    return dmo.getExecDataBatch(param, PuBillBusiSysReg.NNUM);
  }

}
