package nc.pubimpl.pu.mpp.data.strategy.orderbill;

import nc.bs.pu.mpp.strategy.orderbill.OrderBatchFetcher;
import nc.pubitf.mpp.data.strategy.IFetchReadyDataProviderStrategy;
import nc.vo.pl.tbb.PlBillBusiSysReg;
import nc.vo.pub.lang.UFDouble;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.tb.obj.NtbParamVO;

/**
 * @since 6.0
 * @version 2011-3-19 ÏÂÎç12:41:22
 * @author yinfy
 */

public class OrderOrderNumFetchDataProviderStrategy implements
    IFetchReadyDataProviderStrategy {

  @Override
  public UFDouble[][] getReadyDataBatch(NtbParamVO[] param) {
    OrderBatchFetcher dmo = new OrderBatchFetcher();
    dmo.setExebilltype(POBillType.Order.getCode());
    return dmo.getReadyDataBatch(param, PlBillBusiSysReg.NNUM);
  }

}
