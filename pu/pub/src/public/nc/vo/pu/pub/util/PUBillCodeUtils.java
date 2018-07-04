package nc.vo.pu.pub.util;

import nc.bs.scmpub.app.flow.billcode.BillCodeInfoBuilder;
import nc.impl.pubapp.bill.billcode.BillCodeInfo;
import nc.impl.pubapp.bill.billcode.BillCodeUtils;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m24.entity.PricestlHeaderVO;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m27.entity.SettleBillHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.scmpub.res.billtype.POBillType;

public class PUBillCodeUtils {
  
  /**
   * 物资需求申请单单据规则
   * 
   * @return
   */
  public static BillCodeUtils getStorereqCode(){
    BillCodeInfo info = 
        BillCodeInfoBuilder.buildBillCodeInfo(POBillType.MRBill.getCode(),
        StoreReqAppHeaderVO.VBILLCODE, StoreReqAppHeaderVO.PK_GROUP,
        StoreReqAppHeaderVO.PK_ORG, StoreReqAppHeaderVO.VTRANTYPECODE);
    
    return new BillCodeUtils(info);
  }
  
  /**
   * 请购单单据规则
   * 
   * @return
   */
  public static BillCodeUtils getPraybillCode(){
    BillCodeInfo info =
        BillCodeInfoBuilder.buildBillCodeInfo(POBillType.PrayBill.getCode(),
            PraybillHeaderVO.VBILLCODE, PraybillHeaderVO.PK_GROUP,
            PraybillHeaderVO.PK_ORG, PraybillHeaderVO.VTRANTYPECODE);
    
    return new BillCodeUtils(info);
  }

  /**
   * 采购订单单据规则
   * 
   * @return
   */
  public static BillCodeUtils getOrderCode(){
    BillCodeInfo info = 
        BillCodeInfoBuilder.buildBillCodeInfo(POBillType.Order.getCode(),
        OrderHeaderVO.VBILLCODE, OrderHeaderVO.PK_GROUP, 
        OrderHeaderVO.PK_ORG, OrderHeaderVO.VTRANTYPECODE);
    
    return new BillCodeUtils(info);
  }
  
  /**
   * 到货单单据规则
   * 
   * @return
   */
  public static BillCodeUtils getArriveCode(){
    BillCodeInfo info =
        BillCodeInfoBuilder.buildBillCodeInfo(POBillType.Arrive.getCode(), 
            ArriveHeaderVO.VBILLCODE, ArriveHeaderVO.PK_GROUP, 
            ArriveHeaderVO.PK_ORG, ArriveHeaderVO.VTRANTYPECODE);
    
    return new BillCodeUtils(info);
  }
  
  
  /**
   * 采购发票单据规则
   * 
   * @return
   */
  public static BillCodeUtils getInvoiceCode(){
    BillCodeInfo info =
        BillCodeInfoBuilder.buildBillCodeInfo(POBillType.Invoice.getCode(),
            InvoiceHeaderVO.VBILLCODE, InvoiceHeaderVO.PK_GROUP,
            InvoiceHeaderVO.PK_ORG, InvoiceHeaderVO.VTRANTYPECODE);
    
    return new BillCodeUtils(info);
  }
  
  /**
   * 价格结算单单据规则
   * 
   * @return
   */
  public static BillCodeUtils getPricestlCode(){
    BillCodeInfo info =
        BillCodeInfoBuilder.buildBillCodeInfo(POBillType.PriceStl.getCode(),
            PricestlHeaderVO.VBILLCODE, PricestlHeaderVO.PK_GROUP,
            PricestlHeaderVO.PK_ORG, null);
    
    return new BillCodeUtils(info);
  }
  
  /**
   * 结算单单据规则
   * 
   * @return
   */
  public static BillCodeUtils getSettleBillCode(){
    BillCodeInfo info =
        BillCodeInfoBuilder.buildBillCodeInfo(POBillType.SettleBill.getCode(),
            SettleBillHeaderVO.VBILLCODE, SettleBillHeaderVO.PK_GROUP,
            SettleBillHeaderVO.PK_ORG, null);
    
    return new BillCodeUtils(info);
  }
  
  
  /**
   * 期初暂估单单据规则
   * 
   * @return
   */
  public static BillCodeUtils getInitialEstCode(){
    BillCodeInfo info =
        BillCodeInfoBuilder.buildBillCodeInfo(POBillType.InitEstimate.getCode(),
            InitialEstHeaderVO.VBILLCODE, InitialEstHeaderVO.PK_GROUP,
            InitialEstHeaderVO.PK_ORG, InitialEstHeaderVO.VTRANTYPECODE);
    
    return new BillCodeUtils(info);
  }
}
