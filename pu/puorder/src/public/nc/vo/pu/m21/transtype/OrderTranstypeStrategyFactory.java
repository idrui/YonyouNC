package nc.vo.pu.m21.transtype;

import nc.vo.scmpub.res.billtype.CTBillType;
import nc.vo.scmpub.res.billtype.ECBillType;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.INVPBillType;
import nc.vo.scmpub.res.billtype.MMBillType;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.res.billtype.PPBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.scmpub.res.billtype.TOBillType;

public class OrderTranstypeStrategyFactory {
  public static IOrderTranstypeStrategy getTranstypeStrategy(String src_billtype) {
    if (SOBillType.Order.getCode().equals(src_billtype)) {
      return new PurHeadStockHeadMarHeadStrategy();
    }
    else if (CTBillType.PurDaily.getCode().equals(src_billtype)) {
      return new PurHeadStockHeadMarHeadStrategy();
    }
    else if (ICBillType.BorrowIn.getCode().equals(src_billtype)) {
      return new PurBodyStockBodyMarBodyStrategy();
    }
    else if (PPBillType.PriceAudit.getCode().equals(src_billtype)) {
      return new PurHeadStockHeadMarHeadStrategy();
    }
    else if (TOBillType.TransOrder.getCode().equals(src_billtype)) {
      return new PurBodyStockBodyMarBodyStrategy();
    }
    else if (POBillType.Arrive.getCode().equals(src_billtype)) {
      return new PurHeadStockHeadMarHeadStrategy();
    }
    else if (INVPBillType.PoOrder.getCode().equals(src_billtype)) {
      return new SingleHeadStrategy();
    }
    else if (MMBillType.LsProduceOrder.getCode().equals(src_billtype)) {
      return new SingleHeadStrategy();
    }
    else if (MMBillType.PlanOrder.getCode().equals(src_billtype)) {
      return new SingleHeadStrategy();
    }
    else if (MMBillType.ProduceOrder.getCode().equals(src_billtype)) {
      return new PurBodyStockBodyMarBodyStrategy();
    }
    else if (ECBillType.EC49.getCode().equals(src_billtype)) {
      return new PurHeadStockBodyMarBodyStrategy();
    }
    else if (ECBillType.EC38.getCode().equals(src_billtype)) {
      return new PurHeadStockBodyMarBodyStrategy();
    }
    return null;
  }
}
