package nc.pubimpl.pu.mpp.data.strategy.orderbill;

import nc.bs.pu.mpp.strategy.orderbill.OrderBatchFetcher;
import nc.pubitf.mpp.data.strategy.IFetchReadyDataProviderStrategy;
import nc.vo.pl.tbb.PlBillBusiSysReg;
import nc.vo.pub.lang.UFDouble;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.tb.obj.NtbParamVO;

public class OrderPurchaseinNumFetchDataProviderStrategy implements
    IFetchReadyDataProviderStrategy {

  @Override
  public UFDouble[][] getReadyDataBatch(NtbParamVO[] param) {
    OrderBatchFetcher dmo = new OrderBatchFetcher();
    dmo.setExebilltype(ICBillType.PurchaseIn.getCode());
    return dmo.getReadyDataBatch(param, PlBillBusiSysReg.NNUM);
  }

}
