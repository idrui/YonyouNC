package nc.ui.pu.m27.match.util;

import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.ClientContext;
import nc.ui.pubapp.scale.BillModelScaleProcessor;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.entity.StockSettleVO;

/**
 * 结算第一界面和第三界面的精度处理器
 * 
 * @since 6.0
 * @version 2011-1-24 下午09:28:56
 * @author zhyhang
 */
public class DispResultScaleProcesser {
  private BillListPanel blp;

  public DispResultScaleProcesser(BillListPanel blp) {
    super();
    this.blp = blp;
  }

  /**
   * 初始化精度处理
   */
  public void initScale() {
    String pk_group = ClientContext.getInstance().getPk_group();
    // 表头发票精度处理
    BillModelScaleProcessor bmsp =
        new BillModelScaleProcessor(pk_group, this.blp.getHeadBillModel());
    bmsp.setMnyCtlInfo(new String[] {
      InvoiceSettleVO.NCANSETTLEMNY, InvoiceSettleVO.NMNY
    }, InvoiceSettleVO.CCURRENCYID);
    bmsp.setNumCtlInfo(new String[] {
      InvoiceSettleVO.NCANSETTLENUM, InvoiceSettleVO.NNUM
    }, InvoiceSettleVO.CUNITID);
    bmsp.setPriceCtlInfo(new String[] {
      InvoiceItemVO.NPRICE
    }, InvoiceSettleVO.CCURRENCYID);
    bmsp.process();
    // 表体入库单精度处理
    bmsp = new BillModelScaleProcessor(pk_group, this.blp.getBodyBillModel());
    bmsp.setMnyCtlInfo(new String[] {
      StockSettleVO.NPURESETTLEMNY, StockSettleVO.NCANSETTLEMNY,
      StockSettleVO.NESTMNY, StockSettleVO.NACCGOODSSETTLEMNY,
      StockSettleVO.NCOSTMNY
    }, StockSettleVO.CCURRENCYID);
    bmsp.setNumCtlInfo(new String[] {
      StockSettleVO.NPURESETTLENUM, StockSettleVO.NCANSETTLENUM,
      StockSettleVO.NINNUM, StockSettleVO.NESTNUM,
      StockSettleVO.NACCUMSETTLENUM
    }, StockSettleVO.CUNITID);
    bmsp.setPriceCtlInfo(new String[] {
      StockSettleVO.NESTPRICE, StockSettleVO.NESTCALCOSTPRICE,
      StockSettleVO.NAVGSETTLEPRICE, StockSettleVO.NCOSTPRICE
    }, StockSettleVO.CORIGCURRENCYID);
    bmsp.process();
  }

}
